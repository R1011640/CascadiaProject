import java.awt.Polygon;

public class Node {
	public Polygon hexagon;
	
	public Node(int x, int y) {
		hexagon = new Polygon();
		for (int i = 0; i < 6; i++){
			hexagon.addPoint((int) (x + 50 * Math.cos(99 + (i * 2 * Math.PI / 6))),
					  (int) (y + 50 * Math.sin(99 + (i * 2 * Math.PI / 6))));
		}
	}
}
