package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.BigOperatorAtom;
import org.scilab.forge.jlatexmath.PhantomAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.ScriptsAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TypedAtom;

public class CommandSideSet extends Command3A {

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
		final RowAtom ra = new RowAtom();
		c = c.changeLimits(TeXConstants.SCRIPT_NOLIMITS);
		if (a instanceof ScriptsAtom) {
			((ScriptsAtom) a).setBase(new PhantomAtom(c, false, true, true));
		} else if (a instanceof BigOperatorAtom) {
			((BigOperatorAtom) a)
					.setBase(new PhantomAtom(c, false, true, true));
		}
		ra.add(new TypedAtom(TeXConstants.TYPE_ORDINARY, a));

		if (b instanceof ScriptsAtom) {
			((ScriptsAtom) b).setBase(c);
		} else if (b instanceof BigOperatorAtom) {
			((BigOperatorAtom) b).setBase(c);
		} else {
			ra.add(c);
		}
		ra.add(new TypedAtom(TeXConstants.TYPE_ORDINARY, b));

		return ra;
	}
}
