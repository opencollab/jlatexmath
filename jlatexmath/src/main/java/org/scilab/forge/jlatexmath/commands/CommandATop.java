package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FractionAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandATop extends CommandOver {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new FractionAtom(a, b, false);
	}

	@Override
	public Command duplicate() {
		CommandATop ret = new CommandATop();
		ret.den = den;
		ret.num = num;
		return ret;

	}

}
