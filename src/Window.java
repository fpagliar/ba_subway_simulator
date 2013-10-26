import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private Panel panel = new Panel();
	private Point location;
	
	public Window() {
			setTitle("Images Analysis and Treatment");
			setBounds(1, 1, 500, 500);
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			location = new Point(size.width / 3 - getWidth() / 3, size.height / 3
					- getHeight() / 3);
			setLocation(location);
			setResizable(true);
			setMinimumSize(new Dimension(600, 600));
			add(panel);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
