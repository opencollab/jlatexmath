/* Atom.java
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

/**
 * An abstract superclass for all logical mathematical constructions that can be
 * a part of a TeXFormula. All subclasses must implement the abstract 
 * {@link #createBox(TeXEnvironment)} method that transforms this logical unit into
 * a concrete box (that can be painted). They also must define their type, used for
 * determining what glue to use between adjacent atoms in a "row construction". That can
 * be one single type by asigning one of the type constants to the {@link #type} field.
 * But they can also be defined as having two types: a "left type" and a "right type".
 * This can be done by implementing the methods {@link #getLeftType()} and
 * {@link #getRightType()}.
 * The left type will then be used for determining the glue between this atom and the
 * previous one (in a row, if any) and the right type for the glue between this atom and 
 * the following one (in a row, if any).
 * 
 * @author Kurt Vermeulen
 */
public abstract class Atom implements Cloneable {

    /**
     * The type of the atom (default value: ordinary atom)
     */
    public int type = TeXConstants.TYPE_ORDINARY;
    
    public int type_limits = TeXConstants.SCRIPT_NOLIMITS;

    public int alignment = -1;
    
    /**
     * Convert this atom into a {@link Box}, using properties set by "parent"
     * atoms, like the TeX style, the last used font, color settings, ...
     * 
     * @param env the current environment settings
     * @return the resulting box.
     */
    public abstract Box createBox(TeXEnvironment env);
    
    /**
     * Get the type of the leftermost child atom. Most atoms have no child atoms,
     * so the "left type" and the "right type" are the same: the atom's type. This
     * also is the default implementation.
     * But Some atoms are composed of child atoms put one after another in a 
     * horizontal row. These atoms must override this method.
     * 
     * @return the type of the leftermost child atom
     */
    public int getLeftType() {
      return type;
    }
    
    /**
     * Get the type of the rightermost child atom. Most atoms have no child atoms,
     * so the "left type" and the "right type" are the same: the atom's type. This
     * also is the default implementation.
     * But Some atoms are composed of child atoms put one after another in a 
     * horizontal row. These atoms must override this method.
     * 
     * @return the type of the rightermost child atom
     */
    public int getRightType() {
	return type;
    }
    
    public Atom clone() {
	try {
	    return (Atom)super.clone();
	} catch (Exception e) {
	    return null;
	}
    }
}
