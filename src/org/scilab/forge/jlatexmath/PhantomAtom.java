/* PhantomAtom.java
 * =========================================================================
 * This file is originally part of the JMathTeX Library - http://jmathtex.sourceforge.net
 * 
 * Copyright (C) 2004-2007 Universiteit Gent
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
 * An atom representing another atom that should be drawn invisibly.
 */
public class PhantomAtom extends Atom implements Row {

    // RowAtom to be drawn invisibly
    private RowAtom elements;
    
    // dimensions to be taken into account
    private boolean w = true, h = true, d = true;
    
    public PhantomAtom(Atom el) {
	if (el == null)
	    elements = new RowAtom();
	else
	    elements = new RowAtom(el);
    }
    
    public PhantomAtom(Atom el, boolean width, boolean height, boolean depth) {
	this(el);
	w = width;
	h = height;
	d = depth;
    }
    
    public Box createBox(TeXEnvironment env) {
	Box res = elements.createBox(env);
	return new StrutBox((w ? res.getWidth() : 0), (h ? res.getHeight() : 0),
			    (d ? res.getDepth() : 0), res.getShift());
    }
    
    public int getLeftType() {
	return elements.getLeftType();
    }
    
    public int getRightType() {
	return elements.getRightType();
    }
    
    public void setPreviousAtom(Dummy prev) {
	elements.setPreviousAtom(prev);
    }
}
