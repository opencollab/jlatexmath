/* JLaTeXMathXMLHandler.java
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

import org.apache.fop.render.Graphics2DAdapter;
import org.apache.fop.render.Renderer;
import org.apache.fop.render.RendererContext;
import org.apache.fop.render.XMLHandler;

import org.scilab.forge.jlatexmath.fop.image.loader.Graphics2DImagePainterJLaTeXMath;

import org.w3c.dom.Document;

/**
 * XMLHandler which draws LaTeX through a fop G2DAdapter.
 */
public class JLaTeXMathXMLHandler implements XMLHandler {

    public JLaTeXMathXMLHandler() { }

    public void handleXML(RendererContext context, Document document, String ns) throws Exception {
        Graphics2DAdapter g2dAdapter = context.getRenderer().getGraphics2DAdapter();
        
        if (g2dAdapter != null) {
            g2dAdapter.paintImage(new Graphics2DImagePainterJLaTeXMath(document), context,
                                  ((Integer) context.getProperty("xpos")).intValue(),
                                  ((Integer) context.getProperty("ypos")).intValue(),
                                  ((Integer) context.getProperty("width")).intValue(),
                                  ((Integer) context.getProperty("height")).intValue());
        }
    }

    public boolean supportsRenderer(Renderer renderer) {
        return renderer.getGraphics2DAdapter() != null;
    }
    
    public String getMimeType() {
        return JLaTeXMathObj.MIME_TYPE;
    }

    public String getNamespace() {
        return JLaTeXMathObj.JLATEXMATH_NS;
    }
}
