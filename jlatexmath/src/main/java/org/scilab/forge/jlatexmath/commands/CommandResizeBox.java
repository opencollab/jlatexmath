package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ResizeAtom;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandResizeBox extends Command1A {

	TeXLength width;
	TeXLength height;

	public CommandResizeBox(TeXLength width2, TeXLength height2) {
		this.width = width2;
		this.height = height2;
	}

	public CommandResizeBox() {
		//
	}

	@Override
	public boolean init(TeXParser tp) {
		width = tp.getArgAsLengthOrExcl();
		height = tp.getArgAsLengthOrExcl();
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ResizeAtom(a, width, height);
	}

	@Override
	public Command duplicate() {
		return new CommandResizeBox(width, height);
	}

}
