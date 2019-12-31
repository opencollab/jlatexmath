package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.PhantomAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.ScriptsAtom;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandPreScript extends Command3A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
		final RowAtom ra = new RowAtom(new ScriptsAtom(new PhantomAtom(c, false, true, true), b, a, false),
				new SpaceAtom(TeXLength.Unit.MU, -0.3, 0., 0.), c.changeType(TeXConstants.TYPE_ORDINARY));
		ra.lookAtLast(true);
		return ra;
	}

	@Override
	public Command duplicate() {
		CommandPreScript ret = new CommandPreScript();
		ret.atom1 = atom1;
		ret.atom2 = atom2;
		return ret;
	}

}
