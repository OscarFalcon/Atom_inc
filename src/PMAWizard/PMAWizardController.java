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
		view.finishButton.addActionListener(new finishButtonListener());
	}
	
	private class finishButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			PMAObject pma = new PMAObject();
			String customerConcerns = "PMA created by " + view.createdByField.getText() + "\n" + view.custConcernsArea.getText();
			int wo = -1;
			/*try {
				wo = Security.PMA.createPMA(view.custID, view.vehicleVin, customerConcerns);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			if(wo != -1){
				view.frame.dispose();
				PMAView view = new PMAView(wo);
				PMAController controller = new PMAController(view);
				controller.readSQL();
			}
		}
	}
	
	
	private class backButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if((model.getCurrentCard() == view.CUSTOMER_CONCERNS))
				view.nextButton.setEnabled(true);
			else{
				view.nextButton.setEnabled(false);
				view.custTable.clearSelection();
			}
			view.changeCards(model.getBackCard());			
		}
	}
	private class nextButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(( model.getCurrentCard() == view.CUSTOMER_INFO) || model.getCurrentCard() == view.CUSTOMER_CREATE){
				view.updateTable(view.VEHICLE_INFO);
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