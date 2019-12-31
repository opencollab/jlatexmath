package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.UnderOverAtom;
import org.scilab.forge.jlatexmath.Unit;

public class CommandOverSet extends Command2A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		final Atom at = new UnderOverAtom(b, a,
				new TeXLength(Unit.MU, 2.5), true, true);
		return at.changeType(TeXConstants.TYPE_RELATION);
	}
}
