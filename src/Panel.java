import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Toolkit;
public class Panel extends JPanel implements MouseListener, KeyListener{
	
	Game game; // the game
	ArrayList<Node> avs; // spots where the player can place a tile
	public static int[] xcords = {50, 25, -25, -50, -25, 25};
	public static int[] ycords = {0, 40, 40, 0, -40, -40};
	ArrayList<String> first4nodes = new ArrayList<String>();
	String first4animals = ""; // number at end is selected animal, 0 is no animal
	char spent; // 'n' = not spent, 'p' = spent, pending selection, 's' = spent, select separate, 'o' = spent, overpopulate
	boolean placed, aplaced, op3; // if player placed a tile or not
	int turnsLeft = 60;
	String customOvp; // used to find what animals will be replaced if a nature token
	// is spent to overpopulate
	BufferedImage fox = null, hawk = null, elk = null, bear = null, salmon = null, acorn = null, bg = null;
	int viewedPlayer; // the player who is having their info being drawn. will default to currentPlayer
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	double w = width/1920.0, h = height/1080.0;
	public Panel() {
		customOvp = "";
		spent = 'n';
		placed = false;
		aplaced = false;
		op3 = false;
		game = new Game(0, "c");
		first4animals = game.getFirst4Animals2() + "0";
		
		game.currentPlayer().setTokens(1);
		
		while(first4animals.substring(0,4).equals("hhhh") || first4animals.substring(0,4).equals("bbbb")
				|| first4animals.substring(0,4).equals("eeee") || first4animals.substring(0,4).equals("ffff")
				|| first4animals.substring(0,4).equals("ssss")) {
			first4animals = game.getFirst4Animals2() + "0";
		}
		first4nodes.add("ffffff-b.png");
		first4nodes.add("mmmmmm-h.png");
		first4nodes.add("rppprr-bs.png");
		first4nodes.add("wwfffw-es.png");
		first4nodes.add("01"); // first number is selected node, 0 is no node, second number is rotation
		viewedPlayer = game.currentPlayerNum();
		avs = new ArrayList<Node>();
		try {
			fox = ImageIO.read(Panel.class.getResource("/assets/fox.png"));
			hawk = ImageIO.read(Panel.class.getResource("/assets/hawk.png"));
			elk = ImageIO.read(Panel.class.getResource("/assets/elk.png"));
			bear = ImageIO.read(Panel.class.getResource("/assets/bear.png"));
			salmon = ImageIO.read(Panel.class.getResource("/assets/fish.png"));
			acorn = ImageIO.read(Panel.class.getResource("/assets/acorn.png"));
			bg = ImageIO.read(Panel.class.getResource("/assets/bg2.png"));
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		addMouseListener(this);
		addKeyListener(this);
	}
	
	
	public void paint(Graphics g) { //                              begin painting
		
		
		
		g.drawImage(bg, 0, 0, 1920, 1080, null);
		g.setFont(new Font("SANS SERIF", 1, 16));
		
		
		
		
		
		// to draw nodes & animals
		Graphics2D g2 = (Graphics2D) g;
		
		
		for(Node n: game.getPlayer(viewedPlayer).getNodes()) {
			
			
			
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
					if(150 < n.getX()+xcords[i-1] && n.getX()+xcords[i-1] < 1550 &&
						150 < n.getY()+ycords[i-1] && n.getY()+ycords[i-1] < 900) {
						
						Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
						if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
							avs.add(a);
							
					}
					}
				}
			}
			
		}
		
		// this draws the available places where you can place a tile
		if(viewedPlayer==game.currentPlayerNum()) {
			for(Node q: avs) {
			
			g.drawImage(q.getImg(), q.getX()-(q.getSize()/2), q.getY()-(q.getSize()/2), q.getSize(), q.getSize(), null);
		}
		}
		
		
		
		int c = (Integer.parseInt(first4nodes.get(4).substring(0,1))-1);
		if(c!=-1) {
			try { // draws selected node, if there is one
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60), (width/2+50), (height*0.75)+50);
				g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(c))), width/2, (int)(height*0.75), 100, 100, null);
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60)*-1, (width/2+50), (height*0.75)+50);
			} catch (IOException e) {
			}
		}
		
		g.setColor(Color.gray);
		for(int i=0; i<Math.min(4, first4nodes.size()); i++) { // draw animal tokens & tiles
			
			if((Integer.parseInt(first4animals.substring(4, 5))-1)==i) {
				g.setColor(Color.cyan); // shows if an animal token is selected
				g.fillRect((int)(1600*w)-5, 45+(150*i), (int)(110*w), (int)(110*h));
				g.setColor(Color.gray);
			}
			
			if(first4animals.charAt(i) == 'f') {
				g.drawImage(fox, (int)(1600*w), 50+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 's') {
				g.drawImage(salmon, (int)(1600*w), 50+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 'e') {
				g.drawImage(elk, (int)(1600*w), 50+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 'h') {
				g.drawImage(hawk, (int)(1600*w), 50+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 'b') {
				g.drawImage(bear, (int)(1600*w), 50+(150*i), (int)(100*w), (int)(100*h), null);
			}
			if(customOvp.contains((i+1)+"")){
				g.fillRect((int)(1525*w), 50+(150*i), 50, 50);
			}
			try {
				if(!first4nodes.get(i).equals("null")) {
					g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(i))), (int)(1400*w), 50+(150*i), (int)(100*w), (int)(100*h), null);
				}
				
			} catch (IOException e) {
				System.out.println("Error");
			}
			
		}
		
		g.setColor(Color.green); g.fillRect(5, (int)(height*0.85)-35, 150, 100); // rotate button
		g.setColor(Color.red); g.fillRect(200, (int)(height*0.85)-35, 150, 100); // end turn button
		g.setFont(new Font("SANS SERIF", 1, 35));
		g.setColor(Color.black);
		
		g.drawString("Rotate", 5, (int)(height*0.85));
		g.drawString("End Turn", 200, (int)(height*0.85));
		g.drawString("Player #" + (game.currentPlayerNum()+1) + "'s turn", 10, 35);
		g.drawString("Viewing Player #" + (viewedPlayer+1), 10, 75);
		g.drawString("Turns left for all players = " + turnsLeft, 10, 115);
		g.setFont(new Font("SANS SERIF", 1, 15));
		g.drawString("Press 1-3 to see other player's boards.", 10, 140);
		
		if(spent!='n') { // buttons to be shown if a nature token is spend
			g.setColor(Color.green);
			g.fillRect((int)(1400*w), (int)(900*h), (int)(160*w), spent=='s'?45:30);
			g.fillRect((int)(1400*w), (int)(970*h), (int)(160*w), spent=='o'?45:30);
			g.setColor(Color.black);
			g.setFont(new Font("SANS SERIF", 1, (int)(15*w)));
			g.drawString("Pick seperate tokens", (int)(1400*w), (int)(920*h));
			g.setFont(new Font("SANS SERIF", 1, (int)(20*w)));
			g.drawString("Overpopulate", (int)(1400*w), (int)(990*h));
		}
		
		
		if(threeAnimals()!='n' && !op3) { // shows button to overpopulate if 3 animals are the same
			g.setColor(Color.yellow);
			g.fillRect(1450, 625, 200, 80);
			g.setFont(new Font("SANS SERIF", 1, 30));
			g.setColor(Color.black);
			g.drawString("Overpopulate", 1450, 665);
		}
		
		//g.drawImage(acorn, (int)(width*0.83), (int)(height*0.84), 50, 50, null);
		g.drawImage(acorn, (int)(1600*w), (int)(900*h), (int)(50*w), (int)(50*h), null);
		g.setFont(new Font("SANS SERIF", 1, 25));
		//g.drawString(game.currentPlayer().getTokens() + "", (int)(width*0.83), (int)(height*0.84));
		g.drawString(game.currentPlayer().getTokens() + "", (int)(1600*w), (int)(900*h));
		
		
		
	}                                                        // end of painting
	
	public char threeAnimals() {
		for(char q: "fsbeh".toCharArray()) {
			if(first4animals.contains(q + "")) {
				if(first4animals.substring(0, first4animals.lastIndexOf(q)).indexOf(q) !=
					first4animals.substring(0, first4animals.lastIndexOf(q)).lastIndexOf(q)) {
				return q;
				}
			}
		}
		return 'n';
	}
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mouseClicked(MouseEvent e) {
		
		System.out.println(e.getX() + " " + e.getY());
		
		
		if(game.currentPlayerNum() != viewedPlayer || turnsLeft<=0) return; // always first
		
		
		
		if(1450 <= e.getX() && e.getX() <= 1650 && 625 <= e.getY() && e.getY() <= 825 && !op3) { // overpopulate if 3 animals
			char c = threeAnimals();
			for(int i=0; i<4; i++) {
				if(first4animals.charAt(i)==c) {
			first4animals = first4animals.substring(0,i) + game.randomAnimal() + 
					first4animals.substring(i+1);
				}
			}
			op3 = true;
			repaint();
			return;
		}
		
		//g.fillRect((int)(1400*w), (int)(900*h), (int)(160*w), spent=='s'?45:30);
		//g.fillRect((int)(1400*w), (int)(970*h), (int)(160*w), spent=='o'?45:30);
		if(1400*w <= e.getX() && e.getX() <= 1560*w) {
			if(900*h <= e.getY() && e.getY() <= 930*h && customOvp.equals("")) { // click to pick separate tokens
				spent = 's';
				repaint();
				return;
			} else if (970*h <= e.getY() && e.getY() <= 1000*h && first4animals.charAt(4)=='0') { // click to overpopulate
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
		

		if(1600*w <= e.getX() && e.getX() <= 1650*w && 900*h <= e.getY() && e.getY() <= 950*h && !placed && !aplaced) { // clicking acorn
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
		
		if(5 <= e.getX() && e.getX() <= 155 && (int)(height*0.85)-35 <= e.getY() 
				&& e.getY() <= (int)(height*0.85)+115) { // rotating selected tile
			first4nodes.set(4, first4nodes.get(4).charAt(0)
					+ "" + (Integer.parseInt(first4nodes.get(4).substring(1))+1) + "");
			if(first4nodes.get(4).charAt(1)=='7') {
				first4nodes.set(4, first4nodes.get(4).charAt(0) + "" + 1 + "");
			}
			repaint();
			return;
		}
		
		
		if(200 <= e.getX() && e.getX() <= 350 && (int)(height*0.85)-35 <= e.getY() 
				&& e.getY() <= (int)(height*0.85)+115 && placed) { // ending turn
			avs.clear();
			first4nodes.set(4, "01");
			first4animals = first4animals.substring(0,4) + "0";
			placed = false;
			aplaced = false;
			op3 = false;
			spent = 'n';
			turnsLeft--;
			// make end condition if turnsLeft = 0;
			game.endTurn();
			viewedPlayer = game.currentPlayerNum();
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
			if((1600*w) <= e.getX() && e.getX() <= (1700*w) && 50+(150*i) <= e.getY() && e.getY() <= 150+(150*i)) { // selecting an animal token
				if(spent == 's') {
				if(first4animals.charAt(4)-'0' == (i+1)) first4animals = first4animals.substring(0, 4) + "0";
				else first4animals = first4animals.substring(0, 4) + (i+1);
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

			if((1400*w) <= e.getX() && e.getX() <= (1500*w) && 50+(150*i) <= e.getY() && e.getY() <= 150+(150*i) && !placed) { // selecting a tile
				
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
	
	public void keyTyped(KeyEvent e) {
		if(1 <= e.getKeyChar()-'0' && e.getKeyChar()-'0' <= 3) {
			viewedPlayer = (e.getKeyChar()-'0')-1;
			repaint();
			return;
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
/* ------------------------------------------------------------ NOTES
*
* Major stuff happens in mouseClicked(), paint(), constructor, and keyTyped()
* x and y in the node class specify the CENTER of the node, not the top left.
* A negative y value means the node is going UP, not down. Same with positive y values.
*
*
*
*/

/* ------------------------------------------------------------ NOTES
 * 
 * Major stuff happens in mouseClicked()
 * x and y in the node class specify the CENTER of the node, not the top left.
 * A negative y value means the node is going UP, not down. Same with positive y values.
 * 
 * 
 * 
 */
