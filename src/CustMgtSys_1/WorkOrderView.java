package CustMgtSys_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class WorkOrderView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel northPanel, southPanel;
	
	public WorkOrderView() {
		northPanel = new JPanel();
		southPanel = new JPanel();
		
		northPanel.setPreferredSize(new Dimension(500,300));
		northPanel.setBackground(Color.black);
		
		southPanel.setPreferredSize(new Dimension(500,300));
		southPanel.setBackground(Color.gray);
		
		
		setBackground(Color.green);
		add(northPanel,BorderLayout.PAGE_START);
		add(southPanel,BorderLayout.PAGE_END);

	
	
	}

}