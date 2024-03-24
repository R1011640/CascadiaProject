import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	private Polygon hexagon;
	
	public Panel() {
		addMouseListener(this);
		hexagon = new Polygon();
		for (int i = 0; i < 6; i++){
			hexagon.addPoint((int) (50 + 5 * Math.cos(i * 2 * Math.PI / 6)),
					  (int) (50 + 5 * Math.sin(i * 2 * Math.PI / 6)));
		}

	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawPolygon(hexagon);
	}
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	public void mouseClicked(MouseEvent e) {
		System.out.println("clicked");
	}
	
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
