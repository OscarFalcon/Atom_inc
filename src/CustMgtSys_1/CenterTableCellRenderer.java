package CustMgtSys_1;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class CenterTableCellRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	public CenterTableCellRenderer(){
		setHorizontalAlignment(JLabel.CENTER);
	}
}
