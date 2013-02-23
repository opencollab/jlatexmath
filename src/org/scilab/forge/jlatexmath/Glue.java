/* Glue.java
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
 * Represents glue by its 3 components. Contains the "glue rules".
 */
public class Glue {

    // the glue components
    private final float space;
    private final float stretch;
    private final float shrink;

    private final String name;

    // contains the different glue types
    private static Glue[] glueTypes;

    // the glue table representing the "glue rules" (as in TeX)
    private static final int[][][] glueTable;

    static {
        GlueSettingsParser parser = new GlueSettingsParser();
        glueTypes = parser.getGlueTypes();
        glueTable = parser.createGlueTable();
    }

    public Glue(float space, float stretch, float shrink, String name) {
        this.space = space;
        this.stretch = stretch;
        this.shrink = shrink;
        this.name = name;
    }

    /**
     * Name of this glue object.
     */
    public String getName () {
        return this.name;
    }

    /**
     * Creates a box representing the glue type according to the "glue rules" based
     * on the atom types between which the glue must be inserted.
     *
     * @param lType left atom type
     * @param rType right atom type
     * @param env the TeXEnvironment
     * @return a box containing representing the glue
     */
    public static Box get(int lType, int rType, TeXEnvironment env) {
        // types > INNER are considered of type ORD for glue calculations
        int l = (lType > TeXConstants.TYPE_INNER ? TeXConstants.TYPE_ORDINARY : lType);
        int r = (rType > TeXConstants.TYPE_INNER ? TeXConstants.TYPE_ORDINARY : rType);

        // search right glue-type in "glue-table"
        int glueType = glueTable[l][r][env.getStyle() / 2];

        return glueTypes[glueType].createBox(env);
    }

    private Box createBox(TeXEnvironment env) {
        TeXFont tf = env.getTeXFont();
        // use "quad" from a font marked as an "mu font"
        float quad = tf.getQuad(env.getStyle(), tf.getMuFontId());

        return new GlueBox((space / 18.0f) * quad, (stretch / 18.0f) * quad, (shrink / 18.0f) * quad);
    }
}
