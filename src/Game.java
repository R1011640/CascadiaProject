import java.util.ArrayList;

public class Game {

	private Player [] players;
	private int currentPlayer = 0;
	private ArrayList<String> allNodes; // might need to make this an ArrayList of Strings instead
	private Node [][] startingNodes;
	private ArrayList<Character> animals;
	private String scoringCards;
	
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
	
	public String getFirst4Animals() {
		// get first 4 animals and remove them from the ArrayList as well
		return null;
	}
	
	public void overpopulate() {
		// activate this if the first 4 animals are all the same or 3 animals are the same.
	}
	
	public void overpopulate(String animals) {
		
	}
}
