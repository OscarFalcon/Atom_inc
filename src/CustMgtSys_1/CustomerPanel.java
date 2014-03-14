package CustMgtSys_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import org.jdesktop.xswingx.JXSearchField;
import org.jdesktop.xswingx.JXSearchField.SearchMode;
import org.jdesktop.xswingx.PromptSupport;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CustomerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	
	
	private static JTable table, tranTable;
	
	
	
	
	private final JPanel searchClientPanel;

	private final JScrollPane tableScroll, tranScroll;

	private final JSplitPane splitPane;
	
	private final JXSearchField IdField, firstField, lastField;
			
	private JFormattedTextField phoneField;

	private JButton searchClientButton;

	private CustomTableModel tablemodel, workModel;

	
	public CustomerPanel() {
		setLayout(null);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		searchClientPanel = new JPanel();
		searchClientPanel.setLayout(new SpringLayout());
		
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(###) ###-####");
			mask.setPlaceholderCharacter('x');
		} catch (ParseException e) {
			Error.MaskFormatterError();
		} catch (java.text.ParseException e) {
			Error.MaskFormatterError();
		}
		
		ImageIcon icon;

		JLabel titleLabel = new JLabel("");
		titleLabel.setPreferredSize(new Dimension(200, 30));
		icon = new ImageIcon("gnome_search_tool.png");
		searchClientButton = new JButton(icon);
		searchClientButton.setPreferredSize(new Dimension(50, 30));

		IdField = new JXSearchField();
		IdField.setPreferredSize(new Dimension(150, 30));
		IdField.setSearchMode(SearchMode.REGULAR);
		PromptSupport.setPrompt("  ID", IdField);
		firstField = new JXSearchField();
		firstField.setPreferredSize(new Dimension(150, 30));
		firstField.setSearchMode(SearchMode.REGULAR);
		firstField.setRecentSearchesSaveKey("someUniqueKey");
		PromptSupport.setPrompt("  first name", firstField);
		lastField = new JXSearchField();
		lastField.setPreferredSize(new Dimension(150, 30));
		lastField.setSearchMode(SearchMode.REGULAR);
		lastField.setRecentSearchesSaveKey("someUniqueKey02");
		PromptSupport.setPrompt("  last name", lastField);
		phoneField = new JFormattedTextField(mask);
		phoneField.setPreferredSize(new Dimension(150, 30));

		searchClientPanel.add(titleLabel);
		searchClientPanel.add(searchClientButton);
		searchClientPanel.add(IdField);
		searchClientPanel.add(firstField);
		searchClientPanel.add(lastField);
		searchClientPanel.add(phoneField);
		SpringUtilities.makeCompactGrid(searchClientPanel, 1, 6, 0, 0, 0, 0);

		tablemodel = new CustomTableModel();
		table = new JTable(tablemodel);
		table.setFont(new Font("Symbol-Plain", Font.PLAIN, 13));
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(table.getRowHeight() + 3);
		table.setFillsViewportHeight(true);//fills the entire are with the table.
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn col;
		CenterTableCellRenderer centerRenderer;

		col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(60);
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
		col.setPreferredWidth(150);
		centerRenderer = new CenterTableCellRenderer();
		col.setCellRenderer(centerRenderer);

		col = table.getColumnModel().getColumn(4);
		col.setPreferredWidth(150);
		centerRenderer = new CenterTableCellRenderer();
		col.setCellRenderer(centerRenderer);

		col = table.getColumnModel().getColumn(5);
		col.setPreferredWidth(65);
		centerRenderer = new CenterTableCellRenderer();
		col.setCellRenderer(centerRenderer);

		col = table.getColumnModel().getColumn(6);
		col.setPreferredWidth(100);
		centerRenderer = new CenterTableCellRenderer();
		col.setCellRenderer(centerRenderer);

		col = table.getColumnModel().getColumn(7);
		col.setPreferredWidth(170);
		centerRenderer = new CenterTableCellRenderer();
		col.setCellRenderer(centerRenderer);

		col = table.getColumnModel().getColumn(8);
		col.setPreferredWidth(150);
		centerRenderer = new CenterTableCellRenderer();
		col.setCellRenderer(centerRenderer);

		// creates JScrollPane with JTable inside it
		tableScroll = new JScrollPane(table);
		tranTable = new JTable(workModel);
		tranTable.setFont(new Font("Symbol-Plain", Font.PLAIN, 18));
		tranTable.setRowSelectionAllowed(true);
		tranTable.setColumnSelectionAllowed(false);
		tranTable.setAutoCreateRowSorter(true);
		tranTable.getTableHeader().setReorderingAllowed(false);
		tranTable.setRowHeight(table.getRowHeight() + 6);
		tranTable.setGridColor(Color.black);
		tranTable.setBackground(Color.white);

		tranScroll = new JScrollPane(tranTable);
		Dimension tranTablePreferred = tranScroll.getPreferredSize();
		tranScroll.setPreferredSize(new Dimension(tranTablePreferred.width,
				tranTablePreferred.height / 3));

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll,
				tranScroll);
		splitPane.setDividerLocation(400);
		splitPane.setAlignmentY(JSplitPane.CENTER_ALIGNMENT);
		splitPane.setPreferredSize(new Dimension(1110, 550));

		add(searchClientPanel, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);

		//absolute positioning 
		Insets insets;
		Dimension size;
		insets = getInsets();

		size = searchClientPanel.getPreferredSize();
		searchClientPanel.setBounds(insets.left, insets.top, size.width,
				size.height);

		size = splitPane.getPreferredSize();
		splitPane.setBounds(insets.left, insets.top + 30, size.width,
				size.height);

		registerControllers(new CustomerPanelController());
		updateTable(Security.client.selectAll());
	}
	
	private void registerControllers(CustomerPanelController listen) {
		IdField.addActionListener(listen);
		firstField.addActionListener(listen);
		lastField.addActionListener(listen);
		phoneField.addActionListener(listen);
		searchClientButton.addActionListener(listen);
		table.getSelectionModel().addListSelectionListener(listen);
		table.addMouseListener(listen);
	}

	private class CustomerPanelController extends MouseAdapter implements ActionListener, ListSelectionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			ResultSet rs = null;
			if (event.getSource() == IdField) {
				String id = IdField.getText();
				if (id.equals(""))
					rs = Security.client.selectAll();
				else
					rs = Security.client.search(id, null, 0, null, 0, null, null, null, null, null, null);				
				IdField.setText("");
			} else {
				String first, last, phone;
				first = firstField.getText();
				last = lastField.getText();
				phone = phoneField.getText();
				firstField.setText("");
				lastField.setText("");
				phoneField.setValue("");
				rs = Security.client.search(null, first, Security.client.EXACTLY, last, Security.client.EXACTLY, null, null, null, null, phone, null);
			}// else
			updateTable(rs);
		}// ActionPerformed

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
		}

		public void mouseReleased(MouseEvent me) {
			if (me.getClickCount() >= 2)
				new ClientInfo();
		}

	}// CustomerPanelController
	
	public void updateTable(ResultSet rs){
		Object[] tmpRow;
		tablemodel.setRowCount(0);
		try {
			while(rs.next()){
					tmpRow = new Object[]{ rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
					tablemodel.addRow(tmpRow);
			}
		} catch (SQLException e){
			e.printStackTrace(); //handle error
		}
	}
	
	public static int getClientTableSelectedRow(){
		if(table == null)
			return -1;
		return table.getSelectedRow();
	}
	
	public static JTable getClientTable(){
		return table;
	}
	
	
	
	
	
	
	
	

}