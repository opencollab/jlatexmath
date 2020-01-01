package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.RomanAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.mhchem.MhchemParser;

public class CommandCE extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String code = tp.getGroupAsArgument();
		final MhchemParser mp = new MhchemParser(code);
		mp.parse();
		tp.addToConsumer(new RomanAtom(mp.get()));
		return false;
	}

}
