/* RuleAtom.java
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
 * An atom representing a rule. 
 */
public class RuleAtom extends Atom {
    
    private int wunit, hunit, runit;
    private float w, h, r;
    private SpaceAtom width, height, raise;
 
    public RuleAtom(int wunit, float width, int hunit, float height, int runit, float raise) {
	this.wunit = wunit;
	this.hunit = hunit;
	this.runit = runit;
	this.w = width;
	this.h = height;
	this.r = raise;
    }
    
    public Box createBox(TeXEnvironment env) {
	return new HorizontalRule(h * SpaceAtom.getFactor(hunit, env), w * SpaceAtom.getFactor(wunit, env), r * SpaceAtom.getFactor(runit, env)); 
    }    
}