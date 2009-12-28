/* FontInfo.java
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
import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the font information for 1 font.
 */
public class FontInfo {
    
    /**
     * Maximum number of character codes in a TeX font.
     */
    public static final int NUMBER_OF_CHAR_CODES = 256;
    
    private class CharCouple {
        
        private final char left, right;
        
        CharCouple(char l, char r) {
            left = l;
            right = r;
        }
        
        public boolean equals(Object o) {
            CharCouple lig = (CharCouple) o;
            return left == lig.left && right == lig.right;
        }
        
        public int hashCode() {
            return (left + right) % 128;
        }
    }
    
    // ID
    private final int fontId;
    
    // font
    private final Font font;
    
    private final float[][] metrics = new float[NUMBER_OF_CHAR_CODES][];
    private final Map<CharCouple,Character> lig = new HashMap<CharCouple,Character> ();
    private final Map<CharCouple,Float> kern = new HashMap<CharCouple,Float>();
    private final CharFont[] nextLarger = new CharFont[NUMBER_OF_CHAR_CODES];
    private final int[][] extensions = new int[NUMBER_OF_CHAR_CODES][];
    
    // skew character of the font (used for positioning accents)
    private char skewChar = (char) -1;
    
    // general parameters for this font
    private final float xHeight; 
    private final float space;
    private final float quad;
    private int boldId;
    protected final String boldVersion;
    
    public FontInfo(int fontId, Font font, float xHeight, float space, float quad, String boldVersion) {
        this.fontId = fontId;
        this.font = font;
        this.xHeight = xHeight;
        this.space = space;
        this.quad = quad;
	this.boldVersion = boldVersion;
    }
    
    /**
     *
     * @param left
     *           left character
     * @param right
     *           right character
     * @param k
     *           kern value
     */
    public void addKern(char left, char right, float k) {
        kern.put(new CharCouple(left, right), new Float(k));
    }
    
    /**
     * @param left
     *           left character
     * @param right
     *           right character
     * @param ligChar
     *           ligature to replace left and right character
     */
    public void addLigature(char left, char right, char ligChar) {
        lig.put(new CharCouple(left, right), new Character(ligChar));
    }
    
    public int[] getExtension(char ch) {
        return extensions[ch];
    }
    
    public float getKern(char left, char right, float factor) {
        Object obj = kern.get(new CharCouple(left, right));
        if (obj == null)
            return 0;
        else
            return ((Float) obj).floatValue() * factor;
    }
    
    public CharFont getLigature(char left, char right) {
        Object obj = lig.get(new CharCouple(left, right));
        if (obj == null)
            return null;
        else
            return new CharFont(((Character) obj).charValue(), fontId);
    }
    
    public float[] getMetrics(char c) {
        return metrics[c];
    }
    
    public CharFont getNextLarger(char ch) {
        return nextLarger[ch];
    }
    
    public float getQuad(float factor) {
        return quad * factor;
    }
    
    /**
     * @return the skew character of the font (for the correct positioning of
     *         accents)
     */
    public char getSkewChar() {
        return skewChar;
    }
    
    public float getSpace(float factor) {
        return space * factor;
    }
    
    public float getXHeight(float factor) {
        return xHeight * factor;
    }
    
    public boolean hasSpace() {
        return space > TeXFormula.PREC;
    }
    
    public void setExtension(char ch, int[] ext) {
        extensions[ch] =  ext;
    }
    
    public void setMetrics(char c, float[] arr) {
        metrics[c] = arr;
    }
    
    public void setNextLarger(char ch, char larger, int fontLarger) {
        nextLarger[ch] = new CharFont(larger, fontLarger);
    }
    
    public void setSkewChar(char c) {
        skewChar = c;
    }
    
    public int getId() {
        return fontId;
    }

    public int getBoldId() {
        return boldId;
    }

    public void setBoldId(int id) {
	boldId = id == -1 ? fontId : id;
    }

    public Font getFont() {
        return font;
    }
}
