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
import java.util.Map;
import java.util.HashMap;

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

import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

public class JLaTeXMathElement extends JLaTeXMathObj {
    
    private float size;
    private String fwidth;
    private Color fg;
    private TeXIcon icon = null;
    
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
        calculate();
        return new Point2D.Float(icon.getTrueIconWidth(), icon.getTrueIconHeight());
    }
    
    public Length getIntrinsicAlignmentAdjust() {
        calculate();
        return FixedLength.getInstance(-icon.getTrueIconDepth(), "px");
    }
    
    private void calculate() {
        if (icon != null) {
            return;
        }
        
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
        
	fwidth = e.getAttribute("fwidth");
	float[] f = SpaceAtom.getLength(fwidth);
	
	if (f.length == 2) {
	    icon = new TeXFormula(code).createTeXIcon(st, size, (int) f[0], f[1], TeXConstants.ALIGN_CENTER);
	} else {
	    fwidth = "";
	    icon = new TeXFormula(code).createTeXIcon(st, size);
	}
    }    
    
    protected PropertyList createPropertyList(final PropertyList pList,
                                              final FOEventHandler foEventHandler) throws FOPException {
        FOUserAgent userAgent = this.getUserAgent();
        CommonFont commonFont = pList.getFontProps();
        this.size = (float) commonFont.fontSize.getNumericValue() / 1000;
        
        Property colorProp = pList.get(org.apache.fop.fo.Constants.PR_COLOR);
        this.fg = colorProp != null ? colorProp.getColor(userAgent) : null;
        
        return super.createPropertyList(pList, foEventHandler);
    }
}