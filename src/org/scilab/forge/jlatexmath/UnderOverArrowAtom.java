/* UnderOverArrowAtom.java
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

/**
 * An atom representing an other atom with an extensible arrow or doublearrow over or under it.
 */
public class UnderOverArrowAtom extends Atom {

    private Atom base;
    private String arrow;
    private boolean over, left = false, dble = false;

    public UnderOverArrowAtom(Atom base, boolean left, boolean over) {
        this.base = base;
	this.arrow = left ? "leftarrow" : "rightarrow";
	this.left = left;
	this.over = over;
    }
    
    public UnderOverArrowAtom(Atom base, boolean over) {
        this.base = base;
	this.over = over;
	this.dble = true;
    }

    public Box createBox(TeXEnvironment env) {
	TeXFont tf = env.getTeXFont();
        int style = env.getStyle();
	Box b = base != null ? base.createBox(env) : new StrutBox(0, 0, 0, 0);
	float sep = new SpaceAtom(TeXConstants.UNIT_POINT, 1f, 0, 0).createBox(env).getWidth();
	Box arrow;

	if (dble) {
	    arrow = XLeftRightArrowFactory.create(env, b.getWidth());
	    sep = 4 * sep;
	} else {
	    arrow = XLeftRightArrowFactory.create(left, env, b.getWidth());
	    sep = -sep;
	}

	VerticalBox vb = new VerticalBox();
	if (over) {
	    vb.add(arrow);
	    vb.add(new HorizontalBox(b, arrow.getWidth(), TeXConstants.ALIGN_CENTER));
	    float h = vb.getDepth() + vb.getHeight();
	    vb.setDepth(b.getDepth());
	    vb.setHeight(h - b.getDepth());
	} else {
	    vb.add(new HorizontalBox(b, arrow.getWidth(), TeXConstants.ALIGN_CENTER));
	    vb.add(new StrutBox(0, sep, 0, 0));
	    vb.add(arrow);
	    float h = vb.getDepth() + vb.getHeight();
	    vb.setDepth(h - b.getHeight());
	    vb.setHeight(b.getHeight());
	}

	return vb;
	
    }
}
