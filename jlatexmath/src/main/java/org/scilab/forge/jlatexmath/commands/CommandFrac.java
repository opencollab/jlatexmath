package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FractionAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandFrac extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new FractionAtom(a, b);
	}

	@Override
	public Command duplicate() {
		CommandFrac ret = new CommandFrac();
		ret.atom = atom;
		return ret;
	}

}
