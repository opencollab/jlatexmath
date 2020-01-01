package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.Colors;
import org.scilab.forge.jlatexmath.share.SelectionAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandJlmSelection extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new SelectionAtom(a, Colors.SELECTION, null);
	}
}
