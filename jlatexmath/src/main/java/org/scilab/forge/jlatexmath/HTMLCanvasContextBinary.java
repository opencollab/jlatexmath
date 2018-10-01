/* HTMLCanvasContextBinary.java
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
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Base64;

public class HTMLCanvasContextBinary {

	private final static float QUADRATICCURVETO = 0f;
	private final static float LINETO = 1f;
	private final static float MOVETO = 2f;
	private final static float BEZIERCURVETO = 3f;
	private final static float ARCTO = 4f;
	private final static float ARC = 5f;
	private final static float ELLIPSE = 6f;
	private final static float TRANSLATE = 7f;
	private final static float ROTATE = 8f;
	private final static float SCALE = 9f;
	private final static float TRANSFORM = 10f;
	private final static float SETTRANSFORM = 11f;
	private final static float BEGINPATH = 12f;
	private final static float CLOSEPATH = 13f;
	private final static float FILL = 14f;
	private final static float FILLNO = 15f;
	private final static float FILLRECT = 16f;
	private final static float CLEARRECT = 17f;
	private final static float STROKE = 18f;
	private final static float CLIP = 19f;
	private final static float LINEJOIN = 20f;
	private final static float LINECAP = 21f;
	private final static float LINEWIDTH = 22f;
	private final static float MITERLIMIT = 23f;
	private final static float STROKESTYLE = 24f;
	private final static float FILLSTYLE = 25f;
	private final static float IMAGE = 26f;

	private final static float RGB = 0f;
	private final static float RGBA = 1f;

	private final static float BEVEL = 0f;
	private final static float ROUND = 1f;
	private final static float MITER = 2f;

	private final static float BUTT = 0f;
	private final static float SQUARE = 2f;

	private final static float TRUE = 0f;
	private final static float FALSE = 1f;

	private final static float EVENODD = 0f;
	private final static float NONZERO = 1f;

	private final ArrayList<Float> fb;

	public HTMLCanvasContextBinary() {
		this.fb = new ArrayList<>();
	}

	@Override
	public String toString() {
		final int times = Float.SIZE / Byte.SIZE;
		final int N = fb.size();
		final byte[] bytes = new byte[N * times];
		final ByteBuffer bb = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
		for (int i = 0; i < N; ++i) {
			bb.putFloat(i * times, fb.get(i).floatValue());
		}
		return Base64.getEncoder().encodeToString(bytes);
	}

	public void setStroke(final Stroke stroke, final AffineTransform T) {
		if (!(stroke instanceof BasicStroke)) {
			return;
		}
		final BasicStroke bs = (BasicStroke) stroke;
		add(LINEJOIN);
		switch (bs.getLineJoin()) {
		case BasicStroke.JOIN_BEVEL:
			add(BEVEL);
			break;
		case BasicStroke.JOIN_MITER:
			add(MITER);
			break;
		case BasicStroke.JOIN_ROUND:
			add(ROUND);
			break;
		}
		add(LINECAP);
		switch (bs.getEndCap()) {
		case BasicStroke.CAP_BUTT:
			add(BUTT);
			break;
		case BasicStroke.CAP_ROUND:
			add(ROUND);
			break;
		case BasicStroke.CAP_SQUARE:
			add(SQUARE);
			break;
		}
		add(LINEWIDTH, bs.getLineWidth());
		add(MITERLIMIT, bs.getMiterLimit());
	}

	public void beginPath() {
		add(BEGINPATH);
	}

	public void closePath() {
		add(CLOSEPATH);
	}

	public void stroke() {
		add(STROKE);
	}

	public void strokeStyle(final Color c) {
		add(STROKESTYLE);
		makeColor(c);
	}

	public void clip() {
		add(CLIP);
	}

	public void fill() {
		add(FILLNO);
	}

	public void fillStyle(final Color c) {
		add(FILLSTYLE);
		makeColor(c);
	}

	public void fill(final int rule) {
		final float r = rule == PathIterator.WIND_EVEN_ODD ? EVENODD : NONZERO;
		add(FILL, r);
	}

	public void moveTo(float x, float y) {
		add(MOVETO, x, y);
	}

	public void moveTo(double[] arr) {
		moveTo((float) arr[0], (float) arr[1]);
	}

	public void lineTo(float x, float y) {
		add(LINETO, x, y);
	}

	public void lineTo(double[] arr) {
		lineTo((float) arr[0], (float) arr[1]);
	}

	public void clearRect(float a, float b, float c, float d) {
		add(CLEARRECT, a, b, c, d);
	}

	public void fillRect(float a, float b, float c, float d) {
		add(FILLRECT, a, b, c, d);
	}

	public void arc(float a, float b, float c, float d, float e, float f) {
		add(ARC, a, b, c, d, e, f);
	}

	public void arcTo(float a, float b, float c, float d, float e) {
		add(ARCTO, a, b, c, d, e);
	}

	public void bezierCurveTo(float a, float b, float c, float d, float e, float f) {
		add(BEZIERCURVETO, a, b, c, d, e, f);
	}

	public void bezierCurveTo(double[] arr) {
		bezierCurveTo((float) arr[0], (float) arr[1], (float) arr[2], (float) arr[3], (float) arr[4], (float) arr[5]);
	}

	public void quadraticCurveTo(float a, float b, float c, float d) {
		add(QUADRATICCURVETO, a, b, c, d);
	}

	public void quadraticCurveTo(double[] arr) {
		quadraticCurveTo((float) arr[0], (float) arr[1], (float) arr[2], (float) arr[3]);
	}

	public void ellipse(float a, float b, float c, float d, float e, float f, float g, boolean h) {
		add(ELLIPSE, a, b, c, d, e, f, g, h ? TRUE : FALSE);
	}

	public void rotate(float x) {
		add(ROTATE, x);
	}

	public void translate(float x, float y) {
		add(TRANSLATE, x, y);
	}

	public void scale(float x, float y) {
		add(SCALE, x, y);
	}

	public void setTransform(float a, float b, float c, float d, float e, float f) {
		add(SETTRANSFORM, a, b, c, d, e, f);
	}

	public void setTransform(final AffineTransform trans) {
		final double[] flat = new double[6];
		trans.getMatrix(flat);
		setTransform((float) flat[0], (float) flat[1], (float) flat[2], (float) flat[3], (float) flat[4],
				(float) flat[5]);
	}

	public void transform(float a, float b, float c, float d, float e, float f) {
		add(TRANSFORM, a, b, c, d, e, f);
	}

	public void transform(final AffineTransform trans) {
		final double[] flat = new double[6];
		trans.getMatrix(flat);
		transform((float) flat[0], (float) flat[1], (float) flat[2], (float) flat[3], (float) flat[4], (float) flat[5]);
	}

	public void image(int w, int h, float x, float y, int[] pixels) {
		final int N = w * h;
		final ByteBuffer bb = ByteBuffer.allocate(N * Integer.SIZE / Byte.SIZE);
		bb.asIntBuffer().put(pixels);
		final FloatBuffer flb = bb.asFloatBuffer();
		fb.ensureCapacity(fb.size() + 5 + N);
		add(IMAGE, (float) w, (float) h, x, y);
		for (int i = 0; i < N; ++i) {
			fb.add(flb.get(i));
		}
	}

	private void makeColor(final Color c) {
		final int alpha = c.getAlpha();
		if (alpha == 255) {
			add(RGB);
		} else {
			add(RGBA);
		}
		add((float) c.getRed(), (float) c.getGreen(), (float) c.getBlue());
		if (alpha != 255) {
			add(alpha / 255f);
		}
	}

	private void add(Float... floats) {
		for (final Float f : floats) {
			fb.add(f);
		}
	}
}
