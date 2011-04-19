/* ScaleBox.java
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
 */

package org.scilab.forge.jlatexmath;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * A box representing a scaled box.
 */
public class JavaFontRenderingBox extends Box {

    private static final Graphics2D TEMPGRAPHIC = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

    private static Font font = new Font("Serif", Font.PLAIN, 10);

    private String str;
    private TextLayout text;
    private float size;

    public JavaFontRenderingBox(String str, int type, float size, Font f) {
	this.str = str;
	this.size = size;
	this.text = new TextLayout(str, f.deriveFont(type), TEMPGRAPHIC.getFontRenderContext());
	Rectangle2D rect = text.getBounds();
	this.height = (float) (-rect.getY() / 10);
        this.depth = (float) (rect.getHeight() / 10) - this.height;
	this.width = (float) ((rect.getWidth() + rect.getX()) / 10);
    }

    public JavaFontRenderingBox(String str, int type, float size) {
	this(str, type, size, font);
    }

    public static void setFont(String name) {
	font = new Font(name, Font.PLAIN, 10);
    }

    public void draw(Graphics2D g2, float x, float y) {
	drawDebug(g2, x, y);
	g2.translate(x, y);
	g2.scale(0.1 * size, 0.1 * size);
	text.draw(g2, 0, 0);
	g2.scale(10 / size, 10 / size);
	g2.translate(-x, -y);
    }

    public int getLastFontId() {
	return 0;
    }
}
