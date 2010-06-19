/* URLAlphabetRegistration.java
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

import java.lang.Character.UnicodeBlock;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.ClassLoader;

public class URLAlphabetRegistration implements AlphabetRegistration {

    private URL url;
    private String language;
    private AlphabetRegistration pack = null;
    private Character.UnicodeBlock[] blocks;
    
    private URLAlphabetRegistration(URL url, String language, Character.UnicodeBlock[] blocks) {
	this.url = url;
	this.language = language;
	this.blocks = blocks;
    }

    public static void register(URL url, String language, Character.UnicodeBlock[] blocks) {
	DefaultTeXFont.registerAlphabet(new URLAlphabetRegistration(url, language, blocks));
    }

    public Character.UnicodeBlock[] getUnicodeBlock() {
	return blocks;
    }

    public Object getPackage() throws AlphabetRegistrationException {
	URL urls[] = {url};
	language = language.toLowerCase();
	String name = "org.scilab.forge.jlatexmath." + language
	    + "." + Character.toString(Character.toUpperCase(language.charAt(0)))
	    + language.substring(1, language.length()) + "Registration"; 
	
	try {
	    ClassLoader loader = new URLClassLoader(urls);
	    pack = (AlphabetRegistration) Class.forName(name, true, loader).newInstance();
	} catch (ClassNotFoundException e) {
	    throw new AlphabetRegistrationException("Class at " + url + " cannot be got.");
	} catch (Exception e) {
	    throw new AlphabetRegistrationException("Problem in loading the class at " + url + " :\n" + e.getMessage());
	} 
	return pack;
    }

    public String getTeXFontFileName() {
	return pack.getTeXFontFileName();
    }
}