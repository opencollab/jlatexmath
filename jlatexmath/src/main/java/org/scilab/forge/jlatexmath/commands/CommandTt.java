package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TtAtom;

public class CommandTt extends CommandStyle {

	public CommandTt() {
		//
	}

	public CommandTt(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new TtAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandTt(size);
	}

}
