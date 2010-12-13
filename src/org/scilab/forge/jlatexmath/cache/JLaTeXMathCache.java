/* JLaTeXMathCache.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/p/jlatexmath
 *
 * Copyright (C) 2010 DENIZET Calixte
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

package org.scilab.forge.jlatexmath.cache;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 * Class to cache generated image from formulas
 * @author Calixte DENIZET
 */
public final class JLaTeXMathCache {

    private static final AffineTransform identity = new AffineTransform();
    private static ConcurrentMap<CachedTeXFormula, SoftReference<Image>> cache = new ConcurrentHashMap(128);

    private JLaTeXMathCache() { }

    /**
     * @param f a formula
     * @param style a style like TeXConstants.STYLE_DISPLAY
     * @param size the size of font
     * @param inset the inset to add on the top, bottom, left and right
     * @return an array of length 3 containing width, height and depth
     */
    public static int[] getCachedTeXFormulaDimensions(String f, int style, int size, int inset) throws ParseException  {
        return getCachedTeXFormulaDimensions(new CachedTeXFormula(f, style, size, inset));
    }

    /**
     * @param o an Object to identify the image in the cache
     * @return an array of length 3 containing width, height and depth
     */
    public static int[] getCachedTeXFormulaDimensions(Object o) throws ParseException  {
        if (o == null || !(o instanceof CachedTeXFormula)) {
            return new int[]{0, 0, 0};
        }
        CachedTeXFormula cached = (CachedTeXFormula) o;
        SoftReference<Image> img = cache.get(cached);
        if (img == null || img.get() == null) {
            img = makeImage(cached);
        }

        return new int[]{cached.width, cached.height, cached.depth};
    }

    /**
     * Get a cached formula
     * @param f a formula
     * @param style a style like TeXConstants.STYLE_DISPLAY
     * @param size the size of font
     * @param inset the inset to add on the top, bottom, left and right
     * @return the key in the map
     */
    public static Object getCachedTeXFormula(String f, int style, int size, int inset) throws ParseException  {
        CachedTeXFormula cached = new CachedTeXFormula(f, style, size, inset);
        SoftReference<Image> img = cache.get(cached);
        if (img == null || img.get() == null) {
            img = makeImage(cached);
        }

        return cached;
    }

    /**
     * Clear the cache
     */
    public static void clearCache() {
        cache.clear();
    }

    /**
     * Remove a formula from the cache
     * @param f a formula
     * @param style a style like TeXConstants.STYLE_DISPLAY
     * @param size the size of font
     * @param inset the inset to add on the top, bottom, left and right
     */
    public static void removeCachedTeXFormula(String f, int style, int size, int inset) throws ParseException  {
        cache.remove(new CachedTeXFormula(f, style, size, inset));
    }

    /**
     * Remove a formula from the cache. Take care, remove the Object o, invalidate it !
     * @param o an Object to identify the image in the cache
     */
    public static void removeCachedTeXFormula(Object o) throws ParseException  {
        if (o == null || o instanceof CachedTeXFormula) {
            cache.remove((CachedTeXFormula) o);
        }
    }

    /**
     * Paint a cached formula
     * @param f a formula
     * @param style a style like TeXConstants.STYLE_DISPLAY
     * @param size the size of font
     * @param inset the inset to add on the top, bottom, left and right
     * @return the key in the map
     */
    public static Object paintCachedTeXFormula(String f, int style, int size, int inset, Graphics2D g) throws ParseException  {
        return paintCachedTeXFormula(new CachedTeXFormula(f, style, size, inset), g);
    }

    /**
     * Paint a cached formula
     * @param o an Object to identify the image in the cache
     * @param g the graphics where to paint the image
     * @return the key in the map
     */
    public static Object paintCachedTeXFormula(Object o, Graphics2D g) throws ParseException  {
        if (o == null || !(o instanceof CachedTeXFormula)) {
            return null;
        }
        CachedTeXFormula cached = (CachedTeXFormula) o;
        SoftReference<Image> img = cache.get(cached);
        if (img == null || img.get() == null) {
            img = makeImage(cached);
        }
        g.drawImage(img.get(), identity, null);

        return cached;
    }

    /**
     * Get a cached formula
     * @param f a formula
     * @param style a style like TeXConstants.STYLE_DISPLAY
     * @param size the size of font
     * @param inset the inset to add on the top, bottom, left and right
     * @return the cached image
     */
    public static Image getCachedTeXFormulaImage(String f, int style, int size, int inset) throws ParseException {
        return getCachedTeXFormulaImage(new CachedTeXFormula(f, style, size, inset));
    }

    /**
     * Get a cached formula
     * @param o an Object to identify the image in the cache
     * @return the cached image
     */
    public static Image getCachedTeXFormulaImage(Object o) throws ParseException {
        if (o == null || !(o instanceof CachedTeXFormula)) {
            return null;
        }
        CachedTeXFormula cached = (CachedTeXFormula) o;
        SoftReference<Image> img = cache.get(cached);
        if (img == null || img.get() == null) {
            img = makeImage(cached);
        }

        return img.get();
    }

    private static SoftReference<Image> makeImage(CachedTeXFormula cached) throws ParseException {
        TeXFormula formula = new TeXFormula(cached.f);
        TeXIcon icon = formula.createTeXIcon(cached.style, cached.size);
        icon.setInsets(new Insets(cached.inset, cached.inset, cached.inset, cached.inset));
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        icon.paintIcon(null, g2, 0, 0);
        g2.dispose();
        cached.setDimensions(icon.getIconWidth(), icon.getIconHeight(), icon.getIconDepth());
        SoftReference<Image> img = new SoftReference(image);
        cache.put(cached, img);

        return img;
    }

    private static class CachedTeXFormula {

        String f;
        int style;
        int size;
        int inset;
        int width = -1;
        int height;
        int depth;

        CachedTeXFormula(String f, int style, int size, int inset) {
            this.f = f;
            this.style = style;
            this.size = size;
            this.inset = inset;
        }

        void setDimensions(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }

        /**
         * {@inheritDoc}
         */
        public boolean equals(Object o) {
            if (o != null && o instanceof CachedTeXFormula) {
                CachedTeXFormula c = (CachedTeXFormula) o;
                boolean b = (c.f.equals(f) && c.style == style && c.size == size && c.inset == inset);
                if (b) {
                    if (c.width == -1) {
                        c.width = width;
                        c.height = height;
                        c.depth = depth;
                    } else if (width == -1) {
                        width = c.width;
                        height = c.height;
                        depth = c.depth;
                    }
                }

                return b;
            }

            return false;
        }

        /**
         * {@inheritDoc}
         */
        public int hashCode() {
            return f.hashCode();
        }
    }
}
