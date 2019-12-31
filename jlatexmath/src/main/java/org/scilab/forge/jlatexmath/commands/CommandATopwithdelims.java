package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FractionAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandATopwithdelims extends CommandOverwithdelims {

	@Override
	public Atom newI(TeXParser tp, Atom num, Atom den) {
		return new FractionAtom(num, den, false);
	}

	@Override
	public Command duplicate() {
		CommandATopwithdelims ret = new CommandATopwithdelims();

		ret.num = num;
		ret.den = den;
		ret.left = left;
		ret.right = right;

		return ret;

	}

}
