package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.MathAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandDisplayMath extends Command1A {
	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new MathAtom(a, TeXConstants.STYLE_DISPLAY);
	}

}
