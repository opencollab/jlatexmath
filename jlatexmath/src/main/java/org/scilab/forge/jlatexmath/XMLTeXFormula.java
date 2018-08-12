/* XMLTeXFormula.java
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


public class XMLTeXFormula extends XMLReader {

    private final String[] formulas;
    private final String[] symbols;
    private final String[] symbols_text;

    private XMLTeXFormula(String[] formulas, String[] symbols, String[] symbols_text, String name) {
        super(null, name);
        this.formulas = formulas;
        this.symbols = symbols;
        this.symbols_text = symbols_text;
    }

    public static void get(String[] formulas, String[] symbols, String[] symbols_text, InputStream in, String name) throws SAXException {
        new XMLTeXFormula(formulas, symbols, symbols_text, name).convert(in);
    }

    private void mapHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        char code = '\0';
        String sym = null;
        String text = null;
        String formula = null;

        for (int i = 0; i < len; i++) {
            final String nam = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (nam.equals("char")) {
                if (val.isEmpty()) {
                    throw new SAXException(err("Invalid value for attribute char in Map: " + nam));
                }
                code = val.charAt(0);
            } else if (nam.equals("symbol")) {
                sym = val;
            } else if (nam.equals("text")) {
                text = val;
            } else if (nam.equals("formula")) {
                formula = val;
            } else {
                throw new SAXException(err("Invalid attribute in Map: " + nam));
            }
        }

        if (code == '\0') {
            throw new SAXException(err("Invalid char code in Map"));
        }

        if (sym == null) {
            if (formula == null) {
                throw new SAXException(err("No symbol or no formula in Map"));
            }
            formulas[code] = formula;
            if (text != null) {
                symbols_text[code] = text;
            }
        } else {
            symbols[code] = sym;
            if (text != null) {
                symbols_text[code] = text;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
        if (qName.equals("Map")) {
            mapHandler(attr);
        } else if (!qName.equals("FormulaSettings") && !qName.equals("CharacterToSymbolMappings") && !qName.equals("CharacterToFormulaMappings")) {
            throw new SAXException(err("Invalid tag: " + qName));
        }
    }
}
