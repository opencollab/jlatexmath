package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.DBoxAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandDBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new DBoxAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandDBox();
	}

}
