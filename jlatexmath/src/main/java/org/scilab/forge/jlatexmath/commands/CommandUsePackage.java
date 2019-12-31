package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.JLMPackage;
import org.scilab.forge.jlatexmath.TeXParser;

public class CommandUsePackage extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String name = tp.getArgAsString();
		JLMPackage.usePackage(name);
		return false;
	}

	@Override
	public Command duplicate() {
		return new CommandUsePackage();
	}

}
