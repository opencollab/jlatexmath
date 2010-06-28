/* TeXParser.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/p/jlatexmath
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

import java.util.Stack;
import java.lang.Character.UnicodeBlock;

/** 
 * This class implements a parser for LaTeX' formulas.
 * 
 */
public class TeXParser {

    public TeXFormula formula;
    
    private StringBuffer parseString;
    private int pos;
    private int spos;
    private int line = 0;
    private int col = 0;
    private int len;
    private int group = 0;
    private boolean insertion = false;
    private int atIsLetter = 0;
    private boolean arrayMode = false;
    private boolean ignoreWhiteSpace = true;

    // the escape character
    private static final char ESCAPE = '\\';
    
    // grouping characters (for parsing)
    private static final char L_GROUP = '{';
    private static final char R_GROUP = '}';
    private static final char L_BRACK = '[';
    private static final char R_BRACK = ']';
    
    // used as second index in "delimiterNames" table (over or under)
    private static final int OVER_DEL = 0;
    private static final int UNDER_DEL = 1;
            
    // script characters (for parsing)
    private static final char SUB_SCRIPT = '_';
    private static final char SUPER_SCRIPT = '^';
    private static final char PRIME = '\'';

    protected static boolean isLoading = false;
    
    /**
     * Create a new TeXParser
     *
     * @param parseString the string to be parsed
     * @param formula the formula where to put the atoms
     * @throws ParseException if the string could not be parsed correctly
     */
    public TeXParser(String parseString, TeXFormula formula) {
	this(parseString, formula, true); 
    }
    
    /**
     * Create a new TeXParser with or without a first pass
     *
     * @param parseString the string to be parsed
     * @param firstpass a boolean to indicate if the parser must replace the user-defined macros by their content
     * @throws ParseException if the string could not be parsed correctly
     */
    public TeXParser(String parseString, TeXFormula formula, boolean firstpass) {
	this.formula = formula;
	if (parseString != null) {
	    this.parseString = new StringBuffer(parseString);
	    this.len = parseString.length();
	    this.pos = 0;
	    if (firstpass)
		firstpass();
	}
	else {
	    this.parseString = null;
	    this.pos = 0;
	    this.len = 0;
	}
    }

    /**
     * Create a new TeXParser in the context of an array. When the parser meet a & a new atom is added in the current line and when a \\ is met, a new line is created.
     *
     * @param parseString the string to be parsed
     * @param aoa an ArrayOfAtoms where to put the elements
     * @param firstpass a boolean to indicate if the parser must replace the user-defined macros by their content
     * @throws ParseException if the string could not be parsed correctly
     */
    public TeXParser(String parseString, ArrayOfAtoms aoa, boolean firstpass) {
	this(parseString, (TeXFormula)aoa, firstpass);
	arrayMode = true;
    }
    
    /**
     * Create a new TeXParser which ignores or not the white spaces, it's useful for mbox command
     *
     * @param parseString the string to be parsed
     * @param firstpass a boolean to indicate if the parser must replace the user-defined macros by their content
     * @param space a boolean to indicate if the parser must ignore or not the white space
     * @throws ParseException if the string could not be parsed correctly
     */
    public TeXParser(String parseString, TeXFormula formula, boolean firstpass, boolean space) {
	this(parseString, formula, firstpass);
	this.ignoreWhiteSpace = space;
    }

    /**
     * Reset the parser with a new latex expression
     */
    public void reset(String latex) {
	parseString = new StringBuffer(latex);
	len = parseString.length();
	formula.root = null;
	pos = 0;
	spos = 0;
	line = 0;
	col = 0;
	group = 0;
	insertion = false;
	atIsLetter = 0;
	arrayMode = false;
	ignoreWhiteSpace = true;
	firstpass();
    }

    /** Get the number of the current line 
     */
    public int getLine() {
	return line;
    }

    /** Get the number of the current column 
     */
    public int getCol() {
	return pos - col - 1;
    }

    /** Get the last atom of the current formula 
     */
    public Atom getLastAtom() {
	Atom at = formula.root;
	if (at instanceof RowAtom)
	    return ((RowAtom)at).getLastAtom();
	formula.root = null;
	return at;
    }

    /** Get the atom represented by the current formula
     */
    public Atom getFormulaAtom() {
	Atom at = formula.root;
	formula.root = null;
	return at;
    }

    /** Put an atom in the current formula 
     */
    public void addAtom(Atom at) {
	formula.add(at);
    }
    
