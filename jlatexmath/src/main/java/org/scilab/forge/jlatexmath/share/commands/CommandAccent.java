package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AccentSetAtom;
import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.SymbolAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandAccent extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		if (a instanceof SymbolAtom) {
			return new AccentedAtom(b, (SymbolAtom) a);
		} else {
			return new AccentSetAtom(b, a);
		}
	}

}
