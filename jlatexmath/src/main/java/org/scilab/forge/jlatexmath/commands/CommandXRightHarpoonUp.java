package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XArrowAtom;

public class CommandXRightHarpoonUp extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XArrowAtom(b, a, XArrowAtom.Kind.RightHarpoonUp);
	}

	@Override
	public Command duplicate() {
		CommandXRightHarpoonUp ret = new CommandXRightHarpoonUp();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
