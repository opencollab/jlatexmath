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

/**
 * This class contains the most of basic commands of LaTeX, they're activated in
 * the file PredefinedCommands.xml.
 **/
public class predefMacros {
    
    static {
	NewEnvironmentMacro.addNewEnvironment("array", "\\array@@env{#1}{", "}", 1);
	NewEnvironmentMacro.addNewEnvironment("matrix", "\\begin{array}{}", "\\end{array}", 0);
	NewEnvironmentMacro.addNewEnvironment("pmatrix", "\\left(\\begin{array}{}", "\\end{array}\\right)", 0);
	NewEnvironmentMacro.addNewEnvironment("bmatrix", "\\left[\\begin{array}{}", "\\end{array}\\right]", 0);
	NewEnvironmentMacro.addNewEnvironment("Bmatrix", "\\left\\{\\begin{array}{}", "\\end{array}\\right\\}", 0);
	NewEnvironmentMacro.addNewEnvironment("vmatrix", "\\left|\\begin{array}{}", "\\end{array}\\right|", 0);
	NewEnvironmentMacro.addNewEnvironment("Vmatrix", "\\left\\|\\begin{array}{}", "\\end{array}\\right\\|", 0);
	NewEnvironmentMacro.addNewEnvironment("eqnarray", "\\begin{array}{rcl}", "\\end{array}", 0);
	NewEnvironmentMacro.addNewEnvironment("cases", "\\left\\{\\begin{array}{l@{\\!}l}", "\\end{array}\\right.", 0);
	NewEnvironmentMacro.addNewEnvironment("split", "\\begin{array}{rl}", "\\end{array}", 0);
	NewEnvironmentMacro.addNewEnvironment("gather", "\\begin{array}{c}", "\\end{array}", 0);
	NewCommandMacro.addNewCommand("operatorname", "\\mathop{\\mathrm{#1}}\\nolimits", 1);
	NewCommandMacro.addNewCommand("DeclareMathOperator", "\\newcommand{#1}{\\mathop{\\mathrm{#2}}\\nolimits}", 2);
	NewCommandMacro.addNewCommand("substack", "\\begin{array}{l}#1\\end{array}", 1);
	NewCommandMacro.addNewCommand("dfrac", "{\\displaystyle\\frac{#1}{#2}}", 2);
	NewCommandMacro.addNewCommand("tfrac", "{\\textstyle\\frac{#1}{#2}}", 2);
	NewCommandMacro.addNewCommand("dbinom", "{\\displaystyle\\binom{#1}{#2}}", 2);
	NewCommandMacro.addNewCommand("tbinom", "{\\textstyle\\binom{#1}{#2}}", 2);
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
       

    public Atom frac_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula num = new TeXFormula(args[1], false);
	TeXFormula denom = new TeXFormula(args[2], false);
	if (num.root == null || denom.root == null)
	    throw new ParseException("Both numerator and denominator of a fraction can't be empty!");
	return new FractionAtom(num.root, denom.root, true);
    }

    public Atom binom_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula num = new TeXFormula(args[1], false);
	TeXFormula denom = new TeXFormula(args[2], false);
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
	    args[0] = "mathbf";

