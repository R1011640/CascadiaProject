import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	Game game;
	ArrayList<Node> avs;
	Player p;
	int[] xcords = {50, 25, -25, -50, -25, 25};
	int[] ycords = {0, 40, 40, 0, -40, -40};
	ArrayList<String> first4nodes = new ArrayList<String>();
	String first4animals = "fbhs0"; // number at end is selected animal, 0 is no animal
	int natureTokens;
	char spent; // 'n' = not spent, 's' = spent, select separate, 'o' = spent, overpopulate
	int turnsLeft = 60;
	public Panel() {
		
		game = new Game(0, "c");
		System.out.println(game.overpopulate2("four", "eehe"));
		p = new Player();
		Node n = new Node(300, 200, "mmrrrm-bs.png", 50);
		p.addNode(n);
		avs = new ArrayList<Node>();
		addMouseListener(this);
		
	}
	
	
	public void paint(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.cyan);
		g.setFont(new Font("SANS SERIF", 1, 16));
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
		Graphics2D g2 = (Graphics2D) g;
		
		for(Node n: p.getNodes()) {
			
			
			
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
			
			if(first4nodes.get(4).charAt(0)!='0') { // remove this and rework getting avs in future
				for(int i=1; i<7; i++) {
					// badly optimized. fix later if possible
					if(50 < n.getX()+xcords[i-1] && n.getX()+xcords[i-1] < 550 &&
						50 < n.getY()+ycords[i-1] && n.getY()+ycords[i-1] < 450) {
						
						Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
						if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
							avs.add(a);
							
					}
					}
				}
			}
			
		}
		
		for(Node q: avs) { // in the future, only do this when first4nodes.get(4).charAt(0)!='0' and see line 90
			g.drawImage(q.getImg(), q.getX()-(q.getSize()/2), q.getY()-(q.getSize()/2), q.getSize(), q.getSize(), null);
		}
		
		int c = (Integer.parseInt(first4nodes.get(4).substring(0,1))-1);
		if(c!=-1) {
			try {
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60), 425, 475);
				g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(c))), 400, 450, 50, 50, null);
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60)*-1, 425, 475);
			} catch (IOException e) {
			}
		}
		
		
		for(int i=0; i<Math.min(4, first4nodes.size()); i++) {
			
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
		
		g.drawString("Rotate", 10, 500);
		g.drawString("Player #", 10, 20);

		g.drawImage(acorn, 550, 475, 50, 50, null);
		g.setFont(new Font("SANS SERIF", 1, 25));
		g.drawString(p.getTokens() + "", 600, 475);
		
	}
	
	public void makeAvs(Node n) {
		for(int i=1; i<7; i++) {
			// badly optimized. fix later if possible
			if(50 < n.getX()+xcords[i-1] && n.getX()+xcords[i-1] < 550 &&
				50 < n.getY()+ycords[i-1] && n.getY()+ycords[i-1] < 450) {
				
				Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
				if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
					avs.add(a);
					
			}
			}
		}
	}
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mouseClicked(MouseEvent e) {
		
		
		if(5 <= e.getX() && e.getX() <= 40 && 470 <= e.getY() && e.getY() <= 525) { // rotating selected tile
			first4nodes.set(4, first4nodes.get(4).charAt(0)
					+ "" + (Integer.parseInt(first4nodes.get(4).substring(1))+1) + "");
			if(first4nodes.get(4).charAt(1)=='7') {
				first4nodes.set(4, first4nodes.get(4).charAt(0) + "" + 1 + "");
			}
			repaint();
			return;
		}
		
		if(!first4animals.substring(4).equals("0")) { // placing animal token if one is selected
			if(first4nodes.get(4).charAt(0) == '0' || spent == 's'
				|| first4nodes.get(4).charAt(0) == first4animals.charAt(4)) { // check if adjacent or nature token spent
				for(Node n: p.getNodes()) {
					if(n.isClicked(e.getX(), e.getY())) {
						if(n.getAvailable().indexOf(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1)) != -1)
							n.setAnimal(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1));
						repaint();
						return;
					}
				}
			}
		}
		
		for(Node a: avs) { // placing a tile if one is selected
			
			if(a.isClicked(e.getX(), e.getY()) && !first4nodes.get(4).substring(0,1).equals("0")) {
				
				Node n = new Node(a.getX(), a.getY(), first4nodes.get(Integer.parseInt(first4nodes.get(4).substring(0,1))-1), 50);
				p.addNode(n);
				while(n.getRot() != (Integer.parseInt(first4nodes.get(4).substring(1)))){
					n.rotate();
				}
				avs.clear();
				repaint();
				return;
			}
			
		}
		
		for(int i=0; i<4; i++) {
			if(600 <= e.getX() && e.getX() <= 650 && 50+(75*i) <= e.getY() && e.getY() <= 100+(75*i)) { // selecting an animal token
				
				first4animals = first4animals.substring(0, 4) + (i+1);
				repaint();
				return;
			}
			
			if(700 <= e.getX() && e.getX() <= 750 && 50+(75*i) <= e.getY() && e.getY() <= 100+(75*i)) { // selecting a tile
				
				if(Integer.parseInt(first4nodes.get(4).substring(0,1)) == (i+1)) {
					first4nodes.set(4, "01");
					avs.clear(); // might cause problems later on. see line 107
				}
				else first4nodes.set(4, (i+1) + "1");
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
