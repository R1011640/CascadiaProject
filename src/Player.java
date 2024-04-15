
import java.util.*;

public class Player {
	
	private int natureTokens = 0;
	private ArrayList<Node> nodes;
	
	public Player() {
		nodes = new ArrayList<Node>();
	}
	public void addNode(Node n) {
		for(Node node: nodes) {
			if(node.getX() == n.getX()+50 && node.getY() == n.getY()) {
				n.setNearbyNode(1, node);
				node.setNearbyNode(4, n);
			} else if (node.getX() == n.getX()+25) {
				if(node.getY() == n.getY()+40) {
					n.setNearbyNode(2, node);
					node.setNearbyNode(5, n);
				} else if (node.getY() == n.getY()-40){
					n.setNearbyNode(6, node);
					node.setNearbyNode(3, n);
				}
			} else if (node.getX() == n.getX()-25) {
				if(node.getY() == n.getY()+40) {
					n.setNearbyNode(3, node);
					node.setNearbyNode(6, n);
				} else if (node.getY() == n.getY()-40){
					n.setNearbyNode(5, node);
					node.setNearbyNode(2, n);
				}
			} else if (node.getX() == n.getX()-50 && node.getY() == n.getY()) {
				n.setNearbyNode(4, node);
				node.setNearbyNode(1, n);
			}
		}
		nodes.add(n);
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
		
	}
	
	
	
	public int getTokens() {
		return natureTokens;
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
			if(land.getEdges().indexOf(t)!=-1) {
				return land;
			}
		}
		return null;
		
	}
}
