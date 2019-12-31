package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TtAtom;

public class CommandTextTt extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new TtAtom(a);
	}

	@Override
	public Command duplicate() {
		CommandTextTt ret = new CommandTextTt();
		ret.mode = mode;
		return ret;
	}

}
