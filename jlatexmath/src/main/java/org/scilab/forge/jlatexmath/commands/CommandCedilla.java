package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.CedillaAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandCedilla extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new CedillaAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandCedilla();
	}

}
