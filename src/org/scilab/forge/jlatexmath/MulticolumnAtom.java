/* MulticolumnAtom.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2010 DENIZET Calixte
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
 * An atom used in array mode to write on several columns.
 */
public class MulticolumnAtom extends Atom {

    protected int n;
    protected int align;
    protected float w = 0;
    protected Atom cols;

    public MulticolumnAtom(int n, String align, Atom cols) {
	this.n = n;
	this.cols = cols;
	this.align = getAlign(align);
    }

    public void setWidth(float w) {
	this.w = w;
    }

    public int getSkipped() {
	return n;
    }
    
    private int getAlign(String align) {
	char c = align.charAt(0);
	switch (c) {
	case 'l' :
	    return TeXConstants.ALIGN_LEFT;
	case 'r':
	    return TeXConstants.ALIGN_RIGHT;
	default :
	    return TeXConstants.ALIGN_CENTER;
	}
    }

    public Box createBox(TeXEnvironment env) {
	Box b;
	if (w == 0) {
	    b = cols.createBox(env);
	} else {
	    b = new HorizontalBox(cols.createBox(env), w, align);	    
	}
	
	b.type = TeXConstants.TYPE_MULTICOLUMN;
	return b;
    } 
}