    /** Indicate if the character @ can be used in the command's name
     */
    public void makeAtLetter() {
	atIsLetter++;
    }
    
    /** Indicate if the character @ can be used in the command's name
     */
    public void makeAtOther() {
	atIsLetter--;
    }
    
    /** Return a boolean indicating if the character @ is considered as a letter or not
     */
    public boolean isAtLetter() {
	return (atIsLetter != 0);
    }

    /** Return a boolean indicating if the parser is used to parse an array or not
     */
    public boolean isArrayMode() {
	return arrayMode;
    }

    /** Return the current position in the parsed string
     */
    public int getPos() {
	return pos;
    }

    /** Rewind the current parsed string
     * @param n the number of character to be rewinded
     * @return the new position in the parsed string
     */
    public int rewind(int n) {
	pos -= n;
	return pos;
    }
    
    /** Add a new row when the parser is in array mode
     * @throws ParseException if the parser is not in array mode
     */
    public void addRow() throws ParseException {
	if (!arrayMode)
	    throw new ParseException("You can add a row only in array mode !");
	((ArrayOfAtoms)formula).addRow();
    }

    private void firstpass() throws ParseException {
	if (len != 0) {
	    char ch;
	    String com;
	    int spos;
	    String[] args;
	    MacroInfo mac;
	    while (pos < len) {
		ch = parseString.charAt(pos);
		switch (ch) {
		case ESCAPE :
		    spos = pos;
		    com = getCommand();
		    if ("newcommand".equals(com) || "renewcommand".equals(com)) {
			args = getOptsArgs(2, 2);
			mac = MacroInfo.Commands.get(com);
			mac.invoke(this, args);
			parseString.delete(spos, pos);
			len = parseString.length();
			pos = spos;
		    } else if (NewCommandMacro.isMacro(com)) {
			mac = MacroInfo.Commands.get(com);
			args = getOptsArgs(mac.nbArgs, mac.hasOptions ? 1 : 0);
			args[0] = com;
			parseString.replace(spos, pos, (String)mac.invoke(this, args));
			len = parseString.length();
			pos = spos;
		    } else if ("makeatletter".equals(com)) 
			atIsLetter++;
		    else if ("makeatother".equals(com))
			atIsLetter--;
		    break;
		case PRIME :
		    String pr = "^{";
		    spos = pos;
		    while (pos < len && parseString.charAt(pos) == PRIME) {
			pr += "\\prime";
			pos++;
		    }
		    parseString.replace(spos, pos, pr + "}");
		    len = parseString.length();
		    break;
		default :
		    pos++;
		}
	    }
	    pos = 0;
	    len = parseString.length();
	}
    }

    /** Parse the input string
     * @throws ParseException if ann error is encountered during parsing
     */
    public void parse() throws ParseException {
        if (len != 0) {
	    char ch;
            while (pos < len) {
                ch = parseString.charAt(pos);

		switch (ch) {
		case '\n' :
		    line++;
		    col = pos;
		case '\t' :
		case '\r' :
		    pos++;
		    break;
		case ' ' :
		    pos++;
		    if (!ignoreWhiteSpace) {// We are in a mbox
			formula.add(new SpaceAtom());
			while (pos < len) {
			    ch = parseString.charAt(pos);
			    if (ch != ' ')
				break;
			    pos++;
			}
		    }
		    break;
		case ESCAPE :
		    Atom at = processEscape();
		    formula.add(at);
		    if (arrayMode && at instanceof HlineAtom) {
			((ArrayOfAtoms)formula).addRow();
		    }
		    if (insertion) 
			insertion = false;
		    break;
		case L_GROUP : 
                    formula.add(getArgument());
		    break;
                case R_GROUP :
		    group--;
		    pos++;
		    if (group == -1)
			throw new ParseException("Found a closing '" + R_GROUP
						 + "' without an opening '" + L_GROUP + "'!");
		    return;
		case SUPER_SCRIPT :
		case SUB_SCRIPT :
                    formula.add(getScripts(ch));
		    break;
		case '&' :
		    if (!arrayMode)
			throw new ParseException("Character '&' is only available in array mode !");
		    ((ArrayOfAtoms) formula).addCol();
		    pos++;
		    break;
		default :
                    formula.add(convertCharacter(ch));
		    pos++;
		}
	    }
	}
    }
    
