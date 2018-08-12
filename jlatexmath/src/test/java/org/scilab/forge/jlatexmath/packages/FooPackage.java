/* FooPackage.java
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

package org.scilab.forge.jlatexmath.packages;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.ColorAtom;
import org.scilab.forge.jlatexmath.Colors;
import org.scilab.forge.jlatexmath.Command;
import org.scilab.forge.jlatexmath.FractionAtom;
import org.scilab.forge.jlatexmath.JLMPackage;
import org.scilab.forge.jlatexmath.TeXLength;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.TeXEnvironment;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.Box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class FooPackage extends JLMPackage {

    private static FooPackage instance = null;

    private FooPackage() { }

    public static FooPackage getInstance() {
        if (instance == null) {
            instance = new FooPackage();
        }
        return instance;
    }

    @Override
    public void init() { }

    @Override
    public String getName() {
        return "foo";
    }

    @Override
    public Map<String, Command> getCommands() {
        return new HashMap<String, Command>() {
            {
                put("fooA", new Command() {
                    public boolean init(TeXParser tp) {
                        final String arg1 = tp.getGroupAsArgument();
                        final String arg2 = tp.getGroupAsArgument();
                        final String code = "\\frac{\\textcolor{red}{" + arg2 + "}}{" + arg1 + "}";
                        tp.addString(code);
                        return false;
                    }
                });

                put("fooB", new Command() {
                    public boolean init(TeXParser tp) {
                        final double f = tp.getArgAsDecimal();
                        tp.addToConsumer(new MyAtom(f));
                        return false;
                    }
                });

                put("fooC", new Command() {
                    public boolean init(TeXParser tp) {
                        final String opt = tp.getOptionAsString();
                        final double f = tp.getArgAsDecimal();
                        tp.addToConsumer(new MyAtom(f, opt.length() != 0));
                        return false;
                    }
                });

                put("fooD", new Command() {
                    public boolean init(TeXParser tp) {
                        final double f = tp.getArgAsDecimal();
                        final String opt = tp.getOptionAsString();
                        tp.addToConsumer(new MyAtom(f, opt.length() == 0));
                        return false;
                    }
                });

                put("fooE", new Command() {
                    // this command is equivalent to fooA
                    private Atom arg1;

                    public boolean init(TeXParser tp) {
                        // this command is in fact a consumer: it will be fed with atoms
                        return true;
                    }

                    public void add(TeXParser tp, Atom a) {
                        // here we feed
                        if (arg1 == null) {
                            arg1 = a;
                        } else {
                            // we've our second arg (a) so we close this consumer, remove it from the execution stack and feed the previous consumer with the atom in argument
                            tp.closeConsumer(new FractionAtom(new ColorAtom(a, null, Colors.getFromName("red")), arg1));
                        }
                    }
                });

            }
        };
    }

    public class MyAtom extends Atom {

        public double r;
        public boolean filled = false;

        public MyAtom(double r) {
            this.r = r;
        }

        public MyAtom(double r, boolean filled) {
            this.r = r;
            this.filled = filled;
        }

        public Box createBox(TeXEnvironment env) {
            return new MyBox((int) r, new SpaceAtom(TeXLength.Unit.POINT, r, 0., 0.).createBox(env).getWidth(), filled);
        }
    }

    public class MyBox extends Box {

        public boolean filled;
        public int r;

        public MyBox(int r, double x, boolean filled) {
            this.r = r;
            this.filled = filled;
            this.width = x;
            this.height = x / 2.;
            this.depth = x / 2.;
        }

        public void draw(Graphics2D g2, double x, double y) {
            Color old = g2.getColor();
            g2.setColor(Color.RED);
            AffineTransform oldAt = g2.getTransform();
            g2.translate(x, y - height);
            g2.scale(Math.abs(1 / oldAt.getScaleX()), Math.abs(1 / oldAt.getScaleY()));
            if (filled) {
                g2.fill(new Ellipse2D.Double(0., 0., r, r));
            } else {
                g2.draw(new Ellipse2D.Double(0., 0., r, r));
            }
            g2.setColor(old);
            g2.setTransform(oldAt);
        }

        public int getLastFontId() {
            return 0;
        }
    }
}
