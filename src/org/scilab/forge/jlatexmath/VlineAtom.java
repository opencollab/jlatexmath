/* VlineAtom.java
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
 * An atom representing a hline in array environment
 */
public class VlineAtom extends Atom {
    
    private float height;
    private float shift;
    private int n;
    
    public VlineAtom(int n) {
	this.n = n;
    }
    
    public void setHeight(float height) {
	this.height = height;
    }

    public void setShift(float shift) {
	this.shift = shift;
    }

    public float getWidth(TeXEnvironment env) {
	if (n != 0) {
	    float drt = env.getTeXFont().getDefaultRuleThickness(env.getStyle());
	    return drt * (3 * n - 2);
	} else
	    return 0;
    }

    public Box createBox(TeXEnvironment env) {
	if (n != 0) {
	    float drt = env.getTeXFont().getDefaultRuleThickness(env.getStyle());
	    Box b = new HorizontalRule(height, drt, shift);
	    Box sep = new StrutBox(2 * drt, 0, 0, 0);
	    HorizontalBox hb = new HorizontalBox();
	    for (int i = 0; i < n - 1; i++) {
		hb.add(b);
		hb.add(sep);
	    }
	    
	    if (n > 0) {
		hb.add(b);
	    }
	    
	    return hb;
	}
	
	return new StrutBox(0, 0, 0, 0);
    }
}
