package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.SymbolAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandCyrDDot extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AccentedAtom(a, "cyrddot");
	}

	@Override
	public boolean close(TeXParser tp) {
		tp.closeConsumer(SymbolAtom.get("cyrddot"));
		return true;
	}

	@Override
	public boolean isClosable() {
		return true;
	}

}
