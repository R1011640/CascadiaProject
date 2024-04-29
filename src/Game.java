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
		int[][] scores = new int[14][3];
		// 14 rows, 3 columns
		for(int i=0; i<3; i++) {
			Player p = players[i];
			
			// fox is row 5
			while(p.findAnimal('f')!=null) {
				Node f = p.findAnimal('f');
				scores[5][i] += f.getNearbyAnimals().indexOf("f")!=-1?1:0;
				scores[5][i] += f.getNearbyAnimals().indexOf("b")!=-1?1:0;
				scores[5][i] += f.getNearbyAnimals().indexOf("s")!=-1?1:0;
				scores[5][i] += f.getNearbyAnimals().indexOf("e")!=-1?1:0;
				scores[5][i] += f.getNearbyAnimals().indexOf("h")!=-1?1:0;
				f.setAnimal('n');
			}
			
			
		}
		return null;
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
