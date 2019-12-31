package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandMathRm extends Command1A {
	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RomanAtom(a);
	}

}
