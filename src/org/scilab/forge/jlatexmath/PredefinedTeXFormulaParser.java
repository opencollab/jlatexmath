/* PredefinedTeXFormulaParser.java
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

import java.util.Map;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Parses and creates predefined TeXFormula objects form an XML-file.
 */
public class PredefinedTeXFormulaParser {
    
    private static final String RESOURCE_DIR = "";
    
    public static final String RESOURCE_NAME = "PredefinedTeXFormulas.xml";
    
    private Element root;
    private String type;
        
    public PredefinedTeXFormulaParser(InputStream file, String type) throws ResourceParseException {
        try {
	    this.type = type;
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setIgnoringElementContentWhitespace(true);
	    factory.setIgnoringComments(true);
	    root = factory.newDocumentBuilder().parse(file).getDocumentElement();
	} catch (Exception e) { // JDOMException or IOException
            throw new XMLResourceParseException("", e);
        }
    }
    
    public PredefinedTeXFormulaParser(String PredefFile, String type) throws ResourceParseException {
        this(PredefinedTeXFormulaParser.class.getResourceAsStream(PredefFile), type);
    }

    public void parse(Map predefinedTeXFormulas) {
        // get required string attribute
        String enabledAll = getAttrValueAndCheckIfNotNull("enabled", root);
        if ("true".equals(enabledAll)) { // parse formula's
            // iterate all "Font"-elements
	    NodeList list = root.getElementsByTagName(this.type);
            for (int i = 0; i < list.getLength(); i++) {
                Element formula = (Element)list.item(i);
                // get required string attribute
                String enabled = getAttrValueAndCheckIfNotNull("enabled", formula);
                if ("true".equals (enabled)) { // parse this formula
                    // get required string attribute
                    String name = getAttrValueAndCheckIfNotNull("name", formula);
                    
                    // parse and build the formula and add it to the table
                    if ("TeXFormula".equals(this.type))
			predefinedTeXFormulas.put(name, (TeXFormula) new TeXFormulaParser(name, formula, this.type).parse());
		    else 
			predefinedTeXFormulas.put(name, (MacroInfo) new TeXFormulaParser(name, formula, this.type).parse());
                }
            }
        }
    }

    private static String getAttrValueAndCheckIfNotNull(String attrName,
            Element element) throws ResourceParseException {
        String attrValue = element.getAttribute(attrName);
        if (attrValue.equals(""))
            throw new XMLResourceParseException(RESOURCE_NAME, element.getTagName(),
                    attrName, null);
        return attrValue;
    }
}
