import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	ArrayList<Node> avs;
	
	public Panel() {
		avs = new ArrayList<Node>();
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		BufferedImage test = null, av = null;
		
		try {
			test = ImageIO.read(Panel.class.getResource("/assets/ffffff-b.png"));
			av = ImageIO.read(Panel.class.getResource("/assets/available.png"));
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		
		
		Player p = new Player();   // size for all nodes is 50
		p.addNode(new Node(200, 200, test, 50));
		
		for(Node n: p.getNodes()) {
			g.drawImage(n.getImg(), n.getX()-(n.getSize()/2), n.getY()-(n.getSize()/2),
					    n.getSize(), n.getSize(), null);
			for(int i=1; i<7; i++) {
				if(n.getNearbyNode(i) == null) {
					Node a = new Node(n.getX() + (int)Math.floor(50 * Math.cos((i-1)*Math.PI/3)), 
							          n.getY() + (int)Math.floor(50 * Math.sin((i-1)*Math.PI/3)), 
									  av, 10);

					// set nearbyNodes in Player class when node placed in addNode() function, also set nearbyNodes for nearby nodes
					avs.add(a);
					g.drawImage(a.getImg(), a.getX()-(a.getSize()/2), a.getY()-(a.getSize()/2),
						    a.getSize(), a.getSize(), null);
				}
			}
		}
		
		
		/*
		g.drawImage(test, (center - size/2) + (int)Math.floor(size * Math.cos(Math.PI/3)),
							center - size/2 + (int)Math.floor(size * Math.sin(Math.PI/3)), 
							size, size, null); // 2*/
		
		// 1 is 0, 2 is pi/3, 3 is 2pi/3, 4 is 3pi/3, 5 is 4pi/3 or pi, and 6 is 5pi/3
		
		
	}
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	public void mouseClicked(MouseEvent e) {
		for(Node a: avs) {
			if(a.isClicked(e.getX(), e.getY())) {
				System.out.println("clicked");
			}
		}
	}
	
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
