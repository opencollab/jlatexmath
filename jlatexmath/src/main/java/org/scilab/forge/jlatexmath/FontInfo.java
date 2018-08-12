/* FontInfo.java
 * =========================================================================
 * This file is originally part of the JMathTeX Library - http://jmathtex.sourceforge.net
 *
 * Copyright (C) 2004-2007 Universiteit Gent
 * Copyright (C) 2009-2018 DENIZET Calixte
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
import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the font information for 1 font.
 */
public class FontInfo {

    /**
     * Maximum number of character codes in a TeX font.
     */
    private static final int NUMBER_OF_CHAR_CODES = 256;

    // ID
    protected final int fontId;

    // font
    protected Font font;
    protected final Object base;
    protected final int size;
    protected final String path;
    protected final String fontName;

    protected final double[][] metrics;

    // skew character of the font (used for positioning accents)
    protected final char skewChar;

    // general parameters for this font
    protected final double xHeight;
    protected final double space;
    protected final double quad;
    protected final int boldId;
    protected final int romanId;
    protected final int ssId;
    protected final int ttId;
    protected final int itId;

    protected CharFont[][] lig;
    protected double[][] kern;
    protected CharFont[] nextLarger;
    protected char[][] extensions;

    public FontInfo(int fontId, int size, Object base, String path, String fontName, double xHeight, double space, double quad, char skewChar, int boldId, int romanId, int ssId, int ttId, int itId) {
        this.fontId = fontId;
        this.base = base;
        this.path = path;
        this.fontName = fontName;
        this.xHeight = xHeight;
        this.space = space;
        this.quad = quad;
        this.skewChar = skewChar;
        this.boldId = boldId == -1 ? fontId : boldId;
        this.romanId = romanId == -1 ? fontId : romanId;
        this.ssId = ssId == -1 ? fontId : ssId;
        this.ttId = ttId == -1 ? fontId : ttId;
        this.itId = itId == -1 ? fontId : itId;
        this.size = size == -1 ? NUMBER_OF_CHAR_CODES : size;
        this.metrics = new double[this.size][];
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
    public void addKern(final char left, final char right, final double k) {
        if (kern == null) {
            kern = new double[size][];
        }
        if (kern[left] == null) {
            kern[left] = new double[size];
        }
        kern[left][right] = k;
    }

    /**
     * @param left
     *           left character
     * @param right
     *           right character
     * @param ligChar
     *           ligature to replace left and right character
     */
    public void addLigature(final char left, final char right, final char ligChar) {
        if (lig == null) {
            lig = new CharFont[size][];
        }
        if (lig[left] == null) {
            lig[left] = new CharFont[size];
        }
        lig[left][right] = new CharFont(ligChar, fontId);
    }

    public char[] getExtension(final char c) {
        if (extensions == null) {
            return null;
        }
        return extensions[c];
    }

    public double getKern(final char left, final char right, final double factor) {
        if (kern == null || kern[left] == null) {
            return 0.;
        }

        return kern[left][right] * factor;
    }

    public CharFont getLigature(final char left, final char right) {
        if (lig == null || lig[left] == null) {
            return null;
        }
        return lig[left][right];
    }

    public double[] getMetrics(final char c) {
        return metrics[c];
    }

    public double getWidth(final char c) {
        return metrics[c][0];
    }

    public double getHeight(final char c) {
        return metrics[c][1];
    }

    public double getDepth(final char c) {
        return metrics[c][2];
    }

    public double getItalic(final char c) {
        return metrics[c][3];
    }

    public CharFont getNextLarger(final char c) {
        if (nextLarger == null) {
            return null;
        }
        return nextLarger[c];
    }

    /**
     * @return the skew character of the font (for the correct positioning of
     *         accents)
     */
    public double getSkew(final char c, final double factor) {
        if (skewChar != '\0') {
            return getKern(c, skewChar, factor);
        }
        return 0.;
    }

    public void setExtension(final char c, final char[] ext) {
        if (extensions == null) {
            extensions = new char[size][];
        }
        extensions[c] = ext;
    }

    public void setMetrics(char c, double[] arr) {
        metrics[c] = arr;
    }

    public void setNextLarger(final char c, final char larger, final int fontLarger) {
        if (nextLarger == null) {
            nextLarger = new CharFont[size];
        }
        nextLarger[c] = new CharFont(larger, fontLarger);
    }

    public double getQuad(final double factor) {
        return quad * factor;
    }

    public final double getSpace(final double factor) {
        return space * factor;
    }

    public final double getXHeight(final double factor) {
        return xHeight * factor;
    }

    public final boolean hasSpace() {
        return space > TeXFormula.PREC;
    }

    public final char getSkewChar() {
        return skewChar;
    }

    public final int getId() {
        return fontId;
    }

    public final int getBoldId() {
        return boldId;
    }

    public final int getRomanId() {
        return romanId;
    }

    public final int getTtId() {
        return ttId;
    }

    public final int getItId() {
        return itId;
    }

    public final int getSsId() {
        return ssId;
    }

    public final Font getFont() {
        if (font == null) {
            font = FontLoader.createFont(base, path);
        }
        return font;
    }

    public String toString() {
        return "FontInfo: " + fontId + "::" + path + "::" +fontName;
    }
}
