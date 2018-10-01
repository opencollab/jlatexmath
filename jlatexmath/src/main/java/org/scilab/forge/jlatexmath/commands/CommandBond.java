package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.mhchem.MhchemBondParser;

public class CommandBond extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String code = tp.getGroupAsArgument();
		final MhchemBondParser mbp = new MhchemBondParser(code);
		tp.addToConsumer(mbp.get());
		return false;
	}

	@Override
	public Command duplicate() {
		return new CommandBond();
	}

}
