package CustMgtSys_1;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author Oscar: APPROVED
 *
 */

public class AddClientWindow extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JFormattedTextField phoneField;
    private final JTextField emailField;
    private final JTextField addressField;
    private final JTextField cityField;
    private final JTextField zipField;
    private final JComboBox stateList;;
    
	private final JButton addButton, cancelButton; //button on display
	
	private static final String[] states = {"","AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY",
    		"LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR",
    		"PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
	
	private static final String[] labels = {"First Name: ", "Last Name: ", "Phone Number: ", "Email: ", "Address: ", "City: ","State:", "Zip: "};
	int numPairs = labels.length; 

	private Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    

	public AddClientWindow(){
		setTitle("TwoFatGuys");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);         

		MaskFormatter mask = null; 
        try {
        	mask = new MaskFormatter("(###) ###-####");
            	mask.setPlaceholderCharacter('x');
        } catch (ParseException e) {
        	  JOptionPane.showMessageDialog(null,"Error loading add client screen: mask formatter");
        	  dispose();
        } 
           
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        phoneField = new JFormattedTextField(mask);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        cityField = new JTextField(20);
        zipField = new JTextField(20);
        stateList = new JComboBox(states);
               
        //Create and populate the panel.
        final JPanel p = new JPanel(new SpringLayout());
        JLabel filler;
        
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            
            switch(i){
                    case 0:
                            l.setLabelFor(firstNameField);
                            firstNameField.setBorder(border);
                            p.add(firstNameField);
                            break;
                    case 1:
                            l.setLabelFor(lastNameField);
                            lastNameField.setBorder(border);
                            p.add(lastNameField);
                            break;
                    case 2:
                            l.setLabelFor(phoneField);
                            phoneField.setBorder(border);
                            p.add(phoneField);
                            break;
                    case 3:
                            l.setLabelFor(emailField);
                            emailField.setBorder(border);
                            p.add(emailField);
                            break;
                    case 4:
                            l.setLabelFor(addressField);
                            addressField.setBorder(border);
                            p.add(addressField);
                            break;
                    case 5:
                            l.setLabelFor(cityField);
                            cityField.setBorder(border);
                            p.add(cityField);
                            break;                     
                    case 6: 
                    	l.setLabelFor(stateList);
                    	p.add(stateList);
                    	break;
                    case 7:
                            l.setLabelFor(zipField);
                            zipField.setBorder(border);
                            p.add(zipField);
                            break;
            }//switch
            filler = new JLabel();
			p.add(filler);
			filler = new JLabel();
			p.add(filler);
        }//for
    
   
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelListener());
        p.add(cancelButton);
 
        addButton = new JButton("Add");
        addButton.addActionListener(new AddClientListener());
        p.add(addButton);
        
        SpringUtilities.makeCompactGrid(p, numPairs+1+8, 2, 6, 6, 6, 6);
        p.setOpaque(true); //content panes must be opaque
        setContentPane(p);
        
        //frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }//AddClientWindow constructor
	
    /**
     * @return true: if all the textFields are empty
     		   false: otherwise 
     */
	private boolean emptyFields(){
		return ((firstNameField.getText().equals("")) || (lastNameField.getText().equals("")) || (phoneField.getText().equals("(xxx) xxx-xxxx")) || (emailField.getText().equals(""))
                || (addressField.getText().equals("")) || (cityField.getText().equals("")) || (stateList.getSelectedIndex() == 0)
                || (zipField.getText().equals("")));
	}
	
	private class AddClientListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if( !emptyFields() ){
    			if( Security.client.addCustomer(firstNameField.getText(),lastNameField.getText(),addressField.getText(),cityField.getText(),(String)stateList.getSelectedItem(),zipField.getText(),phoneField.getText(),emailField.getText())>=0){
    				JOptionPane.showMessageDialog(null, "Successfully added new Client " + firstNameField.getText() + " " + lastNameField.getText());
    				dispose();
    			}
            }else 
            	JOptionPane.showMessageDialog(null, "All Fields must be filled");
			
		}
		
	}
	private class CancelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 JOptionPane.showMessageDialog(null, "Add client canceled");
             dispose();
		}
		
	}
	
	
	
}