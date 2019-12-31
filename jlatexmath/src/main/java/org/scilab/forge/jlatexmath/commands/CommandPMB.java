package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.OoalignAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandPMB extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new OoalignAtom(a, new RowAtom(new SpaceAtom(TeXLength.Unit.MU, 0.4), a));
	}

	@Override
	public Command duplicate() {
		return new CommandPMB();
	}

}
