package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RaiseAtom;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandLower extends Command1A {

	TeXLength lower;

	@Override
	public boolean init(TeXParser tp) {
		lower = tp.getArgAsLength();
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RaiseAtom(a, lower.scale(-1.), null, null);
	}
}
