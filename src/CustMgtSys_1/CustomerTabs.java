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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class CustomerTabs extends JPanel {

	private static final long serialVersionUID = 1L;

	private final  JButton addClientButton, editClientButton,
			advancedSearchButton, showAllButton, addWorkOrderToButton;

	
	private final Color silver = new Color(204,204,204);

	private final Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	
	private final JTable table;
	
	public CustomerTabs(JTable table) {
		this.table = table;
		setBackground(new Color(81,127,164));
		//setBackground(new Color(0,150,0));
		setBorder(border);
		setLayout(new FlowLayout());
	
		Dimension dimension1 = new Dimension(150, 50);
		ImageIcon customersIcon = new ImageIcon("icon_customers.png");

		JLabel filler = new JLabel();
		filler.setPreferredSize(new Dimension(100, 50));
		add(filler);

		Icon addUser = new ImageIcon("adduser.png");
		addClientButton = new JButton("Add Client", addUser);		
		//addClientButton.setBorder(border);
		addClientButton.setPreferredSize(dimension1);
		//addClientButton.setVerticalAlignment(SwingConstants.BOTTOM);
		//addClientButton.setHorizontalTextPosition(SwingConstants.CENTER);

		addClientButton.setBackground(silver);
		add(addClientButton);

		Icon edituser = new ImageIcon("edituser.png");
		editClientButton = new JButton("Edit Client", edituser);
		//editClientButton.setBorder(border);
		editClientButton.setPreferredSize(dimension1);
		editClientButton.setBackground(silver);
		add(editClientButton);

		showAllButton = new JButton("Show all", customersIcon);
		//showAllButton.setBorder(border);
		showAllButton.setPreferredSize(dimension1);
		showAllButton.setBackground(silver);
		add(showAllButton);

		Icon advancedSearchIcon = new ImageIcon("find.png");
		advancedSearchButton = new JButton("Advanced Search",advancedSearchIcon);
		//advancedSearchButton.setBorder(border);
		advancedSearchButton.setPreferredSize(new Dimension(200, 50));
		advancedSearchButton.setBackground(silver);
		add(advancedSearchButton);

		Icon addWorkOrderIcon = new ImageIcon("plus.png");
		addWorkOrderToButton = new JButton("Add Work Order",addWorkOrderIcon);
		//addWorkOrderToButton.setBorder(border);
		addWorkOrderToButton.setPreferredSize(new Dimension(200, 50));
		addWorkOrderToButton.setBackground(silver);
		add(addWorkOrderToButton);
		
		registerControllers(new Controller());

	}

	private class Controller implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == addClientButton) {
				new AddClientWindow();

			} else if (event.getSource() == editClientButton) {
				if (table.getSelectedRow() >= 0)
					new ClientInfo(table);
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