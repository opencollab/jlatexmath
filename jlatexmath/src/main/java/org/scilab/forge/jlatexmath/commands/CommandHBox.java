package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandHBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		if (a instanceof RowAtom) {
			return a;
		}
		return new RowAtom(a);
	}

}
