package CustMgtSys_1;

import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author Oscar APPROVED
 * 
 */

public class ClientInfo extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String[] labels = { "First Name: ", "Last Name: ",
			"Phone Number: ", "Email: ", "Address: ", "City: ", "State: ",
			"Zip: " };

	private static final String[] states = { "", "AL", "AK", "AZ", "AR", "CA",
			"CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
			"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
			"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA",
			"RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI",
			"WY" };

	private final JComboBox<String> stateList;
	private final JTextField firstNameField;
	private final JTextField lastNameField;
	private final JFormattedTextField phoneField;
	private final JTextField emailField;
	private final JTextField addressField;
	private final JTextField cityField;
	private final JTextField zipField;
	private final int rowIndex;

	private final JButton editButton, clear;
	private final JTable table;
	private Color darkBlue = new Color(0, 0, 51);
	private String first, last, address, city, zip, state, email, phone, id;
	private Border border = BorderFactory
			.createSoftBevelBorder(BevelBorder.LOWERED);

	public ClientInfo(final JTable table) {
		this.table = table;

		rowIndex = table.getSelectedRow();

		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(###) ###-####");
			mask.setPlaceholderCharacter('x');
		} catch (ParseException e) {
			Error.MaskFormatterError();
			dispose();
		}

		first = (String) table.getValueAt(rowIndex, 1);
		last = (String) table.getValueAt(rowIndex, 2);
		address = (String) table.getValueAt(rowIndex, 3);
		city = (String) table.getValueAt(rowIndex, 4);
		state = (String) table.getValueAt(rowIndex, 5);
		zip = (String) table.getValueAt(rowIndex, 6);
		email = (String) table.getValueAt(rowIndex, 7);
		phone = (String) table.getValueAt(rowIndex, 8);

		firstNameField = new JTextField(20);
		lastNameField = new JTextField(20);
		phoneField = new JFormattedTextField(mask);
		emailField = new JTextField(20);
		addressField = new JTextField(20);
		cityField = new JTextField(20);
		zipField = new JTextField(20);
		stateList = new JComboBox<String>(states);
		

		firstNameField.setText(first);
		firstNameField.setBorder(border);
		lastNameField.setText(last);
		lastNameField.setBorder(border);
		phoneField.setText(phone);
		phoneField.setBorder(border);
		emailField.setText(email);
		emailField.setBorder(border);
		addressField.setText(address);
		addressField.setBorder(border);
		cityField.setText(city);
		cityField.setBorder(border);
		zipField.setText(zip);
		zipField.setBorder(border);
		stateList.setSelectedItem(state);

		// Create and populate the panel.
		final JPanel p = new JPanel(new SpringLayout());
		JLabel filler;
		int numPairs = labels.length;
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);

			switch (i) {
			case 0:
				l.setLabelFor(firstNameField);
				firstNameField.setHorizontalAlignment(JTextField.LEFT);
				firstNameField.setEditable(false);
				p.add(firstNameField);
				break;
			case 1:
				l.setLabelFor(lastNameField);
				lastNameField.setHorizontalAlignment(JTextField.LEFT);
				lastNameField.setEditable(false);
				p.add(lastNameField);
				break;
			case 2:
				l.setLabelFor(phoneField);
				phoneField.setHorizontalAlignment(JTextField.LEFT);
				phoneField.setEditable(false);
				p.add(phoneField);
				break;
			case 3:
				l.setLabelFor(emailField);
				emailField.setHorizontalAlignment(JTextField.LEFT);
				emailField.setEditable(false);
				p.add(emailField);
				break;
			case 4:
				l.setLabelFor(addressField);
				addressField.setHorizontalAlignment(JTextField.LEFT);
				addressField.setEditable(false);
				p.add(addressField);
				break;
			case 5:
				l.setLabelFor(cityField);
				cityField.setHorizontalAlignment(JTextField.LEFT);
				cityField.setEditable(false);
				p.add(cityField);
				break;
			case 6:
				l.setLabelFor(stateList);
				stateList.setEnabled(false);
				p.add(stateList);
				break;
			case 7:
				l.setLabelFor(zipField);
				zipField.setHorizontalAlignment(JTextField.LEFT);
				zipField.setEditable(false);
				p.add(zipField);
				break;

			}// switch
			filler = new JLabel();
			p.add(filler);
			filler = new JLabel();
			p.add(filler);
		}// for

		clear = new JButton("Reset");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNameField.setText(first);
				lastNameField.setText(last);
				phoneField.setValue(phone);
				emailField.setText(email);
				addressField.setText(address);
				cityField.setText(city);
				zipField.setText(zip);
				stateList.setSelectedItem(state);
			}
		});

		clear.setEnabled(false);
		p.add(clear);

		editButton = new JButton("Edit");
		p.add(editButton);
		editButton.addActionListener(new EditButtonListener());

		SpringUtilities.makeCompactGrid(p, numPairs + 1 + 8, 2, 6, 6, 6, 6);
		p.setOpaque(true);
		p.setBorder(new LineBorder(darkBlue));
		add(p);
		
		pack();
		setTitle("TwoFatGuys");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private boolean noChanges() {
		return firstNameField.getText().equals(first)
				&& lastNameField.getText().equals(last)
				&& addressField.getText().equals(address)
				&& cityField.getText().equals(city)
				&& ((String) stateList.getSelectedItem()).equals(state)
				&& zipField.getText().equals(zip)
				&& emailField.getText().equals(email)
				&& (phoneField.getText()).equals(phone); // test
	}

	private boolean emptyField() {
		return ((firstNameField.getText().equals(""))
				|| (lastNameField.getText().equals(""))
				|| (phoneField.getText().equals("(xxx) xxx-xxxx"))
				|| (emailField.getText().equals(""))
				|| (addressField.getText().equals(""))
				|| (cityField.getText().equals(""))
				|| (stateList.getSelectedIndex() == 0) || (zipField.getText()
				.equals("")));
	}

	private class EditButtonListener implements ActionListener {
		boolean b = true; // true for edit, false for submit

		public void actionPerformed(ActionEvent e) {

			if (b) { // did user hit edit button?
				clear.setEnabled(true);
				editButton.setText("Submit");
				firstNameField.setEditable(true);
				lastNameField.setEditable(true);
				phoneField.setEditable(true);
				emailField.setEditable(true);
				addressField.setEditable(true);
				cityField.setEditable(true);
				stateList.setEnabled(true);
				zipField.setEditable(true);
				b = false;
			} else { // submit was clicked

				if (noChanges()) // were there changes to the data fields?
									// pointless to submit the same info to
									// server
					JOptionPane.showMessageDialog(null, "No changes were made");
				else { // there was changes to data, proceed

					if (emptyField()) { // is any field empty
						JOptionPane.showMessageDialog(null,
								"All Fields must be filled");
					} else { // they made a valid change, proceed to send
								// request to server

						first = firstNameField.getText();
						last = lastNameField.getText();
						address = addressField.getText();
						city = cityField.getText();
						state = (String) stateList.getSelectedItem();
						zip = zipField.getText();
						email = emailField.getText();
						phone = (String) phoneField.getText();

						table.getModel().setValueAt(first, rowIndex, 1);
						table.getModel().setValueAt(last, rowIndex, 2);
						table.getModel().setValueAt(address, rowIndex, 3);
						table.getModel().setValueAt(city, rowIndex, 4);
						table.getModel().setValueAt(state, rowIndex, 5);
						table.getModel().setValueAt(zip, rowIndex, 6);
						table.getModel().setValueAt(email, rowIndex, 7);
						table.getModel().setValueAt(phone, rowIndex, 8);
						Integer id = (Integer) table.getValueAt(rowIndex, 0);
						
						if (Security.clientDatabase.updateCustomer(id.toString(), first, last, address,
								city, state, zip, phone, email)) {
							JOptionPane.showMessageDialog(null,
									"Successfully edited Client " + first + " "
											+ last);
							dispose();
						}// if
					}// valid changes
				}// no changes
			}// submit
		}// actionPerformed

	}// EditButton class

}// class

