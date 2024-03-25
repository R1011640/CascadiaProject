import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	
	public Panel() {
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		Node n = new Node(200, 200);
		
		//g.drawLine(200, 200, 
		//		200 - (200 - (hexagon.xpoints[0] + hexagon.xpoints[1])/2)*2, 
		//		200 - (200 - (hexagon.ypoints[0] + hexagon.ypoints[1])/2)*2);
		
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
