package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.LapedAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandClap extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new LapedAtom(a, 'c');
	}

	@Override
	public Command duplicate() {
		return new CommandClap();
	}

}
