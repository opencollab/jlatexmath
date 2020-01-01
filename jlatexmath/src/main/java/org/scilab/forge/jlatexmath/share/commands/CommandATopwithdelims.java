package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FractionAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandATopwithdelims extends CommandOverwithdelims {

	@Override
	public Atom newI(TeXParser tp, Atom num, Atom den) {
		return new FractionAtom(num, den, false);
	}
}
