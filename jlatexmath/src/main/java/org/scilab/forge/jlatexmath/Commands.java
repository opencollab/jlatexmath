/* Commands.java
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

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scilab.forge.jlatexmath.dynamic.DynamicAtom;
import org.scilab.forge.jlatexmath.mhchem.MhchemBondParser;
import org.scilab.forge.jlatexmath.mhchem.MhchemParser;

public class Commands {

    private static final Map<String, Command> map = new HashMap<>(700);

    private static final Command dollar = new CommandDollars.Dollar(true, TeXConstants.STYLE_TEXT);
    private static final Command dollardollar = new CommandDollars.Dollar(false, TeXConstants.STYLE_DISPLAY);

    static {
        map.put("usepackage", new Command() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsString();
                JLMPackage.usePackage(name);
                return false;
            }

            public Object clone() {
                return this;
            }
        });
        map.put("ce", new Command() {
            public boolean init(TeXParser tp) {
                final String code = tp.getGroupAsArgument();
                final MhchemParser mp = new MhchemParser(code);
                mp.parse();
                tp.addToConsumer(new RomanAtom(mp.get()));
                return false;
            }
        });
        map.put("bond", new Command() {
            public boolean init(TeXParser tp) {
                final String code = tp.getGroupAsArgument();
                final MhchemBondParser mbp = new MhchemBondParser(code);
                tp.addToConsumer(mbp.get());
                return false;
            }
        });
        map.put("hbox", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                if (a instanceof RowAtom) {
                    return a;
                }
                return new RowAtom(a);
            }
        });
        map.put("cancel", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new CancelAtom(a, CancelAtom.Type.SLASH);
            }
        });
        map.put("bcancel", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new CancelAtom(a, CancelAtom.Type.BACKSLASH);
            }
        });
        map.put("xcancel", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new CancelAtom(a, CancelAtom.Type.X);
            }
        });
        map.put("mathchoice", new Command4A() {
            public Atom newI(TeXParser tp, Atom a, Atom b, Atom c, Atom d) {
                return new MathchoiceAtom(a, b, c, d);
            }
        });
        map.put("pod", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new PodAtom(a, 8., true);
            }
        });
        map.put("bmod", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom sp = new SpaceAtom(TeXLength.Unit.MU, 5.);
                final RowAtom ra = new RowAtom(sp,
                                               new RomanAtom(TeXParser.getAtomForLatinStr("mod", true).changeType(TeXConstants.TYPE_BINARY_OPERATOR)),
                                               sp);
                return ra;
            }
        });
        map.put("pmod", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                final RowAtom ra = new RowAtom(3);
                ra.add(new RomanAtom(TeXParser.getAtomForLatinStr("mod", true)));
                ra.add(new SpaceAtom(TeXLength.Unit.MU, 6.));
                ra.add(a);
                return new PodAtom(ra, 8., true);
            }
        });
        map.put("mod", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                final RowAtom ra = new RowAtom(4);
                final Atom sp = new SpaceAtom(TeXConstants.Muskip.THIN);
                ra.add(new RomanAtom(TeXParser.getAtomForLatinStr("mod", true)));
                ra.add(sp);
                ra.add(sp);
                ra.add(a);
                return new PodAtom(ra, 12., false);
            }
        });
        map.put("begingroup", new Command() {
            public boolean init(TeXParser tp) {
                tp.processLBrace();
                return false;
            }
        });
        map.put("endgroup", new Command() {
            public boolean init(TeXParser tp) {
                tp.processRBrace();
                return false;
            }
        });
        map.put("DeclareMathOperator", new Command() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsCommand();
                final String base = tp.getGroupAsArgument();
                final String code = "\\mathop{\\mathrm{" + base + "}}\\nolimits";
                NewCommandMacro.addNewCommand(tp, name, code, 0, false);
                return false;
            }
        });
        map.put("newcommand", new Command() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsCommand();
                final int nbargs = tp.getOptionAsPositiveInteger(0);
                final String code = tp.getGroupAsArgument();
                NewCommandMacro.addNewCommand(tp, name, code, nbargs, false);
                return false;
            }
        });
        map.put("renewcommand", new Command() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsCommand();
                final int nbargs = tp.getOptionAsPositiveInteger(0);
                final String code = tp.getGroupAsArgument();
                NewCommandMacro.addNewCommand(tp, name, code, nbargs, true);
                return false;
            }
        });
        map.put("newenvironment", new Command() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsString();
                final int nbargs = tp.getOptionAsPositiveInteger(0);
                final String before = tp.getArgAsString();
                final String after = tp.getArgAsString();
                NewEnvironmentMacro.addNewEnvironment(tp, name, before, after, nbargs, false);
                return false;
            }
        });
        map.put("renewenvironment", new Command() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsString();
                final int nbargs = tp.getOptionAsPositiveInteger(0);
                final String before = tp.getArgAsString();
                final String after = tp.getArgAsString();
                NewEnvironmentMacro.addNewEnvironment(tp, name, before, after, nbargs, true);
                return false;
            }
        });
        map.put("left", new CommandLMR.CommandLeft());
        map.put("right", new CommandLMR.CommandRight());
        map.put("middle", new CommandLMR.CommandMiddle());
        map.put("Braket", new CommandBra(Symbols.LANGLE, Symbols.RANGLE));
        map.put("Bra", new CommandBra(Symbols.LANGLE, Symbols.VERT));
        map.put("Ket", new CommandBra(Symbols.VERT, Symbols.RANGLE));
        map.put("Set", new CommandBra(Symbols.LBRACE, Symbols.RBRACE));
        map.put("braket", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RowAtom(Symbols.LANGLE, a, Symbols.RANGLE);
            }
        });
        map.put("bra", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RowAtom(Symbols.LANGLE, a, Symbols.VERT);
            }
        });
        map.put("ket", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RowAtom(Symbols.VERT, a, Symbols.RANGLE);
            }
        });
        map.put("set", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RowAtom(Symbols.LBRACE, a, Symbols.RBRACE);
            }
        });
        map.put("hookrightarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.LHOOK,
                       new SpaceAtom(TeXLength.Unit.EM, -0.169),
                       Symbols.RIGHTARROW);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("hookleftarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.LEFTARROW,
                       new SpaceAtom(TeXLength.Unit.EM, -0.169),
                       Symbols.RHOOK);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("Longrightarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.BIG_RELBAR,
                       new SpaceAtom(TeXLength.Unit.EM, -0.177),
                       Symbols.BIG_RIGHTARROW);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("Longleftarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.BIG_LEFTARROW,
                       new SpaceAtom(TeXLength.Unit.EM, -0.177),
                       Symbols.BIG_RELBAR);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("longleftarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.LEFTARROW,
                       new SpaceAtom(TeXLength.Unit.EM, -0.206),
                       Symbols.MINUS.changeType(TeXConstants.TYPE_RELATION));
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("longrightarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.MINUS.changeType(TeXConstants.TYPE_RELATION),
                       new SpaceAtom(TeXLength.Unit.EM, -0.206),
                       Symbols.RIGHTARROW);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("longleftrightarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.LEFTARROW,
                       new SpaceAtom(TeXLength.Unit.EM, -0.180),
                       Symbols.RIGHTARROW);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put("Longleftrightarrow", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(3);
                ra.add(Symbols.BIG_LEFTARROW,
                       new SpaceAtom(TeXLength.Unit.EM, -0.180),
                       Symbols.BIG_RIGHTARROW);
                ra.setShape(true);
                return new TypedAtom(TeXConstants.TYPE_RELATION, ra);
            }
        });
        map.put(" ", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom();
            }
        });
        map.put("nbsp", map.get(" "));
        map.put("nobreaskspace", map.get(" "));
        map.put("space", map.get(" "));
        map.put("{", new Command0A() {
            public Atom newI(TeXParser tp) {
                return Symbols.LBRACE;
            }
        });
        map.put("}", new Command0A() {
            public Atom newI(TeXParser tp) {
                return Symbols.RBRACE;
            }
        });
        map.put("nolimits", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Atom a = tp.getLastAtom();
                if (a != null) {
                    tp.addToConsumer(a.changeLimits(TeXConstants.SCRIPT_NOLIMITS));
                }
                return false;
            }
        });
        map.put("limits", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Atom a = tp.getLastAtom();
                if (a != null) {
                    tp.addToConsumer(a.changeLimits(TeXConstants.SCRIPT_LIMITS));
                }
                return false;
            }
        });
        map.put("normal", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Atom a = tp.getLastAtom();
                if (a != null) {
                    tp.addToConsumer(a.changeLimits(TeXConstants.SCRIPT_NORMAL));
                }
                return false;
            }
        });
        map.put("surd", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new VCenteredAtom(SymbolAtom.get("surdsign"));
            }
        });
        map.put("vcenter", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new VCenteredAtom(a);
            }
        });
        map.put("int", new Command0A() {
            public Atom newI(TeXParser tp) {
                return Symbols.INT;
            }
        });
        map.put("intop", new Command0A() {
            public Atom newI(TeXParser tp) {
                return Symbols.INTOP;
            }
        });
        map.put("oint", new Command0A() {
            public Atom newI(TeXParser tp) {
                return Symbols.OINT;
            }
        });
        map.put("iint", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom integral = Symbols.INT;
                final SpaceAtom six = new SpaceAtom(TeXLength.Unit.MU, -6., 0., 0.);
                final SpaceAtom nine = new SpaceAtom(TeXLength.Unit.MU, -9., 0., 0.);
                final Atom choice = new MathchoiceAtom(nine, six, six, six);
                final RowAtom ra = new RowAtom(integral, choice, integral);
                ra.lookAtLastAtom = true;
                return ra.changeType(TeXConstants.TYPE_BIG_OPERATOR);
            }
        });
        map.put("iiint", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom integral = Symbols.INT;
                final SpaceAtom six = new SpaceAtom(TeXLength.Unit.MU, -6., 0., 0.);
                final SpaceAtom nine = new SpaceAtom(TeXLength.Unit.MU, -9., 0., 0.);
                final Atom choice = new MathchoiceAtom(nine, six, six, six);
                final RowAtom ra = new RowAtom(integral, choice, integral, choice, integral);
                ra.lookAtLastAtom = true;
                return ra.changeType(TeXConstants.TYPE_BIG_OPERATOR);
            }
        });
        map.put("iiiint", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom integral = Symbols.INT;
                final SpaceAtom six = new SpaceAtom(TeXLength.Unit.MU, -6., 0., 0.);
                final SpaceAtom nine = new SpaceAtom(TeXLength.Unit.MU, -9., 0., 0.);
                final Atom choice = new MathchoiceAtom(nine, six, six, six);
                final RowAtom ra = new RowAtom(integral, choice, integral, choice, integral, choice, integral);
                ra.lookAtLastAtom = true;
                return ra.changeType(TeXConstants.TYPE_BIG_OPERATOR);
            }
        });
        map.put("idotsint", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom integral = Symbols.INT;
                final Atom cdotp = Symbols.CDOTP;
                final SpaceAtom sa = new SpaceAtom(TeXLength.Unit.MU, -1., 0., 0.);
                final RowAtom cdots = new RowAtom(cdotp, cdotp, cdotp);
                final RowAtom ra = new RowAtom(integral,
                                               sa,
                                               cdots.changeType(TeXConstants.TYPE_INNER),
                                               sa,
                                               integral);
                ra.lookAtLastAtom = true;
                return ra.changeType(TeXConstants.TYPE_BIG_OPERATOR);
            }
        });
        map.put("lmoustache", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom at = new BigDelimiterAtom(SymbolAtom.get("lmoustache"), 1);
                at.setType(TeXConstants.TYPE_OPENING);
                return at;
            }
        });
        map.put("rmoustache", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom at = new BigDelimiterAtom(SymbolAtom.get("rmoustache"), 1);
                at.setType(TeXConstants.TYPE_CLOSING);
                return at;
            }
        });
        map.put("-", new Command0A() {
            public Atom newI(TeXParser tp) {
                return BreakMarkAtom.get();
            }
        });
        map.put("frac", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new FractionAtom(a, b);
            }
        });
        map.put("genfrac", new CommandGenfrac());
        map.put("dfrac", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return CommandGenfrac.get(null, a, b, null, null, 0);
            }
        });
        map.put("tfrac", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return CommandGenfrac.get(null, a, b, null, null, 1);
            }
        });
        map.put("dbinom", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                final SymbolAtom left = Symbols.LBRACK;
                final SymbolAtom right = Symbols.RBRACK;
                return CommandGenfrac.get(left, a, b, right, TeXLength.getZero(), 0);
            }
        });
        map.put("tbinom", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                final SymbolAtom left = Symbols.LBRACK;
                final SymbolAtom right = Symbols.RBRACK;
                return CommandGenfrac.get(left, a, b, right, TeXLength.getZero(), 1);
            }
        });
        map.put("binom", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                final SymbolAtom left = Symbols.LBRACK;
                final SymbolAtom right = Symbols.RBRACK;
                return new FencedAtom(new FractionAtom(a, b, false), left, right);
            }
        });
        map.put("over", new CommandOver() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new OverAtom(a, b);
            }
        });
        map.put("buildrel", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                if (a instanceof OverAtom) {
                    final Atom over = ((OverAtom)a).getNum();
                    final Atom base = ((OverAtom)a).getDen();
                    return new BuildrelAtom(base, over);
                }
                return a;
            }
        });
        map.put("choose", new CommandChoose(Symbols.LBRACK, Symbols.RBRACK));
        map.put("brace", new CommandChoose(Symbols.LBRACE, Symbols.RBRACE));
        map.put("bangle", new CommandChoose(Symbols.LANGLE, Symbols.RANGLE));
        map.put("brack", new CommandChoose(Symbols.LSQBRACK, Symbols.RSQBRACK));
        map.put("overwithdelims", new CommandOverwithdelims() {
            public Atom newI(TeXParser tp, Atom num, Atom den) {
                return new FractionAtom(num, den, true);
            }
        });
        map.put("atopwithdelims", new CommandOverwithdelims() {
            public Atom newI(TeXParser tp, Atom num, Atom den) {
                return new FractionAtom(num, den, false);
            }
        });
        map.put("abovewithdelims", new CommandOverwithdelims() {
            TeXLength l;

            public void add(TeXParser tp, Atom a) {
                if (left == null) {
                    left = a;
                } else if (right == null) {
                    right = a;
                    l = tp.getArgAsLength();
                } else {
                    den.add(a);
                }
            }

            public Atom newI(TeXParser tp, Atom num, Atom den) {
                return new FractionAtom(num, den, l);
            }
        });
        map.put("above", new CommandOver() {
            TeXLength len;

            public boolean init(TeXParser tp) {
                super.init(tp);
                len = tp.getArgAsLength();
                return false;
            }

            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new FractionAtom(a, b, len);
            }
        });
        map.put("atop", new CommandOver() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new FractionAtom(a, b, false);
            }
        });
        map.put("sqrt", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new NthRoot(b, a);
            }
        });
        map.put("fcscore", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final int arg = tp.getArgAsPositiveInteger();
                tp.addToConsumer(FcscoreAtom.get(arg));
                return false;
            }
        });
        map.put("longdiv", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final long dividend = tp.getArgAsPositiveInteger();
                final long divisor = tp.getArgAsPositiveInteger();
                tp.addToConsumer(new LongdivAtom(divisor, dividend, tp));
                return false;
            }
        });
        map.put("st", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new StrikeThroughAtom(a);
            }
        });
        map.put("includegraphics", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Map<String, String> arg1 = tp.getOptionAsMap();
                final String arg2 = tp.getArgAsString();
                tp.addToConsumer(new GraphicsAtom(arg2, arg1));
                return false;
            }
        });
        map.put("clap", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new LapedAtom(a, 'c');
            }
        });
        map.put("rlap", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new LapedAtom(a, 'r');
            }
        });
        map.put("llap", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new LapedAtom(a, 'l');
            }
        });
        map.put("mathclap", map.get("clap"));
        map.put("mathrlap", map.get("rlap"));
        map.put("mathllap", map.get("llap"));
        map.put("begin", new CommandBE.Begin());
        map.put("end", new CommandBE.End());
        map.put("begin@array", new EnvArray.Begin("array", ArrayAtom.ARRAY));
        map.put("end@array", new EnvArray.End("array"));
        map.put("begin@tabular", new EnvArray.Begin("tabular", ArrayAtom.ARRAY));
        map.put("end@tabular", new EnvArray.End("tabular"));
        map.put("\\", new CommandCr("\\"));
        map.put("begin@eqnarray", new EnvArray.Begin("eqnarray",
                ArrayAtom.ARRAY,
                new ArrayOptions(3).addAlignment(TeXConstants.Align.RIGHT).addAlignment(TeXConstants.Align.CENTER).addAlignment(TeXConstants.Align.LEFT).close()));
        map.put("end@eqnarray", new EnvArray.End("eqnarray"));
        map.put("begin@split", new EnvArray.Begin("split",
                ArrayAtom.ARRAY,
                new ArrayOptions(2).addAlignment(TeXConstants.Align.RIGHT).addAlignment(TeXConstants.Align.LEFT).close()));
        map.put("end@split", new EnvArray.End("split"));
        map.put("begin@cases", new EnvArray.Begin("cases",
                ArrayAtom.ARRAY,
                new ArrayOptions(3).addAlignment(TeXConstants.Align.LEFT).addSeparator(new SpaceAtom(TeXConstants.Muskip.NEGTHIN)).addAlignment(TeXConstants.Align.LEFT).close()));
        map.put("end@cases", new EnvArray.End("cases") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                final SymbolAtom op = Symbols.LBRACE;
                final SymbolAtom cl = Symbols.RBRACE;
                return new FencedAtom(super.newI(tp, beg), op, null, cl);
            }
        });
        map.put("matrix", new CommandMatrix() {
            public Atom newI(TeXParser tp) {
                return new SMatrixAtom(aoa, false);
            }
        });
        map.put("array", map.get("matrix"));
        map.put("ooalign", new CommandOoalign());
        map.put("pmatrix", new CommandMatrix() {
            public Atom newI(TeXParser tp) {
                return new FencedAtom(new SMatrixAtom(aoa, false), Symbols.LBRACK, Symbols.RBRACK);
            }
        });
        map.put("begin@matrix", new EnvArray.Begin("matrix", ArrayAtom.MATRIX, ArrayOptions.getEmpty()));
        map.put("end@matrix", new EnvArray.End("matrix") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                return new SMatrixAtom(beg.aoa, false);
            }
        });
        map.put("begin@smallmatrix", new EnvArray.Begin("smallmatrix", ArrayAtom.SMALLMATRIX, ArrayOptions.getEmpty()));
        map.put("end@smallmatrix", new EnvArray.End("smallmatrix") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                return new SMatrixAtom(beg.aoa, true);
            }
        });
        map.put("begin@align", new EnvArray.Begin("align", ArrayAtom.ALIGN, ArrayOptions.getEmpty()));
        map.put("end@align", new EnvArray.End("align") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                return new AlignAtom(beg.aoa, false);
            }
        });
        map.put("begin@aligned", new EnvArray.Begin("aligned", ArrayAtom.ALIGNED, ArrayOptions.getEmpty()));
        map.put("end@aligned", new EnvArray.End("aligned") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                return new AlignAtom(beg.aoa, true);
            }
        });
        map.put("begin@flalign", new EnvArray.Begin("flalign", ArrayAtom.FLALIGN, ArrayOptions.getEmpty()));
        map.put("end@flalign", new EnvArray.End("flalign") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                return new FlalignAtom(beg.aoa);
            }
        });
        map.put("begin@alignat", new EnvArray.Begin("alignat", ArrayAtom.ALIGNAT, ArrayOptions.getEmpty()) {
            public boolean init(TeXParser tp) {
                n = tp.getArgAsPositiveInteger();
                if (n <= 0) {
                    throw new ParseException(tp, "Invalid argument in alignat environment");
                }
                aoa = new ArrayOfAtoms(ArrayAtom.ALIGNAT);
                tp.addConsumer(this);
                tp.addConsumer(aoa);
                return false;
            }
        });
        map.put("end@alignat", new EnvArray.End("alignat") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                if (2 * beg.n != beg.aoa.col) {
                    throw new ParseException(tp, "Bad number of equations in alignat environment !");
                }
                return new AlignAtAtom(beg.aoa, false);
            }
        });
        map.put("begin@alignedat", new EnvArray.Begin("alignedat", ArrayAtom.ALIGNEDAT, ArrayOptions.getEmpty()) {
            public boolean init(TeXParser tp) {
                n = tp.getArgAsPositiveInteger();
                if (n <= 0) {
                    throw new ParseException(tp, "Invalid argument in alignedat environment");
                }
                aoa = new ArrayOfAtoms(ArrayAtom.ALIGNEDAT);
                tp.addConsumer(this);
                tp.addConsumer(aoa);
                return false;
            }
        });
        map.put("end@alignedat", new EnvArray.End("alignedat") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                if (2 * beg.n != beg.aoa.col) {
                    throw new ParseException(tp, "Bad number of equations in alignedat environment !");
                }
                return new AlignAtAtom(beg.aoa, true);
            }
        });
        map.put("begin@multline", new EnvArray.Begin("multline", -1, ArrayOptions.getEmpty()) {
            public boolean init(TeXParser tp) {
                final boolean r = super.init(tp);
                aoa.setOneColumn(true);
                return false;
            }
        });
        map.put("end@multline", new EnvArray.End("multline") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                if (beg.aoa.col == 0) {
                    return EmptyAtom.get();
                }
                return new MultlineAtom(beg.aoa, MultlineAtom.MULTLINE);
            }
        });
        map.put("begin@subarray", new EnvArray.Begin("subarray", -1) {
            public boolean init(TeXParser tp) {
                final boolean r = super.init(tp);
                aoa.setOneColumn(true);
                return false;
            }
        });
        map.put("end@subarray", new EnvArray.End("subarray") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                if (beg.aoa.col == 0) {
                    return EmptyAtom.get();
                }
                return new SubarrayAtom(beg.getAOA(), beg.getOptions());
            }
        });
        map.put("substack", new CommandSubstack());
        map.put("displaylines", new CommandDisplaylines());
        map.put("begin@gather", new EnvArray.Begin("gather", -1, ArrayOptions.getEmpty()) {
            public boolean init(TeXParser tp) {
                final boolean r = super.init(tp);
                aoa.setOneColumn(true);
                return false;
            }
        });
        map.put("end@gather", new EnvArray.End("gather") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                if (beg.aoa.col == 0) {
                    return EmptyAtom.get();
                }
                return new MultlineAtom(beg.aoa, MultlineAtom.GATHER);
            }
        });
        map.put("begin@gathered", new EnvArray.Begin("gathered", -1, ArrayOptions.getEmpty()) {
            public boolean init(TeXParser tp) {
                final boolean r = super.init(tp);
                aoa.setOneColumn(true);
                return false;
            }
        });
        map.put("end@gathered", new EnvArray.End("gathered") {
            public Atom newI(TeXParser tp, EnvArray.Begin beg) {
                if (beg.aoa.col == 0) {
                    return EmptyAtom.get();
                }
                return new MultlineAtom(beg.aoa, MultlineAtom.GATHERED);
            }
        });
        map.put("begin@pmatrix", new EnvArray.Begin("pmatrix", ArrayAtom.MATRIX, ArrayOptions.getEmpty()));
        map.put("end@pmatrix", new EnvArray.End("pmatrix", "lbrack", "rbrack"));
        map.put("begin@bmatrix", new EnvArray.Begin("bmatrix", ArrayAtom.MATRIX, ArrayOptions.getEmpty()));
        map.put("end@bmatrix", new EnvArray.End("bmatrix", "lsqbrack", "rsqbrack"));
        map.put("begin@vmatrix", new EnvArray.Begin("bmatrix", ArrayAtom.MATRIX, ArrayOptions.getEmpty()));
        map.put("end@vmatrix", new EnvArray.End("bmatrix", "vert"));
        map.put("begin@Vmatrix", new EnvArray.Begin("Vmatrix", ArrayAtom.MATRIX, ArrayOptions.getEmpty()));
        map.put("end@Vmatrix", new EnvArray.End("Vmatrix", "Vert"));
        map.put(",", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXConstants.Muskip.THIN);
            }
        });
        map.put("thinspace", map.get(","));
        map.put(":", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXConstants.Muskip.MED);
            }
        });
        map.put("medspace", map.get(":"));
        map.put(">", map.get(":"));
        map.put(";", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXConstants.Muskip.THICK);
            }
        });
        map.put("thickspace", map.get(";"));
        map.put("!", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXConstants.Muskip.NEGTHIN);
            }
        });
        map.put("negthinspace", map.get("!"));
        map.put("negmedspace", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXConstants.Muskip.NEGMED);
            }
        });
        map.put("negthickspace", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXConstants.Muskip.NEGTHICK);
            }
        });
        map.put("enspace", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXLength.Unit.EM, 0.5, 0., 0.);
            }
        });
        map.put("quad", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXLength.Unit.EM, 1., 0., 0.);
            }
        });
        map.put("qquad", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXLength.Unit.EM, 2., 0., 0.);
            }
        });
        map.put("Space", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXLength.Unit.EM, 3., 0., 0.);
            }
        });
        map.put("textcircled", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TextCircledAtom(new RomanAtom(a));
            }
        });
        map.put("romannumeral", new CommandRomNum(false));
        map.put("Romannumeral", new CommandRomNum(true));
        map.put("T", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RotateAtom(a, 180., new HashMap<String, String>() {
                    {
                        put("origin", "cc");
                    }
                });
            }
        });
        map.put("char", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final char c = tp.getArgAsCharFromCode();
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    tp.convertASCIIChar(c, true);
                } else {
                    tp.convertCharacter(c, true);
                }
                return false;
            }
        });
        map.put("unicode", new CommandUnicode());
        map.put("kern", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                tp.addToConsumer(new SpaceAtom(tp.getArgAsLength()));
                return false;
            }
        });
        map.put("Dstrok", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(new SpaceAtom(TeXLength.Unit.EX, -0.1, 0., 0.), Symbols.BAR);
                final VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
                vra.setRaise(TeXLength.Unit.EX, -0.55);
                return new RowAtom(vra, new RomanAtom(new CharAtom('D', TextStyle.MATHNORMAL)));
            }
        });
        map.put("dstrok", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(new SpaceAtom(TeXLength.Unit.EX, 0.25, 0., 0.), Symbols.BAR);
                final VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
                vra.setRaise(TeXLength.Unit.EX, -0.1);
                return new RowAtom(vra, new RomanAtom(new CharAtom('d', TextStyle.MATHNORMAL)));
            }
        });
        map.put("Hstrok", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(new SpaceAtom(TeXLength.Unit.EX, 0.28, 0., 0.), Symbols.TEXTENDASH);
                final VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
                vra.setRaise(TeXLength.Unit.EX, 0.55);
                return new RowAtom(vra, new RomanAtom(new CharAtom('H', TextStyle.MATHNORMAL)));
            }
        });
        map.put("hstrok", new Command0A() {
            public Atom newI(TeXParser tp) {
                final RowAtom ra = new RowAtom(new SpaceAtom(TeXLength.Unit.EX, -0.1, 0., 0.), Symbols.BAR);
                final VRowAtom vra = new VRowAtom(new LapedAtom(ra, 'r'));
                vra.setRaise(TeXLength.Unit.EX, -0.1);
                return new RowAtom(vra, new RomanAtom(new CharAtom('h', TextStyle.MATHNORMAL)));
            }
        });
        map.put("smallfrowneq", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom at = new UnderOverAtom(Symbols.EQUALS,
                                                  Symbols.SMALLFROWN,
                                                  new TeXLength(TeXLength.Unit.MU, -2.),
                                                  true, true);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("frowneq", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom at = new UnderOverAtom(Symbols.EQUALS,
                                                  Symbols.FROWN,
                                                  TeXLength.getZero(),
                                                  true, true);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("coloncolonapprox", new CommandColonFoo.ColonColonFoo("approx"));
        map.put("colonapprox", new CommandColonFoo.ColonFoo("approx"));
        map.put("coloncolonsim", new CommandColonFoo.ColonColonFoo("sim"));
        map.put("colonsim", new CommandColonFoo.ColonFoo("sim"));
        map.put("coloncolon", new CommandColonFoo.ColonColonFoo());
        map.put("coloncolonequals", new CommandColonFoo.ColonColonFoo("equals"));
        map.put("colonequals", new CommandColonFoo.ColonFoo("equals"));
        map.put("coloncolonminus", new CommandColonFoo.ColonColonFoo("minus"));
        map.put("colonminus", new CommandColonFoo.ColonFoo("minus"));
        map.put("equalscoloncolon", new CommandColonFoo.FooColonColon("equals"));
        map.put("equalscolon", new CommandColonFoo.FooColon("equals"));
        map.put("minuscoloncolon", new CommandColonFoo.FooColonColon("minus"));
        map.put("minuscolon", new CommandColonFoo.FooColon("minus"));
        map.put("simcoloncolon", new CommandColonFoo.FooColonColon("sim"));
        map.put("simcolon", new CommandColonFoo.FooColon("sim"));
        map.put("approxcoloncolon", new CommandColonFoo.FooColonColon("approx"));
        map.put("approxcolon", new CommandColonFoo.FooColon("approx"));
        map.put("geoprop", new Command0A() {
            public Atom newI(TeXParser tp) {
                final SymbolAtom nd = Symbols.NORMALDOT;
                final RowAtom ddot = new RowAtom(nd, new SpaceAtom(TeXLength.Unit.MU, 4., 0., 0.), nd);
                final TeXLength l = new TeXLength(TeXLength.Unit.MU, -3.4);
                Atom at = new UnderOverAtom(Symbols.MINUS, ddot, l,
                                            false, ddot, l, false);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("ratio", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom a = new VCenteredAtom(Symbols.COLON.changeType(TeXConstants.TYPE_ORDINARY));
                return a.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("dotminus", new Command0A() {
            public Atom newI(TeXParser tp) {
                Atom at = new UnderOverAtom(Symbols.MINUS, Symbols.NORMALDOT,
                                            new TeXLength(TeXLength.Unit.EX, -0.4), false, true);
                return at.changeType(TeXConstants.TYPE_BINARY_OPERATOR);
            }
        });
        map.put("tiny", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 0.5);
            }
        });
        map.put("Tiny", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 0.6);
            }
        });
        map.put("scriptsize", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 0.7);
            }
        });
        map.put("footnotesize", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 0.8);
            }
        });
        map.put("small", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 0.9);
            }
        });
        map.put("normalsize", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 1.);
            }
        });
        map.put("large", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 1.2);
            }
        });
        map.put("Large", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 1.4);
            }
        });
        map.put("LARGE", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 1.8);
            }
        });
        map.put("huge", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 2.);
            }
        });
        map.put("Huge", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MonoScaleAtom(a, 2.5);
            }
        });
        map.put("sc", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new SmallCapAtom(a);
            }
        });
        map.put("hline", new Command0A() {
            public Atom newI(TeXParser tp) {
                if (!tp.isArrayMode()) {
                    throw new ParseException(tp, "The macro \\hline is only available in array mode !");
                }
                return new HlineAtom();
            }
        });
        map.put("cellcolor", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                if (!tp.isArrayMode()) {
                    throw new ParseException(tp, "The macro \\cellcolor is only available in array mode !");
                }
                final Color c = CommandDefinecolor.getColor(tp);
                tp.addToConsumer(new EnvArray.CellColor(c));
                return false;
            }
        });
        map.put("rowcolor", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                if (!tp.isArrayMode()) {
                    throw new ParseException(tp, "The macro \\rowcolor is only available in array mode !");
                }
                final Color c = CommandDefinecolor.getColor(tp);
                tp.addToConsumer(new EnvArray.RowColor(c));
                return false;
            }
        });
        map.put("jlmText", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final String arg = tp.getGroupAsArgument();
                tp.addToConsumer(new JavaFontRenderingAtom(arg, Font.PLAIN));
                return false;
            }
        });
        map.put("jlmTextit", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final String arg = tp.getGroupAsArgument();
                tp.addToConsumer(new JavaFontRenderingAtom(arg, Font.ITALIC));
                return false;
            }
        });
        map.put("jlmTextbf", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final String arg = tp.getGroupAsArgument();
                tp.addToConsumer(new JavaFontRenderingAtom(arg, Font.BOLD));
                return false;
            }
        });
        map.put("jlmTextitbf", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final String arg = tp.getGroupAsArgument();
                tp.addToConsumer(new JavaFontRenderingAtom(arg, Font.BOLD | Font.ITALIC));
                return false;
            }
        });
        map.put("jlmExternalFont", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final String fontname = tp.getArgAsString();
                JavaFontRenderingBox.setFont(fontname);
                return false;
            }
        });
        map.put("jlmDynamic", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                if (DynamicAtom.hasAnExternalConverterFactory()) {
                    final char opt = tp.getOptionAsChar();
                    final String arg = tp.getGroupAsArgument();
                    tp.addToConsumer(new DynamicAtom(arg, opt));

                    return false;
                }
                throw new ParseException(tp, "No ExternalConverterFactory set !");
            }
        });
        map.put("doteq", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom at = new UnderOverAtom(Symbols.EQUALS, Symbols.LDOTP,
                                                  new TeXLength(TeXLength.Unit.MU, 3.7),
                                                  false, true);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("cong", new Command0A() {
            public Atom newI(TeXParser tp) {
                final VRowAtom vra = new VRowAtom(Symbols.SIM,
                                                  new SpaceAtom(TeXLength.Unit.MU, 0., 1.5, 0.),
                                                  Symbols.EQUALS);
                vra.setRaise(TeXLength.Unit.MU, -1.);
                return vra.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("fbox", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new FBoxAtom(a);
            }
        });
        map.put("boxed", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new FBoxAtom(new MathAtom(a, TeXConstants.STYLE_DISPLAY));
            }
        });
        map.put("fcolorbox", new Command1A() {
            Color frame;
            Color bg;

            public boolean init(TeXParser tp) {
                frame = tp.getArgAsColor();
                bg = tp.getArgAsColor();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new FBoxAtom(a, bg, frame);
            }
        });
        map.put("colorbox", new Command1A() {
            Color bg;

            public boolean init(TeXParser tp) {
                bg = CommandDefinecolor.getColor(tp);
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new FBoxAtom(a, bg, bg);
            }
        });
        map.put("textcolor", new Command1A() {
            Color fg;

            public boolean init(TeXParser tp) {
                fg = CommandDefinecolor.getColor(tp);
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new ColorAtom(a, null, fg);
            }
        });
        map.put("color", new CommandTiny() {
            Color fg;

            public boolean init(TeXParser tp) {
                fg = CommandDefinecolor.getColor(tp);
                return super.init(tp);
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new ColorAtom(a, null, fg);
            }
        });
        map.put("bgcolor", new Command1A() {
            Color bg;

            public boolean init(TeXParser tp) {
                bg = CommandDefinecolor.getColor(tp);
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new ColorAtom(a, bg, null);
            }
        });
        map.put("fgcolor", map.get("textcolor"));
        map.put("definecolor", new CommandDefinecolor());
        map.put("doublebox", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new DoubleFramedAtom(a);
            }
        });
        map.put("ovalbox", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OvalAtom(a);
            }
        });
        map.put("shadowbox", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new ShadowAtom(a);
            }
        });
        map.put("raisebox", new Command1A() {
            TeXLength raise;
            TeXLength height;
            TeXLength depth;

            public boolean init(TeXParser tp) {
                raise = tp.getArgAsLength();
                height = tp.getOptionAsLength(null);
                depth = tp.getOptionAsLength(null);
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new RaiseAtom(a, raise, height, depth);
            }
        });
        map.put("raise", new Command1A() {
            TeXLength raise;

            public boolean init(TeXParser tp) {
                raise = tp.getArgAsLength();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new RaiseAtom(a, raise, null, null);
            }
        });
        map.put("lower", new Command1A() {
            TeXLength lower;

            public boolean init(TeXParser tp) {
                lower = tp.getArgAsLength();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new RaiseAtom(a, lower.scale(-1.), null, null);
            }
        });
        map.put("moveleft", new Command1A() {
            TeXLength left;

            public boolean init(TeXParser tp) {
                left = tp.getArgAsLength();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new RowAtom(new SpaceAtom(left.scale(-1.)), a);
            }
        });
        map.put("moveright", new Command1A() {
            TeXLength right;

            public boolean init(TeXParser tp) {
                right = tp.getArgAsLength();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new RowAtom(new SpaceAtom(right), a);
            }
        });
        map.put("resizebox", new Command1A() {
            TeXLength width;
            TeXLength height;

            public boolean init(TeXParser tp) {
                width = tp.getArgAsLengthOrExcl();
                height = tp.getArgAsLengthOrExcl();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new ResizeAtom(a, width, height);
            }
        });
        map.put("scalebox", new Command1A() {
            double hscale;
            double vscale;

            public boolean init(TeXParser tp) {
                hscale = tp.getArgAsDecimal();
                vscale = tp.getOptionAsDecimal(hscale);
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new ScaleAtom(a, hscale, vscale);
            }
        });
        map.put("reflectbox", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new ReflectAtom(a);
            }
        });
        map.put("rotatebox", new Command1A() {
            double angle;
            Map<String, String> options;

            public boolean init(TeXParser tp) {
                options = tp.getOptionAsMap();
                angle = tp.getArgAsDecimal();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new RotateAtom(a, angle, options);
            }
        });
        map.put("scriptscriptstyle", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new StyleAtom(TeXConstants.STYLE_SCRIPT_SCRIPT, a);
            }
        });
        map.put("textstyle", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new StyleAtom(TeXConstants.STYLE_TEXT, a);
            }
        });
        map.put("scriptstyle", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new StyleAtom(TeXConstants.STYLE_SCRIPT, a);
            }
        });
        map.put("displaystyle", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new StyleAtom(TeXConstants.STYLE_DISPLAY, a);
            }
        });
        map.put("Biggr", new CommandBigr(TeXConstants.TYPE_CLOSING, 4));
        map.put("biggr", new CommandBigr(TeXConstants.TYPE_CLOSING, 3));
        map.put("Bigr", new CommandBigr(TeXConstants.TYPE_CLOSING, 2));
        map.put("bigr", new CommandBigr(TeXConstants.TYPE_CLOSING, 1));
        map.put("Biggl", new CommandBigr(TeXConstants.TYPE_OPENING, 4));
        map.put("biggl", new CommandBigr(TeXConstants.TYPE_OPENING, 3));
        map.put("Bigl", new CommandBigr(TeXConstants.TYPE_OPENING, 2));
        map.put("bigl", new CommandBigr(TeXConstants.TYPE_OPENING, 1));
        map.put("Biggm", new CommandBigr(TeXConstants.TYPE_RELATION, 4));
        map.put("biggm", new CommandBigr(TeXConstants.TYPE_RELATION, 3));
        map.put("Bigm", new CommandBigr(TeXConstants.TYPE_RELATION, 2));
        map.put("bigm", new CommandBigr(TeXConstants.TYPE_RELATION, 1));
        map.put("Bigg", new CommandBigg(4));
        map.put("bigg", new CommandBigg(3));
        map.put("Big", new CommandBigg(2));
        map.put("big", new CommandBigg(1));
        map.put("mathstrut", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new PhantomAtom(Symbols.LBRACK.changeType(TeXConstants.TYPE_ORDINARY),
                                       false, true, true);
            }
        });
        map.put("phantom", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new PhantomAtom(a, true, true, true);
            }
        });
        map.put("vphantom", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new PhantomAtom(a, false, true, true);
            }
        });
        map.put("hphantom", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new PhantomAtom(a, true, false, false);
            }
        });
        map.put("LaTeX", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new LaTeXAtom();
            }
        });
        map.put("mathcal", new CommandTextStyle(TextStyle.MATHCAL));
        map.put("cal", new CommandTextStyleTeX(TextStyle.MATHCAL));
        map.put("mathfrak", new CommandTextStyle(TextStyle.MATHFRAK));
        map.put("frak", new CommandTextStyleTeX(TextStyle.MATHFRAK));
        map.put("mathbb", new CommandTextStyle(TextStyle.MATHBB));
        map.put("Bbb", new CommandTextStyleTeX(TextStyle.MATHBB));
        map.put("mathscr", new CommandTextStyle(TextStyle.MATHSCR));
        map.put("scr", new CommandTextStyleTeX(TextStyle.MATHSCR));
        map.put("mathds", new CommandTextStyle(TextStyle.MATHDS));
        map.put("oldstylenums", new CommandTextStyle(TextStyle.OLDSTYLENUMS));
        map.put("mathsf", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new SsAtom(a);
            }
        });
        map.put("sf", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new SsAtom(a);
            }
        });
        map.put("mathrm", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RomanAtom(a);
            }
        });
        map.put("rm", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RomanAtom(a);
            }
        });
        map.put("mathit", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new ItAtom(a);
            }
        });
        map.put("mit", map.get("mathit"));
        map.put("it", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new ItAtom(a);
            }
        });
        map.put("mathtt", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TtAtom(a);
            }
        });
        map.put("tt", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TtAtom(a);
            }
        });
        map.put("mathbf", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new BoldAtom(new RomanAtom(a));
            }
        });
        map.put("bf", new CommandTiny() {
            public Atom newI(TeXParser tp, Atom a) {
                return new BoldAtom(new RomanAtom(a));
            }
        });
        map.put("bold", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new BoldAtom(a);
            }
        });
        map.put("boldsymbol", map.get("bold"));
        map.put("undertilde", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverAtom(a, new AccentedAtom(new PhantomAtom(a, true, false, false), Symbols.WIDETILDE), new TeXLength(TeXLength.Unit.MU, 0.3), true, false);
            }
        });
        map.put("b", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverAtom(a, Symbols.BAR, new TeXLength(TeXLength.Unit.MU, 0.1),
                                         false, false);
            }
        });
        map.put("underaccent", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                // TODO: take into account the italic correction
                // \\underaccent{\\hat}{x}\\underaccent{\\bar}{\\gamma}
                // TODO: verifier que le 0.3 ds undertilde est correct
                // Ca marche pas ce truc parce que \\hat est une command a 1 arg...
                return new UnderOverAtom(b, a, new TeXLength(TeXLength.Unit.MU, 0.1),
                                         false, false);
            }
        });
        map.put("accentset", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                if (a instanceof SymbolAtom) {
                    return new AccentedAtom(b, (SymbolAtom)a);
                } else {
                    return new AccentSetAtom(b, a);
                }
            }
        });
        map.put("underset", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                final Atom at = new UnderOverAtom(b, a,
                                                  new TeXLength(TeXLength.Unit.MU, 0.2),
                                                  true, false);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("overset", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                final Atom at = new UnderOverAtom(b, a,
                                                  new TeXLength(TeXLength.Unit.MU, 2.5),
                                                  true, true);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("stackbin", new Command1O2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
                final Atom at = new UnderOverAtom(c, a,
                                                  new TeXLength(TeXLength.Unit.MU, 3.5),
                                                  true, b, new TeXLength(TeXLength.Unit.MU, 3.),
                                                  true);
                return at.changeType(TeXConstants.TYPE_BINARY_OPERATOR);
            }
        });
        map.put("stackrel", new Command1O2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
                final Atom at = new UnderOverAtom(c, a,
                                                  new TeXLength(TeXLength.Unit.MU, 3.5),
                                                  true, b, new TeXLength(TeXLength.Unit.MU, 3.),
                                                  true);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("questeq", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom at = new UnderOverAtom(Symbols.EQUALS, Symbols.QUESTION,
                                                  new TeXLength(TeXLength.Unit.MU, 2.5),
                                                  true, true);
                return at.changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("eqdef", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new BuildrelAtom(Symbols.EQUALS, new RomanAtom(TeXParser.getAtomForLatinStr("def", true)));
            }
        });
        map.put("shoveleft", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AlignedAtom(a, TeXConstants.Align.LEFT);
            }
        });
        map.put("shoveright", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AlignedAtom(a, TeXConstants.Align.RIGHT);
            }
        });
        map.put("hdotsfor", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                if (!tp.isArrayMode()) {
                    throw new ParseException(tp, "The macro \\hdotsfor is only available in array mode !");
                }
                final double x = tp.getOptionAsDecimal(1.);
                final int n = tp.getArgAsPositiveInteger();
                if (n == -1) {
                    throw new ParseException(tp, "The macro \\hdotsfor requires a positive integer");
                }
                tp.addToConsumer(new HdotsforAtom(n, x));
                return false;
            }
        });
        map.put("multicolumn", new Command1A() {
            int n;
            ArrayOptions options;

            public boolean init(TeXParser tp) {
                if (!tp.isArrayMode()) {
                    throw new ParseException(tp, "The macro \\multicolumn is only available in array mode !");
                }
                n = tp.getArgAsPositiveInteger();
                if (n == -1) {
                    throw new ParseException(tp, "The macro \\multicolumn requires a positive integer");
                }
                options = tp.getArrayOptions();
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new MulticolumnAtom(n, options, a);
            }
        });
        map.put("intertext", new Command() {
            boolean mode;

            public boolean init(TeXParser tp) {
                if (!tp.isArrayMode()) {
                    throw new ParseException(tp, "The macro \\intertext is only available in array mode !");
                }
                mode = tp.setTextMode();
                return true;
            }

            public void add(TeXParser tp, Atom a) {
                tp.setMathMode(mode);
                a = new TextStyleAtom(a, TextStyle.MATHNORMAL);
                a = new StyleAtom(TeXConstants.STYLE_TEXT, new RomanAtom(a));
                tp.closeConsumer(a.changeType(TeXConstants.TYPE_INTERTEXT));
            }
        });
        map.put("cr", new CommandCr("cr"));
        map.put("newline", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                tp.close();
                if (tp.isArrayMode()) {
                    tp.addToConsumer(EnvArray.RowSep.get());
                    return false;
                }
                throw new ParseException(tp, "The macro \\newline must be used in an array");
            }
        });
        map.put("begin@math", new CommandMathStyles.OpenBracket(TeXConstants.Opener.BEGIN_MATH));
        map.put("end@math", new CommandMathStyles.CloseBracket(TeXConstants.Opener.BEGIN_MATH, TeXConstants.STYLE_TEXT, "The command \\) doesn't match any \\("));
        map.put("[", new CommandMathStyles.OpenBracket(TeXConstants.Opener.B_LSQBRACKET));
        map.put("]", new CommandMathStyles.CloseBracket(TeXConstants.Opener.B_LSQBRACKET, TeXConstants.STYLE_DISPLAY, "The command \\] doesn't match any \\["));
        map.put("displaymath", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MathAtom(a, TeXConstants.STYLE_DISPLAY);
            }
        });
        map.put("(", new CommandMathStyles.OpenBracket(TeXConstants.Opener.B_LBRACKET));
        map.put(")", new CommandMathStyles.CloseBracket(TeXConstants.Opener.B_LBRACKET, TeXConstants.STYLE_TEXT, "The command \\) doesn't match any \\("));
        map.put("math", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new MathAtom(a, TeXConstants.STYLE_TEXT);
            }
        });
        map.put("iddots", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new IddotsAtom();
            }
        });
        map.put("ddots", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new DdotsAtom();
            }
        });
        map.put("vdots", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new VdotsAtom();
            }
        });
        map.put("smash", new Command1A() {
            char opt;

            public boolean init(TeXParser tp) {
                opt = tp.getOptionAsChar();
                if (opt != 't' && opt != 'b' && opt != '\0') {
                    throw new ParseException(tp, "Invalid option in \\smash");
                }
                return true;
            }

            public Atom newI(TeXParser tp, Atom a) {
                return new SmashedAtom(a, opt);
            }
        });
        map.put("joinrel", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXLength.Unit.MU, -3, 0, 0).changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("mathclose", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_CLOSING, a);
            }
        });
        map.put("mathopen", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_OPENING, a);
            }
        });
        map.put("mathbin", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_BINARY_OPERATOR, a);
            }
        });
        map.put("mathinner", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_INNER, a);
            }
        });
        map.put("mathord", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_ORDINARY, a);
            }
        });
        map.put("mathpunct", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_PUNCTUATION, a);
            }
        });
        map.put("mathop", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                a = new TypedAtom(TeXConstants.TYPE_BIG_OPERATOR, a);
                a.type_limits = TeXConstants.SCRIPT_NORMAL;
                return a;
            }
        });
        map.put("mathrel", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TypedAtom(TeXConstants.TYPE_RELATION, a);
            }
        });
        map.put("underline", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderlinedAtom(a);
            }
        });
        map.put("overline", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverlinedAtom(a);
            }
        });
        map.put("overparen", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverUnderDelimiter(a, null, Symbols.LBRACK, TeXLength.Unit.EX, 0, true);
            }
        });
        map.put("underparen", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverUnderDelimiter(a, null, Symbols.RBRACK, TeXLength.Unit.EX, 0, false);
            }
        });
        map.put("overbrack", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverUnderDelimiter(a, null, Symbols.LSQBRACK, TeXLength.Unit.EX, 0, true);
            }
        });
        map.put("underbrack", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverUnderDelimiter(a, null, Symbols.RSQBRACK, TeXLength.Unit.EX, 0, false);
            }
        });
        map.put("overbrace", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverUnderDelimiter(a, null, Symbols.LBRACE, TeXLength.Unit.EX, 0, true);
            }
        });
        map.put("underbrace", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OverUnderDelimiter(a, null, Symbols.RBRACE, TeXLength.Unit.EX, 0, false);
            }
        });
        map.put("prescript", new Command3A() {
            public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
                final RowAtom ra = new RowAtom(new ScriptsAtom(new PhantomAtom(c, false, true, true), b, a, false),
                                               new SpaceAtom(TeXLength.Unit.MU, -0.3, 0., 0.),
                                               c.changeType(TeXConstants.TYPE_ORDINARY));
                ra.lookAtLast(true);
                return ra;
            }
        });
        map.put("sideset", new Command3A() {
            public Atom newI(TeXParser tp, Atom a, Atom b, Atom c) {
                final RowAtom ra = new RowAtom();
                c = c.changeLimits(TeXConstants.SCRIPT_NOLIMITS);
                if (a instanceof ScriptsAtom) {
                    ((ScriptsAtom)a).setBase(new PhantomAtom(c, false, true, true));
                } else if (a instanceof BigOperatorAtom) {
                    ((BigOperatorAtom)a).setBase(new PhantomAtom(c, false, true, true));
                }
                ra.add(new TypedAtom(TeXConstants.TYPE_ORDINARY, a));

                if (b instanceof ScriptsAtom) {
                    ((ScriptsAtom)b).setBase(c);
                } else if (b instanceof BigOperatorAtom) {
                    ((BigOperatorAtom)b).setBase(c);
                } else {
                    ra.add(c);
                }
                ra.add(new TypedAtom(TeXConstants.TYPE_ORDINARY, b));

                return ra;
            }
        });
        map.put("xmapsto", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XMapstoAtom(b, a);
            }
        });
        map.put("xlongequal", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XLongequalAtom(b, a);
            }
        });
        map.put("xrightarrow", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.Right);
            }
        });
        map.put("xleftarrow", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.Left);
            }
        });
        map.put("xhookleftarrow", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XHookAtom(b, a, true);
            }
        });
        map.put("xhookrightarrow", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XHookAtom(b, a, false);
            }
        });
        map.put("xleftrightarrow", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.LR);
            }
        });
        map.put("xrightharpoondown", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.RightHarpoonDown);
            }
        });
        map.put("xrightharpoonup", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.RightHarpoonUp);
            }
        });
        map.put("xleftharpoondown", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.LeftHarpoonDown);
            }
        });
        map.put("xleftharpoonup", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.LeftHarpoonUp);
            }
        });
        map.put("xleftrightharpoons", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.LeftRightHarpoons);
            }
        });
        map.put("xrightleftharpoons", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.RightLeftHarpoons);
            }
        });
        map.put("xleftrightarrows", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.LeftAndRight);
            }
        });
        map.put("xrightleftarrows", new Command1O1A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                return new XArrowAtom(b, a, XArrowAtom.Kind.RightAndLeft);
            }
        });
        map.put("underleftrightarrow", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverArrowAtom(a, false);
            }
        });
        map.put("underleftarrow", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverArrowAtom(a, true, false);
            }
        });
        map.put("underrightarrow", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverArrowAtom(a, false, false);
            }
        });
        map.put("overleftrightarrow", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverArrowAtom(a, true);
            }
        });
        map.put("overleftarrow", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverArrowAtom(a, true, true);
            }
        });
        map.put("overrightarrow", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new UnderOverArrowAtom(a, false, true);
            }
        });
        map.put("ogonek", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OgonekAtom(a);
            }
        });
        map.put("tcaron", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new tcaronAtom();
            }
        });
        map.put("Lcaron", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new LCaronAtom(true);
            }
        });
        map.put("lcaron", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new LCaronAtom(false);
            }
        });
        map.put("Tstroke", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new TStrokeAtom(true);
            }
        });
        map.put("tstroke", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new TStrokeAtom(false);
            }
        });
        map.put("IJ", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new IJAtom(true);
            }
        });
        map.put("ij", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new IJAtom(false);
            }
        });
        map.put("cedilla", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new CedillaAtom(a);
            }
        });
        map.put("~", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.TILDE);
            }
        });
        map.put("tilde", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.TILDE);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.TILDE);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("widetilde", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.WIDETILDE);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.WIDETILDE);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("'", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.ACUTE);
            }
        });
        map.put("acute", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.ACUTE);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.ACUTE);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("skew", new Command() {
            private double skew;

            public boolean init(TeXParser tp) {
                skew = tp.getArgAsDecimal();
                return true;
            }

            public void add(TeXParser tp, Atom a) {
                if (a instanceof AccentedAtom) {
                    ((AccentedAtom)a).setSkew(skew);
                    tp.closeConsumer(a);
                    return;
                }
                throw new ParseException(tp, "skew command is only working with an accent as second argument");
            }
        });
        map.put("^", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.HAT);
            }
        });
        map.put("hat", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.HAT);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.HAT);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("widehat", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.WIDEHAT);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.WIDEHAT);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("\"", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.DDOT);
            }
        });
        map.put("ddot", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.DDOT);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.DDOT);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("dddot", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new BuildrelAtom(a, new RowAtom(Symbols.TEXTNORMALDOT,
                                                       Symbols.TEXTNORMALDOT,
                                                       Symbols.TEXTNORMALDOT));
            }
        });
        map.put("ddddot", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new BuildrelAtom(a, new RowAtom(Symbols.TEXTNORMALDOT,
                                                       Symbols.TEXTNORMALDOT,
                                                       Symbols.TEXTNORMALDOT,
                                                       Symbols.TEXTNORMALDOT));
            }
        });
        map.put("`", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.GRAVE);
            }
        });
        map.put("grave", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.GRAVE);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.GRAVE);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("=", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.BAR);
            }
        });
        map.put("bar", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.BAR);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.BAR);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put(".", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.DOT);
            }
        });
        map.put("dot", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.DOT);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.DOT);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("cyrddot", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                // just load the cyrillic fonts
                AlphabetManager.get().addBlock(Character.UnicodeBlock.CYRILLIC);
                return new AccentedAtom(a, "cyrddot");
            }

            public boolean close(TeXParser tp) {
                AlphabetManager.get().addBlock(Character.UnicodeBlock.CYRILLIC);
                tp.closeConsumer(SymbolAtom.get("cyrddot"));
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("u", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.BREVE);
            }
        });
        map.put("breve", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.BREVE);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.BREVE);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("v", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.CHECK);
            }
        });
        map.put("check", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.CHECK);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.CHECK);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("H", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, "doubleacute");
            }
        });
        map.put("t", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, "tie");
            }
        });
        map.put("r", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.MATHRING);
            }
        });
        map.put("mathring", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.MATHRING);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.MATHRING);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("U", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                AlphabetManager.get().addBlock(Character.UnicodeBlock.CYRILLIC);
                return new AccentedAtom(a, "cyrbreve");
            }
        });
        map.put("vec", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new AccentedAtom(a, Symbols.VEC);
            }

            public boolean close(TeXParser tp) {
                tp.closeConsumer(Symbols.VEC);
                return true;
            }

            public boolean isClosable() {
                return true;
            }
        });
        map.put("accent", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                if (a instanceof SymbolAtom) {
                    return new AccentedAtom(b, (SymbolAtom)a);
                } else {
                    return new AccentSetAtom(b, a);
                }
            }
        });
        map.put("grkaccent", new Command2A() {
            public Atom newI(TeXParser tp, Atom a, Atom b) {
                // TODO: instanceof
                return new AccentedAtom(b, (SymbolAtom)a);
            }
        });
        map.put("underscore", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new UnderscoreAtom();
            }
        });
        map.put("_", map.get("underscore"));
        map.put("mbox", new Command() {
            boolean mode;

            public boolean init(TeXParser tp) {
                mode = tp.setTextMode();
                return true;
            }

            public void add(TeXParser tp, Atom a) {
                tp.setMathMode(mode);
                a = new TextStyleAtom(a, TextStyle.MATHNORMAL);
                tp.closeConsumer(new StyleAtom(TeXConstants.STYLE_TEXT, new RomanAtom(a)));
            }
        });
        map.put("textsuperscript", new Command() {
            boolean mode;

            public boolean init(TeXParser tp) {
                mode = tp.setTextMode();
                return true;
            }

            public void add(TeXParser tp, Atom a) {
                tp.setMathMode(mode);
                a = new TextStyleAtom(a, TextStyle.MATHNORMAL);
                tp.closeConsumer(SubSupCom.get(MHeightAtom.get(), null, new StyleAtom(TeXConstants.STYLE_TEXT, new RomanAtom(a))));
            }
        });
        map.put("textsubscript", new Command() {
            boolean mode;

            public boolean init(TeXParser tp) {
                mode = tp.setTextMode();
                return true;
            }

            public void add(TeXParser tp, Atom a) {
                tp.setMathMode(mode);
                a = new TextStyleAtom(a, TextStyle.MATHNORMAL);
                tp.closeConsumer(SubSupCom.get(EmptyAtom.get(), new StyleAtom(TeXConstants.STYLE_TEXT, new RomanAtom(a)), null));
            }
        });
        map.put("text", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return a;
            }
        });
        map.put("pmb", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                return new OoalignAtom(a, new RowAtom(new SpaceAtom(TeXLength.Unit.MU, 0.4), a));
            }
        });
        map.put("textbf", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return new BoldAtom(a);
            }
        });
        map.put("textit", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return new ItAtom(a);
            }
        });
        map.put("textrm", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return new RomanAtom(a);
            }
        });
        map.put("textsf", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return new SsAtom(a);
            }
        });
        map.put("texttt", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return new TtAtom(a);
            }
        });
        map.put("textsc", new CommandText() {
            public Atom newI(TeXParser tp, Atom a) {
                return new SmallCapAtom(a);
            }
        });

        map.put("operatorname", new Command1A() {
            public Atom newI(TeXParser tp, Atom a) {
                a = new RomanAtom(a).changeType(TeXConstants.TYPE_BIG_OPERATOR);
                a.type_limits = TeXConstants.SCRIPT_NOLIMITS;
                return a;
            }
        });
        map.put("sfrac", new CommandSfrac());
        map.put("cfrac", new Command2A() {
            char opt;

            public boolean init(TeXParser tp) {
                opt = tp.getOptionAsChar();
                if (opt != 'c' && opt != 'r' && opt != 'l' && opt != '\0') {
                    throw new ParseException(tp, "Invalid option in \\cfrac");
                }
                return true;
            }

            public Atom newI(TeXParser tp, Atom a, Atom b) {
                TeXConstants.Align align;
                if (opt == 'l') {
                    align = TeXConstants.Align.LEFT;
                } else if (opt == 'r') {
                    align = TeXConstants.Align.RIGHT;
                } else {
                    align = TeXConstants.Align.CENTER;
                }

                a = new FractionAtom(a, b, true, align, TeXConstants.Align.CENTER);
                return new RowAtom(new StyleAtom(TeXConstants.STYLE_DISPLAY, a));
            }
        });
        map.put("setlength", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final String name = tp.getArgAsCommand(true);
                TeXLength newLength = tp.getArgAsLength();
                if (newLength == null) {
                    throw new ParseException(tp, "Invalid length in \\setlength");
                }
                tp.addToConsumer(new SetLengthAtom(newLength, name));
                return false;
            }
        });
        map.put("rule", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                TeXLength r = tp.getOptionAsLength(TeXLength.getZero());
                TeXLength w = tp.getArgAsLength();
                if (w == null) {
                    throw new ParseException(tp, "Invalid length in \\rule");
                }
                TeXLength h = tp.getArgAsLength();
                if (h == null) {
                    throw new ParseException(tp, "Invalid length in \\rule");
                }
                tp.addToConsumer(new RuleAtom(w, h, r));
                return false;
            }
        });
        map.put("vrule", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final TeXLength[] lengths = tp.getDimensions();
                tp.addToConsumer(new HVruleAtom(lengths[0], lengths[1], lengths[2], false));
                return false;
            }
        });
        map.put("hrule", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final TeXLength[] lengths = tp.getDimensions();
                tp.addToConsumer(new HVruleAtom(lengths[0], lengths[1], lengths[2], true));
                return false;
            }
        });
        map.put("textvisiblespace", new Command0A() {
            public Atom newI(TeXParser tp) {
                tp.skipPureWhites();
                final Atom a = new HVruleAtom(null,
                                              new TeXLength(TeXLength.Unit.EX, 0.3),
                                              null,
                                              false);
                return new RowAtom(new SpaceAtom(TeXLength.Unit.EM, 0.06),
                                   a,
                                   new HVruleAtom(new TeXLength(TeXLength.Unit.EM, 0.3),
                                                  null,
                                                  null,
                                                  true),
                                   a);
            }
        });
        map.put("hspace", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final TeXLength w = tp.getArgAsLength();
                if (w == null) {
                    throw new ParseException(tp, "Invalid length in \\hspace");
                }
                tp.addToConsumer(new SpaceAtom(w.getUnit(), w.getL(), 0., 0.));
                return false;
            }
        });
        map.put("vspace", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final TeXLength h = tp.getArgAsLength();
                if (h == null) {
                    throw new ParseException(tp, "Invalid length in \\vspace");
                }
                tp.addToConsumer(new SpaceAtom(h.getUnit(), 0., h.getL(), 0.));
                return false;
            }
        });
        map.put("degree", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, Symbols.CIRC));
                return false;
            }
        });
        map.put("sphat", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a;
                double raise;
                if (tp.isMathMode()) {
                    a = Symbols.WIDEHAT;
                    raise = 1.1;
                } else {
                    a = Symbols.HAT;
                    raise = 0.8;
                }
                a = new StyleAtom(TeXConstants.STYLE_DISPLAY, a);
                final VRowAtom vra = new VRowAtom(a);
                vra.setRaise(TeXLength.Unit.EX, raise);
                a = new SmashedAtom(vra);
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, a));
                return false;
            }
        });
        map.put("spbreve", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Atom ex = new SpaceAtom(TeXConstants.Muskip.NEGTHIN);
                Atom a = Symbols.BREVE;
                a = new StyleAtom(TeXConstants.STYLE_DISPLAY, a);
                final VRowAtom vra = new VRowAtom(a);
                vra.setRaise(TeXLength.Unit.EX, 1.1);
                a = new SmashedAtom(vra);
                final RowAtom ra = new RowAtom(ex, a);
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, ra));
                return false;
            }
        });
        map.put("spcheck", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Atom a = Symbols.VEE;
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, a));
                return false;
            }
        });
        map.put("sptilde", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final Atom a = Symbols.SIM;
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, a));
                return false;
            }
        });
        map.put("spdot", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = Symbols.NORMALDOT;
                a = new StyleAtom(TeXConstants.STYLE_DISPLAY, a);
                final VRowAtom vra = new VRowAtom(a);
                vra.setRaise(TeXLength.Unit.EX, 0.8);
                a = new SmashedAtom(vra);
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, a));
                return false;
            }
        });
        map.put("spddot", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = Symbols.NORMALDOT;
                final RowAtom ra = new RowAtom(a, a);
                a = new StyleAtom(TeXConstants.STYLE_DISPLAY, ra);
                final VRowAtom vra = new VRowAtom(a);
                vra.setRaise(TeXLength.Unit.EX, 0.8);
                a = new SmashedAtom(vra);
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, a));
                return false;
            }
        });
        map.put("spdddot", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = Symbols.NORMALDOT;
                final RowAtom ra = new RowAtom(a, a, a);
                a = new StyleAtom(TeXConstants.STYLE_DISPLAY, ra);
                final VRowAtom vra = new VRowAtom(a);
                vra.setRaise(TeXLength.Unit.EX, 0.8);
                a = new SmashedAtom(vra);
                tp.addToConsumer(SubSupCom.get(SubSupCom.getBase(tp), null, a));
                return false;
            }
        });
        map.put("log", new CommandOpName("log", false));
        map.put("lg", new CommandOpName("lg", false));
        map.put("ln", new CommandOpName("ln", false));
        map.put("lim", new CommandOpName("lim", true));
        map.put("sin", new CommandOpName("sin", false));
        map.put("arcsin", new CommandOpName("arcsin", false));
        map.put("sinh", new CommandOpName("sinh", false));
        map.put("cos", new CommandOpName("cos", false));
        map.put("arccos", new CommandOpName("arccos", false));
        map.put("cosh", new CommandOpName("cosh", false));
        map.put("cot", new CommandOpName("cot", false));
        map.put("arccot", new CommandOpName("arccot", false));
        map.put("coth", new CommandOpName("coth", false));
        map.put("tan", new CommandOpName("tan", false));
        map.put("arctan", new CommandOpName("arctan", false));
        map.put("tanh", new CommandOpName("tanh", false));
        map.put("sec", new CommandOpName("sec", false));
        map.put("arcsec", new CommandOpName("arcsec", false));
        map.put("sech", new CommandOpName("sech", false));
        map.put("csc", new CommandOpName("csc", false));
        map.put("arccsc", new CommandOpName("arccsc", false));
        map.put("csch", new CommandOpName("csch", false));
        map.put("arg", new CommandOpName("arg", false));
        map.put("ker", new CommandOpName("ker", false));
        map.put("dim", new CommandOpName("dim", false));
        map.put("hom", new CommandOpName("hom", false));
        map.put("exp", new CommandOpName("exp", false));
        map.put("deg", new CommandOpName("deg", false));
        map.put("max", new CommandOpName("max", true));
        map.put("min", new CommandOpName("min", true));
        map.put("sup", new CommandOpName("sup", true));
        map.put("inf", new CommandOpName("inf", true));
        map.put("det", new CommandOpName("det", true));
        map.put("Pr", new CommandOpName("Pr", true));
        map.put("gcd", new CommandOpName("gcd", true));
        map.put("limsup", new CommandOpName("lim", "sup", true));
        map.put("liminf", new CommandOpName("lim", "inf", true));
        map.put("injlim", new CommandOpName("inj", "lim", true));
        map.put("projlim", new CommandOpName("proj", "lim", true));
        map.put("varinjlim", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = new RomanAtom(TeXParser.getAtomForLatinStr("lim", true));
                a = new UnderOverArrowAtom(a, false, false);
                a = a.changeType(TeXConstants.TYPE_BIG_OPERATOR);
                a.type_limits = TeXConstants.SCRIPT_LIMITS;
                tp.addToConsumer(a);
                return false;
            }
        });
        map.put("varprojlim", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = new RomanAtom(TeXParser.getAtomForLatinStr("lim", true));
                a = new UnderOverArrowAtom(a, true, false);
                a = a.changeType(TeXConstants.TYPE_BIG_OPERATOR);
                a.type_limits = TeXConstants.SCRIPT_LIMITS;
                tp.addToConsumer(a);
                return false;
            }
        });
        map.put("varliminf", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = new RomanAtom(TeXParser.getAtomForLatinStr("lim", true));
                a = new UnderlinedAtom(a);
                a = a.changeType(TeXConstants.TYPE_BIG_OPERATOR);
                a.type_limits = TeXConstants.SCRIPT_LIMITS;
                tp.addToConsumer(a);
                return false;
            }
        });
        map.put("varlimsup", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                Atom a = new RomanAtom(TeXParser.getAtomForLatinStr("lim", true));
                a = new OverlinedAtom(a);
                a = a.changeType(TeXConstants.TYPE_BIG_OPERATOR);
                a.type_limits = TeXConstants.SCRIPT_LIMITS;
                tp.addToConsumer(a);
                return false;
            }
        });
        map.put("with", new Command0A() {
            public Atom newI(TeXParser tp) {
                return Symbols.WITH.changeType(TeXConstants.TYPE_BINARY_OPERATOR);
            }
        });
        map.put("parr", new Command0A() {
            public Atom newI(TeXParser tp) {
                Atom a = new RotateAtom(Symbols.WITH, 180., new HashMap<String, String>() {
                    {
                        put("origin", "c");
                    }
                });
                return a.changeType(TeXConstants.TYPE_BINARY_OPERATOR);
            }
        });
        map.put("copyright", new Command0A() {
            public Atom newI(TeXParser tp) {
                Atom a = new RomanAtom(new CharAtom('c', false));
                a = new RaiseAtom(a, new TeXLength(TeXLength.Unit.EX, 0.2), TeXLength.getZero(), TeXLength.getZero());
                return new TextCircledAtom(a);
            }
        });
        map.put("L", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom a = new RowAtom(SymbolAtom.get("polishlcross"), new CharAtom('L', false));
                return new RomanAtom(a);
            }
        });
        map.put("l", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom a = new RowAtom(SymbolAtom.get("polishlcross"), new CharAtom('l', false));
                return new RomanAtom(a);
            }
        });
        map.put("Join", new Command0A() {
            public Atom newI(TeXParser tp) {
                Atom a = new LapedAtom(SymbolAtom.get("ltimes"), 'r');
                a = new RowAtom(a, SymbolAtom.get("rtimes"));
                a = a.changeType(TeXConstants.TYPE_BIG_OPERATOR);
                a.type_limits = TeXConstants.SCRIPT_NORMAL;
                return a;
            }
        });
        map.put("notin", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new RowAtom(Symbols.NOT, Symbols.IN);
            }
        });
        map.put("ne", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new RowAtom(Symbols.NOT, Symbols.EQUALS);
            }
        });
        map.put("neq", map.get("ne"));
        map.put("JLaTeXMath", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new RowAtom(new CharAtom('J', TextStyle.MATHBB, true),
                                   new LaTeXAtom(),
                                   new CharAtom('M', TextStyle.MATHNORMAL, true),
                                   new CharAtom('a', TextStyle.MATHNORMAL, true),
                                   new CharAtom('t', TextStyle.MATHNORMAL, true),
                                   new CharAtom('h', TextStyle.MATHNORMAL, true));
            }
        });
        map.put("ldots", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom ldotp = Symbols.LDOTP;
                return new RowAtom(ldotp, ldotp, ldotp).changeType(TeXConstants.TYPE_INNER);
            }
        });
        map.put("dotsc", map.get("ldots"));
        map.put("dots", map.get("ldots"));
        map.put("dotso", map.get("ldots"));
        map.put("cdots", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom cdotp = Symbols.CDOTP;
                return new RowAtom(cdotp, cdotp, cdotp).changeType(TeXConstants.TYPE_INNER);
            }
        });
        map.put("dotsb", map.get("cdots"));
        map.put("dotsm", map.get("cdots"));
        map.put("dotsi", new Command0A() {
            public Atom newI(TeXParser tp) {
                final Atom cdotp = Symbols.CDOTP;
                final RowAtom ra = new RowAtom(cdotp, cdotp, cdotp);
                return new RowAtom(new SpaceAtom(TeXConstants.Muskip.NEGTHIN),
                                   ra.changeType(TeXConstants.TYPE_INNER));
            }
        });
        map.put("relbar", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SmashedAtom(Symbols.MINUS).changeType(TeXConstants.TYPE_RELATION);
            }
        });
        map.put("mkern", new Command0AImpl() {
            public boolean init(TeXParser tp) {
                final TeXLength len = tp.getArgAsLength();
                tp.addToConsumer(new SpaceAtom(len));
                return false;
            }
        });
        map.put("kern", map.get("mkern"));
        map.put("mspace", map.get("mkern"));
        map.put("hskip", map.get("mkern"));
        map.put("mskip", map.get("mkern"));
        map.put("strut", new Command0A() {
            public Atom newI(TeXParser tp) {
                return new SpaceAtom(TeXLength.Unit.PT, 0., 8.6, 3.);
            }
        });
        map.put("iff", new Replacement("\\mathrel{\\;\\Longleftrightarrow\\;}"));
        map.put("bowtie", new Replacement("\\mathrel{\\mathrel{\\triangleright}\\joinrel\\mathrel{\\triangleleft}}"));
        map.put("models", new Replacement("\\mathrel{\\mathrel{\\vert}\\joinrel\\equals}"));
        map.put("implies", new Replacement("\\mathrel{\\;\\Longrightarrow\\;}"));
        map.put("impliedby", new Replacement("\\mathrel{\\;\\Longleftarrow\\;}"));
        map.put("mapsto", new Replacement("\\mathrel{\\mapstochar\\rightarrow}"));
        map.put("longmapsto", new Replacement("\\mathrel{\\mapstochar\\longrightarrow}"));
        map.put("Mapsto", new Replacement("\\mathrel{\\Mapstochar\\Rightarrow}"));
        map.put("mapsfrom", new Replacement("\\mathrel{\\leftarrow\\mapsfromchar}"));
        map.put("Mapsfrom", new Replacement("\\mathrel{\\Leftarrow\\Mapsfromchar}"));
        map.put("Longmapsto", new Replacement("\\mathrel{\\Mapstochar\\Longrightarrow}"));
        map.put("longmapsfrom", new Replacement("\\mathrel{\\longleftarrow\\mapsfromchar}"));
        map.put("Longmapsfrom", new Replacement("\\mathrel{\\Longleftarrow\\Mapsfromchar}"));
        map.put("arrowvert", new Replacement("\\vert"));
        map.put("Arrowvert", new Replacement("\\Vert"));
        map.put("aa", new Replacement("\\mathring{a}"));
        map.put("AA", new Replacement("\\mathring{A}"));
        map.put("ddag", new Replacement("\\ddagger"));
        map.put("dag", new Replacement("\\dagger"));
        map.put("Doteq", new Replacement("\\doteqdot"));
        map.put("doublecup", new Replacement("\\Cup"));
        map.put("doublecap", new Replacement("\\Cap"));
        map.put("llless", new Replacement("\\lll"));
        map.put("gggtr", new Replacement("\\ggg"));
        map.put("Alpha", new Replacement("\\mathord{\\mathrm{A}}"));
        map.put("Beta", new Replacement("\\mathord{\\mathrm{B}}"));
        map.put("Epsilon", new Replacement("\\mathord{\\mathrm{E}}"));
        map.put("Zeta", new Replacement("\\mathord{\\mathrm{Z}}"));
        map.put("Eta", new Replacement("\\mathord{\\mathrm{H}}"));
        map.put("Iota", new Replacement("\\mathord{\\mathrm{I}}"));
        map.put("Kappa", new Replacement("\\mathord{\\mathrm{K}}"));
        map.put("Mu", new Replacement("\\mathord{\\mathrm{M}}"));
        map.put("Nu", new Replacement("\\mathord{\\mathrm{N}}"));
        map.put("Omicron", new Replacement("\\mathord{\\mathrm{O}}"));
        map.put("Rho", new Replacement("\\mathord{\\mathrm{P}}"));
        map.put("Tau", new Replacement("\\mathord{\\mathrm{T}}"));
        map.put("Chi", new Replacement("\\mathord{\\mathrm{X}}"));
        map.put("hdots", new Replacement("\\ldots"));
        map.put("restriction", new Replacement("\\upharpoonright"));
        map.put("celsius", new Replacement("\\mathord{{}^\\circ\\mathrm{C}}"));
        map.put("micro", new Replacement("\\textmu"));
        map.put("marker", new Replacement("{\\kern{0.25ex}\\rule{0.5ex}{1.2ex}\\kern{0.25ex}}"));
        map.put("hybull", new Replacement("\\rule[0.6ex]{1ex}{0.2ex}"));
        map.put("block", new Replacement("\\rule{1ex}{1.2ex}"));
        map.put("uhblk", new Replacement("\\rule[0.6ex]{1ex}{0.6ex}"));
        map.put("lhblk", new Replacement("\\rule{1ex}{0.6ex}"));
        map.put("lVert", new Replacement("\\Vert"));
        map.put("rVert", new Replacement("\\Vert"));
        map.put("lvert", new Replacement("\\vert"));
        map.put("rvert", new Replacement("\\vert"));
        map.put("copyright", new Replacement("\\textcircled{\\raisebox{0.2ex}{c}}"));
        map.put("glj", new Replacement("\\mathbin{\\rlap{>}\\!<}"));
        map.put("gla", new Replacement("\\mathbin{><}"));
        map.put("alef", new Replacement("\\aleph"));
        map.put("alefsym", new Replacement("\\aleph"));
        map.put("And", new Replacement("{\\;\\textampersand\\;}"));
        map.put("and", new Replacement("\\land"));
        map.put("ang", new Replacement("\\angle"));
        map.put("Reals", new Replacement("\\mathbb{R}"));
        map.put("exist", new Replacement("\\exists"));
        map.put("hAar", new Replacement("\\Leftrightarrow"));
        map.put("C", new Replacement("\\mathbb{C}"));
        map.put("Complex", map.get("C"));
        map.put("N", new Replacement("\\mathbb{N}"));
        map.put("natnums", map.get("N"));
        map.put("Q", new Replacement("\\mathbb{Q}"));
        map.put("R", new Replacement("\\mathbb{R}"));
        map.put("real", map.get("R"));
        map.put("reals", map.get("R"));
        map.put("Z", new Replacement("\\mathbb{Z}"));
        map.put("Dagger", new Replacement("\\ddagger"));
        map.put("diamonds", new Replacement("\\diamondsuit"));
        map.put("clubs", new Replacement("\\clubsuit"));
        map.put("hearts", new Replacement("\\heartsuit"));
        map.put("spades", new Replacement("\\spadesuit"));
        map.put("infin", new Replacement("\\infty"));
        map.put("isin", new Replacement("\\in"));
        map.put("plusmn", new Replacement("\\pm"));
        map.put("sube", new Replacement("\\subseteq"));
        map.put("supe", new Replacement("\\supseteq"));
        map.put("sdot", new Replacement("\\cdot"));
        map.put("empty", new Replacement("\\emptyset"));
        map.put("O", map.get("empty"));
        map.put("sub", new Replacement("\\subset"));
        map.put("lang", new Replacement("\\langle"));
        map.put("rang", new Replacement("\\rangle"));
        map.put("bull", new Replacement("\\bullet"));
        map.put("geneuro", new Replacement("\\texteuro"));
        map.put("geneuronarrow", map.get("geneuro"));
        map.put("geneurowide", map.get("geneuro"));
        map.put("jlmXML", new Command() {
            // TODO: check if this is useful or not
            public boolean init(TeXParser tp) {
                final Map<String, String> map = tp.getXMLMap();
                String str = tp.getArgAsString();
                final StringBuffer buffer = new StringBuffer();
                int start = 0;
                int pos;
                while ((pos = str.indexOf("$")) != -1) {
                    if (pos < str.length() - 1) {
                        start = pos;
                        while (++start < str.length() && Character.isLetter(str.charAt(start)));
                        String key = str.substring(pos + 1, start);
                        String value = map.get(key);
                        if (value != null) {
                            buffer.append(str.substring(0, pos));
                            buffer.append(value);
                        } else {
                            buffer.append(str.substring(0, start));
                        }
                        str = str.substring(start);
                    } else {
                        buffer.append(str);
                        str = "";
                    }
                }
                buffer.append(str);
                str = buffer.toString();

                tp.addString(str);

                return false;
            }
        });
    }

    public static AtomConsumer get(final String name) {
        final Command c = map.get(name);
        if (c != null) {
            return (AtomConsumer)c.clone();
        }

        return null;
    }

    public static boolean exec(final TeXParser tp, final String name) {
        final Command c = map.get(name);
        if (c != null) {
            tp.cancelPrevPos();
            final AtomConsumer cons = (AtomConsumer)c.clone();
            if (cons.init(tp)) {
                tp.addConsumer(cons);
            }
            return true;
        }

        return false;
    }

    static Command getUnsafe(final String name) {
        return map.get(name);
    }

    public static AtomConsumer getDollar() {
        return (AtomConsumer)dollar;
    }

    public static AtomConsumer getDollarDollar() {
        return (AtomConsumer)dollardollar;
    }

    public static void getAll(final List<String> l) {
        for (final String k : map.keySet()) {
            l.add(k);
        }
    }
}
