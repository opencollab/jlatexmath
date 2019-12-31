package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.BuildrelAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.Symbols;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandDDDDot extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new BuildrelAtom(a,
				new RowAtom(Symbols.TEXTNORMALDOT, Symbols.TEXTNORMALDOT,
						Symbols.TEXTNORMALDOT, Symbols.TEXTNORMALDOT));
	}

}
