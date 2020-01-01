package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.PodAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandPod extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new PodAtom(a, 8., true);
	}
}
