package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandUnderline extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		// XXX
		// return new UnderlinedAtom(a);
		return null;
	}

	@Override
	public Command duplicate() {
		return new CommandUnderline();
	}

}
