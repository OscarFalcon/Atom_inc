package CustMgtSys_1;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LockScreen extends JDialog {

	private static final long serialVersionUID = 1L;

	public LockScreen(MainWindowController controller) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		final JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter password for :" + Security.getUser());
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		final String[] options = new String[] { "Unlock" };
		JPasswordField pf = new JPasswordField();

		int option = JOptionPane.showOptionDialog(this, pf,
				"Enter password for " + Security.getUser(),
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, "");

		if (option == 0) // pressing OK button
		{
			if (Security.testPassword(new String(pf.getPassword()))) 
				controller.setEnter(true);
		}
	}
}