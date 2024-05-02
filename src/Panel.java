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
import java.io.File;
import java.io.FileNotFoundException;
public class Panel extends JPanel implements MouseListener, KeyListener{
	
	Game game; // the game
	
	ArrayList<Node> avs; // spots where the player can place a tile
	
	public static int[] xcords = {80, 40, -40, -80, -40, 40};
	
	public static int[] ycords = {0, 60, 60, 0, -60, -60};
	
	ArrayList<String> first4nodes = new ArrayList<String>();
	
	String first4animals = ""; // number at end is selected animal, 0 is no animal
	
	char spent; // 'n' = not spent, 'p' = spent, pending selection, 's' = spent, select separate, 'o' = spent, overpopulate
	
	boolean placed, aplaced, op3; // if player placed a tile or not
	int[][] s = null;
	int turnsLeft = 1, viewedPlayer;
	static int offsetx, offsety;
	
	String customOvp; // used to find what animals will be replaced if a nature token
	// is spent to overpopulate
	
	BufferedImage fox = null, hawk = null, elk = null, bear = null, salmon = null, acorn = null, bg = null, scoringcard = null;
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
		offsetx = 0; offsety = 0;
		
		while(first4animals.substring(0,4).equals("hhhh") || first4animals.substring(0,4).equals("bbbb")
				|| first4animals.substring(0,4).equals("eeee") || first4animals.substring(0,4).equals("ffff")
				|| first4animals.substring(0,4).equals("ssss")) {
			first4animals = game.getFirst4Animals2() + "0";
		}
		
		
		
