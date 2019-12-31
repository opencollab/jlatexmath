package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandQuotes extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AccentedAtom(a, Symbols.DDOT);
	}

	@Override
	public Command duplicate() {
		return new CommandQuotes();
	}

}
