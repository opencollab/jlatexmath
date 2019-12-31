package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SsAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTextSf extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SsAtom(a);
	}

	@Override
	public Command duplicate() {
		CommandTextSf ret = new CommandTextSf();
		ret.mode = mode;
		return ret;
	}

}
