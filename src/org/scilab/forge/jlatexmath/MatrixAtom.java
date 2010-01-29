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
    
    public static SpaceAtom hsep = new SpaceAtom(TeXConstants.UNIT_MU, 9.0f, 0.0f, 0.0f);
    public static SpaceAtom vsep_in = new SpaceAtom(TeXConstants.UNIT_MU, 0.0f, 5.0f, 0.0f);
    public static SpaceAtom vsep_ext = new SpaceAtom(TeXConstants.UNIT_MU, 0.0f, 3.0f, 0.0f);

    private ArrayOfAtoms matrix;
    private float plus = 0;
    private int[] position;
    private Atom[] insertAtom;
    private boolean isAlign = false;
    private boolean isAlignat = false;
    
    private static SpaceAtom align = new SpaceAtom(TeXConstants.MEDMUSKIP);

    /**
     * Creates an empty matrix
     *
     */
    public MatrixAtom(ArrayOfAtoms array, String options) {
	this.matrix = array;
	parsePositions(new StringBuffer(options));
	//getPositions(options);
    }

    public MatrixAtom(ArrayOfAtoms array) {
	this.matrix = array;
	isAlign = true;
	position = new int[matrix.col];
	for (int i = 0; i < matrix.col; i += 2) {
	    position[i] = TeXConstants.ALIGN_RIGHT;
	    if (i + 1 < matrix.col) {
		position[i + 1] = TeXConstants.ALIGN_LEFT;
	    }
	}
    }

    public MatrixAtom(ArrayOfAtoms array, boolean isAlignat) {
	this.matrix = array;
	this.isAlignat = isAlignat;
	position = new int[matrix.col];
	for (int i = 0; i < matrix.col; i += 2) {
	    position[i] = TeXConstants.ALIGN_RIGHT;
	    position[i + 1] = TeXConstants.ALIGN_LEFT;
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
    
    public Box createBox(TeXEnvironment env) {
	int row = matrix.row;
	int col = matrix.col;
	Box[][] boxarr = new Box[row][col];
	float[] lineDepth = new float[row];
	float[] lineHeight = new float[row];
	float[] rowWidth = new float[col];
	float matW = 0;

	if (plus == 0) {
	    plus = new TeXFormula("+").root.createBox(env).getHeight();
	}

	for (int i = 0; i < row; i++) {
	    lineDepth[i] = 0;
	    lineHeight[i] = 0;
	    for (int j = 0; j < col; j++) {
		Atom at = matrix.array.get(i).get(j);
		boxarr[i][j] = (at == null) ? new StrutBox(0, 0, 0, 0) : at.createBox(env); 
		lineDepth[i] = Math.max(boxarr[i][j].getDepth(), lineDepth[i]);
		lineHeight[i] = Math.max(boxarr[i][j].getHeight(), lineHeight[i]);
		rowWidth[j] = Math.max(boxarr[i][j].getWidth(), rowWidth[j]);
	    }
	}

	Box Align = align.createBox(env);
	Box AlignSep = null;
	if (isAlign) {
	    for (int j = 0; j < col; j++) {
		matW += rowWidth[j];
	    }
	    float lw = env.getTextwidth();
	    if (lw != Float.POSITIVE_INFINITY && lw != Float.NaN) {
		AlignSep = new StrutBox(Math.max((lw - matW - (col / 2) * Align.getWidth()) / (float)Math.floor((col + 3)/ 2), 0), 0.0f, 0.0f, 0.0f);
	    }
	}
	
	VerticalBox vb = new VerticalBox();
	Box Vsep = vsep_in.createBox(env);
	Box Vsep2 = vsep_ext.createBox(env);
	vb.add(Vsep2);
	float vsepH = Vsep.getHeight();
	float totalHeight = 0;

	Box Hsep = hsep.createBox(env);

	for (int i = 0; i < row; i++) {
	    HorizontalBox hb = new HorizontalBox();
	    for (int j = 0; j < col; j++) {
		hb.add(new HorizontalBox(boxarr[i][j], rowWidth[j], position[j]));
		if (j < col - 1) {
		    if (isAlign || isAlignat) {
			if (position[j] == TeXConstants.ALIGN_RIGHT) {
			    hb.add(Align);
			} else if (!isAlignat) {
			    if (AlignSep != null) {
				hb.add(AlignSep);
			    } else {
				hb.add(Hsep);
			    }
			}
		    } else {
			hb.add(Hsep);
		    }
		}
	    }
	    
	    hb.setHeight(lineHeight[i]);
	    hb.setDepth(lineDepth[i]);
	    vb.add(hb);

	    if (i < row -1) 
		vb.add(Vsep);
	    
	    totalHeight += lineHeight[i] + lineDepth[i] + vsepH; 
	}
	
	totalHeight = totalHeight - vsepH + 2 * Vsep2.getHeight();
	vb.add(Vsep2);
	
	vb.setHeight((totalHeight + plus)/ 2);
	vb.setDepth((totalHeight - plus) / 2);
	return vb;
    }
}