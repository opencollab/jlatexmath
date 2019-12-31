package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TypedAtom;

public class CommandMathOp extends Command1A {
	@Override
	public Atom newI(TeXParser tp, Atom a) {
		a = new TypedAtom(TeXConstants.TYPE_BIG_OPERATOR, a);
		a.type_limits = TeXConstants.SCRIPT_NORMAL;
		return a;
	}

}
