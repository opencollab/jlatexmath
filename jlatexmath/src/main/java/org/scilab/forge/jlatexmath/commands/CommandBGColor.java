package org.scilab.forge.jlatexmath.commands;

import java.awt.Color;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ColorAtom;
import org.scilab.forge.jlatexmath.TeXParser;

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

	@Override
	public Command duplicate() {
		return new CommandBGColor(bg);
	}

}
