package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.MathchoiceAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandMathChoice extends Command4A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b, Atom c, Atom d) {
		return new MathchoiceAtom(a, b, c, d);
	}

	@Override
	public Command duplicate() {
		CommandMathChoice ret = new CommandMathChoice();
		ret.atom1 = atom1;
		ret.atom2 = atom2;
		ret.atom3 = atom3;
		return ret;
	}

}
