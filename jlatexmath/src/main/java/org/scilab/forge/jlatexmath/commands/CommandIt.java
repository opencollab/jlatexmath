package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ItAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandIt extends CommandStyle {

	public CommandIt() {
		//
	}

	public CommandIt(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ItAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandIt(size);
	}

}
