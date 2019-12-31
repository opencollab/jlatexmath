package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.OgonekAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandOgonek extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new OgonekAtom(a);
	}
}
