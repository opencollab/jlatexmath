package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XMapstoAtom;

public class CommandXMapsTo extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XMapstoAtom(b, a);
	}

	@Override
	public Command duplicate() {
		CommandXMapsTo ret = new CommandXMapsTo();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
