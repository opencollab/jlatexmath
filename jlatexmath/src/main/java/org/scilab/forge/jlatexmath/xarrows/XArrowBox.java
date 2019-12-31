package org.scilab.forge.jlatexmath.xarrows;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import org.scilab.forge.jlatexmath.Box;
import org.scilab.forge.jlatexmath.FontInfo;

public abstract class XArrowBox extends Box {

	protected String commands;
	protected double[] data;

	GeneralPath path = new GeneralPath();

	@Override
	public void draw(Graphics2D g2, double x, double y) {
		draw(g2, x, y, commands, data);
	}

	protected void draw(Graphics2D g2, double x, double y, String commands, double[] data) {
		startDraw(g2, x, y);
		g2.translate(x, y);

		// g2.startDrawing();
		path.reset();
		int j = 0;
		for (char c : commands.toCharArray()) {
			switch (c) {
			case 'M':
				path.moveTo(data[j], data[j + 1]);
				j += 2;
				break;
			case 'L':
				path.lineTo(data[j], data[j + 1]);
				j += 2;
				break;
			case 'Q':
				path.quadTo(data[j + 2], data[j + 3], data[j], data[j + 1]);
				j += 4;
				break;
			case 'C':
				path.curveTo(data[j], data[j + 1], data[j + 2], data[j + 3], data[j + 4], data[j + 5]);
				j += 6;
				break;
			}
		}
		g2.setStroke(new BasicStroke(1));
		g2.fill(path);
		// g2.finishDrawing();

		g2.translate(-x, -y);
		endDraw(g2);
	}

	@Override
	public FontInfo getLastFont() {
		return null;
	}
}
