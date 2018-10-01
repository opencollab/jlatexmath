/* JavaFontRenderingBox.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2009 DENIZET Calixte
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 *
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * Linking this library statically or dynamically with other modules
 * is making a combined work based on this library. Thus, the terms
 * and conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce
 * an executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under terms
 * of your choice, provided that you also meet, for each linked independent
 * module, the terms and conditions of the license of that module.
 * An independent module is a module which is not derived from or based
 * on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obliged to do so.
 * If you do not wish to do so, delete this exception statement from your
 * version.
 *
 */

package org.scilab.forge.jlatexmath;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;
import java.util.Map;

/**
 * A box to render text in using a font found on the os.
 */
public class JavaFontRenderingBox extends Box {

	private static final FontRenderContext FRC = new FontRenderContext(new AffineTransform(),
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);

	private static Font font = new Font("Serif", Font.PLAIN, 10);
	private static TextAttribute KERNING;
	private static Integer KERNING_ON;
	private static TextAttribute LIGATURES;
	private static Integer LIGATURES_ON;
	private static boolean enabled = true;

	private final String str;
	private final TextLayout text;
	private final double size;

	static {
		try { // to avoid problems with Java 1.5
			KERNING = (TextAttribute) (TextAttribute.class.getField("KERNING").get(TextAttribute.class));
			KERNING_ON = (Integer) (TextAttribute.class.getField("KERNING_ON").get(TextAttribute.class));
			LIGATURES = (TextAttribute) (TextAttribute.class.getField("LIGATURES").get(TextAttribute.class));
			LIGATURES_ON = (Integer) (TextAttribute.class.getField("LIGATURES_ON").get(TextAttribute.class));
		} catch (Exception e) {
		}
	}

	public JavaFontRenderingBox(final String str, final int style, final double size, Font f, final boolean kerning) {
		if (JavaFontRenderingBox.enabled) {
			this.str = str;
			this.size = size;
			if (f == null) {
				f = font;
			}

			if (str.length() > 1 && kerning && KERNING != null) {
				final Map<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>() {
					{
						put(KERNING, KERNING_ON);
						put(LIGATURES, LIGATURES_ON);
					}
				};
				f = f.deriveFont(map);
			}

			this.text = new TextLayout(str, f.deriveFont(style), FRC);
			final Rectangle2D rect = text.getBounds();
			this.height = -rect.getY() * size / 10.;
			this.depth = rect.getHeight() * size / 10. - this.height;
			this.width = (rect.getWidth() + rect.getX() + 0.4) * size / 10.;
		} else {
			this.str = null;
			this.text = null;
			this.size = 0.;
			this.height = 0.;
			this.depth = 0.;
			this.width = 0.;
		}
	}

	public JavaFontRenderingBox(final String str, final int type, final double size, final Font font) {
		this(str, type, size, font, true);
	}

	public static void setFont(final String name) {
		font = new Font(name, Font.PLAIN, 10);
	}

	@Override
	public void draw(Graphics2D g2, double x, double y) {
		if (JavaFontRenderingBox.enabled) {
			startDraw(g2, x, y);
			final AffineTransform old = g2.getTransform();
			g2.translate(x, y);
			g2.scale(size / 10., size / 10.);
			text.draw(g2, 0, 0);
			g2.setTransform(old);
			endDraw(g2);
		}
	}

	@Override
	public FontInfo getLastFont() {
		return null;
	}

	@Override
	public String toString() {
		return "JavaFontRenderingBox: " + super.toString();
	}

	public static void disable() {
		JavaFontRenderingBox.enabled = false;
	}
}
