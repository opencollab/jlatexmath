package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandRm extends CommandStyle {

	public CommandRm() {
		//
	}

	public CommandRm(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RomanAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandRm(size);
	}

}
