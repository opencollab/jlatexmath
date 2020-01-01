package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.PhantomAtom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.UnderOverAtom;
import org.scilab.forge.jlatexmath.share.Unit;

public class CommandUnderTilde extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverAtom(a,
				new AccentedAtom(new PhantomAtom(a, true, false, false),
						Symbols.WIDETILDE),
				new TeXLength(Unit.MU, 0.3), true, false);
	}

}
