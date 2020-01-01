package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.UnderOverAtom;
import org.scilab.forge.jlatexmath.share.Unit;

public class CommandB extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverAtom(a, Symbols.BAR,
				new TeXLength(Unit.MU, 0.1), false, false);
	}

}
