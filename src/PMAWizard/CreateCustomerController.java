package PMAWizard;


import java.net.URL;
import java.util.ResourceBundle;
import MyCMS.InputLimits;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import MyCMS.MyCMS;

public class CreateCustomerController extends ControlledScreen implements Initializable{

	@FXML private TextField firstNameField,lastNameField,address1Field,address2Field
							,cityField,zipField,phoneField,emailField;
	
	@FXML private ComboBox<String> stateField;
	
	@FXML private Button backToSearchButton,createButton;
	
	
	public void createCustomer(){
		String first,last,address1,address2,city,state,zip,phone,email;	
		
		/** HANDLE LIMITS **/
		if( (first = firstNameField.getText()).length() > InputLimits.MAX_CLIENT_FIRST)
			return;
		if( (last = lastNameField.getText()).length() > InputLimits.MAX_CLIENT_LAST)
			return;
		if( (address1 = address1Field.getText()).length() > InputLimits.MAX_CLIENT_ADDRESS)
			return;
		if( (address2 = address2Field.getText()).length() > InputLimits.MAX_CLIENT_ADDRESS)
			return;
		if( (city = cityField.getText()).length() > InputLimits.MAX_CLIENT_CITY)
			return;
		if( (state = stateField.getEditor().getText()).length()  > InputLimits.MAX_CLIENT_STATE)
			return;
		if( (zip = zipField.getText()).length() > InputLimits.MAX_CLIENT_ZIP)
			return;
		if( (phone = phoneField.getText()).length() > InputLimits.MAX_CLIENT_PHONE)
			return;
		if( (email = emailField.getText()).length() > InputLimits.MAX_CLIENT_EMAIL)
			return;		
		
		/** MISSING INFORMATION **/
		if(first.equals(""))
			return;
		if(last.equals(""))
			return;
		if(address1.equals(""))
			return;
		if(address2.equals(""))
			return;
		if(city.equals(""))
			return;
		if(state.equals(""))
			return;
		if(zip.equals(""))
			return;
		if(phone.equals(""))
			return;
		if(email.equals(""))
			return;
		
		/** HANDLE ERROR **/
		if( MyCMS.client.addCustomer(first, last, address1, city, state, zip, phone, email) == false)
			;	
	}
	
	
	public void backToSearch(){
		controller.switchScreen(PMAWizard.SELECTCUSTOMER_SCREEN);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stateField.getItems().add("");
		stateField.getItems().add("Alabama");
		stateField.getItems().add("Alaska");
		stateField.getItems().add("Arizona");
		stateField.getItems().add("Arkansas");
		stateField.getItems().add("California");
		stateField.getItems().add("Colorado");
		stateField.getItems().add("Connecticut");
		stateField.getItems().add("Delaware");
		stateField.getItems().add("Florida");
		stateField.getItems().add("Georgia");
		stateField.getItems().add("Hawaii");
		stateField.getItems().add("Idaho");
		stateField.getItems().add("Illinois");
		stateField.getItems().add("Indiana");
		stateField.getItems().add("Iowa");
		stateField.getItems().add("Kansas");
		stateField.getItems().add("Kentucky");
		stateField.getItems().add("Louisiana");
		stateField.getItems().add("Maine");
		stateField.getItems().add("Maryland");
		stateField.getItems().add("Massachusetts");
		stateField.getItems().add("Michigan");
		stateField.getItems().add("Minnesota");
		stateField.getItems().add("Mississippi");
		stateField.getItems().add("Missouri");
		stateField.getItems().add("Montana");
		stateField.getItems().add("Nebraska");
		stateField.getItems().add("Nevada");
		stateField.getItems().add("New Hampshire");
		stateField.getItems().add("New Jersey");
		stateField.getItems().add("New Mexico");
		stateField.getItems().add("New York");
		stateField.getItems().add("North Carolina");
		stateField.getItems().add("North Dakota");
		stateField.getItems().add("Ohio");
		stateField.getItems().add("Oklahoma");
		stateField.getItems().add("Oregon");
		stateField.getItems().add("Pennsylvania");
		stateField.getItems().add("Rhode Island");
		stateField.getItems().add("South Carolina");
		stateField.getItems().add("South Dakota");
		stateField.getItems().add("Tennessee");
		stateField.getItems().add("Texas");
		stateField.getItems().add("Utah");
		stateField.getItems().add("Vermont");
		stateField.getItems().add("Virginia");
		stateField.getItems().add("Washington");
		stateField.getItems().add("West Virginia");
		stateField.getItems().add("Wisconsin");
		stateField.getItems().add("Wyomin");	
		
		firstNameField.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		lastNameField.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		address1Field.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		address2Field.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		cityField.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		stateField.valueProperty().addListener(new comboBoxListener());
		zipField.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		phoneField.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		emailField.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
	}
	
	
	private class handler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
			if(hasEmptyField())
				createButton.setDisable(true);
			else
				createButton.setDisable(false);	
		}
	}
	
	private class comboBoxListener implements ChangeListener<String> {
		@Override
		public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
			if(hasEmptyField())
				createButton.setDisable(true);
			else
				createButton.setDisable(false);
		} 	
	}

	
	private boolean hasEmptyField(){
		if( 	firstNameField.getText().equals("") || lastNameField.getText().equals("") ||
				address1Field.getText().equals("") || address2Field.getText().equals("") ||
				cityField.getText().equals("") || stateField.getSelectionModel().getSelectedItem() == null 
				|| stateField.getSelectionModel().getSelectedItem().equals("") ||
				zipField.getText().equals("") || phoneField.getText().equals("") ||
				emailField.getText().equals(""))
			return true;
		return false;	
	}
	
	
	
	
	
}
	