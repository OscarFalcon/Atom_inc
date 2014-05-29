package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController implements Initializable, ControlledScreen {

	ScreenController myController;
	
	public TabPane tabbedPane;
	
	@FXML
	TableView serviceTable;
	@FXML
	TableView customerTable;
	//columns for the customer table
	@FXML
	TableColumn customerId, customerFirst, customerLast, customerPhone, customerAddress, customerEmail;
	//columns for the in garage tab
	@FXML
	TableColumn serviceOrderNum, serviceDateIn, serviceName, serviceVehicle, serviceStatus;
	
	
	

	
	//nameCol.prefWidthProperty().bind(personTable.widthProperty().divide(4)); // w * 1/4
	
	
	
	public void initialize(URL url, ResourceBundle rb) {
		
		//Set the resizing property for customer table
		customerId.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.1666));
		customerFirst.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.1666));
		customerLast.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.1666));
		customerAddress.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.1666));
		customerPhone.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.1666));
		customerEmail.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.1666));
		
		//Set the resizing property for the service order table
		serviceOrderNum.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceDateIn.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceName.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceVehicle.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceStatus.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		

	}
	
	
	
	public void popup(ActionEvent event){
		Stage popupWindow = new Stage();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCustomerView.fxml"));
			Scene scene = (Scene)fxmlLoader.load();
			//myController = fxmlLoader.getController();
			popupWindow.setScene(scene);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
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

	
	public void cancel(ActionEvent event) {
		return;
	}

}
