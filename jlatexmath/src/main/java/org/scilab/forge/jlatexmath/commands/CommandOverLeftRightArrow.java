package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderOverArrowAtom;

public class CommandOverLeftRightArrow extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverArrowAtom(a, true);
	}

	@Override
	public Command duplicate() {
		// TODO Auto-generated method stub
		return new CommandOverLeftRightArrow();
	}

}
