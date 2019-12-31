package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RaiseAtom;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandRaise extends Command1A {
	TeXLength raise;

	@Override
	public boolean init(TeXParser tp) {
		raise = tp.getArgAsLength();
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RaiseAtom(a, raise, null, null);
	}

	@Override
	public Command duplicate() {
		CommandRaise ret = new CommandRaise();
		ret.raise = raise;
		return ret;
	}

}
