package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.SsAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandSf extends CommandStyle {

	public CommandSf() {
		//
	}

	public CommandSf(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SsAtom(a);
	}
}
