/*
 * JLaTeXMath ( http://forge.scilab.org/jlatexmath ) - This file is part of JLaTeXMath
 *
 * Copyright (C) 2012 - Calixte DENIZET & Sylvestre LEDRU
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
 */

package org.scilab.forge.example.giws;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.imageio.ImageIO;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class LaTeXGenerator {

    public LaTeXGenerator() { }

    /**
     * Generate a PNG with the given path and LaTeX formula
     * @param formula the formula to compile
     * @param path the image path
     */
    public void generate(String formula, String path) throws IOException {
        TeXFormula tf = new TeXFormula(formula);
        TeXIcon ti = tf.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40);
        BufferedImage bimg = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	
	Graphics2D g2d = bimg.createGraphics();
	g2d.setColor(Color.white);
	g2d.fillRect(0,0,ti.getIconWidth(),ti.getIconHeight());
	JLabel jl = new JLabel();
	jl.setForeground(new Color(0, 0, 0));
	ti.paintIcon(jl, g2d, 0, 0);

        File out = new File(path);
        ImageIO.write(bimg, "png", out);
    }
}
