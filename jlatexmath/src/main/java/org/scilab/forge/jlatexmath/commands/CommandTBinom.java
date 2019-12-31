package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SymbolAtom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTBinom extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		final SymbolAtom left = Symbols.LBRACK;
		final SymbolAtom right = Symbols.RBRACK;
		return CommandGenfrac.get(left, a, b, right, TeXLength.getZero(), 1);
	}

	@Override
	public Command duplicate() {
		CommandTBinom ret = new CommandTBinom();
		ret.atom = atom;
		return ret;
	}

}
