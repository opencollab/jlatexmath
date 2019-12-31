package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Colors;
import org.scilab.forge.jlatexmath.SelectionAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandJlmSelection extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SelectionAtom(a, Colors.SELECTION, null);
	}
}
