package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.exception.ParseException;

public class CommandSkew extends Command {

	private double skew;

	@Override
	public boolean init(TeXParser tp) {
		skew = tp.getArgAsDecimal();
		return true;
	}

	@Override
	public void add(TeXParser tp, Atom a) {
		if (a instanceof AccentedAtom) {
			((AccentedAtom) a).setSkew(skew);
			tp.closeConsumer(a);
			return;
		}
		throw new ParseException(tp,
				"skew command is only working with an accent as second argument");
	}
}
