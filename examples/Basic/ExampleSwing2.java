import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;


public class ExampleSwing2 {
	public static void main(String[] args)
	{
        String latex = "\\text{hello world}";
		TeXFormula formula = new TeXFormula(latex);
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
				.setSize(16)
				.setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
				.setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f)
				.build();
		
		JFrame frame = new JFrame();
		final JLabel label = new JLabel(icon);
		label.setMaximumSize(new Dimension(100,300));
		label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.getContentPane().add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
