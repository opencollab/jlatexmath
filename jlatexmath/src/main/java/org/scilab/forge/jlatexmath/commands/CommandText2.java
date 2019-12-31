package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandText2 extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return a;
	}

	@Override
	public Command duplicate() {
		CommandText2 ret = new CommandText2();
		ret.mode = mode;
		return ret;
	}

}
