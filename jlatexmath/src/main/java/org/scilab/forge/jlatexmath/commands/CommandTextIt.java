package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ItAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTextIt extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ItAtom(a);
	}

	@Override
	public Command duplicate() {
		CommandTextIt ret = new CommandTextIt();
		ret.mode = mode;
		return ret;
	}

}
