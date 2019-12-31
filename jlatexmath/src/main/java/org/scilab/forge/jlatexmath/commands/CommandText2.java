package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandText2 extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return a;
	}

}
