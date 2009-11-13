/* ScaleBox.java
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
 * A box representing a scaled box.
 */
public class ScaleBox extends Box {

    private Box box;
    private double xscl, yscl;

    public ScaleBox(Box b, double xscl, double yscl) {
	this.box = b;
	this.xscl = xscl;
	this.yscl = yscl;
	width = b.width * (float)Math.abs(xscl);
	height = yscl > 0 ? b.height * (float)yscl : -b.depth * (float)yscl;
	depth = yscl > 0 ? b.depth * (float)yscl : -b.height * (float)yscl;
	shift = b.shift * (float)yscl;
    }
    
    public void draw(Graphics2D g2, float x, float y) {
	float dec = xscl < 0 ? width : 0;
	g2.translate(x + dec, y);
	g2.scale(xscl, yscl);
	box.draw(g2, 0, 0);
	g2.scale(1/xscl, 1/yscl);
	g2.translate(-x - dec, -y);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}
