package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.AlignedAtom;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandShoveRight extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new AlignedAtom(a, TeXConstants.Align.RIGHT);
	}
}
