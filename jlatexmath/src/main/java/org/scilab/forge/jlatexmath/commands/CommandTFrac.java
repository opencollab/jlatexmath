package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTFrac extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return CommandGenfrac.get(null, a, b, null, null, 1);
	}

	@Override
	public Command duplicate() {
		CommandTFrac ret = new CommandTFrac();
		ret.atom = atom;
		return ret;
	}

}
