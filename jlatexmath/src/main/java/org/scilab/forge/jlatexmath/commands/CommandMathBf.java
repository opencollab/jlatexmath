package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.BoldAtom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandMathBf extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new BoldAtom(new RomanAtom(a));
	}
}