		try {
			game.addNodes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		first4nodes.add(game.getNode()); first4nodes.add(game.getNode());
		first4nodes.add(game.getNode()); first4nodes.add(game.getNode());
		
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
			scoringcard = ImageIO.read(Panel.class.getResource("/assets/scoresheet.png"));
		} catch (IOException e) {
			System.out.println("Error");
		}
		// this function finds spots where the player can place a tile
		for(Node n: game.currentPlayer().getNodes()) {
			for(int i=1; i<7; i++) {
				// badly optimized. don't have this in the paint function. will get called too much
					
					Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
					if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
						avs.add(a);
						
				
				}
			}
		}
		addMouseListener(this);
		addKeyListener(this);
	}
	
	
	public void paint(Graphics g) { //                              begin painting
		
		g.drawImage(bg, 0, 0, 1920, 1080, null);
		g.setFont(new Font("SANS SERIF", 1, 16));
		
		
		if(viewedPlayer==game.currentPlayerNum() && first4nodes.get(4).charAt(0)!='0' && !placed) {
			for(Node q: avs) {
				if(150 < q.getX()+offsetx && q.getX()+offsetx < 1400 &&
						150 < q.getY()+offsety && q.getY()+offsety < 900) 
					g.drawImage(q.getImg(), q.getX()-(q.getSize()/2)+offsetx, q.getY()-(q.getSize()/2)+offsety, q.getSize(), q.getSize(), null);
		}
		}
		
		// to draw nodes & animals
		Graphics2D g2 = (Graphics2D) g;
		// this draws the available places where you can place a tile
		g.setColor(Color.black);
		
		for(Node n: game.getPlayer(viewedPlayer).getNodes()) {
			
			
			
			g2.rotate(Math.toRadians((n.getRot()-1)*60), n.getX()+offsetx, n.getY()+offsety);
			if(150 < n.getX()+offsetx && n.getX()+offsetx < 1400 &&
					150 < n.getY()+offsety && n.getY()+offsety < 900) {
				g2.drawImage(n.getImg(), n.getX()-(n.getSize()/2)+offsetx, n.getY()-(n.getSize()/2)+offsety, n.getSize(), n.getSize(), null);
			}
			
			g2.rotate(Math.toRadians((n.getRot()-1)*60)*-1, n.getX()+offsetx, n.getY()+offsety);
			
			if(n.getAnimal()!='n' && 150 < n.getX()+offsetx && n.getX()+offsetx < 1400 &&
					150 < n.getY()+offsety && n.getY()+offsety < 900) {
				g.fillOval(n.getX()-23+offsetx, n.getY()-18+offsety, 46, 46);
				switch (n.getAnimal()) {
				case 'f': 
					
					g.drawImage(fox, n.getX()-20+offsetx, n.getY()-15+offsety, 40, 40, null);
				break;
				case 'b': 
					g.drawImage(bear, n.getX()-20+offsetx, n.getY()-15+offsety, 40, 40, null);
				break;
				case 's': 
					g.drawImage(salmon, n.getX()-20+offsetx, n.getY()-15+offsety, 40, 40, null);
				break;
				case 'e': 
					g.drawImage(elk, n.getX()-20+offsetx, n.getY()-15+offsety, 40, 40, null);
				break;
				case 'h': 
					g.drawImage(hawk, n.getX()-20+offsetx, n.getY()-15+offsety, 40, 40, null);
				break;
				}
				
			}
			
			if(first4nodes.get(4).charAt(0)!='0' && !placed) {
				/*// this function finds spots where the player can place a tile
				for(int i=1; i<7; i++) {
					// badly optimized. don't have this in the paint function. will get called too much
						
						Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
						if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
							avs.add(a);
							
					
					}
				}*/
			}
			
		}
		
		
		
		
		
		/*g.drawImage(bg2,0,0,175,1080,null);
		g.drawImage(bg2,0,0,1920,150,null);
		g.drawImage(bg2,0,1080-225,1920,225,null);
		g.drawImage(bg2,1920-550,0,550,1080,null);*/
		g.setColor(new Color(200, 209, 174));
		g.fillRect(0,0,175,1080);
		g.fillRect(0,0,1920,150); // border
		g.fillRect(0,1080-225,1920,225); // border
		g.fillRect(1920-550,0,550,1080);
		
		
		g.setColor(Color.gray);
		for(int i=0; i<Math.min(4, first4nodes.size()); i++) { // draw animal tokens & tiles
			
			if((Integer.parseInt(first4animals.substring(4, 5))-1)==i) {
				g.setColor(Color.cyan); // shows if an animal token is selected
				g.fillRect((int)(1700*w)-5, 170+(150*i), (int)(110*w), (int)(110*h));
				g.setColor(Color.gray);
			}
			
			if(first4animals.charAt(i) == 'f') {
				g.drawImage(fox, (int)(1700*w), 175+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 's') {
				g.drawImage(salmon, (int)(1700*w), 175+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 'e') {
				g.drawImage(elk, (int)(1700*w), 175+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 'h') {
				g.drawImage(hawk, (int)(1700*w), 175+(150*i), (int)(100*w), (int)(100*h), null);
			} else if (first4animals.charAt(i) == 'b') {
				g.drawImage(bear, (int)(1700*w), 175+(150*i), (int)(100*w), (int)(100*h), null);
			}
			if(customOvp.contains((i+1)+"")){
				g.fillRect((int)(1675*w), 175+(150*i), 50, 50);
			}
			try {
				if(!first4nodes.get(i).equals("null")) {
					g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(i))), (int)(1500*w), 175+(150*i), (int)(100*w), (int)(100*h), null);
				}
				
			} catch (IOException e) {
				System.out.println("Error");
			}
			
		}
		
		
		
		int c = (Integer.parseInt(first4nodes.get(4).substring(0,1))-1);
		if(c!=-1) {
			try { // draws selected node, if there is one
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60), (width/2+50), (height*0.8)+50);
				g.drawImage(ImageIO.read(Panel.class.getResource("/assets/"+ first4nodes.get(c))), width/2, (int)(height*0.8), 100, 100, null);
				g2.rotate(Math.toRadians((Integer.parseInt(first4nodes.get(4).substring(1))-1)*60)*-1, (width/2+50), (height*0.8)+50);
			} catch (IOException e) {
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
		g.drawString("Press 1-3 to see other player's boards.", 520, 35);
		g.drawString("Use WASD to move your board around, and Space to center your board.", 520, 65);
		if(spent=='o' && !customOvp.equals("")) g.drawString("Press the overpopulate button again to overpopulate", 520, 95);
		if(turnsLeft<=0) g.drawString("scoring", 520, 125);
		
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
			g.fillRect((int)(1400*w), (int)(825*h), (int)(200*w), (int)(80*h));
			g.setFont(new Font("SANS SERIF", 1, (int)(30*w)));
			g.setColor(Color.black);
			g.drawString("Overpopulate", (int)(1400*w), (int)(845*h));
		}
		
		//g.drawImage(acorn, (int)(width*0.83), (int)(height*0.84), 50, 50, null);
		g.drawImage(acorn, (int)(1600*w), (int)(900*h), (int)(50*w), (int)(50*h), null);
		g.setFont(new Font("SANS SERIF", 1, 25));
		//g.drawString(game.currentPlayer().getTokens() + "", (int)(width*0.83), (int)(height*0.84));
		g.drawString(game.currentPlayer().getTokens() + "", (int)(1600*w), (int)(900*h));
		
		
		if(turnsLeft<=0) {
			g.setFont(new Font("SERIF", 1, 15));
			g.drawImage(scoringcard, 0, 0, 188, 406, null);
			if(s==null) s = game.scoring2();
			g.drawString("P1", 55, 55-23);
			g.drawString("P2", 55+28, 55-23);
			g.drawString("P3", 55+56, 55-23);
			for (int s1=0; s1<5; s1++) { // set s.length to 11 or vice versa
				for(int s2=0; s2<s[s1].length; s2++) {
					g.drawString(s[s1][s2]+"", 55+(30*s2), 48+(23*s1));
				}
			}
			for (int s1=5; s1<11; s1++) {
				for(int s2=0; s2<s[s1].length; s2++) {
					g.drawString(s[s1][s2]+"", 55+(30*s2), 52+(24*s1));
				}
			}
			for (int s1=11; s1<16; s1++) {
				for(int s2=0; s2<s[s1].length; s2++) {
					g.drawString(s[s1][s2]+"", 70+(30*s2), 198+(25*(s1-11)));
				}
			}
			g.drawString(s[16][0]+"", 52, 318);
			g.drawString(s[16][1]+"", 52+32, 318);
			g.drawString(s[16][2]+"", 52+64, 318);
			g.drawString(s[17][0]+"", 52, 348);
			g.drawString(s[17][1]+"", 52+32, 348);
			g.drawString(s[17][2]+"", 52+64, 348);
			g.drawString(s[18][0]+"", 52, 378);
			g.drawString(s[18][1]+"", 52+32, 378);
			g.drawString(s[18][2]+"", 52+64, 378);
			return;
		}
		
		
		
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
		
		//System.out.println(e.getX() + " " + e.getY());
		//System.out.println(e.getX()-offsetx + " " + (e.getY()-offsety));
		
		if(game.currentPlayerNum() != viewedPlayer || turnsLeft<=0) return; // always first
		
		
		
		if(1400*w <= e.getX() && e.getX() <= 1600*w && 825*h <= e.getY() && e.getY() <= 905*h && !op3) { // overpopulate if 3 animals
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
		
		if(5 <= e.getX() && e.getX() <= 155 && (int)(height*0.85)-35 <= e.getY() // rotating selected tile
				&& e.getY() <= (int)(height*0.85)+115) { 
			first4nodes.set(4, first4nodes.get(4).charAt(0)
					+ "" + (Integer.parseInt(first4nodes.get(4).substring(1))+1) + "");
			if(first4nodes.get(4).charAt(1)=='7') {
				first4nodes.set(4, first4nodes.get(4).charAt(0) + "" + 1 + "");
			}
			repaint();
			return;
		}
		
		
		if(200 <= e.getX() && e.getX() <= 350 && (int)(height*0.85)-35 <= e.getY() // ending turn
				&& e.getY() <= (int)(height*0.85)+115 && placed) { 
			avs.clear();
			offsetx = 0; offsety = 0;
			first4animals = first4animals.substring(0,4) + "0";
			placed = false; aplaced = false; op3 = false;
			spent = 'n'; turnsLeft--; 
			game.endTurn();
			viewedPlayer = game.currentPlayerNum();
			for(Node n: game.currentPlayer().getNodes()) {
				for(int i=1; i<7; i++) {
					// badly optimized. don't have this in the paint function. will get called too much
						
						Node a = new Node(n.getX() + xcords[i-1], n.getY() + ycords[i-1], "available.png", 15);
						if(n.getNearbyNode(i) == null && !avs.toString().contains(a.toString())) {
							avs.add(a);
							
					
					}
				}
			}
			repaint();
			return;
		}
		
		if(!first4animals.substring(4).equals("0")) { // placing animal token if one is selected
			if(first4nodes.get(4).charAt(0) == '0' || spent == 's'
				|| first4nodes.get(4).charAt(0) == first4animals.charAt(4)) { // check if adjacent or nature token spent
				for(Node n: game.currentPlayer().getNodes()) {
					if(n.isClicked((e.getX()-offsetx), (e.getY()-offsety))) {
						if(n.getAvailable().indexOf(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1)) != -1 && !aplaced && placed
								&& n.getAnimal() == 'n') {
							n.setAnimal(first4animals.charAt(Integer.parseInt(first4animals.substring(4, 5))-1));
							if(n.getEdges().equals("mmmmmm") || n.getEdges().equals("wwwwww") || n.getEdges().equals("ffffff")
									|| n.getEdges().equals("rrrrrr") || n.getEdges().equals("pppppp")) {
									game.currentPlayer().setTokens(game.currentPlayer().getTokens()+1);
							}//
							aplaced = true;
							if(spent=='s') spent = 'n';
							first4animals = first4animals.substring(0, (Integer.parseInt(first4animals.substring(4, 5))-1))
									+ game.randomAnimal() + first4animals.substring((Integer.parseInt(first4animals.substring(4, 5))));
							first4animals = first4animals.substring(0, 4) + "0";
							while(first4animals.substring(0,4).equals("hhhh") || first4animals.substring(0,4).equals("bbbb")
									|| first4animals.substring(0,4).equals("eeee") || first4animals.substring(0,4).equals("ffff")
									|| first4animals.substring(0,4).equals("ssss")) {
								first4animals = game.getFirst4Animals2() + "0";
							}
							//System.out.println(first4animals);
						
						}
						repaint();
						return;
					}
				}
			}
		}
		
		for(Node a: avs) { // placing a tile if one is selected
			
			if(a.isClicked(e.getX()-offsetx, e.getY()-offsety) && !first4nodes.get(4).substring(0,1).equals("0") && !placed) {
				if(150 < a.getX()+offsetx && a.getX()+offsetx < 1550 &&
						150 < a.getY()+offsety && a.getY()+offsety < 900) {
					
				Node n = new Node(a.getX(), a.getY(), first4nodes.get(Integer.parseInt(first4nodes.get(4).substring(0,1))-1), 75);
				game.currentPlayer().addNode(n);
				while(n.getRot() != (Integer.parseInt(first4nodes.get(4).substring(1)))){
					n.rotate();
				}
				
				first4nodes.set(Integer.parseInt(first4nodes.get(4).substring(0,1))-1, game.getNode());
				placed = true;
				avs.clear();
				first4nodes.set(4, "01");
				repaint();
				return;
				}
			}
			
		}
		
		for(int i=0; i<4; i++) {
			if((1700*w) <= e.getX() && e.getX() <= (1800*w) && 175+(150*i) <= e.getY() && e.getY() <= 275+(150*i)) { // selecting an animal token
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

			if((1500*w) <= e.getX() && e.getX() <= (1600*w) && 175+(150*i) <= e.getY() && e.getY() <= 275+(150*i) && !placed) { // selecting a tile
				
				if(Integer.parseInt(first4nodes.get(4).substring(0,1)) == (i+1)) {
					first4nodes.set(4, "01");
					if(spent != 's') first4animals = first4animals.substring(0, 4) + "0";
				}
				else {
					first4nodes.set(4, (i+1) + "1");
					if(spent != 's') first4animals = first4animals.substring(0, 4) + (i+1);
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
		else {

			switch (e.getKeyChar()) {
			case 'w': offsety -= 60;// W
			break;
			case 'a': offsetx -= 80;// A
			break;
			case 's': offsety += 60;// S
			break;
			case 'd': offsetx += 80;// D
			break;	
			case ' ': // Space
			offsetx = 0; 
			offsety = 0;
			break;
			/*case 'q': // for testing purposes
			int[][] s = game.scoring2();
			for(int[] s1: s) {
				for(int s2: s1) {
					System.out.print(s2 + " ");
				}
				System.out.println("");
			}
			break;
			case 'e': // for testing purposes
				for(Node n: game.currentPlayer().getNodes()) {
					System.out.println(n.getEdges());
					System.out.println(game.terrainCount('w', n));
					System.out.println(n.getEdges());
				}
			break;*/
			}
			repaint();
			return;
		}
	}
	
	public ArrayList<Player> whoWon(int[][] scores){
		return null;
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
