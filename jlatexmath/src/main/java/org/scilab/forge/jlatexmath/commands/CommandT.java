package org.scilab.forge.jlatexmath.commands;

import java.util.HashMap;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RotateAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandT extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RotateAtom(a, 180., new HashMap<String, String>() {
			{
				put("origin", "cc");
			}
		});
	}

	@Override
	public Command duplicate() {
		return new CommandT();
	}

}
