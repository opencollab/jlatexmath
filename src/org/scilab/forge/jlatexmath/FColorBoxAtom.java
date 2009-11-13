/* FColorBoxAtom.java
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

/**
 * An atom representing a boxed base atom. 
 */
public class FColorBoxAtom extends Atom {

    // base atom
    private final Atom base;
    private Color bg, line;
    
    public FColorBoxAtom(Atom base, Color bg, Color line) {
      if (base == null)
         this.base = new RowAtom(); // empty base
      else {
	  this.base = base;
	  this.type = base.type;
      }
      this.bg = bg;
      this.line = line;
   }

   public Box createBox(TeXEnvironment env) {
       env.isColored = true;
       Box bbase = base.createBox(env);
       float height = bbase.getHeight();
       float width = bbase.getWidth();
       float depth = bbase.getDepth();
       float drt = env.getTeXFont().getDefaultRuleThickness(env.getStyle());
       Box space = new SpaceAtom(TeXConstants.UNIT_MU, 0, 3, 0).createBox(env);
       float mu3 = space.getHeight();
       HorizontalBox hb = new HorizontalBox();
       HorizontalBox hbb = new HorizontalBox();
       HorizontalRule vert = new HorizontalRule(height + depth + 2 * (mu3 + drt), drt, height + depth + 2 * mu3 + drt, line);
       HorizontalRule hor = new HorizontalRule(drt, width + 2 * mu3, 0, line);
       VerticalBox vb = new VerticalBox();
       StrutBox s = new StrutBox(0, mu3, 0, 0);
       StrutBox ss = new StrutBox(mu3, 0, 0, -drt);
       hbb.add(ss);
       hbb.add(bbase);
       hbb.add(ss);
       vb.add(hor);
       vb.add(s);
       vb.add(hbb);
       vb.add(s);
       vb.add(hor);
       hb.add(vert);
       hb.add(vb);
       hb.add(vert);
       float totalheight = hb.getHeight() + hb.getDepth();
       VerticalBox Vbox = new VerticalBox();
       Vbox.add(hb);
       Vbox.setHeight(height + mu3 + drt);
       Vbox.setDepth(totalheight - Vbox.getHeight());
       HorizontalBox Hbox = new HorizontalBox(env.getColor(), bg);
       Hbox.add(Vbox);
       return Hbox; 
   }

}
