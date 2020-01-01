package org.scilab.forge.jlatexmath.share.platform.box;

import org.scilab.forge.jlatexmath.share.Box;

/**
 * Doesn't decorate the box, but simply returns the original one.
 */
public class DefaultBoxDecorator implements BoxDecorator {

	@Override
	public Box decorate(Box box) {
		return box;
	}
}
