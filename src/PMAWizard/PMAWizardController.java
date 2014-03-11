package PMAWizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import CustMgtSys_1.PMAObject;
import CustMgtSys_1.Security;
import PMA.PMAController;
import PMA.PMAView;

public class PMAWizardController{
	PMAWizard view;
	PMAWizardModel model;
	
	
	
	public PMAWizardController(PMAWizard view, PMAWizardModel model){
		this.view = view;
		this.model = model;
		
		view.backButton.addActionListener(new backButtonListener());
		view.nextButton.addActionListener(new nextButtonListener());
		view.cancelButton.addActionListener(new cancelButtonListener());
	}
	
	private class finishButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			PMAObject pma = new PMAObject();
			pma.first = view.customerInformation[0];
			pma.last = view.customerInformation[1];
			pma.tags = view.vehicleInformation[5];
			pma.year = view.vehicleInformation[0];
			pma.make = view.vehicleInformation[1];
			pma.model = view.vehicleInformation[2];
			pma.lic = view.vehicleInformation[3];
			pma.vin = view.vehicleInformation[4];
			pma.engine = view.vehicleInformation[6];
			pma.trans = view.vehicleInformation[7];
			pma.miles = view.vehicleInformation[8];
			pma.customer_concerns = "PMA created by " + view.createdByField.getText() + "\n" + view.custConcernsArea.getText();
			int wo = 0;
			try {
				wo = Security.PMA.createPMA(pma);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			PMAView view = new PMAView(wo);
			PMAController controller = new PMAController(view);
			controller.readSQL();
		}
	}
	
	
	private class backButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.changeCards(model.getBackCard());
			view.nextButton.setEnabled(true);
		}
		
	}
	
	private class nextButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(model.getCurrentCard() == view.CUSTOMER_INFO){
				int row = view.custTable.getSelectedRow();
				view.custID = Integer.parseInt((String) view.custTable.getValueAt(row, 4));
				ResultSet rs = Security.Vehicle.searchVehicles(view.custID);
				
				Object[] tmpRow;
				view.vehicleTablemodel.setRowCount(0);
				try {
					while(rs.next()){
							tmpRow = new Object[]{ rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(9), rs.getString(1)};
							view.vehicleTablemodel.addRow(tmpRow);
					}
				} catch (SQLException e1){
					e1.printStackTrace(); //handle error
				}
			}
			view.changeCards(model.getNextCard());
			view.nextButton.setEnabled(false);
		}
		
	}
	
	private class cancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int val = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the creation of this PMA");
			if (val == 0) view.frame.dispose();
		}
	}
}