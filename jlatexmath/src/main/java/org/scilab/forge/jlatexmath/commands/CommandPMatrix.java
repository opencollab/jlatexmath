package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.FencedAtom;
import org.scilab.forge.jlatexmath.SMatrixAtom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandPMatrix extends CommandMatrix {

	@Override
	public Atom newI(TeXParser tp) {
		return new FencedAtom(new SMatrixAtom(aoa, false), Symbols.LBRACK, Symbols.RBRACK);
	}

	@Override
	public Command duplicate() {
		CommandPMatrix ret = new CommandPMatrix();

		ret.hasLBrace = hasLBrace;
		ret.aoa = aoa;

		return ret;

	}

}
