package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ColorAtom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.platform.graphics.Color;

public class CommandTextColor extends Command1A {

	Color fg;

	public CommandTextColor() {
		//
	}

	public CommandTextColor(Color fg) {
		this.fg = fg;
	}

	@Override
	public boolean init(TeXParser tp) {
		fg = CommandDefinecolor.getColor(tp);
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ColorAtom(a, null, fg);
	}

}
