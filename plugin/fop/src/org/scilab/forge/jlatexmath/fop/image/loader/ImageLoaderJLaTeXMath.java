/* JLaTeXMathObj.java
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

import java.io.IOException;
import java.util.Map;

import org.scilab.forge.jlatexmath.fop.image.ImageJLaTeXMath;

import org.apache.xmlgraphics.image.loader.Image;
import org.apache.xmlgraphics.image.loader.ImageException;
import org.apache.xmlgraphics.image.loader.ImageFlavor;
import org.apache.xmlgraphics.image.loader.ImageInfo;
import org.apache.xmlgraphics.image.loader.ImageSessionContext;
import org.apache.xmlgraphics.image.loader.impl.AbstractImageLoader;
import org.apache.xmlgraphics.image.loader.impl.ImageXMLDOM;

import org.scilab.forge.jlatexmath.fop.JLaTeXMathObj;

public class ImageLoaderJLaTeXMath extends AbstractImageLoader {

    private final ImageFlavor targetFlavor;

    /**
     * Main constructor.
     */
    public ImageLoaderJLaTeXMath(final ImageFlavor target) {
        if (!(ImageJLaTeXMath.FLAVOR.equals(target))) {
            throw new IllegalArgumentException("Unsupported target ImageFlavor: " + target);
        }
        this.targetFlavor = target;
    }

    /** {@inheritDoc} */
    public ImageFlavor getTargetFlavor() {
        return this.targetFlavor;
    }

    /** {@inheritDoc} */
    public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
        if (!JLaTeXMathObj.MIME_TYPE.equals(info.getMimeType())) {
            throw new IllegalArgumentException("ImageInfo must be from an LaTeX image");
        }
        
	final Image img = info.getOriginalImage();
        if (!(img instanceof ImageJLaTeXMath)) {
            throw new IllegalArgumentException("ImageInfo was expected to contain the JLaTeXMath image");
        }
        
	ImageJLaTeXMath jlmImage = (ImageJLaTeXMath) img;

        return jlmImage;
    }
}
