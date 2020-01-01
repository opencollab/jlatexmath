package org.scilab.forge.jlatexmath.share.commands;

import org.scilab.forge.jlatexmath.share.CursorAtom;
import org.scilab.forge.jlatexmath.share.TeXParser;
import org.scilab.forge.jlatexmath.share.platform.FactoryProvider;
import org.scilab.forge.jlatexmath.share.platform.graphics.GraphicsFactory;

public class CommandJlmCursor extends Command {

	@Override
	public boolean init(TeXParser tp) {
		double size = tp.getArgAsDecimal();

		CursorAtom atom = new CursorAtom(FactoryProvider.getInstance()
				.getGraphicsFactory().createColor(GraphicsFactory.CURSOR_RED,
						GraphicsFactory.CURSOR_GREEN,
						GraphicsFactory.CURSOR_BLUE),
				size);

		tp.addToConsumer(atom);
		return false;

	}

}
