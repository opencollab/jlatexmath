/* ParserTest.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://jlatexmath.sourceforge.net
 *
 * Copyright (C) 2018 DENIZET Calixte
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

package org.scilab.forge.jlatexmath.parser;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.scilab.forge.jlatexmath.Colors;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;

public class ParserTest {

    private static final double PREC = Math.pow(10., -TeXParser.MAX_DEC);

    private void compareDecimal(String f) {
        TeXParser tp = new TeXParser(f);
        double r1 = tp.getDecimal();
        double r2 = Double.valueOf(f);
        String s1 = Double.toString(r1);
        String s2 = Double.toString(r2);
        assertTrue("Expect to get same numbers " + s2 + " and " + s1 + " for " + f, Math.abs(r1 - r2) < PREC);
    }

    @Test
    public void getDecimal() {
        final float start = -1000.f;
        final float end = 1000.f;
        for (int i = 0; i < 1000; ++i) {
            float r = new Random().nextFloat();
            if (r < 1e-4) {
                --i;
                continue;
            }
            r = start + r * (end - start);
            final String fl = Float.toString(r);
            if (fl.contains("E")) {
                --i;
                continue;
            }
            compareDecimal(fl);
        }

        compareDecimal(".123456");
        compareDecimal("-.123456");
        compareDecimal("+.123456");
        compareDecimal("0.001234");
    }

    @Test
    public void getLength() {
        Map<String, TeXLength.Unit> units = new HashMap<String, TeXLength.Unit>() {
            {
                put("em", TeXLength.Unit.EM);
                put("ex", TeXLength.Unit.EX);
                put("px", TeXLength.Unit.PIXEL);
                put("pix", TeXLength.Unit.PIXEL);
                put("pixel", TeXLength.Unit.PIXEL);
                put("pt", TeXLength.Unit.PT);
                put("bp", TeXLength.Unit.POINT);
                put("pica", TeXLength.Unit.PICA);
                put("pc", TeXLength.Unit.PICA);
                put("mu", TeXLength.Unit.MU);
                put("cm", TeXLength.Unit.CM);
                put("mm", TeXLength.Unit.MM);
                put("in", TeXLength.Unit.IN);
                put("sp", TeXLength.Unit.SP);
                put("dd", TeXLength.Unit.DD);
                put("cc", TeXLength.Unit.CC);
            }
        };
        String fl = "1.2345";
        for (Map.Entry<String, TeXLength.Unit> kv : units.entrySet()) {
            String s = fl + kv.getKey() + "A";
            TeXParser tp = new TeXParser(s);
            TeXLength l = tp.getLength();
            assertTrue("Expect to get " + fl + kv.getKey() + " and got " + l.getL() + ":" + l.getUnit(), l.getUnit() == kv.getValue() && l.getL() == 1.2345);
            assertTrue("Expect pos to point on A after length and got " + tp.getChar() + ": " + s, tp.getChar() == 'A');
        }

        Map<String, Character> bad = new HashMap<String, Character>() {
            {
                put("e", 'e');
                put("ei", 'e');
                put("p", 'p');
                put("pr", 'p');
                put("pi", 'p');
                put("pio", 'p');
                put("pixe", 'e');
                put("pixew", 'e');
                put("pixa", 'a');
                put("pic", 'p');
                put("picb", 'p');
                put("b", 'b');
                put("bl", 'b');
                put("c", 'c');
                put("cr", 'c');
                put("i", 'i');
                put("im", 'i');
                put("m", 'm');
                put("mz", 'm');
                put("s", 's');
                put("sz", 's');
            }
        };

        for (Map.Entry<String, Character> kv : bad.entrySet()) {
            String s = fl + kv.getKey();
            TeXParser tp = new TeXParser(s);
            TeXLength l = tp.getLength();
            char c = tp.getChar();
            assertTrue("Expect to point on \'" + kv.getValue() + "\' but point on \'" + c + "\' (" + s + ")", c == kv.getValue().charValue());
        }

    }

    @Test
    public void getArgAsString() {
        String[] s = new String[] {
            "{hello world}A",
            "           {hello world}A",
            " \t \t       {hello world}A"
        };
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser(s[i]);
            String str = tp.getArgAsString();
            assertTrue("Expect to get 'hello world' and got: " + str, str.equals("hello world"));
            assertTrue("Expect pos to point after '}'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getOptionAsString() {
        String[] s = new String[] {
            "[hello world]A",
            "           [hello world]A",
            " \t \t       [hello world]A"
        };
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser(s[i]);
            String str = tp.getOptionAsString();
            assertTrue("Expect to get 'hello world' and got: " + str, str.equals("hello world"));
            assertTrue("Expect pos to point after ']'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getArgAsPositiveInteger() {
        String[] s = new String[] {
            "{12345}A",
            "           {12345}A",
            " \t \t       {12345}A",
            " {    12345}A",
            " {    12345       }A",
        };
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser(s[i]);
            int j = tp.getArgAsPositiveInteger();
            assertTrue("Expect to get 12345 and got: " + j, j == 12345);
            assertTrue("Expect pos to point after '}'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getOptionAsPositiveInteger() {
        String[] s = new String[] {
            "[12345]A",
            "           [12345]A",
            " \t \t       [12345]A"
        };
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser(s[i]);
            int j = tp.getOptionAsPositiveInteger();
            assertTrue("Expect to get 12345 and got: " + j, j == 12345);
            assertTrue("Expect pos to point after ']'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getArgAsDecimal() {
        String[] s = new String[] {
            "{12.345}A",
            "           {12.345}A",
            " \t \t       {12.345}A",
            "       {   12.345   }A",
            "12.345A",
            "           12.345A",
            " \t \t       12.345A"
        };
        String n = "12.345";
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser(s[i]);
            double d = tp.getArgAsDecimal();
            assertTrue("Expect to get 12.345 and got: " + d, Double.toString(d).equals(n));
            assertTrue("Expect pos to point after '}'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getOptionAsDecimal() {
        String[] s = new String[] {
            "[-123.45]A",
            "           [-123.45]A",
            " \t \t       [-123.45]A",
            " \t \t       [ \t  \t  -123.45    \t     ]A"
        };
        String n = "-123.45";
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser(s[i]);
            double d = tp.getOptionAsDecimal();
            assertTrue("Expect to get -123.45 and got: " + d, Double.toString(d).equals(n));
            assertTrue("Expect pos to point after ']'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getCommand() {
        String[][] s = {{"hello", "hello"},
            {"world", "world"},
            {"1234", "1"},
            {"st0p", "st"},
            {"\\foo", "\\"},
            {"?bar", "?"}
        };
        for (int i = 0; i < s.length; ++i) {
            TeXParser tp = new TeXParser("\\" + s[i][0]);
            String c = tp.getCommand();
            assertTrue("Expect to get " + s[i][1] + " and got: " + c, c.equals(s[i][1]));
        }
    }

    @Test
    public void convertIntToHex() {
        String[] nums = {"1234", "46789", "1", "0", "9", "21", "456"};
        for (int i = 0; i < nums.length; ++i) {
            int n = Integer.parseInt(nums[i], 10);
            int h = TeXParser.convertIntToHex(n);
            int jh = Integer.parseInt(nums[i], 16);
            assertTrue("Expect to same numbers: " + h + " and " + jh, h == jh);
        }
    }

    public static Color getAlphaColor(Color c, String a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), Integer.parseInt(a, 16));
    }

    @Test
    public void getColor() {
        Map<String, Color> map = new HashMap<>();
        map.put("#010203}A", Color.decode("#010203"));
        map.put("#AaBb37}A", Color.decode("#AaBb37"));
        map.put("#010203AB}A", getAlphaColor(Color.decode("#010203"), "AB"));
        map.put("#AaBb371c}A", getAlphaColor(Color.decode("#AaBb37"), "1C"));
        map.put("#F9a}A", Color.decode("#FF99AA"));
        map.put("#7eB}A", Color.decode("#77EEBB"));
        map.put("#7eBf}A", getAlphaColor(Color.decode("#77EEBB"), "FF"));
        map.put("#d41C}A", getAlphaColor(Color.decode("#DD4411"), "CC"));
        map.put("7}A", Color.decode("#7"));
        map.put("789}A", Color.decode("#789"));
        map.put("1234AB}A", Color.decode("#1234AB"));
        map.put("AbcD12}A", Color.decode("#ABCD12"));
        map.put("cyan}A", Colors.getFromName("cyan"));
        map.put("brown}A", Colors.getFromName("brown"));
        map.put("0.1234}A", new Color(0.1234f, 0.1234f, 0.1234f));
        map.put("-0.1234}A", Color.BLACK);
        map.put("2.1234}A", Color.WHITE);
        map.put("34,56,78}A", new Color(34, 56, 78));
        map.put("0.34 , 0.56,   0.78  }A", new Color(0.34f, 0.56f, 0.78f));
        map.put("0.34,-0.56,0.78}A", new Color(0.34f, 0.f, 0.78f));
        map.put("1.34;-0.001;0.96}A", new Color(1.f, 0.f, 0.96f));
        map.put("0.34 , 0.56,   0.78, 0.678  }A", Colors.conv(0.34f, 0.56f, 0.78f, 0.678f));
        map.put("0 , 1,   1  }A", new Color(0, 1, 1));
        map.put("0. , 1,   1  }A", new Color(0f, 1f, 1f));
        map.put("rgb(0 , 1,   1  )}A", new Color(0, 1, 1));
        map.put("rgb(0. , 1,   1  )}A", new Color(0f, 1f, 1f));
        map.put("rgba(0 , 1,   1, 0.123  )}A", new Color(0f, 1f / 255f, 1f / 255f, 0.123f));
        map.put("rgba(0. , 1,   1, 0.456  )}A", new Color(0f, 1f, 1f, 0.456f));

        for (Map.Entry<String, Color> e : map.entrySet()) {
            TeXParser tp = new TeXParser(e.getKey());
            Color c = tp.getColor('}');
            assertTrue("Expect to get " + e.getValue() + "(Alpha: " + e.getValue().getAlpha() +  ") and got: " + c + "(Alpha: " + c.getAlpha() + ") for " + e.getKey(), c.equals(e.getValue()));
            assertTrue("Expect pos to point after '}'", tp.getChar() == 'A');
        }
    }

    private static String mapToString(final Map<String, String> m) {
        String s = "{";
        for (Map.Entry<String, String> e : m.entrySet()) {
            s += e.getKey() + "=>" + e.getValue() + ";";
        }
        return s + "}";
    }

    @Test
    public void getOptionAsMap() {
        Map<String, Map<String, String>> map = new HashMap<>();
        map.put("[]A", new HashMap<String, String>());
        map.put("[   ]A", new HashMap<String, String>());
        map.put("[h]A", new HashMap<String, String>() {
            {
                put("h", null);
            }
        });
        map.put("[   h   ]A", new HashMap<String, String>() {
            {
                put("h", null);
            }
        });
        map.put("[hello]A", new HashMap<String, String>() {
            {
                put("hello", null);
            }
        });
        map.put("[hello=world]A", new HashMap<String, String>() {
            {
                put("hello", "world");
            }
        });
        map.put("[hello=world,salut=monde]A", new HashMap<String, String>() {
            {
                put("hello", "world");
                put("salut", "monde");
            }
        });
        map.put("[hello=world  \t;       salut=monde]A", new HashMap<String, String>() {
            {
                put("hello", "world");
                put("salut", "monde");
            }
        });
        map.put("[ bar, hello=world;  , salut=monde, foo,  ]A", new HashMap<String, String>() {
            {
                put("hello", "world");
                put("salut", "monde");
                put("foo", null);
                put("bar", null);
            }
        });
        map.put("[bar, hello=world;  , salut=monde, foo,]A", new HashMap<String, String>() {
            {
                put("hello", "world");
                put("salut", "monde");
                put("foo", null);
                put("bar", null);
            }
        });

        for (Map.Entry<String, Map<String, String>> e : map.entrySet()) {
            TeXParser tp = new TeXParser(e.getKey());
            Map<String, String> m = tp.getOptionAsMap();
            assertTrue("Expect to have " + mapToString(e.getValue()) + " and got " + mapToString(m), m.equals(e.getValue()));
            assertTrue("Expect pos to point after ']'", tp.getChar() == 'A');
        }
    }

    @Test
    public void getCharFromCode() {
        for (int i = 0; i < 65536; ++i) {
            String s = Integer.toOctalString(i);
            TeXParser tp = new TeXParser("0" + s + "\\");
            char c = tp.getCharFromCode();
            assertTrue("Expect " + s + " and got " + Integer.toOctalString((int)c), (int)c == i);
            assertTrue("Expect pos to point on \\", tp.getChar() == '\\');
        }

        for (int i = 0; i < 65536; ++i) {
            String s = Integer.toHexString(i);
            TeXParser tp = new TeXParser("0x" + s + "\\");
            char c = tp.getCharFromCode();
            assertTrue("Expect " + s + " and got " + Integer.toHexString((int)c), (int)c == i);
            assertTrue("Expect pos to point on \\", tp.getChar() == '\\');
        }

        for (int i = 0; i < 65536; ++i) {
            String s = Integer.toString(i);
            TeXParser tp = new TeXParser(s + "\\");
            char c = tp.getCharFromCode();
            assertTrue("Expect " + s + " and got " + Integer.toString((int)c), (int)c == i);
            assertTrue("Expect pos to point on \\", tp.getChar() == '\\');
        }
    }
}
