package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandBreve2 extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AccentedAtom(a, Symbols.BREVE);
	}

	@Override
	public boolean close(TeXParser tp) {
		tp.closeConsumer(Symbols.BREVE);
		return true;
	}

	@Override
	public boolean isClosable() {
		return true;
	}

}
