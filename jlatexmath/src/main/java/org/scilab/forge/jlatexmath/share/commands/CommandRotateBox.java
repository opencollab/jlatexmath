package org.scilab.forge.jlatexmath.share.commands;

import java.util.Map;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.RotateAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandRotateBox extends Command1A {

	double angle;
	Map<String, String> options;

	@Override
	public boolean init(TeXParser tp) {
		options = tp.getOptionAsMap();
		angle = tp.getArgAsDecimal();
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new RotateAtom(a, angle, options);
	}
}
