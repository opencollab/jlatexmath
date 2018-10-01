package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandDFrac extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return CommandGenfrac.get(null, a, b, null, null, 0);
	}

	@Override
	public Command duplicate() {
		CommandDFrac ret = new CommandDFrac();
		ret.atom = atom;
		return ret;
	}

}
