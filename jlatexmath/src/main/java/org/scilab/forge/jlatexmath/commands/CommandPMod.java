package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.PodAtom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.RowAtom;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandPMod extends Command1A {

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		final RowAtom ra = new RowAtom(3);
		ra.add(new RomanAtom(TeXParser.getAtomForLatinStr("mod", true)));
		ra.add(new SpaceAtom(TeXLength.Unit.MU, 6.));
		ra.add(a);
		return new PodAtom(ra, 8., true);
	}

	@Override
	public Command duplicate() {
		return new CommandPMod();
	}

}
