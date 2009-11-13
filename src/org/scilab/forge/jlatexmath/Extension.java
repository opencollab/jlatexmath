/* Extension.java
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
 * Represents an extension character that is defined by Char-objects of it's 4
 * possible parts (null means part not present).
 */
public class Extension {

    // there ALLWAYS is a repeat character! (check TFM.isExtensionChar())
    private final Char top;
    private final Char middle;
    private final Char bottom;
    private final Char repeat;
    
    public Extension(Char t, Char m, Char r, Char b) {
	top = t;
	middle = m;
	repeat = r;
	bottom = b;
    }
    
    public boolean hasTop() {
	return top != null;
    }
    
    public boolean hasMiddle() {
	return middle != null;
    }

    public boolean hasBottom() {
	return bottom != null;
    }
    
    public Char getTop() {
	return top;
    }
    
    public Char getMiddle() {
	return middle;
    }
    
    public Char getRepeat() {
	return repeat;
    }
    
    public Char getBottom() {
	return bottom;
    }
}
