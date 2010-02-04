/* MatrixAtom.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
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

package org.scilab.forge.jlatexmath;

import java.util.BitSet;
import java.util.Map;
import java.util.LinkedList;

/**
 * A box representing a matrix.
 */
public class MatrixAtom extends Atom {
    
    public static SpaceAtom hsep = new SpaceAtom(TeXConstants.UNIT_EM, 2.0f, 0.0f, 0.0f);
    public static SpaceAtom vsep_in = new SpaceAtom(TeXConstants.UNIT_EX, 0.0f, 1f, 0.0f);
    public static SpaceAtom vsep_ext_top = new SpaceAtom(TeXConstants.UNIT_EX, 0.0f, 0.0f, 0.0f);
    public static SpaceAtom vsep_ext_bot = new SpaceAtom(TeXConstants.UNIT_EX, 0.0f, 0.0f, 0.0f);

    public static final int MATRIX = 0;
    public static final int ALIGN = 1;
    public static final int ALIGNAT = 2;
    public static final int FLALIGN = 3;
    public static final int SMALLMATRIX = 4;
    public static final int ALIGNED = 5;
    public static final int ALIGNEDAT = 6;

    private ArrayOfAtoms matrix;
    private int[] position;
    private Atom[] insertAtom;
    private boolean isAlign = false;
    private boolean isAlignat = false;
    private boolean isFl = false;
    private int type;
    
    private static SpaceAtom align = new SpaceAtom(TeXConstants.MEDMUSKIP);

    /**
     * Creates an empty matrix
     *
     */
    public MatrixAtom(ArrayOfAtoms array, String options) {
	this.matrix = array;
	this.type = MATRIX;
	parsePositions(new StringBuffer(options));
	//getPositions(options);
    }

    public MatrixAtom(ArrayOfAtoms array, int type) {
	this.matrix = array;
	this.type = type;
	
	if (type != MATRIX) {
	    position = new int[matrix.col];
	    for (int i = 0; i < matrix.col; i += 2) {
		position[i] = TeXConstants.ALIGN_RIGHT;
		if (i + 1 < matrix.col) {
		    position[i + 1] = TeXConstants.ALIGN_LEFT;
		}
	    }
	}
    }

    private void parsePositions(StringBuffer opt) {
	int len = opt.length();
	int pos = 0;
	int i = 0;
	char ch;
	position = new int[Math.max(len, matrix.col)];
	insertAtom = new Atom[position.length];
	while (pos < len) {
	    ch = opt.charAt(pos);
	    if (ch == 'l') 
		position[i++] = TeXConstants.ALIGN_LEFT;
	    else if (ch == 'r') 
		position[i++] = TeXConstants.ALIGN_RIGHT;
	    else if (ch == 'c')
		position[i++] = TeXConstants.ALIGN_CENTER;
	    else if (ch == '@') {
		pos++;
		TeXFormula tf = new TeXFormula();
		TeXParser tp = new TeXParser(opt.substring(pos), tf, false);
		Atom at = tp.getArgument();
		matrix.col++;
		for (int j = 0; j < matrix.row; j++) 
		    matrix.array.get(j).add(i, at);

		position[i++] = TeXConstants.ALIGN_CENTER;
		pos += tp.getPos();
		pos--;
	    } else if (ch == '*') {
		pos++;
		TeXFormula tf = new TeXFormula();
		TeXParser tp = new TeXParser(opt.substring(pos), tf, false);
		String[] args = tp.getOptsArgs(2, 0);
		pos += tp.getPos();
		int nrep =  Integer.parseInt(args[1]);
		String str = "";
		for (int j = 0; j < nrep; j++)
		    str += args[2];
		opt.insert(pos, str);
		len = opt.length();
		pos--;
	    } else 
		position[i++] = TeXConstants.ALIGN_CENTER;
	    pos++;
	}
	for (int j = len; j < matrix.col; j++) {
	    position[j] = TeXConstants.ALIGN_CENTER;
	}
    }
		    

