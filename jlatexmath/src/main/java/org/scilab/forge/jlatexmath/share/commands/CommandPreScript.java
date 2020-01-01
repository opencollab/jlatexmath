package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.PhantomAtom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.ScriptsAtom;
import org.scilab.forge.jlatexmath.share.SpaceAtom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.Unit;

public class CommandPreScript extends Command3A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
		final RowAtom ra = new RowAtom(
				new ScriptsAtom(new PhantomAtom(c, false, true, true), b, a,
						false),
				new SpaceAtom(Unit.MU, -0.3, 0., 0.),
				c.changeType(TeXConstants.TYPE_ORDINARY));
		ra.lookAtLast(true);
		return ra;
	}
}
