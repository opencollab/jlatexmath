package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderOverAtom;

public class CommandB extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverAtom(a, Symbols.BAR, new TeXLength(TeXLength.Unit.MU, 0.1), false, false);
	}

	@Override
	public Command duplicate() {
		return new CommandB();
	}

}
