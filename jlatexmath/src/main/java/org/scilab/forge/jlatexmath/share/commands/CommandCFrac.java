package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.FractionAtom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.StyleAtom;
import org.scilab.forge.jlatexmath.share.TeXConstants;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.exception.ParseException;

public class CommandCFrac extends Command2A {

	char opt;

	@Override
	public boolean init(TeXParser tp) {
		opt = tp.getOptionAsChar();
		if (opt != 'c' && opt != 'r' && opt != 'l' && opt != '\0') {
			throw new ParseException(tp, "Invalid option in \\cfrac");
		}
		return true;
	}

	@Override
	public Atom newI(TeXParser tp, Atom a, Atom b) {
		TeXConstants.Align align;
		if (opt == 'l') {
			align = TeXConstants.Align.LEFT;
		} else if (opt == 'r') {
			align = TeXConstants.Align.RIGHT;
		} else {
			align = TeXConstants.Align.CENTER;
		}

		a = new FractionAtom(a, b, true, align, TeXConstants.Align.CENTER);
		return new RowAtom(new StyleAtom(TeXConstants.STYLE_DISPLAY, a));
	}

}
