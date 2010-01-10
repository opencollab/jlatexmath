/* GraphicsBox.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 * 
 * Copyright (C) 2009 DENIZET Calixte
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

package org.scilab.forge.jlatexmath;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * A box representing a box containing a graphics.
 */
public class GraphicsBox extends Box {

    private BufferedImage image;

    public GraphicsBox(BufferedImage image, int Width, int Height, float width, float height) {
	this.image = image;
	this.width = width;
	this.height = height;
	depth = 0;
	shift = 0;
    }
   
    public void draw(Graphics2D g2, float x, float y) {
	AffineTransform oldAt = g2.getTransform();
	g2.translate(x, y - height);
	g2.scale(Math.abs(1 / oldAt.getScaleX()), Math.abs(1 / oldAt.getScaleY()));
	g2.drawImage(image, 0, 0, null);
	g2.setTransform(oldAt);
    }
    
    public int getLastFontId() {
	return 0;
    }
}