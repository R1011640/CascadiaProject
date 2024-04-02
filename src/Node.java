import java.awt.Polygon;

public class Node {
	public Polygon hexagon;
	
	public Node(int x, int y) {
		hexagon = new Polygon();
		for (int i = 0; i < 6; i++){
			hexagon.addPoint((int) (x + 50 * Math.cos((i * 2 * Math.PI / 6) + Math.PI/2)),
					  (int) (y + 50 * Math.sin((i * 2 * Math.PI / 6) + Math.PI/2)));
		}
	}
}
