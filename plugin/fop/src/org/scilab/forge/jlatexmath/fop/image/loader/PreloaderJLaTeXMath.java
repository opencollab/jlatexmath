/* PreloaderJLaTeXMath.java
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

package org.scilab.forge.jlatexmath.fop.image.loader;

import java.awt.Color;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.fop.JLaTeXMathObj;
import org.scilab.forge.jlatexmath.fop.JLaTeXMathElement;
import org.scilab.forge.jlatexmath.fop.image.ImageJLaTeXMath;

import org.apache.xmlgraphics.image.loader.ImageContext;
import org.apache.xmlgraphics.image.loader.ImageInfo;
import org.apache.xmlgraphics.image.loader.ImageSize;
import org.apache.xmlgraphics.image.loader.impl.AbstractImagePreloader;

/**
 * Preloader
 * @author Calixte DENIZET
 */
public class PreloaderJLaTeXMath extends AbstractImagePreloader {

    public PreloaderJLaTeXMath() { }
    
    /** {@inheritDoc} */
    public ImageInfo preloadImage(String uri, Source src, ImageContext context) {
	Document doc;
	Element e;
	if (src instanceof DOMSource) {
	    doc = (Document) ((DOMSource) src).getNode();
	    e = doc.getDocumentElement();
	}
	else {
	    return null;
	}

	if (!"latex".equals(e.getTagName())) {
	    return null;
	}
	
	ImageInfo info = new ImageInfo(uri, JLaTeXMathObj.MIME_TYPE);
	ImageSize size = new ImageSize();

        float s = Float.parseFloat(e.getAttribute("size"));
        Color fg = new Color(Integer.parseInt(e.getAttribute("fg")));
	TeXIcon icon = JLaTeXMathElement.calculate(doc, s);
	icon.setForeground(fg);

	size.setSizeInMillipoints((int) (icon.getTrueIconWidth() * 1000), (int) (icon.getTrueIconHeight() * 1000));
        size.setBaselinePositionFromBottom((int) (icon.getTrueIconDepth() * 1000));
        size.setResolution(context.getSourceResolution());
        size.calcPixelsFromSize();
        info.setSize(size);

        ImageJLaTeXMath jlmImage = new ImageJLaTeXMath(info, icon);
        info.getCustomObjects().put(ImageInfo.ORIGINAL_IMAGE, jlmImage);

	return info;
    }
}
