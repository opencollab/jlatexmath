package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.NewCommandMacro;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandNewCommand extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String name = tp.getArgAsCommand();
		final int nbargs = tp.getOptionAsPositiveInteger(0);
		final String code = tp.getGroupAsArgument();
		NewCommandMacro.addNewCommand(tp, name, code, nbargs, false);
		return false;
	}

	@Override
	public Command duplicate() {
		return new CommandNewCommand();
	}

}
