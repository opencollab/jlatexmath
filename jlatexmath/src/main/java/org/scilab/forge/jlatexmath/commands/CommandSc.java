package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.SmallCapAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandSc extends CommandStyle {

	public CommandSc() {
		//
	}

	public CommandSc(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SmallCapAtom(a);
	}
}
