import javax.swing.JFrame;
import java.awt.Toolkit;

public class Frame extends JFrame{
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	public Frame (String framename) {
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add(new Panel());
		setVisible(true);
	}
}
