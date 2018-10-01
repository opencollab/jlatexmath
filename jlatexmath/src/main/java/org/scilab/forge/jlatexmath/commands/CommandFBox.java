package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FBoxAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandFBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new FBoxAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandFBox();
	}

}
