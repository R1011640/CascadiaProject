import java.util.ArrayList;

public class Player {
	
	private int natureTokens = 0;
	private ArrayList<Node> nodes;
	
	public Player() {
		nodes = new ArrayList<Node>();
	}
	
	public void addNode(Node n) {
		nodes.add(n);
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
		
	}
	public Node findAnimal(char a) {
		return null;
		
	}
	public Node findTerrain(char t) {
		return null;
		
	}
}
