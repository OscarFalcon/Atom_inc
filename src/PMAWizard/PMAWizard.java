package PMAWizard;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.windows.resources.windows;

public class PMAWizard extends JPanel{
	JFrame frame;
	JDialog wizard;
	JPanel cards, buttonPanel, stepsPanel;
	PMAWizardModel model;
	
	private CardLayout layout;
	private JPanel customerInfo, customerCreate, vehicleInfo, vehicleCreate, customerConcerns;
	private JLabel step1, step2, step3;
	
	public JButton backButton, nextButton, cancelButton, finishButton;
	private int CUSTOMER_INFO = 0, CUSTOMER_CREATE = 1, VEHICLE_INFO = 2, VEHICLE_CREATE = 3, CUSTOMER_CONCERNS = 4;
	
	
	public void changeCards(int card){
		backButton.setEnabled(true);
		nextButton.setEnabled(true);
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
			backButton.setEnabled(false);
			nextButton.setEnabled(false);
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
			backButton.setEnabled(false);
			nextButton.setEnabled(false);
			model.setBackCard(CUSTOMER_INFO);
			model.setCurrentCard(VEHICLE_CREATE);
			model.setNextCard(CUSTOMER_CONCERNS);
		}else if(card == CUSTOMER_CONCERNS){
			layout.show(cards, "customer concerns");
			step3.setForeground(Color.green);
			nextButton.setEnabled(false);
			finishButton.setEnabled(true);
			model.setBackCard(VEHICLE_INFO);
			model.setCurrentCard(CUSTOMER_CONCERNS);
			model.setNextCard(-1);
		} else
			System.out.println("erorr");
	}
	
	
	public PMAWizard(final JFrame frame, final PMAWizardModel model){
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
		//nextButton.setEnabled(false);
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
	
	
	private class card0 extends JPanel{
		
		private JButton create, search;
		
		private card0(){
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
			
			JLabel firstNameLabel = new JLabel("First Name");
			JTextField firstNameField = new JTextField(10);
			JLabel lastNameLabel = new JLabel("Last Name");
			JTextField lastNameField = new JTextField(10);
			JLabel phoneLabel = new JLabel("Phone");
			JTextField phoneField = new JTextField(10);
			JLabel custIDLabel = new JLabel("Customer ID");
			JTextField custIDField = new JTextField(10);
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

			
			create.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					changeCards(CUSTOMER_CREATE);
				}
			});
			
		}
	}
	

	
	private class card1 extends JPanel{
		private final String[] states = { "", "AL", "AK", "AZ", "AR", "CA",
			"CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
			"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
			"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA",
			"RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI",
			"WY" };
		
		
		private card1(){
			JPanel mid = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
			
			JLabel firstNameLabel = new JLabel("First Name");
			JTextField firstNameField = new JTextField(15);
			JLabel lastNameLabel = new JLabel("Last Name");
			JTextField lastNameField = new JTextField(15);
			JLabel phoneLabel = new JLabel("Phone");
			JTextField phoneField = new JTextField(15);
			JLabel addressLabel = new JLabel("Street Address");
			JTextField addressField = new JTextField(20);
			JLabel cityLabel = new JLabel("City");
			JTextField cityField = new JTextField(15);
			JLabel stateLabel = new JLabel("State");
			JComboBox stateField = new JComboBox(states);
			JLabel zipLabel = new JLabel("Zip");
			JTextField zipField = new JTextField(15);
			JButton createButton = new JButton("Create");
			
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
			mid.add(phoneField,c);
			
			c.gridx = 0;
			c.gridy=4;
			mid.add(addressLabel,c);
			
			c.gridx=0;
			c.gridy=5;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			mid.add(addressField, c);
			
			c.gridx=0;
			c.gridy=6;
			c.gridwidth=1;
			mid.add(cityLabel,c);
			c.gridx=1;
			mid.add(stateLabel, c);
			
			c.gridx=0;
			c.gridy=7;
			mid.add(cityField,c);
			c.gridx=1;
			mid.add(stateField,c);
			
			c.gridx=0;
			c.gridy=8;
			mid.add(zipLabel,c);
			
			c.gridy=9;
			mid.add(zipField,c);
		
			c.gridx=1;
			c.gridy=10;
			mid.add(createButton,c);
			
			
			JButton backArrow = new JButton("<");
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(backArrow, BorderLayout.WEST);
			add(buttonPanel,BorderLayout.NORTH);
			
			add(mid, BorderLayout.CENTER);
			
			backArrow.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					changeCards(CUSTOMER_INFO);
				}
			});
			
		}
	}
	
	private class card2 extends JPanel{
		private JButton create, search;
		
		
		private card2(){
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
		
			JLabel firstNameLabel = new JLabel("Vehicle Make");
			JTextField firstNameField = new JTextField(10);
			JLabel lastNameLabel = new JLabel("Vehicle Model");
			JTextField lastNameField = new JTextField(10);
			JLabel phoneLabel = new JLabel("Lic");
			JTextField phoneField = new JTextField(10);
			JLabel custIDLabel = new JLabel("Vin");
			JTextField custIDField = new JTextField(10);
			search = new JButton("Search");
			create = new JButton("Add New Vehicle");
		
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
			
			
			create.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					changeCards(VEHICLE_CREATE);
				}
			});
			
		}
		
	}
	private class card3 extends JPanel{
		
		
		private card3(){
			JPanel mid = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 5, 2, 5);
			
			JLabel yearLabel = new JLabel("Year");
			JTextField yearField = new JTextField(15);
			JLabel makeLabel = new JLabel("Make");
			JTextField makeField = new JTextField(15);
			JLabel modelLabel = new JLabel("Model");
			JTextField modelField = new JTextField(15);
			JLabel licLabel = new JLabel("License Plate");
			JTextField licField = new JTextField(20);
			JLabel vinLabel = new JLabel("Vin");
			JTextField vinField = new JTextField(15);
			JLabel engineLabel = new JLabel("Engine");
			JTextField engineField = new JTextField(15);
			JLabel transLabel = new JLabel("Trans");
			JTextField transField = new JTextField(15);
			JLabel milesLabel = new JLabel("Miles");
			JTextField milesField = new JTextField(15);
			JButton createButton = new JButton("Create");
			
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
			c.gridx=0;
			c.gridy=3;
			mid.add(modelField,c);
			
			c.gridx = 0;
			c.gridy=4;
			mid.add(licLabel,c);
			
			c.gridx=0;
			c.gridy=5;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			mid.add(licField, c);
			
			c.gridx=0;
			c.gridy=6;
			c.gridwidth=1;
			mid.add(vinLabel,c);
			c.gridx=1;
			mid.add(engineLabel, c);
			
			c.gridx=0;
			c.gridy=7;
			mid.add(vinField,c);
			c.gridx=1;
			mid.add(engineField,c);
			
			c.gridx=0;
			c.gridy=8;
			mid.add(transLabel,c);
			
			c.gridy=9;
			mid.add(transField,c);
		
			c.gridx=1;
			c.gridy=10;
			mid.add(createButton,c);
			
			
			JButton backArrow = new JButton("<");
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(backArrow, BorderLayout.WEST);
			add(buttonPanel,BorderLayout.NORTH);
			
			add(mid, BorderLayout.CENTER);
			
			backArrow.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					changeCards(VEHICLE_INFO);
				}
			});
		}
	}
	
	private class card4 extends JPanel{
		private card4(){
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			JLabel createdByLabel = new JLabel("This PMA was created by ");
			c.gridx = 0;
			c.gridy = 0;
			add(createdByLabel, c);
			JTextField createdByField = new JTextField(10);
			c.gridx = 0;
			c.gridy = 1;
			add(createdByField, c);
			JLabel customerConcernsLabel = new JLabel("Please enter Customer Concerns");
			c.gridx = 0;
			c.gridy = 2;
			add(customerConcernsLabel, c);
			JTextArea customerConcerns = new JTextArea(5,20);
			c.gridx = 0;
			c.gridy = 3;
			add(customerConcerns, c);
			
		}
	}
	
	
    private static void createAndShowGUI() {
        //Create and set up the window.
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