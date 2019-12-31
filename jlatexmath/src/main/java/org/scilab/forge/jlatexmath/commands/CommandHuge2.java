package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.MonoScaleAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandHuge2 extends CommandStyle {

	public CommandHuge2() {
		//
	}

	public CommandHuge2(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new MonoScaleAtom(a, 2.5);
	}

	@Override
	public Command duplicate() {
		return new CommandHuge2(size);
	}

}
