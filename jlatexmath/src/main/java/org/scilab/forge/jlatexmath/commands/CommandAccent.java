package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentSetAtom;
import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SymbolAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandAccent extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		if (a instanceof SymbolAtom) {
			return new AccentedAtom(b, (SymbolAtom) a);
		} else {
			return new AccentSetAtom(b, a);
		}
	}

	@Override
	public Command duplicate() {
		CommandAccent ret = new CommandAccent();
		ret.atom = atom;
		return ret;
	}

}
