package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandWideTilde extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AccentedAtom(a, Symbols.WIDETILDE);
	}

	@Override
	public boolean close(TeXParser tp) {
		tp.closeConsumer(Symbols.WIDETILDE);
		return true;
	}

	@Override
	public boolean isClosable() {
		return true;
	}

}
