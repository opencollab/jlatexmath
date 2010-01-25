/* Graphics2DImagePainterJLaTeXMath.java
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.apache.xmlgraphics.java2d.Graphics2DImagePainter;

public class Graphics2DImagePainterJLaTeXMath implements Graphics2DImagePainter {

    private Dimension dim;
    private TeXIcon icon;

    public Graphics2DImagePainterJLaTeXMath(Document doc) {
        Element e = doc.getDocumentElement();
        float size = Float.parseFloat(e.getAttribute("size"));
        Color fg = new Color(Integer.parseInt(e.getAttribute("fg")));
        
        String style = e.getAttribute("style");
        int st = TeXConstants.STYLE_DISPLAY;
        if ("text".equals(style)) {
            st = TeXConstants.STYLE_TEXT;
        } else if ("script".equals(style)) {
            st = TeXConstants.STYLE_SCRIPT;
        } else if ("script_script".equals(style)) {
            st = TeXConstants.STYLE_SCRIPT_SCRIPT;
        }
        
        icon = new TeXFormula(e.getTextContent()).createTeXIcon(st, size);
        icon.setForeground(fg);

        dim = new Dimension((int) (icon.getTrueIconWidth() * 1000), (int) (icon.getTrueIconHeight() * 1000));
    }

    public int getDepth() {
        return (int) (icon.getTrueIconDepth() * 1000);
    }

    public Dimension getImageSize() {
        return dim;
    }

    public void paint(Graphics2D g2d, Rectangle2D rect2d) {
        icon.paintIcon(null, g2d, (int) rect2d.getX(), (int) rect2d.getY());
    }
}
