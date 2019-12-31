package org.scilab.forge.jlatexmath;

import org.scilab.forge.jlatexmath.platform.graphics.Color;

public class SelectionAtom extends ColorAtom {

	public SelectionAtom(Atom atom, Color bg, Color c) {
		super(atom, bg, c);
	}

	@Override
	public Box createBox(TeXEnvironment env) {
		Box box = super.createBox(env);
		return new SelectionBox(box);
	}

}
