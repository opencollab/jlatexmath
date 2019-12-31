package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.BuildrelAtom;
import org.scilab.forge.jlatexmath.OverAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandBuildRel extends CommandStyle {

	public CommandBuildRel() {
		//
	}

	public CommandBuildRel(RowAtom size) {
		this.size = size;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		if (a instanceof OverAtom) {
			final Atom over = ((OverAtom) a).getNum();
			final Atom base = ((OverAtom) a).getDen();
			return new BuildrelAtom(base, over);
		}
		return a;
	}

}
