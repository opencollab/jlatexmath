/* CharMapping.java
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

package org.scilab.forge.jlatexmath.cyrillic;

final class CharMapping {

    public static void init() {
        final org.scilab.forge.jlatexmath.CharMapping cm = org.scilab.forge.jlatexmath.CharMapping.getDefault();
        cm.putSym('ı', "dotlessi");
        cm.putSym('А', "CYRA");
        cm.putSym('Б', "CYRB");
        cm.putSym('В', "CYRV");
        cm.putSym('Г', "CYRG");
        cm.putSym('Д', "CYRD");
        cm.putSym('Е', "CYRE");
        cm.putSym('Ё', "CYRYO");
        cm.putSym('Ж', "CYRZH");
        cm.putSym('З', "CYRZ");
        cm.putSym('И', "CYRI");
        cm.putSym('Й', "CYRIO");
        cm.putSym('К', "CYRK");
        cm.putSym('Л', "CYRL");
        cm.putSym('М', "CYRM");
        cm.putSym('Н', "CYRN");
        cm.putSym('О', "CYRO");
        cm.putSym('П', "CYRP");
        cm.putSym('Р', "CYRR");
        cm.putSym('С', "CYRS");
        cm.putSym('Т', "CYRT");
        cm.putSym('У', "CYRU");
        cm.putSym('Ф', "CYRF");
        cm.putSym('Х', "CYRH");
        cm.putSym('Ц', "CYRC");
        cm.putSym('Ч', "CYRCH");
        cm.putSym('Ш', "CYRSH");
        cm.putSym('Щ', "CYRSHCH");
        cm.putSym('Ъ', "CYRHRDSN");
        cm.putSym('Ы', "CYRY");
        cm.putSym('Ь', "CYRSFTSN");
        cm.putSym('Э', "CYREREV");
        cm.putSym('Ю', "CYRYU");
        cm.putSym('Я', "CYRYA");
        cm.putSym('а', "cyra");
        cm.putSym('б', "cyrb");
        cm.putSym('в', "cyrv");
        cm.putSym('г', "cyrg");
        cm.putSym('д', "cyrd");
        cm.putSym('е', "cyre");
        cm.putSym('ё', "cyryo");
        cm.putSym('ж', "cyrzh");
        cm.putSym('з', "cyrz");
        cm.putSym('и', "cyri");
        cm.putSym('й', "cyrio");
        cm.putSym('к', "cyrk");
        cm.putSym('л', "cyrl");
        cm.putSym('м', "cyrm");
        cm.putSym('н', "cyrn");
        cm.putSym('о', "cyro");
        cm.putSym('п', "cyrp");
        cm.putSym('р', "cyrr");
        cm.putSym('с', "cyrs");
        cm.putSym('т', "cyrt");
        cm.putSym('у', "cyru");
        cm.putSym('ф', "cyrf");
        cm.putSym('х', "cyrh");
        cm.putSym('ц', "cyrc");
        cm.putSym('ч', "cyrch");
        cm.putSym('ш', "cyrsh");
        cm.putSym('щ', "cyrshch");
        cm.putSym('ъ', "cyrhrdsn");
        cm.putSym('ы', "cyry");
        cm.putSym('ь', "cyrsftsn");
        cm.putSym('э', "cyrerev");
        cm.putSym('ю', "cyryu");
        cm.putSym('я', "cyrya");
        cm.putSym('Є', "CYRIE");
        cm.putSym('І', "CYRII");
        cm.putSym('є', "cyrie");
        cm.putSym('і', "cyrii");
        cm.putSym('Ђ', "CYRDJE");
        cm.putSym('Ѕ', "CYRDZE");
        cm.putSym('Ј', "CYRJE");
        cm.putSym('Љ', "CYRLJE");
        cm.putSym('Њ', "CYRNJE");
        cm.putSym('Ћ', "CYRTSHE");
        cm.putSym('Џ', "CYRDZHE");
        cm.putSym('Ѵ', "CYRIZH");
        cm.putSym('Ѣ', "CYRYAT");
        cm.putSym('Ѳ', "CYRFITA");
        cm.putSym('ђ', "cyrdje");
        cm.putSym('ѕ', "cyrdze");
        cm.putSym('ј', "cyrje");
        cm.putSym('љ', "cyrlje");
        cm.putSym('њ', "cyrnje");
        cm.putSym('ћ', "cyrtshe");
        cm.putSym('џ', "cyrdzhe");
        cm.putSym('ѵ', "cyrizh");
        cm.putSym('ѣ', "cyryat");
        cm.putSym('ѳ', "cyrfita");
        cm.putForm('Ѐ', "\\`\\CYRE");
        cm.putForm('Ѓ', "\\'\\CYRG");
        cm.putForm('Ї', "\\cyrddot\\CYRII");
        cm.putForm('ї', "\\cyrddot\\dotlessi");
        cm.putForm('Ѓ', "\\'\\CYRG");
        cm.putForm('Ѓ', "\\'\\CYRK");
        cm.putForm('Ѝ', "\\`\\CYRI");
        cm.putForm('Ў', "\\U\\CYRU");
        cm.putForm('ѐ', "\\`\\cyre");
        cm.putForm('ѓ', "\\'\\cyrg");
        cm.putForm('ќ', "\\'\\cyrk");
        cm.putForm('ѝ', "\\`\\cyri");
        cm.putForm('ў', "\\U\\cyru");
    }
}
