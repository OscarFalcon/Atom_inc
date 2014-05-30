package application;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable, ControlledScreen {

	ScreenController myController;

	public TabPane tabbedPane;
	static Stage primaryStage;

	@FXML
	TableView serviceTable;
	@FXML
	TableView customerTable;
	@FXML
	TableView pmaTable;
	// columns for the customer table
	@FXML
	TableColumn customerId, customerFirst, customerLast, customerPhone,
			customerAddress, customerEmail;
	// columns for the in garage table
	@FXML
	TableColumn serviceOrderNum, serviceDateIn, serviceName, serviceVehicle,
			serviceStatus;
	// columns for the PMA table
	@FXML
	TableColumn workOrderNum, dateIn, first, last, vehicle, phone;
	@FXML
	SplitPane pmaSplitPane;
	@FXML 
	TextField firstAddField, lastAddField, emailAddField;
	
	public Person current;
	
	

	public void initialize(URL url, ResourceBundle rb) {

		// Set the resizing property for customer table
		customerId.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.12));
		customerFirst.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.15));
		customerLast.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.18));
		customerAddress.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.20));
		customerPhone.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.15));
		customerEmail.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.20));

		// Set the resizing property for the service order table
		serviceOrderNum.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.15));
		serviceDateIn.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.15));
		serviceName.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceVehicle.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.30));
		serviceStatus.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));

		// set resizing for the searched PMA table columns
		workOrderNum.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		dateIn.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.12));
		first.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		last.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.20));
		vehicle.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.23));
		phone.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		
		
		current = new Person("7891", "John", "Wimberly", "44561897", "6406 Red Jacket", "johncwimberly@yahoo.com");
		

	}

	
	public void addClient(ActionEvent event){
		EditClientController.person = null;
		Stage popup = loadpopup("EditCustomerView.fxml");
		popup.setTitle("Add Client");
		popup.show();
		
		/*
		AddClientController msgBox = new AddClientController();
	    try {
	        msgBox.showMessageBox(primaryStage);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    */
	}
	
	public void editClient(ActionEvent event){
		EditClientController.person = current;
		Stage popup = loadpopup("EditCustomerView.fxml");
		popup.setTitle("Edit Client");
		popup.show();
		
		
		/*
		EditClientController msgBox = new EditClientController();
	    try {
	        msgBox.showMessageBox(primaryStage, current);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    */
	    
	    
		
	}
	
	public Stage loadpopup(String filename){
		Stage popup = new Stage();
		try{
			Parent root = FXMLLoader.load(getClass().getResource(filename));
			Scene scene = new Scene(root, 450, 350);
			popup.setScene(scene);
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			
			popup.setScene(scene);
	        popup.initOwner(primaryStage);
	        popup.initModality(Modality.WINDOW_MODAL);
	        popup.initStyle(StageStyle.UTILITY);
	        popup.setResizable(false);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return popup;
	}
	

	public void setScreenParent(ScreenController screenParent) {
		myController = screenParent;
	}

	@FXML
	public void signOut(ActionEvent event) {
		//sign out prompt
		//then load login screen clear logged in info
	}

	@FXML
	public void close(ActionEvent event) {
		// add 'are you sure you want to exit application" prompt
		Platform.exit();
	}
	
	

	

}
