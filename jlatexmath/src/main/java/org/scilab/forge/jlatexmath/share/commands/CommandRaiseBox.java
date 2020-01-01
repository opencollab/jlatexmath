package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RaiseAtom;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;

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
}
