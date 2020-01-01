package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FencedAtom;
import org.scilab.forge.jlatexmath.share.SMatrixAtom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandPMatrix extends CommandMatrix {

	@Override
	public Atom newI(TeXParser tp) {
		return new FencedAtom(new SMatrixAtom(aoa, false), Symbols.LBRACK,
				Symbols.RBRACK);
	}
}
