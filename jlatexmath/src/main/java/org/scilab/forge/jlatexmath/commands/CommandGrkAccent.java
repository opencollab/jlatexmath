package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SymbolAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandGrkAccent extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		// TODO: instanceof
		return new AccentedAtom(b, (SymbolAtom) a);
	}

	@Override
	public Command duplicate() {
		CommandGrkAccent ret = new CommandGrkAccent();
		ret.atom = atom;
		return ret;
	}

}
