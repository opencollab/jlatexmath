/* XMLTest.java
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLTest extends DefaultHandler {

    private Locator locator;
    protected final String path;
    private String baseName;
    private Test test;
    private final List<Test> tests;
    private StringBuilder buffer;

    public XMLTest(String path) throws SAXException {
        this.path = path;
        this.tests = new ArrayList<Test>();
        convert();
    }

    private void run() {
        for (final Test t : tests) {
            t.exec();
        }
    }

    /**
     * Start the conversion
     * @throws SAXException if a problem is encountered during the parsing
     * @throws IOException if an IO problem is encountered
     */
    private void convert() throws SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);

        try {
            final SAXParser parser = factory.newSAXParser();
            final InputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
            parser.parse(in, this);
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
            throw e;
        } catch (IOException e) {
            throw new SAXException("IO issue" + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
        if (qName.equals("tests")) {
            testsHandler(attr);
        } else if (qName.equals("test")) {
            testHandler(attr);
        } else if (qName.equals("id")) {
            idHandler(attr);
        } else if (qName.equals("code")) {
            codeHandler(attr);
        } else if (qName.equals("expected")) {
            expectedHandler(attr);
        } else {
            throw new SAXException(err("Invalid tag: " + qName));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("code")) {
            test.setCode(buffer.toString());
            buffer = null;
        } else if (qName.equals("expected")) {
            test.setError(buffer.toString());
            buffer = null;
        } else if (qName.equals("tests")) {
            run();
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (buffer != null) {
            buffer.append(ch, start, length);
        }
    }

    private void idHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        String id = null;
        for (int i = 0; i < len; i++) {
            final String nam = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (nam.equals("name")) {
                id = val;
            } else {
                throw new SAXException(err("Invalid attribute in id: " + nam));
            }
        }

        if (id == null || id.isEmpty()) {
            throw new SAXException(err("Invalid name in id"));
        }

        test.setId(id);
    }

    private void testsHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        String name = null;
        for (int i = 0; i < len; i++) {
            final String nam = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (nam.equals("name")) {
                name = val;
            } else {
                throw new SAXException(err("Invalid attribute in tests: " + nam));
            }
        }

        if (name == null || name.isEmpty()) {
            throw new SAXException(err("Invalid name in tests"));
        }

        baseName = name;
    }

    private void testHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        if (len != 0) {
            throw new SAXException(err("Invalid attribute in test"));
        }

        test = new Test(baseName);
        tests.add(test);
    }

    private void codeHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        if (len != 0) {
            throw new SAXException(err("Invalid attribute in code"));
        }
        buffer = new StringBuilder();
    }

    private void expectedHandler(Attributes attr) throws SAXException {
        final int len = attr.getLength();
        String success = null;
        for (int i = 0; i < len; i++) {
            final String nam = attr.getLocalName(i);
            final String val = attr.getValue(i);
            if (nam.equals("success")) {
                success = val;
            } else {
                throw new SAXException(err("Invalid attribute in expected: " + nam));
            }
        }

        if (success != null && success.equals("true")) {
            test.setError("");
        }
        buffer = new StringBuilder();
    }

    /**
     * {@inheritDoc}
     */
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    protected final String err(String s) {
        return s + " at line " + locator.getLineNumber() + " in " + path;
    }
}
