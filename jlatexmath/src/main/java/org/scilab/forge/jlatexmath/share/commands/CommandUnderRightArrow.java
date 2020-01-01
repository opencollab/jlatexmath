package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.UnderOverArrowAtom;

public class CommandUnderRightArrow extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverArrowAtom(a, false, false);
	}

}
