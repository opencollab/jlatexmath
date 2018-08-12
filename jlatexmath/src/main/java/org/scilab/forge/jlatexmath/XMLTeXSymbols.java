/* XMLTeXSymbols.java
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
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class XMLTeXSymbols extends XMLReader {

    private static final Map<String, Integer> types = new HashMap<String, Integer>() {
        {
            put("ord", TeXConstants.TYPE_ORDINARY);
            put("op", TeXConstants.TYPE_BIG_OPERATOR);
            put("bin", TeXConstants.TYPE_BINARY_OPERATOR);
            put("rel", TeXConstants.TYPE_RELATION);
            put("open", TeXConstants.TYPE_OPENING);
            put("close", TeXConstants.TYPE_CLOSING);
            put("punct", TeXConstants.TYPE_PUNCTUATION);
            put("acc", TeXConstants.TYPE_ACCENT);
        }
    };

    private Map<String, SymbolAtom> map;
    private Map<String, CharFont> symbols;

    private XMLTeXSymbols(Map<String, SymbolAtom> map, Map<String, CharFont> symbols, String name) {
        super(null, name);
        this.map = map;
        this.symbols = symbols;
    }

    public static void get(Map<String, SymbolAtom> map, Map<String, CharFont> symbols, InputStream in, String name) throws SAXException {
        new XMLTeXSymbols(map, symbols, name).convert(in);
    }

    private void symbolHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        String name = null;
        int type = TeXConstants.TYPE_ORDINARY;
        boolean del = false;

        for (int i = 0; i < len; i++) {
            final String nam = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (nam.equals("name")) {
                name = val;
            } else if (nam.equals("type")) {
                Integer ty = types.get(val);
                if (ty == null) {
                    throw new SAXException(err("Invalid type in Symbol: " + val));
                }
                type = ty.intValue();
            } else if (nam.equals("del")) {
                if (val.toLowerCase().equals("true")) {
                    del = true;
                } else if (!val.toLowerCase().equals("false")) {
                    throw new SAXException(err("Invalid value for del: " + val));
                }
            } else {
                throw new SAXException(err("Invalid attribute in Symbol: " + nam));
            }
        }

        if (name == null) {
            throw new SAXException(err("Invalid name in Symbol"));
        }

        map.put(name, new SymbolAtom(symbols.get(name), type));
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
        if (qName.equals("Symbol")) {
            symbolHandler(attr);
        } else if (!qName.equals("TeXSymbols")) {
            throw new SAXException(err("Invalid tag: " + qName));
        }
    }
}
