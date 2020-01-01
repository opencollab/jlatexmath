package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FractionAtom;
import org.scilab.forge.jlatexmath.share.TeXLength;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandAbovewithdelims extends CommandOverwithdelims {

	TeXLength l;

	@Override
	public void add(TeXParser tp, Atom a) {
		if (left == null) {
			left = a;
		} else if (right == null) {
			right = a;
			l = tp.getArgAsLength();
		} else {
			den.add(a);
		}
	}

	@Override
	public Atom newI(TeXParser tp, Atom num, Atom den) {
		return new FractionAtom(num, den, l);
	}
}
