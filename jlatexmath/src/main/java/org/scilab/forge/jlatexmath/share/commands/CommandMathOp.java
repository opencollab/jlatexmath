package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.TypedAtom;

public class CommandMathOp extends Command1A {
	@Override
	public Atom newI(TeXParser tp, Atom a) {
		a = new TypedAtom(TeXConstants.TYPE_BIG_OPERATOR, a);
		a.type_limits = TeXConstants.SCRIPT_NORMAL;
		return a;
	}

}
