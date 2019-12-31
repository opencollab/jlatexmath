package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.StrikeThroughAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandSt extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new StrikeThroughAtom(a);
	}
}
