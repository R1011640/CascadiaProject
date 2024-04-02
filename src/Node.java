import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class Node {
	//public Polygon hexagon;
	private BufferedImage img;
	private int x, y, rotation;
	private String availableAnimals;
	public char animal;
	private Node[] nearbyNodes;
	
	public Node(int x, int y, BufferedImage img) {
		//hexagon = new Polygon();
		//for (int i = 0; i < 6; i++){
		//	hexagon.addPoint((int) (x + 50 * Math.cos((i * 2 * Math.PI / 6) + Math.PI/2)),
		//			  (int) (y + 50 * Math.sin((i * 2 * Math.PI / 6) + Math.PI/2)));
		//}
	}
	
	public boolean isClicked() {
		return false;
	}
	
	public void rotate(){
		
	}
	
	public void setAnimal(char a) {
		
	}
}
