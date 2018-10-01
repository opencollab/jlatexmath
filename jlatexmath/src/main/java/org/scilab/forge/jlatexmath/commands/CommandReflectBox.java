package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ReflectAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandReflectBox extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new ReflectAtom(a);
	}

	@Override
	public Command duplicate() {
		return new CommandReflectBox();
	}

}
