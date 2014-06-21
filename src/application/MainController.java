package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import fxml.ControlledScreen;
import fxml.FXMLEngine;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import workshop.PMARow;
import workshop.Person;
import workshop.WorkOrder;



public class MainController implements Initializable, ControlledScreen {

	private FXMLEngine parentEngine;
	
	
	public static Stage primaryStage;

	
	@FXML
	TableView pmaTable;
	@FXML TableColumn workOrderNum, dateIn, first, last, vehicle, phone;

	@FXML SplitPane pmaSplitPane;
	
	@FXML TextField firstAddField, lastAddField, emailAddField;
	
	
	/** GENERAL **/
	private Button signOutButton;
	
	
	
	
	/** maps work orders to pma previews - needed to work with reordering of in service table **/
	private final HashMap<Integer,ObservableList<PMARow>> map = new HashMap<Integer,ObservableList<PMARow>>(); 
	
	
	
	
	
	
	/** 	IN SERVICE TAB 		**/
	@FXML private TableView<WorkOrder> serviceTable;
	
	@FXML private TableColumn<WorkOrder,String> serviceOrderNum, serviceDateIn, serviceName, serviceVehicle,serviceStatus, 
												serviceLic, serviceTotal;
	
	@FXML private TableView<PMARow> previewTable;
	
	@FXML TableColumn<PMARow, String> previewDescription, previewTechComments, previewRepairComments, previewPriority, previewParts, previewLabor;
	
	
	private final ObservableList<WorkOrder> work_order_list = FXCollections.observableArrayList();
	
	private final ObservableList<PMARow> current_pma_list = FXCollections.observableArrayList();
	
	private final ArrayList<ObservableList<PMARow>> pma_preview_list = new ArrayList<ObservableList<PMARow>>();
	
	
	
	
	
	
	
	/**************REMOVED ***************************/
	@FXML private Label firstNameLabel,lastNameLabel,phoneLabel,emailLabel,addressLabel,cityLabel,stateLabel,zipLabel;
	
	@FXML private Label makeLabel, modelLabel, yearLabel, licenseLabel, vinLabel, colorLabel, mileageLabel,
						engineLabel, transmissionLabel;
	/***************************************************/
	
	
	
	
	
	/** 	CUSTOMERS TAB 			**/
	@FXML private TableView<Person> customerTable;
	
	@FXML private Button searchCustomerButton;
	
	@FXML private TextField firstNameField,lastNameField,phoneField;
	
	@FXML TableColumn<Person, String> customerId, customerFirst, customerLast, customerPhone,customerAddress, customerEmail;
	
	
	
	
	public Person current;
	
	
	
	
	
	@Override
	public void setScreenParent(FXMLEngine parentEngine){
		this.parentEngine = parentEngine;	
	}
	
	
	
	
	public void initialize(URL url, ResourceBundle rb) {
	
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

		
		/** INIT PREVIEW PMA TABLE **/
		previewDescription.setCellValueFactory(new PropertyValueFactory<PMARow,String>("description"));
		previewTechComments.setCellValueFactory(new PropertyValueFactory<PMARow,String>("techComments"));
		previewRepairComments.setCellValueFactory(new PropertyValueFactory<PMARow,String>("repairComments"));
		previewPriority.setCellValueFactory(new PropertyValueFactory<PMARow,String>("priority"));
		previewParts.setCellValueFactory(new PropertyValueFactory<PMARow,String>("parts"));
		previewLabor.setCellValueFactory(new PropertyValueFactory<PMARow,String>("labor"));
		previewTable.setItems(current_pma_list);
		
		
		/** INIT IN SERVICE TABLE VIEW **/
		serviceOrderNum.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("workOrder"));
		serviceDateIn.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("date"));
		serviceName.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("name"));
		serviceVehicle.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("vehicle"));
		serviceTotal.setCellValueFactory(new PropertyValueFactory<WorkOrder,String>("grandTotal"));
		
		serviceTable.setItems(work_order_list);
		serviceTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		serviceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkOrder>() {
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
					emailLabel.setText(newValue.getEmail());
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
					
					int index = serviceTable.getSelectionModel().getSelectedIndex();
					String wo = serviceOrderNum.getCellData(index);
					if(wo != null)
						current_pma_list.setAll(map.get(Integer.parseInt(wo)));					
			}		
		});

		
		// Set the resizing property for the service order table
		serviceOrderNum.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.15));
		serviceDateIn.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.15));
		serviceName.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));
		serviceVehicle.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.30));
		//serviceStatus.prefWidthProperty().bind(serviceTable.widthProperty().multiply(0.20));

		// set resizing for the searched PMA table columns
		workOrderNum.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		dateIn.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.12));
		first.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
		last.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.20));
		vehicle.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.23));
		phone.prefWidthProperty().bind(pmaTable.widthProperty().multiply(0.15));
			
		//set resizing for the previewTable
		previewDescription.prefWidthProperty().bind(previewTable.widthProperty().multiply(0.20));
		previewTechComments.prefWidthProperty().bind(previewTable.widthProperty().multiply(0.25));
		previewRepairComments.prefWidthProperty().bind(previewTable.widthProperty().multiply(0.25));
		previewPriority.prefWidthProperty().bind(previewTable.widthProperty().multiply(0.10));
		previewParts.prefWidthProperty().bind(previewTable.widthProperty().multiply(0.10));
		previewLabor.prefWidthProperty().bind(previewTable.widthProperty().multiply(0.10));
	
        
	}
        

	
	public void addClient(ActionEvent event){
		EditClientController.person = null;
		Stage popup = loadpopup("EditCustomerView.fxml",450,350);
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
		Stage popup = loadpopup("EditCustomerView.fxml",450,350);
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
	
	public void printList(ActionEvent event){
		Stage popup = loadpopup("printDialog.fxml",550,250);
		popup.setTitle("Print Dialog");
		popup.show();
	}
	
	private  Stage loadpopup(String filename, int height, int width){
		Stage popup = new Stage();
		try{
			Parent root = FXMLLoader.load(getClass().getResource(filename));
			Scene scene = new Scene(root, height, width);
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
	

	
	
	
	

	@FXML
	public void close(ActionEvent event) {
		Platform.exit();		// add 'are you sure you want to exit application" prompt
	}
	
	
	/** GENERAL SECTION **/
	public void signOut(ActionEvent event) {
		parentEngine.setScreen(Main.screenLoginID);
	}
	
	
	
	
/** 			IN SERVICE TAB CONTROLLER SECTION 				**/
	
	/** view work order button **/
	public void viewWorkOrder(){
		int workOrder;
		WorkOrder workOrderItem;
		
		workOrderItem = serviceTable.getSelectionModel().getSelectedItem();
		
		if(workOrderItem == null)
			return;
		
		workOrder = workOrderItem.getWorkOrderAsInt();
		Platform.runLater(new PMAView(workOrder));
	}
	
	/** add work order button **/
	public void addPMA(){
		Platform.runLater(new PMAWizard());
	}
	
	/** refresh button **/
	public void refresh(){
		MyCMS.workorder.getWorksOrders(work_order_list,pma_preview_list);
		map.clear();
		
		int count = 0;
		for(WorkOrder w : work_order_list){
			int wo = w.getWorkOrderAsInt();
			map.put(wo, pma_preview_list.get(count++));
		}
		
		
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



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}