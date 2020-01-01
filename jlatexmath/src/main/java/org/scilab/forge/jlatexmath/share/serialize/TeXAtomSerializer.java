package org.scilab.forge.jlatexmath.share.serialize;

import org.scilab.forge.jlatexmath.share.AccentedAtom;
import org.scilab.forge.jlatexmath.share.ArrayAtom;
import org.scilab.forge.jlatexmath.share.ArrayOfAtoms;
import org.scilab.forge.jlatexmath.share.Atom;
import org.scilab.forge.jlatexmath.share.BigOperatorAtom;
import org.scilab.forge.jlatexmath.share.BreakMarkAtom;
import org.scilab.forge.jlatexmath.share.CharAtom;
import org.scilab.forge.jlatexmath.share.ColorAtom;
import org.scilab.forge.jlatexmath.share.EmptyAtom;
import org.scilab.forge.jlatexmath.share.FencedAtom;
import org.scilab.forge.jlatexmath.share.FractionAtom;
import org.scilab.forge.jlatexmath.share.HlineAtom;
import org.scilab.forge.jlatexmath.share.JavaFontRenderingAtom;
import org.scilab.forge.jlatexmath.share.NthRoot;
import org.scilab.forge.jlatexmath.share.PhantomAtom;
import org.scilab.forge.jlatexmath.share.RowAtom;
import org.scilab.forge.jlatexmath.share.ScriptsAtom;
import org.scilab.forge.jlatexmath.share.SpaceAtom;
import org.scilab.forge.jlatexmath.share.SymbolAtom;
import org.scilab.forge.jlatexmath.share.Symbols;
import org.scilab.forge.jlatexmath.share.TypedAtom;
import org.scilab.forge.jlatexmath.share.VRowAtom;
import org.scilab.forge.jlatexmath.share.platform.FactoryProvider;

/**
 * Converts parsed LaTeX to GGB syntax
 * 
 * @author Zbynek
 *
 */
public class TeXAtomSerializer {
	private BracketsAdapterI adapter;

	/**
	 * @param ad
	 *            adapter
	 */
	public TeXAtomSerializer(BracketsAdapterI ad) {
		this.adapter = ad == null ? new DefaultBracketsAdapter() : ad;
	}

	public String serialize(Atom root) {

		String ret = serialize2(root);
		FactoryProvider.debugS(root.getClass() + " " + ret);
		return ret;

	}


