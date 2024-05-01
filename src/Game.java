import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Game {

	private Player [] players;
	private int currNatureToken;
	private int currentPlayer = 0;
	private ArrayList<String> allNodes;
	private String [][] startingNodes;
	private ArrayList<Character> animals; // might not need this anymore
	private String scoringCards;
	private ArrayList<Character> removedAnimals = new ArrayList<Character> (); //The animals removed from the animal arrayList
	private int pos = (int) Math.random(); //This will be used when trying to repopulate the board
	
	//The constructor that sets up everything for the GameBoard
	public Game(int currentPlayer, String scoringCards) { 
		allNodes = new ArrayList<String>();
		players = new Player[3];
		this.currentPlayer = currentPlayer;
		this.scoringCards = scoringCards;
		//Set startingNodes to be a 5 by 3 matrix that adds the tiles from those placed as starter tiles
		startingNodes = new String [5][3];
		//Prepare as the starting tiles will be manually put in
		
		/* STARTING TILE ONE*/
		startingNodes[0][0] = "wwwwww-h.png"; //river w/ hawk key
		startingNodes[0][1] = "fffrrr-ehs.png"; //river & forest w/ fish, elk, and hawk
		startingNodes[0][2] = "pmmmpp-bf.png"; //prairie & mountain w/ bear and fox

		/* STARTING TILE TWO*/
		startingNodes[1][0] = "ffffff-e.png"; //forest w/ elk key
		startingNodes[1][1] = "rrrmmm-beh.png";
		startingNodes[1][2] = "pppwww-fs.png";
		
		/* STARTING TILE THREE*/
		startingNodes[2][0] = "wwwwww-h.png";
		startingNodes[2][1] = "rrrwww-fsh.png";
		startingNodes[2][2] = "fmmmff-be.png";
		/* STARTING TILE FOUR*/
		startingNodes[3][0] = "mmmmmm-b.png"; //mountain with bear key
		startingNodes[3][1] = "wwwfff-hef.png";// wetland & forest w/ hawk, elk, and fox
		startingNodes[3][2] = "rppprr-bs.png"; //river & prairie w/ fish and bear
		/* STARTING TILE FIVE*/
		startingNodes[4][0] = "rrrrrr-s.png"; //river w/ salmon key
		startingNodes[4][1] = "fffppp-bes.png"; // forest & prairie w/ salmon, bear, and elk
		startingNodes[4][2] = "mwwwmm-fh.png"; //mountain & wetland w/ fox and hawk
		
		
		int row1 = (int) (Math.random()*5);
		int row2 = (int) (Math.random()*5);
		while(row2==row1) {
			row2 = (int) (Math.random()*5);
		}
		int row3 = (int) (Math.random()*5);
		while(row3==row1 || row3==row2) {
			row3 = (int) (Math.random()*5);
		}

		
		players[0] = new Player();
		players[0].addNode(new Node(500, 300, startingNodes[row1][0], 75));
		players[0].addNode(new Node(460, 360, startingNodes[row1][1], 75));
		players[0].addNode(new Node(540, 360, startingNodes[row1][2], 75));
		
		players[1] = new Player();
		players[1].addNode(new Node(500, 300, startingNodes[row2][0], 75));
		players[1].addNode(new Node(460, 360, startingNodes[row2][1], 75));
		players[1].addNode(new Node(540, 360, startingNodes[row2][2], 75));
		
		players[2] = new Player();
		players[2].addNode(new Node(500, 300, startingNodes[row3][0], 75));
		players[2].addNode(new Node(460, 360, startingNodes[row3][1], 75));
		players[2].addNode(new Node(540, 360, startingNodes[row3][2], 75));
		
	}
	
	public String getNode() {
		if(allNodes.size()==0) return "null";
		int c = (int)(Math.random()*allNodes.size());
		String ret = allNodes.get(c);
		allNodes.remove(c);
		return ret;
	}
	public int [][] scoring(){
		//ANIMAL COUNTING
		int bearC = 0;
		int elkC = 0;
		int salmonC = 0;
		int foxC = 0;
		int hawkC = 0;
		
		//TERRAIN COUNTING
		int mountainC = 0;
		int forestC = 0;
		int prairieC = 0;
		int wetlandC = 0;
		int riverC = 0;
		
		for(int i = 0; i < players.length; i++) {
			
			for(Node j: players[i].getNodes()) {
				if(j.getAnimal() == 'b') {
					// rework, use getNearbyAnimals() in Node class
					if(j.getNearbyNode(1).getAnimal() == 'b') { 
						bearC++;
					}
					else if(j.getNearbyNode(2).getAnimal() == 'b') {
						bearC++;
					}
					else if(j.getNearbyNode(3).getAnimal() == 'b') {
						bearC++;
					}
					else if(j.getNearbyNode(4).getAnimal() == 'b') {
						bearC++;
					}
					else if(j.getNearbyNode(5).getAnimal() == 'b') {
						bearC++;
					}
					else if (j.getNearbyNode(6).getAnimal() == 'b') {
						bearC++;
					}
					j.setAnimal('n');
				}
				
				else if(j.getAnimal() == 'e') {
					int elkRun = 0;
					while(elkRun < players[i].getNodes().size()) {
						if(j.getNearbyNode(1).getAnimal() == 'e') {
							elkC++; 
							elkRun++;
							j.setAnimal('n'); 
						}
						else if(j.getNearbyNode(2).getAnimal() == 'e') {
							elkC++;
							elkRun++;
							j.setAnimal('n');
						}
						else if (j.getNearbyNode(3).getAnimal() == 'e') {
							elkC++;
							elkRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(4).getAnimal() == 'e') {
							elkC++;
							elkRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(5).getAnimal() == 'e') {
							elkC++;
							elkRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(6).getAnimal() == 'e') {
							elkC++;
							elkRun++;
							j.setAnimal('n');
						}
						j.setAnimal('n');
					}
				}
				else if(j.getAnimal() == 's') {
					int fishRun = 0;
					while(fishRun < players[i].getNodes().size()) { 
						if(j.getNearbyNode(1).getAnimal() == 's') {
							salmonC++;
							fishRun++;
							j.setAnimal('n'); 
						}
						else if(j.getNearbyNode(2).getAnimal() == 's') {
							salmonC++;
							fishRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(3).getAnimal() == 's') {
							salmonC++;
							fishRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(4).getAnimal() == 's') {
							salmonC++;
							fishRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(5).getAnimal() == 's') {
							salmonC++; 
							fishRun++;
							j.setAnimal('n');
						}
						else if(j.getNearbyNode(6).getAnimal() == 's') {
							salmonC++;
							fishRun++;
							j.setAnimal('n');
						}
						j.setAnimal('n'); 
					}
				}
				
				else if(j.getAnimal() == 'f') {
						int differ = 0; 
						while(differ < players[i].getNodes().size()) {
							if(j.getNearbyNode(1).getAnimal() == 'f') {
								differ++;
								foxC++;
								j.setAnimal('n');
							}
							else if(j.getNearbyNode(2).getAnimal() == 'f') {
								differ++;
								foxC++;
								j.setAnimal('n');
							}
							else if (j.getNearbyNode(3).getAnimal() == 'f') {
								differ++;
								foxC++;
								j.setAnimal('n');
							}
							else if(j.getNearbyNode(4).getAnimal() == 'f') {
								differ++;
								foxC++;
								j.setAnimal('n');
							}
							else if(j.getNearbyNode(5).getAnimal() == 'f') {
								differ++;
								foxC++;
								j.setAnimal('n');
							}
							else if (j.getNearbyNode(6).getAnimal() == 'f') {
								differ++;
								foxC++;
								j.setAnimal('n');
							}
							j.setAnimal('n');  
						}
				}
				
				else if(j.getAnimal() == 'h') {
					int isNotAdjacent = 0;
					int hawkRun = 0;
					while(hawkRun < players[i].getNodes().size()) {
						if(j.getNearbyNode(1).getAnimal() != 'h') {
							isNotAdjacent++;
							hawkRun++;
							j.setAnimal('n');
						}
						if(j.getNearbyNode(2).getAnimal() != 'h') {
							isNotAdjacent++;
							hawkRun++;
							j.setAnimal('n');
						}
						if(j.getNearbyNode(3).getAnimal() != 'h') {
							isNotAdjacent++;
							hawkRun++;
							j.setAnimal('n');
						}
						if(j.getNearbyNode(4).getAnimal() != 'h') {
							isNotAdjacent++;
							hawkRun++;
							j.setAnimal('n');
						}
						if(j.getNearbyNode(5).getAnimal() != 'h') {
							isNotAdjacent++;
							hawkRun++;
							j.setAnimal('n');
						}
						if(j.getNearbyNode(6).getAnimal() != 'h') {
							isNotAdjacent++;
							hawkRun++;
							j.setAnimal('n');
						}
						if(isNotAdjacent == 6) {
							hawkC++;
						}
						j.setAnimal('n');
					}
				}
				
				//the loop for scoring the terrain of the player
				for(int k = 0; k < players.length; k++) {
					for(Node t: players[k].getNodes()) {
						int forestRun  = 0; int mountainRun = 0; int prairieRun = 0; int wetlandRun = 0; int riverRun = 0; 
						if(t.getNearbyNode(1).getEdges().equals('f')) {
							forestRun++;
							forestC++;
							t.setEdges("n" + t.getEdges().substring(1));
						}
						else if(t.getNearbyNode(2).getEdges().equals('f')) {
							forestRun++;
							forestC++;
							t.setEdges("n" + t.getEdges().substring(2));
						}
						else if(t.getNearbyNode(3).getEdges().equals('f')) {
							forestRun++;
							forestC++;
							t.setEdges("n" + t.getEdges().substring(3));
						}
						else if(t.getNearbyNode(4).getEdges().equals('f')) {
							forestRun++;
							forestC++;
							t.setEdges("n" + t.getEdges().substring(4));
						}
						else if (t.getNearbyNode(5).getEdges().equals('f')) {
							forestRun++;
							forestC++;
							t.setEdges("n" + t.getEdges().substring(5));
						}
						else if (t.getNearbyNode(6).getEdges().equals('f')) {
							forestRun++;
							forestC++;
							t.setEdges("n" + t.getEdges().substring(6));
						}
						
						//mountain counting
						else if(t.getNearbyNode(1).equals('m')) {
							mountainRun++;
							mountainC++;
							t.setEdges("n" + t.getEdges().substring(1));
						}
						else if (t.getNearbyNode(2).equals('m')) {
							mountainRun++;
							mountainC++;
							t.setEdges("n" + t.getEdges().substring(2));
						}
						else if (t.getNearbyNode(3).equals('m')) {
							mountainRun++;
							mountainC++;
							t.setEdges("n" + t.getEdges().substring(3));
						}
						else if(t.getNearbyNode(4).equals('m')) {
							mountainRun++;
							mountainC++;
							t.setEdges("n" + t.getEdges().substring(4));
						}
						else if (t.getNearbyNode(5).equals('m')) {
							mountainRun++;
							mountainC++;
							t.setEdges("n" + t.getEdges().substring(5));
						}
						else if (t.getNearbyNode(6).equals('m')) {
							mountainRun++;
							mountainC++;
							t.setEdges("n" + t.getEdges().substring(6));
						}
						//prairie count
						else if (t.getNearbyNode(1).equals('p')) {
							prairieRun++;
							prairieC++;
							t.setEdges("n" + t.getEdges().substring(1));
						}
						else if (t.getNearbyNode(2).equals('p')) {
							prairieRun++;
							prairieC++;
							t.setEdges("n" + t.getEdges().substring(2));
						}
						else if (t.getNearbyNode(3).equals('p')) {
							prairieRun++;
							prairieC++;
							t.setEdges("n" + t.getEdges().substring(3));
						}
						else if (t.getNearbyNode(4).equals('p')) {
							prairieRun++;
							prairieC++;
							t.setEdges("n" + t.getEdges().substring(4));
						}
						else if (t.getNearbyNode(5).equals('p')) {
							prairieRun++;
							prairieC++;
							t.setEdges("n" + t.getEdges().substring(5));
						}
						else if (t.getNearbyNode(6).equals('p')) {
							prairieRun++;
							prairieC++;
							t.setEdges("n" + t.getEdges().substring(6));
						}
						//wetland count
						else if (t.getNearbyNode(1).equals('w')) {
							wetlandRun++;
							wetlandC++;
							t.setEdges("n" + t.getEdges().substring(1));
						}
						else if (t.getNearbyNode(2).equals('w')) {
							wetlandRun++;
							wetlandC++;
							t.setEdges("n" + t.getEdges().substring(2));
						}
						else if (t.getNearbyNode(3).equals('w')) {
							wetlandRun++;
							wetlandC++;
							t.setEdges("n" + t.getEdges().substring(3));
						}
						else if (t.getNearbyNode(4).equals('w')) {
							wetlandRun++;
							wetlandC++;
							t.setEdges("n" + t.getEdges().substring(4));
						}
						else if (t.getNearbyNode(5).equals('w')) {
							wetlandRun++;
							wetlandC++;
							t.setEdges("n" + t.getEdges().substring(5));
						}
						else if (t.getNearbyNode(6).equals('w')) {
							wetlandRun++;
							wetlandC++;
							t.setEdges("n" + t.getEdges().substring(6));
						}
						//river count
						else if (t.getNearbyNode(1).equals('r')) {
							riverRun++;
							riverC++;
							t.setEdges("n" + t.getEdges().substring(1));
						}
						else if (t.getNearbyNode(2).equals('r')) {
							riverRun++;
							riverC++;
							t.setEdges("n" + t.getEdges().substring(2));
						}
						else if (t.getNearbyNode(3).equals('r')) {
							riverRun++;
							riverC++;
							t.setEdges("n" + t.getEdges().substring(3));
						}
						else if (t.getNearbyNode(4).equals('r')) {
							riverRun++;
							riverC++;
							t.setEdges("n" + t.getEdges().substring(4));
						}
						else if (t.getNearbyNode(5).equals('r')) {
							riverRun++;
							riverC++;
							t.setEdges("n" + t.getEdges().substring(5));
						}
						else if (t.getNearbyNode(6).equals('r')) {
							riverRun++;
							riverC++;
							t.setEdges("n" + t.getEdges().substring(6));
						}
					}
				}
			}
			return null; 
		}
		return null;
		//return bearC + elkC + foxC + salmonC + hawkC + mountainC + forestC + prairieC + wetlandC + riverC;
	}

	public int[][] scoring2(){
		int[][] scores = new int[19][3];
		// 19 rows, 3 columns
		for(int i=0; i<3; i++) {
			Player p = players[i];
			
			//                     ANIMAL SCORING
			
			// fox is row 5
			while(p.findAnimal('f')!=null) {
				Node f = p.findAnimal('f');
				scores[4][i] += f.getNearbyAnimals().indexOf("f")!=-1?1:0;
				scores[4][i] += f.getNearbyAnimals().indexOf("b")!=-1?1:0;
				scores[4][i] += f.getNearbyAnimals().indexOf("s")!=-1?1:0;
				scores[4][i] += f.getNearbyAnimals().indexOf("e")!=-1?1:0;
				scores[4][i] += f.getNearbyAnimals().indexOf("h")!=-1?1:0;
				f.setAnimal('n');
			}
			
			
			// hawk is row 4
			int hawkc = 0;
			while(p.findAnimal('h')!=null) {
				Node h = p.findAnimal('h');
				if(h.getNearbyAnimals().indexOf('h')==-1) hawkc++;
				else {
					for(Node h22: h.getNearbyNodes()) {
						if(h22!=null) if(h22.getAnimal()=='h') h22.setAnimal('n');
					}
				}
				h.setAnimal('n');
			}
			
			if(1<=hawkc && hawkc<=5)scores[3][i] = 2 + (3*(hawkc-1));
			else if (hawkc!=0) scores[3][i] = 14 + (4*(hawkc-5));
			if(scores[3][i]>26) scores[3][i] = 26;
			
			// elk is row 3
			while(p.findAnimal('e')!=null) {
				Node e = p.findAnimal('e');
				int dir = e.getNearbyAnimals().indexOf('e');
				int count = 1;
				e.setAnimal('n');
				if(dir!=-1) {
					while(e.getNearbyNode(dir+1)!=null) {
						e = e.getNearbyNode(dir+1);
						if(e.getAnimal()!='e') break;
						else {
							count++;
							e.setAnimal('n');
						}
					}
				}
				if(count==1) scores[2][i] += 2;
				else if(count==2) scores[2][i] += 5;
				else if(count==3) scores[2][i] += 9;
				else if(count==4) scores[2][i] += 13;
			}
			
			
			// salmon is row 2
			while(p.findAnimal('s')!=null) {
				Node s = p.findAnimal('s');
				int count = 1;
				if (s.getNearbyAnimals().indexOf('s')!=s.getNearbyAnimals().lastIndexOf('s')) count = 0;
				s.setAnimal('n');
				while(s.getNearbyAnimals().indexOf('s')==s.getNearbyAnimals().lastIndexOf('s') && s.getNearbyAnimals().indexOf('s')!=-1) {
					count++;
					s = s.getNearbyNode(s.getNearbyAnimals().indexOf('s')+1);
					s.setAnimal('n');
				}
				if (s.getNearbyAnimals().indexOf('s')!=s.getNearbyAnimals().lastIndexOf('s')) {
					for(int s2=0; s2<6; s2++) {
						if(s.getNearbyAnimals().charAt(s2)=='s') {
							s.getNearbyNode(s2+1).setAnimal('n');
						}
					}
				}
				if(count==1) scores[1][i] += 2;
				else if(count==2) scores[1][i] += 4;
				else if(count==3) scores[1][i] += 7;
				else if(count==4) scores[1][i] += 11;
				else if(count==5) scores[1][i] += 15;
				else if(count==6) scores[1][i] += 20;
				else if(count>=7) scores[1][i] += 26;
			}
			
			// bear is row 1
			int bpairs = 0; // pairs of bears
			while(p.findAnimal('b')!=null) {
				Node b = p.findAnimal('b');
				if(b.getNearbyAnimals().indexOf('b')==b.getNearbyAnimals().indexOf('b') && b.getNearbyAnimals().indexOf('b')!=-1) {
					if(b.getNearbyNode(b.getNearbyAnimals().indexOf('b')+1).getNearbyAnimals().indexOf('b')==
					   b.getNearbyNode(b.getNearbyAnimals().indexOf('b')+1).getNearbyAnimals().lastIndexOf('b')) {
						bpairs++;
					}
				}
				b.setAnimal('n');
			}
			switch(bpairs) {
			case 1: scores[0][i] += 4; break;
			case 2: scores[0][i] += 11; break;
			case 3: scores[0][i] += 19; break;
			case 4: scores[0][i] += 27; break;
			}
			
			// tally animal points up
			scores[5][i] = scores[4][i] + scores[3][i] + scores[2][i] + scores[1][i] + scores[0][i];
			
			//          TERRAIN SCORING
			
			
			while(p.findTerrain('m')!=null) { // mountains is row 7
				int prevSize = 0;
				Node m = p.findTerrain('m');
				ArrayList<Node> tnodes = new ArrayList<Node>();
				ArrayList<Node> ftnodes = new ArrayList<Node>();
				tnodes.add(m);
				while(tnodes.size()!=prevSize) {
					ftnodes.addAll(tnodes);
					prevSize = tnodes.size();
					for(Node tn: ftnodes) {
						for(Node tn2: terrainCount('m',tn)) if(!tnodes.contains(tn2)) tnodes.add(tn2);
					}
					ftnodes.clear();
				}
				if(tnodes.size()>scores[6][i]) scores[6][i] = tnodes.size();
			}
			
			while(p.findTerrain('f')!=null) { // forest is row 8
				int prevSize = 0;
				Node m = p.findTerrain('f');
				ArrayList<Node> tnodes = new ArrayList<Node>();
				ArrayList<Node> ftnodes = new ArrayList<Node>();
				tnodes.add(m);
				while(tnodes.size()!=prevSize) {
					ftnodes.addAll(tnodes);
					prevSize = tnodes.size();
					for(Node tn: ftnodes) {
						for(Node tn2: terrainCount('f',tn)) if(!tnodes.contains(tn2)) tnodes.add(tn2);
					}
					ftnodes.clear();
				}
				if(tnodes.size()>scores[7][i]) scores[7][i] = tnodes.size();
			}
			
			while(p.findTerrain('p')!=null) { // prairie is row 9
				int prevSize = 0;
				Node m = p.findTerrain('p');
				ArrayList<Node> tnodes = new ArrayList<Node>();
				ArrayList<Node> ftnodes = new ArrayList<Node>();
				tnodes.add(m);
				while(tnodes.size()!=prevSize) {
					ftnodes.addAll(tnodes);
					prevSize = tnodes.size();
					for(Node tn: ftnodes) {
						for(Node tn2: terrainCount('p',tn)) if(!tnodes.contains(tn2)) tnodes.add(tn2);
					}
					ftnodes.clear();
				}
				if(tnodes.size()>scores[8][i]) scores[8][i] = tnodes.size();
			}
			
			
			while(p.findTerrain('w')!=null) { // wetland is row 10
				int prevSize = 0;
				Node m = p.findTerrain('w');
				ArrayList<Node> tnodes = new ArrayList<Node>();
				ArrayList<Node> ftnodes = new ArrayList<Node>();
				tnodes.add(m);
				while(tnodes.size()!=prevSize) {
					ftnodes.addAll(tnodes);
					prevSize = tnodes.size();
					for(Node tn: ftnodes) {
						for(Node tn2: terrainCount('w',tn)) if(!tnodes.contains(tn2)) tnodes.add(tn2);
					}
					ftnodes.clear();
				}
				if(tnodes.size()>scores[9][i]) scores[9][i] = tnodes.size();
			}
			
			while(p.findTerrain('r')!=null) { // river is row 11
				int prevSize = 0;
				Node m = p.findTerrain('r');
				ArrayList<Node> tnodes = new ArrayList<Node>();
				ArrayList<Node> ftnodes = new ArrayList<Node>();
				tnodes.add(m);
				while(tnodes.size()!=prevSize) {
					ftnodes.addAll(tnodes);
					prevSize = tnodes.size();
					for(Node tn: ftnodes) {
						for(Node tn2: terrainCount('r',tn)) if(!tnodes.contains(tn2)) tnodes.add(tn2);
					}
					ftnodes.clear();
				}
				if(tnodes.size()>scores[10][i]) scores[10][i] = tnodes.size();
			}
			
			// extra points for nature tokens
			scores[17][i] = players[i].getTokens();
			
		}
		
		// extra points for terrain
		// extra points for mountain is row 12, mountain is row 7
		// extra points for river is row 16, river is row 11
		
		for(int s=11; s<16; s++) {
			int max = Math.max(Math.max(scores[s-5][0], scores[s-5][1]), scores[s-5][2]);
			if(max==scores[s-5][0] && max==scores[s-5][1] && max==scores[s-5][2]) { // all tie
				scores[s][0] = scores[s][1] = scores[s][2] = 1;
			} else if (max==scores[s-5][0] && max==scores[s-5][1]) { // 2 tie
				scores[s][0] = scores[s][1] = 2;
			} else if (max==scores[s-5][0] && max==scores[s-5][2]) { // 2 tie
				scores[s][0] = scores[s][2] = 2;
			} else if (max==scores[s-5][1] && max==scores[s-5][2]) { // 2 tie
				scores[s][1] = scores[s][2] = 2;
			} else if(max==scores[s-5][0]) { // player 1 highest
				scores[s][0] = 3;
				if(Math.max(scores[s-5][1], scores[s-5][2])==scores[s-5][1] && Math.max(scores[s-5][1], scores[s-5][2])==scores[s-5][2]) 
					continue;
				else if(Math.max(scores[s-5][1], scores[s-5][2])==scores[s-5][1]) scores[s][1] = 1;
				else if(Math.max(scores[s-5][1], scores[s-5][2])==scores[s-5][2]) scores[s][2] = 1;
			} else if(max==scores[s-5][1]) { // player 2 highest
				scores[s][1] = 3;
				if(Math.max(scores[s-5][0], scores[s-5][2])==scores[s-5][0] && Math.max(scores[s-5][0], scores[s-5][2])==scores[s-5][2]) 
					continue;
				else if(Math.max(scores[s-5][0], scores[s-5][2])==scores[s-5][0]) scores[s][0] = 1;
				else if(Math.max(scores[s-5][0], scores[s-5][2])==scores[s-5][2]) scores[s][2] = 1;
			} else if(max==scores[s-5][2]) { // player 3 highest
				scores[s][2] = 3;
				if(Math.max(scores[s-5][0], scores[s-5][1])==scores[s-5][0] && Math.max(scores[s-5][0], scores[s-5][1])==scores[s-5][1]) 
					continue;
				else if(Math.max(scores[s-5][0], scores[s-5][1])==scores[s-5][0]) scores[s][0] = 1;
				else if(Math.max(scores[s-5][0], scores[s-5][1])==scores[s-5][1]) scores[s][1] = 1;
			} 
		}
		
		// totals for terrain
		
		for(int q=0; q<3; q++) {
			scores[16][q] = scores[11][q] + scores[12][q] + scores[13][q] + scores[14][q] + scores[15][q]
					+ scores[6][q] + scores[7][q] + scores[8][q] + scores[9][q] + scores[10][q];
		}
		
		// tally everything up
		
		return scores;
	}
	
	public ArrayList<Node> terrainCount(char t, Node n) { // function for counting terrain. maybe recursive
		ArrayList<Node> countedNodes = new ArrayList<Node>();
		// checks all side for a node with same terrain on adjacent side, then puts node in countedNodes
		for(int i=1; i<7; i++) {
			if(n.getNearbyNode(i)!=null) {
			int other = i+3>6?i-3:i+3;
			if(n.getEdges().charAt(i-1)==t && 
					n.getNearbyNode(i).getEdges().charAt(other-1)==t) {
				if(!countedNodes.contains(n.getNearbyNode(i)))countedNodes.add(n.getNearbyNode(i));
				n.setEdges(n.getEdges().substring(0, i-1) + 'n' + n.getEdges().substring(i));
				n.getNearbyNode(i).setEdges(n.getNearbyNode(i).getEdges().substring(0, other-1) 
						+ 'n' + n.getNearbyNode(i).getEdges().substring(other));
			}
			}
			if(n.getEdges().charAt(i-1)==t) n.setEdges(n.getEdges().substring(0, i-1) + 'n' + n.getEdges().substring(i));
		}
		return countedNodes;
	}
	
	
	public Player currentPlayer() {
		return players[currentPlayer];
	}
	
	public int currentPlayerNum() {
		return currentPlayer;
	}
	public void endTurn() {
		currentPlayer++;
		if(currentPlayer == 3) {
			currentPlayer = 0;
		}
	}
	//This method is of type void due to only adding the different cards into the ArrayList of allNodes
	public  void addNodes() throws FileNotFoundException { 
		
		
		Scanner T = new Scanner(new File("tiles.dat"));
		ArrayList<String> Tokens = new ArrayList<String>();
		while(T.hasNextLine()) {
			Tokens.add(T.nextLine());
		}
		for(int i = 0; i < 63; i++) {
			allNodes.add(Tokens.get((int)(Math.random()*38)));
		}
	}
	
	public void addNode(String n) {allNodes.add(n);}
	
	//will be used for getting the tokens
	public ArrayList <Character> getFirst4Animals() {
		// get first 4 animals and remove them from the ArrayList as well
		animals = new ArrayList<Character>();
		ArrayList<Character> firstF = new ArrayList<Character>();
		int f = 4;
		int i = 0;
		while(i < f) {
			firstF.add(animals.get(i));
			animals.remove(i);
			i++;			
		}
		return firstF;
	}
	
	public String getFirst4Animals2() {
		String ret = "";
		for(int i=0; i<4; i++) {
			ret += randomAnimal();
		}
		
		return ret;
	}
	
	public char randomAnimal() {
		int an = (int) (Math.random()*5);
		switch(an) {
		case 0: 
			return 'f';
		case 1:
			return 's';
		case 2:
			return 'e';
		case 3:
			return 'h';
		case 4:
			return 'b';
		}
		return 'n';
	}
	
	public int playerNToken() {
		return getNatureToken();
	}
	public int getNatureToken() {
		return currNatureToken;
	}
	
	public void overpopulate() {
		// activate this if the first 4 animals are all the same or 3 animals are the same.
		/*Objective: 
		 Check if the animals from the pile of tokens are the same and are >=3 
		  If so, remove the tokens and replace them with new ones
		  either change the arraylist to type string and use the compare to method
		  CONDITIONS
		  2 of the same: Use an animal token to replace the pile
		  3 of the same: Either its automatically wiped or a player uses a nature token to replace the board
		  4 of the same: Automatically clear the board and replace with new animals
		 */
		
		//this checks if there are any of the same animal tokens in the pile
		int theSame = 0; // if this number is greater or equal to three, then repopulate the list
		boolean usedNatureToken = true;
		for(int i = 0; i < 4; i++) {
		if(animals.get(i).compareTo(animals.get(i+1)) == 0) {
			theSame++; 
			}
		}
		
		//checks the results from theSame and will perform the necessary actions needed.
		for(int i = 0; i < animals.size(); i++) {
			ArrayList <Character> identical = new ArrayList<Character>();
			if(theSame == 2 && usedNatureToken) { // if only two of the animal tokens are the same
				//identical.add(null)
				identical.add(animals.get(i));
				identical.add(animals.get(i+1));
			}
			else if(theSame == 3 || usedNatureToken) {
				identical.add(animals.get(i));
				identical.add(animals.get(i+1));
				identical.add(animals.get(i+2));
			}
			else if(theSame == 4) { //Find a way to clear the board and add new animals
				int j = 0; 
				//This while loop will go through the first four elements of your animals arrayList and remove the specified elements
				//It will then add these values to your arrayList identical that will be stored for later use 
				while(j < 4) {
					identical.add(animals.remove(j));
					j++;
				}
			}
			
		}
		
		
		/*if(theSame == 2 && usedNatureToken) { // if only two of the animal tokens are the same
			
		}
		else if(theSame == 3 || usedNatureToken) {
			
		}
		else if(theSame == 4) { //Find a way to clear the board and add new animals
			
		}*/
		//Karthiks idea to check if the size of animal arrayList is less than or equal to four
		//if so, take some of the removed animals and move them back to animal.
		if(animals.size() <= 4) { //the regular bag of animals
			for(int i = 0; i <= removedAnimals.size(); i++) { //loop through the list of removed animal tokens
				 int space = (int) Math.random() * 15; //use this to get a random position of an animal token
				animals.add(removedAnimals.get(space)); 
			}
		}
	}
	
	public void overpopulate(String animals) {
		int similar = 4;
		for(int i = 0; i < 4; i++) {
			
		}
	}
	
	public String overpopulate2(String command, String animals) {
		// for command
		// "four" is replace all four animals
		// "three" is replace three animals of the same type.
		// "custom-bh" is replace animals after the - with random animals.
		
		if(command.equals("four")) {
			return getFirst4Animals2();
		} else if (command.equals("three")) {
			char animal = 'n';
			if(animals.indexOf('f') != animals.lastIndexOf('f')) {
				animal = 'f';
			} else if (animals.indexOf('s') != animals.lastIndexOf('s')) {
				animal = 's';
			} else if (animals.indexOf('e') != animals.lastIndexOf('e')) {
				animal = 'e';
			} else if (animals.indexOf('h') != animals.lastIndexOf('h')) {
				animal = 'h';
			} else if (animals.indexOf('b') != animals.lastIndexOf('b')) {
				animal = 'b';
			}
			
			for(int i=0; i<animals.length(); i++) {
				if(animals.charAt(i)==animal) {
					animals = animals.substring(0,i) + randomAnimal() + animals.substring(i+1);
				}
			}
			return animals;
		} else {
			return null;
		}
	}
	
	public Player getPlayer(int p) {
		return players[p];
	}
}
