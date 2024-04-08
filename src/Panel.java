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
	Player p;
	public Panel() {
		p = new Player();
		p.addNode(new Node(200, 200, "ffffff-b.png", 50));
		avs = new ArrayList<Node>();
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		int[] xcords = {50, 25, -25, -50, -25, 25};
		int[] ycords = {0, 40, 40, 0, -40, -40};
		
		ArrayList<Node> first4nodes;
		String first4animals;
		
		for(Node n: p.getNodes()) {
			g.drawImage(n.getImg(), n.getX()-(n.getSize()/2), n.getY()-(n.getSize()/2), n.getSize(), n.getSize(), null);
			
			for(int i=1; i<7; i++) {
				// badly optimized. fix later if possible
				Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
				if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
					avs.add(a);
					g.drawImage(a.getImg(), a.getX()-(a.getSize()/2), a.getY()-(a.getSize()/2), a.getSize(), a.getSize(), null);
				}
			}
			
		}
		
	}
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mouseClicked(MouseEvent e) {
		for(Node a: avs) {
			
			if(a.isClicked(e.getX(), e.getY())) {
				p.addNode(new Node(a.getX(), a.getY(), "ffffff-b.png", 50));
			}
			
		}
		avs.clear();
		repaint();
	}
	
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
