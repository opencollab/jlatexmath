package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandText2 extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return a;
	}

}
