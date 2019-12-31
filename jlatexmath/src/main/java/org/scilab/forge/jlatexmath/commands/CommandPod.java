package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.PodAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandPod extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new PodAtom(a, 8., true);
	}
}
