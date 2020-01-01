package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FBoxAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.platform.graphics.Color;

public class CommandFColorBox extends Command1A {

	Color frame;
	Color bg;

	public CommandFColorBox() {
		//
	}

	public CommandFColorBox(Color frame2, Color bg2) {
		this.frame = frame2;
		this.bg = bg2;
	}

	@Override
	public boolean init(TeXParser tp) {
		frame = tp.getArgAsColor();
		bg = tp.getArgAsColor();
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new FBoxAtom(a, bg, frame);
	}

}
