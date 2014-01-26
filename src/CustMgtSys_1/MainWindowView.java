package CustMgtSys_1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class MainWindowView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenu fileMenu, AdminMenu, help, reports;

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

	public String userName;

	Color normal = new Color(128, 0, 0);
	Color darkBlue = new Color(0, 0, 51);
	Color orange = new Color(255, 128, 0);
	Color silver = new Color(224, 224, 224);

	
	public MainWindowView() {

		super("TwoFatGuys");
		
		
		WorkOrderPanel = new WorkOrder();

		PMAPanel = new JPanel();
		PMAPanel.setLayout(new BorderLayout());
		PMAPanel.setBackground(Color.blue);
		// ********************* MENUBAR
		// ********************************************************************************

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
		
		split.setDividerLocation(800);
		split.setDividerSize(0);

		add(split, BorderLayout.LINE_START);
// **************************** SOUTH:	**********************************************************************
		statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout(2, 1));
		statusPanel.setBackground(Color.YELLOW);
		JLabel userLabel = new JLabel("Welcome to TwoFatGuys");
		statusPanel.add(userLabel);
		statusLabel = new JLabel("");
		statusPanel.add(statusLabel);
		add(statusPanel, BorderLayout.PAGE_END);

		// **********************************	 CENTER	 *************************************************************** //
		loginPanel = new login(this);
		add(loginPanel, BorderLayout.CENTER);
		// ****************************** NORTH
		// *****************************************************************************

		jtp = new JTabbedPane();
	
		jtp.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		customerTab = null;
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();

		jtp.addTab("Customers", customerTab);
		jtp.setTabComponentAt(0, new JButton());
		jtp.addTab("Work Orders", jp2);
		jtp.addTab("PMA's", jp3);

		jtp.setIconAt(0, customersIcon);
		jtp.setIconAt(1, workOrderIcon);
		jtp.setIconAt(2, PMAIcon);

		jtp.setForegroundAt(0,new Color(81,127,164));
		jtp.setForegroundAt(0,new Color(81,127,164));
		jtp.setForeground(new Color(81,127,164));
		
		
		jtp.setEnabled(false);
		jtp.setVisible(false);
		add(jtp, BorderLayout.PAGE_START);

		// *************************************************************************

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					Security.disconnect();
			}
		});

		setPreferredSize(new Dimension(1300, 800));
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		currentPanel = loginPanel;
	}

	// ***************************** END OF CONSTRUCTOR
	// ***********************************************

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
			loginPanel.statusLabel.setText("");
			if(Security.checkLogin(user, password)){
				remove(currentPanel);
				customerPanel = new CustomerPanel();
				customerTab = new CustomerTabs(customerPanel.table);
				jtp.removeTabAt(0);
				jtp.insertTab("customers", customersIcon, customerTab,"customers", 0);
				jtp.setSelectedComponent(customerTab);
				add(customerPanel, BorderLayout.CENTER);
				add(jtp,BorderLayout.NORTH);
				setJMenuBar(menuBar);
				currentPanel = customerPanel;
				jtp.setEnabled(true);
				jtp.setVisible(true);
				westPanel.add(logoutButton);
				westPanel.add(lockScreenButton);
				Insets insets;
				Dimension size;
				insets = westPanel.getInsets();
				size = logoutButton.getPreferredSize();
				logoutButton.setBounds(insets.right+100, insets.bottom, size.width, size.height);
				size = lockScreenButton.getPreferredSize();
				lockScreenButton.setBounds(20+insets.right,insets.bottom,size.width,size.height);
				
				pack();
				repaint();
				revalidate();
				split.setDividerSize(10);
				split.setDividerLocation(550);
			}else
				loginPanel.statusLabel.setText("Invalid credentials");	
		} else {
			setJMenuBar(null);
			remove(currentPanel);
			remove(jtp);
			Security.disconnect();
			add(loginPanel, BorderLayout.CENTER);
			westPanel.remove(logoutButton);
			westPanel.remove(lockScreenButton);
			currentPanel = loginPanel;
			pack();
			repaint();
			revalidate();
			split.setDividerSize(0);
		}

	
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