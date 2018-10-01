package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AlignedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandShoveRight extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AlignedAtom(a, TeXConstants.Align.RIGHT);
	}

	@Override
	public Command duplicate() {
		return new CommandShoveRight();
	}

}
