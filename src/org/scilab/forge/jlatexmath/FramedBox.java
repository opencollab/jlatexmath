/* FramedBox.java
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
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.Color;

/**
 * A box representing a rotated box.
 */
public class FramedBox extends Box {
    
    private Box box;
    private float thickness;
    private Color line = null;

    public FramedBox(Box box, float thickness) {
	this.box = box;thickness += 0.00f;
	this.width = box.width + 2 * thickness;
	this.height = box.height + thickness;
	this.depth = box.depth + thickness;
	this.shift = box.shift;
	this.thickness = thickness;
    }

    public FramedBox(Box box, float thickness, Color line) {
	this(box, thickness);
	this.line = line;
    }

    public void draw(Graphics2D g2, float x, float y) {
	box.draw(g2, x, y);
	Stroke st = g2.getStroke();
	AffineTransform oldAt = g2.getTransform();
	g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
	if (line != null) {
	    Color prev = g2.getColor();
	    g2.setColor(line);
	    g2.draw(new Rectangle2D.Float(x, y - box.getHeight(), box.getWidth(), box.getHeight() + box.getDepth()));
	    g2.setColor(prev);
	} else {
	    g2.draw(new Rectangle2D.Float(x, y - box.getHeight(), box.getWidth(), box.getHeight() + box.getDepth()));
	}

	g2.setStroke(st);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}