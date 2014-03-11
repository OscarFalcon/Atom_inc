package CustMgtSys_1;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class SearchWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String[] states = { "", "AL", "AK", "AZ", "AR", "CA",
			"CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
			"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
			"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA",
			"RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI",
			"WY" };

	private static final Border border = BorderFactory
			.createBevelBorder(BevelBorder.LOWERED);

	private final JTextField firstField, lastField, addressField, cityField,
			zipField, emailField;
	private final JFormattedTextField phoneField;
	private final JComboBox<String> stateList;
	private final JCheckBox matchesFirstBox, exactlyFirstBox, matchesLastBox,
			exactlyLastBox;
	private JButton submit, about;

	public SearchWindow() {
		Font font; // font used throughout constructor
		
		/**
		 * Main panel will cover entire JFrame and compose of four
		 * panels(panelT-panel3) in a SpringLayout
		 */
		JPanel mainPanel, panelT, panel1, panel2, panel3, panel4;

		mainPanel = new JPanel(); // the main panel that covers entire JFrame
		mainPanel.setLayout(new SpringLayout());
		mainPanel.setPreferredSize(new Dimension(600, 250));

		// ************** Title Panel ************************************* //
		panelT = new JPanel();
		JLabel title = new JLabel("Advanced Search");
		panelT.add(title);
		// *************** panel1 *************************************** //
		/**
		 * Panel1 will be a 2X2 grid Layout consisting of the first and last
		 * name fields and labels as well as check boxes for matches and exactly
		 * 
		 */
		panel1 = new JPanel(); // top panel
		panel1.setLayout(new GridLayout(2, 2, 10, 5));

		// the 4 panels added to the 2 X 2 grid layout
		JPanel panel1A, panel1B, panel1C, panel1D;

		// top left panel in Panel1
		panel1A = new JPanel();
		panel1A.setLayout(new SpringLayout());
		JLabel firstLabel = new JLabel("   First name: ");
		firstField = new JTextField(15);
		firstField.setPreferredSize(new Dimension(250, 20));
		firstField.setBorder(border);
		panel1A.add(firstLabel);
		panel1A.add(firstField);
		SpringUtilities.makeCompactGrid(panel1A, 1, 2, 0, 0, 10, 0);

		// top right panel in Panel1
		panel1B = new JPanel();
		panel1B.setLayout(new SpringLayout());
		JLabel lastLabel = new JLabel("Last name: ");
		lastField = new JTextField(15);
		lastField.setPreferredSize(new Dimension(200, 20));
		lastField.setBorder(border);
		panel1B.add(lastLabel);
		panel1B.add(lastField);
		SpringUtilities.makeCompactGrid(panel1B, 1, 2, 0, 0, 10, 0);

		// bottom left panel of panel1
		panel1C = new JPanel();
		panel1C.setLayout(new GridLayout(1, 5, 0, 0));
		matchesFirstBox = new JCheckBox();
		exactlyFirstBox = new JCheckBox();
		JLabel matches = new JLabel("matches");
		font = new Font("Dialog-Italic", Font.PLAIN, 11);
		matches.setFont(font);
		JLabel exactly = new JLabel("exactly");
		exactly.setFont(font);
		JLabel filler = new JLabel();
		panel1C.add(filler);
		panel1C.add(matches);
		panel1C.add(matchesFirstBox);
		panel1C.add(exactly);
		panel1C.add(exactlyFirstBox);

		// bottom right of panel1
		panel1D = new JPanel();
		panel1D.setLayout(new GridLayout(1, 5, 0, 0));
		matchesLastBox = new JCheckBox();
		exactlyLastBox = new JCheckBox();
		matches = new JLabel("matches");
		matches.setFont(font);
		exactly = new JLabel("exactly");
		exactly.setFont(font);
		filler = new JLabel();
		panel1D.add(filler);
		panel1D.add(matches);
		panel1D.add(matchesLastBox);
		panel1D.add(exactly);
		panel1D.add(exactlyLastBox);

		exactlyFirstBox.setSelected(true);
		exactlyLastBox.setSelected(true);

		boxListener listen1 = new boxListener(exactlyFirstBox, matchesFirstBox);
		exactlyFirstBox.addActionListener(listen1);
		matchesFirstBox.addActionListener(listen1);

		boxListener listen2 = new boxListener(exactlyLastBox, matchesLastBox);
		exactlyLastBox.addActionListener(listen2);
		matchesLastBox.addActionListener(listen2);

		panel1.add(panel1A);
		panel1.add(panel1B);
		panel1.add(panel1C);
		panel1.add(panel1D);

		// *********** panel2 ******************** //

		Insets insets; // used for absolute positioning
		Dimension size;

		/**
		 * Panel2 uses absolute positioning to get a custom look
		 */
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setSize(new Dimension(150, 20));
		panel2.setPreferredSize(new Dimension(150, 20));

		// initialize and customize JLabels and JFields
		JLabel addressLabel = new JLabel("   Address:");
		addressLabel.setPreferredSize(new Dimension(150, 25));
		addressField = new JTextField();
		addressField.setBorder(border);
		addressField.setPreferredSize(new Dimension(150, 25));
		JLabel cityLabel = new JLabel("City:");
		cityLabel.setPreferredSize(new Dimension(125, 25));
		cityField = new JTextField();
		cityField.setBorder(border);
		cityField.setPreferredSize(new Dimension(125, 25));
		stateList = new JComboBox<String>(states);
		stateList.setPreferredSize(new Dimension(55, 25));
		JLabel zipLabel = new JLabel("Zip");
		zipLabel.setPreferredSize(new Dimension(65, 25));
		zipField = new JTextField();
		zipField.setBorder(border);
		zipField.setPreferredSize(new Dimension(200, 25));
		// add to panel2
		panel2.add(addressLabel);
		panel2.add(addressField);
		panel2.add(cityLabel);
		panel2.add(cityField);
		panel2.add(stateList);
		panel2.add(zipLabel);
		panel2.add(zipField);
		// absolute positioning
		insets = panel2.getInsets();
		size = addressLabel.getPreferredSize();
		addressLabel
				.setBounds(insets.left, insets.top, size.width, size.height);
		size = addressField.getPreferredSize();
		addressField.setBounds(75 + insets.left, insets.top, size.width,
				size.height);
		size = cityLabel.getPreferredSize();
		cityLabel.setBounds(240 + insets.left, insets.top, size.width,
				size.height);
		size = cityField.getPreferredSize();
		cityField.setBounds(280 + insets.left, insets.top, size.width,
				size.height);
		size = stateList.getPreferredSize();
		stateList.setBounds(425 + insets.left, insets.top, size.width,
				size.height);
		size = zipLabel.getPreferredSize();
		zipLabel.setBounds(485 + insets.left, insets.top, size.width,
				size.height);
		size = zipField.getPreferredSize();
		zipField.setBounds(515 + insets.left, insets.top, size.width,
				size.height);

		// ******** panel3 ********************** //
		/**
		 * Panel3 uses ablate positioning
		 */
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setSize(new Dimension(150, 20));
		panel3.setPreferredSize(new Dimension(150, 20));

		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(###) ###-####");
			mask.setPlaceholderCharacter('x');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setPreferredSize(new Dimension(150, 25));
		phoneField = new JFormattedTextField(mask);

		phoneField.setBorder(border);
		phoneField.setPreferredSize(new Dimension(120, 23));
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setPreferredSize(new Dimension(150, 25));
		emailField = new JTextField();
		emailField.setBorder(border);
		emailField.setPreferredSize(new Dimension(200, 25));

		panel3.add(phoneLabel);
		panel3.add(phoneField);
		panel3.add(emailLabel);
		panel3.add(emailField);
		// absolute positioning
		insets = panel3.getInsets();
		size = phoneLabel.getPreferredSize();
		phoneLabel.setBounds(50 + insets.left, insets.top, size.width,
				size.height);
		size = phoneField.getPreferredSize();
		phoneField.setBounds(110 + insets.left, insets.top, size.width,
				size.height);
		size = emailLabel.getPreferredSize();
		emailLabel.setBounds(300 + insets.left, insets.top, size.width,
				size.height);
		size = emailField.getPreferredSize();
		emailField.setBounds(350 + insets.left, insets.top, size.width,
				size.height);
		// ******** panel4 ******************* //
		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setSize(new Dimension(150, 20));
		panel4.setPreferredSize(new Dimension(150, 20));

		// Search Client Button
		Icon search = new ImageIcon("search.png");
		submit = new JButton("Search", search);

		submit.setPreferredSize(new Dimension(120, 25));

		about = new JButton("About");
		about.setPreferredSize(new Dimension(100, 25));

		panel4.add(submit);
		panel4.add(about);

		insets = panel4.getInsets();
		size = submit.getPreferredSize();
		submit.setBounds(320 + insets.right, insets.top, size.width,
				size.height);

		size = about.getPreferredSize();
		about.setBounds(180 + insets.left, insets.top, size.width, size.height);

		// **** Add panelT - panel3 to main panel **/
		mainPanel.add(panelT);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		SpringUtilities.makeCompactGrid(mainPanel, 5, 1, 0, 0, 20, 15);
		// add mainpanel to frame
		add(mainPanel);

		registerController(new SearchController());

		setTitle("TwoFatGuys - Advanced Search");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	// constructor

	private void registerController(SearchController listen) {
		firstField.addActionListener(listen);
		lastField.addActionListener(listen);
		addressField.addActionListener(listen);
		cityField.addActionListener(listen);
		zipField.addActionListener(listen);
		phoneField.addActionListener(listen);
		emailField.addActionListener(listen);
		submit.addActionListener(listen);
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutSearch();
			}
		});
	}

	private class boxListener implements ActionListener {
		JCheckBox box1, box2;

		public boxListener(JCheckBox box1, JCheckBox box2) {
			this.box1 = box1;
			this.box2 = box2;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == box1) {
				if (box1.isSelected()) {
					box2.setSelected(false);
					box1.setSelected(true);
				} else
					box1.setSelected(true);

			} else {
				if (box2.isSelected()) {
					box1.setSelected(false);
					box2.setSelected(true);
				} else
					box2.setSelected(true);
			}

		}// actionPerformed
	}// boxListener

	private boolean emptyFields() {
		return ((firstField.getText().equals(""))
				&& (lastField.getText().equals(""))
				&& (phoneField.getText().equals("(xxx) xxx-xxxx"))
				&& (emailField.getText().equals(""))
				&& (addressField.getText().equals(""))
				&& (cityField.getText().equals(""))
				&& (stateList.getSelectedIndex() == 0) && (zipField.getText()
				.equals("")));
	}

	private class SearchController implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!emptyFields()) {
				String phone;
				int b1, b2;

				phone = phoneField.getText();
				if (phone.equals("(xxx) xxx-xxxx")) {
					phone = null;
				}
				b1 = Security.client.MATCHES;
				b2 = Security.client.MATCHES;
				if (exactlyFirstBox.isSelected())
					b1 = Security.client.EXACTLY;
				if (exactlyLastBox.isSelected())
					b2 = Security.client.EXACTLY;

				Security.client.search(null, firstField.getText(), b1,
						lastField.getText(), b2,
						addressField.getText(),
						cityField.getText(),
						(String) stateList.getSelectedItem(),
						zipField.getText(), phone,
						emailField.getText());
				dispose();
			} else
				JOptionPane.showMessageDialog(null, "No criteria provided");
		}
	}

}