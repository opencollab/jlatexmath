/* Example3.java
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
public class Example3 {
    public static void main(String[] args) {

        String latex = "\\definecolor{gris}{gray}{0.9}";
        latex += "\\definecolor{noir}{rgb}{0,0,0}";
        latex += "\\definecolor{bleu}{rgb}{0,0,1}\\newcommand{\\pa}{\\left|}";
        latex += "\\begin{array}{c}";
        latex += "\\JLaTeXMath\\\\";
        latex += "\\begin{split}";
        latex += " &Тепловой\\ поток\\ \\mathrm{Тепловой\\ поток}\\ \\mathtt{Тепловой\\ поток}\\\\";
        latex += " &\\boldsymbol{\\mathrm{Тепловой\\ поток}}\\ \\mathsf{Тепловой\\ поток}\\\\";
        latex += "|I_2| &= \\pa\\int_0^T\\psi(t)\\left\\{ u(a,t)-\\int_{\\gamma(t)}^a \\frac{d\\theta}{k} (\\theta,t) \\int_a^\\theta c(\\xi) u_t (\\xi,t)\\,d\\xi\\right\\}dt\\right|\\\\";
        latex += "&\\le C_6 \\Bigg|\\pa f \\int_\\Omega \\pa\\widetilde{S}^{-1,0}_{a,-} W_2(\\Omega, \\Gamma_1)\\right|\\ \\right|\\left| |u|\\overset{\\circ}{\\to} W_2^{\\widetilde{A}}(\\Omega;\\Gamma_r,T)\\right|\\Bigg|\\\\";
        latex += "&\\\\";
        latex += "&\\textcolor{magenta}{\\mathrm{Produit\\ avec\\ Java\\ et\\ \\LaTeX\\ par\\ }\\mathscr{C}\\mathcal{A}\\mathfrak{L}\\mathbf{I}\\mathtt{X}\\mathbb{T}\\mathsf{E}}\\\\";
        latex += "&\\begin{pmatrix}\\alpha&\\beta&\\gamma&\\delta\\\\\\aleph&\\beth&\\gimel&\\daleth\\\\\\mathfrak{A}&\\mathfrak{B}&\\mathfrak{C}&\\mathfrak{D}\\\\\\boldsymbol{\\mathfrak{a}}&\\boldsymbol{\\mathfrak{b}}&\\boldsymbol{\\mathfrak{c}}&\\boldsymbol{\\mathfrak{d}}\\end{pmatrix}\\quad{(a+b)}^{\\frac{n}{2}}=\\sqrt{\\sum_{k=0}^n\\tbinom{n}{k}a^kb^{n-k}}\\quad \\Biggl(\\biggl(\\Bigl(\\bigl(()\\bigr)\\Bigr)\\biggr)\\Biggr)\\\\";
        latex += "&\\forall\\varepsilon\\in\\mathbb{R}_+^*\\ \\exists\\eta>0\\ |x-x_0|\\leq\\eta\\Longrightarrow|f(x)-f(x_0)|\\leq\\varepsilon\\\\";
        latex += "&\\det\\begin{bmatrix}a_{11}&a_{12}&\\cdots&a_{1n}\\\\a_{21}&\\ddots&&\\vdots\\\\\\vdots&&\\ddots&\\vdots\\\\a_{n1}&\\cdots&\\cdots&a_{nn}\\end{bmatrix}\\overset{\\mathrm{def}}{=}\\sum_{\\sigma\\in\\mathfrak{S}_n}\\varepsilon(\\sigma)\\prod_{k=1}^n a_{k\\sigma(k)}\\\\";
        latex += "&\\Delta f(x,y)=\\frac{\\partial^2f}{\\partial x^2}+\\frac{\\partial^2f}{\\partial y^2}\\qquad\\qquad \\fcolorbox{noir}{gris}{n!\\underset{n\\rightarrow+\\infty}{\\sim} {\\left(\\frac{n}{e}\\right)}^n\\sqrt{2\\pi n}}\\\\";
        latex += "&\\sideset{_\\alpha^\\beta}{_\\gamma^\\delta}{\\begin{pmatrix}a&b\\\\c&d\\end{pmatrix}}\\xrightarrow[T]{n\\pm i-j}\\sideset{^t}{}A\\xleftarrow{\\overrightarrow{u}\\wedge\\overrightarrow{v}}\\underleftrightarrow{\\iint_{\\mathds{R}^2}e^{-\\left(x^2+y^2\\right)}\\,\\mathrm{d}x\\mathrm{d}y}";
        latex += "\\end{split}\\\\";
        latex += "\\rotatebox{30}{\\sum_{n=1}^{+\\infty}}\\quad\\mbox{Mirror rorriM}\\reflectbox{\\mbox{Mirror rorriM}}";
        latex += "\\end{array}";

        try {
            Convert.toSVG(latex, "Example3.svg", false);
            Convert.toSVG(latex, "Example3_shaped.svg", true);
            Convert.SVGTo("Example3.svg", "Example3.pdf", Convert.PDF);
            Convert.SVGTo("Example3_shaped.svg", "Example3_shaped.pdf", Convert.PDF);
            Convert.SVGTo("Example3.svg", "Example3.ps", Convert.PS);
            Convert.SVGTo("Example3.svg", "Example3.eps", Convert.EPS);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
