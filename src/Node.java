import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class Node {
	//public Polygon hexagon;
	private int x, y, rotation, size;
	private String availableAnimals, edges;
	private BufferedImage img;
	public char animal;
	private Node[] nearbyNodes;
	
	public Node(int x, int y, BufferedImage imageName, int size) {
		//hexagon = new Polygon();
		//for (int i = 0; i < 6; i++){
		//	hexagon.addPoint((int) (x + 50 * Math.cos((i * 2 * Math.PI / 6) + Math.PI/2)),
		//			  (int) (y + 50 * Math.sin((i * 2 * Math.PI / 6) + Math.PI/2)));
		//}
		
		this.x = x;
		this.y = y;
		this.img = imageName;
		rotation = 0;
		nearbyNodes = new Node[6];
		this.size = size;
	}
	
	public boolean isClicked(int x, int y) {
		// used for clicking available tiles
		
		return (this.x-size < x && x < this.x+size && this.y-size < y && y < this.y+size);
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
	public void setNearbyNode(int dir, Node n) {
		nearbyNodes[dir-1] = n;
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
	public BufferedImage getImg() {
		return img;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSize() {return size;}
}
