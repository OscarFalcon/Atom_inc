package PMAWizard;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import CustMgtSys_1.CenterTableCellRenderer;
import CustMgtSys_1.CustomTableModel;
import CustMgtSys_1.Error;
import CustMgtSys_1.Security;


public class PMAWizard extends JPanel{
	//GUI Components
	private static final long serialVersionUID = 1L;
	private final JDialog wizard;
	private final JPanel cards, buttonPanel, stepsPanel;
	private final PMAWizardModel model;
	private final CardLayout layout;
	private final JPanel  customerConcerns;
	private final JLabel step1, step2, step3;
	private final card0 customerInfo;
	private final card1 customerCreate;
	private final card2 vehicleInfo;
	private final card3 vehicleCreate;
	public final JFrame frame;
	public JButton backButton, nextButton, cancelButton, finishButton;
	public DefaultTableModel vehicleTablemodel;
	public JTable custTable;
	public JTextArea custConcernsArea;
	public JTextField createdByField;

	//constants
	public final int CUSTOMER_INFO = 0;
	public final int CUSTOMER_CREATE = 1;
	public final int VEHICLE_INFO = 2;
	public final int VEHICLE_CREATE = 3;
	public final int CUSTOMER_CONCERNS = 4;
	
	//public variables
	public int custID;
	public String vehicleVin;
	


	public void clearFields(int whichCard){
		if(whichCard == CUSTOMER_CREATE) 
			customerCreate.clearFields();
		else if (whichCard == VEHICLE_CREATE)
			vehicleCreate.clearFields();		
	}
	
	public void setEditableFields(boolean b,int whichCard){
		if(whichCard == CUSTOMER_CREATE)
			customerCreate.setEditableFields(b);
		else if(whichCard == VEHICLE_CREATE)
			vehicleCreate.setEditableFields(b);
	}
	
	public void updateTable(int whichCard){
		if(whichCard == CUSTOMER_INFO)
			customerInfo.updateClientTable();
		else if(whichCard == VEHICLE_INFO)
			vehicleInfo.updateVehicleTable();
	}
	
	

	public void changeCards(int card){
		backButton.setEnabled(true);
		finishButton.setEnabled(false);
		step1.setForeground(null);
		step2.setForeground(null);
		step3.setForeground(null);
		if(card == CUSTOMER_INFO){
			layout.show(cards, "customer info");
			step1.setForeground(Color.green);
			backButton.setEnabled(false);
			model.setBackCard(-1);
			model.setCurrentCard(CUSTOMER_INFO);
			model.setNextCard(VEHICLE_INFO);
		}else if(card == CUSTOMER_CREATE){
			layout.show(cards, "customer create");
			step1.setForeground(Color.green);
			model.setBackCard(-1);
			model.setCurrentCard(CUSTOMER_CREATE);
			model.setNextCard(VEHICLE_INFO);
		}else if(card == VEHICLE_INFO){
			layout.show(cards, "vehicle info");
			step2.setForeground(Color.green);
			model.setBackCard(CUSTOMER_INFO);
			model.setCurrentCard(VEHICLE_INFO);
			model.setNextCard(CUSTOMER_CONCERNS);
		}else if(card == VEHICLE_CREATE){
			layout.show(cards, "vehicle create");
			step2.setForeground(Color.green);
			model.setBackCard(CUSTOMER_INFO);
			model.setCurrentCard(VEHICLE_CREATE);
			model.setNextCard(CUSTOMER_CONCERNS);
		}else if(card == CUSTOMER_CONCERNS){
			layout.show(cards, "customer concerns");
			step3.setForeground(Color.green);
			finishButton.setEnabled(true);
			model.setBackCard(VEHICLE_INFO);
			model.setCurrentCard(CUSTOMER_CONCERNS);
			model.setNextCard(-1);
		}
	}
	
	
	public PMAWizard(JFrame frame,PMAWizardModel model){
		super(new BorderLayout());
		this.frame = frame;
		this.model = model;

		wizard = new JDialog(frame);
		wizard.pack();
		
		layout = new CardLayout();
		cards = new JPanel(layout);
		cards.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
		
		
		customerInfo = new card0();
		customerCreate = new card1();
		vehicleInfo = new card2();
		vehicleCreate = new card3();
		customerConcerns = new card4();
		
		custID = -1;//important 
		
		cards.add(customerInfo, "customer info");
		cards.add(customerCreate, "customer create");
		cards.add(vehicleInfo, "vehicle info");
		cards.add(vehicleCreate, "vehicle create");
		cards.add(customerConcerns, "customer concerns");
		
		JLabel titel = new JLabel("<HTML><u>Steps</u></HTML>");
		step1 = new JLabel("1. Customer Information");
		step1.setForeground(Color.GREEN);
		step2 = new JLabel("2. Vehicle Information");
		step3 = new JLabel("3. Customer Concerns");
		
		Box stepsBox = new Box(BoxLayout.Y_AXIS);
		stepsPanel = new JPanel(new BorderLayout());
		stepsPanel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.EAST);
		
		stepsBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
		
		stepsBox.add(titel);
		stepsBox.add(Box.createVerticalStrut(15));
		stepsBox.add(step1);
		stepsBox.add(Box.createVerticalStrut(10));
		stepsBox.add(step2);
		stepsBox.add(Box.createVerticalStrut(10));
		stepsBox.add(step3);
		stepsPanel.add(stepsBox, java.awt.BorderLayout.CENTER);
		add(stepsPanel, java.awt.BorderLayout.WEST);
		
		
		backButton = new JButton("< Back");
		backButton.setEnabled(false);
		nextButton = new JButton("Next >");
		nextButton.setEnabled(false);
		cancelButton = new JButton("Cancel");
		finishButton = new JButton("Finish");
		finishButton.setEnabled(false);

		
		Box buttonBox = new Box(BoxLayout.X_AXIS);
		buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(new JSeparator(), BorderLayout.NORTH);
		
		buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
	
		buttonBox.add(backButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(nextButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(cancelButton);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(finishButton);
		buttonPanel.add(buttonBox, java.awt.BorderLayout.EAST);
		add(buttonPanel, java.awt.BorderLayout.SOUTH);
		add(cards, java.awt.BorderLayout.CENTER);
	}
	
	/**
	 * CUSTOMER INFORMATION CARD
	 * alternative flow CUSTOMER CREATE CARD
	 * 
	 * @author adrian and oscar
	 *
	 */
	private class card0 extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private final JButton create, search;
		private final JScrollPane tableScroll;
		private final JTextField firstNameField,lastNameField,phoneField,custIDField;
		private final JLabel firstNameLabel,lastNameLabel,phoneLabel,custIDLabel;
		private DefaultTableModel tablemodel;
		
		private card0(){
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
			
			firstNameLabel = new JLabel("First Name");
			firstNameField = new JTextField(10);
			lastNameLabel = new JLabel("Last Name");
			lastNameField = new JTextField(10);
			phoneLabel = new JLabel("Phone");
			phoneField = new JTextField(10);
			custIDLabel = new JLabel("Customer ID");
			custIDField = new JTextField(10);
			search = new JButton("Search");
			create = new JButton("Create New");
			
			c.gridx=0;
			c.gridy=0;
			c.gridwidth=1;
			add(firstNameLabel,c);
			c.gridx=1;
			add(lastNameLabel,c);
			c.gridx=2;
			add(phoneLabel, c);
			c.gridx=3;
			add(custIDLabel,c);
			
			c.gridx=0;
			c.gridy=1;
			add(firstNameField, c);
			c.gridx=1;
			add(lastNameField, c);
			c.gridx=2;
			add(phoneField, c);
			c.gridx=3;
			add(custIDField,c);
			
			c.gridx=2;
			c.gridy=2;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(search,c);
			c.gridx=3;
			add(create,c);
			
			
			tablemodel = new CustomTableModel();
			tablemodel.setColumnIdentifiers(new Object[] {"First Name","Last Name","Address", "Phone", "Cust ID"});
			custTable = new JTable(tablemodel);
			custTable.setFont(new Font("Symbol-Plain", Font.PLAIN, 13));
			custTable.setRowSelectionAllowed(true);
			custTable.setColumnSelectionAllowed(false);
			custTable.setAutoCreateRowSorter(true);
			custTable.getTableHeader().setReorderingAllowed(false);
			custTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			custTable.setRowHeight(custTable.getRowHeight() + 3);
			custTable.setFillsViewportHeight(true);
			custTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			TableColumn col;
			CenterTableCellRenderer centerRenderer;

			col = custTable.getColumnModel().getColumn(0);
			col.setPreferredWidth(125);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);

			col = custTable.getColumnModel().getColumn(1);
			col.setPreferredWidth(135);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);

