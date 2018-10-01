package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TextCircledAtom;

public class CommandTextCircled extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new TextCircledAtom(new RomanAtom(a));
	}

	@Override
	public Command duplicate() {
		return new CommandTextCircled();
	}

}
