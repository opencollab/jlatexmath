package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FractionAtom;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;

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
}
