import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	Game game;
	ArrayList<Node> avs;
	Player p;
	int[] xcords = {50, 25, -25, -50, -25, 25};
	int[] ycords = {0, 40, 40, 0, -40, -40};
	ArrayList<String> first4nodes = new ArrayList<String>();
	String first4animals = "fbhh0"; // number at end is selected animal, 0 is no animal
	
	public Panel() {
		game = new Game(0, "c");
		p = new Player();
		Node n = new Node(200, 200, "mmrrrm-bs.png", 50);
		n.rotate();
		n.rotate();
		n.setAnimal('b');
		p.addNode(n);
		avs = new ArrayList<Node>();
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.cyan);
		
		first4nodes.add("ffffff-b.png"); 
		first4nodes.add("mmmmmm-h.png");
		first4nodes.add("rppprr-bs.png");
		first4nodes.add("wwfffw-es.png");
		first4nodes.add("01"); // first number is selected node, 0 is no node, second number is rotation
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
			
			Graphics2D g2 = (Graphics2D) g;
			
			g2.rotate(Math.toRadians((n.getRot()-1)*60), n.getX(), n.getY());
			g2.drawImage(n.getImg(), n.getX()-(n.getSize()/2), n.getY()-(n.getSize()/2), n.getSize(), n.getSize(), null);
			g2.rotate(Math.toRadians((n.getRot()-1)*60)*-1, n.getX(), n.getY());
			
			if(n.getAnimal()!='n') {
				char an = n.getAnimal();
				switch (an) {
				case 'f': g.drawImage(fox, n.getX()-15, n.getY()-15, 25, 25, null);
				break;
				case 'b': g.drawImage(bear, n.getX()-15, n.getY()-15, 25, 25, null);
				break;
				case 's': g.drawImage(salmon, n.getX()-15, n.getY()-15, 25, 25, null);
				break;
				case 'e': g.drawImage(elk, n.getX()-15, n.getY()-15, 25, 25, null);
				break;
				case 'h': g.drawImage(hawk, n.getX()-15, n.getY()-15, 25, 25, null);
				break;
				}
				
			}
			
			for(int i=1; i<7; i++) {
				// badly optimized. fix later if possible
				Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
				if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
					avs.add(a);
					
				}
			}
			
		}
		
		for(Node q: avs) {
			g.drawImage(q.getImg(), q.getX()-(q.getSize()/2), q.getY()-(q.getSize()/2), q.getSize(), q.getSize(), null);
		}
		
		int c = (Integer.parseInt(first4nodes.get(4).substring(0,1))-1);
		if(c!=-1) {
			try {
				g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(c))), 400, 450, 50, 50, null);
			} catch (IOException e) {
			}
		}
		
		
		for(int i=0; i<4; i++) {
			
			if((Integer.parseInt(first4animals.substring(4, 5))-1)==i) {
				g.fillRect(595, 45+(75*i), 60, 60);
			}
			
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
				if(!first4nodes.get(i).equals("null")) {
					g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(i))), 700, 50+(75*i), 50, 50, null);
				}
			} catch (IOException e) {
				System.out.println("Error");
			}
			
		}
		
		g.setColor(Color.black);
		
		g.drawString("Rotate", 100, 500);
		
	}
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mouseClicked(MouseEvent e) {
		if(!first4animals.substring(4).equals("0")) {
			for(Node n: p.getNodes()) {
				if(n.isClicked(e.getX(), e.getY())) {
					n.setAnimal(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1));
					repaint();
					return;
				}
			}
		}
		
		for(Node a: avs) {
			
			if(a.isClicked(e.getX(), e.getY()) && !first4nodes.get(4).substring(0,1).equals("0")) {
				
				p.addNode(new Node(a.getX(), a.getY(), 
						first4nodes.get(Integer.parseInt(first4nodes.get(4).substring(0,1))-1), 50));
				avs.clear();
				repaint();
				return;
			}
			
		}
		
		for(int i=0; i<4; i++) {
			if(600 <= e.getX() && e.getX() <= 650 && 50+(75*i) <= e.getY() && e.getY() <= 100+(75*i)) {
				
				first4animals = first4animals.substring(0, 4) + (i+1);
				repaint();
				return;
			}
			
			if(700 <= e.getX() && e.getX() <= 750 && 50+(75*i) <= e.getY() && e.getY() <= 100+(75*i)) {
				
				first4nodes.set(4, (i+1) + "" + first4nodes.get(4).charAt(1));
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
