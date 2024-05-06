import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SPanel extends JPanel{
	
	BufferedImage fox, bear, hawk, elk, salmon;
	
	public SPanel() {
		try {
			fox = ImageIO.read(Panel.class.getResource("/assets/foxsc.png"));
			hawk = ImageIO.read(Panel.class.getResource("/assets/hawksc.png"));
			elk = ImageIO.read(Panel.class.getResource("/assets/elksc.png"));
			bear = ImageIO.read(Panel.class.getResource("/assets/bearsc.png"));
			salmon = ImageIO.read(Panel.class.getResource("/assets/salmonsc.png"));
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(bear, 0, 0, 200, 200, null);
		g.drawImage(fox, 0, 200, 200, 200, null);
		g.drawImage(hawk, 0, 400, 200, 200, null);
		g.drawImage(salmon, 0, 600, 200, 200, null);
		g.drawImage(elk, 0, 800, 200, 200, null);
	}

}
