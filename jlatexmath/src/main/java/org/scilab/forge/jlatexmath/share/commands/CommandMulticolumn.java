package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.ArrayOptions;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.MulticolumnAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.exception.ParseException;

public class CommandMulticolumn extends Command1A {
	int n;
	ArrayOptions options;

	public CommandMulticolumn() {
		//
	}

	public CommandMulticolumn(int n2, ArrayOptions options2) {
		this.n = n2;
		this.options = options2;
	}

	@Override
	public boolean init(TeXParser tp) {
		if (!tp.isArrayMode()) {
			throw new ParseException(tp,
					"The macro \\multicolumn is only available in array mode !");
		}
		n = tp.getArgAsPositiveInteger();
		if (n == -1) {
			throw new ParseException(tp,
					"The macro \\multicolumn requires a positive integer");
		}
		options = tp.getArrayOptions();
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a) {
		return new MulticolumnAtom(n, options, a);
	}

}
