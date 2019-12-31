package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.NewCommandMacro;
import org.scilab.forge.jlatexmath.SetLengthAtom;
import org.scilab.forge.jlatexmath.TeXLengthSettings;
import org.scilab.forge.jlatexmath.TeXParser;

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
