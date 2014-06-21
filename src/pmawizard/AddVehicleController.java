package pmawizard;

import java.net.URL;
import java.util.ResourceBundle;

import mycms.MyCMS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class AddVehicleController extends ControlledScreen implements Initializable{

	@FXML private TextField yearField,makeField,modelField,vinField,licenseField,tagsField,engineField,transField,milesField;
	
	@FXML private Button addVehicleButton;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yearField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		makeField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		modelField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		vinField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		licenseField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		tagsField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		engineField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		transField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());
		milesField.addEventHandler(KeyEvent.KEY_RELEASED,new handler());	
		addVehicleButton.setDisable(true);
		
	}
	
	
	/**
	 * onAction:
	 * adds a vehicle into the database 
	 */
	public void addVehicle(){
		final String vin,licnum,tags,make,model,engine,trans,miles;
		final int year;
		
		
		vin = vinField.getText();
		licnum = licenseField.getText();
		tags = tagsField.getText();
		year = Integer.parseInt(yearField.getText()); /** HANDLE ERROR **/
		make = makeField.getText();
		model = modelField.getText();
		engine = engineField.getText();
		trans = transField.getText();
		miles = milesField.getText();
	
		/** ERROR TRAP **/
		if(MyCMS.vehicle.addVehicle(controller.getCustID(), vin, licnum, tags, year, make, model, engine, trans, miles))
			controller.setVehicleVin(vin);
		
	}
	

	
	
	public void backToSearch(){
		controller.switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
		SelectVehicleController c = (SelectVehicleController) controller.getController(PMAWizard.SELECTVEHICLE_SCREEN);
		c.clearSelection();
	}
	
	
	
	private class handler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
			if(hasEmptyField())
				addVehicleButton.setDisable(true);
			else
				addVehicleButton.setDisable(false);	
		}
	}
	
	private boolean hasEmptyField(){
		if( 	yearField.getText().equals("") || makeField.getText().equals("") ||
				modelField.getText().equals("") || vinField.getText().equals("") ||
				licenseField.getText().equals("") || tagsField.getText().equals("") ||
				engineField.getText().equals("") ||transField.getText().equals("") || milesField.getText().equals(""))
			return true;
		return false;	
	}
	
	
	
	
}
