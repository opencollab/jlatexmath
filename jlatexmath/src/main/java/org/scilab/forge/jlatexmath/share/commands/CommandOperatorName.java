package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RomanAtom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandOperatorName extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		a = new RomanAtom(a).changeType(TeXConstants.TYPE_BIG_OPERATOR);
		a.type_limits = TeXConstants.SCRIPT_NOLIMITS;
		return a;
	}
}
