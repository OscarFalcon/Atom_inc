package popups;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import mycms.MyCMS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import custom.IntegerTextField;
import pma.PMAObject;
import pma.PMAController;

public class MarkUpRateController implements Initializable {

	@FXML private GridPane grid;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	
	private IntegerTextField partsTextField,laborTextField;
	
	private int index;
	private PMAController pma_controller;	
	private Stage stage;
	
	private static final BigDecimal one_hundred = new BigDecimal(100.00);
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		partsTextField = new IntegerTextField();
		laborTextField = new IntegerTextField();
		
		grid.add(partsTextField, 1, 0);
		grid.add(laborTextField, 1, 1);
		
	}
	public void initializeFields(PMAObject pma,int index,PMAController controller,Stage stage){
		this.pma_controller = controller;
		this.index = index;
		this.stage = stage;
		
		partsTextField.setValue(pma.markUpRates[index]);
	}
	
	
	
	public void submit(){
		if(MyCMS.employee.login_employee(usernameField.getText(), passwordField.getText())){
			BigDecimal value = new BigDecimal(0.0);
			value = partsTextField.getValue();
			value = value.divide(one_hundred);
			pma_controller.setMarkUpRate(index,value);
			pma_controller.computeTotals();
		}
		else
			System.out.println("wrong username");
		
		stage.close();
	}
	
	

}
