/* Example4.java
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
import org.scilab.forge.jlatexmath.TeXFormula.TeXIconBuilder;

/**
 * A class to test LaTeX rendering.
 **/
public class Example4 {
    public static void main(String[] args) {
	
	String latex = "\\begin{array}{|c|c|c|c|}\n";
	latex += "\\multicolumn{4}{c}{\\shadowbox{\\text{\\Huge An image from the \\LaTeX3 project}}}\\cr\n";
	latex += "\\hline\n";
	latex += "\\text{Left}\\includegraphics{lion.png}\\text{Right} & \\text{Left}\\includegraphics[width=3cm,interpolation=bicubic]{lion.png}\\text{Right} & \\text{Left}\\includegraphics[angle=45,width=3cm]{lion.png}\\text{Right} & \\text{Left}\\includegraphics[angle=160]{lion.png}\\text{Right} \\cr\n";
	latex += "\\hline\n";
	latex += "\\text{\\backslash includegraphics\\{lion.png\\}} & \\text{\\backslash includegraphics[width=3cm,interpolation=bicubic]\\{lion.png\\}} & \\text{\\backslash includegraphics[angle=45,width=3cm]\\{lion.png\\}} & \\text{\\backslash includegraphics[angle=160]\\{lion.png\\}}\\cr\n";
	latex += "\\hline\n";
	latex += "\\end{array}\n";

	TeXFormula formula = new TeXFormula(latex);
	// Note: Old interface for creating icons:
	//TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 10);
	// Note: New interface using builder pattern (inner class):
	TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(10).build();

	icon.setInsets(new Insets(5, 5, 5, 5));
	
	BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = image.createGraphics();
	g2.setColor(Color.white);
	g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
	JLabel jl = new JLabel();
	jl.setForeground(new Color(0, 0, 0));
	icon.paintIcon(jl, g2, 0, 0);
	File file = new File("Example4.png");
	try {
	    ImageIO.write(image, "png", file.getAbsoluteFile());
	} catch (IOException ex) {}
    }    
}