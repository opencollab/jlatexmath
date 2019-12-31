package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.SmashedAtom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.exception.ParseException;

public class CommandSmash extends Command1A {

	char opt;

	@Override
	public boolean init(TeXParser tp) {
		opt = tp.getOptionAsChar();
		if (opt != 't' && opt != 'b' && opt != '\0') {
			throw new ParseException(tp, "Invalid option in \\smash");
		}
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SmashedAtom(a, opt);
	}
}
