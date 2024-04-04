
import java.util.*;

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
	//returns the first node that has the animal listed in the parameter
	public Node findAnimal(char a) {
		//Lets try a for-each that will go through the array list of nodes
		for(Node mammal: nodes) {
			if(mammal.getAnimal() == a) {
				return mammal;
			}
		}
		return null;
		
	}
	//returns the first node that has the terrain listed in the parameter.
	public Node findTerrain(char t) {
		//Do the same thing that was done with the animal, only this one is for the animal terrain
		for(Node land: nodes) {
			if(land.getEdges().charAt(t) == t) {
				return land;
			}
		}
		return null;
		
	}
}
