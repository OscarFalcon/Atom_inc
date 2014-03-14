package CustMgtSys_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CustomerTabs extends JPanel {

	private static final long serialVersionUID = 1L;

	private final  JButton addClientButton, editClientButton,
			advancedSearchButton, showAllButton, addWorkOrderToButton;

	
	private final static Color silver = new Color(204,204,204);
	private final static Color blue = new Color(81,127,164);
	private final static Dimension dimension = new Dimension(150, 50);
	
	
	ImageIcon customersIcon = new ImageIcon("icon_customers.png");
	ImageIcon addUserIcon = new ImageIcon("adduser.png");
	ImageIcon editUserIcon = new ImageIcon("edituser.png");
	ImageIcon advancedSearchIcon = new ImageIcon("find.png");
	ImageIcon addWorkOrderIcon = new ImageIcon("plus.png");
	
	
	
	
	//Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	
	public CustomerTabs() {
		
		setBackground(silver);
		setLayout(new FlowLayout());

		JToolBar toolbar = new JToolBar();
		
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		addClientButton = new JButton("Add Client", addUserIcon);		
		addClientButton.setPreferredSize(dimension);
		toolbar.add(addClientButton);
		
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		editClientButton = new JButton("Edit Client", editUserIcon);
		editClientButton.setPreferredSize(dimension);
		toolbar.add(editClientButton);
		
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		showAllButton = new JButton("Show all", customersIcon);
		showAllButton.setPreferredSize(dimension);
		toolbar.add(showAllButton);
		
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		advancedSearchButton = new JButton("Advanced Search",advancedSearchIcon);
		advancedSearchButton.setPreferredSize(new Dimension(200, 50));
		toolbar.add(advancedSearchButton);
		
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		addWorkOrderToButton = new JButton("Add Work Order",addWorkOrderIcon);
		addWorkOrderToButton.setPreferredSize(new Dimension(200, 50));
		toolbar.add(addWorkOrderToButton);
		
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		add(toolbar);
	
		registerControllers(new Controller());

	}

	private class Controller implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == addClientButton) {
				new AddClientWindow();

			} else if (event.getSource() == editClientButton) {
				if (CustomerPanel.getClientTableSelectedRow() >= 0)
					new ClientInfo();
				else
					NoCustomerSelected();

			} else if (event.getSource() == showAllButton) {
				Security.client.selectAll();
			}
			else if(event.getSource() == advancedSearchButton){
				new SearchWindow();
			}	
		}
	}
	
	private void registerControllers(Controller listen){
				addClientButton.addActionListener(listen);
				editClientButton.addActionListener(listen);
				showAllButton.addActionListener(listen);
				advancedSearchButton.addActionListener(listen);
	}
	
	private void NoCustomerSelected() {
		JOptionPane.showMessageDialog(null, "No customer selected");

	}

}