    private Atom getScripts(char f) throws ParseException {
	pos++;
	Atom first = getArgument();
	Atom second = (Atom)null;
	char s = '\0';

	if (pos < len)  
	    s = parseString.charAt(pos);
	
	if (f == SUB_SCRIPT && s == SUB_SCRIPT) 
	    throw new ParseException("Consecutive subscripts are not allowed !");
	if (f == SUPER_SCRIPT && s == SUPER_SCRIPT) 
	    throw new ParseException("Consecutive superscripts are not allowed !");
	
	if (f == SUB_SCRIPT && s == SUPER_SCRIPT) {
	    pos++;
	    second = getArgument();
	}

	if (f == SUPER_SCRIPT && s == SUB_SCRIPT) {
	    pos++;
	    second = first;
	    first = getArgument();
	} 
	
	if (f == SUPER_SCRIPT && s != SUB_SCRIPT) {
	    second = first;
	    first = (Atom)null;
	}

	Atom at;
	if (formula.root instanceof RowAtom) {
	    at = ((RowAtom)formula.root).getLastAtom();
	} else if (formula.root == null) {
	    at = new PhantomAtom(new CharAtom('M', "mathrm"), false, true, true);
	} else {
	    at = formula.root;
	    formula.root = null;
	}
	
	if (at.getRightType() == TeXConstants.TYPE_BIG_OPERATOR)
	    return new BigOperatorAtom(at, first, second);
	else if (at instanceof OverUnderDelimiter) {
	    if (((OverUnderDelimiter)at).isOver()) {
		if (second != null) {
		    ((OverUnderDelimiter)at).addScript(second);
		    return new ScriptsAtom(at, first, null);
		}
	    } else if (first != null) {
		((OverUnderDelimiter)at).addScript(first);
		return new ScriptsAtom(at, null, second);
	    }   
	}
	
	return new ScriptsAtom(at, first, second);
    }
    
    /** Get the contents between two delimiters
     * @param open the opening character
     * @param close the closing character
     * @return the enclosed contents
     * @throws ParseException if the contents are badly enclosed
     */
    public String getGroup(char open, char close) throws ParseException {
        if (pos == len)
	    return null;

	int group, spos;
	char ch = parseString.charAt(pos);
	
        if (pos < len && ch == open) {
	    group = 1;
	    spos = pos;
	    while (pos < len - 1 && group != 0) {
		pos++;
		ch = parseString.charAt(pos);
		if (ch == open)
		    group++;
		else if (ch == close)
		    group--;
		else if (ch == ESCAPE)
		    pos++;
	    }
	    
	    if (group != 0)
		// end of string reached, but not processed properly
		throw new ParseException("Illegal end,  missing '" + close
					 + "'!");
	    pos++;
	    return parseString.substring(spos + 1, pos - 1);
	} else
	    throw new ParseException("missing '" + open + "'!");
    }

    /** Get the contents between two strings as in \begin{foo}...\end{foo}
     * @param open the opening string
     * @param close the closing string
     * @return the enclosed contents
     * @throws ParseException if the contents are badly enclosed
     */
    public String getGroup(String open, String close) throws ParseException {
	int group = 1;
	int ol = open.length(), cl = close.length();
	int oc = 0, cc = 0;
	StringBuffer buf = new StringBuffer();

	while (pos < len && group != 0) {
	    char c = parseString.charAt(pos);
	    char c1;
	    if (c == open.charAt(oc))
		oc++;
	    else
		oc = 0;
	    
	    if (c == close.charAt(cc))
		cc++;
	    else
		cc = 0;
	    
	    if (pos + 1 < len) {
		c1 = parseString.charAt(pos + 1);
		
		if (oc == ol) {
		    if ((c1 < 'a' || c1 > 'z') && (c1 < 'A' || c1 > 'Z')) {
			group++;
		    }
		    oc = 0;
		}
		    
		if (cc == cl) {
		    if ((c1 < 'a' || c1 > 'z') && (c1 < 'A' || c1 > 'Z')) {
			group--;
		    }
		    cc = 0;
		}

	    } else {
		if (oc == ol) {
		    group++;
		    oc = 0;
		}
		if (cc == cl) {
		    group--;
		    cc = 0;
		}
	    }
		
	    buf.append(c);
	    pos++;
	}
	
	if (group != 0)
	    throw new ParseException("The token " + open + " must be closed by " + close);
	
	return buf.substring(0, buf.length() - cl);
    }
    
