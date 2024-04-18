import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	Game game; // the game
	ArrayList<Node> avs; // spots where the player can place a tile
	public static int[] xcords = {50, 25, -25, -50, -25, 25};
	public static int[] ycords = {0, 40, 40, 0, -40, -40};
	ArrayList<String> first4nodes = new ArrayList<String>();
	String first4animals; // number at end is selected animal, 0 is no animal
	char spent; // 'n' = not spent, 'p' = spent, pending selection, 's' = spent, select separate, 'o' = spent, overpopulate
	boolean placed, aplaced; // if player placed a tile or not
	int turnsLeft = 60;
	String customOvp; // used to find what animals will be replaced if a nature token
	// is spent to overpopulate
	public Panel() {
		customOvp = "";
		spent = 'n';
		placed = false;
		aplaced = false;
		game = new Game(0, "c");
		first4animals = game.getFirst4Animals2() + "0";
		while(first4animals.substring(0,4).equals("hhhh") || first4animals.substring(0,4).equals("bbbb")
				|| first4animals.substring(0,4).equals("eeee") || first4animals.substring(0,4).equals("ffff")
				|| first4animals.substring(0,4).equals("ssss")) {
			first4animals = game.getFirst4Animals2() + "0";
		}
		avs = new ArrayList<Node>();
		addMouseListener(this);
		
	}
	
	
	public void paint(Graphics g) { //                              begin painting
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 600);
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
		
		// for(Node n: p.getNodes()) {
		for(Node n: game.currentPlayer().getNodes()) {
			
			
			
			g2.rotate(Math.toRadians((n.getRot()-1)*60), n.getX(), n.getY());
			g2.drawImage(n.getImg(), n.getX()-(n.getSize()/2), n.getY()-(n.getSize()/2), n.getSize(), n.getSize(), null);
			g2.rotate(Math.toRadians((n.getRot()-1)*60)*-1, n.getX(), n.getY());
			
			if(n.getAnimal()!='n') {
				switch (n.getAnimal()) {
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
			
			if(first4nodes.get(4).charAt(0)!='0' && !placed) {
				// this function finds spots where the player can place a tile
				for(int i=1; i<7; i++) {
					// badly optimized. fix later if possible
					if(50 < n.getX()+xcords[i-1] && n.getX()+xcords[i-1] < 550 &&
						50 < n.getY()+ycords[i-1] && n.getY()+ycords[i-1] < 350) {
						
						Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
						if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
							avs.add(a);
							
					}
					}
				}
			}
			
		}
		
		for(Node q: avs) { // in the future, only do this when first4nodes.get(4).charAt(0)!='0' and see line 90
			// also this draws the available places where you can place a tile
			g.drawImage(q.getImg(), q.getX()-(q.getSize()/2), q.getY()-(q.getSize()/2), q.getSize(), q.getSize(), null);
		}
		
		int c = (Integer.parseInt(first4nodes.get(4).substring(0,1))-1);
		if(c!=-1) {
			try { // draws selected node, if there is one
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60), 415, 465);
				g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(c))), 375, 425, 80, 80, null);
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60)*-1, 415, 465);
			} catch (IOException e) {
			}
		}
		
		g.setColor(Color.gray);
		for(int i=0; i<Math.min(4, first4nodes.size()); i++) {
			
			if((Integer.parseInt(first4animals.substring(4, 5))-1)==i) {
				g.setColor(Color.cyan); // shows if an animal token is selected
				g.fillRect(595, 45+(75*i), 60, 60);
				g.setColor(Color.gray);
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
			if(customOvp.contains((i+1)+"")){
				g.fillRect(550, 50+(75*i), 25, 25);
			}
			try {
				if(!first4nodes.get(i).equals("null")) {
					g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(i))), 700, 50+(75*i), 50, 50, null);
				}
				
			} catch (IOException e) {
				System.out.println("Error");
			}
			
		}
		
		g.setColor(Color.green); g.fillRect(5, 470, 60, 55);
		g.setColor(Color.red); g.fillRect(75, 470, 70, 55);
		
		g.setColor(Color.black);
		
		g.drawString("Rotate", 10, 500);
		g.drawString("End Turn", 75, 500);
		g.drawString("Player #" + (game.currentPlayerNum()+1), 10, 20);

		if(spent!='n') {
			g.setColor(Color.green); 
			g.fillRect(630, 400, 160, spent=='s'?45:30);
			g.fillRect(630, 470, 160, spent=='o'?45:30); 
			g.setColor(Color.black);
			g.setFont(new Font("SANS SERIF", 1, 15));
			g.drawString("Pick seperate tokens", 630, 420);
			g.setFont(new Font("SANS SERIF", 1, 20));
			g.drawString("Overpopulate", 630, 490);
		}
		
		g.drawImage(acorn, 550, 475, 50, 50, null);
		g.setFont(new Font("SANS SERIF", 1, 25));
		g.drawString(game.currentPlayer().getTokens() + "", 600, 475);
		
	}                                                        // end of painting
	
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
		
		//g.fillRect(630, 400, 160, 55); separate  g.fillRect(630, 470, 160, 55); overpopulate
		
		if(630 <= e.getX() && e.getX() <= 790) {
			if(400 <= e.getY() && e.getY() <= 455) { // click to pick separate tokens
				spent = 's';
				repaint();
				return;
			} else if (470 <= e.getY() && e.getY() <= (470+55)) { // click to overpopulate
				if(spent!='o')spent = 'o';
				else if (!customOvp.equals("")){
					for(char i: customOvp.toCharArray()) {
				first4animals = first4animals.substring(0, (i-'0')-1) +
				game.randomAnimal() + first4animals.substring((i-'0'));
					}
					customOvp = "";
					spent = 'n';
				}
				repaint();
				return;
			}
		}
		
		if(550 <= e.getX() && e.getX() <= 600 && 475 <= e.getY() && e.getY() <= 525 && !placed && !aplaced) { // clicking acorn
			if(spent=='n' && game.currentPlayer().getTokens()>0) {
				spent = 'p';
				game.currentPlayer().setTokens(game.currentPlayer().getTokens()-1);
			}
			else if(spent!='n'){
				spent = 'n';
				game.currentPlayer().setTokens(game.currentPlayer().getTokens()+1);
			}
			repaint();
			return;
		}
		
		if(5 <= e.getX() && e.getX() <= 70 && 470 <= e.getY() && e.getY() <= 525) { // rotating selected tile
			first4nodes.set(4, first4nodes.get(4).charAt(0)
					+ "" + (Integer.parseInt(first4nodes.get(4).substring(1))+1) + "");
			if(first4nodes.get(4).charAt(1)=='7') {
				first4nodes.set(4, first4nodes.get(4).charAt(0) + "" + 1 + "");
			}
			repaint();
			return;
		}
		
		if(75 <= e.getX() && e.getX() <= 145 && 470 <= e.getY() && e.getY() <= 525 && placed) { // ending turn
			avs.clear();
			first4nodes.set(4, "01");
			first4animals = first4animals.substring(0,4) + "0";
			placed = false;
			aplaced = false;
			//if(spent=='p' || spent=='o') {
			//	game.currentPlayer().setTokens(game.currentPlayer().getTokens()-1);
			//}
			spent = 'n';
			game.endTurn();
			repaint();
			return;
		}
		
		
		if(!first4animals.substring(4).equals("0")) { // placing animal token if one is selected
			if(first4nodes.get(4).charAt(0) == '0' || spent == 's'
				|| first4nodes.get(4).charAt(0) == first4animals.charAt(4)) { // check if adjacent or nature token spent
				for(Node n: game.currentPlayer().getNodes()) {
					if(n.isClicked(e.getX(), e.getY())) {
						if(n.getAvailable().indexOf(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1)) != -1 && !aplaced && placed) {
							n.setAnimal(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1));
							aplaced = true;
							if(spent=='s') spent = 'n';
							first4animals = first4animals.substring(0, (Integer.parseInt(first4animals.substring(4, 5))-1))
									+ game.randomAnimal() + first4animals.substring((Integer.parseInt(first4animals.substring(4, 5))));
							first4animals = first4animals.substring(0, 4) + "0"; 
							System.out.println(first4animals);
						
						}
						repaint();
						return;
					}
				}
			}
		}
		
		for(Node a: avs) { // placing a tile if one is selected
			
			if(a.isClicked(e.getX(), e.getY()) && !first4nodes.get(4).substring(0,1).equals("0") && !placed) {
				
				Node n = new Node(a.getX(), a.getY(), first4nodes.get(Integer.parseInt(first4nodes.get(4).substring(0,1))-1), 50);
				game.currentPlayer().addNode(n);
				while(n.getRot() != (Integer.parseInt(first4nodes.get(4).substring(1)))){
					n.rotate();
				}
				if(n.getEdges().equals("mmmmmm") || n.getEdges().equals("wwwwww") || n.getEdges().equals("ffffff")
					|| n.getEdges().equals("rrrrrr") || n.getEdges().equals("pppppp")) {
					game.currentPlayer().setTokens(game.currentPlayer().getTokens()+1);
				}
				placed = true;
				avs.clear();
				repaint();
				return;
			}
			
		}
		
		for(int i=0; i<4; i++) {
			if(600 <= e.getX() && e.getX() <= 650 && 50+(75*i) <= e.getY() && e.getY() <= 100+(75*i)) { // selecting an animal token
				if(spent == 's') {
				first4animals = first4animals.substring(0, 4) + (i+1);
				repaint();
				return;
				} else if (spent == 'o') {
					if(!customOvp.contains((i+1)+"")) customOvp += (i+1);
					else {
			customOvp = customOvp.substring(0, customOvp.indexOf((i+1)+"")) + 
					    customOvp.substring(customOvp.indexOf((i+1)+"")+1); 
					}
					repaint();
					return;
					
				}
			}
			
			if(700 <= e.getX() && e.getX() <= 750 && 50+(75*i) <= e.getY() && e.getY() <= 100+(75*i) && !placed) { // selecting a tile
				
				if(Integer.parseInt(first4nodes.get(4).substring(0,1)) == (i+1)) {
					first4nodes.set(4, "01");
					first4animals = first4animals.substring(0, 4) + "0"; 
					avs.clear(); // might cause problems later on. see line 107
				}
				else {
					first4nodes.set(4, (i+1) + "1");
					first4animals = first4animals.substring(0, 4) + (i+1);
				}
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
