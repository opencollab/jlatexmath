package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.EmptyAtom;
import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.StyleAtom;
import org.scilab.forge.jlatexmath.SubSupCom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TextStyle;
import org.scilab.forge.jlatexmath.TextStyleAtom;

public class CommandTextSubscript extends Command {

	boolean mode;

	@Override
	public boolean init(TeXParser tp) {
		mode = tp.setTextMode();
		return true;
	}

	@Override
	public void add(TeXParser tp, Atom a) {
		tp.setMathMode(mode);
		a = new TextStyleAtom(a, TextStyle.MATHNORMAL);
		tp.closeConsumer(SubSupCom.get(EmptyAtom.get(),
				new StyleAtom(TeXConstants.STYLE_TEXT, new RomanAtom(a)),
				null));
	}
}
