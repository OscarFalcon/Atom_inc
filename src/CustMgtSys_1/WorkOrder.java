package CustMgtSys_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorkOrder extends JPanel {

	public WorkOrder(){
		super();
	
		JLabel title = new JLabel("Work Orders");
		setLayout(new BorderLayout());
		setBackground(Color.green);
		add(title,BorderLayout.NORTH);
	
	
	
	
	}
	
	
	
	
}
