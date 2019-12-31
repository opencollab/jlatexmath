package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.MonoScaleAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTiny2 extends CommandStyle {

	public CommandTiny2() {
		//
	}

	public CommandTiny2(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new MonoScaleAtom(a, 0.6);
	}

	@Override
	public Command duplicate() {
		return new CommandTiny2(size);
	}

}
