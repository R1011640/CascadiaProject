import java.util.ArrayList;

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
		players[0].addNode(new Node(400, 200, startingNodes[row1][0], 75));
		players[0].addNode(new Node(360, 260, startingNodes[row1][1], 75));
		players[0].addNode(new Node(440, 260, startingNodes[row1][2], 75));
		
		players[1] = new Player();
		players[1].addNode(new Node(400, 200, startingNodes[row2][0], 75));
		players[1].addNode(new Node(360, 260, startingNodes[row2][1], 75));
		players[1].addNode(new Node(440, 260, startingNodes[row2][2], 75));
		
		players[2] = new Player();
		players[2].addNode(new Node(400, 200, startingNodes[row3][0], 75));
		players[2].addNode(new Node(360, 260, startingNodes[row3][1], 75));
		players[2].addNode(new Node(440, 260, startingNodes[row3][2], 75));
		
	}
	//This method will return a fixed array of all the first four front nodes in the allNodes arrayList.
	//this will most likely need a while loop
	public ArrayList<String> getNodes() {
		ArrayList<String> theFirstFour = new ArrayList<String>();
		int pos = 0;
		int limit = 4;
		boolean isFour = false;
		while(pos<allNodes.size()) {
			theFirstFour.add(allNodes.get(pos));
			pos++;
		}
		if(pos == limit) {
			isFour = true;
		}
		
		// remove the nodes you took from allNodes
		return theFirstFour;
		
	}
	public int [][] scoring(){
		int bearC = 0;
		int elkC = 0;
		int salmonC = 0;
		int foxC = 0;
		int hawkC = 0;
		//The scoring will be based on the following type of card
		//iterate through the animals, not the players
		//The scoring may or may not need to use a recursion method
		for(int i = 0; i < players.length; i++) {
			//This is for scoring card bear: Checks for those that are in pairs
			for(Node j: players[i].getNodes()) {
				if(j.getAnimal() == 'b') { //We have already found one bear node
					if(j.getNearbyNode(1).getAnimal() == 'b') { //checks if there is another one by it
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
				//This is the scoring card elk: Checks for a STRAIGHT line of elks
				//if the nearbyNode is in that direction, keep going in that direction and count the amount of times it is there
				else if(j.getAnimal() == 'e') {
					int elkRun = 0;
					while(elkRun < players[i].getNodes().size()) {
						if(j.getNearbyNode(1).getAnimal() == 'e') {
							elkC++; //Keep going in that direction of one
							elkRun++;
							j.setAnimal('n'); //after using this node, don't count it again
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
				}//This is the scoring card salmon: Checks for a run, not a straight line like the elk
				//For now, make it a bit similar to the elk run count
				else if(j.getAnimal() == 's') {
					int fishRun = 0;
					while(fishRun < players[i].getNodes().size()) { //checks for the ones that are in a run, not a straight line
						if(j.getNearbyNode(1).getAnimal() == 's') {
							salmonC++;
							fishRun++;
							j.setAnimal('n'); //Dont count this node again after using it 
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
						j.setAnimal('n'); //This one will firmly end the use of counting the specific node selected
					}
				}
				//This is the scoring card fox: Checks for the number of differing animals surrounding it
				else if(j.getAnimal() == 'f') {
						int differ = 0; // This counts the differing amount of animals that are surrounding the fox
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
							j.setAnimal('n'); // this will firmly end the node's use on the current player's board and also make sure that it isn't counted twice
						}
				}
				//This is the scoring card hawk: Checks for each hawk that isn't adjacent to any other hawk
				//Hawk counting might actually be the one that really needs to use a recursive method to check if there are no adjacent pairs
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
			}
			return null; 
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
	public  void addNodes() { 
		allNodes.add("mmmmmm-b.png");
		allNodes.add("mmmmmm-e.png");
		allNodes.add("mmmmmm-h.png");
		allNodes.add("mmmrrr-bs.png");
		allNodes.add("mmmrrr-hs.png");
		allNodes.add("mmwwwm-fh.png");
		allNodes.add("pfffpp-bes.png");
		allNodes.add("ppmmmp-bf.png");
		allNodes.add("pppfff-es.png");
		allNodes.add("pppfff-fs.png");
		allNodes.add("pppmmm-bs.png");
		allNodes.add("pppmmm-es.png");
		allNodes.add("pppppp-e.png");
		allNodes.add("pppppp-f.png");
		allNodes.add("pppppp-s.png");
		allNodes.add("rfffrr-ehs.png");
		allNodes.add("rrpppr-bs.png");
		allNodes.add("rrrfff-fs.png");
		allNodes.add("rrrppp-es.png");
		allNodes.add("rrrrrr-b.png");
		allNodes.add("rrrrrr-h.png");
		allNodes.add("rrrrrr-s.png");
		allNodes.add("rrrwww-bs.png");
		allNodes.add("rrrwww-fs.png");
		allNodes.add("rrrwww-hs.png");
		allNodes.add("wwwfff-bs.png");
		allNodes.add("wwwfff-es.png");
		allNodes.add("wwwmmm-bs.png");
		allNodes.add("wwwmmm-hs.png");
		allNodes.add("wwwppp-es.png");
		allNodes.add("wwwppp-hs.png");
		allNodes.add("wwwwww-f.png");
		allNodes.add("wwwwww-h.png");
		allNodes.add("wwwwww-s.png");
		allNodes.add("ffffff-b.png");
		allNodes.add("ffffff-e.png");
		allNodes.add("ffffff-f.png");
		allNodes.add("fwwwff-hef.png");
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
