/* HdotsforAtom.java
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
public class HdotsforAtom extends MulticolumnAtom {

    private static final Atom ldotp = SymbolAtom.get("ldotp");
    private static final Atom thin = new SpaceAtom(TeXConstants.THINMUSKIP);
    private float coeff;

    public HdotsforAtom(int n, float coeff) {
	super(n, "c", ldotp);
	this.coeff = coeff;
    }

    public Box createBox(TeXEnvironment env) {
	Box sp = new StrutBox(coeff * thin.createBox(env).getWidth(), 0, 0, 0);
	HorizontalBox db = new HorizontalBox(sp);
	db.add(ldotp.createBox(env));
	db.add(sp);
	Box b;
	if (w != 0) {
	    float dw = db.getWidth();
	    b = new HorizontalBox(db);
	    while (b.getWidth() < w) {
		b.add(db);
	    }
	    b = new HorizontalBox(b, w, TeXConstants.ALIGN_CENTER); 
	} else {
	    b = db;
	}
	
	b.type = TeXConstants.TYPE_MULTICOLUMN;
	return b;
    } 
}