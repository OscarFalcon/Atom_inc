package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController implements Initializable, ControlledScreen {

	ScreenController myController;

	
	/**
	 * Initializes the controller class.
	 */
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	public void setScreenParent(ScreenController screenParent) {
		myController = screenParent;
	}

	@FXML
	public void signOut(ActionEvent event) {
		// add "are you sure you wish to sign out" prompt
		myController.setScreen(Main.screenLoginID);
	}

	@FXML
	public void close(ActionEvent event) {
		// add 'are you sure you want to exit application" prompt
		Platform.exit();
	}

	public void signIn(ActionEvent event) {
		//verify fields
		myController.setScreen(Main.screenMainID);
		return;

	}

	public void cancel(ActionEvent event) {
		return;
	}


	
	
}
