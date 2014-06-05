package PMAWizard;


import java.net.URL;
import java.util.ResourceBundle;

import application.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import MyCMS.MyCMS;

public class SelectCustomerController extends ControlledScreen implements Initializable{
	
	
	private ObservableList<Person> list;
	@FXML private TextField firstNameField,lastNameField,phoneField;
	@FXML private TableView<Person> customerTable;
	@FXML private TableColumn<Person,String> iDColumn,firstNameColumn,lastNameColumn,addressColumn,phoneColumn;
	
	
	public void createCustomer(ActionEvent event){
		controller.switchScreen(PMAWizard.CREATECUSTOMER_SCREEN);
	}

	public void search(){
		//String first = first
		//list = MyCMS.client.search(first, b1, last, b2, address, city, state, zip, phone, email)
		
		
		
	}

	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iDColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("customerId"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
	    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
	    phoneColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("phoneId"));
	    addressColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("addressId"));
	}
	
	
	
	
}