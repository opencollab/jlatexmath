package org.scilab.forge.jlatexmath.commands;

import java.awt.Color;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ColorAtom;
import org.scilab.forge.jlatexmath.TeXParser;

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

	@Override
	public Command duplicate() {
		return new CommandTextColor(fg);
	}

}
