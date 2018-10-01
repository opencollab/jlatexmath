package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.XLongequalAtom;

public class CommandXLongEqual extends Command1O1A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return new XLongequalAtom(b, a);
	}

	@Override
	public Command duplicate() {
		CommandXLongEqual ret = new CommandXLongEqual();
		ret.hasopt = hasopt;
		ret.option = option;
		return ret;
	}

}
