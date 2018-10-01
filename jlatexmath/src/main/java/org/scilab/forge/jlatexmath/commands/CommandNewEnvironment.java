package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.NewEnvironmentMacro;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandNewEnvironment extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String name = tp.getArgAsString();
		final int nbargs = tp.getOptionAsPositiveInteger(0);
		final String before = tp.getArgAsString();
		final String after = tp.getArgAsString();
		NewEnvironmentMacro.addNewEnvironment(tp, name, before, after, nbargs, false);
		return false;
	}

	@Override
	public Command duplicate() {
		return new CommandNewEnvironment();
	}

}
