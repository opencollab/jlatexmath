package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.MathchoiceAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandMathChoice extends Command4A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b, Atom c, Atom d) {
		return new MathchoiceAtom(a, b, c, d);
	}
}