			col = custTable.getColumnModel().getColumn(2);
			col.setPreferredWidth(175);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);

			col = custTable.getColumnModel().getColumn(3);
			col.setPreferredWidth(120);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);
			
			col = custTable.getColumnModel().getColumn(4);
			col.setPreferredWidth(0);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);
			
			tableScroll = new JScrollPane(custTable);
			Dimension tranTablePreferred = tableScroll.getPreferredSize();
			tableScroll.setPreferredSize(new Dimension(tranTablePreferred.width,tranTablePreferred.height / 3));
			
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(tableScroll,c);
			
			custTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				int row;
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					if((row = custTable.getSelectedRow()) >= 0){
						custID = Integer.parseInt((String)custTable.getValueAt(row, 4));
						nextButton.setEnabled(true);
					}
				}
			});	
			search.addActionListener(new ActionListener(){
				ResultSet rs;
				public void actionPerformed(ActionEvent arg0) {
					nextButton.setEnabled(false);
					//custID = -1;
					updateClientTable();
				}
			});
				
			create.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					setEditableFields(true,CUSTOMER_CREATE);
					clearFields(CUSTOMER_CREATE);
					customerCreate.setCreateButtonEnabled(true);
					customerCreate.setBackArrowButtonEnabled(true);
					changeCards(CUSTOMER_CREATE);
				}
			});
		}
		public void updateClientTable(){
			ResultSet rs = Security.client.search(custIDField.getText(), firstNameField.getText(), Security.client.EXACTLY, lastNameField.getText(),Security.client.EXACTLY, 
					null, null, null, null, null, null);
			Object[] tmpRow;
			tablemodel.setRowCount(0);
			try {
				while(rs.next()){
						tmpRow = new Object[]{ rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(9), rs.getString(1)};
						tablemodel.addRow(tmpRow);
				}
				rs.close();
			} catch (SQLException e){
				e.printStackTrace(); //handle error
			}
		}
	}
	
	
	/**
	 * alternative flow CUSTOMER CREATE CARD
	 * subcard of CUSTOMER INFORMATION CARD
	 * 
	 * @author adrian and oscar
	 *
	 */
	private class card1 extends JPanel{
		
		private static final long serialVersionUID = 1L;

		private final String[] states = { "", "AL", "AK", "AZ", "AR", "CA",
			"CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
			"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
			"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA",
			"RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI",
			"WY" };
		
		
		private final JFormattedTextField phoneField;
		private final JComboBox stateField;
		private final JTextField firstNameField, lastNameField, emailField, addressField, cityField, zipField;
		private final JLabel firstNameLabel,lastNameLabel,phoneLabel,emailLabel,addressLabel,cityLabel,stateLabel,zipLabel;
		private JButton createButton,backArrow;
		private card1(){
			JPanel mid = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
			
			MaskFormatter mask = null;
			try {
				mask = new MaskFormatter("(###) ###-####");
				mask.setPlaceholderCharacter('x');
			} catch (ParseException e) {
				Error.MaskFormatterError();
				frame.dispose();
			}
			firstNameLabel = new JLabel("First Name");
			firstNameField = new JTextField(15);
			lastNameLabel = new JLabel("Last Name");
			lastNameField = new JTextField(15);
			phoneLabel = new JLabel("Phone");
			phoneField = new JFormattedTextField(mask);
			emailLabel = new JLabel("Email");
			emailField = new JTextField(15);
			addressLabel = new JLabel("Street Address");
			addressField = new JTextField(20);
			cityLabel = new JLabel("City");
			cityField = new JTextField(15);
			stateLabel = new JLabel("State");
			stateField = new JComboBox(states);
			zipLabel = new JLabel("Zip");
			zipField = new JTextField(15);
			createButton = new JButton("Create");
			
			c.gridx=0;
			c.gridy=0;
			c.gridwidth=1;
			mid.add(firstNameLabel,c);
			c.gridx=1;
			mid.add(lastNameLabel,c);
			
			c.gridx=0;
			c.gridy=1;
			mid.add(firstNameField, c);
			c.gridx=1;
			mid.add(lastNameField, c);
			
			c.gridx=0;
			c.gridy=2;
			mid.add(phoneLabel,c);
			c.gridx=0;
			c.gridy=3;
			c.fill = GridBagConstraints.HORIZONTAL;
			mid.add(phoneField,c);
			
			c.gridx=0;
			c.gridy=4;
			mid.add(emailLabel,c);
			
			c.gridx=0;
			c.gridy=5;
			c.gridwidth=2;
			c.fill = GridBagConstraints.HORIZONTAL;
			mid.add(emailField,c);
			
			c.gridx = 0;
			c.gridy=6;
			mid.add(addressLabel,c);
			
			c.gridx=0;
			c.gridy=7;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			mid.add(addressField, c);
			
			c.gridx=0;
			c.gridy=8;
			c.gridwidth=1;
			mid.add(cityLabel,c);
			c.gridx=1;
			mid.add(stateLabel, c);
			
			c.gridx=0;
			c.gridy=9;
			mid.add(cityField,c);
			c.gridx=1;
			mid.add(stateField,c);
			
			c.gridx=0;
			c.gridy=10;
			mid.add(zipLabel,c);
			
			c.gridy=11;
			mid.add(zipField,c);
		
			c.gridx=1;
			c.gridy=12;
			mid.add(createButton,c);
			
			
			backArrow = new JButton("<");
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(backArrow, BorderLayout.WEST);
			add(buttonPanel,BorderLayout.NORTH);
			
			add(mid, BorderLayout.CENTER);
			
			backArrow.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					custTable.clearSelection();
					changeCards(CUSTOMER_INFO);
				}
			});
			
			createButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(!emptyField()){
						if((custID = Security.client.addCustomer(firstNameField.getText(), lastNameField.getText(), addressField.getText(), cityField.getText(), stateField.getSelectedItem().toString(), zipField.getText(), 
								phoneField.getText(), emailField.getText())) >= 0){
							JOptionPane.showMessageDialog(null,"Successfully added Client " + firstNameField.getText() + " " + lastNameField.getText());
							setEditableFields(false);
							backArrow.setEnabled(false);
							nextButton.setEnabled(true);
							createButton.setEnabled(false);
						}else
							Error.AddClientFailed();
					} else 
						Error.EmptyField();
				}
			});
		}

		private boolean emptyField() {
			return ((firstNameField.getText().equals(""))
					|| (lastNameField.getText().equals(""))
					|| (phoneField.getText().equals("(xxx) xxx-xxxx"))
					|| (emailField.getText().equals(""))
					|| (addressField.getText().equals(""))
					|| (cityField.getText().equals(""))
					|| (stateField.getSelectedIndex() == 0) || (zipField.getText()
					.equals("")));
		}
		
		private void clearFields(){
			firstNameField.setText("");
			lastNameField.setText("");
			emailField.setText("");
			addressField.setText("");
			cityField.setText("");
			zipField.setText("");
			phoneField.setValue("");
			stateField.setSelectedIndex(0);
		}
		private void setEditableFields(boolean b){
			firstNameField.setEditable(b);
			lastNameField.setEditable(b);
			emailField.setEditable(b);
			addressField.setEditable(b);
			cityField.setEditable(b);
			zipField.setEditable(b);
			phoneField.setEditable(b);
			stateField.setEnabled(b);
		}
		private void setCreateButtonEnabled(boolean b){
			createButton.setEnabled(b);
		}
		private void setBackArrowButtonEnabled(boolean b){
			backArrow.setEnabled(b);
		}
	}
	
	/**
	 * VEHICLE INFORMATION CARD
	 * alternative flow VEHICLE CREATE CARD
	 * 
	 * @author adrian and oscar
	 *
	 */
	private class card2 extends JPanel{
		private static final long serialVersionUID = 1L;
		private JButton create;
		private JScrollPane tableScroll;
		private JTable table;
		
		private card2(){
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
		
			vehicleTablemodel = new CustomTableModel();
			vehicleTablemodel.setColumnIdentifiers(new Object[] {"Year",
					"Make", "Model", "License Plate", "VIN"});
			
			table = new JTable(vehicleTablemodel);
			table.setFont(new Font("Symbol-Plain", Font.PLAIN, 13));
			table.setRowSelectionAllowed(true);
			table.setColumnSelectionAllowed(false);
			table.setAutoCreateRowSorter(true);
			table.getTableHeader().setReorderingAllowed(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setRowHeight(table.getRowHeight() + 3);
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			create = new JButton("Add New Vehicle");
		
			TableColumn col;
			CenterTableCellRenderer centerRenderer;

			col = table.getColumnModel().getColumn(0);
			col.setPreferredWidth(84);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);

			col = table.getColumnModel().getColumn(1);
			col.setPreferredWidth(130);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);

			col = table.getColumnModel().getColumn(2);
			col.setPreferredWidth(115);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);

			col = table.getColumnModel().getColumn(3);
			col.setPreferredWidth(121);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);
			
			col = table.getColumnModel().getColumn(4);
			col.setPreferredWidth(0);
			centerRenderer = new CenterTableCellRenderer();
			col.setCellRenderer(centerRenderer);
			
			tableScroll = new JScrollPane(table);
			Dimension tranTablePreferred = tableScroll.getPreferredSize();
			tableScroll.setPreferredSize(new Dimension(tranTablePreferred.width,
					tranTablePreferred.height / 3));
			
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(tableScroll,c);
			
			
			c.gridx=3;
			c.gridy=1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(create,c);
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					int row;
					if((row = table.getSelectedRow()) >= 0){
						vehicleVin = (String)table.getValueAt(row, 4);
						System.out.println("vehicleVIN = "+vehicleVin);
						nextButton.setEnabled(true);
					}
				}
			});
			
			create.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					clearFields(VEHICLE_CREATE);
					setEditableFields(true,VEHICLE_CREATE);
					create.setEnabled(true);
					vehicleCreate.backArrow.setEnabled(true);
					vehicleCreate.createButton.setEnabled(true);
					changeCards(VEHICLE_CREATE);
				}
			});
			
		}
		public void updateVehicleTable(){
			ResultSet rs = Security.Vehicle.searchVehicles(custID);
			Object[] tmpRow;
			vehicleTablemodel.setRowCount(0);
			try {
				while(rs.next()){
						tmpRow = new Object[]{ rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(2), rs.getString(1)};
						vehicleTablemodel.addRow(tmpRow);
				}
				rs.close();
			} catch (SQLException e1){
				e1.printStackTrace(); //handle error
			}
			clearFields(CUSTOMER_CREATE);
		}
	}
	
	/**
	 * alternative flow VEHICLE CREATE CARD
	 * subcard of VEHICLE INFORMATION CARD
	 * 
	 * @author adrian and oscar
	 *
	 */
	private class card3 extends JPanel{
		private static final long serialVersionUID = 1L;
		private final JTextField yearField,makeField,modelField,vinField,licField,tagsField,engineField,transField,milesField;
		private final JLabel yearLabel,makeLabel,modelLabel,vinLabel,licLabel,tagsLabel,engineLabel,transLabel,milesLabel;
		public JButton createButton,backArrow;
		
		private card3(){
			JPanel mid = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
			
			yearLabel = new JLabel("Year");
			yearField = new JTextField(15);
			makeLabel = new JLabel("Make");
			makeField = new JTextField(15);
			modelLabel = new JLabel("Model");
			modelField = new JTextField(15);
			vinLabel = new JLabel("Vin");
			vinField = new JTextField(15);
			licLabel = new JLabel("License Plate");
			licField = new JTextField(15);
			tagsLabel = new JLabel("Tags");
			tagsField = new JTextField(15);
			engineLabel = new JLabel("Engine");
			engineField = new JTextField(15);
			transLabel = new JLabel("Trans");
			transField = new JTextField(15);
			milesLabel = new JLabel("Miles");
			milesField = new JTextField(15);
			createButton = new JButton("Create");
			
			c.gridx=0;
			c.gridy=0;
			c.gridwidth=1;
			mid.add(yearLabel,c);
			c.gridx=1;
			mid.add(makeLabel,c);
			
			c.gridx=0;
			c.gridy=1;
			mid.add(yearField, c);
			c.gridx=1;
			mid.add(makeField, c);
			
			c.gridx=0;
			c.gridy=2;
			mid.add(modelLabel,c);
			c.gridy=3;
			mid.add(modelField,c);
			
			c.gridx=1;
			c.gridy=2;
			mid.add(licLabel,c);
			c.gridy=3;
			mid.add(licField,c);
			
			c.gridx = 0;
			c.gridy=4;
			mid.add(vinLabel,c);
			c.gridx=1;
			mid.add(tagsLabel, c);
			
			c.gridx=0;
			c.gridy=5;
			mid.add(vinField, c);
			c.gridx=1;
			mid.add(tagsField,c);
			
			c.gridx=0;
			c.gridy=6;
			c.gridwidth=1;
			mid.add(engineLabel,c);
			c.gridx=1;
			mid.add(transLabel, c);
			
			c.gridx=0;
			c.gridy=7;
			mid.add(engineField,c);
			c.gridx=1;
			mid.add(transField,c);
			
			c.gridx=0;
			c.gridy=8;
			mid.add(milesLabel,c);
			
			c.gridy=9;
			mid.add(milesField,c);
		
			c.gridx=1;
			c.gridy=10;
			mid.add(createButton,c);
			
			backArrow = new JButton("<");
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(backArrow, BorderLayout.WEST);
			add(buttonPanel,BorderLayout.NORTH);
			add(mid, BorderLayout.CENTER);
			
			backArrow.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					changeCards(VEHICLE_INFO);
					
				}
			});
			
			createButton.addActionListener(new ActionListener(){
				private String vehicleInformation[];
				@Override
				public void actionPerformed(ActionEvent arg0) {
					vehicleInformation = new String[9];
					vehicleVin = vinField.getText();
					vehicleInformation[0] = vinField.getText();
					vehicleInformation[1] = licField.getText();
					vehicleInformation[2] = tagsField.getText();
					vehicleInformation[3] = yearField.getText();
					vehicleInformation[4] = makeField.getText();
					vehicleInformation[5] = modelField.getText();
					vehicleInformation[6] = engineField.getText();
					vehicleInformation[7] = transField.getText();
					vehicleInformation[8] = milesField.getText();
					if(Security.Vehicle.addVehicle(vehicleInformation, custID)){
						JOptionPane.showMessageDialog(null,
								"Successfully added " + yearField.getText() + " " + makeField.getText() + " " + modelField.getText() + " to customer ");
						nextButton.setEnabled(true);
						createButton.setEnabled(false);
						backArrow.setEnabled(false);
						setEditableFields(false);
					}
					else
						JOptionPane.showMessageDialog(null,
								"Failed to add " + yearField.getText() + " " + makeField.getText() + " " + modelField.getText() + " to customer ");
				}
				
			});
		}
		private void clearFields(){
			yearField.setText("");
			makeField.setText("");
			modelField.setText("");
			vinField.setText("");
			licField.setText("");
			tagsField.setText("");
			engineField.setText("");
			transField.setText("");
			milesField.setText("");
		}
		private void setEditableFields(boolean b){
			yearField.setEditable(b);
			makeField.setEditable(b);
			modelField.setEditable(b);
			vinField.setEditable(b);
			licField.setEditable(b);
			tagsField.setEditable(b);
			engineField.setEditable(b);
			transField.setEditable(b);
			milesField.setEditable(b);
		}
		

	}
	
	/**
	 * CUSTOMER CONCERNS CARD
	 * 
	 * @author adrian and oscar
	 *
	 */
	private class card4 extends JPanel{
		private static final long serialVersionUID = 1L;
		
		private card4(){
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			JLabel createdByLabel = new JLabel("This PMA was created by ");
			c.gridx = 0;
			c.gridy = 0;
			add(createdByLabel, c);
			createdByField = new JTextField(10);
			c.gridx = 0;
			c.gridy = 1;
			add(createdByField, c);
			JLabel customerConcernsLabel = new JLabel("Please enter Customer Concerns");
			c.gridx = 0;
			c.gridy = 2;
			add(customerConcernsLabel, c);
			custConcernsArea = new JTextArea(5,20);
			c.gridx = 0;
			c.gridy = 3;
			add(custConcernsArea, c);
			
		}	
	}
	
	
    private static void createAndShowGUI() {
        //Create and set up the window.
        
    	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		        	UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			try {
	           
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
			} 
			catch (UnsupportedLookAndFeelException e1) {
			}
			catch (ClassNotFoundException e1) {
	    
			}
			catch (InstantiationException e1) {
	      
			}
			catch (IllegalAccessException e1) {
	      
			}
		}		
    	
    	Security.Login("birdman", "password");
    	JFrame frame = new JFrame("PMA Wizard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        PMAWizardModel model = new PMAWizardModel();
        PMAWizard newContentPane = new PMAWizard(frame, model);
        PMAWizardController controller = new PMAWizardController(newContentPane, model);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
}