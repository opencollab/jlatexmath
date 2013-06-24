/* VRowAtom.java
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

/* Modified by Calixte Denizet */

package org.scilab.forge.jlatexmath;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * An atom representing a vertical row of other atoms.
 */
public class VRowAtom extends Atom {
    
    // atoms to be displayed horizontally next to eachother
    protected LinkedList<Atom> elements = new LinkedList<Atom>();
    private SpaceAtom raise = new SpaceAtom(TeXConstants.UNIT_EX, 0, 0, 0);
    protected boolean addInterline = false;
    
    public VRowAtom() {
        // empty
    }
    
    public VRowAtom(Atom el) {
        if (el != null) {
            if (el instanceof VRowAtom)
                // no need to make an mrow the only element of an mrow
                elements.addAll(((VRowAtom) el).elements);
            else
                elements.add(el);
        }
    }

    public void setAddInterline(boolean addInterline) {
	this.addInterline = addInterline;
    }

    public boolean getAddInterline() {
	return this.addInterline;
    }

    public void setRaise(int unit, float r) {
	raise = new SpaceAtom(unit, r, 0, 0);
    }
    
    public Atom getLastAtom() {
	return elements.removeLast();
    }
	
    public final void add(Atom el) {
        if (el != null)
            elements.add(0, el);
    }

    public final void append(Atom el) {
        if (el != null)
            elements.add(el);
    }
    
    public Box createBox(TeXEnvironment env) {
        VerticalBox vb = new VerticalBox();
	Box interline = new StrutBox(0, env.getInterline(), 0, 0);

        // convert atoms to boxes and add to the horizontal box
        for (ListIterator it = elements.listIterator(); it.hasNext();) {
            vb.add(((Atom)it.next()).createBox(env));
	    if (addInterline && it.hasNext()) {
		vb.add(interline);
	    }
	}

	vb.setShift(-raise.createBox(env).getWidth());
	float t = vb.getSize() == 0 ? 0 : vb.children.getLast().getDepth();
	vb.setHeight(vb.getDepth() + vb.getHeight() - t);
	vb.setDepth(t);
		
	return vb;
    }
}
