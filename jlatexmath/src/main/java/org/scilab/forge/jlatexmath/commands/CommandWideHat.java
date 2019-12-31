package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXParser;

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

	@Override
	public Command duplicate() {
		return new CommandWideHat();
	}

}
