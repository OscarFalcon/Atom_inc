package pmawizard;


import java.net.URL;
import java.util.ResourceBundle;

import mycms.InputLimits;
import mycms.MyCMS;
import workshop.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SelectCustomerController extends ControlledScreen implements Initializable{
	
	
	private ObservableList<Person> list;
	@FXML private TextField firstNameField,lastNameField,phoneField;
	@FXML private TableView<Person> customerTable;
	@FXML private TableColumn<Person,String> iDColumn,firstNameColumn,lastNameColumn,addressColumn,phoneColumn;
	
	
	public void createCustomer(ActionEvent event){
		controller.switchScreen(PMAWizard.CREATECUSTOMER_SCREEN);
	}

	public void search(){
		String first = firstNameField.getText();
		String last = lastNameField.getText();
		String phone = phoneField.getText();
		
		if(first.length() > InputLimits.MAX_CLIENT_FIRST)
			first = first.substring(0,InputLimits.MAX_CLIENT_FIRST);
		if(last.length() > InputLimits.MAX_CLIENT_LAST)
			last = last.substring(0,InputLimits.MAX_CLIENT_LAST);
		if(phone.length() > InputLimits.MAX_CLIENT_PHONE)
			phone = phone.substring(0,InputLimits.MAX_CLIENT_LAST);
		
		customerTable.getSelectionModel().clearSelection();
		list = MyCMS.customer.search(first,MyCMS.EXACTLY, last,MyCMS.EXACTLY,null,null,null,null, phone,null);
		customerTable.setItems(list);	
		
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iDColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("customerId"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
	    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
	    phoneColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("phoneId"));
	    addressColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("addressId"));
	    customerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    customerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>(){
			@Override
			public void changed(ObservableValue<? extends Person> observable,Person oldValue,Person newValue) {
				if(customerTable.getSelectionModel().getSelectedIndex() < 0)
					controller.setNextButtonDisable(true);
				else{
					controller.setNextButtonDisable(false);
					controller.setCustID(Integer.parseInt(newValue.getCustomerId()));
				}
			}
	    	
	    }); 
	    list = null;
	    customerTable.setItems(list);
	}
	
	
	
	
}