/* CharMapping.java
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

import java.util.Map;

public final class CharMapping {

    public static final char PRIME = '\'';
    public static final char BACKPRIME = '`';
    public static final char DEGREE = '\u00B0';
    public static final char SUPZERO = '\u2070';
    public static final char SUPONE = '\u00B9';
    public static final char SUPTWO = '\u00B2';
    public static final char SUPTHREE = '\u00B3';
    public static final char SUPFOUR = '\u2074';
    public static final char SUPFIVE = '\u2075';
    public static final char SUPSIX = '\u2076';
    public static final char SUPSEVEN = '\u2077';
    public static final char SUPEIGHT = '\u2078';
    public static final char SUPNINE = '\u2079';
    public static final char SUPPLUS = '\u207A';
    public static final char SUPMINUS = '\u207B';
    public static final char SUPEQUAL = '\u207C';
    public static final char SUPLPAR = '\u207D';
    public static final char SUPRPAR = '\u207E';
    public static final char SUPN = '\u207F';
    public static final char SUBZERO = '\u2080';
    public static final char SUBONE = '\u2081';
    public static final char SUBTWO = '\u2082';
    public static final char SUBTHREE = '\u2083';
    public static final char SUBFOUR = '\u2084';
    public static final char SUBFIVE = '\u2085';
    public static final char SUBSIX = '\u2086';
    public static final char SUBSEVEN = '\u2087';
    public static final char SUBEIGHT = '\u2088';
    public static final char SUBNINE = '\u2089';
    public static final char SUBPLUS = '\u208A';
    public static final char SUBMINUS = '\u208B';
    public static final char SUBEQUAL = '\u208C';
    public static final char SUBLPAR = '\u208D';
    public static final char SUBRPAR = '\u208E';

    public interface Mapping {
        public void map(TeXParser tp, boolean mathMode);
    }

    public static final class SymbolMapping implements Mapping {

        final char c;
        final SymbolAtom sym;
        final String text;
        SymbolAtom textSym;

        public SymbolMapping(final char c, final SymbolAtom sym, final String text) {
            this.c = c;
            this.sym = sym;
            this.sym.setUnicode(c);
            this.textSym = text != null ? SymbolAtom.get(text, false) : null;
            this.text = textSym == null ? text : null;
        }

        public SymbolMapping(final char c, final SymbolAtom sym) {
            this(c, sym, null);
        }

        public SymbolMapping(final char c, final String sym, final String text) {
            this(c, SymbolAtom.get(sym), text);
        }

        public SymbolMapping(final char c, final String sym) {
            this(c, SymbolAtom.get(sym), null);
        }

        public void map(TeXParser tp, boolean mathMode) {
            tp.addToConsumer(get(mathMode));
        }

        public SymbolAtom get(final boolean mathMode) {
            if (!mathMode) {
                if (textSym != null) {
                    return textSym;
                } else if (text == null) {
                    textSym = sym.toTextMode();
                    return textSym;
                }
                // we've an unexisting symbol so try to load it via an alphabet
                UnicodeMapping.get(c);
                textSym = SymbolAtom.get(text, false);
                return textSym;
            }
            return sym;
        }

        public SymbolAtom get(TeXParser tp) {
            return get(tp.isMathMode());
        }
    }

    public static final class FormulaMapping implements Mapping {

        final String sym;
        final String text;
        SymbolAtom textSym;

        public FormulaMapping(final String sym, final String text) {
            this.sym = sym;
            this.text = text;
        }

        public FormulaMapping(final String sym) {
            this(sym, null);
        }

        public void map(TeXParser tp, boolean mathMode) {
            if (!mathMode) {
                if (text == null) {
                    tp.addString(sym);
                } else {
                    if (textSym == null) {
                        // We expected to have a symbol and we don't have one...
                        UnicodeMapping.get(text.charAt(0));
                        textSym = SymbolAtom.get(text, false);
                    }
                    tp.addToConsumer(textSym);
                }
            } else {
                tp.addString(sym);
            }
        }
    }

    public static final class SubSupMapping implements Mapping {

        final char c;
        final boolean sup;

        public SubSupMapping(final char c, final boolean sup) {
            this.c = c;
            this.sup = sup;
        }

        public void map(final TeXParser tp, final boolean mathMode) {
            if (sup) {
                tp.cumSupSymbols(new CharAtom(c, tp.isMathMode()));
            } else {
                tp.cumSubSymbols(new CharAtom(c, tp.isMathMode()));
            }
        }
    }

    private final static CharMapping defaultMappings = new CharMapping();

    private final Mapping[] mapToSym;
    // for unicode after 0xFFFF
    private Map<Integer, Mapping> mapToSymExtra;

    private CharMapping() {
        mapToSym = new Mapping[65536];
        Configuration.get(); // just to load everything
        initMappings();
        initCyrillic();
        initGreek();
    }

    public CharMapping(Mapping[] mapToSym) {
        this.mapToSym = mapToSym;
    }

    public CharMapping(CharMapping cm) {
        mapToSym = new Mapping[65536];
        System.arraycopy(cm.mapToSym, 0, mapToSym, 0, cm.mapToSym.length);
    }

    public static CharMapping getDefault() {
        return defaultMappings;
    }

    public void put(final char c, final Mapping m) {
        mapToSym[c] = m;
    }

    public void putSym(final char c, final String s) {
        mapToSym[c] = new SymbolMapping(c, s);
    }

    public void putForm(final char c, final String s) {
        mapToSym[c] = new FormulaMapping(s);
    }

    public boolean replace(final char c, final TeXParser tp) {
        return replace(c, tp, tp.isMathMode());
    }

    public boolean replace(final int c, final TeXParser tp) {
        return replace(c, tp, tp.isMathMode());
    }

    public boolean replace(final char c, final TeXParser tp, final boolean mathMode) {
        final Mapping m = mapToSym[c];
        if (m != null) {
            m.map(tp, mathMode);
            return true;
        }

        return false;
    }

    public boolean replace(final int c, final TeXParser tp, final boolean mathMode) {
        if (mapToSymExtra != null) {
            final Mapping m = mapToSymExtra.get(c);
            if (m != null) {
                m.map(tp, mathMode);
                return true;
            }
        }
        return false;
    }

    public boolean hasMapping(final char c) {
        return mapToSym[c] != null;
    }

    public Atom getAtom(final char c, final boolean mathMode) {
        final TeXParser tp = new TeXParser(true);
        final SingleAtomConsumer cons = new SingleAtomConsumer();
        tp.addConsumer(cons);
        if (replace(c, tp, mathMode)) {
            tp.parse();
            return cons.get();
        }
        return null;
    }

    public Atom getAtom(final int c, final boolean mathMode) {
        final TeXParser tp = new TeXParser(true);
        final SingleAtomConsumer cons = new SingleAtomConsumer();
        tp.addConsumer(cons);
        if (replace(c, tp, mathMode)) {
            tp.parse();
            return cons.get();
        }
        return null;
    }

    public void replaceUnsafe(final char c, final TeXParser tp) {
        mapToSym[c].map(tp, tp.isMathMode());
    }

    public SymbolAtom get(final char c, final TeXParser tp) {
        return ((SymbolMapping) mapToSym[c]).get(tp);
    }

    // Some mapping char2com don't exists
    // for example U02269-0FE00 => gvertneqq
    // https://www.w3.org/TR/MathML2/bycodes.html

    private void initMappings() {
        put('°', new Mapping() {
            public void map(final TeXParser tp, final boolean mathMode) {
                tp.cumSupSymbols(Symbols.CIRC);
            }
        });
        put(SUPZERO, new SubSupMapping('0', true));
        put(SUPONE, new SubSupMapping('1', true));
        put(SUPTWO, new SubSupMapping('2', true));
        put(SUPTHREE, new SubSupMapping('3', true));
        put(SUPFOUR, new SubSupMapping('4', true));
        put(SUPFIVE, new SubSupMapping('5', true));
        put(SUPSIX, new SubSupMapping('6', true));
        put(SUPSEVEN, new SubSupMapping('7', true));
        put(SUPEIGHT, new SubSupMapping('8', true));
        put(SUPNINE, new SubSupMapping('9', true));
        put(SUPPLUS, new SubSupMapping('+', true));
        put(SUPMINUS, new SubSupMapping('-', true));
        put(SUPEQUAL, new SubSupMapping('=', true));
        put(SUPLPAR, new SubSupMapping('(', true));
        put(SUPRPAR, new SubSupMapping(')', true));
        put(SUPN, new SubSupMapping('n', true));
        put(SUBZERO, new SubSupMapping('0', false));
        put(SUBONE, new SubSupMapping('1', false));
        put(SUBTWO, new SubSupMapping('2', false));
        put(SUBTHREE, new SubSupMapping('3', false));
        put(SUBFOUR, new SubSupMapping('4', false));
        put(SUBFIVE, new SubSupMapping('5', false));
        put(SUBSIX, new SubSupMapping('6', false));
        put(SUBSEVEN, new SubSupMapping('7', false));
        put(SUBEIGHT, new SubSupMapping('8', false));
        put(SUBNINE, new SubSupMapping('9', false));
        put(SUBPLUS, new SubSupMapping('+', false));
        put(SUBMINUS, new SubSupMapping('-', false));
        put(SUBEQUAL, new SubSupMapping('=', false));
        put(SUBLPAR, new SubSupMapping('(', false));
        put(SUBRPAR, new SubSupMapping(')', false));
        put('@', new SymbolMapping('@', "@"));
        put('+', new SymbolMapping('+', "plus"));
        put('-', new SymbolMapping('-', "minus", "textminus"));
        put('/', new SymbolMapping('/', "slash", "textfractionsolidus"));
        put('*', new SymbolMapping('*', "ast"));
        put('(', new SymbolMapping('(', "lbrack"));
        put('.', new SymbolMapping('.', Symbols.NORMALDOT, "textnormaldot"));
        put(';', new SymbolMapping(';', "semicolon"));
        put('>', new SymbolMapping('>', "gt"));
        put('{', new SymbolMapping('{', "lbrace"));
        put('!', new SymbolMapping('!', "faculty"));
        put(')', new SymbolMapping(')', "rbrack"));
        put(',', new SymbolMapping(',', "comma"));
        put('<', new SymbolMapping('<', "lt"));
        put('?', new SymbolMapping('?', "question"));
        put(']', new SymbolMapping(']', "rsqbrack"));
        put('|', new SymbolMapping('|', "vert"));
        put(':', new SymbolMapping(':', "colon"));
        put('=', new SymbolMapping('=', "equals"));
        put('[', new SymbolMapping('[', "lsqbrack"));
        put('}', new SymbolMapping('}', "rbrace"));
        put('\'', new SymbolMapping('\'', "textapos"));
        put('`', new SymbolMapping('`', "jlatexmathlapos"));
        put('#', new SymbolMapping('#', "#"));
        put('£', new SymbolMapping('£', "mathsterling"));
        put('¥', new SymbolMapping('¥', "yen"));
        put('§', new SymbolMapping('§', "S"));
        put('«', new SymbolMapping('«', "guillemotleft"));
        put('®', new SymbolMapping('®', "textregistered"));
        put('µ', new SymbolMapping('µ', "textmu"));
        put('¶', new SymbolMapping('¶', "P"));
        put('»', new SymbolMapping('»', "guillemotright"));
        put('¿', new SymbolMapping('¿', "questiondown"));
        put('α', new SymbolMapping('α', "alpha", "α"));
        put('β', new SymbolMapping('β', "beta", "β"));
        put('γ', new SymbolMapping('γ', "gamma", "γ"));
        put('δ', new SymbolMapping('δ', "delta", "δ"));
        put('ε', new SymbolMapping('ε', "varepsilon", "ε"));
        put('ζ', new SymbolMapping('ζ', "zeta", "ζ"));
        put('η', new SymbolMapping('η', "eta", "η"));
        put('θ', new SymbolMapping('θ', "theta", "θ"));
        put('ι', new SymbolMapping('ι', "iota", "ι"));
        put('κ', new SymbolMapping('κ', "kappa", "κ"));
        put('λ', new SymbolMapping('λ', "lambda", "λ"));
        put('μ', new SymbolMapping('μ', "mu", "μ"));
        put('ν', new SymbolMapping('ν', "nu", "ν"));
        put('ξ', new SymbolMapping('ξ', "xi", "ξ"));
        put('ο', new SymbolMapping('ο', "omicron", "ο"));
        put('π', new SymbolMapping('π', "pi", "π"));
        put('ρ', new SymbolMapping('ρ', "rho", "ρ"));
        put('ς', new SymbolMapping('ς', "varsigma", "ς"));
        put('σ', new SymbolMapping('σ', "sigma", "σ"));
        put('τ', new SymbolMapping('τ', "tau", "τ"));
        put('υ', new SymbolMapping('υ', "upsilon", "υ"));
        put('φ', new SymbolMapping('φ', "varphi", "φ"));
        put('χ', new SymbolMapping('χ', "chi", "χ"));
        put('ψ', new SymbolMapping('ψ', "psi", "ψ"));
        put('ω', new SymbolMapping('ω', "omega", "ω"));
        put('ϑ', new SymbolMapping('ϑ', "vartheta", "ϑ"));
        put('ϕ', new SymbolMapping('ϕ', "phi"));
        put('ϖ', new SymbolMapping('ϖ', "varpi"));
        put('ϰ', new SymbolMapping('ϰ', "varkappa"));
        put('ϱ', new SymbolMapping('ϱ', "varrho"));
        put(' ', new FormulaMapping("\\ "));
        put('¡', new FormulaMapping("{!`}"));
        put('©', new FormulaMapping("\\copyright"));
        put('À', new FormulaMapping("\\`A"));
        put('Á', new FormulaMapping("\\\'A"));
        put('Â', new FormulaMapping("\\^A"));
        put('Ã', new FormulaMapping("\\~A"));
        put('Ä', new FormulaMapping("\\\"A"));
        put('Å', new FormulaMapping("\\r A"));
        put('Æ', new SymbolMapping('Æ', "AE"));
        put('Ç', new FormulaMapping("\\c C"));
        put('È', new FormulaMapping("\\`E"));
        put('É', new FormulaMapping("\\\'E"));
        put('Ê', new FormulaMapping("\\^E"));
        put('Ë', new FormulaMapping("\\\"E"));
        put('Ì', new FormulaMapping("\\`I"));
        put('Í', new FormulaMapping("\\\'I"));
        put('Î', new FormulaMapping("\\^I"));
        put('Ï', new FormulaMapping("\\\"I"));
        put('Ñ', new FormulaMapping("\\~N"));
        put('Ò', new FormulaMapping("\\`O"));
        put('Ó', new FormulaMapping("\\\'O"));
        put('Ô', new FormulaMapping("\\^O"));
        put('Õ', new FormulaMapping("\\~O"));
        put('Ö', new FormulaMapping("\\\"O"));
        put('Ø', new SymbolMapping('Ø', "O"));
        put('Ù', new FormulaMapping("\\`U"));
        put('Ú', new FormulaMapping("\\\'U"));
        put('Û', new FormulaMapping("\\^U"));
        put('Ü', new FormulaMapping("\\\"U"));
        put('Ý', new FormulaMapping("\\\'Y"));
        put('ß', new SymbolMapping('ß', "ss"));
        put('à', new FormulaMapping("\\`a"));
        put('á', new FormulaMapping("\\\'a"));
        put('â', new FormulaMapping("\\^a"));
        put('ã', new FormulaMapping("\\~a"));
        put('ä', new FormulaMapping("\\\"a"));
        put('å', new FormulaMapping("\\aa"));
        put('æ', new SymbolMapping('æ', "ae"));
        put('ç', new FormulaMapping("\\c c"));
        put('è', new FormulaMapping("\\`e"));
        put('é', new FormulaMapping("\\\'e"));
        put('ê', new FormulaMapping("\\^e"));
        put('ë', new FormulaMapping("\\\"e"));
        put('ì', new FormulaMapping("\\`\\i"));
        put('í', new FormulaMapping("\\\'\\i"));
        put('î', new FormulaMapping("\\^\\i"));
        put('ï', new FormulaMapping("\\\"\\i"));
        put('ð', new SymbolMapping('ð', "eth"));
        put('ñ', new FormulaMapping("\\~n"));
        put('ò', new FormulaMapping("\\`o"));
        put('ó', new FormulaMapping("\\\'o"));
        put('ô', new FormulaMapping("\\^o"));
        put('õ', new FormulaMapping("\\~o"));
        put('ö', new FormulaMapping("\\\"o"));
        put('÷', new SymbolMapping('÷', "div"));
        put('ø', new SymbolMapping('ø', "o"));
        put('ù', new FormulaMapping("\\`u"));
        put('ú', new FormulaMapping("\\\'u"));
        put('û', new FormulaMapping("\\^u"));
        put('ü', new FormulaMapping("\\\"u"));
        put('ý', new FormulaMapping("\\\'y"));
        put('ÿ', new FormulaMapping("\\\"y"));
        put('Ā', new FormulaMapping("\\=A"));
        put('ā', new FormulaMapping("\\=a"));
        put('Ă', new FormulaMapping("\\u A"));
        put('ă', new FormulaMapping("\\u a"));
        put('Ą', new FormulaMapping("\\k A"));
        put('ą', new FormulaMapping("\\k a"));
        put('Ć', new FormulaMapping("\\\'C"));
        put('ć', new FormulaMapping("\\\'c"));
        put('Ĉ', new FormulaMapping("\\^C"));
        put('ĉ', new FormulaMapping("\\^c"));
        put('Ċ', new FormulaMapping("\\.C"));
        put('ċ', new FormulaMapping("\\.c"));
        put('Č', new FormulaMapping("\\v C"));
        put('č', new FormulaMapping("\\v c"));
        put('Ď', new FormulaMapping("\\v D"));
        put('ď', new FormulaMapping("{d\\text{\'}}"));
        put('Đ', new FormulaMapping("\\Dstrok"));
        put('đ', new FormulaMapping("\\dstrok"));
        put('Ē', new FormulaMapping("\\=E"));
        put('ē', new FormulaMapping("\\=e"));
        put('Ĕ', new FormulaMapping("\\u E"));
        put('ĕ', new FormulaMapping("\\u e"));
        put('Ė', new FormulaMapping("\\.E"));
        put('ė', new FormulaMapping("\\.e"));
        put('Ę', new FormulaMapping("\\k E"));
        put('ę', new FormulaMapping("\\k e"));
        put('Ě', new FormulaMapping("\\v E"));
        put('ě', new FormulaMapping("\\v e"));
        put('Ĝ', new FormulaMapping("\\^G"));
        put('ĝ', new FormulaMapping("\\^g"));
        put('Ğ', new FormulaMapping("\\u G"));
        put('ğ', new FormulaMapping("\\u g"));
        put('Ġ', new FormulaMapping("\\.G"));
        put('ġ', new FormulaMapping("\\.g"));
        put('Ģ', new FormulaMapping("\\underaccent{,}G"));
        put('ģ', new FormulaMapping("\\\'g"));
        put('Ĥ', new FormulaMapping("\\^H"));
        put('ĥ', new FormulaMapping("\\^h"));
        put('Ħ', new FormulaMapping("\\Hstrok"));
        put('ħ', new FormulaMapping("\\hstrok"));
        put('Ĩ', new FormulaMapping("\\~I"));
        put('ĩ', new FormulaMapping("\\~\\i"));
        put('Ī', new FormulaMapping("\\=I"));
        put('ī', new FormulaMapping("\\=\\i"));
        put('Ĭ', new FormulaMapping("\\u I"));
        put('ĭ', new FormulaMapping("\\u \\i"));
        put('Į', new FormulaMapping("\\k I"));
        put('į', new FormulaMapping("\\k i"));
        put('İ', new FormulaMapping("\\.I"));
        put('ı', new SymbolMapping('ı', "i"));
        put('Ĳ', new FormulaMapping("\\IJ"));
        put('ĳ', new FormulaMapping("\\ij"));
        put('Ĵ', new FormulaMapping("\\^J"));
        put('ĵ', new FormulaMapping("\\^\\j"));
        put('Ķ', new FormulaMapping("\\underaccent{,}K"));
        put('ķ', new FormulaMapping("\\underaccent{,}k"));
        put('Ĺ', new FormulaMapping("\\\'L"));
        put('ĺ', new FormulaMapping("\\\'l"));
        put('Ļ', new FormulaMapping("\\underaccent{,}L"));
        put('ļ', new FormulaMapping("\\underaccent{,}l"));
        put('Ľ', new FormulaMapping("\\Lcaron"));
        put('ľ', new FormulaMapping("\\lcaron"));
        put('Ŀ', new FormulaMapping("L\\cdot"));
        put('ŀ', new FormulaMapping("l\\cdot"));
        put('Ł', new FormulaMapping("\\L"));
        put('ł', new FormulaMapping("\\l"));
        put('Ń', new FormulaMapping("\\\'N"));
        put('ń', new FormulaMapping("\\\'n"));
        put('Ņ', new FormulaMapping("\\underaccent{,}N"));
        put('ņ', new FormulaMapping("\\underaccent{,}n"));
        put('Ň', new FormulaMapping("\\v N"));
        put('ň', new FormulaMapping("\\v n"));
        put('ŉ', new FormulaMapping("{\\text{\'}n}"));
        put('Ō', new FormulaMapping("\\=O"));
        put('ō', new FormulaMapping("\\=o"));
        put('Ŏ', new FormulaMapping("\\u O"));
        put('ŏ', new FormulaMapping("\\u o"));
        put('Ő', new FormulaMapping("\\H O"));
        put('ő', new FormulaMapping("\\H o"));
        put('Œ', new SymbolMapping('Œ', "OE"));
        put('œ', new SymbolMapping('œ', "oe"));
        put('Ŕ', new FormulaMapping("\\\'R"));
        put('ŕ', new FormulaMapping("\\\'r"));
        put('Ŗ', new FormulaMapping("\\underaccent{,}R"));
        put('ŗ', new FormulaMapping("\\underaccent{,}r"));
        put('Ř', new FormulaMapping("\\v R"));
        put('ř', new FormulaMapping("\\v r"));
        put('Ś', new FormulaMapping("\\\'S"));
        put('ś', new FormulaMapping("\\\'s"));
        put('Ŝ', new FormulaMapping("\\^S"));
        put('ŝ', new FormulaMapping("\\^s"));
        put('Ş', new FormulaMapping("\\c S"));
        put('ş', new FormulaMapping("\\c s"));
        put('Š', new FormulaMapping("\\v S"));
        put('š', new FormulaMapping("\\v s"));
        put('Ţ', new FormulaMapping("\\c T"));
        put('ţ', new FormulaMapping("\\c t"));
        put('Ť', new FormulaMapping("\\v T"));
        put('ť', new FormulaMapping("\\tcaron"));
        put('Ŧ', new FormulaMapping("\\TStroke"));
        put('ŧ', new FormulaMapping("\\tStroke"));
        put('Ũ', new FormulaMapping("\\~U"));
        put('ũ', new FormulaMapping("\\~u"));
        put('Ū', new FormulaMapping("\\=U"));
        put('ū', new FormulaMapping("\\=u"));
        put('Ŭ', new FormulaMapping("\\u U"));
        put('ŭ', new FormulaMapping("\\u u"));
        put('Ů', new FormulaMapping("\\r U"));
        put('ů', new FormulaMapping("\\r u"));
        put('Ű', new FormulaMapping("\\H U"));
        put('ű', new FormulaMapping("\\H u"));
        put('Ų', new FormulaMapping("\\k U"));
        put('ų', new FormulaMapping("\\k u"));
        put('Ŵ', new FormulaMapping("\\^W"));
        put('ŵ', new FormulaMapping("\\^w"));
        put('Ŷ', new FormulaMapping("\\^Y"));
        put('ŷ', new FormulaMapping("\\^y"));
        put('Ÿ', new FormulaMapping("\\\"Y"));
        put('Ź', new FormulaMapping("\\\'Z"));
        put('ź', new FormulaMapping("\\\'z"));
        put('Ż', new FormulaMapping("\\.Z"));
        put('ż', new FormulaMapping("\\.z"));
        put('Ž', new FormulaMapping("\\v Z"));
        put('ž', new FormulaMapping("\\v z"));
        put('Α', new FormulaMapping("\\Alpha", "Α"));
        put('Β', new FormulaMapping("\\Beta", "Β"));
        put('Γ', new FormulaMapping("\\Gamma", "Γ"));
        put('Δ', new FormulaMapping("\\Delta", "Δ"));
        put('Ε', new FormulaMapping("\\Epsilon", "Ε"));
        put('Ζ', new FormulaMapping("\\Zeta", "Ζ"));
        put('Η', new FormulaMapping("\\Eta", "Η"));
        put('Θ', new FormulaMapping("\\Theta", "Θ"));
        put('Ι', new FormulaMapping("\\Iota", "Ι"));
        put('Κ', new FormulaMapping("\\Kappa", "Κ"));
        put('Λ', new FormulaMapping("\\Lambda", "Λ"));
        put('Μ', new FormulaMapping("\\Mu", "Μ"));
        put('Ν', new FormulaMapping("\\Nu", "Ν"));
        put('Ξ', new FormulaMapping("\\Xi", "Ξ"));
        put('Ο', new FormulaMapping("\\Omicron", "Ο"));
        put('Π', new FormulaMapping("\\Pi", "Π"));
        put('Ρ', new FormulaMapping("\\Rho", "Ρ"));
        put('Σ', new FormulaMapping("\\Sigma", "Σ"));
        put('Τ', new FormulaMapping("\\Tau", "Τ"));
        put('Υ', new FormulaMapping("\\Upsilon", "Υ"));
        put('Φ', new FormulaMapping("\\Phi", "Φ"));
        put('Χ', new FormulaMapping("\\Chi", "Χ"));
        put('Ψ', new FormulaMapping("\\Psi", "Ψ"));
        put('Ω', new FormulaMapping("\\Omega", "Ω"));
        put('∦', new SymbolMapping('∦', "nshortparallel"));
        put('⇋', new SymbolMapping('⇋', "leftrightharpoons"));
        put('⇛', new SymbolMapping('⇛', "Rrightarrow"));
        put('⫌', new SymbolMapping('⫌', "supsetneqq"));
        put('≥', new SymbolMapping('≥', "ge"));
        put('⊳', new SymbolMapping('⊳', "rhd"));
        put('⩽', new SymbolMapping('⩽', "leqslant"));
        put('⇊', new SymbolMapping('⇊',"downdownarrows"));
        put('≳', new SymbolMapping('≳', "gtrsim"));
        put('≶', new SymbolMapping('≶', "lessgtr"));
        put('⟶', new FormulaMapping("\\longrightarrow"));
        put('⇔', new SymbolMapping('⇔', "Leftrightarrow"));
        put('←', new SymbolMapping('←', "leftarrow"));
        put('⊖', new SymbolMapping('⊖', "ominus"));
        put('​', new FormulaMapping("\\!"));
        put('⊗', new SymbolMapping('⊗', "otimes"));
        put('⌈', new SymbolMapping('⌈', "lceil"));
        put('∂', new SymbolMapping('∂', "partial"));
        put('⌢', new SymbolMapping('⌢', "smallfrown"));
        put(' ', new FormulaMapping("\\,"));
        put('⪢', new SymbolMapping('⪢', "gg"));
        put('⪤', new FormulaMapping("\\glj"));
        put('⪥', new FormulaMapping("\\gla"));
        put('⪦', new SymbolMapping('⪦', "leftslice"));
        put('⪧', new SymbolMapping('⪧', "rightslice"));
        put(' ', new FormulaMapping("\\;"));
        put(' ', new FormulaMapping("\\:"));
        put('↙', new SymbolMapping('↙', "swarrow"));
        put('≩', new SymbolMapping('≩', "gneqq"));
        put('≠', new FormulaMapping("\\neq"));
        put('×', new SymbolMapping('×', "times"));
        put('¬', new SymbolMapping('¬', "lnot"));
        put('↑', new SymbolMapping('↑', "uparrow"));
        put('⋎', new SymbolMapping('⋎', "curlyvee"));
        put('∖', new SymbolMapping('∖', "setminus"));
        put('∗', new FormulaMapping("{{}_\\ast}"));
        put('⊋', new SymbolMapping('⊋', "supsetneq"));
        put('↚', new SymbolMapping('↚', "nleftarrow"));
        put('⇎', new SymbolMapping('⇎', "nLeftrightarrow"));
        put('▾', new SymbolMapping('▾', "blacktriangledown"));
        put('⇂', new SymbolMapping('⇂', "downharpoonright"));
        put('⫅', new SymbolMapping('⫅', "subseteqq"));
        put('∃', new SymbolMapping('∃', "exists"));
        put('∅', new SymbolMapping('∅', "emptyset"));
        put('⋙', new SymbolMapping('⋙', "ggg"));
        put('∼', new SymbolMapping('∼', "sim"));
        put('≨', new SymbolMapping('≨', "lvertneqq"));
        put('‖', new SymbolMapping('‖', "|"));
        put('∨', new SymbolMapping('∨', "vee"));
        put('∙', new SymbolMapping('∙', "bullet"));
        put('⫋', new SymbolMapping('⫋', "subsetneqq"));
        put('▿', new SymbolMapping('▿', "triangledown"));
        put('≃', new SymbolMapping('≃', "simeq"));
        put('⇉', new SymbolMapping('⇉', "rightrightarrows"));
        put('▽', new SymbolMapping('▽', "bigtriangledown"));
        put('≰', new SymbolMapping('≰', "nleqslant"));
        put('↱', new SymbolMapping('↱', "Rsh"));
        put('↘', new SymbolMapping('↘', "searrow"));
        put('◀', new SymbolMapping('◀', "blacktriangleleft"));
        put('⋭', new SymbolMapping('⋭', "ntrianglerighteq"));
        put('↔', new SymbolMapping('↔', "leftrightarrow"));
        put('⌅', new SymbolMapping('⌅', "barwedge"));
        put('⋫', new SymbolMapping('⋫', "ntriangleright"));
        put('⋊', new SymbolMapping('⋊', "rtimes"));
        put('⊝', new SymbolMapping('⊝', "circleddash"));
        put('⋓', new SymbolMapping('⋓', "Cup"));
        put('⇈', new SymbolMapping('⇈', "upuparrows"));
        put('≯', new SymbolMapping('≯', "ngtr"));
        put('⋩', new SymbolMapping('⋩', "succnsim"));
        put('⩴', new FormulaMapping("\\coloncolonequals"));
        put('⪉', new SymbolMapping('⪉', "lnapprox"));
        put('⋗', new SymbolMapping('⋗', "gtrdot"));
        put('…', new FormulaMapping("\\ldots"));
        put('≈', new SymbolMapping('≈', "approx"));
        put('⪅', new SymbolMapping('⪅', "lessapprox"));
        put('⋏', new SymbolMapping('⋏', "curlywedge"));
        put('≇', new SymbolMapping('≇', "ncong"));
        put('≏', new SymbolMapping('≏', "bumpeq"));
        put('⟸', new FormulaMapping("\\Longleftarrow"));
        put('∥', new SymbolMapping('∥', "parallel"));
        put(' ', new FormulaMapping("\\thinspace"));
        put('〉', new SymbolMapping('〉', "rangle"));
        put('⋂', new SymbolMapping('⋂', "bigcap"));
        put('♣', new SymbolMapping('♣', "clubsuit"));
        put('⊃', new SymbolMapping('⊃', "supset"));
        put('⊄', new FormulaMapping("{\\not\\subset}"));
        put('⊅', new FormulaMapping("{\\not\\supset}"));
        put('⊘', new SymbolMapping('⊘', "oslash"));
        put('∴', new SymbolMapping('∴', "therefore"));
        put('∶', new FormulaMapping("\\ratio"));
        put('∷', new FormulaMapping("\\mathbin{\\ratio\\ratio}"));
        put('⪸', new SymbolMapping('⪸', "succapprox"));
        put('⋛', new SymbolMapping('⋛', "gtreqless"));
        put('≎', new SymbolMapping('≎', "Bumpeq"));
        put('⇕', new SymbolMapping('⇕', "Updownarrow"));
        put('϶', new SymbolMapping('϶', "backepsilon"));
        put('∪', new SymbolMapping('∪', "cup"));
        put('⟺', new FormulaMapping("\\Longleftrightarrow"));
        put('⪯', new SymbolMapping('⪯', "preceq"));
        put('⊇', new SymbolMapping('⊇', "supseteq"));
        put('≱', new SymbolMapping('≱', "ngeqslant"));
        put('†', new SymbolMapping('†', "dagger"));
        put('⨿', new SymbolMapping('⨿', "amalg"));
        put('⪋', new SymbolMapping('⪋', "lesseqqgtr"));
        put('≦', new SymbolMapping('≦', "leqq"));
        put('≗', new SymbolMapping('≗', "circeq"));
        put('≘', new FormulaMapping("\\frowneq"));
        put('≙', new FormulaMapping("\\stackrel{\\wedge}{=}"));
        put('≚', new FormulaMapping("\\stackrel{\\vee}{=}"));
        put('≛', new FormulaMapping("\\stackrel{\\scalebox{0.8}{\\bigstar}}{=}"));
        put('≜', new SymbolMapping('≜', "triangleq"));
        put('≝', new FormulaMapping("\\stackrel{\\scalebox{0.75}{\\mathrm{def}}}{=}"));
        put('≞', new FormulaMapping("\\stackrel{\\scalebox{0.75}{\\mathrm{m}}}{=}"));
        put('≟', new FormulaMapping("\\questeq"));
        put('↼', new SymbolMapping('↼', "leftharpoonup"));
        put('⊚', new SymbolMapping('⊚', "circledcirc"));
        put('↖', new SymbolMapping('↖', "nwarrow"));
        put('⋦', new SymbolMapping('⋦', "lnsim"));
        put('⌣', new SymbolMapping('⌣', "smallsmile"));
        put('⊙', new SymbolMapping('⊙', "odot"));
        put('∤', new SymbolMapping('∤', "nmid"));
        put('⋖', new SymbolMapping('⋖', "lessdot"));
        put('′', new SymbolMapping('′', "prime"));
        put('±', new SymbolMapping('±', "pm"));
        put('⊒', new SymbolMapping('⊒', "sqsupseteq"));
        put('≐', new FormulaMapping("\\doteq"));
        put('≪', new SymbolMapping('≪', "ll"));
        put('≧', new SymbolMapping('≧', "geqq"));
        put('♡', new SymbolMapping('♡', "heartsuit"));
        put('↾', new SymbolMapping('↾', "upharpoonright"));
        put('⇆', new SymbolMapping('⇆', "leftrightarrows"));
        put('⋌', new SymbolMapping('⋌', "rightthreetimes"));
        put('∓', new SymbolMapping('∓', "mp"));
        put('⋮', new FormulaMapping("\\vdots"));
        put('⋪', new SymbolMapping('⋪', "ntriangleleft"));
        put('⋆', new SymbolMapping('⋆', "star"));
        put('∹', new FormulaMapping("\\minuscolon"));
        put('∺', new FormulaMapping("\\geoprop"));
        put('▪', new SymbolMapping('▪', "blacksquare"));
        put('▮', new FormulaMapping("\\marker"));
        put('▵', new SymbolMapping('▵', "triangle"));
        put('⨄', new SymbolMapping('⨄', "biguplus"));
        put('⪺', new SymbolMapping('⪺', "succnapprox"));
        put('⪊', new SymbolMapping('⪊', "gnapprox"));
        put('□', new SymbolMapping('□', "square"));
        put('∍', new SymbolMapping('∍', "ni"));
        put('⋰', new FormulaMapping("\\iddots"));
        put('⋱', new FormulaMapping("\\ddots"));
        put('⋴', new SymbolMapping('⋴', "inplus"));
        put('⊬', new SymbolMapping('⊬', "nvdash"));
        put('∐', new SymbolMapping('∐', "coprod"));
        put('∣', new SymbolMapping('∣', "shortmid"));
        put('↩', new FormulaMapping("\\hookleftarrow"));
        put('⇏', new SymbolMapping('⇏', "nRightarrow"));
        put('↿', new SymbolMapping('↿', "upharpoonleft"));
        put('⊡', new SymbolMapping('⊡', "boxdot"));
        put('⊉', new SymbolMapping('⊉', "nsupseteq"));
        put('∈', new SymbolMapping('∈', "in"));
        put('∉', new FormulaMapping("\\notin"));
        put('⋉', new SymbolMapping('⋉', "ltimes"));
        put('≻', new SymbolMapping('≻', "succ"));
        put('−', new SymbolMapping('−', "minus"));
        put('⪰', new SymbolMapping('⪰', "succeq"));
        put('∫', new FormulaMapping("\\int"));
        put('♭', new SymbolMapping('♭', "flat"));
        put('⅁', new SymbolMapping('⅁', "Game"));
        put('⅋', new FormulaMapping("\\parr"));
        put('≽', new SymbolMapping('≽', "succcurlyeq"));
        put('〈', new SymbolMapping('〈', "langle"));
        put('⋅', new SymbolMapping('⋅', "cdot"));
        put('⊔', new SymbolMapping('⊔', "sqcup"));
        put('ℷ', new SymbolMapping('ℷ', "gimel"));
        put('⊛', new SymbolMapping('⊛', "circledast"));
        put('⪕', new SymbolMapping('⪕', "eqslantless"));
        put('≤', new SymbolMapping('≤', "le"));
        put('≷', new SymbolMapping('≷', "gtrless"));
        put('∝', new SymbolMapping('∝', "propto"));
        put('◁', new SymbolMapping('◁', "triangleleft"));
        put('▴', new SymbolMapping('▴', "blacktriangle"));
        put('ℑ', new SymbolMapping('ℑ', "Im"));
        put('⨁', new SymbolMapping('⨁', "bigoplus"));
        put('⨌', new FormulaMapping("\\iiiint"));
        put('∽', new SymbolMapping('∽', "backsim"));
        put('∞', new SymbolMapping('∞', "infty"));
        put('⊂', new SymbolMapping('⊂', "subset"));
        put('⋒', new SymbolMapping('⋒', "Cap"));
        put('⋄', new SymbolMapping('⋄', "diamond"));
        put('⊮', new SymbolMapping('⊮', "nVdash"));
        put('≒', new SymbolMapping('≒', "fallingdotseq"));
        put('≔', new FormulaMapping("\\colonequals"));
        put('≕', new FormulaMapping("\\equalscolon"));
        put('≬', new SymbolMapping('≬', "between"));
        put('↓', new SymbolMapping('↓', "downarrow"));
        put('⋟', new SymbolMapping('⋟', "curlyeqsucc"));
        put('⊎', new SymbolMapping('⊎', "uplus"));
        put('∊', new SymbolMapping('∊', "in"));
        put('⊈', new SymbolMapping('⊈', "nsubseteq"));
        put('⋇', new SymbolMapping('⋇', "divideontimes"));
        put('⋨', new SymbolMapping('⋨', "precnsim"));
        put('⋼', new SymbolMapping('⋼', "niplus"));
        put('⩾', new SymbolMapping('⩾', "geqslant"));
        put('≍', new SymbolMapping('≍', "asymp"));
        put('⌆', new SymbolMapping('⌆', "doublebarwedge"));
        put('⊑', new SymbolMapping('⊑', "sqsubseteq"));
        put('⎰', new SymbolMapping('⎰', "lmoustache"));
        put('≁', new SymbolMapping('≁', "nsim"));
        put('⊓', new SymbolMapping('⊓', "sqcap"));
        put('⫆', new SymbolMapping('⫆', "supseteqq"));
        put('⋋', new SymbolMapping('⋋', "leftthreetimes"));
        put('⊢', new SymbolMapping('⊢', "vdash"));
        put('∡', new SymbolMapping('∡', "measuredangle"));
        put('⊆', new SymbolMapping('⊆', "subseteq"));
        put('⋘', new FormulaMapping("\\llless"));
        put('⇐', new SymbolMapping('⇐', "Leftarrow"));
        put('℘', new SymbolMapping('℘', "wp"));
        put('≀', new SymbolMapping('≀', "wr"));
        put('⨂', new SymbolMapping('⨂', "bigotimes"));
        put('⊧', new FormulaMapping("\\models"));
        put('⊞', new SymbolMapping('⊞', "boxplus"));
        put('↣', new SymbolMapping('↣', "rightarrowtail"));
        put('⊟', new SymbolMapping('⊟', "boxminus"));
        put('⪆', new SymbolMapping('⪆', "gtrapprox"));
        put('≺', new SymbolMapping('≺', "prec"));
        put('⇃', new SymbolMapping('⇃', "downharpoonleft"));
        put('⟿', new SymbolMapping('⟿', "leadsto"));
        put('⪖', new SymbolMapping('⪖', "eqslantgtr"));
        put('≅', new FormulaMapping("\\cong"));
        put('↫', new SymbolMapping('↫', "looparrowleft"));
        put('ℶ', new SymbolMapping('ℶ', "beth"));
        put('∬', new FormulaMapping("\\iint"));
        put('⊥', new SymbolMapping('⊥', "perp"));
        put('⇍', new SymbolMapping('⇍', "nLeftarrow"));
        put('⨆', new SymbolMapping('⨆', "bigsqcup"));
        put('∇', new SymbolMapping('∇', "nabla"));
        put('⊴', new SymbolMapping('⊴', "trianglelefteq"));
        put('⊏', new SymbolMapping('⊏', "sqsubset"));
        put('∄', new SymbolMapping('∄', "nexists"));
        put('▶', new SymbolMapping('▶', "blacktriangleright"));
        put('∧', new SymbolMapping('∧', "wedge"));
        put('∠', new SymbolMapping('∠', "angle"));
        put('⨀', new SymbolMapping('⨀', "bigodot"));
        put(' ', new FormulaMapping("\\quad"));
        put('⊪', new SymbolMapping('⊪', "Vvdash"));
        put('≼', new SymbolMapping('≼', "preccurlyeq"));
        put('▷', new SymbolMapping('▷', "triangleright"));
        put('≊', new SymbolMapping('≊', "approxeq"));
        put('⩡', new SymbolMapping('⩡', "veebar"));
        put('⇓', new SymbolMapping('⇓', "Downarrow"));
        put('⊭', new SymbolMapping('⊭', "nvDash"));
        put('≲', new SymbolMapping('≲', "lesssim"));
        put('≾', new SymbolMapping('≾', "precsim"));
        put('∮', new FormulaMapping("\\oint"));
        put('⊊', new SymbolMapping('⊊', "subsetneq"));
        put('⊩', new SymbolMapping('⊩', "Vdash"));
        put('╱', new SymbolMapping('╱', "diagup"));
        put('∢', new SymbolMapping('∢', "sphericalangle"));
        put('♢', new SymbolMapping('♢', "diamondsuit"));
        put('⊣', new SymbolMapping('⊣', "dashv"));
        put('↗', new SymbolMapping('↗', "nearrow"));
        put('↛', new SymbolMapping('↛', "nrightarrow"));
        put('⪌', new SymbolMapping('⪌', "gtreqqless"));
        put('⋈', new FormulaMapping("\\bowtie"));
        put('⋔', new SymbolMapping('⋔', "pitchfork"));
        put('♮', new SymbolMapping('♮', "natural"));
        put('⪶', new SymbolMapping('⪶', "succneqq"));
        put('↭', new SymbolMapping('↭', "leftrightsquigarrow"));
        put('∩', new SymbolMapping('∩', "cap"));
        put('⎱', new SymbolMapping('⎱', "rmoustache"));
        put('⋑', new SymbolMapping('⋑', "Supset"));
        put('∑', new SymbolMapping('∑', "sum"));
        put('⊸', new SymbolMapping('⊸', "multimap"));
        put('⌋', new SymbolMapping('⌋', "rfloor"));
        put('↮', new SymbolMapping('↮', "nleftrightarrow"));
        put('◊', new SymbolMapping('◊', "lozenge"));
        put('⇇', new SymbolMapping('⇇', "leftleftarrows"));
        put('↝', new SymbolMapping('↝', "rightsquigarrow"));
        put('⊠', new SymbolMapping('⊠', "boxtimes"));
        put('⇄', new SymbolMapping('⇄', "rightleftarrows"));
        put('⋬', new SymbolMapping('⋬', "ntrianglelefteq"));
        put('∭', new FormulaMapping("\\iiint"));
        put('⊤', new SymbolMapping('⊤', "top"));
        put('→', new SymbolMapping('→', "rightarrow"));
        put('ℜ', new SymbolMapping('ℜ', "Re"));
        put('⊐', new SymbolMapping('⊐', "sqsupset"));
        put('↠', new SymbolMapping('↠', "twoheadrightarrow"));
        put('■', new SymbolMapping('■', "blacksquare"));
        put('↶', new SymbolMapping('↶', "curvearrowleft"));
        put('ℸ', new SymbolMapping('ℸ', "daleth"));
        put('≖', new SymbolMapping('≖', "eqcirc"));
        put('≿', new SymbolMapping('≿', "succsim"));
        put('⇑', new SymbolMapping('⇑', "Uparrow"));
        put('℧', new SymbolMapping('℧', "mho"));
        put('Å', new FormulaMapping("\\text{\\AA}"));
        put('≂', new SymbolMapping('≂', "eqsim"));
        put('↬', new SymbolMapping('↬', "looparrowright"));
        put('⇀', new SymbolMapping('⇀', "rightharpoonup"));
        put('≡', new SymbolMapping('≡', "equiv"));
        put('≢', new FormulaMapping("{\\not\\equiv}"));
        put('↢', new SymbolMapping('↢', "leftarrowtail"));
        put('⊲', new SymbolMapping('⊲', "lhd"));
        put('★', new SymbolMapping('★', "bigstar"));
        put('⋧', new SymbolMapping('⋧', "gnsim"));
        put('⊨', new SymbolMapping('⊨', "vDash"));
        put('⧫', new SymbolMapping('⧫', "blacklozenge"));
        put('·', new SymbolMapping('·', "cdot"));
        put('≮', new SymbolMapping('≮', "nless"));
        put('‡', new SymbolMapping('‡', "ddagger"));
        put('≓', new SymbolMapping('≓', "risingdotseq"));
        put('ℓ', new SymbolMapping('ℓ', "ell"));
        put('≑', new SymbolMapping('≑', "doteqdot"));
        put('⪇', new SymbolMapping('⪇', "lneq"));
        put('⇒', new SymbolMapping('⇒', "Rightarrow"));
        put('⟹', new FormulaMapping("\\Longrightarrow"));
        put('↷', new SymbolMapping('↷', "curvearrowright"));
        put('↪', new FormulaMapping("\\hookrightarrow"));
        put('⟷', new FormulaMapping("\\longleftrightarrow"));
        put('⋞', new SymbolMapping('⋞', "curlyeqprec"));
        put('⟵', new FormulaMapping("\\longleftarrow"));
        put('⇁', new SymbolMapping('⇁', "rightharpoondown"));
        put('ℏ', new SymbolMapping('ℏ', "hslash"));
        put('∏', new SymbolMapping('∏', "prod"));
        put('↕', new SymbolMapping('↕', "updownarrow"));
        put('↰', new SymbolMapping('↰', "Lsh"));
        put('⊁', new SymbolMapping('⊁', "nsucc"));
        put('∕', new SymbolMapping('∕', "slash"));
        put('↽', new SymbolMapping('↽', "leftharpoondown"));
        put('ℴ', new FormulaMapping("\\mathit{o}"));
        put('ℵ', new SymbolMapping('ℵ', "aleph"));
        put('⊯', new SymbolMapping('⊯', "nVDash"));
        put('⌊', new SymbolMapping('⌊', "lfloor"));
        put('∘', new SymbolMapping('∘', "circ"));
        put('⋃', new SymbolMapping('⋃', "bigcup"));
        put('⪷', new SymbolMapping('⪷', "precapprox"));
        put('⊵', new SymbolMapping('⊵', "trianglerighteq"));
        put('∔', new SymbolMapping('∔', "dotplus"));
        put('⊀', new SymbolMapping('⊀', "nprec"));
        put('⋯', new FormulaMapping("\\cdots"));
        put('⇚', new SymbolMapping('⇚', "Lleftarrow"));
        put('⪵', new SymbolMapping('⪵', "precneqq"));
        put('ℯ', new FormulaMapping("e"));
        put('Ⅎ', new SymbolMapping('Ⅎ', "Finv"));
        put('⊺', new SymbolMapping('⊺', "intercal"));
        put('√', new FormulaMapping("\\surd"));
        put('⋁', new SymbolMapping('⋁', "bigvee"));
        put('⪈', new SymbolMapping('⪈', "gneq"));
        put('∀', new SymbolMapping('∀', "forall"));
        put('⪹', new SymbolMapping('⪹', "precnapprox"));
        put('♠', new SymbolMapping('♠', "spadesuit"));
        put('⟼', new FormulaMapping("\\longmapsto"));
        put('⟦', new SymbolMapping('⟦', "llbracket"));
        put('⟧', new SymbolMapping('⟧', "rrbracket"));
        put('⟨', new SymbolMapping('⟨', "langle"));
        put('⟩', new SymbolMapping('⟩', "rangle"));
        put('◯', new SymbolMapping('◯', "bigcirc"));
        put('⋍', new SymbolMapping('⋍', "backsimeq"));
        put('∁', new SymbolMapping('∁', "complement"));
        put('∵', new SymbolMapping('∵', "because"));
        put('∸', new FormulaMapping("\\dotminus"));
        put('△', new SymbolMapping('△', "bigtriangleup"));
        put('↞', new SymbolMapping('↞', "twoheadleftarrow"));
        put('ϵ', new SymbolMapping('ϵ', "epsilon"));
        put('♯', new SymbolMapping('♯', "sharp"));
        put('⋀', new SymbolMapping('⋀', "bigwedge"));
        put('╲', new SymbolMapping('╲', "diagdown"));
        put('↦', new FormulaMapping("\\mapsto"));
        put('⊕', new SymbolMapping('⊕', "oplus"));
        put('⌉', new SymbolMapping('⌉', "rceil"));
        put('⋐', new SymbolMapping('⋐', "Subset"));
        put('⇌', new SymbolMapping('⇌', "rightleftharpoons"));
        put('́', new FormulaMapping("\\acute"));
        put('̀', new FormulaMapping("\\grave"));
        put('̈', new FormulaMapping("\\ddot"));
        put('̋', new FormulaMapping("\\doubleacute"));
        put('̃', new FormulaMapping("\\tilde"));
        put('˚', new FormulaMapping("\\jlatexmathring"));
        put('̄', new FormulaMapping("\\bar"));
        put('̆', new FormulaMapping("\\breve"));
        put('̌', new FormulaMapping("\\check"));
        put('̂', new FormulaMapping("\\hat"));
        put('⃗', new FormulaMapping("\\vec"));
        put('̇', new FormulaMapping("\\dot"));
        put('ℂ', new FormulaMapping("\\mathbb{C}"));
        put('℃', new FormulaMapping("\\sideset{^\\circ}{}\\text{C}"));
        put('ℇ', new SymbolMapping('ℇ', "Eulerconst"));
        put('℉', new FormulaMapping("\\sideset{^\\circ}{}\\text{F}"));
        put('ℍ', new FormulaMapping("\\mathbb{H}"));
        put('ℕ', new FormulaMapping("\\mathbb{N}"));
        put('ℙ', new FormulaMapping("\\mathbb{P}"));
        put('ℚ', new FormulaMapping("\\mathbb{Q}"));
        put('ℝ', new FormulaMapping("\\mathbb{R}"));
        put('ℤ', new FormulaMapping("\\mathbb{Z}"));
        put('ℨ', new FormulaMapping("\\mathfrak{Z}"));
        put('ℋ', new FormulaMapping("\\mathscr{H}"));
        put('ℌ', new FormulaMapping("\\mathfrak{H}"));
        put('ℐ', new FormulaMapping("\\mathscr{I}"));
        put('ℒ', new FormulaMapping("\\mathscr{L}"));
        put('ℛ', new FormulaMapping("\\mathscr{R}"));
        put('ℬ', new FormulaMapping("\\mathscr{B}"));
        put('ℭ', new FormulaMapping("\\mathfrak{C}"));
        put('ℰ', new FormulaMapping("\\mathscr{E}"));
        put('ℱ', new FormulaMapping("\\mathscr{F}"));
        put('ℳ', new FormulaMapping("\\mathscr{M}"));
        put('ﬀ', new FormulaMapping("\\text{ff}"));
        put('ﬁ', new FormulaMapping("\\text{fi}"));
        put('ﬂ', new FormulaMapping("\\text{fl}"));
        put('ﬃ', new FormulaMapping("\\text{ffi}"));
        put('ﬄ', new FormulaMapping("\\text{ffl}"));
        put('⦓', new FormulaMapping("\\mathbin{\\rlap{<}\\;(}"));
        put('⦔', new FormulaMapping("\\mathbin{\\rlap{>}\\,)}"));
        put('⦵', new SymbolMapping('⦵', "minuso"));
        put('⦾', new SymbolMapping('⦾', "varocircle"));
        put('⧀', new SymbolMapping('⧀', "olessthan"));
        put('⧁', new SymbolMapping('⧁', "ogreaterthan"));
        put('⧄', new SymbolMapping('⧄', "boxslash"));
        put('⧅', new SymbolMapping('⧅', "boxbslash"));
        put('✠', new SymbolMapping('✠', "maltese"));
        put('✓', new SymbolMapping('✓', "checkmark"));
        put('‾', new FormulaMapping("\\mathpunct{\\={\\ }}"));
        put('⁃', new FormulaMapping("\\hybull"));
        put('‰', new SymbolMapping('‰', "textperthousand"));
        put('‱', new SymbolMapping('‱', "textpertenthousand"));
        put('‹', new SymbolMapping('‹', "guilsinglleft"));
        put('›', new SymbolMapping('›', "guilsinglright"));
        put('≄', new FormulaMapping("{\\not\\simeq}"));
        put('‴', new FormulaMapping("\'\'\'"));
        put('⃛', new FormulaMapping("\\dddot"));
        put('‘', new FormulaMapping("`"));
        put('“', new FormulaMapping("\\text{``}"));
        put('’', new SymbolMapping('’', "textapos"));
        put('‚', new FormulaMapping(","));
        put('”', new FormulaMapping("{\\textapos\\textapos}"));
        put('„', new FormulaMapping(",,"));
        put('‎', new FormulaMapping(" "));
        put('‏', new FormulaMapping(" "));
        put('€', new SymbolMapping('€', "euro"));
        put('‐', new SymbolMapping('‐', "textminus"));
        put('–', new SymbolMapping('–', "textendash"));
        put('—', new SymbolMapping('—', "textemdash"));
        put('℀', new FormulaMapping("\\sfrac{a}{c}"));
        put('℁', new FormulaMapping("\\sfrac{a}{s}"));
        put('℅', new FormulaMapping("\\sfrac{c}{o}"));
        put('℆', new FormulaMapping("\\sfrac{c}{u}"));
        put('℠', new FormulaMapping("{{}^{\\text{SM}}}"));
        put('™', new FormulaMapping("{{}^{\\text{TM}}}"));
        put('⌜', new SymbolMapping('⌜', "ulcorner"));
        put('⌝', new SymbolMapping('⌝', "urcorner"));
        put('⌞', new SymbolMapping('⌞', "llcorner"));
        put('⌟', new SymbolMapping('⌟', "lrcorner"));
        put('⅓', new FormulaMapping("\\text{\\sfrac13}"));
        put('⅔', new FormulaMapping("\\text{sfrac23}"));
        put('⅕', new FormulaMapping("\\text{\\sfrac15}"));
        put('⅖', new FormulaMapping("\\text{\\sfrac25}"));
        put('⅗', new FormulaMapping("\\text{\\sfrac35}"));
        put('⅘', new FormulaMapping("\\text{\\sfrac45}"));
        put('⅙', new FormulaMapping("\\text{\\sfrac16}"));
        put('⅚', new FormulaMapping("\\text{\\sfrac56}"));
        put('½', new FormulaMapping("\\text{\\sfrac12}"));
        put('¼', new FormulaMapping("\\text{\\sfrac14}"));
        put('¾', new FormulaMapping("\\text{\\sfrac34}"));
        put('⅛', new FormulaMapping("\\text{\\sfrac18}"));
        put('⅜', new FormulaMapping("\\text{\\sfrac38}"));
        put('⅝', new FormulaMapping("\\text{\\sfrac58}"));
        put('⅞', new FormulaMapping("\\text{\\sfrac78}"));
        put('⅟', new FormulaMapping("\\text{\\sfrac{1}{\\ }}"));
        put('Ⅰ', new FormulaMapping("\\text{I}"));
        put('Ⅱ', new FormulaMapping("\\text{II}"));
        put('Ⅲ', new FormulaMapping("\\text{III}"));
        put('Ⅳ', new FormulaMapping("\\text{IV}"));
        put('Ⅴ', new FormulaMapping("\\text{V}"));
        put('Ⅵ', new FormulaMapping("\\text{VI}"));
        put('Ⅶ', new FormulaMapping("\\text{VII}"));
        put('Ⅷ', new FormulaMapping("\\text{VIII}"));
        put('Ⅸ', new FormulaMapping("\\text{IX}"));
        put('Ⅹ', new FormulaMapping("\\text{X}"));
        put('Ⅺ', new FormulaMapping("\\text{XI}"));
        put('Ⅻ', new FormulaMapping("\\text{XII}"));
        put('Ⅼ', new FormulaMapping("\\text{L}"));
        put('Ⅽ', new FormulaMapping("\\text{C}"));
        put('Ⅾ', new FormulaMapping("\\text{D}"));
        put('Ⅿ', new FormulaMapping("\\text{M}"));
        put('ⅰ', new FormulaMapping("\\text{i}"));
        put('ⅱ', new FormulaMapping("\\text{ii}"));
        put('ⅲ', new FormulaMapping("\\text{iii}"));
        put('ⅳ', new FormulaMapping("\\text{iv}"));
        put('ⅴ', new FormulaMapping("\\text{v}"));
        put('ⅵ', new FormulaMapping("\\text{vi}"));
        put('ⅶ', new FormulaMapping("\\text{vii}"));
        put('ⅷ', new FormulaMapping("\\text{viii}"));
        put('ⅸ', new FormulaMapping("\\text{ix}"));
        put('ⅹ', new FormulaMapping("\\text{x}"));
        put('ⅺ', new FormulaMapping("\\text{xi}"));
        put('ⅻ', new FormulaMapping("\\text{xii}"));
        put('ⅼ', new FormulaMapping("\\text{l}"));
        put('ⅽ', new FormulaMapping("\\text{c}"));
        put('ⅾ', new FormulaMapping("\\text{d}"));
        put('ⅿ', new FormulaMapping("\\text{m}"));
        put('▀', new FormulaMapping("\\uhblk"));
        put('▁', new FormulaMapping("\\lhblk"));
        put('█', new FormulaMapping("\\block"));
        put('░', new FormulaMapping("\\fgcolor{bfbfbf}{\\block}"));
        put('▒', new FormulaMapping("\\fgcolor{808080}{\\block}"));
        put('▓', new FormulaMapping("\\fgcolor{404040}{\\block}"));
        put('①', new FormulaMapping("\\textcircled{\\texttt 1}"));
        put('②', new FormulaMapping("\\textcircled{\\texttt 2}"));
        put('③', new FormulaMapping("\\textcircled{\\texttt 3}"));
        put('④', new FormulaMapping("\\textcircled{\\texttt 4}"));
        put('⑤', new FormulaMapping("\\textcircled{\\texttt 5}"));
        put('⑥', new FormulaMapping("\\textcircled{\\texttt 6}"));
        put('⑦', new FormulaMapping("\\textcircled{\\texttt 7}"));
        put('⑧', new FormulaMapping("\\textcircled{\\texttt 8}"));
        put('⑨', new FormulaMapping("\\textcircled{\\texttt 9}"));
        put('Ⓐ', new FormulaMapping("\\textcircled{\\texttt A}"));
        put('Ⓑ', new FormulaMapping("\\textcircled{\\texttt B}"));
        put('Ⓒ', new FormulaMapping("\\textcircled{\\texttt C}"));
        put('Ⓓ', new FormulaMapping("\\textcircled{\\texttt D}"));
        put('Ⓔ', new FormulaMapping("\\textcircled{\\texttt E}"));
        put('Ⓕ', new FormulaMapping("\\textcircled{\\texttt F}"));
        put('Ⓖ', new FormulaMapping("\\textcircled{\\texttt G}"));
        put('Ⓗ', new FormulaMapping("\\textcircled{\\texttt H}"));
        put('Ⓘ', new FormulaMapping("\\textcircled{\\texttt I}"));
        put('Ⓙ', new FormulaMapping("\\textcircled{\\texttt J}"));
        put('Ⓚ', new FormulaMapping("\\textcircled{\\texttt K}"));
        put('Ⓛ', new FormulaMapping("\\textcircled{\\texttt L}"));
        put('Ⓜ', new FormulaMapping("\\textcircled{\\texttt M}"));
        put('Ⓝ', new FormulaMapping("\\textcircled{\\texttt N}"));
        put('Ⓞ', new FormulaMapping("\\textcircled{\\texttt O}"));
        put('Ⓟ', new FormulaMapping("\\textcircled{\\texttt P}"));
        put('Ⓠ', new FormulaMapping("\\textcircled{\\texttt Q}"));
        put('Ⓡ', new FormulaMapping("\\textcircled{\\texttt R}"));
        put('Ⓢ', new FormulaMapping("\\textcircled{\\texttt S}"));
        put('Ⓣ', new FormulaMapping("\\textcircled{\\texttt T}"));
        put('Ⓤ', new FormulaMapping("\\textcircled{\\texttt U}"));
        put('Ⓥ', new FormulaMapping("\\textcircled{\\texttt V}"));
        put('Ⓦ', new FormulaMapping("\\textcircled{\\texttt W}"));
        put('Ⓧ', new FormulaMapping("\\textcircled{\\texttt X}"));
        put('Ⓨ', new FormulaMapping("\\textcircled{\\texttt Y}"));
        put('Ⓩ', new FormulaMapping("\\textcircled{\\texttt Z}"));
        put('ⓐ', new FormulaMapping("\\textcircled{\\texttt a}"));
        put('ⓑ', new FormulaMapping("\\textcircled{\\texttt b}"));
        put('ⓒ', new FormulaMapping("\\textcircled{\\texttt c}"));
        put('ⓓ', new FormulaMapping("\\textcircled{\\texttt d}"));
        put('ⓔ', new FormulaMapping("\\textcircled{\\texttt e}"));
        put('ⓕ', new FormulaMapping("\\textcircled{\\texttt f}"));
        put('ⓖ', new FormulaMapping("\\textcircled{\\texttt g}"));
        put('ⓗ', new FormulaMapping("\\textcircled{\\texttt h}"));
        put('ⓘ', new FormulaMapping("\\textcircled{\\texttt i}"));
        put('ⓙ', new FormulaMapping("\\textcircled{\\texttt j}"));
        put('ⓚ', new FormulaMapping("\\textcircled{\\texttt k}"));
        put('ⓛ', new FormulaMapping("\\textcircled{\\texttt l}"));
        put('ⓜ', new FormulaMapping("\\textcircled{\\texttt m}"));
        put('ⓝ', new FormulaMapping("\\textcircled{\\texttt n}"));
        put('ⓞ', new FormulaMapping("\\textcircled{\\texttt o}"));
        put('ⓟ', new FormulaMapping("\\textcircled{\\texttt p}"));
        put('ⓠ', new FormulaMapping("\\textcircled{\\texttt q}"));
        put('ⓡ', new FormulaMapping("\\textcircled{\\texttt r}"));
        put('ⓢ', new FormulaMapping("\\textcircled{\\texttt s}"));
        put('ⓣ', new FormulaMapping("\\textcircled{\\texttt t}"));
        put('ⓤ', new FormulaMapping("\\textcircled{\\texttt u}"));
        put('ⓥ', new FormulaMapping("\\textcircled{\\texttt v}"));
        put('ⓦ', new FormulaMapping("\\textcircled{\\texttt w}"));
        put('ⓧ', new FormulaMapping("\\textcircled{\\texttt x}"));
        put('ⓨ', new FormulaMapping("\\textcircled{\\texttt y}"));
        put('ⓩ', new FormulaMapping("\\textcircled{\\texttt z}"));
        put('⅄', new SymbolMapping('⅄', "Yup"));
        put('↤', new FormulaMapping("\\mapsfrom"));
        put('⤆', new FormulaMapping("\\Mapsfrom"));
        put('↯', new SymbolMapping('↯', "lightning"));
        put('⇽', new SymbolMapping('⇽', "leftarrowtriangle"));
        put('⇾', new SymbolMapping('⇾', "rightarrowtriangle"));
        put('◫', new SymbolMapping('◫', "boxbar"));
        put('⟅', new SymbolMapping('⟅', "Lbag"));
        put('⟆', new SymbolMapping('⟆', "Rbag"));
        put('⟻', new FormulaMapping("\\longmapsfrom"));
        put('⟽', new FormulaMapping("\\Longmapsfrom"));
        put('⟾', new FormulaMapping("\\Longmapsto"));
        put('⤇', new FormulaMapping("\\Mapsto"));
        put('⧆', new SymbolMapping('⧆', "boxast"));
        put('⧇', new SymbolMapping('⧇', "boxcircle"));
        put('⧈', new SymbolMapping('⧈', "boxbox"));
        put('⫴', new SymbolMapping('⫴', "interleave"));
        put('⫼', new SymbolMapping('⫼', "biginterleave"));
        put('⫽', new SymbolMapping('⫽', "sslash"));
        put('⫾', new SymbolMapping('⫾', "talloblong"));
    }

    private void initCyrillic() {
        putSym('ı', "dotlessi");
        putSym('А', "CYRA");
        putSym('Б', "CYRB");
        putSym('В', "CYRV");
        putSym('Г', "CYRG");
        putSym('Д', "CYRD");
        putSym('Е', "CYRE");
        putSym('Ё', "CYRYO");
        putSym('Ж', "CYRZH");
        putSym('З', "CYRZ");
        putSym('И', "CYRI");
        putSym('Й', "CYRIO");
        putSym('К', "CYRK");
        putSym('Л', "CYRL");
        putSym('М', "CYRM");
        putSym('Н', "CYRN");
        putSym('О', "CYRO");
        putSym('П', "CYRP");
        putSym('Р', "CYRR");
        putSym('С', "CYRS");
        putSym('Т', "CYRT");
        putSym('У', "CYRU");
        putSym('Ф', "CYRF");
        putSym('Х', "CYRH");
        putSym('Ц', "CYRC");
        putSym('Ч', "CYRCH");
        putSym('Ш', "CYRSH");
        putSym('Щ', "CYRSHCH");
        putSym('Ъ', "CYRHRDSN");
        putSym('Ы', "CYRY");
        putSym('Ь', "CYRSFTSN");
        putSym('Э', "CYREREV");
        putSym('Ю', "CYRYU");
        putSym('Я', "CYRYA");
        putSym('а', "cyra");
        putSym('б', "cyrb");
        putSym('в', "cyrv");
        putSym('г', "cyrg");
        putSym('д', "cyrd");
        putSym('е', "cyre");
        putSym('ё', "cyryo");
        putSym('ж', "cyrzh");
        putSym('з', "cyrz");
        putSym('и', "cyri");
        putSym('й', "cyrio");
        putSym('к', "cyrk");
        putSym('л', "cyrl");
        putSym('м', "cyrm");
        putSym('н', "cyrn");
        putSym('о', "cyro");
        putSym('п', "cyrp");
        putSym('р', "cyrr");
        putSym('с', "cyrs");
        putSym('т', "cyrt");
        putSym('у', "cyru");
        putSym('ф', "cyrf");
        putSym('х', "cyrh");
        putSym('ц', "cyrc");
        putSym('ч', "cyrch");
        putSym('ш', "cyrsh");
        putSym('щ', "cyrshch");
        putSym('ъ', "cyrhrdsn");
        putSym('ы', "cyry");
        putSym('ь', "cyrsftsn");
        putSym('э', "cyrerev");
        putSym('ю', "cyryu");
        putSym('я', "cyrya");
        putSym('Є', "CYRIE");
        putSym('І', "CYRII");
        putSym('є', "cyrie");
        putSym('і', "cyrii");
        putSym('Ђ', "CYRDJE");
        putSym('Ѕ', "CYRDZE");
        putSym('Ј', "CYRJE");
        putSym('Љ', "CYRLJE");
        putSym('Њ', "CYRNJE");
        putSym('Ћ', "CYRTSHE");
        putSym('Џ', "CYRDZHE");
        putSym('Ѵ', "CYRIZH");
        putSym('Ѣ', "CYRYAT");
        putSym('Ѳ', "CYRFITA");
        putSym('ђ', "cyrdje");
        putSym('ѕ', "cyrdze");
        putSym('ј', "cyrje");
        putSym('љ', "cyrlje");
        putSym('њ', "cyrnje");
        putSym('ћ', "cyrtshe");
        putSym('џ', "cyrdzhe");
        putSym('ѵ', "cyrizh");
        putSym('ѣ', "cyryat");
        putSym('ѳ', "cyrfita");
        putForm('Ѐ', "\\`\\CYRE");
        putForm('Ѓ', "\\'\\CYRG");
        putForm('Ї', "\\cyrddot\\CYRII");
        putForm('ї', "\\cyrddot\\dotlessi");
        putForm('Ѓ', "\\'\\CYRG");
        putForm('Ѓ', "\\'\\CYRK");
        putForm('Ѝ', "\\`\\CYRI");
        putForm('Ў', "\\U\\CYRU");
        putForm('ѐ', "\\`\\cyre");
        putForm('ѓ', "\\'\\cyrg");
        putForm('ќ', "\\'\\cyrk");
        putForm('ѝ', "\\`\\cyri");
        putForm('ў', "\\U\\cyru");
    }

    private void initGreek() {
        putSym('ʹ', "ʹ");
        putSym('͵', "͵");
        putSym('ͺ', "ͺ");
        putSym('΄', "΄");
        putSym('΅', "΅");
        putSym('·', "·");
        putSym('ΐ', "ΐ");
        putSym('Ϊ', "Ϊ");
        putSym('Ϋ', "Ϋ");
        putSym('ά', "ά");
        putSym('έ', "έ");
        putSym('ή', "ή");
        putSym('ί', "ί");
        putSym('ΰ', "ΰ");
        putSym('ϊ', "ϊ");
        putSym('ϋ', "ϋ");
        putSym('ό', "ό");
        putSym('ύ', "ύ");
        putSym('ώ', "ώ");
        putSym('Ϙ', "Ϙ");
        putSym('ϙ', "ϙ");
        putSym('Ϛ', "Ϛ");
        putSym('ϛ', "ϛ");
        putSym('Ϝ', "Ϝ");
        putSym('ϝ', "ϝ");
        putSym('ϟ', "ϟ");
        putSym('Ϡ', "Ϡ");
        putSym('ϡ', "ϡ");
        putSym('ἀ', "ἀ");
        putSym('ἁ', "ἁ");
        putSym('ἂ', "ἂ");
        putSym('ἃ', "ἃ");
        putSym('ἄ', "ἄ");
        putSym('ἅ', "ἅ");
        putSym('ἆ', "ἆ");
        putSym('ἇ', "ἇ");
        putSym('ἐ', "ἐ");
        putSym('ἑ', "ἑ");
        putSym('ἒ', "ἒ");
        putSym('ἓ', "ἓ");
        putSym('ἔ', "ἔ");
        putSym('ἕ', "ἕ");
        putSym('ἠ', "ἠ");
        putSym('ἡ', "ἡ");
        putSym('ἢ', "ἢ");
        putSym('ἣ', "ἣ");
        putSym('ἤ', "ἤ");
        putSym('ἥ', "ἥ");
        putSym('ἦ', "ἦ");
        putSym('ἧ', "ἧ");
        putSym('ἰ', "ἰ");
        putSym('ἱ', "ἱ");
        putSym('ἲ', "ἲ");
        putSym('ἳ', "ἳ");
        putSym('ἴ', "ἴ");
        putSym('ἵ', "ἵ");
        putSym('ἶ', "ἶ");
        putSym('ἷ', "ἷ");
        putSym('ὀ', "ὀ");
        putSym('ὁ', "ὁ");
        putSym('ὂ', "ὂ");
        putSym('ὃ', "ὃ");
        putSym('ὄ', "ὄ");
        putSym('ὅ', "ὅ");
        putSym('ὐ', "ὐ");
        putSym('ὑ', "ὑ");
        putSym('ὒ', "ὒ");
        putSym('ὓ', "ὓ");
        putSym('ὔ', "ὔ");
        putSym('ὕ', "ὕ");
        putSym('ὖ', "ὖ");
        putSym('ὗ', "ὗ");
        putSym('ὠ', "ὠ");
        putSym('ὡ', "ὡ");
        putSym('ὢ', "ὢ");
        putSym('ὣ', "ὣ");
        putSym('ὤ', "ὤ");
        putSym('ὥ', "ὥ");
        putSym('ὦ', "ὦ");
        putSym('ὧ', "ὧ");
        putSym('ὰ', "ὰ");
        putSym('ὲ', "ὲ");
        putSym('ὴ', "ὴ");
        putSym('ὶ', "ὶ");
        putSym('ὸ', "ὸ");
        putSym('ὺ', "ὺ");
        putSym('ὼ', "ὼ");
        putSym('ᾀ', "ᾀ");
        putSym('ᾁ', "ᾁ");
        putSym('ᾂ', "ᾂ");
        putSym('ᾃ', "ᾃ");
        putSym('ᾄ', "ᾄ");
        putSym('ᾅ', "ᾅ");
        putSym('ᾆ', "ᾆ");
        putSym('ᾇ', "ᾇ");
        putSym('ᾐ', "ᾐ");
        putSym('ᾑ', "ᾑ");
        putSym('ᾒ', "ᾒ");
        putSym('ᾓ', "ᾓ");
        putSym('ᾔ', "ᾔ");
        putSym('ᾕ', "ᾕ");
        putSym('ᾖ', "ᾖ");
        putSym('ᾗ', "ᾗ");
        putSym('ᾠ', "ᾠ");
        putSym('ᾡ', "ᾡ");
        putSym('ᾢ', "ᾢ");
        putSym('ᾣ', "ᾣ");
        putSym('ᾤ', "ᾤ");
        putSym('ᾥ', "ᾥ");
        putSym('ᾦ', "ᾦ");
        putSym('ᾧ', "ᾧ");
        putSym('ᾲ', "ᾲ");
        putSym('ᾳ', "ᾳ");
        putSym('ᾴ', "ᾴ");
        putSym('ᾶ', "ᾶ");
        putSym('ᾷ', "ᾷ");
        putSym('ᾼ', "ᾼ");
        putSym('᾽', "᾿");
        putSym('ι', "ι");
        putSym('᾿', "᾿");
        putSym('῀', "῀");
        putSym('῁', "῁");
        putSym('ῂ', "ῂ");
        putSym('ῃ', "ῃ");
        putSym('ῄ', "ῄ");
        putSym('ῆ', "ῆ");
        putSym('ῇ', "ῇ");
        putSym('ῌ', "ῌ");
        putSym('῍', "῍");
        putSym('῎', "῎");
        putSym('῏', "῏");
        putSym('ῒ', "ῒ");
        putSym('ῖ', "ῖ");
        putSym('ῗ', "ῗ");
        putSym('῝', "῝");
        putSym('῞', "῞");
        putSym('῟', "῟");
        putSym('ῢ', "ῢ");
        putSym('ῤ', "ῤ");
        putSym('ῥ', "ῥ");
        putSym('ῦ', "ῦ");
        putSym('ῧ', "ῧ");
        putSym('῭', "῭");
        putSym('΅', "΅");
        putSym('`', "`");
        putSym('ῲ', "ῲ");
        putSym('ῳ', "ῳ");
        putSym('ῴ', "ῴ");
        putSym('ῶ', "ῶ");
        putSym('ῷ', "ῷ");
        putSym('ῼ', "ῼ");
        putSym('´', "ʹ");
        putSym('῾', "῾");
        putSym('’', "’");
        putForm('Ά', "{\\grkaccent{ʹ}{\\phantom{ι}}\\!\\!A}");
        putForm('Έ', "\\grkaccent{ʹ}{\\phantom{ι}}Ε");
        putForm('Ή', "\\grkaccent{ʹ}{\\phantom{ι}}H");
        putForm('Ί', "\\grkaccent{ʹ}{\\phantom{ι}}Ι");
        putForm('Ό', "\\grkaccent{ʹ}{\\phantom{ι}}\\!Ο");
        putForm('Ύ', "\\grkaccent{ʹ}{\\phantom{ι}}Υ");
        putForm('Ώ', "\\grkaccent{ʹ}{\\phantom{ι}}\\!Ω");
        putForm('Ἀ', "’Α");
        putForm('Ἁ', "῾Α");
        putForm('Ἂ', "῍Α");
        putForm('Ἃ', "῝Α");
        putForm('Ἄ', "῎Α");
        putForm('Ἅ', "῞Α");
        putForm('Ἆ', "῏Α");
        putForm('Ἇ', "῟Α");
        putForm('Ἐ', "’Ε");
        putForm('Ἑ', "῾Ε");
        putForm('Ἒ', "῍Ε");
        putForm('Ἓ', "῝Ε");
        putForm('Ἔ', "῎Ε");
        putForm('Ἕ', "῞Ε");
        putForm('Ἠ', "’Η");
        putForm('Ἡ', "῾Η");
        putForm('Ἢ', "῍Η");
        putForm('Ἣ', "῝Η");
        putForm('Ἤ', "῎Η");
        putForm('Ἥ', "῞Η");
        putForm('Ἦ', "῏Η");
        putForm('Ἧ', "῟Η");
        putForm('Ἰ', "’Ι");
        putForm('Ἱ', "῾Ι");
        putForm('Ἲ', "῍Ι");
        putForm('Ἳ', "῝Ι");
        putForm('Ἴ', "῎Ι");
        putForm('Ἵ', "῞Ι");
        putForm('Ἶ', "῏Ι");
        putForm('Ἷ', "῟Ι");
        putForm('Ὀ', "’Ο");
        putForm('Ὁ', "῾Ο");
        putForm('Ὂ', "῍Ο");
        putForm('Ὃ', "῝Ο");
        putForm('Ὄ', "῎Ο");
        putForm('Ὅ', "῞Ο");
        putForm('Ὑ', "῾Υ");
        putForm('὚', "῝Υ");
        putForm('Ὓ', "῞Υ");
        putForm('὜', "῟Υ");
        putForm('Ὠ', "’Ω");
        putForm('Ὡ', "῾Ω");
        putForm('Ὢ', "῍Ω");
        putForm('Ὣ', "῝Ω");
        putForm('Ὤ', "῎Ω");
        putForm('Ὥ', "῞Ω");
        putForm('Ὦ', "῏Ω");
        putForm('Ὧ', "῟Ω");
        putForm('ά', "\\grkaccent{΄}α");
        putForm('έ', "\\grkaccent{΄}ε");
        putForm('ή', "\\grkaccent{΄}η");
        putForm('ί', "\\grkaccent{΄}ι");
        putForm('ό', "\\grkaccent{΄}ο");
        putForm('ύ', "\\grkaccent{΄}υ");
        putForm('ώ', "\\grkaccent{΄}ω");
        putForm('ᾈ', "’ᾼ");
        putForm('ᾉ', "῾ᾼ");
        putForm('ᾊ', "῍ᾼ");
        putForm('ᾋ', "῝ᾼ");
        putForm('ᾌ', "῎ᾼ");
        putForm('ᾍ', "῞ᾼ");
        putForm('ᾎ', "῏ᾼ");
        putForm('ᾏ', "῟ᾼ");
        putForm('ᾘ', "’ῌ");
        putForm('ᾙ', "῾ῌ");
        putForm('ᾚ', "῍ῌ");
        putForm('ᾛ', "῝ῌ");
        putForm('ᾜ', "῎ῌ");
        putForm('ᾝ', "῞ῌ");
        putForm('ᾞ', "῏ῌ");
        putForm('ᾟ', "῟ῌ");
        putForm('ᾤ', "\\grkaccent{῎}ῳ");
        putForm('ᾨ', "’ῼ");
        putForm('ᾩ', "῾ῼ");
        putForm('ᾪ', "῍ῼ");
        putForm('ᾫ', "῝ῼ");
        putForm('ᾬ', "῎ῼ");
        putForm('ᾭ', "῞ῼ");
        putForm('ᾮ', "῏ῼ");
        putForm('ᾯ', "῟ῼ");
        putForm('ᾰ', "\\u α");
        putForm('ᾱ', "\\= α");
        putForm('Ᾰ', "\\u Α");
        putForm('Ᾱ', "\\= Α");
        putForm('Ὰ', "{\\grkaccent{`}{\\vphantom{ι}}Α}");
        putForm('Ά', "{\\grkaccent{ʹ}{\\vphantom{ι}}\\!\\!Α}");
        putForm('Ὲ', "{\\grkaccent{`}{\\vphantom{ι}}Ε}");
        putForm('Έ', "{\\grkaccent{ʹ}{\\vphantom{ι}}Ε}");
        putForm('Ὴ', "{\\grkaccent{`}{\\vphantom{ι}}Η}");
        putForm('Ή', "{\\grkaccent{ʹ}{\\vphantom{ι}}Η}");
        putForm('ῐ', "\\u ι");
        putForm('ῑ', "\\= ι");
        putForm('ΐ', "\\grkaccent{΅}ι");
        putForm('Ῐ', "\\u Ι");
        putForm('Ῑ', "\\= Ι");
        putForm('Ὶ', "{\\grkaccent{`}{\\phantom{ι}}Ι}");
        putForm('Ί', "{\\grkaccent{ʹ}{\\phantom{ι}}Ι}");
        putForm('ῠ', "\\u υ");
        putForm('ῡ', "\\= υ");
        putForm('ΰ', "\\grkaccent{΅}υ");
        putForm('Ῠ', "\\u Υ");
        putForm('Ῡ', "\\= Υ");
        putForm('Ὺ', "{\\grkaccent{`}{\\phantom{ι}}Υ}");
        putForm('Ύ', "{\\grkaccent{ʹ}{\\phantom{ι}}Υ}");
        putForm('Ὸ', "{\\grkaccent{`}{\\vphantom{ι}}Ο}");
        putForm('Ό', "{\\grkaccent{ʹ}{\\vphantom{ι}}\\!Ο}");
        putForm('Ὼ', "{\\grkaccent{`}{\\vphantom{ι}}Ω}");
        putForm('Ώ', "{\\grkaccent{ʹ}{\\vphantom{ι}}\\!Ω}");
    }
}
