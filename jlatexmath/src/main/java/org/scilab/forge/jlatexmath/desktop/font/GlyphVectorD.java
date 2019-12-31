package org.scilab.forge.jlatexmath.desktop.font;

import org.scilab.forge.jlatexmath.desktop.geom.GeneralPathD;
import org.scilab.forge.jlatexmath.platform.FactoryProvider;
import org.scilab.forge.jlatexmath.platform.font.GlyphVector;
import org.scilab.forge.jlatexmath.platform.geom.Shape;

public class GlyphVectorD extends GlyphVector {

	private java.awt.font.GlyphVector impl;

	public GlyphVectorD(java.awt.font.GlyphVector gv) {
		impl = gv;
	}

	@Override
	public Shape getGlyphOutline(int i) {
		java.awt.Shape ret = impl.getGlyphOutline(i);
		if (ret instanceof java.awt.geom.GeneralPath) {
			return new GeneralPathD((java.awt.geom.GeneralPath) ret);
		}

		FactoryProvider.getInstance()
				.debug("unhandled Shape " + ret.getClass());
		return null;
	}

}
