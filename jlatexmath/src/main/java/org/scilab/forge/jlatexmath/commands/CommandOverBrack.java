package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.OverUnderDelimiter;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandOverBrack extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new OverUnderDelimiter(a, null, Symbols.LSQBRACK, TeXLength.Unit.EX, 0, true);
	}

	@Override
	public Command duplicate() {
		return new CommandOverBrack();
	}

}
