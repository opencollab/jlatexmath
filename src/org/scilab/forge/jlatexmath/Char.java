/* Char.java
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
 */

package org.scilab.forge.jlatexmath;

import java.awt.Font;

/**
 * Represents a character together with its font, font ID and metric information.
 */
public class Char {

    private final char c;
    private final Font font;
    private final Metrics m;
    private final int fontCode;
    
    public Char(char c, Font f, int fc, Metrics m) {
	font = f;
	fontCode = fc;
	this.c = c;
	this.m = m;
    }
    
    public CharFont getCharFont() {
	return new CharFont(c, fontCode);
    }
    
    public char getChar() {
	return c;
    }
    
    public Font getFont() {
	return font;
    }
    
    public int getFontCode() {
	return fontCode;
    }
    
    public float getWidth() {
	return m.getWidth();
    }
    
    public float getItalic() {
	return m.getItalic();
    }
    
    public float getHeight() {
	return m.getHeight();
    }
    
    public float getDepth() {
	return m.getDepth();
    }
    
    public Metrics getMetrics() {
	return m;
    }
}