	/**
	 * @param root
	 *            tex formula
	 * @return expression in GeoGebra syntax
	 */
	public String serialize2(Atom root) {

		// FactoryProvider.debugS("root = " + root.getClass());
		if (root instanceof FractionAtom) {
			FractionAtom frac = (FractionAtom) root;

			if (frac.getRuleThickness() == 0) {
				// \binom not \frac
				return "nCr(" + serialize(frac.getNumerator()) + ","
						+ serialize(frac.getDenominator()) + ")";
			}

			return "(" + serialize(frac.getNumerator()) + ")/("
					+ serialize(frac.getDenominator()) + ")";
		}
		if (root instanceof NthRoot) {
			NthRoot nRoot = (NthRoot) root;
			if (nRoot.getRoot() == null) {
				return "sqrt(" + serialize(nRoot.getTrueBase()) + ")";
			}
			return "nroot(" + serialize(nRoot.getTrueBase()) + ","
					+ serialize(nRoot.getRoot()) + ")";
		}
		if (root instanceof CharAtom) {
			CharAtom ch = (CharAtom) root;
			return ch.getCharacter() + "";
		}
		if (root instanceof TypedAtom) {
			TypedAtom ch = (TypedAtom) root;
			return serialize(ch.getBase());
		}
		if (root instanceof ScriptsAtom) {
			ScriptsAtom ch = (ScriptsAtom) root;
			return subSup(ch);
		}
		if (root instanceof FencedAtom) {
			FencedAtom ch = (FencedAtom) root;
			String left = serialize(ch.getLeft());
			String right = serialize(ch.getRight());
			String base = serialize(ch.getTrueBase());
			return adapter.transformBrackets(left, base, right);
		}
		if (root instanceof SpaceAtom) {
			return " ";
		}
		if (root instanceof EmptyAtom || root instanceof BreakMarkAtom
				|| root instanceof PhantomAtom) {
			return "";
		}
		if (root instanceof SymbolAtom) {

			SymbolAtom ch = (SymbolAtom) root;
			String out = ch.getUnicode() + "";
			if ("\u00b7".equals(out)) {
				return "*";
			}
			return out;

		}
		if (root instanceof RowAtom) {
			RowAtom row = (RowAtom) root;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; row.getElement(i) != null; i++) {
				sb.append(serialize(row.getElement(i)));
			}
			return sb.toString();
		}
		if (root instanceof AccentedAtom) {
			SymbolAtom accent = ((AccentedAtom) root).getAccent();
			String content = serialize(((AccentedAtom) root).getBase());
			if (accent == Symbols.VEC) {
				return " vector " + content;
			}
			return content + " with " + accent.getUnicode();
		}
		if (root instanceof VRowAtom) {
			VRowAtom row = (VRowAtom) root;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; row.getElement(i) != null; i++) {
				sb.append(serialize(row.getElement(i)));
				sb.append(" new line ");
			}
			return sb.toString();
		}

		if (root instanceof HlineAtom) {
			return "";
		}

		if (root instanceof ColorAtom) {
			ColorAtom row = (ColorAtom) root;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; row.getElement(i) != null; i++) {
				sb.append(serialize(row.getElement(i)));
			}
			return sb.toString();
		}

		if (root instanceof JavaFontRenderingAtom) {
			return ((JavaFontRenderingAtom) root).getString();
		}

		// serialise table to eg {{1,2,3},{3,4,5}}
		if (root instanceof ArrayAtom) {
			ArrayAtom atom = (ArrayAtom) root;
			ArrayOfAtoms matrix = atom.getMatrix();
			int rows = matrix.getRows();
			int cols = matrix.getCols();

			StringBuilder sb = new StringBuilder();
			sb.append('{');
			for (int row = 0; row < rows; row++) {
				sb.append('{');
				for (int col = 0; col < cols; col++) {
					sb.append(serialize(matrix.get(row, col)));

					if (col < cols - 1) {
						sb.append(",");
					}
				}
				sb.append("}");

				if (row < rows - 1) {
					sb.append(",");
				}
			}
			sb.append('}');

			return sb.toString();
		}

		if (root instanceof BigOperatorAtom) {

			BigOperatorAtom bigOp = (BigOperatorAtom) root;

			String op = serialize(bigOp.getTrueBase());

			if ("log".equals(op)) {
				return "log_" + serialize(bigOp.getBottom());
			}

			// eg sum/product
			return serialize(bigOp.getTrueBase()) + " from " + serialize(bigOp.getBottom()) + " to "
					+ serialize(bigOp.getTop());
		}
		
		// BoldAtom, ItAtom, TextStyleAtom, StyleAtom, RomanAtom
		// TODO: probably more atoms need to implement HasTrueBase
		if (root instanceof HasTrueBase) {
			return serialize(((HasTrueBase) root).getTrueBase());
		}

		FactoryProvider.debugS("Unhandled atom:"
				+ (root == null ? "null" : (root.getClass() + " " + root.toString())));
		// FactoryProvider.getInstance().printStacktrace();

		return "?";
	}

	private String subSup(ScriptsAtom script) {
		StringBuilder sb = new StringBuilder(serialize(script.getTrueBase()));
		if (script.getSub() != null) {
			String sub = serialize(script.getSub());
			sb.append(adapter.subscriptContent(sub));
		}
		if (script.getSup() != null) {
			sb.append("^(");
			sb.append(serialize(script.getSup()));
			sb.append(")");
		}
		return sb.toString();
	}

}
