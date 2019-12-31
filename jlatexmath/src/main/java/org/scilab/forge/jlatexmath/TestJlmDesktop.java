package org.scilab.forge.jlatexmath;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestJlmDesktop extends JFrame {

	// static {
	// if (FactoryProvider.getInstance() == null) {
	// FactoryProvider.setInstance(new FactoryProviderDesktop());
	// }
	//
	// }

	public TestJlmDesktop() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setSize(1000, 1000);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		// Graphics2DD g2d = new Graphics2DD(g2);

		String[] texts2 = { 
				
				//"\\Huge{\\overleftarrow{aaaaaaa}}",
				
				//"\\Huge{\\overleftrightarrow{aaaaaaa}}",
				
				//"\\Huge{\\overleftarrow{a}}",
				
				//"\\Huge{\\overleftrightarrow{a}}",
				
				//"\\Huge{A\\xhookrightarrow{aaaa}}B",
				
//				"\\Huge{A\\xhookrightarrow{aaa}}B", "\\Huge{A\\xhookrightarrow{aa}}B", "\\Huge{A\\xhookrightarrow{a}}B",
//				"\\Huge{A\\xhookrightarrow{}}B", "\\Huge{A\\xhookleftarrow{aaaa}}B", "\\Huge{A\\xhookleftarrow{aaa}}B",
//				"\\Huge{A\\xhookleftarrow{aa}}B", "\\Huge{A\\xhookleftarrow{a}}B", "\\Huge{A\\xhookleftarrow{}}B",
//				"\\Huge{A\\xmapsto{aaaa}}B", "\\Huge{A\\xmapsto{}}B", "\\Huge{A\\xlongequal{aaaa}}B",
//				"\\Huge{A\\xlongequal{}}B", "\\Huge{A\\xrightleftharpoons{}}B", "\\Huge{A\\xrightleftharpoons{aaaa}}B",
//				"\\Huge{A\\xleftrightharpoons{}}B", "\\Huge{A\\xleftrightharpoons{aaaa}}B",
//				"\\Huge{\\xrightharpoonup{}}", "\\Huge{\\xrightharpoonup{aaaa}}", "\\Huge{\\xrightharpoondown{}}",
//				"\\Huge{\\xrightharpoondown{aaaa}}", "\\Huge{\\xleftharpoondown{}}", "\\Huge{\\xleftharpoondown{aaaa}}",
//				"\\Huge{\\xleftharpoonup{}}", "\\Huge{\\xleftharpoonup{aaaa}}", "\\Huge{\\xleftarrow{}}",
//				"\\Huge{\\xleftarrow{aaaa}}", "\\Huge{\\xrightarrow{}}", "\\Huge{\\xrightarrow{aaaa}}",
//				"\\Huge{\\xleftrightarrows{}}", "\\Huge{A\\xleftrightarrows{aaaa}B}", "\\Huge{\\xrightleftarrows{}}",
				
				//"\\Huge{\\xrightleftarrows{aaaa}}",
				
				//"\\Huge{A\\xleftrightarrow{}B}",
				
				//"\\Huge{A\\xleftrightarrow{aaaa}B}", 
				
				"\\lim_{  }",
				
				//"\\text{should be a big space ->           <-}",
				
				//"′ ″ '' ' ' ' ' ' '''''''''''''''"
				//"' '"
						};
		
//		String texts[] = {
//		"\\begin{tabular}{| c || @{\\hspace{3.7 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{7.4 mm}} c @{\\hspace{3.7 mm}}|} \\hline \\vphantom{\\Huge{p}}\\hspace{8 mm} & \\text{-}1 & 0 & 1 & 2 & 3 & 4 & 5 & 6 & 7 & 8\\\\ \\hline \\vphantom{\\Huge{p}}\\hspace{8 mm} & \\phantom{x} & \\phantom{x} & \\phantom{x} & \\phantom{x} & \\phantom{\\text{-1}} & \\phantom{x} & \\phantom{x} & \\phantom{x} & \\phantom{x} & \\phantom{x}\\\\ \\hline \\end{tabular}" };


		String[] texts = {				"\\newcommand{\\red}[1]{\\textcolor{255,0,0}{#1}}",
				"\\red{\\longrightarrow} B",
				"\\textit{P}\\text{(\\hspace{11mm})=\\frac{18}{50}}",
				 "\\textit{P}\\text{(\\hspace{11mm})=\\frac{\\vphantom{8}\\hspace{14mm}}{\\vphantom{50}\\hspace{14mm}}" 
};
		
		int y = 100;
		for (String text : texts) {
			TeXFormula formula = new TeXFormula(text);
			Image im = formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, 30, Color.BLACK, Color.WHITE);
			g2.drawImage(im, 100, y, null);
			y += im.getHeight(null) + 10;
		}
	}

	public static void main(String[] args) {
		// Configuration.getFontMapping();
		TestJlmDesktop s = new TestJlmDesktop();
		s.setVisible(true);
	}

}