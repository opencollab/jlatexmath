/* Test.java
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

import java.awt.Insets;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

import org.scilab.forge.jlatexmath.packages.FooPackage;

public class Test {

    private final String baseName;
    private String id;
    private String code;
    private String error;

    public Test(final String baseName) {
        this.baseName = baseName;
        if (baseName.equals("foopackage")) {
            JLMPackage.addPackage(FooPackage.getInstance());
        }
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setError(final String error) {
        if (error == null) {
            this.error = error;
        }
    }

    public void exec() {
        JavaFontRenderingBox.disable();
        TeXFormula.setDPITarget(96);
        final TeXFormula tf = new TeXFormula(code);
        final TeXIcon icon = tf.new TeXIconBuilder()
                             .setStyle(TeXConstants.STYLE_DISPLAY)
                             .setSize(10)
                             .build();

        icon.setInsets(new Insets(5, 5, 5, 5));
        final SVGGraphics2D g2 = new SVGGraphics2D(icon.getIconWidth(), icon.getIconHeight(), false, "fonts/");
        icon.paintIcon(null, g2, 0, 0);
        final String svg = g2.getSVG();

        try {
            final String prev = get(code, svg);
            if (prev != null) {
                if (!svg.equals(prev)) {
                    final Path path = getFailedPath();
                    Files.write(path, svg.getBytes());
                    String err = "SVG outputs for test " + baseName + "_" + id + " are differents.\nThe failing svg can be found at " + path;
                    err += "\n" + svg + "\n";
                    err += "========================================================================";
                    assertTrue(err, false);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String get(final String code, final String svg) throws IOException {
        final Path path = getPath();
        if (Files.exists(path)) {
            final byte[] data = Files.readAllBytes(path);
            final String prev = new String(data, StandardCharsets.UTF_8);
            if (!compareLaTeX(code, prev)) {
                write(code, svg);
                return null;
            }
            final int start = prev.indexOf("-->\n");
            return prev.substring(start + 4);
        }
        write(code, svg);
        return null;
    }

    private void write(final String code, final String svg) throws IOException {
        final Path path = getPath();
        final String output = "<!--\n" + code.trim().replace("--", "&#45;&#45;") + "\n-->\n" + svg;
        Files.write(path, output.getBytes());
    }

    private boolean compareLaTeX(final String code, final String svg) {
        final String c1 = code.trim();
        final int start = svg.indexOf("<!--");
        final int end = svg.indexOf("-->");
        final String c2 = svg.substring(start + 4, end).trim().replace("&#45;&#45;", "--");

        return c1.equals(c2);
    }

    private Path getPath() {
        return Paths.get("src/test/resources/expected/" + baseName + "_" + id + ".svg");
    }

    private Path getFailedPath() {
        return Paths.get("src/test/resources/expected/" + baseName + "_" + id + ".failed.svg");
    }

    public String toString() {
        return "name: " + baseName + "\nid: " + id + "\ncode: " + code.trim() + "\n";
    }
}
