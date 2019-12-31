package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FractionAtom;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandAbove extends CommandOver {

	TeXLength len;

	@Override
	public boolean init(TeXParser tp) {
		super.init(tp);
		len = tp.getArgAsLength();
		return false;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new FractionAtom(a, b, len);
	}

	@Override
	public Command duplicate() {
		CommandAbove ret = new CommandAbove();
		ret.len = len;
		ret.den = den;
		ret.num = num;
		return ret;

	}

}
