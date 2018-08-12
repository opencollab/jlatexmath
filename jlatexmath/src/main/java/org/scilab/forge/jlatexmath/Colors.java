/* Colors.java
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


import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * An atom representing the foreground and background color of an other atom.
 */
public class Colors {

    private static Map<String, Color> all;

    private static final void init() {
        all = new HashMap<String, Color>() {
            {
                put("AliceBlue", new Color(15792383));
                put("AntiqueWhite", new Color(16444375));
                put("Apricot", new Color(16756090));
                put("Aqua", new Color(65535));
                put("Aquamarine", new Color(3080115));
                put("Azure", new Color(15794175));
                put("AzureAzureBlue", new Color(26367));
                put("AzureAzureCyan", new Color(39423));
                put("AzureBlueDark", new Color(13209));
                put("AzureBlueLight", new Color(6724095));
                put("AzureBlueMedium", new Color(3368652));
                put("AzureCyanDark", new Color(26265));
                put("AzureCyanLight", new Color(6737151));
                put("AzureCyanMedium", new Color(3381708));
                put("AzureDarkDull", new Color(3368601));
                put("AzureDarkHard", new Color(26316));
                put("AzureLightDull", new Color(6724044));
                put("AzureLightHard", new Color(3381759));
                put("AzureObscureDull", new Color(13158));
                put("AzurePaleDull", new Color(10079487));
                put("Beige", new Color(16119260));
                put("Bisque", new Color(16770244));
                put("Bittersweet", new Color(12726272));
                put("Black", new Color(0));
                put("BlanchedAlmond", new Color(16772045));
                put("Blue", new Color(255));
                put("BlueAzureDark", new Color(13260));
                put("BlueAzureLight", new Color(3368703));
                put("BlueBlueAzure", new Color(13311));
                put("BlueBlueViolet", new Color(3342591));
                put("BlueDarkDull", new Color(3355545));
                put("BlueDarkFaded", new Color(153));
                put("BlueDarkHard", new Color(204));
                put("BlueDarkWeak", new Color(3355494));
                put("BlueGreen", new Color(2555819));
                put("BlueLightDull", new Color(6710988));
                put("BlueLightFaded", new Color(6711039));
                put("BlueLightHard", new Color(3355647));
                put("BlueLightWeak", new Color(10066380));
                put("BlueMediumFaded", new Color(3355596));
                put("BlueMediumWeak", new Color(6710937));
                put("BlueObscureDull", new Color(102));
                put("BlueObscureWeak", new Color(51));
                put("BluePaleDull", new Color(10066431));
                put("BluePaleWeak", new Color(13421823));
                put("BlueViolet", new Color(2234101));
                put("BlueVioletDark", new Color(3342540));
                put("BlueVioletLight", new Color(6697983));
                put("BrickRed", new Color(12063755));
                put("Brown", new Color(6689536));
                put("BurlyWood", new Color(14596231));
                put("BurntOrange", new Color(16743680));
                put("CadetBlue", new Color(6385348));
                put("CarnationPink", new Color(16735999));
                put("Cerulean", new Color(1041407));
                put("Chartreuse", new Color(8388352));
                put("Chocolate", new Color(13789470));
                put("Coral", new Color(16744272));
                put("CornflowerBlue", new Color(5889791));
                put("Cornsilk", new Color(16775388));
                put("Crimson", new Color(14423100));
                put("Cyan", new Color(65535));
                put("CyanAzureDark", new Color(39372));
                put("CyanAzureLight", new Color(3394815));
                put("CyanCyanAzure", new Color(52479));
                put("CyanCyanTeal", new Color(65484));
                put("CyanDarkDull", new Color(3381657));
                put("CyanDarkFaded", new Color(39321));
                put("CyanDarkHard", new Color(52428));
                put("CyanDarkWeak", new Color(3368550));
                put("CyanLightDull", new Color(6737100));
                put("CyanLightFaded", new Color(6750207));
                put("CyanLightHard", new Color(3407871));
                put("CyanLightWeak", new Color(10079436));
                put("CyanMediumFaded", new Color(3394764));
                put("CyanMediumWeak", new Color(6723993));
                put("CyanObscureDull", new Color(26214));
                put("CyanObscureWeak", new Color(13107));
                put("CyanPaleDull", new Color(10092543));
                put("CyanPaleWeak", new Color(13434879));
                put("CyanTealDark", new Color(52377));
                put("CyanTealLight", new Color(3407820));
                put("Dandelion", new Color(16758057));
                put("DarkBlue", new Color(139));
                put("DarkCyan", new Color(35723));
                put("DarkGoldenrod", new Color(12092939));
                put("DarkGray", new Color(11119017));
                put("DarkGreen", new Color(25600));
                put("DarkKhaki", new Color(12433259));
                put("DarkMagenta", new Color(9109643));
                put("DarkOliveGreen", new Color(5597999));
                put("DarkOrange", new Color(16747520));
                put("DarkOrchid", new Color(10040268));
                put("DarkRed", new Color(9109504));
                put("DarkSalmon", new Color(15308410));
                put("DarkSeaGreen", new Color(9419919));
                put("DarkSlateBlue", new Color(4734347));
                put("DarkSlateGray", new Color(3100495));
                put("DarkTurquoise", new Color(52945));
                put("DarkViolet", new Color(9699539));
                put("DeepPink", new Color(16716947));
                put("DeepSkyBlue", new Color(49151));
                put("DimGray", new Color(6908265));
                put("DodgerBlue", new Color(2003199));
                put("Emerald", new Color(65408));
                put("FireBrick", new Color(11674146));
                put("FloralWhite", new Color(16775920));
                put("ForestGreen", new Color(1368091));
                put("Fuchsia", new Color(8132075));
                put("Gainsboro", new Color(14474460));
                put("GhostWhite", new Color(16316671));
                put("Gold", new Color(16766720));
                put("Goldenrod", new Color(16770601));
                put("Gray", new Color(8421504));
                put("GrayDark", new Color(6710886));
                put("GrayLight", new Color(10066329));
                put("GrayObscure", new Color(3355443));
                put("GrayPale", new Color(13421772));
                put("Green", new Color(65280));
                put("GreenDarkDull", new Color(3381555));
                put("GreenDarkFaded", new Color(39168));
                put("GreenDarkHard", new Color(52224));
                put("GreenDarkWeak", new Color(3368499));
                put("GreenGreenSpring", new Color(3407616));
                put("GreenGreenTeal", new Color(65331));
                put("GreenLightDull", new Color(6736998));
                put("GreenLightFaded", new Color(6750054));
                put("GreenLightHard", new Color(3407667));
                put("GreenLightWeak", new Color(10079385));
                put("GreenMediumFaded", new Color(3394611));
                put("GreenMediumWeak", new Color(6723942));
                put("GreenObscureDull", new Color(26112));
                put("GreenObscureWeak", new Color(13056));
                put("GreenPaleDull", new Color(10092441));
                put("GreenPaleWeak", new Color(13434828));
                put("GreenSpringDark", new Color(3394560));
                put("GreenSpringLight", new Color(6750003));
                put("GreenTealDark", new Color(52275));
                put("GreenTealLight", new Color(3407718));
                put("GreenYellow", new Color(14286671));
                put("Honeydew", new Color(15794160));
                put("HotPink", new Color(16738740));
                put("IndianRed", new Color(13458524));
                put("Indigo", new Color(4915330));
                put("Ivory", new Color(16777200));
                put("JungleGreen", new Color(262010));
                put("Khaki", new Color(15787660));
                put("Lavender", new Color(16745983));
                put("LavenderBlush", new Color(16773365));
                put("LawnGreen", new Color(8190976));
                put("LemonChiffon", new Color(16775885));
                put("LightBlue", new Color(11393254));
                put("LightCoral", new Color(15761536));
                put("LightCyan", new Color(14745599));
                put("LightGoldenrod", new Color(16448210));
                put("LightGray", new Color(13882323));
                put("LightGreen", new Color(9498256));
                put("LightPink", new Color(16758465));
                put("LightSalmon", new Color(16752762));
                put("LightSeaGreen", new Color(2142890));
                put("LightSkyBlue", new Color(8900346));
                put("LightSlateGray", new Color(7833753));
                put("LightSteelBlue", new Color(11584734));
                put("LightYellow", new Color(16777184));
                put("Lime", new Color(65280));
                put("LimeGreen", new Color(8453888));
                put("Linen", new Color(16445670));
                put("Magenta", new Color(16711935));
                put("MagentaDarkDull", new Color(10040217));
                put("MagentaDarkFaded", new Color(10027161));
                put("MagentaDarkHard", new Color(13369548));
                put("MagentaDarkWeak", new Color(6697830));
                put("MagentaLightDull", new Color(13395660));
                put("MagentaLightFaded", new Color(16738047));
                put("MagentaLightHard", new Color(16724991));
                put("MagentaLightWeak", new Color(13408716));
                put("MagentaMagentaPink", new Color(16711884));
                put("MagentaMagentaViolet", new Color(13369599));
                put("MagentaMediumFaded", new Color(13382604));
                put("MagentaMediumWeak", new Color(10053273));
                put("MagentaObscureDull", new Color(6684774));
                put("MagentaObscureWeak", new Color(3342387));
                put("MagentaPaleDull", new Color(16751103));
                put("MagentaPaleWeak", new Color(16764159));
                put("MagentaPinkDark", new Color(13369497));
                put("MagentaPinkLight", new Color(16724940));
                put("MagentaVioletDark", new Color(10027212));
                put("MagentaVioletLight", new Color(13382655));
                put("Mahogany", new Color(10885398));
                put("Maroon", new Color(11343671));
                put("MediumAquamarine", new Color(6737322));
                put("MediumBlue", new Color(205));
                put("MediumOrchid", new Color(12211667));
                put("MediumPurple", new Color(9662683));
                put("MediumSeaGreen", new Color(3978097));
                put("MediumSlateBlue", new Color(8087790));
                put("MediumSpringGreen", new Color(64154));
                put("MediumTurquoise", new Color(4772300));
                put("MediumVioletRed", new Color(13047173));
                put("Melon", new Color(16747136));
                put("MidnightBlue", new Color(229009));
                put("MintCream", new Color(16121850));
                put("MistyRose", new Color(16770273));
                put("Moccasin", new Color(16770229));
                put("Mulberry", new Color(10820090));
                put("NavajoWhite", new Color(16768685));
                put("Navy", new Color(128));
                put("NavyBlue", new Color(1013247));
                put("OldLace", new Color(16643558));
                put("Olive", new Color(8421376));
                put("OliveDrab", new Color(7048739));
                put("OliveGreen", new Color(3643656));
                put("Orange", new Color(16737057));
                put("OrangeDarkDull", new Color(10053171));
                put("OrangeDarkHard", new Color(13395456));
                put("OrangeLightDull", new Color(13408614));
                put("OrangeLightHard", new Color(16750899));
                put("OrangeObscureDull", new Color(6697728));
                put("OrangeOrangeRed", new Color(16737792));
                put("OrangeOrangeYellow", new Color(16750848));
                put("OrangePaleDull", new Color(16764057));
                put("OrangeRed", new Color(16711808));
                put("OrangeRedDark", new Color(10040064));
                put("OrangeRedLight", new Color(16750950));
                put("OrangeRedMedium", new Color(13395507));
                put("OrangeYellowDark", new Color(10053120));
                put("OrangeYellowLight", new Color(16764006));
                put("OrangeYellowMedium", new Color(13408563));
                put("Orchid", new Color(11361535));
                put("PaleGoldenrod", new Color(15657130));
                put("PaleGreen", new Color(10025880));
                put("PaleTurquoise", new Color(11529966));
                put("PaleVioletRed", new Color(14381203));
                put("PapayaWhip", new Color(16773077));
                put("Peach", new Color(16744525));
                put("PeachPuff", new Color(16767673));
                put("Periwinkle", new Color(7238655));
                put("Peru", new Color(13468991));
                put("PineGreen", new Color(1032014));
                put("Pink", new Color(16761035));
                put("PinkDarkDull", new Color(10040166));
                put("PinkDarkHard", new Color(13369446));
                put("PinkLightDull", new Color(13395609));
                put("PinkLightHard", new Color(16724889));
                put("PinkMagentaDark", new Color(10027110));
                put("PinkMagentaLight", new Color(16737996));
                put("PinkMagentaMedium", new Color(13382553));
                put("PinkObscureDull", new Color(6684723));
                put("PinkPaleDull", new Color(16751052));
                put("PinkPinkMagenta", new Color(16711833));
                put("PinkPinkRed", new Color(16711782));
                put("PinkRedDark", new Color(10027059));
                put("PinkRedLight", new Color(16737945));
                put("PinkRedMedium", new Color(13382502));
                put("Plum", new Color(8388863));
                put("PowderBlue", new Color(11591910));
                put("ProcessBlue", new Color(720895));
                put("Purple", new Color(9184511));
                put("RawSienna", new Color(9185024));
                put("RebeccaPurple", new Color(6697881));
                put("Red", new Color(16711680));
                put("RedDarkDull", new Color(10040115));
                put("RedDarkFaded", new Color(10027008));
                put("RedDarkHard", new Color(13369344));
                put("RedDarkWeak", new Color(6697779));
                put("RedLightDull", new Color(13395558));
                put("RedLightFaded", new Color(16737894));
                put("RedLightHard", new Color(16724787));
                put("RedLightWeak", new Color(13408665));
                put("RedMediumFaded", new Color(13382451));
                put("RedMediumWeak", new Color(10053222));
                put("RedObscureDull", new Color(6684672));
                put("RedObscureWeak", new Color(3342336));
                put("RedOrange", new Color(16726817));
                put("RedOrangeDark", new Color(13382400));
                put("RedOrangeLight", new Color(16737843));
                put("RedPaleDull", new Color(16751001));
                put("RedPaleWeak", new Color(16764108));
                put("RedPinkDark", new Color(13369395));
                put("RedPinkLight", new Color(16724838));
                put("RedRedOrange", new Color(16724736));
                put("RedRedPink", new Color(16711731));
                put("RedViolet", new Color(10293672));
                put("Rhodamine", new Color(16723711));
                put("RosyBrown", new Color(12357519));
                put("RoyalBlue", new Color(33023));
                put("RoyalPurple", new Color(4200959));
                put("RubineRed", new Color(16711902));
                put("SaddleBrown", new Color(9127187));
                put("Salmon", new Color(16742558));
                put("SandyBrown", new Color(16032864));
                put("SeaGreen", new Color(5242752));
                put("Seashell", new Color(16774638));
                put("Sepia", new Color(5049600));
                put("Sienna", new Color(10506797));
                put("Silver", new Color(12632256));
                put("SkyBlue", new Color(6422496));
                put("SlateBlue", new Color(6970061));
                put("SlateGray", new Color(7372944));
                put("Snow", new Color(16775930));
                put("SpringDarkDull", new Color(6723891));
                put("SpringDarkHard", new Color(6736896));
                put("SpringGreen", new Color(12451645));
                put("SpringGreenDark", new Color(3381504));
                put("SpringGreenLight", new Color(10092390));
                put("SpringGreenMedium", new Color(6736947));
                put("SpringLightDull", new Color(10079334));
                put("SpringLightHard", new Color(10092339));
                put("SpringObscureDull", new Color(3368448));
                put("SpringPaleDull", new Color(13434777));
                put("SpringSpringGreen", new Color(6749952));
                put("SpringSpringYellow", new Color(10092288));
                put("SpringYellowDark", new Color(6723840));
                put("SpringYellowLight", new Color(13434726));
                put("SpringYellowMedium", new Color(10079283));
                put("SteelBlue", new Color(4620980));
                put("Tan", new Color(14390384));
                put("Teal", new Color(32896));
                put("TealBlue", new Color(2357925));
                put("TealCyanDark", new Color(39270));
                put("TealCyanLight", new Color(6750156));
                put("TealCyanMedium", new Color(3394713));
                put("TealDarkDull", new Color(3381606));
                put("TealDarkHard", new Color(52326));
                put("TealGreenDark", new Color(39219));
                put("TealGreenLight", new Color(6750105));
                put("TealGreenMedium", new Color(3394662));
                put("TealLightDull", new Color(6737049));
                put("TealLightHard", new Color(3407769));
                put("TealObscureDull", new Color(26163));
                put("TealPaleDull", new Color(10092492));
                put("TealTealCyan", new Color(65433));
                put("TealTealGreen", new Color(65382));
                put("Thistle", new Color(14707199));
                put("Tomato", new Color(16737095));
                put("Turquoise", new Color(2555852));
                put("Violet", new Color(3547135));
                put("VioletBlueDark", new Color(3342489));
                put("VioletBlueLight", new Color(10053375));
                put("VioletBlueMedium", new Color(6697932));
                put("VioletDarkDull", new Color(6697881));
                put("VioletDarkHard", new Color(6684876));
                put("VioletLightDull", new Color(10053324));
                put("VioletLightHard", new Color(10040319));
                put("VioletMagentaDark", new Color(6684825));
                put("VioletMagentaLight", new Color(13395711));
                put("VioletMagentaMedium", new Color(10040268));
                put("VioletObscureDull", new Color(3342438));
                put("VioletPaleDull", new Color(13408767));
                put("VioletRed", new Color(16724223));
                put("VioletVioletBlue", new Color(6684927));
                put("VioletVioletMagenta", new Color(10027263));
                put("Wheat", new Color(16113331));
                put("White", new Color(16777215));
                put("WhiteSmoke", new Color(16119285));
                put("WildStrawberry", new Color(16714396));
                put("Yellow", new Color(16776960));
                put("YellowDarkDull", new Color(10066227));
                put("YellowDarkFaded", new Color(10066176));
                put("YellowDarkHard", new Color(13421568));
                put("YellowDarkWeak", new Color(6710835));
                put("YellowGreen", new Color(9436994));
                put("YellowLightDull", new Color(13421670));
                put("YellowLightFaded", new Color(16777062));
                put("YellowLightHard", new Color(16777011));
                put("YellowLightWeak", new Color(13421721));
                put("YellowMediumFaded", new Color(13421619));
                put("YellowMediumWeak", new Color(10066278));
                put("YellowObscureDull", new Color(6710784));
                put("YellowObscureWeak", new Color(3355392));
                put("YellowOrange", new Color(16749568));
                put("YellowOrangeDark", new Color(13408512));
                put("YellowOrangeLight", new Color(16763955));
                put("YellowPaleDull", new Color(16777113));
                put("YellowPaleWeak", new Color(16777164));
                put("YellowSpringDark", new Color(10079232));
                put("YellowSpringLight", new Color(13434675));
                put("YellowYellowOrange", new Color(16763904));
                put("YellowYellowSpring", new Color(13434624));
                put("aliceblue", new Color(15792383));
                put("antiquewhite", new Color(16444375));
                put("aqua", new Color(65535));
                put("aquamarine", new Color(8388564));
                put("azure", new Color(15794175));
                put("beige", new Color(16119260));
                put("bisque", new Color(16770244));
                put("black", new Color(0));
                put("blanchedalmond", new Color(16772045));
                put("blue", new Color(255));
                put("blueviolet", new Color(9055202));
                put("brown", new Color(10824234));
                put("burlywood", new Color(14596231));
                put("cadetblue", new Color(6266528));
                put("chartreuse", new Color(8388352));
                put("chocolate", new Color(13789470));
                put("coral", new Color(16744272));
                put("cornflowerblue", new Color(6591981));
                put("cornsilk", new Color(16775388));
                put("crimson", new Color(14423100));
                put("cyan", new Color(65535));
                put("darkblue", new Color(139));
                put("darkcyan", new Color(35723));
                put("darkgoldenrod", new Color(12092939));
                put("darkgray", new Color(11119017));
                put("darkgreen", new Color(25600));
                put("darkgrey", new Color(11119017));
                put("darkkhaki", new Color(12433259));
                put("darkmagenta", new Color(9109643));
                put("darkolivegreen", new Color(5597999));
                put("darkorange", new Color(16747520));
                put("darkorchid", new Color(10040012));
                put("darkred", new Color(9109504));
                put("darksalmon", new Color(15308410));
                put("darkseagreen", new Color(9419919));
                put("darkslateblue", new Color(4734347));
                put("darkslategray", new Color(3100495));
                put("darkslategrey", new Color(3100495));
                put("darkturquoise", new Color(52945));
                put("darkviolet", new Color(9699539));
                put("deeppink", new Color(16716947));
                put("deepskyblue", new Color(49151));
                put("dimgray", new Color(6908265));
                put("dimgrey", new Color(6908265));
                put("dodgerblue", new Color(2003199));
                put("firebrick", new Color(11674146));
                put("floralwhite", new Color(16775920));
                put("forestgreen", new Color(2263842));
                put("fuchsia", new Color(16711935));
                put("gainsboro", new Color(14474460));
                put("ghostwhite", new Color(16316671));
                put("gold", new Color(16766720));
                put("goldenrod", new Color(14329120));
                put("gray", new Color(8421504));
                put("green", new Color(32768));
                put("greenyellow", new Color(11403055));
                put("grey", new Color(8421504));
                put("honeydew", new Color(15794160));
                put("hotpink", new Color(16738740));
                put("indianred", new Color(13458524));
                put("indigo", new Color(4915330));
                put("ivory", new Color(16777200));
                put("khaki", new Color(15787660));
                put("lavender", new Color(15132410));
                put("lavenderblush", new Color(16773365));
                put("lawngreen", new Color(8190976));
                put("lemonchiffon", new Color(16775885));
                put("lightblue", new Color(11393254));
                put("lightcoral", new Color(15761536));
                put("lightcyan", new Color(14745599));
                put("lightgoldenrodyellow", new Color(16448210));
                put("lightgray", new Color(13882323));
                put("lightgreen", new Color(9498256));
                put("lightgrey", new Color(13882323));
                put("lightpink", new Color(16758465));
                put("lightsalmon", new Color(16752762));
                put("lightseagreen", new Color(2142890));
                put("lightskyblue", new Color(8900346));
                put("lightslategray", new Color(7833753));
                put("lightslategrey", new Color(7833753));
                put("lightsteelblue", new Color(11584734));
                put("lightyellow", new Color(16777184));
                put("lime", new Color(65280));
                put("limegreen", new Color(3329330));
                put("linen", new Color(16445670));
                put("magenta", new Color(16711935));
                put("maroon", new Color(8388608));
                put("mediumaquamarine", new Color(6737322));
                put("mediumblue", new Color(205));
                put("mediumorchid", new Color(12211667));
                put("mediumpurple", new Color(9662683));
                put("mediumseagreen", new Color(3978097));
                put("mediumslateblue", new Color(8087790));
                put("mediumspringgreen", new Color(64154));
                put("mediumturquoise", new Color(4772300));
                put("mediumvioletred", new Color(13047173));
                put("midnightblue", new Color(1644912));
                put("mintcream", new Color(16121850));
                put("mistyrose", new Color(16770273));
                put("moccasin", new Color(16770229));
                put("navajowhite", new Color(16768685));
                put("navy", new Color(128));
                put("oldlace", new Color(16643558));
                put("olive", new Color(8421376));
                put("olivedrab", new Color(7048739));
                put("orange", new Color(16753920));
                put("orangered", new Color(16729344));
                put("orchid", new Color(14315734));
                put("palegoldenrod", new Color(15657130));
                put("palegreen", new Color(10025880));
                put("paleturquoise", new Color(11529966));
                put("palevioletred", new Color(14381203));
                put("papayawhip", new Color(16773077));
                put("peachpuff", new Color(16767673));
                put("peru", new Color(13468991));
                put("pink", new Color(16761035));
                put("plum", new Color(14524637));
                put("powderblue", new Color(11591910));
                put("purple", new Color(8388736));
                put("red", new Color(16711680));
                put("rosybrown", new Color(12357519));
                put("royalblue", new Color(4286945));
                put("saddlebrown", new Color(9127187));
                put("salmon", new Color(16416787));
                put("sandybrown", new Color(16032864));
                put("seagreen", new Color(3050327));
                put("seashell", new Color(16774638));
                put("sienna", new Color(10506797));
                put("silver", new Color(12632256));
                put("skyblue", new Color(8900331));
                put("slateblue", new Color(6970061));
                put("slategray", new Color(7372944));
                put("slategrey", new Color(7372944));
                put("snow", new Color(16775930));
                put("springgreen", new Color(65407));
                put("steelblue", new Color(4620980));
                put("tan", new Color(13808780));
                put("teal", new Color(32896));
                put("thistle", new Color(14204888));
                put("tomato", new Color(16737095));
                put("turquoise", new Color(4251856));
                put("violet", new Color(15631086));
                put("wheat", new Color(16113331));
                put("white", new Color(16777215));
                put("whitesmoke", new Color(16119285));
                put("yellow", new Color(16776960));
                put("yellowgreen", new Color(10145074));
            }
        };
    }

