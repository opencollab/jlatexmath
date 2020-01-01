package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.exception.ParseException;

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