    /** Get the argument of a command in his atomic format
     * @return the corresponding atom
     * @throws ParseException if the argument is incorrect
     */
    public Atom getArgument() throws ParseException {
	skipWhiteSpace();
	char ch = parseString.charAt(pos);
	if (ch == L_GROUP) {
	    TeXFormula tf = new TeXFormula();
	    TeXFormula sformula = this.formula;
	    this.formula = tf;
	    pos++;
	    group++;
	    parse();
	    this.formula = sformula;
	    if (this.formula.root == null) {
		RowAtom at = new RowAtom();
		at.add(tf.root);
		return at;
	    }
	    return tf.root;
	}
	
	if (ch == ESCAPE) {
	    Atom at = processEscape();
	    if (insertion) {
		insertion = false;
		return getArgument();
	    }
	    return at;
	}
	pos++;

	return convertCharacter(ch);
    }

    public String getOverArgument() throws ParseException {
	if (pos == len)
	    return null;

	int ogroup = 1, spos;
	char ch = '\0';
	
        spos = pos;
	while (pos < len && ogroup != 0) {
	    ch = parseString.charAt(pos);
	    switch (ch) {
	    case L_GROUP :
		ogroup++;
		break;
	    case '&' :
	    case R_GROUP :
		ogroup--;
		break;
	    }
	    pos++;
	}

	if (ogroup >= 2)
	    // end of string reached, but not processed properly
	    throw new ParseException("Illegal end,  missing '}' !");

	String str;
	if (ogroup == 0) {
	    str = parseString.substring(spos, pos - 1);
	} else {
	    str = parseString.substring(spos, pos);
	    ch = '\0';
	}
	
	if (ch == '&' || ch == R_GROUP) 
	    pos--;
	
	return str;
    }

