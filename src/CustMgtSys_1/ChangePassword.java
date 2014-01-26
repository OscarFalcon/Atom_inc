package CustMgtSys_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

/**
 * 
 * @author amartine
 * 
 *         This window is for any user to change their password if desired. in
 *         the event that any user (ADMIN or normal user) goes to file menubar
 *         and clicks changePassword this window will appear.
 * 
 *         this window calls the delete account in ench.java and then adds a new
 *         account to the user.txt (same username just different password) also
 *         inserts the same boolean admincheck flag that the user originally
 *         had.
 * 
 */

public class ChangePassword extends JFrame {
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel newPasswordLabel;
	private JPasswordField newPasswordField;
	private JLabel verifyLabel;
	private JPasswordField verifyPassword;
	private JButton login;
	private String username;

	public ChangePassword(String user) {
		super("MyCMS Change Password");
		// Changed to DISPOSE_ON_CLOSE so that this window will not exit the
		// whole program on exit
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(385, 160);
		this.username = user;

		setLayout(new FlowLayout());
		passwordLabel = new JLabel("Current Password");
		add(passwordLabel);

		passwordField = new JPasswordField("", 20);
		add(passwordField);

		newPasswordLabel = new JLabel("New Password      ");
		add(newPasswordLabel);

		newPasswordField = new JPasswordField("", 20);
		add(newPasswordField);

		verifyLabel = new JLabel("Verify Password   ");
		add(verifyLabel);

		verifyPassword = new JPasswordField("", 20);
		add(verifyPassword);

		login = new JButton("Change Password");
		add(login);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String password = String.format(passwordField.getText());
				String newPassword = String.format(newPasswordField.getText());
				String newPasswordVerify = String.format(verifyPassword
						.getText());

				boolean[] flag = { false, false };
				//try {
					//flag = ench.logincheck(username, password, master);
			//	} catch (IOException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}

				if (flag[0] == false)
					JOptionPane.showMessageDialog(null, "Wrong Password");
				else if (newPassword.equals("") || newPasswordVerify.equals(""))
					JOptionPane.showMessageDialog(null,
							"Password cannot be empty");
				else if (newPassword.length() < 8
						|| newPasswordVerify.length() < 8) {
					JOptionPane.showMessageDialog(null,
							"Password must be 8 characters or more");
					passwordField.setText("");
					verifyPassword.setText("");
				} else if ((newPassword.equals(newPasswordVerify))) {
					JOptionPane.showMessageDialog(null,
							"successfully changed Password for " + username);
					//try {
						//ench.deleteaccount(username, password, flag[1], master);
						//ench.addaccount(username, newPassword, flag[1], master);
				//	} catch (IOException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}
					dispose();
					// at this point, close when they press OK... and send user
					// to next window.
				} else
					JOptionPane.showMessageDialog(null,
							"passwords do not match");
				// }
			}
		});

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setVisible(true);
		setResizable(false);
	}
}
