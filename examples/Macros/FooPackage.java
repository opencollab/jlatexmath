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
 */

package Foo;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.TeXParser;
import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXEnvironment;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.Box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class FooPackage {
    
    /*
     * The macro fooA is equivalent to \newcommand{\fooA}[2]{\frac{\textcolor{red}{#2}}{#1}}
     */
    public Atom fooA_macro(TeXParser tp, String[] args) throws ParseException {
	return new TeXFormula("\\frac{\\textcolor{red}{" + args[2] + "}}{" + args[1] + "}").root;
    }
    
    public Atom fooB_macro(TeXParser tp, String[] args) throws ParseException {
	float f = Float.parseFloat(args[1]);
        return new MyAtom(f);
    }
    
    public Atom fooC_macro(TeXParser tp, String[] args) throws ParseException {
	float f = Float.parseFloat(args[1]);
        return new MyAtom(f, args[2].length() != 0);
    }
    
    public Atom fooD_macro(TeXParser tp, String[] args) throws ParseException {
	float f = Float.parseFloat(args[1]);
        return new MyAtom(f, args[2].length() == 0);
    }

    public class MyAtom extends Atom {
        
        public float f;
        public boolean filled = false;

        public MyAtom(float f) {
            this.f = f;
        }

        public MyAtom(float f, boolean filled) {
            this.f = f;
            this.filled = filled;
        }

        public Box createBox(TeXEnvironment env) {
            return new MyBox((int) f, new SpaceAtom(TeXConstants.UNIT_POINT, f, 0, 0).createBox(env).getWidth(), filled);
        }
    }

    public class MyBox extends Box {
        
        public boolean filled;
        public int r;
        
        public MyBox(int r, float f, boolean filled) {
            this.r = r;
            this.filled = filled;
            this.width = f;
            this.height = f / 2;
            this.depth = f / 2;
        }
        
        public void draw(Graphics2D g2, float x, float y) {
            Color old = g2.getColor();
            g2.setColor(Color.RED);
            AffineTransform oldAt = g2.getTransform();
            g2.translate(x, y - height);
            g2.scale(Math.abs(1 / oldAt.getScaleX()), Math.abs(1 / oldAt.getScaleY()));
            if (filled) {
                g2.fillOval(0, 0, r, r);
            } else {
                g2.drawOval(0, 0, r, r);
            }
            g2.setColor(old);
            g2.setTransform(oldAt);
        }
        
        public int getLastFontId() {
            return 0;
        }
    }
}