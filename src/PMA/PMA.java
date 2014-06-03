package PMA;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PMA extends Application{

	private int workOrderNumber = 18;
	
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();		
		
		Parent root = fxmlLoader.load(getClass().getResource("PMA.fxml").openStream());
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
     
        PMAController controller =  fxmlLoader.getController();
        controller.initializePMA(workOrderNumber);
  
	}
	
	public void setWorkOrder(int workOrder){
		workOrderNumber = workOrder;
	}
	
	
	
}

