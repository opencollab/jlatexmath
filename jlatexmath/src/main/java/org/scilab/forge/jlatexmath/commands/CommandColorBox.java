package org.scilab.forge.jlatexmath.commands;

import java.awt.Color;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FBoxAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandColorBox extends Command1A {

	Color bg;

	public CommandColorBox() {
		//
	}

	public CommandColorBox(Color bg2) {
		this.bg = bg2;
	}

	@Override
	public boolean init(TeXParser tp) {
		bg = CommandDefinecolor.getColor(tp);
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new FBoxAtom(a, bg, bg);
	}

	@Override
	public Command duplicate() {
		return new CommandColorBox(bg);
	}

}
