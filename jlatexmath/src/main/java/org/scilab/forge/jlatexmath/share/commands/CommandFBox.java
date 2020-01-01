package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FBoxAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandFBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new FBoxAtom(a);
	}

}
