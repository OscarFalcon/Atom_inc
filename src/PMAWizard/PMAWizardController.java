package PMAWizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
