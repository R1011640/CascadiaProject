import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Node {
	//public Polygon hexagon;
	private int x, y, rotation, size;
	private String availableAnimals, edges;
	private BufferedImage img;
	public char animal;
	private Node[] nearbyNodes;
	
	public Node(int x, int y, String imageName, int size) {
		this.x = x;
		this.y = y;
		try {
			img = ImageIO.read(Panel.class.getResource("/assets/" + imageName));
		} catch (IOException e) {
			System.out.println("Error");
		}
		rotation = 0;
		nearbyNodes = new Node[6];
		this.size = size;
	}
	
	public boolean isClicked(int x, int y) {
		// used for clicking available tiles
		
		return (this.x-((size==50?40:size)/2) < x && x < this.x+((size==50?40:size)/2)
				&& this.y-((size==50?40:size)/2) < y && y < this.y+((size==50?40:size)/2));
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
	public Node[] getNearbyNodes() {
		return nearbyNodes;
	}
	public String toString() {
		return "("+x + "," + y + ")";
	}
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public int getSize() {return size;}
}
