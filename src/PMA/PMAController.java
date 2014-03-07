package PMA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PMAController {

	private PMAView view;
	private boolean[][] isChecked;
	private String[][] comboBoxValues;
	private String[][] totalFields;
	private String[][] mechanicFields;

	public PMAController(PMAView view) {
		this.view = view;
		isChecked = new boolean[50][3];
		comboBoxValues = new String[100][3];
		totalFields = new String[50][2];
		mechanicFields = new String[50][4];
	}

	public void writeToSQL() {
		String item;
		String checkboxYes = "";
		String checkboxNo = "";
		String techComments = "";
		String recComments = "";
		String priority = "";
		
		
		
		/**
		 * customer name all the way to the customer concerns
		 */
		
		view.nameField.getText();
		view.dateField.getText();
		view.tagsField.getText();
		view.vehicleYearField.getText();
		view.vehicleMakeField.getText();
		view.workOrderField.getText();
		view.licField.getText();
		view.vinField.getText();
		view.engineField.getText();
		view.transField.getText();
		view.milesField.getText();
		view.customerConcerns.getText();
		
		
		for(int i = 0; i <= 42; i++){
			if(view.checkBoxes[i][0].isSelected()) checkboxYes.concat("1");  // on
			else checkboxYes.concat("0");   								 // off
			
			if(view.checkBoxes[i][1].isSelected()) checkboxNo.concat("1");
			else checkboxNo.concat("0");
			
			techComments.concat(view.comboBoxes[i][0].getSelectedItem().toString().replaceAll("|", "\\|") + "|");
			recComments.concat(view.comboBoxes[i][1].getSelectedItem().toString().replaceAll("|", "\\|") + "|");
			priority.concat(Integer.toString(view.comboBoxes[i][2].getSelectedIndex()));
			
			view.totalFields[i][0].getText();
			view.totalFields[i][1].getText();
			view.QTYfields[i].getText();
			view.numberFields[i][0].getText();
			view.numberFields[i][1].getText();
			view.vendorFields[i].getText();
		}
		
		
		view.totalParts.getText();
		view.totalLabor.getText();
		view.totalPrice.getText();
		view.tax.getText();
		view.shopSupplies.getText();
		view.grandTotal.getText();
		
		
		
		// Gets all values of the checkBoxes and stores them into an array to
		// send to the Security class
		// for writing to SQL

		
		
		/*
		 * send all the customer information to Security Class to write data to
		 * SQL
		 * insertTable0(view.nameField.getText(),view.dateField.getText(),view
		 * .tagsField.getText(),
		 * view.vehicleYearField.getText(),view.vehicleMakeField
		 * .getText(),view.vehicleModelField.getText(),
		 * view.workOrderField.getText
		 * (),view.licField.getText(),view.vinField.getText(),
		 * view.engineField.getText
		 * (),view.transField.getText(),view.milesField.getText(),
		 * view.customerConcerns.getText());
		 */

	}
}
