/* TeXFont.java
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
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.Character.UnicodeBlock;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;

/**
 * The default implementation of the TeXFont-interface. All font information is read
 * from an xml-file.
 */
public final class TeXFont {

    public static final int NO_FONT = -1;
    public static final int SERIF = 0;
    public static final int SANSSERIF = 1;
    public static final int BOLD = 2;
    public static final int ITALIC = 4;
    public static final int ROMAN = 8;
    public static final int TYPEWRITER = 16;

    private final static int[] OFFSETS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                                          16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
                                          32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
                                          0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 58, 59, 60, 61, 62, 63,
                                          64, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                                          15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 91, 92, 93, 94, 95,
                                          96, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                                          15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
                                         };
    private final static int[] KINDS = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3,
                                        3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3,
                                        3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2
                                       };

    private static boolean magnificationEnable = true;

    protected static final char NONE = '\0';
    protected static final int TOP = 0, MID = 1, REP = 2, BOT = 3;
    protected static final int WIDTH = 0, HEIGHT = 1, DEPTH = 2, IT = 3;

    private static final double TEXT_FACTOR = 1.;
    private static final double SCRIPT_FACTOR = 0.7;
    private static final double SCRIPTSCRIPT_FACTOR = 0.5;

    private static final double AXISHEIGHT = 0.25;
    private static final double BIGOPSPACING1 = 0.111112;
    private static final double BIGOPSPACING2 = 0.166667;
    private static final double BIGOPSPACING3 = 0.2;
    private static final double BIGOPSPACING4 = 0.6;
    private static final double BIGOPSPACING5 = 0.1;
    private static final double DEFAULTRULETHICKNESS = 0.039999;
    private static final double DENOM1 = 0.685951;
    private static final double DENOM2 = 0.344841;
    private static final double NUM1 = 0.676508;
    private static final double NUM2 = 0.393732;
    private static final double NUM3 = 0.443731;
    private static final double SUB1 = 0.15;
    private static final double SUB2 = 0.247217;
    private static final double SUBDROP = 0.05;
    private static final double SUP1 = 0.412892;
    private static final double SUP2 = 0.362892;
    private static final double SUP3 = 0.288889;
    private static final double SUPDROP = 0.386108;

    private static final FontInfo muFont = Configuration.getFonts().cmsy10;
    private static final FontInfo spaceFont = Configuration.getFonts().cmr10;

    private final double size; // standard size
    private double factor = 1.;
    public boolean isBold = false;
    public boolean isRoman = false;
    public boolean isSs = false;
    public boolean isTt = false;
    public boolean isIt = false;

    public TeXFont(final double pointSize) {
        size = pointSize;
    }

    public TeXFont(final double pointSize, final boolean b, final boolean rm, final boolean ss, final boolean tt, final boolean it) {
        this(pointSize, 1, b, rm, ss, tt, it);
    }

    public TeXFont(final double pointSize, final double f, final boolean b, final boolean rm, final boolean ss, final boolean tt, final boolean it) {
        size = pointSize;
        factor = f;
        isBold = b;
        isRoman = rm;
        isSs = ss;
        isTt = tt;
        isIt = it;
    }

    public TeXFont copy() {
        return new TeXFont(size, factor, isBold, isRoman, isSs, isTt, isIt);
    }

    public TeXFont deriveFont(final double size) {
        return new TeXFont(size, factor, isBold, isRoman, isSs, isTt, isIt);
    }

    public TeXFont scaleFont(final double factor) {
        return new TeXFont(size, factor, isBold, isRoman, isSs, isTt, isIt);
    }

    public double getScaleFactor() {
        return factor;
    }

    public double getAxisHeight(int style) {
        return AXISHEIGHT * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getBigOpSpacing1(int style) {
        return BIGOPSPACING1 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getBigOpSpacing2(int style) {
        return BIGOPSPACING2 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getBigOpSpacing3(int style) {
        return BIGOPSPACING3 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getBigOpSpacing4(int style) {
        return BIGOPSPACING4 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getBigOpSpacing5(int style) {
        return BIGOPSPACING5 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getDefaultRuleThickness(int style) {
        return DEFAULTRULETHICKNESS * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getDenom1(int style) {
        return DENOM1 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getDenom2(int style) {
        return DENOM2 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getNum1(int style) {
        return NUM1 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getNum2(int style) {
        return NUM2 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getNum3(int style) {
        return NUM3 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSub1(int style) {
        return SUB1 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSub2(int style) {
        return SUB2 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSubDrop(int style) {
        return SUBDROP * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSup1(int style) {
        return SUP1 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSup2(int style) {
        return SUP2 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSup3(int style) {
        return SUP3 * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getSupDrop(int style) {
        return SUPDROP * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    private Char getChar(char c, TextStyle[] styles, int style) {
        int kind, offset;
        if (c < OFFSETS.length) {
            kind = KINDS[c];
            offset = OFFSETS[c];
        } else {
            kind = TextStyle.UNICODE;
            offset = c;
        }

        // if the mapping for the character's range, then use the default style
        if (styles[kind] == null) {
            styles = TextStyle.getDefault();
        }
        return getChar(new CharFont((char)(styles[kind].getStart() + offset), styles[kind].getFont()), style);
    }

    public Char getChar(char c, String textStyle, int style) {
        return getChar(c, TextStyle.get(textStyle), style);
    }

    public Char getChar(char c, int textStyle, int style) {
        return getChar(c, TextStyle.get(textStyle), style);
    }

    public Char getChar(CharFont cf, int style) {
        FontInfo info = cf.getFont();

        if (isBold) {
            info = info.getBold();
        }
        if (isRoman) {
            info = info.getRoman();
        }
        if (isSs) {
            info = info.getSs();
        }
        if (isTt) {
            info = info.getTt();
        }
        if (isIt) {
            info = info.getIt();
        }
        return new Char(cf.c, info, getMetrics(info, cf.c, factor * getSizeFactor(style)));
    }

    public Char getDefaultChar(char c, int style) {
        return getChar(c, TextStyle.getDefault(), style);
    }

    public Extension getExtension(Char c, int style) {
        final Font f = c.getFont();
        final double s = getSizeFactor(style);

        // construct Char for every part
        final FontInfo fi = c.getFontInfo();
        final char[] ext = fi.getExtension(c.getChar());
        final Char top = ext[0] == NONE ? null : new Char(ext[0], fi, getMetrics(fi, ext[0], s));
        final Char mid = ext[1] == NONE ? null : new Char(ext[1], fi, getMetrics(fi, ext[1], s));
        final Char rep = ext[2] == NONE ? null : new Char(ext[2], fi, getMetrics(fi, ext[2], s));
        final Char bot = ext[3] == NONE ? null : new Char(ext[3], fi, getMetrics(fi, ext[3], s));

        return new Extension(top, mid, rep, bot);
    }

    public double getKern(CharFont left, CharFont right, int style) {
        if (left.getFont() == right.getFont()) {
            return left.getFont().getKern(left.c, right.c, getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT);
        }
        return 0.;
    }

    public CharFont getLigature(CharFont left, CharFont right) {
        if (left.getFont() == right.getFont()) {
            return left.getFont().getLigature(left.c, right.c);
        }
        return null;
    }

    private Metrics getMetrics(CharFont cf, double size) {
        return getMetrics(cf.getFont(), cf.c, size);
    }

    private Metrics getMetrics(FontInfo fi, char c, double size) {
        final double[] m = fi.getMetrics(c);
        return new Metrics(m[WIDTH], m[HEIGHT], m[DEPTH], m[IT], size * TeXFormula.PIXELS_PER_POINT, size);
    }

    public FontInfo getMuFont() {
        return muFont;
    }

    public Char getNextLarger(Char c, int style) {
        final CharFont ch = c.getFontInfo().getNextLarger(c.getChar());
        final FontInfo newInfo = ch.getFont();
        return new Char(ch.c, newInfo, getMetrics(ch, getSizeFactor(style)));
    }

    public double getQuad(int style, FontInfo info) {
        return info.getQuad(getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT);
    }

    public double getQuad(int style) {
        return getQuad(style, getMuFont());
    }

    public double getSize() {
        return size;
    }

    public double getSkew(final CharFont cf, final int style) {
        return cf.getFont().getSkew(cf.c, getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT);
    }

    public double getSpace(int style) {
        return spaceFont.getSpace(getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT);
    }

    public double getXHeight(int style, FontInfo font) {
        return font.getXHeight(getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT);
    }

    public double getDefaultXHeight(int style) {
        return spaceFont.getXHeight(getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT);
    }

    public double getMHeight(int style) {
        return TextStyle.getDefault(TextStyle.CAPITALS).getFont().getHeight('M') * getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public double getEM(int style) {
        return getSizeFactor(style) * TeXFormula.PIXELS_PER_POINT;
    }

    public boolean hasNextLarger(Char c) {
        return c.getFontInfo().getNextLarger(c.getChar()) != null;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean getBold() {
        return isBold;
    }

    public void setRoman(boolean rm) {
        isRoman = rm;
    }

    public boolean getRoman() {
        return isRoman;
    }

    public void setTt(boolean tt) {
        isTt = tt;
    }

    public boolean getTt() {
        return isTt;
    }

    public void setIt(boolean it) {
        isIt = it;
    }

    public boolean getIt() {
        return isIt;
    }

    public void setSs(boolean ss) {
        isSs = ss;
    }

    public boolean getSs() {
        return isSs;
    }

    public boolean hasSpace(FontInfo font) {
        return font.hasSpace();
    }

    public boolean isExtensionChar(Char c) {
        return c.getFontInfo().getExtension(c.getChar()) != null;
    }

    public static void setMathSizes(double ds, double ts, double ss, double sss) {
        /*if (magnificationEnable) {
            generalSettings.put("scriptfactor", Math.abs(ss / ds));
            generalSettings.put("scriptscriptfactor", Math.abs(sss / ds));
            generalSettings.put("textfactor", Math.abs(ts / ds));
            TeXIcon.defaultSize = Math.abs(ds);
            }*/
    }

    public static void setMagnification(double mag) {
        if (magnificationEnable) {
            TeXIcon.magFactor = mag / 1000.;
        }
    }

    public static void enableMagnification(boolean b) {
        magnificationEnable = b;
    }

    public final static double getSizeFactor(int style) {
        if (style < TeXConstants.STYLE_SCRIPT) {
            return TEXT_FACTOR;
        }

        if (style < TeXConstants.STYLE_SCRIPT_SCRIPT) {
            return SCRIPT_FACTOR;
        }

        return SCRIPTSCRIPT_FACTOR;
    }
}
