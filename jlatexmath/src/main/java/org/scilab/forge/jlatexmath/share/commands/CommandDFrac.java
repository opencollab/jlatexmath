package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandDFrac extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		return CommandGenfrac.get(null, a, b, null, null, 0);
	}

}
