package application;

import java.net.URL;
import java.util.ResourceBundle;

import mycms.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class LoginController implements Initializable, ControlledScreen {
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Label loginLabel;
	@FXML private Button loginButton;
	
	
	private ScreenController myController;

	
	private static final int MAX_USERNAME = 32;
	private static final int MAX_PASSWORD = 32;
	
	
	private static final int ALLOWED_ATTEMPTS = 3;
	private int attempts = 0;
	private static boolean timed_out;
	private static Timeout timeout;
	
	/**
	 * Initializes the controller class.
	 */
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void setScreenParent(ScreenController screenParent) {
		myController = screenParent;
	}


	@FXML
	public void close(ActionEvent event) {
		// add 'are you sure you want to exit application" prompt
		Platform.exit();
	}

	public void signIn(ActionEvent event) {
		final String username,password;
		final int user_name_length, password_length;
		
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
			timed_out = true;
			timeout = new Timeout();
			timeout.setTimeout(this, 30000); //TIME OUT FOR 30 SECONDS
			Thread t = new Thread(timeout);
			t.start();
			attempts = 0;					// RESET ATTEMPTS TO GIVE USER 3 MORE TRIES
		}
		if(getTimeOutStatus()){
			System.out.println("TIMED OUT");
			return;
		}
		if(MyCMS.employee.login_employee(username, password)){
			attempts = 0;
			myController.setScreen(Main.screenMainID);
			usernameField.setText("");
			passwordField.setText("");
		}
		else
			attempts++;
		
		return;
	}
	
	public synchronized void setTimeOutStatus(boolean b){
		System.out.println("TIMEOUT ENDED");
		timed_out = b;
	}
	private synchronized boolean getTimeOutStatus(){
		return timed_out;
	}
	
	public void cancel(ActionEvent event) {
		return;
	}
	
	
}
