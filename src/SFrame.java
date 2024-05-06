import javax.swing.JFrame;
import java.awt.Toolkit;

public class SFrame extends JFrame{
	private static final int WIDTH = 250;
	private static final int HEIGHT = 1000;
	public SFrame (String framename) {
		super(framename);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add(new SPanel());
		setVisible(true);
	}
}