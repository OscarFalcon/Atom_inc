package PMAWizard;

import java.net.URL;
import java.util.ResourceBundle;

import application.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import workshop.Vehicle;
import MyCMS.MyCMS;

public class SelectVehicleController extends ControlledScreen implements Initializable{
	
	
	@FXML private TableView<Vehicle> vehicleTable;
	
	@FXML private TableColumn<Vehicle,String> yearColumn,makeColumn,modelColumn,licenseColumn,vinColumn;
	
	public void addVehicle(){
		controller.switchScreen(PMAWizard.ADDVEHICLE_SCREEN);
		controller.setNextButtonDisable(true);
	}

	public void setItems(int custID){
		ObservableList<Vehicle> items = MyCMS.vehicle.getVehicles(custID);
		if(items == null)
			return;
		vehicleTable.setItems(items);
	}
	
	public void clearSelection(){
		vehicleTable.getSelectionModel().clearSelection();
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yearColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("year"));
		makeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("make"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("model"));
		licenseColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("license"));
		vinColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("VIN"));
		
		vehicleTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>(){
			@Override
			public void changed(ObservableValue<? extends Vehicle> observable,Vehicle oldValue,Vehicle newValue) {
				if(vehicleTable.getSelectionModel().getSelectedIndex() < 0)
					controller.setNextButtonDisable(true);
				else{
					controller.setNextButtonDisable(false);
					controller.setVehicleVin(newValue.getVIN());
				}	
			}
		});
		
	}
	

}
