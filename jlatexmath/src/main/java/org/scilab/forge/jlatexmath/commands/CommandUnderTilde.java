package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.AccentedAtom;
import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.PhantomAtom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderOverAtom;
import org.scilab.forge.jlatexmath.Unit;

public class CommandUnderTilde extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new UnderOverAtom(a,
				new AccentedAtom(new PhantomAtom(a, true, false, false),
						Symbols.WIDETILDE),
				new TeXLength(Unit.MU, 0.3), true, false);
	}

}
