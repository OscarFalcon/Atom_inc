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
	@FXML
	TableView pmaTable;
	//columns for the customer table
	@FXML
	TableColumn customerId, customerFirst, customerLast, customerPhone, customerAddress, customerEmail;
	//columns for the in garage table
	@FXML
	TableColumn serviceOrderNum, serviceDateIn, serviceName, serviceVehicle, serviceStatus;
	//columns for the PMA table
	@FXML
	TableColumn workOrderNum, dateIn, first, last, vehicle, phone;
	@FXML
	SplitPane pmaSplitPane;
	
	


	
	
	public void initialize(URL url, ResourceBundle rb) {
		
		//Set the resizing property for customer table
		customerId.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.12));
		customerFirst.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.15));
		customerLast.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.18));
		customerAddress.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.20));
		customerPhone.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.15));
		customerEmail.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.20));
		
		//Set the resizing property for the service order table
		serviceOrderNum.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.15));
		serviceDateIn.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.15));
		serviceName.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceVehicle.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.30));
		serviceStatus.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		
		//set resizing for the searched PMA table columns
		workOrderNum.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		dateIn.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.12));
		first.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		last.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.20));
		vehicle.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.23));
		phone.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		
		
	}
	
	
	
	public void popup(ActionEvent event){

		final Stage secondaryStage = new Stage();
	    
	    
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddCustomerView.fxml")); 
	        Scene scene = new Scene(root, 450, 350);
	        secondaryStage.setTitle("Add Customer");
	        secondaryStage.setScene(scene);
	        
	        secondaryStage.initModality(Modality.APPLICATION_MODAL);
	        secondaryStage.setResizable(false);
	        
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
