package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ColorAtom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.platform.graphics.Color;

public class CommandBGColor extends Command1A {

	Color bg;

	public CommandBGColor() {
		//
	}

	public CommandBGColor(Color bg2) {
		this.bg = bg2;
	}

	@Override
	public boolean init(TeXParser tp) {
		bg = CommandDefinecolor.getColor(tp);
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ColorAtom(a, bg, null);
	}

}
