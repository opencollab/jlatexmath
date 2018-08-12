/* AlphabetManager.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2018 DENIZET Calixte
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
 * Linking this library statically or dynamically with other modules
 * is making a combined work based on this library. Thus, the terms
 * and conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce
 * an executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under terms
 * of your choice, provided that you also meet, for each linked independent
 * module, the terms and conditions of the license of that module.
 * An independent module is a module which is not derived from or based
 * on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obliged to do so.
 * If you do not wish to do so, delete this exception statement from your
 * version.
 *
 */

package org.scilab.forge.jlatexmath;

import java.lang.Character.UnicodeBlock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlphabetManager {

    private final Map<Character.UnicodeBlock, AlphabetRegistration> registeredAlphabets = new HashMap<>();
    private final Set<Character.UnicodeBlock> loadedAlphabets = new HashSet<Character.UnicodeBlock>() {
        {
            add(Character.UnicodeBlock.BASIC_LATIN); // Default font handles this block
        }
    };
    private static final AlphabetManager instance = new AlphabetManager();

    private AlphabetManager() {
        // Just try to load cyrillic and greek alphabets if they're here
        // Don't put in import section, since they'll be required to compile and to load
        try {
            registerAlphabet((AlphabetRegistration)Class.forName("org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration").newInstance());
        } catch (Exception e) { }

        try {
            registerAlphabet((AlphabetRegistration)Class.forName("org.scilab.forge.jlatexmath.greek.GreekRegistration").newInstance());
        } catch (Exception e) { }
    }

    public static AlphabetManager get() {
        return instance;
    }

    public void addBlock(Character.UnicodeBlock block) {
        if (!loadedAlphabets.contains(block)) {
            addAlphabet(registeredAlphabets.get(block));
        }
    }

    public void registerAlphabet(AlphabetRegistration reg) {
        Character.UnicodeBlock[] blocks = reg.getUnicodeBlock();
        for (Character.UnicodeBlock block : reg.getUnicodeBlock()) {
            registeredAlphabets.put(block, reg);
        }
    }

    private void addAlphabet(AlphabetRegistration reg) {
        if (reg != null) {
            try {
                addAlphabet(reg.getPackage(), reg.getUnicodeBlock(), reg.getTeXFontFileName());
                reg.initMapping();
            } catch (AlphabetRegistrationException e) {
                System.err.println(e.toString());
            }
        }
    }

    private void addAlphabet(Object base, Character.UnicodeBlock[] alphabet, String language) {
        Configuration.get().loadTeXFont(base, language);
        for (Character.UnicodeBlock block : alphabet) {
            loadedAlphabets.add(block);
        }
    }
}
