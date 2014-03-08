package CustMgtSys_1;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Settings extends JPanel{

	private JTabbedPane jtp;
	private JPanel passwordPanel;
	private JLabel passwordLabel;
	
	public Settings(){
		jtp = new JTabbedPane(JTabbedPane.LEFT);
		
		passwordPanel = new JPanel();
		passwordLabel = new JLabel("Password Settings");
		passwordPanel.add(passwordLabel);
		
		jtp.add("Password Settings",passwordPanel);	
	}
	
	
}