/* Colors.java
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


import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * An atom representing the foreground and background color of an other atom.
 */
public class Colors {

    private static Map<String, Color> all;

    private static final void init() {
        all = new HashMap<String, Color>() {
                    {
                        put("aliceblue", new Color(0xF0F8FF));
                        put("antiquewhite", new Color(0xFAEBD7));
                        put("apricot", new Color(0xFBB982));
                        put("aqua", new Color(0x00FFFF));
                        put("aquamarine", new Color(0x7FFFD4));
                        put("azure", new Color(0xF0FFFF));
                        put("beige", new Color(0xF5F5DC));
                        put("bisque", new Color(0xFFE4C4));
                        put("bittersweet", new Color(0xC04F17));
                        put("black", new Color(0x000000));
                        put("blanchedalmond", new Color(0xFFEBCD));
                        put("blue", new Color(0x0000FF));
                        put("blueviolet", new Color(0x473992));
                        put("brown", new Color(0xBF8040));
                        put("burlywood", new Color(0xDEB887));
                        put("cadetblue", new Color(0x74729A));
                        put("cerulean", new Color(0x00A2E3));
                        put("chartreuse", new Color(0x7FFF00));
                        put("chocolate", new Color(0xD2691E));
                        put("coral", new Color(0xFF7F50));
                        put("cornflowerblue", new Color(0x6495ED));
                        put("cornsilk", new Color(0xFFF8DC));
                        put("crimson", new Color(0xDC143C));
                        put("cyan", new Color(0x00FFFF));
                        put("darkblue", new Color(0x00008B));
                        put("darkcyan", new Color(0x008B8B));
                        put("darkgoldenrod", new Color(0xB8860B));
                        put("darkgray", new Color(0x404040));
                        put("darkgreen", new Color(0x006400));
                        put("darkgrey", new Color(0xA9A9A9));
                        put("darkkhaki", new Color(0xBDB76B));
                        put("darkmagenta", new Color(0x8B008B));
                        put("darkolivegreen", new Color(0x556B2F));
                        put("darkorange", new Color(0xFF8C00));
                        put("darkorchid", new Color(0xA4538A));
                        put("darkred", new Color(0x8B0000));
                        put("darksalmon", new Color(0xE9967A));
                        put("darkseagreen", new Color(0x8FBC8F));
                        put("darkslateblue", new Color(0x483D8B));
                        put("darkslategray", new Color(0x2F4F4F));
                        put("darkslategrey", new Color(0x2F4F4F));
                        put("darkturquoise", new Color(0x00CED1));
                        put("darkviolet", new Color(0x9400D3));
                        put("deeppink", new Color(0xFF1493));
                        put("deepskyblue", new Color(0x00BFFF));
                        put("dimgray", new Color(0x696969));
                        put("dimgrey", new Color(0x696969));
                        put("dodgerblue", new Color(0x1E90FF));
                        put("firebrick", new Color(0xB22222));
                        put("floralwhite", new Color(0xFFFAF0));
                        put("forestgreen", new Color(0x009B55));
                        put("fuchsia", new Color(0xFF00FF));
                        put("gainsboro", new Color(0xDCDCDC));
                        put("ghostwhite", new Color(0xF8F8FF));
                        put("gold", new Color(0xFFD700));
                        put("goldenrod", new Color(0xFFDF42));
                        put("gray", new Color(0x808080));
                        put("green", new Color(0x00FF00));
                        put("greenyellow", new Color(0xADFF2F));
                        put("grey", new Color(0x808080));
                        put("honeydew", new Color(0xF0FFF0));
                        put("hotpink", new Color(0xFF69B4));
                        put("indianred", new Color(0xCD5C5C));
                        put("indigo", new Color(0x4B0082));
                        put("ivory", new Color(0xFFFFF0));
                        put("junglegreen", new Color(0x00A99A));
                        put("khaki", new Color(0xF0E68C));
                        put("lavender", new Color(0xE6E6FA));
                        put("lavenderblush", new Color(0xFFF0F5));
                        put("lawngreen", new Color(0x7CFC00));
                        put("lemonchiffon", new Color(0xFFFACD));
                        put("lightblue", new Color(0xADD8E6));
                        put("lightcoral", new Color(0xF08080));
                        put("lightcyan", new Color(0xE0FFFF));
                        put("lightgoldenrodyellow", new Color(0xFAFAD2));
                        put("lightgray", new Color(0xBFBFBF));
                        put("lightgreen", new Color(0x90EE90));
                        put("lightgrey", new Color(0xD3D3D3));
                        put("lightpink", new Color(0xFFB6C1));
                        put("lightsalmon", new Color(0xFFA07A));
                        put("lightseagreen", new Color(0x20B2AA));
                        put("lightskyblue", new Color(0x87CEFA));
                        put("lightslategray", new Color(0x778899));
                        put("lightslategrey", new Color(0x778899));
                        put("lightsteelblue", new Color(0xB0C4DE));
                        put("lightyellow", new Color(0xFFFFE0));
                        put("lime", new Color(0xBFFF00));
                        put("limegreen", new Color(0x8DC73E));
                        put("linen", new Color(0xFAF0E6));
                        put("magenta", new Color(0xFF00FF));
                        put("mahogany", new Color(0xA9341F));
                        put("maroon", new Color(0x800000));
                        put("mediumaquamarine", new Color(0x66CDAA));
                        put("mediumblue", new Color(0x0000CD));
                        put("mediumorchid", new Color(0xBA55D3));
                        put("mediumpurple", new Color(0x9370DB));
                        put("mediumseagreen", new Color(0x3CB371));
                        put("mediumslateblue", new Color(0x7B68EE));
                        put("mediumspringgreen", new Color(0x00FA9A));
                        put("mediumturquoise", new Color(0x48D1CC));
                        put("mediumvioletred", new Color(0xC71585));
                        put("melon", new Color(0xF89E7B));
                        put("midnightblue", new Color(0x191970));
                        put("mintcream", new Color(0xF5FFFA));
                        put("mistyrose", new Color(0xFFE4E1));
                        put("moccasin", new Color(0xFFE4B5));
                        put("mulberry", new Color(0x93C93));
                        put("navajowhite", new Color(0xFFDEAD));
                        put("navy", new Color(0x000080));
                        put("oldlace", new Color(0xFDF5E6));
                        put("olive", new Color(0x808000));
                        put("olivedrab", new Color(0x6B8E23));
                        put("olivegreen", new Color(0x3C8031));
                        put("orange", new Color(0xFF8000));
                        put("orangered", new Color(0xED135A));
                        put("orchid", new Color(0xDA70D6));
                        put("palegoldenrod", new Color(0xEEE8AA));
                        put("palegreen", new Color(0x98FB98));
                        put("paleturquoise", new Color(0xAFEEEE));
                        put("palevioletred", new Color(0xDB7093));
                        put("papayawhip", new Color(0xFFEFD5));
                        put("peach", new Color(0xF7965A));
                        put("peachpuff", new Color(0xFFDAB9));
                        put("peru", new Color(0xCD853F));
                        put("pinegreen", new Color(0x008B72));
                        put("pink", new Color(0xFFBFBF));
                        put("plum", new Color(0xDDA0DD));
                        put("powderblue", new Color(0xB0E0E6));
                        put("processblue", new Color(0x00B0F0));
                        put("purple", new Color(0xBF0040));
                        put("rawsienna", new Color(0x974006));
                        put("red", new Color(0xFF0000));
                        put("redorange", new Color(0xF26035));
                        put("rhodamine", new Color(0xEF559F));
                        put("rosybrown", new Color(0xBC8F8F));
                        put("royalblue", new Color(0x4169E1));
                        put("royalpurple", new Color(0x613F99));
                        put("saddlebrown", new Color(0x8B4513));
                        put("salmon", new Color(0xF69289));
                        put("sandybrown", new Color(0xF4A460));
                        put("seagreen", new Color(0x2E8B57));
                        put("seashell", new Color(0xFFF5EE));
                        put("sepia", new Color(0x671800));
                        put("sienna", new Color(0xA0522D));
                        put("silver", new Color(0xC0C0C0));
                        put("skyblue", new Color(0x87CEEB));
                        put("slateblue", new Color(0x6A5ACD));
                        put("slategray", new Color(0x708090));
                        put("slategrey", new Color(0x708090));
                        put("snow", new Color(0xFFFAFA));
                        put("springgreen", new Color(0xC6DC67));
                        put("steelblue", new Color(0x4682B4));
                        put("tan", new Color(0xD2B48C));
                        put("teal", new Color(0x008080));
                        put("tealblue", new Color(0x00AEB3));
                        put("thistle", new Color(0xD8BFD8));
                        put("tomato", new Color(0xFF6347));
                        put("turquoise", new Color(0x00B4CE));
                        put("violet", new Color(0x800080));
                        put("violetred", new Color(0xEF58A0));
                        put("wheat", new Color(0xF5DEB3));
                        put("white", new Color(0xFFFFF));
                        put("whitesmoke", new Color(0xF5F5F5));
                        put("wildstrawberry", new Color(0xEE2967));
                        put("yellow", new Color(0xFFFF00));
                        put("yellowgreen", new Color(0x98CC70));
                    }
            };
    }

