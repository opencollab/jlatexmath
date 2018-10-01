package org.scilab.forge.jlatexmath.commands;

import java.util.Map;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.RotateAtom;
import org.scilab.forge.jlatexmath.TeXParser;

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

	@Override
	public Command duplicate() {
		CommandRotateBox ret = new CommandRotateBox();
		ret.angle = angle;
		ret.options = options;
		return ret;
	}

}
