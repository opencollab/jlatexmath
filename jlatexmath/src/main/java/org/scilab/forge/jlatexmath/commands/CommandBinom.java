package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FencedAtom;
import org.scilab.forge.jlatexmath.FractionAtom;
import org.scilab.forge.jlatexmath.SymbolAtom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandBinom extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		final SymbolAtom left = Symbols.LBRACK;
		final SymbolAtom right = Symbols.RBRACK;
		return new FencedAtom(new FractionAtom(a, b, false), left, right);
	}

	@Override
	public Command duplicate() {
		CommandBinom ret = new CommandBinom();
		ret.atom = atom;
		return ret;
	}

}
