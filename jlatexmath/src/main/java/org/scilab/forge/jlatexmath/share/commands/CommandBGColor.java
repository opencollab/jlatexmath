package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.ColorAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.platform.graphics.Color;

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
