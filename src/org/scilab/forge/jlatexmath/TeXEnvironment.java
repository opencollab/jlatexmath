/* TeXEnvironment.java
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

/* Modified by Calixte Denizet */

package org.scilab.forge.jlatexmath;

import java.awt.Color;

/**
 * Contains the used TeXFont-object, color settings and the current style in which a
 * formula must be drawn. It's used in the createBox-methods. Contains methods that
 * apply the style changing rules for subformula's.
 */
public class TeXEnvironment {
    
    // colors
    private Color background = null, color = null;
    
    // current style
    private int style = TeXConstants.STYLE_DISPLAY;
    
    // TeXFont used
    private TeXFont tf;
    
    // last used font
    private int lastFontId = TeXFont.NO_FONT;
    
    private float textwidth = Float.POSITIVE_INFINITY;

    public boolean isColored = false;
        
    public TeXEnvironment(int style, TeXFont tf) {
        this(style, tf, null, null);
    }

    public TeXEnvironment(int style, TeXFont tf, int widthUnit, float textwidth) {
        this(style, tf, null, null);
	this.textwidth = new SpaceAtom(widthUnit, textwidth, 0.0f, 0.0f).createBox(this).getWidth();
    }

    private TeXEnvironment(int style, TeXFont tf, Color bg, Color c) {
        // check if style is valid
        // if not : DISPLAY = default value
        if (style == TeXConstants.STYLE_DISPLAY || style == TeXConstants.STYLE_TEXT
                || style == TeXConstants.STYLE_SCRIPT || style == TeXConstants.STYLE_SCRIPT_SCRIPT)
            this.style = style;
        else
            this.style = TeXConstants.STYLE_DISPLAY;
        
        this.tf = tf;
        background = bg;
        color = c;
    }
    
    public void setTextwidth(int widthUnit, float textwidth) {
	this.textwidth = new SpaceAtom(widthUnit, textwidth, 0.0f, 0.0f).createBox(this).getWidth();
    }
    
    public float getTextwidth() {
	return textwidth;
    }

    protected TeXEnvironment copy() {
        return new TeXEnvironment(style, tf, background, color);
    }

    protected TeXEnvironment copy(TeXFont tf) {
        TeXEnvironment te = new TeXEnvironment(style, tf, background, color);
	te.style = style;
	te.textwidth = textwidth;
	return te;
    }
    
    /**
     *
     * @return a copy of the environment, but in a cramped style.
     */
    public TeXEnvironment crampStyle() {
        TeXEnvironment s = copy();
        s.style = (style % 2 == 1 ? style : style + 1);
        return s;
    }
    
    /**
     *
     * @return a copy of the environment, but in denominator style.
     */
    public TeXEnvironment denomStyle() {
        TeXEnvironment s = copy();
        s.style = 2 * (style / 2) + 1 + 2 - 2 * (style / 6);
        return s;
    }
    
    /**
     *
     * @return the background color setting
     */
    public Color getBackground() {
        return background;
    }
    
    /**
     *
     * @return the foreground color setting
     */
    public Color getColor() {
        return color;
    }
    
    /**
     *
     * @return the point size of the TeXFont
     */
    public float getSize() {
        return tf.getSize();
    }
    
    /**
     *
     * @return the current style
     */
    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
    
    /**
     *
     * @return the TeXFont to be used
     */
    public TeXFont getTeXFont() {
        return tf;
    }
    
    /**
     *
     * @return a copy of the environment, but in numerator style.
     */
    public TeXEnvironment numStyle() {
        TeXEnvironment s = copy();
        s.style = style + 2 - 2 * (style / 6);
        return s;
    }
    
    /**
     * Resets the color settings.
     *
     */
    public void reset() {
        color = null;
        background = null;
    }
    
    /**
     *
     * @return a copy of the environment, but with the style changed for roots
     */
    public TeXEnvironment rootStyle() {
        TeXEnvironment s = copy();
        s.style = TeXConstants.STYLE_SCRIPT_SCRIPT;
        return s;
    }
    
    /**
     *
     * @param c the background color to be set
     */
    public void setBackground(Color c) {
        background = c;
    }
    
    /**
     *
     * @param c the foreground color to be set
     */
    public void setColor(Color c) {
        color = c;
    }
    
    /**
     *
     * @return a copy of the environment, but in subscript style.
     */
    public TeXEnvironment subStyle() {
        TeXEnvironment s = copy();
        s.style = 2 * (style / 4) + 4 + 1;
        return s;
    }
    
    /**
     *
     * @return a copy of the environment, but in superscript style.
     */
    public TeXEnvironment supStyle() {
        TeXEnvironment s = copy();
        s.style = 2 * (style / 4) + 4 + (style % 2);
        return s;
    }
    
    public float getSpace() {
        return tf.getSpace(style);
    }
    
    public void setLastFontId(int id) {
        lastFontId = id;
    }
    
    public int getLastFontId() {
        // if there was no last font id (whitespace boxes only), use default "mu font"
        return (lastFontId == TeXFont.NO_FONT ? tf.getMuFontId() : lastFontId);
    }
}
