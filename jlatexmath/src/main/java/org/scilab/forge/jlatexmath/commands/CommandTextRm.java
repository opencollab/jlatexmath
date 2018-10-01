package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTextRm extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RomanAtom(a);
	}

	@Override
	public Command duplicate() {
		CommandTextRm ret = new CommandTextRm();
		ret.mode = mode;
		return ret;
	}

}
