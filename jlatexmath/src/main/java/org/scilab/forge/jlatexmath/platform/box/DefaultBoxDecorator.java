package org.scilab.forge.jlatexmath.platform.box;

import org.scilab.forge.jlatexmath.Box;

/**
 * Doesn't decorate the box, but simply returns the original one.
 */
public class DefaultBoxDecorator implements BoxDecorator {

	@Override
	public Box decorate(Box box) {
		return box;
	}
}
