package CustMgtSys_1;



import java.awt.Color;
import java.awt.Dimension;
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
	
	JPanel westPanel,southPanel,northPanel,centerPanel;
	
	public JTextField user;
	
	public JPasswordField password;
	
	private JButton loginButton;
	
	public JLabel statusLabel;
	
	
	public login(MainWindowView view){	
		this.view = view;
		
		user = new JTextField();
		user.setPreferredSize(new Dimension(160,30));
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(160,30));
		statusLabel = new JLabel();
		statusLabel.setPreferredSize(new Dimension(160,30));
		statusLabel.setForeground(Color.RED);
		statusLabel.setText("Login please");
		
		JLabel userLabel = new JLabel("user name: ");
		userLabel.setPreferredSize(new Dimension(120,30));
		JLabel passwordLabel = new JLabel("password: ");
		passwordLabel.setPreferredSize(new Dimension(120,30));
		
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(120,30));
		
		setLayout(null);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		add(user);
		add(password);
		add(userLabel);
		add(passwordLabel);
		add(loginButton);
		add(statusLabel);
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = getInsets();
		
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
