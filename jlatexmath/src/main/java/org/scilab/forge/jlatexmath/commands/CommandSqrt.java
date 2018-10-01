package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.NthRoot;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandSqrt extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new NthRoot(b, a);
	}

	@Override
	public Command duplicate() {
		CommandSqrt ret = new CommandSqrt();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
