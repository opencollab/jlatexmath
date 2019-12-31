package org.scilab.forge.jlatexmath.commands;

import org.scilab.forge.jlatexmath.TeXParser;

public class CommandBeginGroup extends Command {

	@Override
	public boolean init(TeXParser tp) {
		tp.processLBrace();
		return false;
	}

}
