package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.MonoScaleAtom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

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