    public static Color getFromName(final String name) {
        if (all == null) {
            init();
        }
        final Color c = all.get(name);
        if (c == null) {
            return all.get(name.toLowerCase());
        }
        return c;
    }

    public static void add(final String name, final Color color) {
        if (all == null) {
            init();
        }
        all.put(name, color);
    }

    public static Color conv(final double c, final double m, final double y, final double k) {
        final double kk = 255. * (1. - k);
        final int R = (int)(kk * (1. - c) + 0.5);
        final int G = (int)(kk * (1. - m) + 0.5);
        final int B = (int)(kk * (1. - y) + 0.5);
        return new Color((R << 16) | (G << 8) | B);
    }

    public static Color convHSB(final double h, final double s, final double l) {
        final double h1 = normH(h);
        return new Color(Color.HSBtoRGB((float)h1, (float)s, (float)l));
    }

    public static Color convHSL(final double h, final double s, final double l, final double a) {
        // https://www.w3.org/TR/css3-color/#hsl-color for algorithm
        final double ls = l * s;
        final double m2 = l + (l <= 0.5 ? ls : (s - ls));
        final double m1 = l * 2. - m2;
        final double h1 = normH(h);
        final float R = (float)HUEtoRGB(m1, m2, h1 + 1. / 3.);
        final float G = (float)HUEtoRGB(m1, m2, h1);
        final float B = (float)HUEtoRGB(m1, m2, h1 - 1. / 3.);

        return new Color(R, G, B, (float)a);
    }

