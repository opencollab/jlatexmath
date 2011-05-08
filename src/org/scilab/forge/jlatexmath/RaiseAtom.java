/* RaiseAtom.java
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
public class RaiseAtom extends Atom {

    private Atom base;
    private int runit, hunit, dunit;
    private float r, h, d;

    public RaiseAtom(Atom base, int runit, float r, int hunit, float h, int dunit, float d) {
	this.base = base;
	this.runit = runit;
	this.r = r;
	this.hunit = hunit;
	this.h = h;
	this.dunit = dunit;
	this.d = d;
    }

    public int getLeftType() {
        return base.getLeftType();
    }

    public int getRightType() {
        return base.getRightType();
    }

    public Box createBox(TeXEnvironment env) {
        Box bbox = base.createBox(env);
	if (runit == -1) {
	    bbox.setShift(0);
	} else {
	    bbox.setShift(-r * SpaceAtom.getFactor(runit, env));
	}

	if (hunit == -1) {
	    return bbox;
	}

	HorizontalBox hbox = new HorizontalBox(bbox);
	hbox.setHeight(h * SpaceAtom.getFactor(hunit, env));
	if (dunit == -1) {
	    hbox.setDepth(0);
	} else {
	    hbox.setDepth(d * SpaceAtom.getFactor(dunit, env));
	}

	return hbox;
    }
}