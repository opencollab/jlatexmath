/* ReflectAtom.java
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
 * An atom representing a reflected Atom.
 */
public class CumulativeScriptsAtom extends Atom {
    
    private Atom base;
    private RowAtom sup;
    private RowAtom sub;

    public CumulativeScriptsAtom(Atom base, Atom sub, Atom sup) {
	super();
	if (base instanceof CumulativeScriptsAtom) {
	    CumulativeScriptsAtom at = (CumulativeScriptsAtom) base;
	    this.base = at.base;
	    at.sup.add(sup);
	    at.sub.add(sub);
	    this.sup = at.sup;
	    this.sub = at.sub;
	} else {
	    if (base == null) {
		this.base = new PhantomAtom(new CharAtom('M', "mathnormal"), false, true, true);
	    } else {
		this.base = base;
	    }
	    this.sup = new RowAtom(sup);
	    this.sub = new RowAtom(sub);
	}
    }
    
    public Box createBox(TeXEnvironment env) {
	return new ScriptsAtom(base, sub, sup).createBox(env);
    } 	
}
