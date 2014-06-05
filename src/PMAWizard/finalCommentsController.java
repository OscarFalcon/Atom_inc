package PMAWizard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;


public class finalCommentsController extends ControlledScreen implements Initializable{

	@FXML TextArea customerConcernsTextArea,techCommentsTextArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customerConcernsTextArea.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		techCommentsTextArea.addEventHandler(KeyEvent.KEY_RELEASED, new handler());
		
	}
	
	
	public String getCustomerConcerns(){
		return customerConcernsTextArea.getText();
	}
	public String getTechComments(){
		return techCommentsTextArea.getText();
	}
	
	
	
	
	
	private class handler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
			if(hasEmptyField())
				controller.setFinishButtonDisable(true);
			else
				controller.setFinishButtonDisable(false);
		}
	}
	
	private boolean hasEmptyField(){
		if(customerConcernsTextArea.getText().equals("") || techCommentsTextArea.getText().equals(""))
			return true;
		return false;
	}
	
	
}
