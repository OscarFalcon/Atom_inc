package CustMgtSys_1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class MainWindowView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenu fileMenu, help;

	public JMenuItem logout, save, about, addUser, deleteUser, changePassword;

	public JPanel statusPanel, WorkOrderPanel, PMAPanel, currentPanel,
			northPanel, westPanel;

	private CustomerTabs customerTab; // tab for customer buttons
	public CustomerPanel customerPanel;// center panel for displaying customer
	private login loginPanel;									// information
	
	public JButton logoutButton,lockScreenButton;
	
	private JMenuBar menuBar;

	private JLabel statusLabel; // status to be displayed at SOUTH region

	private JSplitPane split;

	ImageIcon customersIcon = new ImageIcon("icon_customers.png");
	ImageIcon workOrderIcon = new ImageIcon("seamless.png");
	ImageIcon PMAIcon = new ImageIcon("PMA.png");

	public JTabbedPane jtp;



	Color normal = new Color(128, 0, 0);
	Color darkBlue = new Color(0, 0, 51);
	Color orange = new Color(255, 128, 0);
	Color silver = new Color(224, 224, 224);

	
	
	private MainWindowController listen;
	
	
	public MainWindowView() {
		
// ************************************ WEST		************************************************************************
		// Image on top left
		JPanel imagePanel = new JPanel();

		ImageIcon icon = new ImageIcon("2fatguyslogo.png");
		JLabel IconLabel = new JLabel();
		IconLabel.setIcon(icon);
		imagePanel.add(IconLabel);

		westPanel = new JPanel();
		westPanel.setLayout(null);
		
		icon = new ImageIcon("exit(1).png");
		logoutButton = new JButton(icon);
		
		icon = new ImageIcon("system_lock_screen(1).png");
		lockScreenButton = new JButton(icon);
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				imagePanel, westPanel);
		split.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		split.setDividerLocation(600);
		split.setDividerSize(0);

		add(split, BorderLayout.LINE_START);
// **************************** 				SOUTH:					**********************************************************************
		statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout(2, 1));
		statusPanel.setBackground(Color.YELLOW);
		JLabel userLabel = new JLabel("Welcome to TwoFatGuys");
		statusPanel.add(userLabel);
		statusLabel = new JLabel("");
		statusPanel.add(statusLabel);
		//	add(statusPanel, BorderLayout.PAGE_END);

// **********************************	 CENTER	 		*************************************************************** //
		loginPanel = new login(this);
		add(loginPanel, BorderLayout.CENTER);
			
// ***********************				OPTIONS 		***********************************************************			
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					Security.disconnect();
			}
		});
		setTitle("TwoFatGuys");
		setPreferredSize(new Dimension(1300, 800));
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		currentPanel = loginPanel;
		
	}

	// ***************************** END OF CONSTRUCTOR			 ***********************************************

	/**
	 * Updates the status bar located at the SOUTH of the frame
	 * 
	 * @param status
	 */
	public void updateStatus(String status) {
		statusLabel.setText(status);
	}

	public void switchPanels(int whichPanel) {
		if (whichPanel == 0) {
			String user = loginPanel.user.getText();
			String password = new String(loginPanel.password.getPassword());
			loginPanel.user.setText("");
			loginPanel.password.setText("");
			loginPanel.setText("");
			if(Security.Login(user, password))
				login();
			else{
				if(Security.getFailedConnectionStatus())
					loginPanel.setText("Connection Error");
				else
					loginPanel.setText("Invalid credentials");
			}
		} else 
			logout();
	}

	private void addTabbedPane(){
		jtp = new JTabbedPane();
		
		customerTab = new CustomerTabs(customerPanel.table);
		JPanel jp1 = new JPanel();
		JPanel jp3 = new JPanel();
		//JPanel jp4 = new Settings();

		jtp.addTab("Customers", customerTab);
		jtp.addTab("Work Orders",jp1 );
		jtp.addTab("PMA's", jp3);
		//jtp.addTab("Settings",jp4);

		jtp.setIconAt(0, customersIcon);
		jtp.setIconAt(1, workOrderIcon);
		jtp.setIconAt(2, PMAIcon);

		jtp.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		jtp.setForeground(new Color(81,127,164));
		add(jtp, BorderLayout.PAGE_START);
	}
	
	private void addLogoutButtons(){
		westPanel.add(logoutButton);
		westPanel.add(lockScreenButton);
		Insets insets;
		Dimension size;
		insets = westPanel.getInsets();
		size = logoutButton.getPreferredSize();
		logoutButton.setBounds(insets.right+100, insets.bottom, size.width, size.height);
		size = lockScreenButton.getPreferredSize();
		lockScreenButton.setBounds(20+insets.right,insets.bottom,size.width,size.height);
		split.setDividerSize(10);
		split.setDividerLocation(590);
	}

	private void addMenuBar(){
				menuBar = new JMenuBar();
				fileMenu = new JMenu("File");
				menuBar.add(fileMenu);
				changePassword = new JMenuItem("Change Password");
				fileMenu.add(changePassword);
				save = new JMenuItem("Save");
				fileMenu.add(save);
				logout = new JMenuItem("Logout");
				fileMenu.add(logout);
				help = new JMenu("Help");
				menuBar.add(help);
				about = new JMenuItem("About MyCMS");
				help.add(about);
				setJMenuBar(menuBar);
	}
	
	private void addCustomerPanel(){
		remove(currentPanel);
		customerPanel = new CustomerPanel();
		add(customerPanel, BorderLayout.CENTER);
	}
	
	
	private void login(){
		WorkOrderPanel = new WorkOrder();
		PMAPanel = new JPanel();
		PMAPanel.setLayout(new BorderLayout());
		PMAPanel.setBackground(Color.blue);
		
		addCustomerPanel();
		addTabbedPane();		
		addMenuBar();
		addLogoutButtons();
		currentPanel = customerPanel;
		listen = new MainWindowController(this);
		registerControllers(listen);
		pack();
		repaint();
		revalidate();
	}
	
	
	private void logout(){
		Security.disconnect();
		setJMenuBar(null);
		remove(currentPanel);
		remove(jtp);
		westPanel.remove(logoutButton);
		westPanel.remove(lockScreenButton);
		add(loginPanel, BorderLayout.CENTER);
		currentPanel = loginPanel;
		pack();
		repaint();
		revalidate();
		split.setDividerSize(0);
	}
	
	
	
	/**
	 * Registers all intractable components with the specified class listener
	 * This class must implement ActionListener and ListSelectionListener
	 * 
	 * @param listen
	 */
	public void registerControllers(MainWindowController listen) {
		jtp.addChangeListener(listen);
		logout.addActionListener(listen);
		logoutButton.addActionListener(listen);
		lockScreenButton.addActionListener(listen);
	}

}