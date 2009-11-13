/* ColorAtom.java
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

import java.awt.Color;
import java.util.HashMap;

/**
 * An atom representing the foreground and background color of an other atom. 
 */
public class ColorAtom extends Atom implements Row {

    public static HashMap<String,Color> Colors = new HashMap<String,Color>();

    // background color
    private final Color background;
    
    // foreground color
    private final Color color;
    
    // RowAtom for which the colorsettings apply
    private final RowAtom elements;
    
    static {
	initColors();
    }

    /**
     * Creates a new ColorAtom that sets the given colors for the given atom.
     * Null for a color means: no specific color set for this atom.
     * 
     * @param atom the atom for which the given colors have to be set 
     * @param bg the background color
     * @param c the foreground color
     */
    public ColorAtom(Atom atom, Color bg, Color c) {
	elements = new RowAtom(atom);
	background = bg;
	color = c;
    }
    
    /**
     * Creates a ColorAtom that overrides the colors of the given ColorAtom if the given
     * colors are not null. If they're null, the old values are used.
     * 
     * @param bg the background color
     * @param c the foreground color
     * @param old the ColorAtom for which the colorsettings should be overriden with the
     * 			given colors.
     */
    public ColorAtom(Color bg, Color c, ColorAtom old) {
	elements = new RowAtom(old.elements);
	background = (bg == null ? old.background : bg);
	color = (c == null ? old.color : c);
    }
    
    public Box createBox(TeXEnvironment env) {
	env.isColored = true;
	TeXEnvironment copy = env.copy();
	if (background != null)
	    copy.setBackground(background);
	if (color != null)
	    copy.setColor(color);
	return elements.createBox(copy);
    }
    
    public int getLeftType() {
	return elements.getLeftType();
    }
    
    public int getRightType() {
	return elements.getRightType();
    }
    
    public void setPreviousAtom(Dummy prev) {
	elements.setPreviousAtom(prev);
    }

