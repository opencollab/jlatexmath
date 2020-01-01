package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.NewCommandMacro;
import org.scilab.forge.jlatexmath.share.SetLengthAtom;
import org.scilab.forge.jlatexmath.share.TeXLengthSettings;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandRenewCommand extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String name = tp.getArgAsCommand();
		final int nbargs = tp.getOptionAsPositiveInteger(0);

		if (TeXLengthSettings.isLengthName(name) && nbargs == 0) {
			tp.addToConsumer(new SetLengthAtom(name, tp.getArgAsLength()));
			return false;
		}

		if (TeXLengthSettings.isFactorName(name) && nbargs == 0) {
			tp.addToConsumer(new SetLengthAtom(name, tp.getArgAsDecimal()));
			return false;
		}

		final String code = tp.getGroupAsArgument();

		NewCommandMacro.addNewCommand(tp, name, code, nbargs, true);
		return false;
	}
}
