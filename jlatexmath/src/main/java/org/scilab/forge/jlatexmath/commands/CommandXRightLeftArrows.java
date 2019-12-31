package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XArrowAtom;

public class CommandXRightLeftArrows extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XArrowAtom(b, a, XArrowAtom.Kind.RightAndLeft);
	}

	@Override
	public Command duplicate() {
		CommandXRightLeftArrows ret = new CommandXRightLeftArrows();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
