import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawLine(0, 0, 100, 100);
	}
}
