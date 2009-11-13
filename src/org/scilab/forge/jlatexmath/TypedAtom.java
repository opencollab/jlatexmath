/* TypedAtom.java
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
 * An atom representing another atom with an overrided lefttype and righttype. This
 * affects the glue inserted before and after this atom.
 */
public class TypedAtom extends Atom {

    // new lefttype and righttype
    private final int leftType;
    private final int rightType;
    
    // atom for which new types are set
    private final Atom atom;
    
    public TypedAtom(int leftType, int rightType, Atom atom) {
	this.leftType = leftType;
	this.rightType = rightType;
	this.atom = atom;
	this.type_limits = atom.type_limits;
    }
    
    public Atom getBase() {
	atom.type_limits = type_limits;
	return atom;
    }
    
    public Box createBox(TeXEnvironment env) {
	return atom.createBox(env);
    }
    
    public int getLeftType() {
	return leftType;
    }
    
    public int getRightType() {
	return rightType;
    }
}
