package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.MathchoiceAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandMathChoice extends Command4A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b, Atom c, Atom d) {
		return new MathchoiceAtom(a, b, c, d);
	}
}
