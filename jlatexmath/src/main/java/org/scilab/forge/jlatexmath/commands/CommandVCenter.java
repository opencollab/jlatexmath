package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.VCenteredAtom;

public class CommandVCenter extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new VCenteredAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandVCenter();
	}

}
