package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.DoubleFramedAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandDoubleBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new DoubleFramedAtom(a);
	}
}
