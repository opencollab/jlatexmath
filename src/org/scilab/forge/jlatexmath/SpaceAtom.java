/* SpaceAtom.java
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

import java.util.HashMap;

/**
 * An atom representing whitespace. The dimension values can be set using different
 * unit types.
 */
public class SpaceAtom extends Atom {
    
    private static HashMap units = new HashMap<String, Integer>();
    static {
	units.put("em", TeXConstants.UNIT_EM);
	units.put("ex", TeXConstants.UNIT_EX);
	units.put("px", TeXConstants.UNIT_PIXEL);
	units.put("pix", TeXConstants.UNIT_PIXEL);
	units.put("pixel", TeXConstants.UNIT_PIXEL);
	units.put("pt", TeXConstants.UNIT_POINT);
	units.put("point", TeXConstants.UNIT_POINT);
	units.put("pica", TeXConstants.UNIT_PICA);
	units.put("pc", TeXConstants.UNIT_PICA);
	units.put("mu", TeXConstants.UNIT_MU);
	units.put("cm", TeXConstants.UNIT_CM);
	units.put("mm", TeXConstants.UNIT_MM);
	units.put("in", TeXConstants.UNIT_IN);
    }

    private static interface UnitConversion { // NOPMD
        public float getPixelConversion(TeXEnvironment env);
    }
    
    // TODO: UNIT_EM and UNIT_EX yield the same converters ??
    
    private static UnitConversion[] unitConversions = new UnitConversion[] {
        
        new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return env.getTeXFont().getXHeight(env.getStyle(), env.getLastFontId());
            }
        },
        
        new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return env.getTeXFont().getXHeight(env.getStyle(), env.getLastFontId());
            }
        },
        
        new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return 1 / env.getSize();
            }
        },
        
        new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return TeXFont.PIXELS_PER_POINT / env.getSize();
            }
        },
        
        new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return (12 * TeXFont.PIXELS_PER_POINT) / env.getSize();
            }
        },
        
        new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                TeXFont tf = env.getTeXFont();
                return tf.getQuad(env.getStyle(), tf.getMuFontId()) / 18;
            }
        },

	new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return (28.346456693f * TeXFont.PIXELS_PER_POINT) / env.getSize();
            }
        },

	new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return (2.8346456693f * TeXFont.PIXELS_PER_POINT) / env.getSize();
            }
        },

	new UnitConversion() {
            public float getPixelConversion(TeXEnvironment env) {
                return (72 * TeXFont.PIXELS_PER_POINT) / env.getSize();
            }
        }
    };
    
    // whether a hard space should be represented
    private boolean blankSpace = false;
    
    // thinmuskip, medmuskip, thickmuskip
    private int blankType = 0;

    // dimensions
    private float width;
    private float height;
    private float depth;
    
    // units for the dimensions
    private int wUnit;
    private int hUnit;
    private int dUnit;
    
    public SpaceAtom() {
        blankSpace = true;
    }
    
    public SpaceAtom(int type) {
	blankSpace = true;
	blankType = type;
    }
    
    public SpaceAtom(int unit, float width, float height, float depth)
    throws InvalidUnitException {
        // check if unit is valid
        checkUnit(unit);
        
        // unit valid
        this.wUnit = unit;
        this.hUnit = unit;
        this.dUnit = unit;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    /**
     * Check if the given unit is valid
     *
     * @param unit the unit's integer representation (a constant)
     * @throws InvalidUnitException if the given integer value does not represent
     * 			a valid unit
     */
    public static void checkUnit(int unit) throws InvalidUnitException {
        if (unit < 0 || unit >= unitConversions.length)
            throw new InvalidUnitException();
    }
    
    public SpaceAtom(int widthUnit, float width, int heightUnit, float height,
            int depthUnit, float depth) throws InvalidUnitException {
        // check if units are valid
        checkUnit(widthUnit);
        checkUnit(heightUnit);
        checkUnit(depthUnit);
        
        // all units valid
        wUnit = widthUnit;
        hUnit = heightUnit;
        dUnit = depthUnit;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public static int getUnit(String unit) {
	Integer u = (Integer) units.get(unit);
	return u == null ? -1 : u.intValue();
    }

    public static float[] getLength(String lgth) {
	if (lgth == null) {
	    return new float[]{0f, 0f};
	}

	int i = 0;
	for (; i < lgth.length() && !Character.isLetter(lgth.charAt(i)); i++);
	float f = 0;
	try {
	    f = Float.parseFloat(lgth.substring(0, i));
	} catch (NumberFormatException e) {
	    return new float[]{Float.NaN};
	}
 
	int unit;
	if (i != lgth.length()) {
	    unit = getUnit(lgth.substring(i).toLowerCase());
	} else {
	    unit = TeXConstants.UNIT_PIXEL;
	}
	
	return new float[]{(float) unit, f};
    }

    public Box createBox(TeXEnvironment env) {
        if (blankSpace) {
            if (blankType == 0)
		return new StrutBox(env.getSpace(), 0, 0, 0);
	    else {
		int bl = blankType < 0 ? -blankType : blankType;
		Box b;
		if (bl == TeXConstants.THINMUSKIP) 
		    b = Glue.get(TeXConstants.TYPE_INNER, TeXConstants.TYPE_ORDINARY, env);
		else if (bl == TeXConstants.MEDMUSKIP) 
		    b = Glue.get(TeXConstants.TYPE_BINARY_OPERATOR, TeXConstants.TYPE_BIG_OPERATOR, env);
		else 
		    b = Glue.get(TeXConstants.TYPE_RELATION, TeXConstants.TYPE_BIG_OPERATOR, env);
		if (blankType < 0)
		    b.negWidth();
		return b;
	    }
	} 	
        else
            return new StrutBox(width * getFactor(wUnit, env), height
                    * getFactor(hUnit, env), depth * getFactor(dUnit, env), 0);
    }
    
    public float getFactor(int unit, TeXEnvironment env) {
        return unitConversions[unit].getPixelConversion(env);
    }
}
