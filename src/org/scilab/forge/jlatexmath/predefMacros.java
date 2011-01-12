/* predefMacros.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
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
 */

package org.scilab.forge.jlatexmath;

import java.awt.Color;
import java.util.StringTokenizer;

import org.scilab.forge.jlatexmath.dynamic.DynamicAtom;

/**
 * This class contains the most of basic commands of LaTeX, they're activated in
 * the file PredefinedCommands.xml.
 **/
public class predefMacros {
    
    static {
	NewEnvironmentMacro.addNewEnvironment("array", "\\array@@env{#1}{", "}", 1);
	NewEnvironmentMacro.addNewEnvironment("tabular", "\\array@@env{#1}{", "}", 1);
	NewEnvironmentMacro.addNewEnvironment("matrix", "\\matrix@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("smallmatrix", "\\smallmatrix@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("pmatrix", "\\left(\\begin{matrix}", "\\end{matrix}\\right)", 0);
	NewEnvironmentMacro.addNewEnvironment("bmatrix", "\\left[\\begin{matrix}", "\\end{matrix}\\right]", 0);
	NewEnvironmentMacro.addNewEnvironment("Bmatrix", "\\left\\{\\begin{matrix}", "\\end{matrix}\\right\\}", 0);
	NewEnvironmentMacro.addNewEnvironment("vmatrix", "\\left|\\begin{matrix}", "\\end{matrix}\\right|", 0);
	NewEnvironmentMacro.addNewEnvironment("Vmatrix", "\\left\\|\\begin{matrix}", "\\end{matrix}\\right\\|", 0);
	NewEnvironmentMacro.addNewEnvironment("eqnarray", "\\begin{array}{rcl}", "\\end{array}", 0);
	NewEnvironmentMacro.addNewEnvironment("align", "\\align@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("flalign", "\\flalign@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("alignat", "\\alignat@@env{#1}{", "}", 1);
	NewEnvironmentMacro.addNewEnvironment("aligned", "\\aligned@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("alignedat", "\\alignedat@@env{#1}{", "}", 1);
	NewEnvironmentMacro.addNewEnvironment("multline", "\\multline@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("cases", "\\left\\{\\begin{array}{l@{\\!}l}", "\\end{array}\\right.", 0);
	NewEnvironmentMacro.addNewEnvironment("split", "\\begin{array}{rl}", "\\end{array}", 0);
	NewEnvironmentMacro.addNewEnvironment("gather", "\\gather@@env{", "}", 0);
	NewEnvironmentMacro.addNewEnvironment("gathered", "\\gathered@@env{", "}", 0);
	NewCommandMacro.addNewCommand("operatorname", "\\mathop{\\mathrm{#1}}\\nolimits ", 1);
	NewCommandMacro.addNewCommand("DeclareMathOperator", "\\newcommand{#1}{\\mathop{\\mathrm{#2}}\\nolimits}", 2);
	NewCommandMacro.addNewCommand("substack", "{\\scriptstyle\\begin{array}{c}#1\\end{array}}", 1);
	NewCommandMacro.addNewCommand("dfrac", "\\genfrac{}{}{}{}{#1}{#2}", 2);
	NewCommandMacro.addNewCommand("tfrac", "\\genfrac{}{}{}{1}{#1}{#2}", 2);
	NewCommandMacro.addNewCommand("dbinom", "\\genfrac{(}{)}{0pt}{}{#1}{#2}", 2);
	NewCommandMacro.addNewCommand("tbinom", "\\genfrac{(}{)}{0pt}{1}{#1}{#2}", 2);
	NewCommandMacro.addNewCommand("pmod", "\\qquad\\mathbin{(\\mathrm{mod}\\ #1)}", 1);
	NewCommandMacro.addNewCommand("mod", "\\qquad\\mathbin{\\mathrm{mod}\\ #1}", 1);
	NewCommandMacro.addNewCommand("pod", "\\qquad\\mathbin{(#1)}", 1);
	NewCommandMacro.addNewCommand("dddot", "\\mathop{#1}\\limits^{...}", 1);
	NewCommandMacro.addNewCommand("ddddot", "\\mathop{#1}\\limits^{....}", 1);
	NewCommandMacro.addNewCommand("spdddot", "^{\\mathrm{...}}", 0);
	NewCommandMacro.addNewCommand("spbreve", "^{\\makeatletter\\sp@breve\\makeatother}", 0);
	NewCommandMacro.addNewCommand("sphat", "^{\\makeatletter\\sp@hat\\makeatother}", 0);
	NewCommandMacro.addNewCommand("spddot", "^{\\displaystyle..}", 0);
	NewCommandMacro.addNewCommand("spcheck", "^{\\vee}", 0);
	NewCommandMacro.addNewCommand("sptilde", "^{\\sim}", 0);
	NewCommandMacro.addNewCommand("spdot", "^{\\displaystyle.}", 0);
	NewCommandMacro.addNewCommand("d", "\\underaccent{\\dot}{#1}", 1);
	NewCommandMacro.addNewCommand("b", "\\underaccent{\\bar}{#1}", 1);
	NewCommandMacro.addNewCommand("Bra", "\\left\\langle{#1}\\right\\vert", 1);
	NewCommandMacro.addNewCommand("Ket", "\\left\\vert{#1}\\right\\rangle", 1);
	NewCommandMacro.addNewCommand("textsuperscript", "{}^{\\text{#1}}", 1);
	NewCommandMacro.addNewCommand("textsubscript", "{}_{\\text{#1}}", 1);
	NewCommandMacro.addNewCommand("textit", "\\mathit{\\text{#1}}", 1);
	NewCommandMacro.addNewCommand("textbf", "\\mathbf{\\text{#1}}", 1);
	NewCommandMacro.addNewCommand("textsf", "\\mathsf{\\text{#1}}", 1);
	NewCommandMacro.addNewCommand("texttt", "\\mathtt{\\text{#1}}", 1);
	NewCommandMacro.addNewCommand("textrm", "\\text{#1}", 1);
	NewCommandMacro.addNewCommand("degree", "^\\circ", 0);
	NewCommandMacro.addNewCommand("with", "\\mathbin{\\&}", 0);
	NewCommandMacro.addNewCommand("parr", "\\mathbin{\\rotatebox[origin=c]{180}{\\&}}", 0);
    }

    public Atom Braket_macro(TeXParser tp, String[] args) throws ParseException {
	String str = args[1].replaceAll("\\|", "\\\\middle\\\\vert ");
	return new TeXFormula(tp.getIsPartial(), "\\left\\langle " + str + "\\right\\rangle").root;
    }

    public Atom Set_macro(TeXParser tp, String[] args) throws ParseException {
	String str = args[1].replaceFirst("\\|", "\\\\middle\\\\vert ");
	return new TeXFormula(tp.getIsPartial(), "\\left\\{" + str + "\\right\\}").root;
    }

    public Atom spATbreve_macro(TeXParser tp, String[] args) throws ParseException {
	VRowAtom vra = new VRowAtom(new TeXFormula("\\displaystyle\\!\\breve{}").root);
	vra.setRaise(TeXConstants.UNIT_EX, 0.6f);

	return new SmashedAtom(vra, null);
    }

    public Atom spAThat_macro(TeXParser tp, String[] args) throws ParseException {
	VRowAtom vra = new VRowAtom(new TeXFormula("\\displaystyle\\widehat{}").root);
	vra.setRaise(TeXConstants.UNIT_EX, 0.6f);
	
	return new SmashedAtom(vra, null);
    }

    public Atom hvspace_macro(TeXParser tp, String[] args) throws ParseException {
	int i;
	for (i = 0; i < args[1].length() && !Character.isLetter(args[1].charAt(i)); i++);
	float f = 0;
	try {
	    f = Float.parseFloat(args[1].substring(0, i));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString());
	}
 
	int unit;
	if (i != args[1].length()) {
	    unit = SpaceAtom.getUnit(args[1].substring(i).toLowerCase());
	} else {
	    unit = TeXConstants.UNIT_POINT;
	}
	
	if (unit == -1) {
	    throw new ParseException("Unknown unit \"" + args[1].substring(i) + "\" !");
	}

	return args[0].charAt(0) == 'h' ? new SpaceAtom(unit, f, 0, 0) : new SpaceAtom(unit, 0, f, 0);
    }
    
    public Atom clrlap_macro(TeXParser tp, String[] args) throws ParseException {
	return new LapedAtom(new TeXFormula(tp.getIsPartial(), args[1]).root, args[0].charAt(0));
    }

    public Atom mathclrlap_macro(TeXParser tp, String[] args) throws ParseException {
	return new LapedAtom(new TeXFormula(tp.getIsPartial(), args[1]).root, args[0].charAt(4));
    }

    public Atom includegraphics_macro(TeXParser tp, String[] args) throws ParseException {
	return new GraphicsAtom(args[1], args[2]);
    }
     
    public Atom rule_macro(TeXParser tp, String[] args) throws ParseException {
	float[] winfo = SpaceAtom.getLength(args[1]);
	if (winfo.length == 1) {
	    throw new ParseException("Error in getting width in \\rule command !");
	}
	float[] hinfo = SpaceAtom.getLength(args[2]);
	if (hinfo.length == 1) {
	    throw new ParseException("Error in getting height in \\rule command !");
	}
	
	float[] rinfo = SpaceAtom.getLength(args[3]);
	if (rinfo.length == 1) {
	    throw new ParseException("Error in getting raise in \\rule command !");
	}
	
	return new RuleAtom((int) winfo[0], winfo[1], (int) hinfo[0], hinfo[1], (int) rinfo[0], -rinfo[1]);	
    }

    /* Thanks to Juan Enrique Escobar Robles for this macro */
    public Atom cfrac_macro(TeXParser tp, String[] args) throws ParseException {
    	int alig = TeXConstants.ALIGN_CENTER;
	if ("r".equals(args[3])) {
	    alig = TeXConstants.ALIGN_RIGHT;
	} else if ("l".equals(args[3])) {
	    alig = TeXConstants.ALIGN_LEFT;
	}    		
	TeXFormula num = new TeXFormula(tp.getIsPartial(), args[1], false);
	TeXFormula denom = new TeXFormula(tp.getIsPartial(), args[2], false);
	if (num.root == null || denom.root == null) {
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	}
	Atom f = new FractionAtom(num.root, denom.root, true, alig, TeXConstants.ALIGN_CENTER);
	RowAtom rat = new RowAtom();
	rat.add(new StyleAtom(TeXConstants.STYLE_DISPLAY, f));
	return rat;
    }

    public Atom frac_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula num = new TeXFormula(tp.getIsPartial(), args[1], false);
	TeXFormula denom = new TeXFormula(tp.getIsPartial(), args[2], false);
	if (num.root == null || denom.root == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	return new FractionAtom(num.root, denom.root, true);
    }

    public Atom sfrac_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula num = new TeXFormula(tp.getIsPartial(), args[1], false);
	TeXFormula denom = new TeXFormula(tp.getIsPartial(), args[2], false);
	if (num.root == null || denom.root == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	
	double scaleX = 0.75;
	double scaleY = 0.75;
	float raise1 = 0.45f;
	float shiftL = -0.3f;
	float shiftR = -0.15f;
	Atom slash = SymbolAtom.get("slash");

	if (!tp.isMathMode()) {
	    scaleX = 0.6;
	    scaleY = 0.5;
	    raise1 = 0.75f;
	    shiftL = -0.55f;
	    shiftR = -0.55f;
	    slash = new VRowAtom(new ScaleAtom(SymbolAtom.get("textfractionsolidus"), 1.25, 0.65));
	    ((VRowAtom) slash).setRaise(TeXConstants.UNIT_EX, 0.4f);
	}

	VRowAtom snum = new VRowAtom(new ScaleAtom(num.root, scaleX, scaleY));
	snum.setRaise(TeXConstants.UNIT_EX, raise1);
	RowAtom at = new RowAtom(snum);
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, shiftL, 0f, 0f));
	at.add(slash);
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, shiftR, 0f, 0f));
	at.add(new ScaleAtom(denom.root, scaleX, scaleY));

	return at;
    }

    public Atom genfrac_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula left = new TeXFormula(tp.getIsPartial(), args[1], false);
	SymbolAtom L = null, R = null;
	if (left != null && left.root instanceof SymbolAtom) {
	    L = (SymbolAtom) left.root;
	}
	    
	TeXFormula right = new TeXFormula(tp.getIsPartial(), args[2], false);
	if (right != null && right.root instanceof SymbolAtom) {
	    R = (SymbolAtom) right.root;
	}
	
	boolean rule = true;
	float[] ths = SpaceAtom.getLength(args[3]);
	if (args[3] == null || args[3].length() == 0 || ths.length == 1) {
	    ths = new float[]{0.0f, 0.0f};
	    rule = false;
	}
	
	int style = 0;
	if (args[4].length() != 0) {
	    style = Integer.parseInt(args[4]);
	}
	TeXFormula num = new TeXFormula(tp.getIsPartial(), args[5], false);
	TeXFormula denom = new TeXFormula(tp.getIsPartial(), args[6], false);
	if (num.root == null || denom.root == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	Atom at = new FractionAtom(num.root, denom.root, rule, (int) ths[0], ths[1]);
	RowAtom rat = new RowAtom();
	rat.add(new StyleAtom(style * 2, new FencedAtom(at, L, R)));
	
	return rat;
    }

    public Atom over_macro(TeXParser tp, String[] args) throws ParseException {
	Atom num = tp.getFormulaAtom();
	Atom denom = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	if (num == null || denom == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	return new FractionAtom(num, denom, true);
    }

    public Atom overwithdelims_macro(TeXParser tp, String[] args) throws ParseException {
	Atom num = tp.getFormulaAtom();
	Atom denom = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	
	if (num == null || denom == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	
	Atom left = new TeXFormula(tp.getIsPartial(), args[1], false).root;
	if (left instanceof BigDelimiterAtom) 
	    left = ((BigDelimiterAtom)left).delim;
	Atom right = new TeXFormula(tp.getIsPartial(), args[2], false).root;
	if (right instanceof BigDelimiterAtom) 
	    right = ((BigDelimiterAtom)right).delim;
	if (left instanceof SymbolAtom && right instanceof SymbolAtom) {
	    return new FencedAtom(new FractionAtom(num, denom, true), (SymbolAtom) left, (SymbolAtom) right);
	}

	RowAtom ra = new RowAtom();
	ra.add(left);
	ra.add(new FractionAtom(num, denom, true));
	ra.add(right);
	return ra;
    }

    public Atom atop_macro(TeXParser tp, String[] args) throws ParseException {
	Atom num = tp.getFormulaAtom();
	Atom denom = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	if (num == null || denom == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	return new FractionAtom(num, denom, false);
    }

    public Atom atopwithdelims_macro(TeXParser tp, String[] args) throws ParseException {
	Atom num = tp.getFormulaAtom();
	Atom denom = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	
	if (num == null || denom == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	
	Atom left = new TeXFormula(tp.getIsPartial(), args[1], false).root;
	if (left instanceof BigDelimiterAtom) 
	    left = ((BigDelimiterAtom)left).delim;
	Atom right = new TeXFormula(tp.getIsPartial(), args[2], false).root;
	if (right instanceof BigDelimiterAtom) 
	    right = ((BigDelimiterAtom)right).delim;
	if (left instanceof SymbolAtom && right instanceof SymbolAtom) {
	    return new FencedAtom(new FractionAtom(num, denom, false), (SymbolAtom) left, (SymbolAtom) right);
	}

	RowAtom ra = new RowAtom();
	ra.add(left);
	ra.add(new FractionAtom(num, denom, false));
	ra.add(right);
	return ra;
    }

    public Atom choose_macro(TeXParser tp, String[] args) throws ParseException {
	Atom num = tp.getFormulaAtom();
	Atom denom = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	if (num == null || denom == null)
	    throw new ParseException("Both numerator and denominator of choose can't be empty!");
	return new FencedAtom(new FractionAtom(num, denom, false), new SymbolAtom("lbrack", TeXConstants.TYPE_OPENING, true), new SymbolAtom("rbrack", TeXConstants.TYPE_CLOSING, true));
    }

    public Atom binom_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula num = new TeXFormula(tp.getIsPartial(), args[1], false);
	TeXFormula denom = new TeXFormula(tp.getIsPartial(), args[2], false);
	if (num.root == null || denom.root == null)
	    throw new ParseException("Both binomial coefficients must be not empty !!");
	return new FencedAtom(new FractionAtom(num.root, denom.root, false), new SymbolAtom("lbrack", TeXConstants.TYPE_OPENING, true), new SymbolAtom("rbrack", TeXConstants.TYPE_CLOSING, true));
    }
    
    public Atom textstyle_macros(TeXParser tp, String[] args) throws ParseException {
	if ("frak".equals(args[0]))
	    args[0] = "mathfrak";
	else if ("Bbb".equals(args[0]))
	    args[0] = "mathbb";
	else if ("bold".equals(args[0]))
	    return new BoldAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);

	return new TeXFormula(tp.getIsPartial(), args[1], args[0]).root;
    }

    public Atom mbox_macro(TeXParser tp, String[] args) throws ParseException {
	String str = args[1].replaceAll("\\^\\{\\\\prime\\}", "\'");
	str = str.replaceAll("\\^\\{\\\\prime\\\\prime\\}", "\'\'");
	str = str.replaceAll("_", "\\\\_");
	Atom group = new RomanAtom(new TeXFormula(tp.getIsPartial(), str, "mathnormal", false, false).root);
	return new StyleAtom(TeXConstants.STYLE_TEXT, group);
    }

    public Atom text_macro(TeXParser tp, String[] args) throws ParseException {
	String str = args[1].replaceAll("\\^\\{\\\\prime\\}", "\'");
	str = str.replaceAll("\\^\\{\\\\prime\\\\prime\\}", "\'\'");
	str = str.replaceAll("_", "\\\\_");
	return new RomanAtom(new TeXFormula(tp.getIsPartial(), str, "mathnormal", false, false).root);
    }
    
    public Atom underscore_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderscoreAtom();
    }
    
    public Atom accent_macros(TeXParser tp, String[] args) throws ParseException {
	return new AccentedAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, args[0]);
    }

    public Atom grkaccent_macro(TeXParser tp, String[] args) throws ParseException {
	return new AccentedAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[1], false).root, false);
    }

    public Atom accent_macro(TeXParser tp, String[] args) throws ParseException {
	return new AccentedAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom accentbis_macros(TeXParser tp, String[] args) throws ParseException {
	String acc = "";
	switch (args[0].charAt(0)) {
	case '~' :
	    acc = "tilde";
	    break;
	case '\'' :
	    acc = "acute";
	    break;
	case '^' :
	    acc = "hat";
	    break;
	case '\"' :
	    acc = "ddot";
	    break;
	case '`' :
	    acc = "grave";
	    break;
	case '=' :
	    acc = "bar";
	    break;
	case '.' :
	    acc = "dot";
	    break;
	case 'u' :
	    acc = "breve";
	    break;
	case 'v' :
	    acc = "check";
	    break;
	case 'H' :
	    acc = "doubleacute";
	    break;
	case 't' : 
	    acc = "tie";
	    break;
	case 'r' : 
	    acc = "mathring";
	    break;
	case 'U' : 
	    acc = "cyrbreve";
	}

	return new AccentedAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, acc);
    }
    
    public Atom cedilla_macro(TeXParser tp, String[] args) throws ParseException {
	return new CedillaAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }
    
    public Atom IJ_macro(TeXParser tp, String[] args) throws ParseException {
	return new IJAtom(args[0].charAt(0) == 'I');
    }

    public Atom TStroke_macro(TeXParser tp, String[] args) throws ParseException {
	return new TStrokeAtom(args[0].charAt(0) == 'T');
    }

    public Atom LCaron_macro(TeXParser tp, String[] args) throws ParseException {
	return new LCaronAtom(args[0].charAt(0) == 'L');
    }

    public Atom tcaron_macro(TeXParser tp, String[] args) throws ParseException {
	return new tcaronAtom();
    }
    
    public Atom ogonek_macro(TeXParser tp, String[] args) throws ParseException {
	return new OgonekAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }

    public Atom nbsp_macro(TeXParser tp, String[] args) throws ParseException {
	return new SpaceAtom();
    }

    public Atom sqrt_macro(TeXParser tp, String[] args) throws ParseException {
	if (args[2] == null)
	    return new NthRoot(new TeXFormula(tp.getIsPartial(), args[1], false).root, null);
	return new NthRoot(new TeXFormula(tp.getIsPartial(), args[1], false).root, new TeXFormula(tp.getIsPartial(), args[2], false).root);
    }

    public Atom overrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, false, true);
    }

    public Atom overleftarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, true, true);
    }

    public Atom overleftrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, true);
    }

    public Atom underrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, false, false);
    }

    public Atom underleftarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, true, false);
    }

    public Atom underleftrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, false);
    }

    public Atom xleftarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new XArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, new TeXFormula(tp.getIsPartial(), args[2]).root, true);
    }

    public Atom xrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new XArrowAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, new TeXFormula(tp.getIsPartial(), args[2]).root, false);
    }
    
    public Atom sideset_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula tf = new TeXFormula();
	tf.add(new PhantomAtom(new TeXFormula(tp.getIsPartial(), args[3]).root, false, true, true));
	tf.append(tp.getIsPartial(), args[1]);
	tf.add(new SpaceAtom(TeXConstants.UNIT_MU, -0.3f, 0f, 0f));
	tf.append(tp.getIsPartial(), args[3] + "\\nolimits" + args[2]);
	return new TypedAtom(TeXConstants.TYPE_ORDINARY, TeXConstants.TYPE_ORDINARY, tf.root);
    }

    public Atom prescript_macro(TeXParser tp, String[] args) throws ParseException {
	Atom base = new TeXFormula(tp.getIsPartial(), args[3]).root;
	tp.addAtom(new ScriptsAtom(new PhantomAtom(base, false, true, true), new TeXFormula(tp.getIsPartial(), args[2]).root, new TeXFormula(tp.getIsPartial(), args[1]).root, false));
	tp.addAtom(new SpaceAtom(TeXConstants.UNIT_MU, -0.3f, 0f, 0f));
	return new TypedAtom(TeXConstants.TYPE_ORDINARY, TeXConstants.TYPE_ORDINARY, base);
    }
    
    public Atom underbrace_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(tp.getIsPartial(), args[1], false).root, null, SymbolAtom.get("rbrace"), TeXConstants.UNIT_EX, 0, false);
    }
    
    public Atom overbrace_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(tp.getIsPartial(), args[1], false).root, null, SymbolAtom.get("lbrace"), TeXConstants.UNIT_EX, 0, true);
    }

    public Atom underbrack_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(tp.getIsPartial(), args[1], false).root, null, SymbolAtom.get("rsqbrack"), TeXConstants.UNIT_EX, 0, false);
    }
    
    public Atom overbrack_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(tp.getIsPartial(), args[1], false).root, null, SymbolAtom.get("lsqbrack"), TeXConstants.UNIT_EX, 0, true);
    }

    public Atom underparen_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(tp.getIsPartial(), args[1], false).root, null, SymbolAtom.get("rbrack"), TeXConstants.UNIT_EX, 0, false);
    }
    
    public Atom overparen_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(tp.getIsPartial(), args[1], false).root, null, SymbolAtom.get("lbrack"), TeXConstants.UNIT_EX, 0, true);
    }

    public Atom overline_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverlinedAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom underline_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderlinedAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom mathop_macro(TeXParser tp, String[] args) throws ParseException {
	TypedAtom at =  new TypedAtom(TeXConstants.TYPE_BIG_OPERATOR, TeXConstants.TYPE_BIG_OPERATOR, new TeXFormula(tp.getIsPartial(), args[1], false).root);
	at.type_limits = TeXConstants.SCRIPT_NORMAL;
	return at;
    }
    
    public Atom mathpunct_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_PUNCTUATION, TeXConstants.TYPE_PUNCTUATION, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom mathord_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_ORDINARY, TeXConstants.TYPE_ORDINARY, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom mathrel_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom mathinner_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_INNER, TeXConstants.TYPE_INNER, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom mathbin_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_BINARY_OPERATOR, TeXConstants.TYPE_BINARY_OPERATOR, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom mathopen_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_OPENING, TeXConstants.TYPE_OPENING, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom mathclose_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_CLOSING, TeXConstants.TYPE_CLOSING, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom joinrel_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, new SpaceAtom(TeXConstants.UNIT_MU, -2.6f, 0, 0));
    }
    
    public Atom smash_macro(TeXParser tp, String[] args) throws ParseException {
	return new SmashedAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, args[2]);	
    }
    
    public Atom vdots_macro(TeXParser tp, String[] args) throws ParseException {
	return new VdotsAtom();
    }

    public Atom ddots_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_INNER, TeXConstants.TYPE_INNER, new DdotsAtom());
    }

    public Atom iddots_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_INNER, TeXConstants.TYPE_INNER, new IddotsAtom());
    }
    
    public Atom nolimits_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = tp.getLastAtom();
	at.type_limits = TeXConstants.SCRIPT_NOLIMITS;
	return at.clone();
    }
    
    public Atom limits_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = tp.getLastAtom();
	at.type_limits = TeXConstants.SCRIPT_LIMITS;
	return at.clone();
    }
    
    public Atom normal_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = tp.getLastAtom();
	at.type_limits = TeXConstants.SCRIPT_NORMAL;
	return at.clone();
    }
    
    public Atom left_macro(TeXParser tp, String[] args) throws ParseException {
	String grp = tp.getGroup("\\left", "\\right");
	Atom left = new TeXFormula(tp.getIsPartial(), args[1], false).root;
	if (left instanceof BigDelimiterAtom) 
	    left = ((BigDelimiterAtom)left).delim;
	Atom right = tp.getArgument();
	if (right instanceof BigDelimiterAtom) 
	    right = ((BigDelimiterAtom)right).delim;
	if (left instanceof SymbolAtom && right instanceof SymbolAtom) {
	    TeXFormula tf = new TeXFormula(tp.getIsPartial(), grp, false);
	    return new FencedAtom(tf.root, (SymbolAtom)left, tf.middle, (SymbolAtom)right);
	}

	RowAtom ra = new RowAtom();
	ra.add(left);
	ra.add(new TeXFormula(tp.getIsPartial(), grp, false).root);
	ra.add(right);
	return ra;
    }
    
    public Atom middle_macro(TeXParser tp, String[] args) throws ParseException {
	return new MiddleAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }

    public Atom cr_macro(TeXParser tp, String[] args) throws ParseException {
	if (!tp.isArrayMode())
	    throw new ParseException("The token \\cr is only available in array mode !");
	tp.addRow();
	return null;
    }
    
    public Atom backslashcr_macro(TeXParser tp, String[] args) throws ParseException {
	if (tp.isArrayMode())
	    tp.addRow();
	return null;
    }

    public Atom intertext_macro(TeXParser tp, String[] args) throws ParseException {
	if (!tp.isArrayMode()) {
	    throw new ParseException("Bad environment for \\intertext command !");
	}

	String str = args[1].replaceAll("\\^\\{\\\\prime\\}", "\'");
	str = str.replaceAll("\\^\\{\\\\prime\\\\prime\\}", "\'\'");
	Atom at = new RomanAtom(new TeXFormula(tp.getIsPartial(), str, "mathnormal", false, false).root);
	at.type = TeXConstants.TYPE_INTERTEXT;
	tp.addAtom(at);
	tp.addRow();
	return null;
    }

    public Atom smallmatrixATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.SMALLMATRIX);
    }

    public Atom matrixATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.MATRIX);
    }

    public Atom multicolumn_macro(TeXParser tp, String[] args) throws ParseException {
	int n = Integer.parseInt(args[1]);
	tp.addAtom(new MulticolumnAtom(n, args[2], new TeXFormula(tp.getIsPartial(), args[3]).root));
	((ArrayOfAtoms)tp.formula).addCol(n);
	return null;
    }

    public Atom hdotsfor_macro(TeXParser tp, String[] args) throws ParseException {
	int n = Integer.parseInt(args[1]);
	float f = 1;
	if (args[2] != null) {
	    f = Float.parseFloat(args[2]);
	}
	tp.addAtom(new HdotsforAtom(n, f));
	((ArrayOfAtoms)tp.formula).addCol(n);
	return null;
    }

    public Atom arrayATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[2], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(tp.getIsPartial(), array, args[1]);
    }

    public Atom alignATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.ALIGN);
    }

    public Atom flalignATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.FLALIGN);
    }

    public Atom alignatATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[2], array, false);
	parser.parse();
	array.checkDimensions();
	int n = Integer.parseInt(args[1]);
	if (array.col != 2 * n) {
	    throw new ParseException("Bad number of equations in alignat environment !");
	}

	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.ALIGNAT);
    }

    public Atom alignedATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.ALIGNED);
    }

    public Atom alignedatATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[2], array, false);
	parser.parse();
	array.checkDimensions();
	int n = Integer.parseInt(args[1]);
	if (array.col != 2 * n) {
	    throw new ParseException("Bad number of equations in alignedat environment !");
	}

	return new MatrixAtom(tp.getIsPartial(), array, MatrixAtom.ALIGNEDAT);
    }

    public Atom multlineATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	if (array.col > 1) {
	    throw new ParseException("Character '&' is only available in array mode !");
	}
	if (array.col == 0) {
	    return null;
	}
	
	return new MultlineAtom(tp.getIsPartial(), array, MultlineAtom.MULTLINE);
    }

    public Atom gatherATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	if (array.col > 1) {
	    throw new ParseException("Character '&' is only available in array mode !");
	}
	if (array.col == 0) {
	    return null;
	}
	
	return new MultlineAtom(tp.getIsPartial(), array, MultlineAtom.GATHER);
    }

    public Atom gatheredATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(tp.getIsPartial(), args[1], array, false);
	parser.parse();
	array.checkDimensions();
	if (array.col > 1) {
	    throw new ParseException("Character '&' is only available in array mode !");
	}
	if (array.col == 0) {
	    return null;
	}
	
	return new MultlineAtom(tp.getIsPartial(), array, MultlineAtom.GATHERED);
    }

    public Atom shoveright_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new TeXFormula(tp.getIsPartial(), args[1]).root;
	at.alignment = TeXConstants.ALIGN_RIGHT;
	return at;
    }

    public Atom shoveleft_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new TeXFormula(tp.getIsPartial(), args[1]).root;
	at.alignment = TeXConstants.ALIGN_LEFT;
	return at;
    }

    public Atom newcommand_macro(TeXParser tp, String[] args) throws ParseException {
	String newcom = args[1];
	Integer nbArgs;
	if (!tp.isValidName(newcom))
	    throw new ParseException("Invalid name for the command :" + newcom);

	if (args[3] == null) 
	    nbArgs = new Integer(0);
	else 
	    nbArgs = Integer.parseInt(args[3]);
	
	if (nbArgs == null)
	    throw new ParseException("The optional argument should be an integer !");
	
	if (args[4] == null)
	    NewCommandMacro.addNewCommand(newcom.substring(1), args[2], nbArgs.intValue());
	else
	    NewCommandMacro.addNewCommand(newcom.substring(1), args[2], nbArgs.intValue(), args[4]);
	
	return null;
    }
    
    public Atom renewcommand_macro(TeXParser tp, String[] args) throws ParseException {
	String newcom = args[1];
	Integer nbArgs;
	if (!tp.isValidName(newcom))
	    throw new ParseException("Invalid name for the command :" + newcom);

	if (args[3] == null) 
	    nbArgs = new Integer(0);
	else 
	    nbArgs = Integer.parseInt(args[3]);
	
	if (nbArgs == null)
	    throw new ParseException("The optional argument should be an integer !");
	
	NewCommandMacro.addReNewCommand(newcom.substring(1), args[2], nbArgs.intValue());
		
	return null;
    }

    public Atom makeatletter_macro(TeXParser tp, String[] args) throws ParseException {
	tp.makeAtLetter();
	return null;
    }

    public Atom makeatother_macro(TeXParser tp, String[] args) throws ParseException {
	tp.makeAtOther();
	return null;
    }

    public Atom newenvironment_macro(TeXParser tp, String[] args) throws ParseException {
	Integer opt = args[4] == null ? 0 : Integer.parseInt(args[4]);
	if (opt == null)
	    throw new ParseException("The optional argument should be an integer !");
	
	NewEnvironmentMacro.addNewEnvironment(args[1], args[2], args[3], opt.intValue());	
	return null;
    }

    public Atom renewenvironment_macro(TeXParser tp, String[] args) throws ParseException {
	Integer opt = args[4] == null ? 0 : Integer.parseInt(args[4]);
	if (opt == null)
	    throw new ParseException("The optional argument should be an integer !");
	
	NewEnvironmentMacro.addReNewEnvironment(args[1], args[2], args[3], opt.intValue());	
	return null;
    }
    
    public Atom fbox_macro(TeXParser tp, String[] args) throws ParseException {
	return new FBoxAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom stackrel_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[3], false).root, TeXConstants.UNIT_MU, 0.5f, true, new TeXFormula(tp.getIsPartial(), args[1], false).root, TeXConstants.UNIT_MU, 2.5f, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom stackbin_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[3], false).root, TeXConstants.UNIT_MU, 0.5f, true, new TeXFormula(tp.getIsPartial(), args[1], false).root, TeXConstants.UNIT_MU, 2.5f, true);
	return new TypedAtom(TeXConstants.TYPE_BINARY_OPERATOR, TeXConstants.TYPE_BINARY_OPERATOR, at);
    }

    public Atom overset_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[1], false).root, TeXConstants.UNIT_MU, 2.5f, true, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }
    
    public Atom underset_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[1], false).root, TeXConstants.UNIT_MU, 0.5f, true, false);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom accentset_macro(TeXParser tp, String[] args) throws ParseException {
	return new AccentedAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom underaccent_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverAtom(new TeXFormula(tp.getIsPartial(), args[2], false).root, new TeXFormula(tp.getIsPartial(), args[1], false).root, TeXConstants.UNIT_MU, 0.3f, true, false);
    }
    
    public Atom undertilde_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new TeXFormula(tp.getIsPartial(), args[1], false).root;
	return new UnderOverAtom(at, new AccentedAtom(new PhantomAtom(at, true, false, false), "widetilde"), TeXConstants.UNIT_MU, 0.3f, true, false);
    }

    public Atom boldsymbol_macro(TeXParser tp, String[] args) throws ParseException {
	return new BoldAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
    
    public Atom mathrm_macro(TeXParser tp, String[] args) throws ParseException {
	return new RomanAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom mathbf_macro(TeXParser tp, String[] args) throws ParseException {
	return new BoldAtom(new RomanAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root));
    }

    public Atom mathtt_macro(TeXParser tp, String[] args) throws ParseException {
	return new TtAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom mathit_macro(TeXParser tp, String[] args) throws ParseException {
	return new ItAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }

    public Atom mathsf_macro(TeXParser tp, String[] args) throws ParseException {
	return new SsAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root);
    }
   
    public Atom LaTeX_macro(TeXParser tp, String[] args) throws ParseException {
	return new LaTeXAtom();
    }
    
    public Atom GeoGebra_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula tf = new TeXFormula("\\mathbb{G}\\mathsf{e}");
	tf.add(new GeoGebraLogoAtom());
	tf.add("\\mathsf{Gebra}");
	return new ColorAtom(tf.root, null, new Color(102, 102, 102));
    }

    public Atom hphantom_macro(TeXParser tp, String[] args) throws ParseException {
	return new PhantomAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, true, false, false);
    }

    public Atom vphantom_macro(TeXParser tp, String[] args) throws ParseException {
	return new PhantomAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, false, true, true);
    }

    public Atom phantom_macro(TeXParser tp, String[] args) throws ParseException {
	return new PhantomAtom(new TeXFormula(tp.getIsPartial(), args[1], false).root, true, true, true);
    }
    
    public Atom big_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 1);
    }
    
    public Atom Big_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 2);
    }

    public Atom bigg_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 3);
    }
    
    public Atom Bigg_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 4);
    }

    public Atom bigl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 1);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }
    
    public Atom Bigl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 2);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }

    public Atom biggl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 3);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }
    
    public Atom Biggl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 4);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }

    public Atom bigr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 1);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }
    
    public Atom Bigr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 2);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }

    public Atom biggr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 3);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }
    
    public Atom Biggr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(tp.getIsPartial(), args[1], false).root, 4);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }

    public Atom displaystyle_macro(TeXParser tp, String[] args) throws ParseException {
	Atom group = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	return new StyleAtom(TeXConstants.STYLE_DISPLAY, group);
    }

    public Atom scriptstyle_macro(TeXParser tp, String[] args) throws ParseException {
	Atom group = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	return new StyleAtom(TeXConstants.STYLE_SCRIPT, group);
    }
    
    public Atom textstyle_macro(TeXParser tp, String[] args) throws ParseException {
	Atom group = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	return new StyleAtom(TeXConstants.STYLE_TEXT, group);
    }
    
    public Atom scriptscriptstyle_macro(TeXParser tp, String[] args) throws ParseException {
	Atom group = new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), false).root;
	return new StyleAtom(TeXConstants.STYLE_SCRIPT_SCRIPT, group);
    }

    public Atom rotatebox_macro(TeXParser tp, String[] args) throws ParseException {
	return new RotateAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, args[1] == null ? 0 : Double.parseDouble(args[1]), args[3]);
    }

    public Atom reflectbox_macro(TeXParser tp, String[] args) throws ParseException {
	return new ReflectAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }

    public Atom scalebox_macro(TeXParser tp, String[] args) throws ParseException {
	return new ScaleAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, Double.parseDouble(args[1]), args[3] == null ? Double.parseDouble(args[1]) : Double.parseDouble(args[3]));
    }

    public Atom resizebox_macro(TeXParser tp, String[] args) throws ParseException {
	return new ResizeAtom(new TeXFormula(tp.getIsPartial(), args[3]).root, args[1], args[2], false);
    }

    public Atom raisebox_macro(TeXParser tp, String[] args) throws ParseException {
	float[] raise = SpaceAtom.getLength(args[1]);
	float[] height = SpaceAtom.getLength(args[3]);
	float[] depth = SpaceAtom.getLength(args[4]);
	if (height.length == 1 || height[1] == 0) {
	    height[0] = -1;
	}
	if (depth.length == 1 || depth[1] == 0) {
	    depth[0] = -1;
	}
	return new RaiseAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, (int) raise[0], raise[1], (int) height[0], height[1], (int) depth[0], depth[1]);
    }

    public Atom shadowbox_macro(TeXParser tp, String[] args) throws ParseException {
	return new ShadowAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }

    public Atom ovalbox_macro(TeXParser tp, String[] args) throws ParseException {
	return new OvalAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }

    public Atom doublebox_macro(TeXParser tp, String[] args) throws ParseException {
	return new DoubleFramedAtom(new TeXFormula(tp.getIsPartial(), args[1]).root);
    }
    
    public Atom definecolor_macro(TeXParser tp, String[] args) throws ParseException {
	Color color = null;
	if ("gray".equals(args[2])) {
	    float f = Float.parseFloat(args[3]);
	    color = new Color(f, f, f);
	} else if ("rgb".equals(args[2])) {
	    StringTokenizer stok = new StringTokenizer(args[3], ",");
	    if (stok.countTokens() != 3)
		throw new ParseException("The color definition must have three components !");
	    float r = Float.parseFloat(stok.nextToken());
	    float g = Float.parseFloat(stok.nextToken());
	    float b = Float.parseFloat(stok.nextToken());
	    color = new Color(r, g, b);
	} else if ("cmyk".equals(args[2])) {
	    StringTokenizer stok = new StringTokenizer(args[3], ",");
	    if (stok.countTokens() != 4)
		throw new ParseException("The color definition must have four components !");
	    float[] cmyk = new float[4];
	    for (int i = 0; i < 4; i++) 
		cmyk[i] = Float.parseFloat(stok.nextToken());
	    float k = 1 - cmyk[3];
	    color = new Color(k*(1-cmyk[0]), k*(1-cmyk[1]), k*(1-cmyk[2]));
	} else
	    throw new ParseException("The color model is incorrect !");

	ColorAtom.Colors.put(args[1], color);
	return null;
    }
    
    public Atom fgcolor_macro(TeXParser tp, String[] args) throws ParseException {
	String code = args[1] == null ? "#000000" : args[1].trim();
	if (!code.startsWith("#")) {
	    code = "#" + code;
	}
	try {
	    return new ColorAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, null, Color.decode(code));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString());
	}
    }	

    public Atom bgcolor_macro(TeXParser tp, String[] args) throws ParseException {
	String code = args[1] == null ? "#000000" : args[1].trim();
	if (!code.startsWith("#")) {
	    code = "#" + code;
	}
	try {
	    return new ColorAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, Color.decode(code), null);
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString());
	}
    }	

    public Atom textcolor_macro(TeXParser tp, String[] args) throws ParseException {
	return new ColorAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, null, ColorAtom.Colors.get(args[1]));
    }
    
    public Atom colorbox_macro(TeXParser tp, String[] args) throws ParseException {
	Color c = ColorAtom.Colors.get(args[1]);
	return new FBoxAtom(new TeXFormula(tp.getIsPartial(), args[2]).root, c, c);
    }

    public Atom fcolorbox_macro(TeXParser tp, String[] args) throws ParseException {	
	return new FBoxAtom(new TeXFormula(tp.getIsPartial(), args[3]).root, ColorAtom.Colors.get(args[2]), ColorAtom.Colors.get(args[1]));
    }
    
    public Atom cong_macro(TeXParser tp, String[] args) throws ParseException {
	VRowAtom vra = new VRowAtom(SymbolAtom.get("equals"));
	vra.add(new SpaceAtom(TeXConstants.UNIT_MU, 0f, 1.5f, 0f));
	vra.add(SymbolAtom.get("sim"));
	vra.setRaise(TeXConstants.UNIT_MU, -1f);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, vra);
    }
    
    public Atom doteq_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(SymbolAtom.get("equals"), SymbolAtom.get("ldotp"), TeXConstants.UNIT_MU, 3.7f, false, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom jlmDynamic_macro(TeXParser tp, String[] args) throws ParseException {
	if (DynamicAtom.hasAnExternalConverterFactory()) {
	    return new DynamicAtom(args[1]);
	} else 
	    throw new ParseException("No ExternalConverterFactory set !");	
    }

    public Atom DeclareMathSizes_macro(TeXParser tp, String[] args) throws ParseException {
	DefaultTeXFont.setMathSizes(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]));
	return null;
    }
    
    public Atom magnification_macro(TeXParser tp, String[] args) throws ParseException {
	DefaultTeXFont.setMagnification(Float.parseFloat(args[1]));
	return null;
    }

    public Atom hline_macro(TeXParser tp, String[] args) throws ParseException {
	if (!tp.isArrayMode())
	    throw new ParseException("The macro \\hline is only available in array mode !");
	return new HlineAtom();
    }

    public Atom size_macros(TeXParser tp, String[] args) throws ParseException {
	double f = 1;
	if ("tiny".equals(args[0])) {
	    f = 0.5;
	} else if ("scriptsize".equals(args[0])) {
	    f = 0.7;
	} else if ("footnotesize".equals(args[0])) {
	    f = 0.8;
	} else if ("small".equals(args[0])) {
	    f = 0.9;
	} else if ("normalsize".equals(args[0])) {
	    f = 1;
	} else if ("large".equals(args[0])) {
	    f = 1.2;
	} else if ("Large".equals(args[0])) {
	    f = 1.4;
	} else if ("LARGE".equals(args[0])) {
	    f = 1.8;
	} else if ("huge".equals(args[0])) {
	    f = 2;
	} else if ("Huge".equals(args[0])) {
	    f = 2.5;
	}

	return new ScaleAtom(new TeXFormula(tp.getIsPartial(), tp.getOverArgument(), null, false, tp.isIgnoreWhiteSpace()).root, f, f);
    }

    public Atom jlatexmathcumsup_macro(TeXParser tp, String[] args) throws ParseException {	
	return new CumulativeScriptsAtom(tp.getLastAtom(), null, new TeXFormula(tp.getIsPartial(), args[1]).root);
    }

    public Atom jlatexmathcumsub_macro(TeXParser tp, String[] args) throws ParseException {	
	return new CumulativeScriptsAtom(tp.getLastAtom(), new TeXFormula(tp.getIsPartial(), args[1]).root, null);
    }

    public Atom dotminus_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(SymbolAtom.get("minus"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, -3.3f, false, true);
	return new TypedAtom(TeXConstants.TYPE_BINARY_OPERATOR, TeXConstants.TYPE_BINARY_OPERATOR, at);
    }

    public Atom ratio_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom geoprop_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom ddot = new RowAtom(SymbolAtom.get("normaldot"));
	ddot.add(new SpaceAtom(TeXConstants.UNIT_MU, 4f, 0f, 0f));
	ddot.add(SymbolAtom.get("normaldot"));
	Atom at = new UnderOverAtom(SymbolAtom.get("minus"), ddot, TeXConstants.UNIT_MU, -3.4f, false, ddot, TeXConstants.UNIT_MU, -3.4f, false);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom minuscolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("minus"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	at.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom minuscoloncolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("minus"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true); 
	at.add(colon);
	at.add(colon);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom simcolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("sim"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	at.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom simcoloncolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("sim"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true); 
	at.add(colon);
	at.add(colon);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom approxcolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("approx"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	at.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom approxcoloncolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("approx"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true); 
	at.add(colon);
	at.add(colon);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom equalscolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("equals"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	at.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom equalscoloncolon_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(SymbolAtom.get("equals"));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.22f, 0f, 0f));
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true); 
	at.add(colon);
	at.add(colon);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom colonminus_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("minus"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom coloncolonminus_macro(TeXParser tp, String[] args) throws ParseException {
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true);
	RowAtom at = new RowAtom(colon);
	at.add(colon);
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("minus"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom colonequals_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("equals"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom coloncolonequals_macro(TeXParser tp, String[] args) throws ParseException {
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true);
	RowAtom at = new RowAtom(colon);
	at.add(colon);
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("equals"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom coloncolon_macro(TeXParser tp, String[] args) throws ParseException {
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true);
	RowAtom at = new RowAtom(colon);
	at.add(colon);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom colonsim_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("sim"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom coloncolonsim_macro(TeXParser tp, String[] args) throws ParseException {
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true);
	RowAtom at = new RowAtom(colon);
	at.add(colon);
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("sim"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom colonapprox_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom at = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true));
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("approx"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom coloncolonapprox_macro(TeXParser tp, String[] args) throws ParseException {
	Atom colon = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), TeXConstants.UNIT_MU, 5.2f, false, true);
	RowAtom at = new RowAtom(colon);
	at.add(colon);
	at.add(new SpaceAtom(TeXConstants.UNIT_EM, -0.75f, 0f, 0f));
	at.add(SymbolAtom.get("approx"));
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom smallfrowneq_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(SymbolAtom.get("equals"), SymbolAtom.get("smallfrown"), TeXConstants.UNIT_MU, -2f, true, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom hstrok_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom ra = new RowAtom(new SpaceAtom(TeXConstants.UNIT_EX, -0.1f, 0f, 0f));
	ra.add(SymbolAtom.get("bar"));
	VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
	vra.setRaise(TeXConstants.UNIT_EX, -0.1f);
	RowAtom at = new RowAtom(vra);
	at.add(new RomanAtom(new CharAtom('h', tp.formula.textStyle)));
	return at;
    }

    public Atom Hstrok_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom ra = new RowAtom(new SpaceAtom(TeXConstants.UNIT_EX, 0.28f, 0f, 0f));
	ra.add(SymbolAtom.get("textendash"));
	VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
	vra.setRaise(TeXConstants.UNIT_EX, 0.55f);
	RowAtom at = new RowAtom(vra);
	at.add(new RomanAtom(new CharAtom('H', tp.formula.textStyle)));
	return at;
    }

    public Atom dstrok_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom ra = new RowAtom(new SpaceAtom(TeXConstants.UNIT_EX, 0.25f, 0f, 0f));
	ra.add(SymbolAtom.get("bar"));
	VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
	vra.setRaise(TeXConstants.UNIT_EX, -0.1f);
	RowAtom at = new RowAtom(vra);
	at.add(new RomanAtom(new CharAtom('d', tp.formula.textStyle)));
	return at;
    }

    public Atom Dstrok_macro(TeXParser tp, String[] args) throws ParseException {
	RowAtom ra = new RowAtom(new SpaceAtom(TeXConstants.UNIT_EX, -0.1f, 0f, 0f));
	ra.add(SymbolAtom.get("bar"));
	VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
	vra.setRaise(TeXConstants.UNIT_EX, -0.55f);
	RowAtom at = new RowAtom(vra);
	at.add(new RomanAtom(new CharAtom('D', tp.formula.textStyle)));
	return at;
    }

    public Atom kern_macro(TeXParser tp, String[] args) throws ParseException {
	float[] info = SpaceAtom.getLength(args[1]);
	return new SpaceAtom((int) info[0], info[1], 0f, 0f);
    }

    public Atom char_macro(TeXParser tp, String[] args) throws ParseException {
	String number = args[1];
	int radix = 10;
	if (number.startsWith("0x") || number.startsWith("0X")) {
	    number = number.substring(2);
	    radix = 16;
	} else if (number.startsWith("x") || number.startsWith("X")) {
	    number = number.substring(1);
	    radix = 16;
	} else if (number.startsWith("0")) {
	    number = number.substring(1);
	    radix = 8;
	}
	int n = Integer.parseInt(number, radix);
	return tp.convertCharacter((char) n);
    }

    public Atom T_macro(TeXParser tp, String[] args) throws ParseException {
	return new RotateAtom(new TeXFormula(tp.getIsPartial(), args[1]).root, 180, "origin=cc");
    }

    public Atom romannumeral_macro(TeXParser tp, String[] args) throws ParseException {
	int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] letters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	String roman = "";
        int num = Integer.parseInt(args[1].trim());
        for (int i = 0; i < numbers.length; i++) {
           while (num >= numbers[i]) {
              roman += letters[i];
              num -= numbers[i];
           }
        }
	
	if (args[0].charAt(0) == 'r') {
	    roman = roman.toLowerCase();
	}

	return new TeXFormula(roman, false).root;
    }
}