	return new TeXFormula(args[1], args[0]).root;
    }

    public Atom mbox_macro(TeXParser tp, String[] args) throws ParseException {
	String str = args[1].replaceAll("\\^\\{\\\\prime\\}", "\'");
	str = str.replaceAll("\\^\\{\\\\prime\\\\prime\\}", "\'\'");
	return new RomanAtom(new TeXFormula(str, "mathnormal", false, false).root);
    }
    
    public Atom accent_macros(TeXParser tp, String[] args) throws ParseException {
	return new AccentedAtom(new TeXFormula(args[1], false).root, args[0]);
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
	case 'U' : 
	    acc = "cyrbreve";
	}

	return new AccentedAtom(new TeXFormula(args[1], false).root, acc);
    }
    
    public Atom cedilla_macro(TeXParser tp, String[] args) throws ParseException {
	return new CedillaAtom(new TeXFormula(args[1]).root);
    }

    public Atom nbsp_macro(TeXParser tp, String[] args) throws ParseException {
	return new SpaceAtom();
    }
    
    public Atom sqrt_macro(TeXParser tp, String[] args) throws ParseException {
	if (args[2] == null)
	    return new NthRoot(new TeXFormula(args[1], false).root, null);
	return new NthRoot(new TeXFormula(args[1], false).root, new TeXFormula(args[2], false).root);
    }

    public Atom overrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(args[1], false).root, false, true);
    }

    public Atom overleftarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(args[1], false).root, true, true);
    }

    public Atom overleftrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(args[1], false).root, true);
    }

    public Atom underrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(args[1], false).root, false, false);
    }

    public Atom underleftarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(args[1], false).root, true, false);
    }

    public Atom underleftrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverArrowAtom(new TeXFormula(args[1], false).root, false);
    }

    public Atom xleftarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new XArrowAtom(new TeXFormula(args[1], false).root, new TeXFormula(args[2]).root, true);
    }

    public Atom xrightarrow_macro(TeXParser tp, String[] args) throws ParseException {
	return new XArrowAtom(new TeXFormula(args[1], false).root, new TeXFormula(args[2]).root, false);
    }
    
    public Atom sideset_macro(TeXParser tp, String[] args) throws ParseException {
	TeXFormula tf = new TeXFormula();
	tf.add(new PhantomAtom(new TeXFormula(args[3]).root, false, true, true));
	tf.append(args[1]);
	tf.add(new SpaceAtom(TeXConstants.UNIT_MU, -2, 0, 0));
	tf.append(args[3] + "\\nolimits" + args[2]);
	return new TypedAtom(TeXConstants.TYPE_ORDINARY, TeXConstants.TYPE_ORDINARY, tf.root);
    }
    
    public Atom underbrace_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(args[1], false).root, null, SymbolAtom.get("rbrace"), TeXConstants.UNIT_EX, 0, false);
    }
    
    public Atom overbrace_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(args[1], false).root, null, SymbolAtom.get("lbrace"), TeXConstants.UNIT_EX, 0, true);
    }

    public Atom underbrack_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(args[1], false).root, null, SymbolAtom.get("rsqbrack"), TeXConstants.UNIT_EX, 0, false);
    }
    
    public Atom overbrack_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(args[1], false).root, null, SymbolAtom.get("lsqbrack"), TeXConstants.UNIT_EX, 0, true);
    }

    public Atom underparen_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(args[1], false).root, null, SymbolAtom.get("rbrack"), TeXConstants.UNIT_EX, 0, false);
    }
    
    public Atom overparen_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverUnderDelimiter(new TeXFormula(args[1], false).root, null, SymbolAtom.get("lbrack"), TeXConstants.UNIT_EX, 0, true);
    }

    public Atom overline_macro(TeXParser tp, String[] args) throws ParseException {
	return new OverlinedAtom(new TeXFormula(args[1], false).root);
    }
    
    public Atom underline_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderlinedAtom(new TeXFormula(args[1], false).root);
    }
    
    public Atom mathop_macro(TeXParser tp, String[] args) throws ParseException {
	TypedAtom at =  new TypedAtom(TeXConstants.TYPE_BIG_OPERATOR, TeXConstants.TYPE_BIG_OPERATOR, new TeXFormula(args[1], false).root);
	at.type_limits = TeXConstants.SCRIPT_NORMAL;
	return at;
    }
    
    public Atom mathord_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_ORDINARY, TeXConstants.TYPE_ORDINARY, new TeXFormula(args[1], false).root);
    }
    
    public Atom mathrel_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, new TeXFormula(args[1], false).root);
    }

    public Atom mathinner_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_INNER, TeXConstants.TYPE_INNER, new TeXFormula(args[1], false).root);
    }
    
    public Atom mathbin_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_BINARY_OPERATOR, TeXConstants.TYPE_BINARY_OPERATOR, new TeXFormula(args[1], false).root);
    }

    public Atom mathopen_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_OPENING, TeXConstants.TYPE_OPENING, new TeXFormula(args[1], false).root);
    }
    
    public Atom mathclose_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_CLOSING, TeXConstants.TYPE_CLOSING, new TeXFormula(args[1], false).root);
    }
    
    public Atom joinrel_macro(TeXParser tp, String[] args) throws ParseException {
	return new SpaceAtom(TeXConstants.UNIT_MU, -3.0f, 0, 0);
    }
    
    public Atom smash_macro(TeXParser tp, String[] args) throws ParseException {
	return new SmashedAtom(new TeXFormula(args[1], false).root, args[2]);	
    }
    
    public Atom vdots_macro(TeXParser tp, String[] args) throws ParseException {
	return new VdotsAtom();
    }

    public Atom ddots_macro(TeXParser tp, String[] args) throws ParseException {
	return new TypedAtom(TeXConstants.TYPE_INNER, TeXConstants.TYPE_INNER, new DdotsAtom());
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
	Atom left = new TeXFormula(args[1], false).root;
	if (left instanceof BigDelimiterAtom) 
	    left = ((BigDelimiterAtom)left).delim;
	Atom right = tp.getArgument();
	if (right instanceof BigDelimiterAtom) 
	    right = ((BigDelimiterAtom)right).delim;
	if (left instanceof SymbolAtom && right instanceof SymbolAtom) {
	    TeXFormula tf = new TeXFormula(grp, false);
	    if (tf.middle != null && !(tf.middle.base instanceof SymbolAtom))
		tf.middle = null;

	    return new FencedAtom(tf.root, (SymbolAtom)left, tf.middle, (SymbolAtom)right);
	}

	RowAtom ra = new RowAtom();
	ra.add(left);
	ra.add(new TeXFormula(grp, false).root);
	ra.add(right);
	return ra;
    }
    
    public Atom middle_macro(TeXParser tp, String[] args) throws ParseException {
	return new MiddleAtom(new TeXFormula(args[1]).root);
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

    public Atom arrayATATenv_macro(TeXParser tp, String[] args) throws ParseException {
	ArrayOfAtoms array = new ArrayOfAtoms();
	TeXParser parser = new TeXParser(args[2], array, false);
	parser.parse();
	array.checkDimensions();
	return new MatrixAtom(array, args[1]);
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
    
    public Atom begin_macro(TeXParser tp, String[] args) throws ParseException {
	MacroInfo mac = MacroInfo.Commands.get(args[1] + "@env");
	int nbArgs = mac.nbArgs - 1;
	String[] optarg = tp.getOptsArgs(nbArgs, 0);
	String grp = tp.getGroup("\\begin{" + args[1] + "}", "\\end{" + args[1] + "}");
	String expr = "\\makeatletter \\" + args[1] + "@env";
	for (int i = 1; i <= nbArgs; i++)
	    expr += "{" + optarg[i] + "}";
	expr += "{" + grp  + "}\\makeatother";
	
	TeXFormula tf = new TeXFormula(expr);
	return tf.root;
    }
    
    public Atom fbox_macro(TeXParser tp, String[] args) throws ParseException {
	return new FBoxAtom(new TeXFormula(args[1], false).root);
    }
    
    public Atom stackrel_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(args[2], false).root,new TeXFormula(args[3], false).root, TeXConstants.UNIT_MU, 0.5f, true, new TeXFormula(args[1], false).root, TeXConstants.UNIT_MU, 2.5f, true);
	//at.type = TeXConstants.TYPE_RELATION;
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom stackbin_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(args[2], false).root,new TeXFormula(args[3], false).root, TeXConstants.UNIT_MU, 0.5f, true, new TeXFormula(args[1], false).root, TeXConstants.UNIT_MU, 2.5f, true);
	return new TypedAtom(TeXConstants.TYPE_BINARY_OPERATOR, TeXConstants.TYPE_BINARY_OPERATOR, at);
    }

    public Atom overset_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(args[2], false).root, new TeXFormula(args[1], false).root, TeXConstants.UNIT_MU, 2.5f, true, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }
    
    public Atom underset_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula(args[2], false).root, new TeXFormula(args[1], false).root, TeXConstants.UNIT_MU, 0.5f, true, false);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }

    public Atom accentset_macro(TeXParser tp, String[] args) throws ParseException {
	return new AccentedAtom(new TeXFormula(args[2], false).root, new TeXFormula(args[1], false).root);
    }

    public Atom underaccent_macro(TeXParser tp, String[] args) throws ParseException {
	return new UnderOverAtom(new TeXFormula(args[2], false).root, new TeXFormula(args[1], false).root, TeXConstants.UNIT_MU, 0.3f, true, false);
    }
    
    public Atom undertilde_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new TeXFormula(args[1], false).root;
	return new UnderOverAtom(at, new AccentedAtom(new PhantomAtom(at, true, false, false),"widetilde"), TeXConstants.UNIT_MU, 0.3f, true, false);
    }

    public Atom boldsymbol_macro(TeXParser tp, String[] args) throws ParseException {
	return new BoldAtom(new TeXFormula(args[1], false).root);
    }
    
    public Atom mathrm_macro(TeXParser tp, String[] args) throws ParseException {
	return new RomanAtom(new TeXFormula(args[1], false).root);
    }

    public Atom mathtt_macro(TeXParser tp, String[] args) throws ParseException {
	return new TtAtom(new TeXFormula(args[1], false).root);
    }

    public Atom mathit_macro(TeXParser tp, String[] args) throws ParseException {
	return new ItAtom(new TeXFormula(args[1], false).root);
    }

    public Atom mathsf_macro(TeXParser tp, String[] args) throws ParseException {
	return new SsAtom(new TeXFormula(args[1], false).root);
    }
   
    public Atom LaTeX_macro(TeXParser tp, String[] args) throws ParseException {
	return new LaTeXAtom();
    }
    
    public Atom hphantom_macro(TeXParser tp, String[] args) throws ParseException {
	return new PhantomAtom(new TeXFormula(args[1], false).root, true, false, false);
    }

    public Atom vphantom_macro(TeXParser tp, String[] args) throws ParseException {
	return new PhantomAtom(new TeXFormula(args[1], false).root, false, true, true);
    }

    public Atom phantom_macro(TeXParser tp, String[] args) throws ParseException {
	return new PhantomAtom(new TeXFormula(args[1], false).root, true, true, true);
    }
    
    public Atom big_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 1);
    }
    
    public Atom Big_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 2);
    }

    public Atom bigg_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 3);
    }
    
    public Atom Bigg_macro(TeXParser tp, String[] args) throws ParseException {
	return new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 4);
    }

    public Atom bigl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 1);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }
    
    public Atom Bigl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 2);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }

    public Atom biggl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 3);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }
    
    public Atom Biggl_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 4);
	at.type = TeXConstants.TYPE_OPENING;
	return at;
    }

    public Atom bigr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 1);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }
    
    public Atom Bigr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 2);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }

    public Atom biggr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 3);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }
    
    public Atom Biggr_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new BigDelimiterAtom((SymbolAtom)new TeXFormula(args[1], false).root, 4);
	at.type = TeXConstants.TYPE_CLOSING;
	return at;
    }

    public Atom displaystyle_macro(TeXParser tp, String[] args) throws ParseException {
	tp.hasStyleChanged = true;
	return new StyleAtom(TeXConstants.STYLE_DISPLAY);
    }

    public Atom scriptstyle_macro(TeXParser tp, String[] args) throws ParseException {
	tp.hasStyleChanged = true;
	return new StyleAtom(TeXConstants.STYLE_SCRIPT);
    }
    
    public Atom textstyle_macro(TeXParser tp, String[] args) throws ParseException {
	tp.hasStyleChanged = true;
	return new StyleAtom(TeXConstants.STYLE_TEXT);
    }
    
    public Atom scriptscriptstyle_macro(TeXParser tp, String[] args) throws ParseException {
	tp.hasStyleChanged = true;
	return new StyleAtom(TeXConstants.STYLE_SCRIPT_SCRIPT);
    }

    public Atom rotatebox_macro(TeXParser tp, String[] args) throws ParseException {
	return new RotateAtom(new TeXFormula(args[2]).root, args[1] == null ? 0 : Double.parseDouble(args[1]));
    }

    public Atom reflectbox_macro(TeXParser tp, String[] args) throws ParseException {
	return new ReflectAtom(new TeXFormula(args[1]).root);
    }

    public Atom scalebox_macro(TeXParser tp, String[] args) throws ParseException {
	return new ScaleAtom(new TeXFormula(args[2]).root, Double.parseDouble(args[1]), args[3] == null ? Double.parseDouble(args[1]) : Double.parseDouble(args[3]));
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
	try {
	    return new ColorAtom(new TeXFormula(args[2]).root, null, Color.decode("#" + args[1]));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString());
	}
    }	

    public Atom bgcolor_macro(TeXParser tp, String[] args) throws ParseException {
	try {
	    return new ColorAtom(new TeXFormula(args[2]).root, Color.decode("#" + args[1]), null);
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString());
	}
    }	

    public Atom textcolor_macro(TeXParser tp, String[] args) throws ParseException {
	
	return new ColorAtom(new TeXFormula(args[2]).root, null, ColorAtom.Colors.get(args[1]));
    }
    
    public Atom colorbox_macro(TeXParser tp, String[] args) throws ParseException {
	
	return new ColorAtom(new TeXFormula(args[2]).root, ColorAtom.Colors.get(args[1]), null);
    }

    public Atom fcolorbox_macro(TeXParser tp, String[] args) throws ParseException {
	
	return new FColorBoxAtom(new TeXFormula(args[3]).root, ColorAtom.Colors.get(args[2]), ColorAtom.Colors.get(args[1]));
    }
    
    public Atom cong_macro(TeXParser tp, String[] args) throws ParseException {
	VRowAtom vra = new VRowAtom(new TeXFormula("\\equals").root);
	vra.add(new SpaceAtom(TeXConstants.UNIT_MU, 0f, 1.5f, 0f));
	vra.add(new TeXFormula("\\sim").root);
	vra.setRaise(TeXConstants.UNIT_MU, -1f);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, vra);
    }
    
    public Atom doteq_macro(TeXParser tp, String[] args) throws ParseException {
	Atom at = new UnderOverAtom(new TeXFormula("\\equals", false).root, new TeXFormula("\\ldotp", false).root, TeXConstants.UNIT_MU, 3.7f, false, true);
	return new TypedAtom(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_RELATION, at);
    }
}