    /** Convert a character in the corresponding atom in using the file TeXFormulaSettings.xml for non-alphanumeric characters
     * @param c the character to be converted
     * @return the corresponding atom
     * @throws ParseException if the character is unknown 
     */
    public Atom convertCharacter(char c) throws ParseException {
        if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
	    if (!isLoading && !DefaultTeXFont.loadedAlphabets.contains(block)) {
		DefaultTeXFont.addAlphabet(DefaultTeXFont.registeredAlphabets.get(block));
	    }
	    
            String symbolName = TeXFormula.symbolMappings[c];
	    if (symbolName == null && (TeXFormula.symbolFormulaMappings == null || TeXFormula.symbolFormulaMappings[c] == null))
                throw new ParseException("Unknown character : '"
					 + Character.toString(c) + "' (or " + ((int) c) + ")");
            else {
		if (TeXFormula.symbolFormulaMappings != null && TeXFormula.symbolFormulaMappings[c] != null)
		    return TeXFormula.symbolFormulaMappings[c];
		
		try {
                    return SymbolAtom.get(symbolName);
                } catch (SymbolNotFoundException e) {
                    throw new ParseException("The character '"
					     + Character.toString(c)
					     + "' was mapped to an unknown symbol with the name '"
					     + symbolName + "'!", e);
                }
	    }
        } else
            // alphanumeric character
            return new CharAtom(c, formula.textStyle);
    }    

    private String getCommand() {
	int spos = ++pos;
	char ch = '\0';
	
	while (pos < len) {
	    ch = parseString.charAt(pos);

	    if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (atIsLetter == 0 || ch != '@'))
		break;
	    
	    pos++;
	}
	
	if (ch == '\0')
	    throw new ParseException("An expression cannot be finished by a \\ !");
	
	if (pos == spos)
	    pos++;

	return parseString.substring(spos, pos);
    }

    
    private Atom processEscape() throws ParseException {
        spos = pos;
	String command = getCommand();

	SymbolAtom s = null;
	TeXFormula predef = null;
	
	if (MacroInfo.Commands.get(command) != null)
	    return processCommands(command);
	
	try { 
	    predef = TeXFormula.get(command);
	    return predef.root;
	} catch (FormulaNotFoundException e) { 
	    try {
		s = SymbolAtom.get(command);
		return s;
	    } catch (SymbolNotFoundException e1) {}
	}
	
	// not a valid command or symbol or predefined TeXFormula found
	throw new ParseException("Unknown symbol or command or predefined TeXFormula: '" + command + "'");
    }

    private void insert(int beg, int end, String formula) {
        parseString.replace(beg, end, formula);
	len = parseString.length();
	pos = beg;
	insertion = true;
    }

    /** Get the arguments ant the options of a command
     * @param nbArgs the number of arguments of the command
     * @param opts must be 1 if the options are found before the first argument and must be 2 if they must be found before the second argument
     * @return an array containing arguments and at the end the options are put
     */
    /* Should be ameliorated */
    public String[] getOptsArgs(int nbArgs, int opts) {
	//A maximum of 10 options can be passed to a command
	String[] args = new String[nbArgs + 10 + 1];
	if (nbArgs != 0) {	    
	    
	    //We get the options just after the command name
	    if (opts == 1) {
		int j = nbArgs + 1;
		try {
		    for (; j < nbArgs + 11; j++) {
			skipWhiteSpace();
			args[j] = getGroup(L_BRACK, R_BRACK);
		    }
		}
		catch (ParseException e) {
		    args[j] = null;
		}
	    }
	    
	    //We get the first argument
	    skipWhiteSpace();
	    try {
		args[1] = getGroup(L_GROUP, R_GROUP);
	    } catch (ParseException e) {
		if (parseString.charAt(pos) != '\\') {
		    args[1] = "" + parseString.charAt(pos);
		    pos++;
		}
		else 
		    args[1] = getCommandWithArgs(getCommand());
	    }
	    
	    //We get the options after the first argument
	    if (opts == 2) {
		int j = nbArgs + 1;
		try {
		    for (; j < nbArgs + 11; j++) {
			skipWhiteSpace();
			args[j] = getGroup(L_BRACK, R_BRACK);
		    }
		}
		catch (ParseException e) {
		    args[j] = null;
		}
	    }
	    
	    //We get the next arguments
	    for (int i = 2; i <= nbArgs; i++) {
		skipWhiteSpace();
		try {
		    args[i] = getGroup(L_GROUP, R_GROUP);
		} catch (ParseException e) {
		    if (parseString.charAt(pos) != '\\') {
			args[i] = "" + parseString.charAt(pos);
			pos++;
		    }
		    else 
			args[i] = getCommandWithArgs(getCommand());
		}
	    }
	    
	    skipWhiteSpace();
	}
	return args;
    }
    
    /**
     * return a string with command and options and args
     * @param command name of command
     * @return
     * @author Juan Enrique Escobar Robles
     */
    private String getCommandWithArgs(String command) {
	if(command.equals("left")){
	    return getGroup("\\left", "\\right");
	}
	
	MacroInfo mac = MacroInfo.Commands.get(command);
	if (mac != null) {
	    int mac_opts = 0;
	    if (mac.hasOptions) {
		mac_opts = mac.posOpts;
	    }

	    String[] mac_args = getOptsArgs(mac.nbArgs, mac_opts);
	    StringBuffer mac_arg = new StringBuffer("\\");
	    mac_arg.append(command);
	    for (int j = 0; j < mac.posOpts; j++) {
		String arg_t = mac_args[mac.nbArgs + j + 1];
		if (arg_t != null) {
		    mac_arg.append("[").append(arg_t).append("]");
		}
	    }

	    for (int j = 0; j < mac.nbArgs; j++) {
		String arg_t = mac_args[j + 1];
		if (arg_t != null) {
		    mac_arg.append("{").append(arg_t).append("}");
		}
	    }

	    return mac_arg.toString();
	}

	return "\\" + command;
    }

    /**
     * Processes the given TeX command (by parsing following command arguments
     * in the parse string).
     */
    private Atom processCommands(String command) throws ParseException {
	MacroInfo mac = MacroInfo.Commands.get(command);
	int opts = 0;
	if (mac.hasOptions) 
	    opts = mac.posOpts;
	
	String[] args = getOptsArgs(mac.nbArgs, opts);
	args[0] = command;

	if (NewCommandMacro.isMacro(command)) {
	    String ret = (String)mac.invoke(this, args);
	    insert(spos, pos, ret);
	    return null;
	}
	
	return (Atom)mac.invoke(this, args);
    }

    /** Test the validity of the name of a command. It must contains only alpha characters and eventually a @ if makeAtletter activated
     * @param com the command's name
     * @return the validity of the name 
     */
    public boolean isValidName(String com) {
	char c = '\0';
	if (com.charAt(0) == '\\') {
	    int pos = 1;
	    int len = com.length();
	    while (pos < len) {
		c = com.charAt(pos);
		if (!Character.isLetter(c) && (atIsLetter == 0 || c != '@'))
		    break;
		pos++;
	    }
	} else return false;
	return Character.isLetter(c);
    }

    private void skipWhiteSpace() {
	char c;
	while (pos < len) {
	    c = parseString.charAt(pos);
	    if (c != ' ' && c != '\t' && c != '\n' && c != '\r')
		break;
	    if (c == '\n') {
		line++;
		col = pos;
	    }
	    pos++;
	}
    }
}    
    