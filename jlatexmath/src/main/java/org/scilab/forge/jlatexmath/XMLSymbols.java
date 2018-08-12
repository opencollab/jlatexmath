/* XMLSymbols.java
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
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class XMLSymbols extends XMLReader {

    private Map<String, CharFont> map;

    private XMLSymbols(FontIDs ids, Map<String, CharFont> map, String name) {
        super(ids, name);
        this.map = map;
    }

    public static void get(FontIDs ids, Map<String, CharFont> map, InputStream in, String name) throws SAXException {
        new XMLSymbols(ids, map, name).convert(in);
    }

    private void symbolMappingHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        String name = null;
        char code = '\0';
        String id = null;

        for (int i = 0; i < len; i++) {
            final String nam = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (nam.equals("name")) {
                name = val;
            } else if (nam.equals("ch")) {
                code = (char)Integer.parseInt(val);
            } else if (nam.equals("fontId")) {
                id = val;
            } else {
                throw new SAXException(err("Invalid attribute in SymbolMapping: " + nam));
            }
        }

        if (code == '\0') {
            throw new SAXException(err("Invalid char code in SymbolMapping"));
        }
        if (name == null) {
            throw new SAXException(err("Invalid name in SymbolMapping"));
        }
        if (id == null) {
            throw new SAXException(err("Invalid fontId in SymbolMapping"));
        }

        map.put(name, new CharFont(code, ids.get(id)));
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
        if (qName.equals("SymbolMapping")) {
            symbolMappingHandler(attr);
        } else if (!qName.equals("SymbolMappings")) {
            throw new SAXException(err("Invalid tag: " + qName));
        }
    }
}
