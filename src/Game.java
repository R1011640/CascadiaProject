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

}

