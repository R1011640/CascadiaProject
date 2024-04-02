import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class Node {
	//public Polygon hexagon;
	private BufferedImage img;
	private int x, y, rotation;
	private String availableAnimals;
	private String edges;
	public char animal;
	private Node[] nearbyNodes;
	
	public Node(int x, int y, BufferedImage img) {
		//hexagon = new Polygon();
		//for (int i = 0; i < 6; i++){
		//	hexagon.addPoint((int) (x + 50 * Math.cos((i * 2 * Math.PI / 6) + Math.PI/2)),
		//			  (int) (y + 50 * Math.sin((i * 2 * Math.PI / 6) + Math.PI/2)));
		//}
		
		this.x = x;
		this.y = y;
		this.img = img;
		rotation = 0;
	}
	
	public boolean isClicked() {
		return false;
	}
	
	public void rotate(){
		rotation++;
		if(rotation>6) {
			rotation = 1;
		}
	}
	
	public void setAnimal(char a) {
		animal = a;
	}
	public void setEdges(String s) {
		edges = s;
	}
	public String getEdges() {
		return edges;
	}
	public Node getNearbyNode(int dir) {
		return nearbyNodes[dir-1]; 
	}
	public char getAnimal() {
		return animal;
	}
	public int getRot() {
		return rotation;
	}
	public void setRot(int r) {
		rotation = r;
	}
}
