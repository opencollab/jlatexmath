package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.StyleAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTextStyle2 extends CommandStyle {

	public CommandTextStyle2() {
		//
	}

	public CommandTextStyle2(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new StyleAtom(TeXConstants.STYLE_TEXT, a);
	}

	@Override
	public Command duplicate() {
		return new CommandTextStyle2(size);
	}

}
