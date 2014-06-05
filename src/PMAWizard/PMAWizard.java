package PMAWizard;



import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PMAWizard extends Application{
	
	
	private static final String SCREEN2 = "SelectCustomerScreen.fxml";
	private static final String SCREEN3 = "CreateCustScreen.fxml";
	private static final String SCREEN4 = "SelectVehicleScreen.fxml";
	private static final String SCREEN5 = "AddVehicleScreen.fxml";
	private static final String SCREEN6 = "FinalCommentsScreen.fxml";
	
	
	public static final String MAIN_SCREEN    = 		"SCREEN1";	
    public static final String SELECTCUSTOMER_SCREEN = 	"SCREEN2";
    public static final String CREATECUSTOMER_SCREEN = 	"SCREEN3";
    public static final String SELECTVEHICLE_SCREEN = 	"SCREEN4";
    public static final String ADDVEHICLE_SCREEN =	 	"SCREEN5";
    public static final String FINALCOMMENTS_SCREEN = 	"SCREEN6";
	
	
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage){
		Parent root;
		FXMLLoader fxmlLoader = new FXMLLoader();		
		
		try{
			root = fxmlLoader.load(getClass().getResource("PMAWizard.fxml").openStream());
		}catch(Exception e){	
			e.printStackTrace();
			return;
		}
		MainController mainController = fxmlLoader.getController();
		mainController.loadScreen(SELECTCUSTOMER_SCREEN, SCREEN2);
	    mainController.loadScreen(CREATECUSTOMER_SCREEN, SCREEN3);
	    mainController.loadScreen(SELECTVEHICLE_SCREEN, SCREEN4);
	   // controller.loadScreen(ADDVEHICLE_SCREEN,SCREEN5);
	   // controller.loadScreen(FINALCOMMENTS_SCREEN,SCREEN6);
		
		mainController.setNextButtonDisable(true);
		mainController.setBackButtonDisable(true);
		mainController.setFinishButtonDisable(true);
		mainController.switchScreen(SELECTCUSTOMER_SCREEN);
		
		Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();
	}
}

