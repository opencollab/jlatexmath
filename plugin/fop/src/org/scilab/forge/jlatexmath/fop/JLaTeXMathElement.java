/* JLaTeXMathElement.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2010 DENIZET Calixte
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 *
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 */

/* This file is largely inspired by files wrote by Jeremias Maerki,
 * for the fop plugin of barcode4j available at
 * http://barcode4j.sourceforge.net/
 */

package org.scilab.forge.jlatexmath.fop;

import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.SpaceAtom;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.datatypes.Length;
import org.apache.fop.fo.FOEventHandler;
import org.apache.fop.fo.FONode;
import org.apache.fop.fo.XMLObj;
import org.apache.fop.fo.PropertyList;
import org.apache.fop.fo.properties.CommonFont;
import org.apache.fop.fo.properties.FixedLength;
import org.apache.fop.fo.properties.Property;
import org.apache.fop.fo.properties.PercentLength;
import org.apache.fop.datatypes.LengthBase;
import org.apache.fop.fo.properties.LengthProperty;
import org.apache.fop.fo.flow.BlockContainer;
import org.apache.fop.fo.FONode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

public class JLaTeXMathElement extends JLaTeXMathObj {

    private float size;
    private Color fg;
    private TeXIcon icon = null;
    private boolean bool = true;
    private String PR_COLOR = "PR_COLOR";

    public JLaTeXMathElement(FONode parent) {
        super(parent);
    }

    public void processNode(final String elementName, final Locator locator,
                            final Attributes attlist, final PropertyList propertyList)
        throws FOPException {
        super.processNode(elementName, locator, attlist, propertyList);
        Element e = createBasicDocument().getDocumentElement();
        e.setAttribute("size", "" + size);
        e.setAttribute("fg", "" + fg.getRGB());
    }

    public Point2D getDimension(Point2D p) {
        if (icon == null) {
            icon = calculate(doc, size);
        }
        return new Point2D.Float(icon.getTrueIconWidth(), icon.getTrueIconHeight());
    }

    public Length getIntrinsicAlignmentAdjust() {
        if (icon == null) {
            icon = calculate(doc, size);
        }
        return FixedLength.getInstance(-icon.getTrueIconDepth(), "px");
    }

    public static float getFWidth(String str) {
        StringTokenizer tok = new StringTokenizer(str, ",");
        int sum = 0;
        while (tok.hasMoreTokens()) {
            int i = 0;
            String s = tok.nextToken();
            for (; i < s.length() && !Character.isLetter(s.charAt(i)); i++);
            double w = 0;
            try {
                w = Double.parseDouble(s.substring(0, i));
            } catch (NumberFormatException e) {
                return 0.0f;
            }

            String unit = "px";
            if (i != s.length()) {
                unit = s.substring(i).toLowerCase();
            }

            sum += FixedLength.getInstance(w, unit).getValue();
        }

        return (float) (sum / 1000f);
    }

    public static TeXIcon calculate(Document doc, float size) {
        TeXIcon icon;
        Element e = doc.getDocumentElement();
        String code = e.getTextContent();
        String style = e.getAttribute("style");
        int st = TeXConstants.STYLE_DISPLAY;
        if ("text".equals(style)) {
            st = TeXConstants.STYLE_TEXT;
        } else if ("script".equals(style)) {
            st = TeXConstants.STYLE_SCRIPT;
        } else if ("script_script".equals(style)) {
            st = TeXConstants.STYLE_SCRIPT_SCRIPT;
        }

        NamedNodeMap attributes = e.getAttributes();
        int len = attributes.getLength();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < len; i++) {
            map.put(attributes.item(i).getNodeName(), attributes.item(i).getNodeValue());
        }

        String stfw = e.getAttribute("fwidth");
        if (stfw.length() != 0) {
            icon = new TeXFormula(code, map).createTeXIcon(st, size, TeXConstants.UNIT_PIXEL, getFWidth(stfw), TeXConstants.ALIGN_CENTER);
        } else {
            icon = new TeXFormula(code, map).createTeXIcon(st, size, true);
        }

        return icon;
    }

    protected PropertyList createPropertyList(final PropertyList pList,
                                              final FOEventHandler foEventHandler) throws FOPException {
        FOUserAgent userAgent = this.getUserAgent();
        CommonFont commonFont = pList.getFontProps();
        this.size = (float) commonFont.fontSize.getNumericValue() / 1000;

        int n = org.apache.fop.fo.Constants.PR_COLOR;
        try {
            n = org.apache.fop.fo.Constants.class.getDeclaredField(PR_COLOR).getInt(null);
        } catch (Exception e) {
            System.err.println("Error in getting field:\n" + e);
        }

        Property colorProp = pList.get(n);

        this.fg = colorProp != null ? colorProp.getColor(userAgent) : null;

        return super.createPropertyList(pList, foEventHandler);
    }
}