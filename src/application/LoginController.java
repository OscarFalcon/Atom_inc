package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import MyCMS.*;

public class LoginController implements Initializable, ControlledScreen {
	
	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	
	ScreenController myController;

	private static final int MAX_USERNAME = 32;
	private static final int MAX_PASSWORD = 32;
	
	
	private static final int ALLOWED_ATTEMPTS = 3;
	private int attempts = 0;
	
	
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
		String username,password;
		int user_name_length, password_length;
		
		username = usernameField.getText();
		password = passwordField.getText();
		user_name_length = usernameField.getLength();
		password_length = passwordField.getLength();
			
		if(user_name_length == 0){
			System.out.println("USERNAME NO ENTRY ERROR");
			return;
		}
		if(password_length == 0){
			System.out.println("PASSWORD NO ENTRY ERROR");
			return;
		}
		if(username.length() > MAX_USERNAME){
			System.out.println("USERNAME MAX ERROR");
			return;
		}
		if(password.length() > MAX_PASSWORD){
			System.out.println("PASSWORD MAX ERROR");
			return;
		}
		if(attempts >= ALLOWED_ATTEMPTS){
			System.out.println("ALLOWED ATTEMPTS ERROR");
			return;
		}
		if(MyCMS.employee.login_employee(username, password)){
			attempts = 0;
			myController.setScreen(Main.screenMainID);
		}
		else
			attempts++;
		
		return;
	}

	public void cancel(ActionEvent event) {
		return;
	}


	
	
}
