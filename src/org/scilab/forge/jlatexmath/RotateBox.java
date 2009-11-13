/* RotateBox.java
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
public class RotateBox extends Box {

    protected double angle = 0;
    private Box box;
    private float xmax, xmin, ymax, ymin;

    public RotateBox(Box b, double angle) {
	this.box = b;
	angle = angle * Math.PI / 180;
	this.angle = angle;
	height = b.height;
	depth = b.depth;
	width = b.width;
	float s = (float)Math.sin(angle);
	float c = (float)Math.cos(angle);
	xmax = Math.max(-height*s, Math.max(depth*s, Math.max(width*c+depth*s,width*c-height*s)));
	xmin = Math.min(-height*s, Math.min(depth*s, Math.min(width*c+depth*s,width*c-height*s)));
	ymax = Math.max(height*c, Math.max(-depth*c, Math.max(width*s-depth*c,width*s+height*c)));
	ymin = Math.min(height*c, Math.min(-depth*c, Math.min(width*s-depth*c,width*s+height*c)));
	width = xmax - xmin;
	height = ymax;
	depth = -ymin;
    }
    
    public void draw(Graphics2D g2, float x, float y) {
	x -= xmin;
	g2.rotate(-angle, x, y);
	box.draw(g2, x, y);
	g2.rotate(angle, x, y);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}
