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
import java.awt.geom.Point2D;

/**
 * A box representing a rotated box.
 */
public class RotateBox extends Box {

    public static final int BL = 0;
    public static final int BR = 1;
    public static final int TL = 2;
    public static final int TR = 3;
    public static final int BBL = 4;
    public static final int BBR = 5;
    public static final int C = 6;

    protected double angle = 0;
    private Box box;
    private float xmax, xmin, ymax, ymin;
    private int option;
    
    private float shiftX;
    private float shiftY;

    //origin=loc; determines the point about which the rotation occurs; default is bl for bottom left corner; also possible are c for center, t for top, r for right, and B for baseline; any sensible combination, such as tr, is allowed;

    public RotateBox(Box b, double angle, int option) {
	this.box = b;
	angle = angle * Math.PI / 180;
	this.angle = angle;
	height = b.height;
	depth = b.depth;
	width = b.width;
	float s = (float) Math.sin(angle);
	float c = (float) Math.cos(angle);
	Point2D.Float origin = calculateShift(c, s, option);
	shiftX = origin.x * (1 - c) + origin.y * s;
	shiftY = origin.y * (1 - c) - origin.x * s;
	xmax = Math.max(-height * s, Math.max(depth * s, Math.max(width * c + depth * s, width * c - height * s))) + shiftX;
	xmin = Math.min(-height * s, Math.min(depth * s, Math.min(width * c + depth * s, width * c - height * s))) + shiftX;
	ymax = Math.max(height * c, Math.max(-depth * c, Math.max(width * s - depth * c, width * s + height * c)));
	ymin = Math.min(height * c, Math.min(-depth * c, Math.min(width * s - depth * c, width * s + height * c)));
	width = xmax - xmin;
	height = ymax + shiftY;
	depth = -ymin - shiftY;
    }

    public static int getOption(String option) {
	if (option == null || option.length() == 0) {
	    return BL;
	}
	option = option.trim();
	if (!option.startsWith("origin=")) {
	    return BL;
	}

	option = option.substring("origin=".length());
    
	if (option.equals("bl")) {
	    return BL;
	} else if (option.equals("c")) {
	    return C;
	} else if (option.equals("br")) {
	    return BR;
	} else if (option.equals("tl")) {
	    return TL;
	} else if (option.equals("tr")) {
	    return TR;
	} else if (option.equals("Bl")) {
	    return BBL;
	} else if (option.equals("Br")) {
	    return BBR;
	}

	return BL;
    }
    
    private Point2D.Float calculateShift(float s, float c, int option) {
	Point2D.Float p = new Point2D.Float(0, -box.depth);
	switch (option) {
	case BL :
	    p.x = 0;
	    p.y = -box.depth;
	    break;
	case C :
	    p.x = box.width / 2;
	    p.y = (box.height - box.depth) / 2;
	    break;
	case BR :
	    p.x = box.width;
	    p.y = - box.depth;
	    break;
	case TL :
	    p.x = 0;
	    p.y = box.height;
	    break;
	case TR :
	    p.x = box.width;
	    p.y = box.height;
	    break;
	case BBL :
	    p.x = 0;
	    p.y = 0;
	    break;
	case BBR :
	    p.x = box.width;
	    p.y = 0;
	    break;
	default :
	}

	return p;
    }

    public void draw(Graphics2D g2, float x, float y) {
	drawDebug(g2, x, y);
	y -= shiftY;
	x = x - xmin + shiftX;
	g2.rotate(-angle, x, y);
	box.draw(g2, x, y);
	g2.rotate(angle, x, y);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}
