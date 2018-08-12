/* UniFontInfo.java
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
import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the font information for 1 font.
 */
public class UniFontInfo extends FontInfo {

    private final Map<Character, Character> unicode;

    public UniFontInfo(int fontId, int size, Object base, String path, String fontName, double xHeight, double space, double quad, char skewChar, int boldId, int romanId, int ssId, int ttId, int itId) {
        super(fontId, size, base, path, fontName, xHeight, space, quad, skewChar, boldId, romanId, ssId, ttId, itId);
        this.unicode = new HashMap<Character, Character>(size);
    }

    public void addKern(final char left, final char right, final double k) {
        super.addKern(get(left), get(right), k);
    }

    public double getKern(final char left, final char right, final double factor) {
        if (kern == null) {
            return 0.;
        }
        final char l = unicode.get(left);
        if (kern[l] == null) {
            return 0.;
        }
        return kern[l][unicode.get(right)] * factor;
    }

    public void addLigature(final char left, final char right, final char ligChar) {
        super.addLigature(get(left), get(right), ligChar);
    }

    public CharFont getLigature(final char left, final char right) {
        if (lig == null) {
            return null;
        }
        final char l = unicode.get(left);
        if (lig[l] == null) {
            return null;
        }
        return lig[l][unicode.get(right)];
    }

    public char[] getExtension(final char c) {
        if (extensions == null) {
            return null;
        }
        return extensions[unicode.get(c)];
    }

    public double[] getMetrics(final char c) {
        return metrics[unicode.get(c)];
    }

    public double getWidth(final char c) {
        return metrics[unicode.get(c)][0];
    }

    public double getHeight(final char c) {
        return metrics[unicode.get(c)][1];
    }

    public double getDepth(final char c) {
        return metrics[unicode.get(c)][2];
    }

    public double getItalic(final char c) {
        return metrics[unicode.get(c)][3];
    }

    public CharFont getNextLarger(final char c) {
        if (nextLarger == null) {
            return null;
        }
        return nextLarger[unicode.get(c)];
    }

    public void setExtension(final char c, final char[] ext) {
        super.setExtension(get(c), ext);
    }

    public void setMetrics(final char c, final double[] arr) {
        super.setMetrics(get(c), arr);
    }

    public void setNextLarger(final char c, final char larger, final int fontLarger) {
        super.setNextLarger(get(c), larger, fontLarger);
    }

    public String toString() {
        return "UniFontInfo: " + fontId + "::" + path + "::" +fontName;
    }

    private char get(final char c) {
        final Character ch = unicode.get(c);
        if (ch == null) {
            final char s = (char)unicode.size();
            unicode.put(c, s);
            return s;
        }
        return ch.charValue();
    }
}
