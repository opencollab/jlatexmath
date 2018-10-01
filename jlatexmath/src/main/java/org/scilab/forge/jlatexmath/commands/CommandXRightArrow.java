package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XArrowAtom;

public class CommandXRightArrow extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XArrowAtom(b, a, XArrowAtom.Kind.Right);
	}

	@Override
	public Command duplicate() {
		CommandXRightArrow ret = new CommandXRightArrow();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
