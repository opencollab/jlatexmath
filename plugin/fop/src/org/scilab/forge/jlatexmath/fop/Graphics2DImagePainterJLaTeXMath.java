package org.scilab.forge.jlatexmath.fop;

import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.DOMImplementation;

import org.apache.xmlgraphics.java2d.Graphics2DImagePainter;

public class Graphics2DImagePainterJLaTeXMath implements Graphics2DImagePainter {

    private Dimension dim;
    private TeXIcon icon;

    public Graphics2DImagePainterJLaTeXMath(Document doc) {
        Element e = doc.getDocumentElement();
        float size = Float.parseFloat(e.getAttribute("size"));
        Color fg = new Color(Integer.parseInt(e.getAttribute("fg")));
        
        String style = e.getAttribute("style");
        int st = TeXConstants.STYLE_DISPLAY;
        if ("text".equals(style)) {
            st = TeXConstants.STYLE_TEXT;
        } else if ("script".equals(style)) {
            st = TeXConstants.STYLE_SCRIPT;
        } else if ("script_script".equals(style)) {
            st = TeXConstants.STYLE_SCRIPT_SCRIPT;
        }
        
        icon = new TeXFormula(e.getTextContent()).createTeXIcon(st, size);
        icon.setForeground(fg);

        dim = new Dimension((int) (icon.getTrueIconWidth() * 1000), (int) (icon.getTrueIconHeight() * 1000));
    }

    public int getDepth() {
        return (int) (icon.getTrueIconDepth() * 1000);
    }

    public Dimension getImageSize() {
        return dim;
    }

    public void paint(Graphics2D g2d, Rectangle2D rect2d) {
        icon.paintIcon(null, g2d, (int) rect2d.getX(), (int) rect2d.getY());
    }
}
