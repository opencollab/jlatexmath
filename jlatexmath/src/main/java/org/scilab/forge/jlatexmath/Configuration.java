/* Configuration.java
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

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.xml.sax.SAXException;

public final class Configuration {

    private static class Pair {
        final Object o;
        final String path;

        Pair(final Object o, final String path) {
            this.o = o;
            this.path = path;
        }
    }

    private final FontIDs ids = new FontIDs();
    private final ArrayList<FontInfo> fontinfo = new ArrayList<>();
    private final Map<String, SymbolAtom> symbolAtoms = new HashMap<>();
    private final ArrayList<Pair> metricFiles = new ArrayList<>();

    private static final String DEFAULT_TEX_FONT = "DefaultTeXFont.xml";
    private static final Configuration instance = new Configuration();

    private Configuration() {
        loadTeXFont(this, DEFAULT_TEX_FONT);
    }

    public static Configuration get() {
        return instance;
    }

    public Map<String, SymbolAtom> getSymbolAtoms() {
        return symbolAtoms;
    }

    public FontInfo[] getFontInfo() {
        return fontinfo.toArray(new FontInfo[fontinfo.size()]);
    }

    public FontInfo getFontInfo(final int id) {
        FontInfo fi = fontinfo.get(id);
        if (fi == null) {
            final Pair p = metricFiles.get(id);
            loadMetrics(p.o, p.path);
            metricFiles.set(id, null);
            fi = fontinfo.get(id);
        }
        return fi;
    }

    public Font getFont(final int id) {
        return fontinfo.get(id).getFont();
    }

    public int getFontId(final String id) {
        return ids.get(id);
    }

    public void loadTeXFont(final String path) {
        loadTeXFont(null, path);
    }

    public void loadTeXFont(final Object o, final String path) {
        final List<String> symbols = new ArrayList<>();
        final List<String> texsymbols = new ArrayList<>();
        final Map<Integer, String> metrics = new TreeMap<>();
        InputStream in = null;
        try {
            if (o == null) {
                in = new BufferedInputStream(new FileInputStream(new File(path)));
            } else {
                in = o.getClass().getResourceAsStream(path);
            }
            XMLTeXFont.get(ids, in, path, metrics, symbols, texsymbols);
            setMetrics(o, metrics);
            final Map<String, CharFont> cfs = loadSymbols(o, symbols);
            loadTeXSymbols(o, texsymbols, cfs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setMetrics(final Object o, final Map<Integer, String> metrics) {
        int maxId = -1;
        for (final int i : metrics.keySet()) {
            maxId = i > maxId ? i : maxId;
        }
        ++maxId;
        fontinfo.ensureCapacity(maxId);
        metricFiles.ensureCapacity(maxId);
        for (int i = fontinfo.size(); i < maxId; ++i) {
            fontinfo.add(null);
            metricFiles.add(null);
        }
        for (final Map.Entry<Integer, String> e : metrics.entrySet()) {
            final int id = e.getKey().intValue();
            final String path = e.getValue();
            metricFiles.set(id, new Pair(o, path));
        }
    }

    public void loadMetrics(final String path) {
        loadMetrics(null, path);
    }

    public void loadMetrics(final Object o, final String path) {
        InputStream in = null;
        try {
            if (o == null) {
                in = new BufferedInputStream(new FileInputStream(new File(path)));
            } else {
                in = o.getClass().getResourceAsStream(path);
            }
            final FontInfo fi = XMLFontMetrics.get(ids, o, in, path);
            fontinfo.set(fi.getId(), fi);
        } catch (SAXException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadSymbols(final Object o, final String path) {
        loadSymbols(o, new ArrayList<String>() {
            {
                add(path);
            }
        });
    }

    public void loadSymbols(final String path) {
        loadSymbols(null, new ArrayList<String>() {
            {
                add(path);
            }
        });
    }

    public void loadSymbols(final List<String> paths) {
        loadSymbols(null, paths);
    }

    public Map<String, CharFont> loadSymbols(final Object o, final List<String> paths) {
        final Map<String, CharFont> map = new HashMap<String, CharFont>();
        for (final String path : paths) {
            InputStream in = null;
            try {
                if (o == null) {
                    in = new BufferedInputStream(new FileInputStream(new File(path)));
                } else {
                    in = o.getClass().getResourceAsStream(path);
                }
                XMLSymbols.get(ids, map, in, path);
            } catch (SAXException e) {
                System.err.println(e);
            } catch (FileNotFoundException e) {
                System.err.println(e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }

    public void loadTeXSymbols(final Object o, final List<String> paths, Map<String, CharFont> map) {
        for (final String path : paths) {
            InputStream in = null;
            try {
                if (o == null) {
                    in = new BufferedInputStream(new FileInputStream(new File(path)));
                } else {
                    in = o.getClass().getResourceAsStream(path);
                }
                XMLTeXSymbols.get(symbolAtoms, map, in, path);
            } catch (SAXException e) {
                System.err.println(e);
            } catch (FileNotFoundException e) {
                System.err.println(e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