    public static Color getFromName(final String name) {
        if (all == null) {
            init();
        }
        final Color c = all.get(name);
        if (c == null) {
            return all.get(name.toLowerCase());
        }
        return c;
    }

    public static void add(final String name, final Color color) {
        if (all == null) {
            init();
        }
        all.put(name, color);
    }

    public static Color conv(final double c, final double m, final double y, final double k) {
        final double kk = 255. * (1. - k);
        final int R = (int)(kk * (1. - c) + 0.5);
        final int G = (int)(kk * (1. - m) + 0.5);
        final int B = (int)(kk * (1. - y) + 0.5);
        return new Color((R << 16) | (G << 8) | B);
    }

    public static Color convHSB(final double h, final double s, final double l) {
        final double h1 = normH(h);
        return new Color(Color.HSBtoRGB((float)h1, (float)s, (float)l));
    }

    public static Color convHSL(final double h, final double s, final double l, final double a) {
        // https://www.w3.org/TR/css3-color/#hsl-color for algorithm
        final double ls = l * s;
        final double m2 = l + (l <= 0.5 ? ls : (s - ls));
        final double m1 = l * 2. - m2;
        final double h1 = normH(h);
        final float R = (float)HUEtoRGB(m1, m2, h1 + 1. / 3.);
        final float G = (float)HUEtoRGB(m1, m2, h1);
        final float B = (float)HUEtoRGB(m1, m2, h1 - 1. / 3.);

        return new Color(R, G, B, (float)a);
    }

