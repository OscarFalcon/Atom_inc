package pmawizard;



import fxml.SimpleEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PMAWizard extends Application implements Runnable{
	
	
	private static final String SCREEN2 = "../pmawizard/SelectCustomerScreen.fxml";
	private static final String SCREEN3 = "../pmawizard/CreateCustScreen.fxml";
	private static final String SCREEN4 = "../pmawizard/SelectVehicleScreen.fxml";
	private static final String SCREEN5 = "../pmawizard/AddVehicleScreen.fxml";
	private static final String SCREEN6 = "../pmawizard/FinalCommentsScreen.fxml";

	public static final String MAIN_SCREEN    = 		"SCREEN1";	
    public static final String SELECTCUSTOMER_SCREEN = 	"SCREEN2";
    public static final String CREATECUSTOMER_SCREEN = 	"SCREEN3";
    public static final String SELECTVEHICLE_SCREEN = 	"SCREEN4";
    public static final String ADDVEHICLE_SCREEN =	 	"SCREEN5";
    public static final String FINALCOMMENTS_SCREEN = 	"SCREEN6";
	
    	
	@Override
	public void start(Stage stage){		
		Parent root;
		
		
		
		
		
		
		SimpleEngine engine = new SimpleEngine();
		root = engine.loadScreen("PMAWizard", "../pmawizard/PMAWizard.fxml");
		MainController mainController = (MainController) engine.getController("PMAWizard");
		
		engine.setContainer(mainController.getVistaHolder());
		
		
		engine.loadScreen(SELECTCUSTOMER_SCREEN, SCREEN2);
		engine.loadScreen(CREATECUSTOMER_SCREEN, SCREEN3);
		engine.loadScreen(SELECTVEHICLE_SCREEN, SCREEN4);
		engine.loadScreen(ADDVEHICLE_SCREEN,SCREEN5);
		engine.loadScreen(FINALCOMMENTS_SCREEN,SCREEN6);
		
		/**FXMLLoader fxmlLoader = new FXMLLoader();		
		
		try{
			root = fxmlLoader.load(getClass().getResource("PMAWizard.fxml").openStream());
		}catch(Exception e){	
			e.printStackTrace();
			return;
		}**/
		
		/**MainController mainController = fxmlLoader.getController();
		mainController.loadScreen(SELECTCUSTOMER_SCREEN, SCREEN2);
	    mainController.loadScreen(CREATECUSTOMER_SCREEN, SCREEN3);
	    mainController.loadScreen(SELECTVEHICLE_SCREEN, SCREEN4);
	    mainController.loadScreen(ADDVEHICLE_SCREEN,SCREEN5);
	    mainController.loadScreen(FINALCOMMENTS_SCREEN,SCREEN6);**/
		
		mainController.setNextButtonDisable(true);
		mainController.setBackButtonDisable(true);
		mainController.setFinishButtonDisable(true);
		
		mainController.switchScreen(SELECTCUSTOMER_SCREEN);
		mainController.setStage(stage);
		Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();
        
 
	}
	
	
	
	@Override
	public void run(){
		start(new Stage());
	}

	
	
	
	
}

