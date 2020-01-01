package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RaiseAtom;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;

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
}
