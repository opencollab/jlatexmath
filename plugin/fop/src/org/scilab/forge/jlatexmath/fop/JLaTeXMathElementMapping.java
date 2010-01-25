/* JLaTeXMathElementMapping.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2010 DENIZET Calixte
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

/* This file is largely inspired by files wrote by Jeremias Maerki,
 * for the fop plugin of barcode4j available at 
 * http://barcode4j.sourceforge.net/
 */

package org.scilab.forge.jlatexmath.fop;

import java.util.HashMap;

import org.apache.fop.fo.ElementMapping;
import org.apache.fop.fo.FONode;
import org.w3c.dom.DOMImplementation;

/**
 * This class provides the element mapping for FOP.
 */
public class JLaTeXMathElementMapping extends ElementMapping {

    public JLaTeXMathElementMapping() {
        this.namespaceURI = JLaTeXMathObj.JLATEXMATH_NS;
    }
    
    public DOMImplementation getDOMImplementation() {
        return ElementMapping.getDefaultDOMImplementation();
    }

    protected void initialize() {
        if (this.foObjs == null) {
            this.foObjs = new HashMap();
            this.foObjs.put("latex", new JLMEMaker());
            this.foObjs.put(ElementMapping.DEFAULT, new JLMMaker());
        }
    }
    
    public final class JLMMaker extends ElementMapping.Maker {
        private JLMMaker() { }
        public FONode make(final FONode parent) {
            return new JLaTeXMathObj(parent);
        }
    }

    public final class JLMEMaker extends ElementMapping.Maker {
        private JLMEMaker() { }
        public FONode make(final FONode parent) {
            return new JLaTeXMathElement(parent);
        }
    }
}
