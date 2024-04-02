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
		g.drawPolygon(n.hexagon);
		g.drawLine(200, 200, 
				200 - (200 - (n.hexagon.xpoints[0] + n.hexagon.xpoints[1])/2)*2, 
				200 - (200 - (n.hexagon.ypoints[0] + n.hexagon.ypoints[1])/2)*2);
		
		
		
		Node n2 = new Node((int)(200 - (200 - (n.hexagon.xpoints[0] + n.hexagon.xpoints[1])/2)*2),
				           (int)(200 - (200 - (n.hexagon.ypoints[0] + n.hexagon.ypoints[1])/2)*2));
		
		
		// 0, pi/3, 2pi/3, 3pi/3, 4pi/3, 5pi/3
		
		g.drawPolygon(n2.hexagon);
		
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
