package application;

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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

		final Stage secondaryStage = new Stage();
	    
	    
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddCustomerView.fxml")); 
	        Scene scene = new Scene(root, 300, 275);
	        secondaryStage.setTitle("Add Customer");
	        secondaryStage.setScene(scene);
	        
	        secondaryStage.initModality(Modality.APPLICATION_MODAL);
	        
	        secondaryStage.show();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
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
