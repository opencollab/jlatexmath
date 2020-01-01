package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.BigOperatorAtom;
import org.scilab.forge.jlatexmath.share.PhantomAtom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.ScriptsAtom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.TypedAtom;

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
