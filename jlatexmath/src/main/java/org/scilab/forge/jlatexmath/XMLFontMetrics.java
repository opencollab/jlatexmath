/* XMLFontMetrics.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
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

package org.scilab.forge.jlatexmath;

import java.io.InputStream;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class XMLFontMetrics extends XMLReader {

    private FontInfo info;
    private char code;
    private final Object base;

    private XMLFontMetrics(FontIDs ids, Object base, String name) {
        super(ids, name);
        this.base = base;
    }

    public static FontInfo get(FontIDs ids, Object base, InputStream in, String name) throws SAXException {
        XMLFontMetrics xfm = new XMLFontMetrics(ids, base, name);
        xfm.convert(in);
        return xfm.info;
    }

    public void fontHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        String fontName = null;
        String fontId = null;
        double space = 0.;
        double xHeight = 0.;
        double quad = 0.;
        char skewChar = '\0';
        int unicode = 0;
        String boldVersion = null;
        String romanVersion = null;
        String ssVersion = null;
        String ttVersion = null;
        String itVersion = null;

        for (int i = 0; i < len; i++) {
            final String name = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (name.equals("name")) {
                fontName = val;
            } else if (name.equals("id")) {
                fontId = val;
            } else if (name.equals("space")) {
                space = Double.parseDouble(val);
            } else if (name.equals("xHeight")) {
                xHeight = Double.parseDouble(val);
            } else if (name.equals("quad")) {
                quad = Double.parseDouble(val);
            } else if (name.equals("skewChar")) {
                skewChar = (char)Integer.parseInt(val);
            } else if (name.equals("unicode")) {
                unicode = Integer.parseInt(val);
            } else if (name.equals("boldVersion")) {
                boldVersion = val;
            } else if (name.equals("romanVersion")) {
                romanVersion = val;
            } else if (name.equals("ssVersion")) {
                ssVersion = val;
            } else if (name.equals("ttVersion")) {
                ttVersion = val;
            } else if (name.equals("itVersion")) {
                itVersion = val;
            } else {
                throw new SAXException(err("Invalid attribute in Char: " + name));
            }
        }

        final String path = name.substring(0, name.lastIndexOf("/") + 1) + fontName;
        if (unicode == 0) {
            info = new FontInfo(ids.get(fontId), -1, base, path, fontName, xHeight, space, quad, skewChar, ids.get(boldVersion), ids.get(romanVersion), ids.get(ssVersion), ids.get(ttVersion), ids.get(itVersion));
        } else {
            info = new UniFontInfo(ids.get(fontId), unicode, base, path, fontName, xHeight, space, quad, skewChar, ids.get(boldVersion), ids.get(romanVersion), ids.get(ssVersion), ids.get(ttVersion), ids.get(itVersion));
        }
    }

    public void charHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        code = '\0';
        double[] metrics = new double[] {0., 0., 0., 0.};

        for (int i = 0; i < len; i++) {
            final String name = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (name.equals("code")) {
                code = (char)Integer.parseInt(val);
            } else {
                final double x = Double.parseDouble(val);
                if (name.equals("width")) {
                    metrics[TeXFont.WIDTH] = x;
                } else if (name.equals("height")) {
                    metrics[TeXFont.HEIGHT] = x;
                } else if (name.equals("depth")) {
                    metrics[TeXFont.DEPTH] = x;
                } else if (name.equals("italic")) {
                    metrics[TeXFont.IT] = x;
                } else {
                    throw new SAXException(err("Invalid attribute in Char: " + name));
                }
            }
        }

        if (code == '\0') {
            throw new SAXException(err("Invalid Character value: \\0"));
        }
        info.setMetrics(code, metrics);
    }

    public void kernHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        char c = '\0';
        double x = 0.;

        for (int i = 0; i < len; i++) {
            final String name = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (name.equals("code")) {
                c = (char)Integer.parseInt(val);
            } else if (name.equals("val")) {
                x = Double.parseDouble(val);
            } else {
                throw new SAXException(err("Invalid attribute in Kern: " + name));
            }
        }

        if (c == '\0') {
            throw new SAXException(err("Invalid Character value: \\0"));
        }
        info.addKern(code, c, x);
    }

    public void ligHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        char c = '\0';
        char lig = '\0';

        for (int i = 0; i < len; i++) {
            final String name = attr.getLocalName(i);
            final String val = attr.getValue(i);
            final char ch = (char)Integer.parseInt(val);
            if (name.equals("code")) {
                c = ch;
            } else if (name.equals("ligCode")) {
                lig = ch;
            } else {
                throw new SAXException(err("Invalid attribute in Kern: " + name));
            }
        }

        if (c == '\0') {
            throw new SAXException(err("Invalid Character value: \\0"));
        }
        info.addLigature(code, c, lig);
    }

    public void nextLargerHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        char c = '\0';
        String id = null;

        for (int i = 0; i < len; i++) {
            final String name = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (name.equals("code")) {
                c = (char)Integer.parseInt(val);
            } else if (name.equals("fontId")) {
                id = val;
            } else {
                throw new SAXException(err("Invalid attribute in NextLarger: " + name));
            }
        }

        if (c == '\0') {
            throw new SAXException(err("Invalid Character value: \\0"));
        }

        if (id == null) {
            throw new SAXException(err("Invalid fontId in NextLarger"));
        }

        info.setNextLarger(code, c, ids.get(id));
    }

    public void extensionHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        char[] ext = new char[] {TeXFont.NONE, TeXFont.NONE,
                                 TeXFont.NONE, TeXFont.NONE
                                };

        for (int i = 0; i < len; i++) {
            final String name = attr.getLocalName(i);
            final String val = attr.getValue(i);
            final char x = (char)Integer.parseInt(val);
            if (name.equals("rep")) {
                ext[TeXFont.REP] = x;
            } else if (name.equals("top")) {
                ext[TeXFont.TOP] = x;
            } else if (name.equals("mid")) {
                ext[TeXFont.MID] = x;
            } else if (name.equals("bot")) {
                ext[TeXFont.BOT] = x;
            } else {
                throw new SAXException(err("Invalid attribute in Extension: " + name));
            }
        }

        info.setExtension(code, ext);
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
        if (qName.equals("Char")) {
            charHandler(attr);
        } else if (qName.equals("Kern")) {
            kernHandler(attr);
        } else if (qName.equals("Lig")) {
            ligHandler(attr);
        } else if (qName.equals("NextLarger")) {
            nextLargerHandler(attr);
        } else if (qName.equals("Extension")) {
            extensionHandler(attr);
        } else if (qName.equals("Font")) {
            fontHandler(attr);
        } else {
            throw new SAXException(err("Invalid tag: " + qName));
        }
    }
}
