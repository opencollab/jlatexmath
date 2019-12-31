package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.BoldAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTextBf extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new BoldAtom(a);
	}

	@Override
	public Command duplicate() {
		CommandTextBf ret = new CommandTextBf();
		ret.mode = mode;
		return ret;
	}

}
