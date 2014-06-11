package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mycms.MyCMS;
import pma.PMAView;
import pmawizard.PMAWizard;
import workshop.Person;
import workshop.WorkOrder;



public class MainController implements Initializable, ControlledScreen {

	private ScreenController myController;
	static Stage primaryStage;

	
	
	
	
	@FXML
	TableView pmaTable;
	@FXML TableColumn workOrderNum, dateIn, first, last, vehicle, phone;

	@FXML SplitPane pmaSplitPane;
	
	@FXML TextField firstAddField, lastAddField, emailAddField;
	
	
	
	/** GENERAL **/
	private Button signOutButton;
	
	
	/** 	IN SERVICE TAB 		**/
	@FXML private TableView<WorkOrder> serviceTable;
	
	@FXML private TableColumn<WorkOrder,String> serviceOrderNum, serviceDateIn, serviceName, serviceVehicle,serviceStatus, 
					serviceLic, serviceTotal;
	
	@FXML private Label firstNameLabel,lastNameLabel,phoneLabel,addressLabel,cityLabel,stateLabel,zipLabel;
	
	@FXML private Label makeLabel, modelLabel, yearLabel, licenseLabel, vinLabel, colorLabel, mileageLabel,
			engineLabel, transmissionLabel;
	
	private ObservableList<WorkOrder> workOrderList;
	
	
	
	
	
	/** 	CUSTOMERS TAB 			**/
	@FXML private TableView<Person> customerTable;
	
	@FXML private Button searchCustomerButton;
	
	@FXML private TextField firstNameField,lastNameField,phoneField;
	
	@FXML TableColumn<Person, String> customerId, customerFirst, customerLast, customerPhone,customerAddress, customerEmail;
	
	
	
	
	public Person current;
	
	

	public void initialize(URL url, ResourceBundle rb) {
		
		customerTable.setOpacity(.6);
		//customerTable.setPlaceholder(node);
		
		/** INIT CUSTOMER TABLE VIEW **/
		customerId.setCellValueFactory(new PropertyValueFactory<Person,String>("customerId"));
		customerFirst.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
	    customerLast.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
	    customerPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("phoneId"));
	    customerAddress.setCellValueFactory(new PropertyValueFactory<Person,String>("addressId"));
	    customerEmail.setCellValueFactory(new PropertyValueFactory<Person,String>("emailId"));
	        
		// Set the resizing property for customer table
		customerId.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.12));
		customerFirst.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.15));
		customerLast.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.18));
		customerAddress.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.20));
		customerPhone.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.15));
		customerEmail.prefWidthProperty().bind(customerTable.widthProperty().multiply(0.20));

		/** INIT IN SERVICE TABLE VIEW **/
		serviceOrderNum.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("workOrder"));
		serviceDateIn.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("date"));
		serviceName.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("name"));
		serviceVehicle.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("vehicle"));
		
		/** Still need to init the value for license plate in table and TODO Status , Total  **/
		//serviceLic.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>())
		
		
		workOrderList = MyCMS.workorder.getWorksOrders();
		if(workOrderList != null)							/** HANDLE ERROR APPROPRIATELY **/
			serviceTable.setItems(workOrderList);
		
		serviceTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		serviceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkOrder>() {
			@Override
			public void changed(
					ObservableValue<? extends WorkOrder> observable,WorkOrder oldValue, WorkOrder newValue) {
					
					if(newValue == null){
						firstNameLabel.setText("");
						lastNameLabel.setText("");
						phoneLabel.setText("");
						addressLabel.setText("");
						cityLabel.setText("");
						stateLabel.setText("");
						/**HANDLE ERROR BY DISPLAYING MESSAGE ? **/
						return;
					
					}
					/** UPDATES PERSONAL INFO TAB **/
					firstNameLabel.setText(newValue.getFirstName());
					lastNameLabel.setText(newValue.getLastName());
					phoneLabel.setText(newValue.getPhone());
					addressLabel.setText(newValue.getAddress());
					cityLabel.setText(newValue.getCity());
					stateLabel.setText(newValue.getState());	
					
					/** UPDATES VEHICLE INFO TAB **/
					makeLabel.setText(newValue.getMake());
					modelLabel.setText(newValue.getModel());
					yearLabel.setText(newValue.getYear());
					licenseLabel.setText(newValue.getLic());
					vinLabel.setText(newValue.getVIN());
					mileageLabel.setText(newValue.getMiles());
					engineLabel.setText(newValue.getEngine());
					transmissionLabel.setText(newValue.getTransmission());
					
			}		
		});

		
		
		
		
		
		
		
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
	
	private  Stage loadpopup(String filename){
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
	public void close(ActionEvent event) {
		// add 'are you sure you want to exit application" prompt
		Platform.exit();
	}
	
	
	/** GENERAL SECTION **/
	public void signOut(ActionEvent event) {
		myController.setScreen(Main.screenLoginID);
	}
	public void setScreenController(ScreenController controller){
		myController = controller;
	}
	
	
	
	/** 		CUSTOMERS TAB CONTROLLER SECTION 			**/
	
	public void searchCustomer(){
		String first,last,phone;
		first = firstNameField.getText();
		last = lastNameField.getText();
		phone = phoneField.getText();
	
		
		if(first.length() > 50){
			System.out.println("MAX CLIENT FIRST ERROR");
			return;
		}
		if(last.length() > 50){
			System.out.println("MAX CLIENT LAST ERROR");
			return;
		}
		if(phone.length() > 50){
			System.out.println("MAX CLIENT PHONE ERROR");
			return;
		}
		if(first.equals(""))
			first = null;
		if(last.equals(""))
			last = null;
		if(phone.equals(""))
			phone = null;
		
		ObservableList<Person> persons = MyCMS.customer.search(first, MyCMS.EXACTLY,last,MyCMS.EXACTLY, null,null,null,null,null,null);
		customerTable.setItems(persons);
	}
	
	/** 			IN SERVICE TAB CONTROLLER SECTION 				**/
	
	
	public void viewWorkOrder(){
		int workOrder;
		WorkOrder workOrderItem;
		
		workOrderItem = serviceTable.getSelectionModel().getSelectedItem();
		
		if(workOrderItem == null)
			return;
		
		workOrder = workOrderItem.getWorkOrderAsInt();
		Platform.runLater(new PMAView(workOrder));
	}
	
	
	public void refresh(){
		if(MyCMS.workorder.getWorksOrders() == null) /**HANDLE ERROR !! */
			System.out.println("FAILED TO GET WORK ORDERS");
		serviceTable.getSelectionModel().clearSelection();
	}
	
	public void addPMA(){
		Platform.runLater(new PMAWizard());
	}
	
	
	
	
	
	
	

}