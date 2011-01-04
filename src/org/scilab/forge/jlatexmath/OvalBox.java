/* OvalBox.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 * 
 * Copyright (C) 2011 DENIZET Calixte
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
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.RoundRectangle2D;

/**
 * A box representing a rotated box.
 */
public class OvalBox extends FramedBox {
    
    private float shadowRule;

    public OvalBox(FramedBox fbox) {
	super(fbox.box, fbox.thickness, fbox.space);
    }

    public void draw(Graphics2D g2, float x, float y) {
	box.draw(g2, x + space + thickness, y);
	Stroke st = g2.getStroke();
	g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
	float th = thickness / 2;
	float r = 0.5f * Math.min(width - thickness, height + depth - thickness);
	g2.draw(new RoundRectangle2D.Float(x + th, y - height + th, width - thickness, height + depth - thickness, r, r));
	//drawDebug(g2, x, y);
	g2.setStroke(st);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}
