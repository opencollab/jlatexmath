package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandHBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		if (a instanceof RowAtom) {
			return a;
		}
		return new RowAtom(a);
	}

}
