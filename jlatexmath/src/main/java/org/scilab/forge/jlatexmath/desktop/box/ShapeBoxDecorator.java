package org.scilab.forge.jlatexmath.desktop.box;

import org.scilab.forge.jlatexmath.share.Box;
import org.scilab.forge.jlatexmath.share.ShapeBox;
import org.scilab.forge.jlatexmath.share.platform.box.BoxDecorator;

/**
 * Creates a ShapeBox.
 */
public class ShapeBoxDecorator implements BoxDecorator {

	@Override
	public Box decorate(Box box) {
		return ShapeBox.create(box);
	}
}
