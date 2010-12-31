/* DdotsAtom.java
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
 * An atom representing ddots.
 */
public class DdotsAtom extends Atom {

    public DdotsAtom() {
    }
    
    public Box createBox(TeXEnvironment env) {
	Box ldots = TeXFormula.get("ldots").root.createBox(env);
	float w = ldots.getWidth();
	Box dot = SymbolAtom.get("ldotp").createBox(env);
	HorizontalBox hb1 = new HorizontalBox(dot, w, TeXConstants.ALIGN_LEFT);
	HorizontalBox hb2 = new HorizontalBox(dot, w, TeXConstants.ALIGN_CENTER);
	HorizontalBox hb3 = new HorizontalBox(dot, w, TeXConstants.ALIGN_RIGHT);
	Box pt4 = new SpaceAtom(TeXConstants.UNIT_MU, 0, 4, 0).createBox(env);
	VerticalBox vb = new VerticalBox();
	vb.add(hb1);
	vb.add(pt4);
	vb.add(hb2);
	vb.add(pt4);
	vb.add(hb3);
	
	float h = vb.getHeight() + vb.getDepth();
	vb.setHeight(h);
	vb.setDepth(0);

	return vb;
    }  
}
