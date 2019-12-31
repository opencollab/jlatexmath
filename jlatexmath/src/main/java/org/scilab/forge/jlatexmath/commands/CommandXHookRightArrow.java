package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XHookAtom;

public class CommandXHookRightArrow extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XHookAtom(b, a, false);
	}

	@Override
	public Command duplicate() {
		CommandXHookRightArrow ret = new CommandXHookRightArrow();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
