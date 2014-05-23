package FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import Custom.NumberTextField;

public class MyController implements Initializable{
	
	@FXML private CheckBox OK1, OK2, OK3, OK4, OK5;
	
	
	@FXML private CheckBox NOTOK1, NOTOK2, NOTOK3, NOTOK4, NOTOK5;
	
	
	
	private CheckBox[][] checkboxes = new CheckBox[][]{
			{OK1, NOTOK1}, {OK2, NOTOK2}, {OK3, NOTOK3}, {OK4, NOTOK4}, {OK5, NOTOK5}};
	
	
	@FXML private Label CUST, DATE, TAGS, YEAR, MAKE, MODEL, WO, LICNUM, VIN, ENG, TRANS, MILES;
	
	@FXML GridPane grid;
	
	public void checked(ActionEvent event){
		int place, j=1;
		CheckBox box;
		
		box = (CheckBox) event.getSource();
		
		if(box.isSelected()){
			box.getStyleClass().remove("check-box-regular");
			box.getStyleClass().add("check-box-valid");
		}
		else{
			box.getStyleClass().remove("check-box-valid");
			box.getStyleClass().add("check-box-regular");
		}
		
		
/**		
 * 
 * 
		// Get the box that was checked!
		//place is what line it is located with respect to corresponding values
		for(place = 0; place < checkboxes.length; place++){
			if( event.getSource() == checkboxes[place][0]){
				j=0;
				break;
			} if( event.getSource() == checkboxes[place][1]){
				j=1;
				break;
			}
		}
		
		//set all fields to false, default colors appropriately, and set default values to 0.0*********************
		
		if (j==0){
			if(checkboxes[place][j].isSelected()){
				//If the OK checkbox is checked then we dont need to open the corresponding fields
				
			}
			//set NotOK checkbox to be disabled and default color
		} else if (j==1){
			if(checkboxes[place][j].isSelected()){
				//If the NOTOK checkbox is checked then we DO need to open the fields to say whats wrong and give prices
				
			}
			//set OK checkbox to be disabled and default color
		}
		
		//test.getStyleClass().remove("check-box-regular"); // standard grey color
		//test.getStyleClass().add("check-box-valid"); // green color
													// need to make red
		*/
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//OK1.setSelected(true);
		//OK1.getStyleClass().remove("check-box-regular");
		//OK1.getStyleClass().add("check-box-valid");
		//cust.setText("TESTING");
		
		//grid.add(new NumberTextField(),6, 15);
	}
		
	
	
		
	
	
	
	
}
