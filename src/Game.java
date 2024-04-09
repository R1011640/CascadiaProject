import java.util.ArrayList;

public class Game {

	private Player [] players;
	private int currentPlayer = 0;
	private ArrayList<String> allNodes; // might need to make this an ArrayList of Strings instead
	private Node [][] startingNodes;
	private ArrayList<Character> animals;
	private String scoringCards;
	private ArrayList<Character> removedAnimals = new ArrayList<Character> (); //The animals removed from the animal arrayList
	private int pos = (int) Math.random(); //This will be used when trying to repopulate the board
	
	//The constructor that sets up everything for the GameBoard
	public Game(int currentPlayer, String scoringCards) { 
		this.currentPlayer = currentPlayer;
		this.scoringCards = scoringCards;
		
		// Set up players like so
		// players.add(new Player());
		
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
		return null;    
		
	}
	public Player currentPlayer() {
		return players[currentPlayer];
	}
	public void endTurn() {
		currentPlayer++;
		if(currentPlayer == 2) {
			currentPlayer = 0;
		}
	}
	//This method is of type void due to only adding the different cards into the ArrayList of allNodes
	public  void addNodes() { 
		Player play = new Player();
		allNodes.add("mmmmmm-b.png");
		allNodes.add("mmmmmm-e.png");
		allNodes.add("mmmmmm-h.png");
		allNodes.add("mmmrrr-bs.png");
		allNodes.add("mmmrrr-hs.png");
		allNodes.add("mmwwwm-fh.png");
		allNodes.add("pfffpp-bes.png");
		/*allNodes.add((new Node(200, 200, "ppmmmp-bf.png", 50)));
		allNodes.add((new Node(200, 200, "pppfff-es.png", 50)));
		allNodes.add((new Node(200, 200, "pppfff-fs.png", 50)));
		allNodes.add((new Node(200, 200, "pppmmm-bs.png", 50)));
		allNodes.add((new Node(200, 200, "pppmmm-es.png", 50)));
		allNodes.add((new Node(200, 200, "pppppp-e.png", 50)));
		allNodes.add((new Node(200, 200, "pppppp-f.png", 50)));
		allNodes.add((new Node(200, 200, "pppppp-s.png", 50)));
		allNodes.add((new Node(200, 200, "rfffrr-ehs.png", 50)));
		allNodes.add((new Node(200, 200, "rrpppr-bs.png", 50)));
		allNodes.add((new Node(200, 200, "rrrfff-fs.png", 50)));
		allNodes.add((new Node(200, 200, "rrrppp-es.png", 50)));
		allNodes.add((new Node(200, 200, "rrrrrr-b.png", 50)));
		allNodes.add((new Node(200, 200, "rrrrrr-h.png", 50)));
		allNodes.add((new Node(200, 200, "rrrrrr-s.png", 50)));
		allNodes.add((new Node(200, 200, "rrrwww-bs.png", 50)));
		allNodes.add((new Node(200, 200, "rrrwww-fs.png", 50)));
		allNodes.add((new Node(200, 200, "rrrwww-hs.png", 50)));
		allNodes.add((new Node(200, 200, "wwwfff-bs.png", 50)));
		allNodes.add((new Node(200, 200, "wwwfff-es.png", 50)));
		allNodes.add((new Node(200, 200, "wwwmmm-bs.png", 50)));
		allNodes.add((new Node(200, 200, "wwwmmm-hs.png", 50)));
		allNodes.add((new Node(200, 200, "wwwppp-es.png", 50)));
		allNodes.add((new Node(200, 200, "wwwppp-hs.png", 50)));
		allNodes.add((new Node(200, 200, "wwwwww-f.png", 50)));
		allNodes.add((new Node(200, 200, "wwwwww-h.png", 50)));
		allNodes.add((new Node(200, 200, "wwwwww-s.png", 50)));
		allNodes.add((new Node(200, 200, "ffffff-b.png", 50)));
		allNodes.add((new Node(200, 200, "ffffff-e.png", 50)));
		allNodes.add((new Node(200, 200, "ffffff-f.png", 50)));
		allNodes.add((new Node(200, 200, "fwwwff-hef.png", 50)));*/
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
			}
			else if(theSame == 3 || usedNatureToken) {
				
			}
			else if(theSame == 4) { //Find a way to clear the board and add new animals
				
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
}
