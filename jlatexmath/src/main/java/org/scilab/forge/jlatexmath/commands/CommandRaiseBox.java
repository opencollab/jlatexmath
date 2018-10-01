package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RaiseAtom;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandRaiseBox extends Command1A {

	TeXLength raise;
	TeXLength height;
	TeXLength depth;

	@Override
	public boolean init(TeXParser tp) {
		raise = tp.getArgAsLength();
		height = tp.getOptionAsLength(null);
		depth = tp.getOptionAsLength(null);
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RaiseAtom(a, raise, height, depth);
	}

	@Override
	public Command duplicate() {
		CommandRaiseBox ret = new CommandRaiseBox();

		ret.raise = raise;
		ret.height = height;
		ret.depth = depth;

		return ret;
	}

}
