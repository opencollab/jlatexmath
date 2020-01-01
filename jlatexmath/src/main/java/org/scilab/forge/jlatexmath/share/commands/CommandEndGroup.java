package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandEndGroup extends Command {

	@Override
	public boolean init(TeXParser tp) {
		tp.processRBrace();
		return false;
	}

}
