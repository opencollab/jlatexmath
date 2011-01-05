/* ParseOption.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 * 
 * Copyright (C) 2011 DENIZET Calixte
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

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Parse command options, e.g. \includegraphics[width=1cm,height=2in,keepaspectratio]{...}
 */
public final class ParseOption {

    public final static Map<String, String> parseMap(String options) {
	Map<String, String> map = new HashMap<String, String>();
	if (options == null || options.length() == 0) {
	    return map;
	}
	StringTokenizer tokens = new StringTokenizer(options, ",");
	while (tokens.hasMoreTokens()) {
	    String tok = tokens.nextToken().trim();
	    String[] optarg = tok.split("=");
	    if (optarg != null){
		if (optarg.length == 2) {
		    map.put(optarg[0].trim(), optarg[1].trim());
		} else if (optarg.length == 1) {
		    map.put(optarg[0].trim(), null);
		}
	    }
	}
	
	return map;
    }

}