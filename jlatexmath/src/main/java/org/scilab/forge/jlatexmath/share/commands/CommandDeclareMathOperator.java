package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.NewCommandMacro;
import org.scilab.forge.jlatexmath.share.TeXParser;

public class CommandDeclareMathOperator extends Command {

	@Override
	public boolean init(TeXParser tp) {
		final String name = tp.getArgAsCommand();
		final String base = tp.getGroupAsArgument();
		final String code = "\\mathop{\\mathrm{" + base + "}}\\nolimits";
		NewCommandMacro.addNewCommand(tp, name, code, 0, false);
		return false;
	}

}
