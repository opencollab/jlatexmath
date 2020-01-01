package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.MathAtom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandDisplayMath extends Command1A {
	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new MathAtom(a, TeXConstants.STYLE_DISPLAY);
	}

}
