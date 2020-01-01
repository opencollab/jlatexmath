package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.SsAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

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
