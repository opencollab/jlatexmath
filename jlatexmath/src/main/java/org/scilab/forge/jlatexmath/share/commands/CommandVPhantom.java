package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.PhantomAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandVPhantom extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new PhantomAtom(a, false, true, true);
	}

}
