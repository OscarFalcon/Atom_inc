package PMAWizard;

import java.net.URL;
import java.util.ResourceBundle;

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
	}

	public void setItems(int custID){
		System.out.println("searching for..." + custID);
		ObservableList<Vehicle> items = MyCMS.vehicle.getVehicles(custID);
		if(items == null)
			return;
		vehicleTable.setItems(items);
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yearColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("year"));
		makeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("make"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("model"));
		licenseColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("license"));
		vinColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("VIN"));
	}
	

}
