package PMAWizard;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * Main controller class for the entire layout.
 */
public class MainController {

    /** Holder of a switchable vista. */
    @FXML
    private Pane vistaHolder;
    @FXML
    Button nextButton;
    @FXML
    Button backButton;
    @FXML
    Button finishButton;
 
 
    
    public static final String MAIN_SCREEN    = 		"PMAWizard.fxml";
    public static final String SELECTCUSTOMER_SCREEN = 	"SelectCustomerScreen.fxml";
    public static final String CREATECUSTOMER_SCREEN = 	"CreateCustScreen.fxml";
    public static final String SELECTVEHICLE_SCREEN = 	"SelectVehicleScreen.fxml";
    public static final String ADDVEHICLE_SCREEN =	 	"AddVehicleScreen.fxml";
    public static final String FINALCOMMENTS_SCREEN = 	"FinalCommentsScreen.fxml";

    
    private String current_screen = SELECTCUSTOMER_SCREEN;
         


    
    public void switchScreen(String whichScreen){
    	
    	try {
 			Node node = (Node) FXMLLoader.load(MainController.class.getResource(whichScreen));
 			AnchorPane.setBottomAnchor(node, 28.0);
 			AnchorPane.setLeftAnchor(node,25.0);
 			AnchorPane.setRightAnchor(node, 14.0);
 			AnchorPane.setTopAnchor(node, 29.0);
 			vistaHolder.getChildren().setAll(node);
 			current_screen = whichScreen;
    	} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}	
    }
    
    public Pane getScreen(String whichScreen){
    	Pane pane = null;
    	try {
 			pane = (Pane) FXMLLoader.load(MainController.class.getResource(whichScreen));
    	} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}	
    	return pane;
    }
    
    
    
    public void next(ActionEvent event){
    	switch (current_screen){
    		
    		case SELECTCUSTOMER_SCREEN:
    			switchScreen(CREATECUSTOMER_SCREEN);
    			break;
    		case CREATECUSTOMER_SCREEN:
    			switchScreen(SELECTVEHICLE_SCREEN);
    			break;
    		case SELECTVEHICLE_SCREEN:
    			switchScreen(ADDVEHICLE_SCREEN);
    			break;
    		case ADDVEHICLE_SCREEN:
    			switchScreen(FINALCOMMENTS_SCREEN);
    			break;
    	
    	}
    	
    }
    public void back(ActionEvent event){
    	
    	switch (current_screen){
			case SELECTCUSTOMER_SCREEN:
				break;
			case CREATECUSTOMER_SCREEN:
				switchScreen(SELECTCUSTOMER_SCREEN);
				break;
			case SELECTVEHICLE_SCREEN:
				switchScreen(CREATECUSTOMER_SCREEN);
				break;
			case ADDVEHICLE_SCREEN:
				switchScreen(SELECTVEHICLE_SCREEN);
				break;
			case FINALCOMMENTS_SCREEN:
				switchScreen(ADDVEHICLE_SCREEN);
    	}
    	
    }
    	
}
    