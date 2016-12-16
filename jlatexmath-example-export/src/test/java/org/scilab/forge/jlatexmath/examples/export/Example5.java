/* Example5.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://jlatexmath.sourceforge.net
 * 
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
package org.scilab.forge.jlatexmath.examples.export;

import java.io.IOException;

/**
 * A class to test LaTeX rendering.
 **/
public class Example5 {
    public static void main(String[] args) {

        String latex = "\\begin{array}{|c|l|||r|c|}";
        latex += "\\hline";
        latex += "\\text{Matrix}&\\multicolumn{2}{|c|}{\\text{Multicolumns}}&\\text{Font sizes commands}\\cr";
        latex += "\\hline";
        latex += "\\begin{pmatrix}\\alpha_{11}&\\cdots&\\alpha_{1n}\\cr\\hdotsfor{3}\\cr\\alpha_{n1}&\\cdots&\\alpha_{nn}\\end{pmatrix}&\\Large \\text{Large Right}&\\small \\text{small Left}&\\tiny \\text{tiny Tiny}\\cr";
        latex += "\\hline";
        latex += "\\multicolumn{4}{|c|}{\\Huge \\text{Huge Multicolumns}}\\cr";
        latex += "\\hline";
        latex += "\\end{array}";

        try {
            Convert.toSVG(latex, "Example5.svg", false);
            Convert.toSVG(latex, "Example5_shaped.svg", true);
            Convert.SVGTo("Example5.svg", "Example5.pdf", Convert.PDF);
            Convert.SVGTo("Example5_shaped.svg", "Example5_shaped.pdf", Convert.PDF);
            Convert.SVGTo("Example5.svg", "Example5.ps", Convert.PS);
            Convert.SVGTo("Example5.svg", "Example5.eps", Convert.EPS);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
