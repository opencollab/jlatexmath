/* CharAtom.java
 * =========================================================================
 * This file is originally part of the JMathTeX Library - http://jmathtex.sourceforge.net
 * 
 * Copyright (C) 2004-2007 Universiteit Gent
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

/**
 * An atom representing exactly one alphanumeric character and the text style in which 
 * it should be drawn. 
 */
public class CharAtom extends CharSymbol {

   // alphanumeric character
   private final char c;

   // text style (null means the default text style)
   private final String textStyle;

   /**
    * Creates a CharAtom that will represent the given character in the given text style.
    * Null for the text style means the default text style.
    * 
    * @param c the alphanumeric character
    * @param textStyle the text style in which the character should be drawn
    */
   public CharAtom(char c, String textStyle) {
      this.c = c;
      this.textStyle = textStyle;
   }

   public Box createBox(TeXEnvironment env) {
      Char ch = getChar(env.getTeXFont(), env.getStyle());
      return new CharBox(ch);
   }

   /*
    * Get the Char-object representing this character ("c") in the right text style
    */
   private Char getChar(TeXFont tf, int style) {
      if (textStyle == null) // default text style
         return tf.getDefaultChar(c, style);
      else
         return tf.getChar(c, textStyle, style);
   }

   public CharFont getCharFont(TeXFont tf) {
      // style doesn't matter here 
      return getChar(tf, TeXConstants.STYLE_DISPLAY).getCharFont();
   }

}
