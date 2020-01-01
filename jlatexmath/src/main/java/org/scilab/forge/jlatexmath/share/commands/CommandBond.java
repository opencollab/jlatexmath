package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.mhchem.MhchemBondParser;

public class CommandBond extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String code = tp.getGroupAsArgument();
		final MhchemBondParser mbp = new MhchemBondParser(code);
		tp.addToConsumer(mbp.get());
		return false;
	}
}
