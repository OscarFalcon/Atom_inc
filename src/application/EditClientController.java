package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

	
	public class EditClientController implements Initializable {
		public static final int EDIT = 0;
		public static final int ADD = 1;

		@FXML
		Button confirm;
		@FXML
		TextField firstAddField; // Value injected by FXMLLoader

		@FXML
		TextField lastAddField; // Value injected by FXMLLoader
		
		public static Person person;

		int flag = ADD;
		private Stage myParent;
		private Stage messageBoxStage;
		
		/*
		public void showMessageBox(Stage parentStage, Person person) {
		    this.myParent = parentStage;
		    this.person = person;
		    
		    try {
		        messageBoxStage = new Stage();
		        AnchorPane page = (AnchorPane) FXMLLoader.load(EditClientController.class.getResource("AddCustomerView.fxml"));
		        Scene scene = new Scene(page);
		        messageBoxStage.setScene(scene);
		        messageBoxStage.initOwner(this.myParent);
		        messageBoxStage.initModality(Modality.WINDOW_MODAL);
		        messageBoxStage.initStyle(StageStyle.UTILITY);
		        messageBoxStage.setResizable(false);
		        messageBoxStage.setTitle("Edit Client");

		        messageBoxStage.show();
		    } catch (Exception ex) {
		        System.out.println("Exception found in showMessageBox");
		        ex.printStackTrace();
		    }
		    
		  
		}
	*/
		public void initialize(URL fxmlFileLocation, ResourceBundle arg1) {
			
	        if(person == null){
	        	confirm.setText("Add");
	        	return;
	        }
	        confirm.setText("Edit");
	        flag = EDIT;
			firstAddField.setText(person.getFirstName());
			lastAddField.setText(person.getLastName());
			
			//on confirm

		}

		public void OnBtnYes(ActionEvent event) {
			if(flag == EDIT){
				//sql.edit stuff
			}
			else  //flag == ADD
				//sql.addperson
				;
		}

		public void OnBtnNo(ActionEvent event) {

		}
	
	

}
