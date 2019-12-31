package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XArrowAtom;

public class CommandXLeftRightArrows extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XArrowAtom(b, a, XArrowAtom.Kind.LeftAndRight);
	}

	@Override
	public Command duplicate() {
		CommandXLeftRightArrows ret = new CommandXLeftRightArrows();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
