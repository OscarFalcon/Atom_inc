package FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import Custom.NumberTextField;

public class MyController implements Initializable{

	@FXML private CheckBox test;
	
	@FXML private Label cust;
	
	@FXML GridPane grid;
	
	public void checked(){
		
		test.getStyleClass().remove("check-box-regular");
		test.getStyleClass().add("check-box-valid");
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		test.setSelected(true);
		cust.setText("TESTING");
		
 
		grid.add(new NumberTextField(),6, 15);

	}
		
	
	
		
	
	
	
	
}
