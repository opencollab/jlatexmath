package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.TtAtom;

public class CommandTextTt extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new TtAtom(a);
	}

}
