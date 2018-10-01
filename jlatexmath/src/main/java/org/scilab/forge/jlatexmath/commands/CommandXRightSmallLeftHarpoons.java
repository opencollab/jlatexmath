package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XArrowAtom;

public class CommandXRightSmallLeftHarpoons extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XArrowAtom(b, a, XArrowAtom.Kind.RightSmallLeftHarpoons);
	}

	@Override
	public Command duplicate() {
		CommandXRightSmallLeftHarpoons ret = new CommandXRightSmallLeftHarpoons();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
