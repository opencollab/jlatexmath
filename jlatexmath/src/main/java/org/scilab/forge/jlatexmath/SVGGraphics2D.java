/* SVGGraphics2D.java
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
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class SVGGraphics2D extends Graphics2D {

	private final SVGContext ctx;
	private final FontRenderContext frc;
	private final double[] coords6 = new double[6];
	private final boolean glyphAsShape;
	private Font font;
	private Color color = Color.BLACK;
	private Color bgColor = Color.WHITE;
	private BasicStroke stroke = new BasicStroke();
	private AffineTransform T = new AffineTransform();

	/**
	 * Instantiates a new Graphics2D object. This constructor should never be
	 * called directly.
	 */
	public SVGGraphics2D(final int width, final int height, final boolean glyphAsShape, final String fontBaseURL) {
		super();
		this.glyphAsShape = glyphAsShape;
		frc = new FontRenderContext(new AffineTransform(), RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
				RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		ctx = new SVGContext(width, height, fontBaseURL, color, stroke, T);
	}

	/**
	 * Adds preferences for the rendering algorithms. The preferences are
	 * arbitrary and specified by Map objects. All specified by Map object
	 * preferences can be modified.
	 *
	 * @param hints
	 *            the rendering hints.
	 */
	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		// nothing
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setColor(Color color) {
		if (!this.color.equals(color)) {
			this.color = color;
			ctx.setColor(color);
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void clip(Shape s) {
	}

	@Override
	public void setClip(Shape shape) {

	}

	@Override
	public Shape getClip() {
		return null;
	}

	@Override
	public void setClip(int x, int y, int width, int height) {

	}

	@Override
	public void clipRect(int x, int y, int width, int height) {

	}

	@Override
	public Rectangle getClipBounds() {
		return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font f) {
		return null;
	}

	@Override
	public void setXORMode(Color c) {
		// nothing
	}

	@Override
	public void setPaintMode() {
		// nothing
	}

	@Override
	public Graphics create() {
		return null;
	}

	/**
	 * Draws the outline of the specified Shape.
	 *
	 * @param s
	 *            the Shape which outline is drawn.
	 */
	@Override
	public void draw(Shape s) {
		// TODO: handle special cases for ellipse and rectangle
		ctx.beginPath();
		for (PathIterator pi = s.getPathIterator(null); !pi.isDone(); pi.next()) {
			final int type = pi.currentSegment(coords6);
			switch (type) {
			case PathIterator.SEG_CLOSE:
				ctx.closePath();
				break;
			case PathIterator.SEG_CUBICTO:
				ctx.bezierCurveTo(coords6);
				break;
			case PathIterator.SEG_LINETO:
				ctx.lineTo(coords6);
				break;
			case PathIterator.SEG_MOVETO:
				ctx.moveTo(coords6);
				break;
			case PathIterator.SEG_QUADTO:
				ctx.quadraticCurveTo(coords6);
				break;
			}
		}
		ctx.endPath();
		ctx.stroke();
	}

	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		fill(g.getOutline(x, y));
	}

	@Override
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {

	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			Color color, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int w, int h, Color bgcolor, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int w, int h, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		if (img instanceof RenderedImage) {
			ctx.image((RenderedImage) img);
			return true;
		}
		return false;
	}

	/**
	 * Draws BufferedImage transformed from image space into user space
	 * according to the AffineTransform xform and notifies the ImageObserver.
	 *
	 * @param img
	 *            the BufferedImage to be rendered.
	 * @param xform
	 *            the affine transformation from the image to the user space.
	 * @param obs
	 *            the ImageObserver to be notified about the image conversion.
	 * @return true, if the image is successfully loaded and rendered, or it's
	 *         null, otherwise false.
	 */
	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
		return false;
	}

	/**
	 * Draws a RenderableImage which is transformed from image space into user
	 * according to the AffineTransform xform.
	 *
	 * @param img
	 *            the RenderableImage to be rendered.
	 * @param xform
	 *            the affine transformation from image to user space.
	 */
	@Override
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {

	}

	/**
	 * Draws a RenderedImage which is transformed from image space into user
	 * according to the AffineTransform xform.
	 *
	 * @param img
	 *            the RenderedImage to be rendered.
	 * @param xform
	 *            the affine transformation from image to user space.
	 */
	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {

	}

	/**
	 * Draws the string specified by the AttributedCharacterIterator. The first
	 * character's position is specified by the X, Y parameters.
	 *
	 * @param iterator
	 *            whose text is drawn.
	 * @param x
	 *            the X position where the first character is drawn.
	 * @param y
	 *            the Y position where the first character is drawn.
	 */
	@Override
	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
	}

	/**
	 * Draws the String whose the first character position is specified by the
	 * parameters X, Y.
	 *
	 * @param s
	 *            the String to be drawn.
	 * @param x
	 *            the X position of the first character.
	 * @param y
	 *            the Y position of the first character.
	 */
	@Override
	public void drawString(String s, float x, float y) {
		if (glyphAsShape) {
			drawGlyphVector(font.createGlyphVector(getFontRenderContext(), s), x, y);
		} else {
			ctx.string(s, font);
		}
	}

	/**
	 * Draws the String whose the first character coordinates are specified by
	 * the parameters X, Y.
	 *
	 * @param str
	 *            the String to be drawn.
	 * @param x
	 *            the X coordinate of the first character.
	 * @param y
	 *            the Y coordinate of the first character.
	 * @see java.awt.Graphics#drawString(String, int, int)
	 */
	@Override
	public void drawString(String str, int x, int y) {
		drawString(str, (float) x, (float) y);
	}

	/**
	 * Fills the interior of the specified Shape.
	 *
	 * @param s
	 *            the Shape to be filled.
	 */
	@Override
	public void fill(Shape s) {
		// TODO: handle special cases for ellipse and rectangle
		int rule = 0;
		ctx.beginPath();
		for (PathIterator pi = s.getPathIterator(null); !pi.isDone(); pi.next()) {
			final int type = pi.currentSegment(coords6);
			switch (type) {
			case PathIterator.SEG_CLOSE:
				ctx.closePath();
				rule = pi.getWindingRule();
				break;
			case PathIterator.SEG_CUBICTO:
				ctx.bezierCurveTo(coords6);
				break;
			case PathIterator.SEG_LINETO:
				ctx.lineTo(coords6);
				break;
			case PathIterator.SEG_MOVETO:
				ctx.moveTo(coords6);
				break;
			case PathIterator.SEG_QUADTO:
				ctx.quadraticCurveTo(coords6);
				break;
			}
		}
		ctx.endPath();
		ctx.fill(rule);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
		if (bgColor != null && !bgColor.equals(color)) {
			ctx.setColor(bgColor);
			fillRect(x, y, width, height);
			ctx.setColor(color);
		} else {
			fillRect(x, y, width, height);
		}
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		ctx.fillRect(x, y, width, height);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		ctx.beginPath();
		ctx.moveTo(x1, y1);
		ctx.lineTo(x2, y2);
		ctx.stroke();
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		// Nothing
	}

	/**
	 * Gets the background color.
	 *
	 * @return the current background color.
	 */
	@Override
	public Color getBackground() {
		return bgColor;
	}

	/**
	 * Gets the current composite of the Graphics2D.
	 *
	 * @return the current composite which specifies the compositing style.
	 */
	@Override
	public Composite getComposite() {
		return null;
	}

	/**
	 * Gets the device configuration.
	 *
	 * @return the device configuration.
	 */
	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return null;
	}

	/**
	 * Gets the rendering context of the Font.
	 *
	 * @return the FontRenderContext.
	 */
	@Override
	public FontRenderContext getFontRenderContext() {
		return frc;
	}

	/**
	 * Gets the current Paint of Graphics2D.
	 *
	 * @return the current Paint of Graphics2D.
	 */
	@Override
	public Paint getPaint() {
		return null;
	}

	/**
	 * Gets the value of single preference for specified key.
	 *
	 * @param key
	 *            the specified key of the rendering hint.
	 * @return the value of rendering hint for specified key.
	 */
	@Override
	public Object getRenderingHint(RenderingHints.Key key) {
		return null;
	}

	/**
	 * Gets the set of the rendering preferences as a collection of key/value
	 * pairs.
	 *
	 * @return the RenderingHints which contains the rendering preferences.
	 */
	@Override
	public RenderingHints getRenderingHints() {
		return null;
	}

	/**
	 * Gets current stroke of the Graphics2D.
	 *
	 * @return current stroke of the Graphics2D.
	 */
	@Override
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * Gets current affine transform of the Graphics2D.
	 *
	 * @return current AffineTransform of the Graphics2D.
	 */
	@Override
	public AffineTransform getTransform() {
		return (AffineTransform) T.clone();
	}

	/**
	 * Determines whether or not the specified Shape intersects the specified
	 * Rectangle. If the onStroke parameter is true, this method checks whether
	 * or not the specified Shape outline intersects the specified Rectangle,
	 * otherwise this method checks whether or not the specified Shape's
	 * interior intersects the specified Rectangle.
	 *
	 * @param rect
	 *            the specified Rectangle.
	 * @param s
	 *            the Shape to check for intersection.
	 * @param onStroke
	 *            the parameter determines whether or not this method checks for
	 *            intersection of the Shape outline or of the Shape interior
	 *            with the Rectangle.
	 * @return true, if there is a hit, false otherwise.
	 */
	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return true;
	}

	/**
	 * Performs a rotation transform relative to current Graphics2D Transform.
	 * The coordinate system is rotated by the specified angle in radians
	 * relative to current origin.
	 *
	 * @param theta
	 *            the angle of rotation in radians.
	 */
	@Override
	public void rotate(double theta) {
		T.rotate(theta);
	}

	/**
	 * Performs a translated rotation transform relative to current Graphics2D
	 * Transform. The coordinate system is rotated by the specified angle in
	 * radians relative to current origin and then moved to point (x, y). Is
	 * this right?
	 *
	 * @param theta
	 *            the angle of rotation in radians.
	 * @param x
	 *            the X coordinate.
	 * @param y
	 *            the Y coordinate.
	 */
	@Override
	public void rotate(double theta, double x, double y) {
		T.translate(x, y);
		T.rotate(theta);
		T.translate(-x, -y);
	}

	/**
	 * Performs a linear scale transform relative to current Graphics2D
	 * Transform. The coordinate system is rescaled vertically and horizontally
	 * by the specified parameters.
	 *
	 * @param sx
	 *            the scaling factor by which the X coordinate is multiplied.
	 * @param sy
	 *            the scaling factor by which the Y coordinate is multiplied.
	 */
	@Override
	public void scale(double sx, double sy) {
		T.scale(sx, sy);
	}

	/**
	 * Sets a new background color for clearing rectangular areas. The clearRect
	 * method uses the current background color.
	 *
	 * @param color
	 *            the new background color.
	 */
	@Override
	public void setBackground(Color color) {
		if (color != null) {
			bgColor = color;
		}
	}

	/**
	 * Sets the current composite for Graphics2D.
	 *
	 * @param comp
	 *            the Composite object.
	 */
	@Override
	public void setComposite(Composite comp) {

	}

	/**
	 * Sets the paint for Graphics2D.
	 *
	 * @param paint
	 *            the Paint object.
	 */
	@Override
	public void setPaint(Paint paint) {

	}

	/**
	 * Sets a key-value pair in the current RenderingHints map.
	 *
	 * @param key
	 *            the key of the rendering hint to set.
	 * @param value
	 *            the value to set for the rendering hint.
	 */
	@Override
	public void setRenderingHint(RenderingHints.Key key, Object value) {

	}

	/**
	 * Replaces the current rendering hints with the specified rendering
	 * preferences.
	 *
	 * @param hints
	 *            the new Map of rendering hints.
	 */
	@Override
	public void setRenderingHints(Map<?, ?> hints) {

	}

	/**
	 * Sets the stroke for the Graphics2D.
	 *
	 * @param s
	 *            the Stroke object.
	 */
	@Override
	public void setStroke(Stroke s) {
		if (stroke != s && s instanceof BasicStroke) {
			stroke = (BasicStroke) s;
			ctx.setStroke(stroke);
		}
	}

	/**
	 * Overwrite the current Transform of the Graphics2D. The specified
	 * Transform should be received from the getTransform() method and should be
	 * used only for restoring the original Graphics2D transform after calling
	 * draw or fill methods.
	 *
	 * @param Tx
	 *            the specified Transform.
	 */
	@Override
	public void setTransform(AffineTransform Tx) {
		T = Tx;
		ctx.setTransform(T);
	}

	/**
	 * Performs a shear transform relative to current Graphics2D Transform. The
	 * coordinate system is shifted by the specified multipliers relative to
	 * current position.
	 *
	 * @param shx
	 *            the multiplier by which the X coordinates shift position along
	 *            X axis as a function of Y coordinates.
	 * @param shy
	 *            the multiplier by which the Y coordinates shift position along
	 *            Y axis as a function of X coordinates.
	 */
	@Override
	public void shear(double shx, double shy) {
		transform(AffineTransform.getShearInstance(shx, shy));
	}

	/**
	 * Concatenates the AffineTransform object with current Transform of this
	 * Graphics2D. The transforms are applied in reverse order with the last
	 * specified transform applied first and the next transformation applied to
	 * the result of previous transformation. More precisely, if Cx is the
	 * current Graphics2D transform, the transform method's result with Tx as
	 * the parameter is the transformation Rx, where Rx(p) = Cx(Tx(p)), for p -
	 * a point in current coordinate system. Rx becomes the current Transform
	 * for this Graphics2D.
	 *
	 * @param Tx
	 *            the AffineTransform object to be concatenated with current
	 *            Transform.
	 */
	@Override
	public void transform(AffineTransform Tx) {
		T.concatenate(Tx);
	}

	/**
	 * Performs a translate transform relative to current Graphics2D Transform.
	 * The coordinate system is moved by the specified distance relative to
	 * current position.
	 *
	 * @param tx
	 *            the translation distance along the X axis.
	 * @param ty
	 *            the translation distance along the Y axis.
	 */
	@Override
	public void translate(double tx, double ty) {
		T.translate(tx, ty);
	}

	/**
	 * Moves the origin Graphics2D Transform to the point with x, y coordinates
	 * in current coordinate system. The new origin of coordinate system is
	 * moved to the (x, y) point accordingly. All rendering and transform
	 * operations are performed relative to this new origin.
	 *
	 * @param x
	 *            the X coordinate.
	 * @param y
	 *            the Y coordinate.
	 * @see java.awt.Graphics#translate(int, int)
	 */
	@Override
	public void translate(int x, int y) {
		translate((double) x, (double) y);
	}

	/**
	 * Fills a 3D rectangle with the current color. The rectangle is specified
	 * by its width, height, and top left corner coordinates.
	 *
	 * @param x
	 *            the X coordinate of the rectangle's top left corner.
	 * @param y
	 *            the Y coordinate of the rectangle's top left corner.
	 * @param width
	 *            the width of rectangle.
	 * @param height
	 *            the height of rectangle.
	 * @param raised
	 *            a boolean value that determines whether the rectangle is drawn
	 *            as raised or indented.
	 * @see java.awt.Graphics#fill3DRect(int, int, int, int, boolean)
	 */
	@Override
	public void fill3DRect(int x, int y, int width, int height, boolean raised) {

	}

	/**
	 * Draws the highlighted outline of a rectangle.
	 *
	 * @param x
	 *            the X coordinate of the rectangle's top left corner.
	 * @param y
	 *            the Y coordinate of the rectangle's top left corner.
	 * @param width
	 *            the width of rectangle.
	 * @param height
	 *            the height of rectangle.
	 * @param raised
	 *            a boolean value that determines whether the rectangle is drawn
	 *            as raised or indented.
	 * @see java.awt.Graphics#draw3DRect(int, int, int, int, boolean)
	 */
	@Override
	public void draw3DRect(int x, int y, int width, int height, boolean raised) {

	}

	public String getSVG() {
		return ctx.toString();
	}

	@Override
	public void dispose() {
	}
}
