package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.TeXParser;

public class CommandEndGroup extends Command {

	@Override
	public boolean init(TeXParser tp) {
		tp.processRBrace();
		return false;
	}

	@Override
	public Command duplicate() {
		return new CommandEndGroup();
	}

}