    public static Color convHSL(final double h, final double s, final double l) {
        return convHSL(h, s, l, 1f);
    }

    private static double HUEtoRGB(final double m1, final double m2, double h) {
        if (h < 0.) {
            h += 1.;
        } else if (h > 1.) {
            h -= 1.;
        }
        final double h6 = h * 6.;
        if (h6 < 1.) {
            return m1 + (m2 - m1) * h6;
        }
        if (h * 2. < 1.) {
            return m2;
        }
        if (h * 3. < 2.) {
            return m1 + (m2 - m1) * (4. - h6);
        }
        return m1;
    }

    private static double mod360(final double x) {
        return x - Math.floor(x / 360.) * 360.;
    }

    private static double normH(final double x) {
        return mod360(mod360(x) + 360.) / 360.;
    }

    private static double adjust(final double c, final double factor) {
        if (c == 0. || factor == 0.) {
            return 0.;
        }

        final double Gamma = 0.8;
        return Math.round(Math.pow(c * factor, Gamma));
    }

    public static Color convWave(final double waveLen) {
        double R, G, B;

        if (waveLen >= 380. && waveLen <= 439.) {
            R = -(waveLen - 440.) / 60.;
            G = 0.;
            B = 1.;
        } else if (waveLen >= 440. && waveLen <= 489.) {
            R = 0.;
            G = (waveLen - 440.) / 50.;
            B = 1.;
        } else if (waveLen >= 490. && waveLen <= 509.) {
            R = 0.;
            G = 1.;
            B = -(waveLen - 510.) / 20.;
        } else if (waveLen >= 510. && waveLen <= 579.) {
            R = (waveLen - 510.) / 70.;
            G = 1.;
            B = 0.;
        } else if (waveLen >= 580. && waveLen <= 644.) {
            R = 1.;
            G = -(waveLen - 645.) / 65.;
            B = 0.;
        } else if (waveLen >= 645. && waveLen <= 780.) {
            R = 1.;
            G = 0.;
            B = 0.;
        } else {
            R = 0.;
            G = 0.;
            B = 0.;
        }

        final double twave = Math.floor(waveLen);
        double factor;
        if (twave >= 380. && twave <= 419.) {
            factor = 0.3 + 0.7 * (waveLen - 380.) / 40.;
        } else if (twave >= 420. && twave <= 700.) {
            factor = 1.;
        } else if (twave >= 701. && twave <= 780.) {
            factor = 0.3 + 0.7 * (780. - waveLen) / 80.;
        } else {
            factor = 0.;
        }

        R = adjust(R, factor);
        G = adjust(G, factor);
        B = adjust(B, factor);

        return new Color((float)R, (float)G, (float)B);
    }
}
