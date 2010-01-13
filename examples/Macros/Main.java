/* Main.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://jlatexmath.sourceforge.net
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

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants; 
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 * A class to test LaTeX rendering.
 **/
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        TeXFormula.addPredefinedCommands(new FileInputStream("Package_Foo.xml"));	
	String latex = "\\begin{array}{l}";
        latex += "\\fooA{\\pi}{C}\\\\";
        latex += "\\mbox{A red circle }\\fooB{75.3}\\\\";
        latex += "\\mbox{A red disk }\\fooC[abc]{126.7}\\\\";
        latex += "\\mbox{An other red circle }\\fooD{159.81}[ab]";
        latex += "\\end{array}";
	
	TeXFormula formula = new TeXFormula(latex);
	TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
	icon.setInsets(new Insets(5, 5, 5, 5));
	
	BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = image.createGraphics();
	g2.setColor(Color.white);
	g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
	JLabel jl = new JLabel();
	jl.setForeground(new Color(0, 0, 0));
	icon.paintIcon(jl, g2, 0, 0);
	File file = new File("Example.png");
	try {
	    ImageIO.write(image, "png", file.getAbsoluteFile());
	} catch (IOException ex) { }
    }    
}