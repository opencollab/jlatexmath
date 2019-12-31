package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.CancelAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandCancel extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new CancelAtom(a, CancelAtom.Type.SLASH);
	}

}