    public static Color convHSL(final double h, final double s, final double l) {
        return convHSL(h, s, l, 1f);
    }

    private static double HUEtoRGB(final double m1, final double m2, double h) {
        if (h < 0.) {
            h += 1.;
        } else if (h > 1.) {
            h -= 1.;
        }
        final double h6 = h * 6.;
        if (h6 < 1.) {
            return m1 + (m2 - m1) * h6;
        }
        if (h * 2. < 1.) {
            return m2;
        }
        if (h * 3. < 2.) {
            return m1 + (m2 - m1) * (4. - h6);
        }
        return m1;
    }

    private static double mod360(final double x) {
        return x - Math.floor(x / 360.) * 360.;
    }

    private static double normH(final double x) {
        return mod360(mod360(x) + 360.) / 360.;
    }

    private static double adjust(final double c, final double factor) {
        if (c == 0. || factor == 0.) {
            return 0.;
        }

        final double Gamma = 0.8;
        return Math.round(Math.pow(c * factor, Gamma));
    }

    public static Color convWave(final double waveLen) {
        double R, G, B;

        if (waveLen >= 380. && waveLen <= 439.) {
            R = -(waveLen - 440.) / 60.;
            G = 0.;
            B = 1.;
        } else if (waveLen >= 440. && waveLen <= 489.) {
            R = 0.;
            G = (waveLen - 440.) / 50.;
            B = 1.;
        } else if (waveLen >= 490. && waveLen <= 509.) {
            R = 0.;
            G = 1.;
            B = -(waveLen - 510.) / 20.;
        } else if (waveLen >= 510. && waveLen <= 579.) {
            R = (waveLen - 510.) / 70.;
            G = 1.;
            B = 0.;
        } else if (waveLen >= 580. && waveLen <= 644.) {
            R = 1.;
            G = -(waveLen - 645.) / 65.;
            B = 0.;
        } else if (waveLen >= 645. && waveLen <= 780.) {
            R = 1.;
            G = 0.;
            B = 0.;
        } else {
            R = 0.;
            G = 0.;
            B = 0.;
        }

        final double twave = Math.floor(waveLen);
        double factor;
        if (twave >= 380. && twave <= 419.) {
            factor = 0.3 + 0.7 * (waveLen - 380.) / 40.;
        } else if (twave >= 420. && twave <= 700.) {
            factor = 1.;
        } else if (twave >= 701. && twave <= 780.) {
            factor = 0.3 + 0.7 * (780. - waveLen) / 80.;
        } else {
            factor = 0.;
        }

        R = adjust(R, factor);
        G = adjust(G, factor);
        B = adjust(B, factor);

        return new Color((float)R, (float)G, (float)B);
    }
}
