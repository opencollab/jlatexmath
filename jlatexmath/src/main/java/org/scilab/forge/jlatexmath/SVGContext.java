/* SVGContext.java
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

public class SVGContext {

    private final double[] flat = new double[6];
    private final StringBuilder sb;
    private final Set<Font> fonts;
    private final int width;
    private final int height;
    private final String fontBaseURL;
    private Color color;
    private BasicStroke stroke;
    private AffineTransform T;
    private boolean hasG;
    private boolean firstG;

    public SVGContext(final int width, final int height, final String fontBaseURL, final Color c, final BasicStroke stroke, final AffineTransform T) {
        this.sb = new StringBuilder();
        this.width = width;
        this.height = height;
        this.fonts = new HashSet<Font>();
        this.fontBaseURL = fontBaseURL;
        this.color = c;
        this.stroke = stroke;
        this.T = T;
        this.hasG = true;
        this.firstG = true;
    }

    public String toString() {
        final StringBuilder s = new StringBuilder(sb.capacity() + 1024);
        s.append("<svg width=\"").append(width).append("\" height=\"").append(height).append("\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n");
        s.append("<style type=\"text/css\">\n");
        for (final Font f : fonts) {
            final String family = f.getFamily();
            s.append("@font-face {\n");
            s.append("font-family: \'").append(family).append("\';\n");
            s.append("src: url(\'").append(fontBaseURL).append(f.getName()).append(".ttf\') format(\'truetype\');\n");
            s.append("font-style: normal;\n");
            s.append("}\n");
            s.append(".").append(family).append(" {\n");
            s.append("font-family: \'").append(family).append("\';\n");
            s.append("font-size: ").append(f.getSize()).append("px;\n");
            s.append("}\n");
        }
        s.append("</style>\n");
        s.append(sb);
        s.append("</g>\n</svg>\n");
        return s.toString();
    }

    public void setStroke(final BasicStroke stroke) {
        this.stroke = stroke;
        hasG = true;
    }

    public void setColor(final Color c) {
        this.color = c;
        hasG = true;
    }

    public void setTransform(final AffineTransform T) {
        this.T = T;
    }

    public void beginPath() {
        makeG();
        sb.append("<path");
        makeTransform();
        sb.append(" d=\"");
    }

    public void closePath() {
        sb.append(" Z ");
    }

    public void endPath() {
        sb.append("\"");
    }

    public void stroke() {
        sb.append(" fill=\"transparent\"/>\n");
    }

    public void fill() {
        sb.append(" stroke=\"transparent\"/>\n");
    }

    public void fill(final int rule) {
        final String r = rule == PathIterator.WIND_EVEN_ODD ? "evenodd" : "nonzero";
        sb.append(" stroke=\"transparent\" fill-rule=\"").append(r).append("\"/>\n");
    }

    public void moveTo(float x, float y) {
        sb.append(" M ");
        coords(x, y);
    }

    public void moveTo(double[] arr) {
        moveTo((float)arr[0], (float)arr[1]);
    }

    public void lineTo(float x, float y) {
        sb.append(" L ");
        coords(x, y);
    }

    public void lineTo(double[] arr) {
        lineTo((float)arr[0], (float)arr[1]);
    }

    public void fillRect(int a, int b, int c, int d) {
        makeG();
        sb.append("<rect x=\"").append(a).append("\" y=\"").append(b).append("\" width=\"").append(c).append("\" height=\"").append(d).append("\" stroke=\"transparent\"/>\n");
    }

    public void bezierCurveTo(float a, float b, float c, float d, float e, float f) {
        sb.append(" C ");
        coords(a, b, c, d, e, f);
    }

    public void bezierCurveTo(double[] arr) {
        bezierCurveTo((float)arr[0], (float)arr[1], (float)arr[2], (float)arr[3], (float)arr[4], (float)arr[5]);
    }

    public void quadraticCurveTo(float a, float b, float c, float d) {
        sb.append(" Q ");
        coords(a, b, c, d);
    }

    public void quadraticCurveTo(double[] arr) {
        quadraticCurveTo((float)arr[0], (float)arr[1], (float)arr[2], (float)arr[3]);
    }

    public void image(RenderedImage img) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage)img, "png", baos);
            final String b64 = Base64.getEncoder().encodeToString(baos.toByteArray());
            makeG();
            sb.append("<image width=\"").append(img.getWidth());
            sb.append("\" height=\"").append(img.getHeight());
            sb.append("\"");
            makeTransform();
            sb.append(" xlink:href=\"data:image/png;base64,").append(b64).append("\"/>\n");
        } catch (IOException e) {

        }
    }

    public void string(String s, Font f) {
        makeG();
        fonts.add(f);
        sb.append("<text class=\"").append(f.getFamily());
        sb.append("\"");
        makeTransform();
        sb.append(">");
        escape(s);
        sb.append("</text>\n");
    }

    private void coords(Float... nums) {
        sb.append(nums[0].floatValue());
        for (int i = 1; i < nums.length; ++i) {
            sb.append(" ").append(nums[i].floatValue());
        }
    }

    private String makeColor() {
        final int alpha = color.getAlpha();
        String c = alpha == 255 ? "rgb(" : "rgba(";
        c += color.getRed() + "," + color.getGreen() + "," + color.getBlue();
        if (alpha != 255) {
            c += "," + (alpha / 255.);
        }

        return c + ")";
    }

    private void makeTransform() {
        T.getMatrix(flat);
        sb.append(" transform=\"matrix(").append(flat[0]);
        for (int i = 1; i < 6; ++i) {
            sb.append(",").append((float)flat[i]);
        }
        sb.append(")\"");
    }

    private void makeStroke() {
        sb.append("stroke-width=\"").append(stroke.getLineWidth())
        .append("\" stroke-miterlimit=\"").append(stroke.getMiterLimit())
        .append("\" stroke-linejoin=\"");

        switch (stroke.getLineJoin()) {
        case BasicStroke.JOIN_BEVEL:
            sb.append("bevel");
            break;
        case BasicStroke.JOIN_MITER:
            sb.append("miter");
            break;
        case BasicStroke.JOIN_ROUND:
            sb.append("round");
            break;
        }
        sb.append("\" stroke-linecap=\"");
        switch (stroke.getEndCap()) {
        case BasicStroke.CAP_BUTT:
            sb.append("butt");
            break;
        case BasicStroke.CAP_ROUND:
            sb.append("round");
            break;
        case BasicStroke.CAP_SQUARE:
            sb.append("square");
            break;
        }

        float[] dashes = stroke.getDashArray();
        if (dashes != null && dashes.length != 0) {
            sb.append("\" stroke-dasharray=\"");
            for (int i = 0; i < dashes.length - 1; ++i) {
                sb.append(dashes[i]).append(",");
            }
            sb.append(dashes[dashes.length - 1]);
        }
        sb.append("\"");
    }

    private void makeG() {
        if (hasG) {
            if (firstG) {
                firstG = false;
            } else {
                sb.append("</g>\n");
            }
            final String c = makeColor();
            sb.append("<g stroke=\"").append(c)
            .append("\" fill=\"").append(c)
            .append("\" ");
            makeStroke();
            sb.append(">\n");
            hasG = false;
        }
    }

    private void escape(final String s) {
        final int N = s.length();
        for (int i = 0; i < N; ++i) {
            final char c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '&' :
                sb.append("&amp;");
                break;
            case '\'':
                sb.append("&apos;");
                break;
            case '\"':
                sb.append("&quot;");
                break;
            default:
                sb.append(c);
            }
        }
    }
}
