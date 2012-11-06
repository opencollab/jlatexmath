/* CharAtom.java
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

/**
 * An atom representing exactly one alphanumeric character and the text style in which 
 * it should be drawn. 
 */
public class CharAtom extends CharSymbol {

    // alphanumeric character
    private final char c;
    
    // text style (null means the default text style)
    private String textStyle;

    /**
     * Creates a CharAtom that will represent the given character in the given text style.
     * Null for the text style means the default text style.
     * 
     * @param c the alphanumeric character
     * @param textStyle the text style in which the character should be drawn
     */
    public CharAtom(char c, String textStyle) {
	this.c = c;
	this.textStyle = textStyle;
    }

    public Box createBox(TeXEnvironment env) {
	if (textStyle == null) {
	    String ts = env.getTextStyle();
	    if (ts != null) {
		textStyle = ts;
	    }
	}
	boolean smallCap = env.getSmallCap();
	Char ch = getChar(env.getTeXFont(), env.getStyle(), smallCap);
	Box box = new CharBox(ch);
	if (smallCap && Character.isLowerCase(c)) {
	    // We have a small capital
	    box = new ScaleBox(box, 0.8f, 0.8f);
	}
	
	return box;
    }

    public char getCharacter() {
	return c;
    }

    /*
     * Get the Char-object representing this character ("c") in the right text style
     */
    private Char getChar(TeXFont tf, int style, boolean smallCap) {
	char chr = c;
	if (smallCap) {
	    if (Character.isLowerCase(c)) {
		chr = Character.toUpperCase(c);
	    }
	}
	if (textStyle == null) {
	    return tf.getDefaultChar(chr, style);
	} else {
	    return tf.getChar(chr, textStyle, style);
	}
    }

    public CharFont getCharFont(TeXFont tf) {
	return getChar(tf, TeXConstants.STYLE_DISPLAY, false).getCharFont();
    }

    public String toString() {
	return "CharAtom: \'" + c + "\'";
    }
}
