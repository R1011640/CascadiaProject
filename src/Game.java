import java.util.ArrayList;

public class Game {

	private Player [] players;
	private int currentPlayer = 0;
	private ArrayList<Node> allNodes;
	private Node [][] startingNodes;
	private ArrayList<Character> animals;
	private String scoringCards;
	
	public Game() {
		
	}
	public Node[] getNodes() {
		return null;
		
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
