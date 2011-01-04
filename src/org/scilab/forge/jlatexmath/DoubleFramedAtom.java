/* FBoxAtom.java
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

import java.awt.Color;

/**
 * An atom representing a boxed base atom. 
 */
public class DoubleFramedAtom extends FBoxAtom {

    public DoubleFramedAtom(Atom base) {
	super(base);
    }
    
    public Box createBox(TeXEnvironment env) {
	Box bbase = base.createBox(env);
	float drt = env.getTeXFont().getDefaultRuleThickness(env.getStyle());
	float space = INTERSPACE * SpaceAtom.getFactor(TeXConstants.UNIT_EM, env);
	float sspace = 1.5f * drt + 0.5f * SpaceAtom.getFactor(TeXConstants.UNIT_POINT, env);
	return new FramedBox(new FramedBox(bbase, 0.75f * drt, space), 1.5f * drt, sspace);
    }
}
