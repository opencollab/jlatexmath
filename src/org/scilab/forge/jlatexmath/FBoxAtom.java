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
public class FBoxAtom extends Atom {

    public static SpaceAtom hsep = new SpaceAtom(TeXConstants.UNIT_EM, 0.5f, 0.0f, 0.0f);
    public static SpaceAtom vsep = new SpaceAtom(TeXConstants.UNIT_EX, 0.0f, 0.5f, 0.0f);

    // base atom
    private final Atom base;
    private Color bg = null, line = null;
	
    public FBoxAtom(Atom base) {
	if (base == null)
	    this.base = new RowAtom(); // empty base
	else {
	    this.base = base;
	    this.type = base.type;
	}
    }
    
    public FBoxAtom(Atom base, Color bg, Color line) {
	this(base);
	this.bg = bg;
	this.line = line;
    }

    public Box createBox(TeXEnvironment env) {
	Box Hsep = hsep.createBox(env);
	Box Vsep = vsep.createBox(env);
	Box bbase = base.createBox(env);
	HorizontalBox hb = new HorizontalBox(Hsep);
	hb.add(bbase);
	hb.add(Hsep);
	VerticalBox vb = new VerticalBox();
	vb.add(Vsep);
	vb.add(hb);
	vb.add(Vsep);
	vb.setHeight(bbase.getHeight() + Vsep.getHeight());
	vb.setDepth(bbase.getDepth() + Vsep.getHeight());
	float drt = env.getTeXFont().getDefaultRuleThickness(env.getStyle());
	if (bg == null) {
	    return new FramedBox(vb, drt);
	} else {
	    env.isColored = true;
	    HorizontalBox hbb = new HorizontalBox(env.getColor(), bg);
	    hbb.add(vb);
	    return new FramedBox(hbb, drt, line);
	}
    }
}
