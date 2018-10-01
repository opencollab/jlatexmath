package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XArrowAtom;

public class CommandXLeftRightHarpoons extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XArrowAtom(b, a, XArrowAtom.Kind.LeftRightHarpoons);
	}

	@Override
	public Command duplicate() {
		CommandXLeftRightHarpoons ret = new CommandXLeftRightHarpoons();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
