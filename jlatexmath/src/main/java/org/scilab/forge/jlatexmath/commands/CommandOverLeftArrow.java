package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderOverArrowAtom;

public class CommandOverLeftArrow extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverArrowAtom(a, true, true);
	}

	@Override
	public Command duplicate() {
		return new CommandOverLeftArrow();
	}

}
