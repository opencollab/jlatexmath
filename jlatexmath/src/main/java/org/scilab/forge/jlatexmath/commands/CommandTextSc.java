package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SmallCapAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTextSc extends CommandText {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SmallCapAtom(a);
	}

}
