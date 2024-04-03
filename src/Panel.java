import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	
	public Panel() {
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		BufferedImage test = null;
		try {
			test = ImageIO.read(Panel.class.getResource("/assets/ffffff-b.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}
		int center = 200, size = 50;
		g.drawImage(test, center - size/2, center - size/2, size, size, null);// original
		
		g.drawImage(test, (center - size/2) + (int)Math.floor(size * Math.cos(5*Math.PI/3)),
							center - size/2 + (int)Math.floor(size * Math.sin(5*Math.PI/3)), 
							size, size, null); // 1
		g.drawImage(test, (center - size/2) + (int)Math.floor(size * Math.cos(0)),
							center - size/2 + (int)Math.floor(size * Math.sin(0)),
							size, size, null); // 2
		
		g.drawImage(test, (center - size/2) + (int)Math.floor(size * Math.cos(Math.PI/3)),
							center - size/2 + (int)Math.floor(size * Math.sin(Math.PI/3)), 
							size, size, null); // 3
		
		g.drawImage(test, 225 + (int)Math.floor(size * Math.cos(Math.PI/3)),
							175 + (int)Math.floor(size * Math.sin((Math.PI/3))),
							size, size, null);
		
		g.drawImage(test, 225 + (int)Math.floor(size * Math.cos(5*Math.PI/3)),
							175 + (int)Math.floor(size * Math.sin((5*Math.PI/3))),
							size, size, null);
		
		
		
		// 4 is 2pi/3, 5 is 3pi/3 or pi, and 6 is 4pi/3


		//g.drawImage(test, (int)(75 + (1*Math.cos((Math.PI * 5)/3))), 
		//		    (int)(75 + (1*Math.sin((Math.PI * 5)/3))), 50, 50, null);

		/*Node n = new Node(200, 200);
		g.drawPolygon(n.hexagon);
		g.drawLine(200, 200, 
				200 - (200 - (n.hexagon.xpoints[0] + n.hexagon.xpoints[1])/2)*2, 
				200 - (200 - (n.hexagon.ypoints[0] + n.hexagon.ypoints[1])/2)*2);
		
		
		
		Node n2 = new Node((int)(200 - (200 - (n.hexagon.xpoints[0] + n.hexagon.xpoints[1])/2)*2),
				           (int)(200 - (200 - (n.hexagon.ypoints[0] + n.hexagon.ypoints[1])/2)*2));
		
		
		// 0, pi/3, 2pi/3, 3pi/3, 4pi/3, 5pi/3
		
		g.drawPolygon(n2.hexagon);
		*/
		
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
