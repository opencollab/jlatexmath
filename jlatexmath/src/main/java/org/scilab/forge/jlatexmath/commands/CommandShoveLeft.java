package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AlignedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandShoveLeft extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AlignedAtom(a, TeXConstants.Align.LEFT);
	}

	@Override
	public Command duplicate() {
		return new CommandShoveLeft();
	}

}
