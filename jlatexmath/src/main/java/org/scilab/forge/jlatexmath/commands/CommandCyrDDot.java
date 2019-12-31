package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SymbolAtom;
import org.scilab.forge.jlatexmath.TeXParser;

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

	@Override
	public Command duplicate() {
		return new CommandCyrDDot();
	}

}