    private void getPositions(String pos) {
	int len = pos.length();
	position = new int[Math.max(len, matrix.col)];
	for (int i = 0; i < len; i++) {
	    char c = pos.charAt(i);
	    switch (c) {
	    case 'l' :
		position[i] = TeXConstants.ALIGN_LEFT;
		break;
	    case 'r':
		position[i] = TeXConstants.ALIGN_RIGHT;
		break;
	    default :
		position[i] = TeXConstants.ALIGN_CENTER;
		break;
	    }
	}
	for (int i = len; i < matrix.col; i++) {
	    position[i] = TeXConstants.ALIGN_CENTER;
	}
    }

    public Box[] getColumnSep(TeXEnvironment env, float width) {
	int row = matrix.row;
	int col = matrix.col;
	Box[] arr = new Box[col + 1];
	Box Align, AlignSep;
	float h, w = env.getTextwidth();

	if (type == ALIGNED || type == ALIGNEDAT) {
	    w = Float.POSITIVE_INFINITY;
	}

	switch (type) {
	case MATRIX :
	case SMALLMATRIX :
	    //Simple matrix : 0 elem hsep_col elem hsep_col ... hsep_col elem 0
	    arr[0] = new StrutBox(0, 0, 0, 0);
	    arr[col] = arr[0];
	    Box Hsep = hsep.createBox(env);
	    for (int i = 1; i < col; i++) {
		arr[i] = Hsep;
	    }
	    
	    return arr;
	case ALIGN :
	    //Align env. : hsep=(textwidth-matWidth)/(2n+1) and hsep eq_lft \medskip el_rgt hsep ... hsep elem hsep
	    Align = align.createBox(env);
	    if (w != Float.POSITIVE_INFINITY) {
		h = Math.max((w - width - (col / 2) * Align.getWidth()) / (float)Math.floor((col + 3)/ 2), 0);
		AlignSep = new StrutBox(h, 0.0f, 0.0f, 0.0f);
	    } else {
		AlignSep = hsep.createBox(env);
	    }
	    
	    arr[col] = AlignSep;
	    for (int i = 0; i < col; i++) {
		if (i % 2 == 0) {
		    arr[i] = AlignSep;
		} else {
		    arr[i] = Align;
		}
	    }
	    
	    break;
	case ALIGNAT :
	    //Alignat env. : hsep=(textwidth-matWidth)/2 and hsep elem ... elem hsep
	    if (w != Float.POSITIVE_INFINITY) {
		h = Math.max((w - width) / 2, 0);
	    } else {
		h = 0;
	    }
	    
	    Align = align.createBox(env);
	    Box empty = new StrutBox(0, 0, 0, 0);
	    arr[0] = new StrutBox(h, 0.0f, 0.0f, 0.0f);
	    arr[col] = arr[0];
	    for (int i = 1; i < col; i++) {
		if (i % 2 == 0) {
		    arr[i] = empty;
		} else {
		    arr[i] = Align;
		}
	    }

	    break;
	case FLALIGN : 
	    //flalign env. : hsep=(textwidth-matWidth)/(2n+1) and hsep eq_lft \medskip el_rgt hsep ... hsep elem hsep
	    Align = align.createBox(env);
	    if (w != Float.POSITIVE_INFINITY) {
		h = Math.max((w - width - (col / 2) * Align.getWidth()) / (float)Math.floor((col - 1)/ 2), 0);
		AlignSep = new StrutBox(h, 0.0f, 0.0f, 0.0f);
	    } else {
		AlignSep = hsep.createBox(env);
	    }
	    
	    arr[0] = new StrutBox(0, 0, 0, 0);
	    arr[col] = arr[0];
	    for (int i = 1; i < col; i++) {
		if (i % 2 == 0) {
		    arr[i] = AlignSep;
		} else {
		    arr[i] = Align;
		}
	    }
	    
	    break;
	}

	if (w == Float.POSITIVE_INFINITY) {
	    arr[0] = new StrutBox(0, 0, 0, 0);
	    arr[col] = arr[0];
	}

	return arr;

    }
    
