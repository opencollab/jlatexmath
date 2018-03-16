/* CharBox.java
 * =========================================================================
 * This file is originally part of the JMathTeX Library - http://jmathtex.sourceforge.net
 * 
 * Copyright (C) 2004-2007 Universiteit Gent
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
import java.awt.geom.AffineTransform;

/**
 * A box representing a single character.
 */
public class CharBox extends Box {

    private final CharFont cf;
    private final float size;
    private float italic; 
    
    private final char[] arr = new char[1]; 

    /**
     * Create a new CharBox that will represent the character defined by the given
     * Char-object.
     * 
     * @param c a Char-object containing the character's font information.
     */
    public CharBox(Char c) {
	cf = c.getCharFont();
	size = c.getMetrics().getSize();
	width = c.getWidth();
	height = c.getHeight();
	depth = c.getDepth();
    italic = c.getItalic();
    }

    public void addItalicCorrectionToWidth() {
        width += italic;
        italic = 0;
    }

    public void draw(Graphics2D g2, float x, float y) {
        drawDebug(g2, x, y);
        AffineTransform at = g2.getTransform();
        g2.translate(x, y);
        Font font = FontInfo.getFont(cf.fontId);

        if (Math.abs(size - TeXFormula.FONT_SCALE_FACTOR) > TeXFormula.PREC) {
            g2.scale(size / TeXFormula.FONT_SCALE_FACTOR,
                    size / TeXFormula.FONT_SCALE_FACTOR);
        }

        if (g2.getFont() != font) {
            g2.setFont(font);
        }

        arr[0] = cf.c;
        g2.drawChars(arr, 0, 1, 0, 0);
        g2.setTransform(at);
    }
    
    public int getLastFontId() {
	return cf.fontId;
    }

    public String toString() {
	return super.toString() + "=" + cf.c;
    }
}
