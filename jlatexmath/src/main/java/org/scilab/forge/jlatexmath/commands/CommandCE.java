package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.RomanAtom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.mhchem.MhchemParser;

public class CommandCE extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String code = tp.getGroupAsArgument();
		final MhchemParser mp = new MhchemParser(code);
		mp.parse();
		tp.addToConsumer(new RomanAtom(mp.get()));
		return false;
	}

	@Override
	public Command duplicate() {
		return new CommandCE();
	}

}
