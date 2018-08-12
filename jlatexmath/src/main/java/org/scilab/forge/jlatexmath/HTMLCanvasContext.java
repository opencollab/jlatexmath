/* HTMLCanvasContext.java
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
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;

public class HTMLCanvasContext {

    final StringBuilder sb;
    final String ctx;

    public HTMLCanvasContext(final String ctx) {
        this.sb = new StringBuilder();
        this.ctx = ctx;
    }

    public String toString() {
        final StringBuilder s = new StringBuilder(sb.capacity() + 50);
        s.append("(async function() {const t0=performance.now();\n");
        s.append(sb);
        s.append("\nconst t1=performance.now();console.log(\"Call to doSomething took \" + (t1 - t0) + \" milliseconds.\")})();\n");
        return s.toString();
    }

    public void setStroke(final Stroke stroke, final AffineTransform T) {
        if (!(stroke instanceof BasicStroke)) {
            return;
        }
        final BasicStroke bs = (BasicStroke)stroke;
        sb.append(ctx).append(".lineJoin=");
        switch (bs.getLineJoin()) {
        case BasicStroke.JOIN_BEVEL:
            sb.append("\"bevel\";");
            break;
        case BasicStroke.JOIN_MITER:
            sb.append("\"miter\";");
            break;
        case BasicStroke.JOIN_ROUND:
            sb.append("\"round\";");
            break;
        }
        sb.append(ctx).append(".lineCap=");
        switch (bs.getEndCap()) {
        case BasicStroke.CAP_BUTT:
            sb.append("\"butt\";");
            break;
        case BasicStroke.CAP_ROUND:
            sb.append("\"round\";");
            break;
        case BasicStroke.CAP_SQUARE:
            sb.append("\"square\";");
            break;
        }
        sb.append(ctx).append(".lineWidth=").append(bs.getLineWidth()).append(";");
        sb.append(ctx).append(".miterLimit=").append(bs.getMiterLimit()).append(";\n");
    }

    public void beginPath() {
        sb.append(ctx).append(".beginPath();");
    }

    public void closePath() {
        sb.append(ctx).append(".closePath();");
    }

    public void stroke() {
        sb.append(ctx).append(".stroke();");
    }

    public void strokeStyle(final Color c) {
        sb.append(ctx).append(".strokeStyle=");
        makeColor(c);
    }

    public void clip() {
        sb.append(ctx).append(".clip();");
    }

    public void fill() {
        sb.append(ctx).append(".fill();");
    }

    public void fillStyle(final Color c) {
        sb.append(ctx).append(".fillStyle=");
        makeColor(c);
    }

    public void fill(final int rule) {
        final String r = rule == PathIterator.WIND_EVEN_ODD ? "evenodd" : "nonzero";
        sb.append(ctx).append(".fill(\"").append(r).append("\");");
    }

    public void moveTo(float x, float y) {
        sb.append(ctx).append(".moveTo");
        coords(x, y);
    }

    public void moveTo(double[] arr) {
        moveTo((float)arr[0], (float)arr[1]);
    }

    public void lineTo(float x, float y) {
        sb.append(ctx).append(".lineTo");
        coords(x, y);
    }

    public void lineTo(double[] arr) {
        lineTo((float)arr[0], (float)arr[1]);
    }

    public void clearRect(float a, float b, float c, float d) {
        sb.append(ctx).append(".clearRect");
        coords(a, b, c, d);
    }

    public void fillRect(float a, float b, float c, float d) {
        sb.append(ctx).append(".fillRect");
        coords(a, b, c, d);
    }

    public void arc(float a, float b, float c, float d, float e, float f) {
        sb.append(ctx).append(".arc");
        coords(a, b, c, d, e, f);
    }

    public void arcTo(float a, float b, float c, float d, float e) {
        sb.append(ctx).append(".arcTo");
        coords(a, b, c, d, e);
    }

    public void bezierCurveTo(float a, float b, float c, float d, float e, float f) {
        sb.append(ctx).append(".bezierCurveTo");
        coords(a, b, c, d, e, f);
    }

    public void bezierCurveTo(double[] arr) {
        bezierCurveTo((float)arr[0], (float)arr[1], (float)arr[2], (float)arr[3], (float)arr[4], (float)arr[5]);
    }

    public void quadraticCurveTo(float a, float b, float c, float d) {
        sb.append(ctx).append(".quadraticCurveTo");
        coords(a, b, c, d);
    }

    public void quadraticCurveTo(double[] arr) {
        quadraticCurveTo((float)arr[0], (float)arr[1], (float)arr[2], (float)arr[3]);
    }

    public void ellipse(float a, float b, float c, float d, float e, float f, float g, boolean h) {
        sb.append(ctx).append(".ellipse(").append(a);
        sb.append(",").append(b);
        sb.append(",").append(c);
        sb.append(",").append(d);
        sb.append(",").append(e);
        sb.append(",").append(f);
        sb.append(",").append(g);
        sb.append(",").append(h ? "true" : "false");
        sb.append(");\n");
    }

    public void rotate(float x) {
        sb.append(ctx).append(".rotate");
        coords(x);
    }

    public void translate(float x, float y) {
        sb.append(ctx).append(".translate");
        coords(x, y);
    }

    public void scale(float x, float y) {
        sb.append(ctx).append(".scale");
        coords(x, y);
    }

    public void setTransform(float a, float b, float c, float d, float e, float f) {
        sb.append(ctx).append(".setTransform");
        coords(a, b, c, d, e, f);
    }

    public void setTransform(final AffineTransform trans) {
        final double[] flat = new double[6];
        trans.getMatrix(flat);
        setTransform((float)flat[0], (float)flat[1], (float)flat[2], (float)flat[3], (float)flat[4], (float)flat[5]);
    }

    public void transform(float a, float b, float c, float d, float e, float f) {
        sb.append(ctx).append(".transform");
        coords(a, b, c, d, e, f);
    }

    public void transform(final AffineTransform trans) {
        final double[] flat = new double[6];
        trans.getMatrix(flat);
        transform((float)flat[0], (float)flat[1], (float)flat[2], (float)flat[3], (float)flat[4], (float)flat[5]);
    }

    public void image(int w, int h, float x, float y, int[] pixels) {
        final int N = w * h;
        final int times = Integer.SIZE / Byte.SIZE;
        final byte[] bytes = new byte[N * times];
        final ByteBuffer bb = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        for(int i = 0; i < N; ++i) {
            bb.putInt(i * times, pixels[i]);
        }
        final String b64 = Base64.getEncoder().encodeToString(bytes);
        sb.append("await displayImage(").append(ctx).append(",").append(w).append(",").append(h).append(",").append(x).append(",").append(y).append(",\"").append(b64).append("\")\n;");
    }

    private void coords(Float... nums) {
        sb.append("(").append(nums[0].floatValue());
        for (int i = 1; i < nums.length; ++i) {
            sb.append(",").append(nums[i].floatValue());
        }
        sb.append(");\n");
    }

    private void makeColor(final Color c) {
        final int alpha = c.getAlpha();
        if (alpha == 255) {
            sb.append("\"rgb(");
        } else {
            sb.append("\"rgba(");
        }
        sb.append(c.getRed()).append(",");
        sb.append(c.getGreen()).append(",");
        sb.append(c.getBlue());
        if (alpha != 255) {
            sb.append(",").append((float)alpha / 255f);
        }
        sb.append(")\";");
    }
}
