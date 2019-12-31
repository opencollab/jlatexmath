package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandOperatorName extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		a = new RomanAtom(a).changeType(TeXConstants.TYPE_BIG_OPERATOR);
		a.type_limits = TeXConstants.SCRIPT_NOLIMITS;
		return a;
	}
}
