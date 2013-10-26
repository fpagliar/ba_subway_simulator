import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	public Panel() {
		setBounds(0, 0, 250, 75);
		JLabel tLabel = new JLabel("T = ");
		final JTextField tTextField = new JTextField("128");
		tTextField.setColumns(3);

//		repaint();
		add(tLabel);
	}

}