    private static void initColors() {
	Colors.put("black", Color.black);
	Colors.put("white", Color.white);
	Colors.put("red", Color.red);
	Colors.put("green", Color.green);
	Colors.put("blue", Color.blue);
	Colors.put("cyan", Color.cyan);
	Colors.put("magenta", Color.magenta);
	Colors.put("yellow", Color.yellow);
	Colors.put("GreenYellow", convColor(0.15f,0f,0.69f,0f));
	Colors.put("Yellow", convColor(0f,0f,1f,0f));
	Colors.put("Goldenrod", convColor(0f,0.10f,0.84f,0f));
	Colors.put("Dandelion", convColor(0f,0.29f,0.84f,0f));
	Colors.put("Apricot", convColor(0f,0.32f,0.52f,0f));
	Colors.put("Peach", convColor(0f,0.50f,0.70f,0f));
	Colors.put("Melon", convColor(0f,0.46f,0.50f,0f));
	Colors.put("YellowOrange", convColor(0f,0.42f,1f,0f));
	Colors.put("Orange", convColor(0f,0.61f,0.87f,0f));
	Colors.put("BurntOrange", convColor(0f,0.51f,1f,0f));
	Colors.put("Bittersweet", convColor(0f,0.75f,1f,0.24f));
	Colors.put("RedOrange", convColor(0f,0.77f,0.87f,0f));
	Colors.put("Mahogany", convColor(0f,0.85f,0.87f,0.35f));
	Colors.put("Maroon", convColor(0f,0.87f,0.68f,0.32f));
	Colors.put("BrickRed", convColor(0f,0.89f,0.94f,0.28f));
	Colors.put("Red", convColor(0f,1f,1f,0f));
	Colors.put("OrangeRed", convColor(0f,1f,0.50f,0f));
	Colors.put("RubineRed", convColor(0f,1f,0.13f,0f));
	Colors.put("WildStrawberry", convColor(0f,0.96f,0.39f,0f));
	Colors.put("Salmon", convColor(0f,0.53f,0.38f,0f));
	Colors.put("CarnationPink", convColor(0f,0.63f,0f,0f));
	Colors.put("Magenta", convColor(0f,1f,0f,0f));
	Colors.put("VioletRed", convColor(0f,0.81f,0f,0f));
	Colors.put("Rhodamine", convColor(0f,0.82f,0f,0f));
	Colors.put("Mulberry", convColor(0.34f,0.90f,0f,0.02f));
	Colors.put("RedViolet", convColor(0.07f,0.90f,0f,0.34f));
	Colors.put("Fuchsia", convColor(0.47f,0.91f,0f,0.08f));
	Colors.put("Lavender", convColor(0f,0.48f,0f,0f));
	Colors.put("Thistle", convColor(0.12f,0.59f,0f,0f));
	Colors.put("Orchid", convColor(0.32f,0.64f,0f,0f));
	Colors.put("DarkOrchid", convColor(0.40f,0.80f,0.20f,0f));
	Colors.put("Purple", convColor(0.45f,0.86f,0f,0f));
	Colors.put("Plum", convColor(0.50f,1f,0f,0f));
	Colors.put("Violet", convColor(0.79f,0.88f,0f,0f));
	Colors.put("RoyalPurple", convColor(0.75f,0.90f,0f,0f));
	Colors.put("BlueViolet", convColor(0.86f,0.91f,0f,0.04f));
	Colors.put("Periwinkle", convColor(0.57f,0.55f,0f,0f));
	Colors.put("CadetBlue", convColor(0.62f,0.57f,0.23f,0f));
	Colors.put("CornflowerBlue", convColor(0.65f,0.13f,0f,0f));
	Colors.put("MidnightBlue", convColor(0.98f,0.13f,0f,0.43f));
	Colors.put("NavyBlue", convColor(0.94f,0.54f,0f,0f));
	Colors.put("RoyalBlue", convColor(1f,0.50f,0f,0f));
	Colors.put("Blue", convColor(1f,1f,0f,0f));
	Colors.put("Cerulean", convColor(0.94f,0.11f,0f,0f));
	Colors.put("Cyan", convColor(1f,0f,0f,0f));
	Colors.put("ProcessBlue", convColor(0.96f,0f,0f,0f));
	Colors.put("SkyBlue", convColor(0.62f,0f,0.12f,0f));
	Colors.put("Turquoise", convColor(0.85f,0f,0.20f,0f));
	Colors.put("TealBlue", convColor(0.86f,0f,0.34f,0.02f));
	Colors.put("Aquamarine", convColor(0.82f,0f,0.30f,0f));
	Colors.put("BlueGreen", convColor(0.85f,0f,0.33f,0f));
	Colors.put("Emerald", convColor(1f,0f,0.50f,0f));
	Colors.put("JungleGreen", convColor(0.99f,0f,0.52f,0f));
	Colors.put("SeaGreen", convColor(0.69f,0f,0.50f,0f));
	Colors.put("Green", convColor(1f,0f,1f,0f));
	Colors.put("ForestGreen", convColor(0.91f,0f,0.88f,0.12f));
	Colors.put("PineGreen", convColor(0.92f,0f,0.59f,0.25f));
	Colors.put("LimeGreen", convColor(0.50f,0f,1f,0f));
	Colors.put("YellowGreen", convColor(0.44f,0f,0.74f,0f));
	Colors.put("SpringGreen", convColor(0.26f,0f,0.76f,0f));
	Colors.put("OliveGreen", convColor(0.64f,0f,0.95f,0.40f));
	Colors.put("RawSienna", convColor(0f,0.72f,1f,0.45f));
	Colors.put("Sepia", convColor(0f,0.83f,1f,0.70f));
	Colors.put("Brown", convColor(0f,0.81f,1f,0.60f));
	Colors.put("Tan", convColor(0.14f,0.42f,0.56f,0f));
	Colors.put("Gray", convColor(0f,0f,0f,0.50f));
	Colors.put("Black", convColor(0f,0f,0f,1f));
	Colors.put("White", convColor(0f,0f,0f,0f));
    }

    private static Color convColor(float c, float m, float y, float k) {
	k = 1 - k;
	return new Color(k*(1-c), k*(1-m), k*(1-y));
    }

}
