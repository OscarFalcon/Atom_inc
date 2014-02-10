package PMA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PMAController implements ActionListener {
	
	private PMAView view;
	private boolean[][] isChecked;
	private String[][] comboBoxValues;
	private String[][] totalFields;
	private String[][] mechanicFields;
	
	public PMAController(PMAView view){
		this.view = view;
		isChecked = new boolean[50][3];
		comboBoxValues = new String[100][3];
		totalFields = new String[50][2];
		mechanicFields = new String[50][4];
	}

	public void actionPerformed(ActionEvent e) {
		String item;
		if(e.getActionCommand().equals("Submit")){
			

			//Gets all values of the checkBoxes and stores them into an array to send to the Security class
			//for writing to SQL
			for(int i =0; i < view.checkBoxes.length; i++){
				if(view.checkBoxes[i][0].isSelected()){
					isChecked[i][0] = true;
					isChecked[i][1] = false;
				}else if(view.checkBoxes[i][1].isSelected()){
					isChecked[i][0] = false;
					isChecked[i][1] = true;
				} else {
					isChecked[i][0] = false;
					isChecked[i][1] = false;
				}
			}
			
			
			for(int i = 0; i < view.comboBoxes.length; i++){
				for(int j = 0; j < 3; j++){
					if((item = (String) view.comboBoxes[i][j].getSelectedItem()) != null){
						System.out.println((String) view.comboBoxes[i][j].getSelectedItem());
						comboBoxValues[i][j] = item;
					}
				}
			}
			
			for(int i = 0; i < view.totalFields.length; i++){
				for(int j = 0; j < 2; j++){
					if((item = view.totalFields[i][j].getText()) != null){
						System.out.println(item);
						totalFields[i][j] = item;
					}
				}
			}
			
			for(int i = 0; i < view.numberFields.length; i++){
				mechanicFields[i][0] = view.QTYfields[i].getText();
				for(int j=0; j < 2; j++){
					if((item = view.numberFields[i][j].getText()) != null){
						System.out.println(view.numberFields[i][j].getText());
						mechanicFields[i][j] = item; 
					}
				}
				mechanicFields[i][3] = view.vendorFields[i].getText();
			}
			
			
			/*send all the customer information to Security Class to write data to SQL
			insertTable0(view.nameField.getText(),view.dateField.getText(),view.tagsField.getText(),
			view.vehicleYearField.getText(),view.vehicleMakeField.getText(),view.vehicleModelField.getText(),
			view.workOrderField.getText(),view.licField.getText(),view.vinField.getText(),
			view.engineField.getText(),view.transField.getText(),view.milesField.getText(), 
			view.customerConcerns.getText());
			*/
		}	
		
		
	}
}
