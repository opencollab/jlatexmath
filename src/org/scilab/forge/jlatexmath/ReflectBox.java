/* ReflectBox.java
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

/**
 * A box representing a rotated box.
 */
public class ReflectBox extends Box {

    private Box box;

    public ReflectBox(Box b) {
	this.box = b;
	width = b.width;
	height = b.height;
	depth = b.depth;
	shift = b.shift;
    }
    
    public void draw(Graphics2D g2, float x, float y) {
	drawDebug(g2, x, y);
	g2.translate(x, y);
	g2.scale(-1, 1);
	box.draw(g2, -width, 0);
	g2.scale(-1, 1);
	g2.translate(-x, -y);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}
