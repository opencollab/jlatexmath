package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderOverArrowAtom;

public class CommandUnderLeftRightArrow extends Command1A {
	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverArrowAtom(a, false);
	}

	@Override
	public Command duplicate() {
		return new CommandUnderLeftRightArrow();
	}

}
