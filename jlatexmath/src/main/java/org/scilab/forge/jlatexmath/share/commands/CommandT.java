package org.scilab.forge.jlatexmath.share.commands;

import java.util.HashMap;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RotateAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandT extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RotateAtom(a, 180., new HashMap<String, String>() {
			{
				put("origin", "cc");
			}
		});
	}

}