    public Box createBox(TeXEnvironment env) {
	int row = matrix.row;
	int col = matrix.col;
	Box[][] boxarr = new Box[row][col];
	float[] lineDepth = new float[row];
	float[] lineHeight = new float[row];
	float[] rowWidth = new float[col];
	float matW = 0;
	
	if (type == SMALLMATRIX) {
	    env = env.copy();
	    env.setStyle(TeXConstants.STYLE_SCRIPT);
	}

	for (int i = 0; i < row; i++) {
	    lineDepth[i] = 0;
	    lineHeight[i] = 0;
	    for (int j = 0; j < col; j++) {
		Atom at = null;
		try {
		    at = matrix.array.get(i).get(j);
		} catch (Exception e) {
		    //The previous atom was an intertext atom
		    //position[j - 1] = -1;
		    boxarr[i][j - 1].type = TeXConstants.TYPE_INTERTEXT;
		    j = col - 1;
		}

		boxarr[i][j] = (at == null) ? new StrutBox(0, 0, 0, 0) : at.createBox(env);
		
		if (boxarr[i][j].type != TeXConstants.TYPE_MULTICOLUMN) {
		    lineDepth[i] = Math.max(boxarr[i][j].getDepth(), lineDepth[i]);
		    lineHeight[i] = Math.max(boxarr[i][j].getHeight(), lineHeight[i]);
		    rowWidth[j] = Math.max(boxarr[i][j].getWidth(), rowWidth[j]);
		}
	    }
	}

	for (int j = 0; j < col; j++) {
	    matW += rowWidth[j];
	}
	Box[] Hsep = getColumnSep(env, matW);

	VerticalBox vb = new VerticalBox();
	Box Vsep = vsep_in.createBox(env);
	vb.add(vsep_ext_top.createBox(env));
	float vsepH = Vsep.getHeight();
	float totalHeight = 0;

	for (int i = 0; i < row; i++) {
	    HorizontalBox hb = new HorizontalBox(Hsep[0]);
	    for (int j = 0; j < col; j++) {
		switch (boxarr[i][j].type) {
		case -1 :
		    hb.add(new HorizontalBox(boxarr[i][j], rowWidth[j], position[j]));
		    hb.add(Hsep[j + 1]);
		    break;
		case TeXConstants.TYPE_INTERTEXT :
		    float f = env.getTextwidth();
		    f = f == Float.POSITIVE_INFINITY ? rowWidth[j] : f;
		    hb = new HorizontalBox(boxarr[i][j], f, TeXConstants.ALIGN_LEFT);
		    j = col - 1;
		    break;
		case TeXConstants.TYPE_MULTICOLUMN :
		    float w = 0;
		    MulticolumnAtom mca = (MulticolumnAtom) matrix.array.get(i).get(j);
		    int k, n = mca.getSkipped();
		    for (k = j; k < j + n - 1; k++) {
			w += rowWidth[k] + Hsep[k + 1].getWidth();
		    }
		    w += rowWidth[k];
		    Box b = mca.createBox(env);
		    float bw = b.getWidth();
		    if (bw > w) {
			// It isn't a good idea but for the moment I have no other solution ! 
			w = 0;
		    }
		    
		    mca.setWidth(w);
		    b = mca.createBox(env);
		    hb.add(b);
		    hb.add(Hsep[k + 1]);
		    j = k;
		    break;
		}
	    }
	    
	    hb.setHeight(lineHeight[i]);
	    hb.setDepth(lineDepth[i]);
	    vb.add(hb);

	    if (i < row - 1) 
		vb.add(Vsep);
	}
	
	vb.add(vsep_ext_bot.createBox(env));
	totalHeight = vb.getHeight() + vb.getDepth();
	
	float axis = env.getTeXFont().getAxisHeight(env.getStyle());
	vb.setHeight(totalHeight / 2 + axis);
	vb.setDepth(totalHeight / 2 - axis);

	return vb;
    }
}