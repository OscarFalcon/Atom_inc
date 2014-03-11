package CustMgtSys_1;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class login  extends JPanel{

	private static final long serialVersionUID = 1L;

	private MainWindowView view;

	
	public JTextField user;
	
	public JPasswordField password;
	
	private JButton loginButton;
	
	public JLabel statusLabel,userLabel,passwordLabel;
	
	
	public login(MainWindowView view){	
		this.view = view;
		
		user = new JTextField();
		user.setPreferredSize(new Dimension(160,30));
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(160,30));
		
		statusLabel = new JLabel();
		statusLabel.setPreferredSize(new Dimension(160,30));
		statusLabel.setForeground(Color.RED);
		statusLabel.setText("     Login please");
		
		userLabel = new JLabel("user name: ");
		userLabel.setPreferredSize(new Dimension(120,30));
		passwordLabel = new JLabel("password: ");
		passwordLabel.setPreferredSize(new Dimension(120,30));
		
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(120,30));
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		c.gridx= 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		add(userLabel,c);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		add(user,c);
		c.gridx = 0;
		c.gridy = 2;
		add(passwordLabel,c);
		c.gridx = 1;
		add(password,c);
		c.gridx = 1;
		c.gridy = 3;
		add(loginButton,c);
		c.gridy = 4;
		add(statusLabel,c);
		

		/**
		size = user.getPreferredSize();
		user.setBounds(insets.left+500,insets.top+200, size.width, size.height);
		size = password.getPreferredSize();
		password.setBounds(500+insets.left,insets.top+250, size.width, size.height);
		size = userLabel.getPreferredSize();
		userLabel.setBounds(380+insets.left,insets.top+200,size.width,size.height);
		size = passwordLabel.getPreferredSize();
		passwordLabel.setBounds(380+insets.left,insets.top+250,size.width,size.height);
		size = loginButton.getPreferredSize();
		loginButton.setBounds(520+insets.left,300+insets.top,size.width,size.height);
		size = statusLabel.getPreferredSize();
		statusLabel.setBounds(520+insets.left, 350+insets.top, size.width, size.height);
		
		**/
		
		registerControllers(new Controller());
	}
	
	private class Controller implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.switchPanels(0);
		}
		
	}
	
	private void registerControllers(Controller listen){
		loginButton.addActionListener(listen);
		password.addActionListener(listen);
	}
	
	public void setText(String t){
		statusLabel.setText(t);
	}
	
}