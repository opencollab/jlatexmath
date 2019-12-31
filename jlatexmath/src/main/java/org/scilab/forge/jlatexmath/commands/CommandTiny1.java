package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.MonoScaleAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandTiny1 extends CommandStyle {

	public CommandTiny1(RowAtom size) {
		this.size = size;
	}

	public CommandTiny1() {
		//
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new MonoScaleAtom(a, 0.5);
	}

}
