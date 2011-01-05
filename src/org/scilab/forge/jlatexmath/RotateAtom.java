/* RotateAtom.java
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

import java.util.Map;

/**
 * An atom representing a rotated Atom.
 */
public class RotateAtom extends Atom {
    
    private Atom base;
    private double angle;
    private int option = -1;
    private int xunit, yunit;
    private float x, y;
    
    public RotateAtom(Atom base, String angle, String option) {
	this.type = base.type;
	this.base = base;
	this.angle = Double.parseDouble(angle);
	this.option = RotateBox.getOrigin(option);
    }

    public RotateAtom(Atom base, double angle, String option) {
	this.type = base.type;
	this.base = base;
	this.angle = angle;
	Map<String, String> map = ParseOption.parseMap(option);
	if (map.containsKey("origin")) {
	    this.option = RotateBox.getOrigin(map.get("origin"));
	} else {
	    if (map.containsKey("x")) {
		float[] xinfo = SpaceAtom.getLength(map.get("x"));
		this.xunit = (int) xinfo[0];
		this.x = xinfo[1];
	    } else {
		this.xunit = TeXConstants.UNIT_POINT;
		this.x = 0;
	    }
	    if (map.containsKey("y")) {
		float[] yinfo = SpaceAtom.getLength(map.get("y"));
		this.yunit = (int) yinfo[0];
		this.y = yinfo[1];
	    } else {
		this.yunit = TeXConstants.UNIT_POINT;
		this.y = 0;
	    }
	}
    }
    
    public Box createBox(TeXEnvironment env) {
	if (option != -1) {
	    return new RotateBox(base.createBox(env), angle, option);
	} else {
	    return new RotateBox(base.createBox(env), angle, x * SpaceAtom.getFactor(xunit, env), y * SpaceAtom.getFactor(yunit, env));
	} 
    }
}
