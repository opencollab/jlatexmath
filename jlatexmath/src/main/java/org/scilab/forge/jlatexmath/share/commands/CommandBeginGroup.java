package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandBeginGroup extends Command {

	@Override
	public boolean init(TeXParser tp) {
		tp.processLBrace();
		return false;
	}

}
