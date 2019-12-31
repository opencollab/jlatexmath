package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.DoubleFramedAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandDoubleBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new DoubleFramedAtom(a);
	}
}
