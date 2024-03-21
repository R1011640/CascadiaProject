import javax.swing.JFrame;

public class Frame extends JFrame{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	public Frame (String framename) {
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add(new Panel());
		setVisible(true);
	}
}
