package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderlinedAtom;

public class CommandUnderline extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderlinedAtom(a);
	}

}
