/* ResizeAtom.java
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

/**
 * An atom representing a scaled Atom.
 */
public class ResizeAtom extends Atom {

    private Atom base;
    private int wunit, hunit;
    private float w, h;
    private boolean keepaspectratio;

    public ResizeAtom(Atom base, String ws, String hs, boolean keepaspectratio) {
        this.type = base.type;
        this.base = base;
	this.keepaspectratio = keepaspectratio;
	float[] w = SpaceAtom.getLength(ws == null ? "" : ws);
	float[] h = SpaceAtom.getLength(hs == null ? "" : hs);
	if (w.length != 2) {
	    this.wunit = -1;
	} else {
	    this.wunit = (int) w[0];
	    this.w = w[1];
	}
	if (h.length != 2) {
	    this.hunit = -1;
	} else {
	    this.hunit = (int) h[0];
	    this.h = h[1];
	}
    }
    
    public int getLeftType() {
        return base.getLeftType();
    }

    public int getRightType() {
        return base.getRightType();
    }

    public Box createBox(TeXEnvironment env) {
        Box bbox = base.createBox(env);
	if (wunit == -1 && hunit == -1) {
	    return bbox;
	} else {
	    double xscl = 1;
	    double yscl = 1;
	    if (wunit != -1 && hunit != -1) {
		xscl = w * SpaceAtom.getFactor(wunit, env) / bbox.width;
		yscl = h * SpaceAtom.getFactor(hunit, env) / bbox.height;
		if (keepaspectratio) {
		    xscl = Math.min(xscl, yscl);
		    yscl = xscl;
		}
	    } else if (wunit != -1 && hunit == -1) {
		xscl = w * SpaceAtom.getFactor(wunit, env) / bbox.width;
		yscl = xscl;
	    } else {
		yscl = h * SpaceAtom.getFactor(hunit, env) / bbox.height;
		xscl = yscl;
	    }

	    return new ScaleBox(bbox, xscl, yscl);
	}
    }
}
