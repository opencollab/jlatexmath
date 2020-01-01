package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandKet extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RowAtom(Symbols.VERT, a, Symbols.RANGLE);
	}
}
