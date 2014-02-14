package CustMgtSys_1;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class AboutSearch extends JDialog {

	
	private JTextArea textBox;

	public AboutSearch() {
		super();
		
		textBox = new JTextArea();
		textBox.setFont(new Font("Symbol-Plain", Font.BOLD, 13));
		textBox.setEditable(false);
		textBox.setText("\t\t\tAdvanced Search\n\n\nAdvanced Search allows you to search for a customer using any combination of criteria!\n"
				+ "For first and last name, you can select \"exactly\" or \"matches\" mode. If \"exactly\" is selected\n"
				+ "Advanced Search will return customers whose first or last name is EXACTLY what you typed in\n"
				+ "the respective search field. If \"matches\" is selected, Advanced Search will return customers whose\n"
				+ "first or last names match what you typed in the respective search field. For example, if you do not\n"
				+ "know how to spell the last name of a certain customer, and you know only that it begins with “Fa”,\n"
				+ "you would put “Fa” in the last name field and hit search. Advanced Search will return all customers\n"
				+ "whose last name has an “Fa” in it. To further narrow your search, you can input into multiple search\n"
				+ "fields. For example, inputing “John” with the exactly box checked for first name, “W” with the matches\n"
				+ "box checked for last name, “San Antonio”  for city, and “TX” selected in the state drop down,  will return\n" 
				+ "all customers whose first name is exactly John whose last name contains a “W”, and  who live in the city \n " 
				+ "\"San Antonio\" in the state of Texas. You can fill in as many fields as you want as long as "
				+ "at least one is provided.\n\n\n");

		
		add(textBox);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		setSize(new Dimension(500,400));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);	
		
		
	
	}

}
