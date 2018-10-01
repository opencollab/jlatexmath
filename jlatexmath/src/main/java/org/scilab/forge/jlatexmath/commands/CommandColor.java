package org.scilab.forge.jlatexmath.commands;

import java.awt.Color;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ColorAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandColor extends CommandStyle {

	Color fg;

	public CommandColor() {
		//
	}

	public CommandColor(RowAtom size, Color fg2) {
		this.size = size;
		this.fg = fg2;
	}

	@Override
	public boolean init(TeXParser tp) {
		fg = CommandDefinecolor.getColor(tp);
		return super.init(tp);
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ColorAtom(a, null, fg);
	}

	@Override
	public Command duplicate() {
		return new CommandColor(size, fg);
	}

}
