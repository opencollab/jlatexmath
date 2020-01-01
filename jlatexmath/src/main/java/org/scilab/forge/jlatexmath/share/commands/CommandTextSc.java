package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.SmallCapAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandTextSc extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SmallCapAtom(a);
	}

}
