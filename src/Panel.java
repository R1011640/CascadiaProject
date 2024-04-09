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
	int[] xcords = {50, 25, -25, -50, -25, 25};
	int[] ycords = {0, 40, 40, 0, -40, -40};
	public Panel() {
		p = new Player();
		p.addNode(new Node(200, 200, "ffffff-b.png", 50));
		avs = new ArrayList<Node>();
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		
		ArrayList<String> first4nodes = new ArrayList<String>();
		first4nodes.add("ffffff-b.png");
		first4nodes.add("ffffff-b.png");
		first4nodes.add("ffffff-b.png");
		first4nodes.add("ffffff-b.png");
		String first4animals = "fbhh";
		BufferedImage fox = null, hawk = null, elk = null, bear = null, salmon = null, acorn = null;
		try {
			fox = ImageIO.read(Panel.class.getResource("/assets/fox.png"));
			hawk = ImageIO.read(Panel.class.getResource("/assets/hawk.png"));
			elk = ImageIO.read(Panel.class.getResource("/assets/elk.png"));
			bear = ImageIO.read(Panel.class.getResource("/assets/bear.png"));
			salmon = ImageIO.read(Panel.class.getResource("/assets/fish.png"));
			acorn = ImageIO.read(Panel.class.getResource("/assets/acorn.png"));
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		
		// draw nodes & animals
		
		
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
		
		for(int i=0; i<4; i++) {
			if(first4animals.charAt(i) == 'f') {
				g.drawImage(fox, 600, 50+(75*i), 50, 50, null);
			} else if (first4animals.charAt(i) == 's') {
				g.drawImage(salmon, 600, 50+(75*i), 50, 50, null);
			} else if (first4animals.charAt(i) == 'e') {
				g.drawImage(elk, 600, 50+(75*i), 50, 50, null);
			} else if (first4animals.charAt(i) == 'h') {
				g.drawImage(hawk, 600, 50+(75*i), 50, 50, null);
			} else if (first4animals.charAt(i) == 'b') {
				g.drawImage(bear, 600, 50+(75*i), 50, 50, null);
			}
	
			try {
				g.drawImage(ImageIO.read(Panel.class.getResource("/assets/" + first4nodes.get(i))), 700, 50+(75*i), 50, 50, null);
			} catch (IOException e) {
				System.out.println("Error");
			}
			
		}
		
	}
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mouseClicked(MouseEvent e) {
		
		for(Node ss: p.getNodes()) {
			if(ss.isClicked(e.getX(), e.getY())) {
				System.out.println("c");
			}
		}
		for(Node a: avs) {
			
			if(a.isClicked(e.getX(), e.getY())) {
				p.addNode(new Node(a.getX(), a.getY(), "ffffff-b.png", 50));
				avs.clear();
				repaint();
				return;
			}
			
		}
		
	}
	
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}


/* ------------------------------------------------------------ NOTES
 * 
 * Major stuff happens in mouseClicked()
 * x and y in the node class specify the CENTER of the node, not the top left.
 * A negative y value means the node is going UP, not down. Same with positive y values.
 * 
 * 
 * 
 */
