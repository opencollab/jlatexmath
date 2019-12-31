package org.scilab.forge.jlatexmath.desktop.box;

import org.scilab.forge.jlatexmath.Box;
import org.scilab.forge.jlatexmath.ShapeBox;
import org.scilab.forge.jlatexmath.platform.box.BoxDecorator;

/**
 * Creates a ShapeBox.
 */
public class ShapeBoxDecorator implements BoxDecorator {

	@Override
	public Box decorate(Box box) {
		return ShapeBox.create(box);
	}
}
