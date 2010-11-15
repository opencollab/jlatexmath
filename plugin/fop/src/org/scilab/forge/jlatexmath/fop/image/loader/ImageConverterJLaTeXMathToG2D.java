/* ImageConverterJLaTeXMathToG2D.java
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

import java.util.Map;

import org.scilab.forge.jlatexmath.fop.image.ImageJLaTeXMath;

import org.apache.xmlgraphics.image.loader.Image;
import org.apache.xmlgraphics.image.loader.ImageException;
import org.apache.xmlgraphics.image.loader.ImageFlavor;
import org.apache.xmlgraphics.image.loader.impl.AbstractImageConverter;
import org.apache.xmlgraphics.image.loader.impl.ImageGraphics2D;

/**
 * Convert a LaTeX label to a Graphics2d Painter.
 * @author Calixte DENIZET
 */
public class ImageConverterJLaTeXMathToG2D extends AbstractImageConverter {

    /**
     * Default Constructor.
     */
    public ImageConverterJLaTeXMathToG2D() { }

    /** {@inheritDoc} */
    public Image convert(Image src, Map hints) throws ImageException {
        return new ImageGraphics2D(src.getInfo(), new Graphics2DImagePainterJLaTeXMath(((ImageJLaTeXMath) src).getIcon()));
    }

    /** {@inheritDoc} */
    public ImageFlavor getSourceFlavor() {
        return ImageJLaTeXMath.FLAVOR;
    }

    /** {@inheritDoc} */
    public ImageFlavor getTargetFlavor() {
        return ImageFlavor.GRAPHICS2D;
    }
}
