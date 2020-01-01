package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.VCenteredAtom;

public class CommandVCenter extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new VCenteredAtom(a);
	}

}
