package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.OverUnderDelimiter;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.Unit;

public class CommandOverBrace extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new OverUnderDelimiter(a, null, Symbols.LBRACE,
				Unit.EX, 0, true);
	}
}
