import java.util.ArrayList;

public class Game {

	private Player [] players;
	private int currentPlayer = 0;
	private ArrayList<Node> allNodes;
	private Node [][] startingNodes;
	private ArrayList<Character> animals;
	private String scoringCards;
	
	//The constructor that sets up everything for the GameBoard
	public Game() { 
		this.currentPlayer = currentPlayer;
		this.scoringCards = scoringCards;
	}
	//This method will return a fixed array of all the first four front nodes in the allNodes arrayList.
	//this will most likely need a while loop
	public ArrayList<Node> getNodes() {
		ArrayList<Node> theFirstFour = new ArrayList<Node>();
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
		allNodes.add((new Node(200, 200, "mmmmmm-b.png", 50)));
		allNodes.add((new Node(200, 200, "mmmmmm-e.png", 50)));
		allNodes.add((new Node(200, 200, "mmmmmm-h.png", 50)));
		allNodes.add((new Node(200, 200, "mmmrrr-bs.png", 50)));
		allNodes.add((new Node(200, 200, "mmmrrr-hs.png", 50)));
		allNodes.add((new Node(200, 200, "mmwwwm-fh.png", 50)));
		allNodes.add((new Node(200, 200, "pfffpp-bes.png", 50)));
		allNodes.add((new Node(200, 200, "ppmmmp-bf.png", 50)));
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
		allNodes.add((new Node(200, 200, "fwwwff-hef.png", 50)));
	}

}
