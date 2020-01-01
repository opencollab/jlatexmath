package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandWideHat extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AccentedAtom(a, Symbols.WIDEHAT);
	}

	@Override
	public boolean close(TeXParser tp) {
		tp.closeConsumer(Symbols.WIDEHAT);
		return true;
	}

	@Override
	public boolean isClosable() {
		return true;
	}
}
