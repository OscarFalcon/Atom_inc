package PMAWizard;

import java.io.IOException;
import java.util.HashMap;

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
    @FXML Button nextButton;
    @FXML Button backButton;
    @FXML Button finishButton;

    private String current_screen;
    final private HashMap<String,Node> screens = new HashMap<String,Node>();

    
    public void switchScreen(String whichScreen){
    	Node node = null;
    	
    	if((node = screens.get(whichScreen)) == null)
    		return;
    	AnchorPane.setBottomAnchor(node,1.0);
 		AnchorPane.setLeftAnchor(node,1.0);
 		AnchorPane.setRightAnchor(node, 1.0);
 		AnchorPane.setTopAnchor(node, 1.0);
 		vistaHolder.getChildren().setAll(node);
 		current_screen = whichScreen;
    }
    
    public boolean loadScreen(String name,String filename){
    	if(screens.get(name) != null) /** screen already loaded **/
    			return true;
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filename));
    	Node node = null;
    	try {
    		node = (Node) fxmlLoader.load();
    		ControlledScreen controlledScreen = (ControlledScreen) fxmlLoader.getController();
    		controlledScreen.setController(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    	
		screens.put(name, node);
    	return true;
    }
    
    public Node getScreen(String whichScreen){
    	return screens.get(whichScreen);
    }
    
    
    
    public void next(ActionEvent event){
    	switch (current_screen){
    		
    		case PMAWizard.SELECTCUSTOMER_SCREEN:
    			switchScreen(PMAWizard.CREATECUSTOMER_SCREEN);
    			break;
    		case PMAWizard.CREATECUSTOMER_SCREEN:
    			switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
    			break;
    		case PMAWizard.SELECTVEHICLE_SCREEN:
    			switchScreen(PMAWizard.ADDVEHICLE_SCREEN);
    			break;
    		case PMAWizard.ADDVEHICLE_SCREEN:
    			switchScreen(PMAWizard.FINALCOMMENTS_SCREEN);
    			break;
    	
    	}
    	
    }
    
    public void back(ActionEvent event){
    	
    	switch (current_screen){
			case PMAWizard.SELECTCUSTOMER_SCREEN:
				break;
			case PMAWizard.CREATECUSTOMER_SCREEN:
				switchScreen(PMAWizard.SELECTCUSTOMER_SCREEN);
				break;
			case PMAWizard.SELECTVEHICLE_SCREEN:
				switchScreen(PMAWizard.CREATECUSTOMER_SCREEN);
				break;
			case PMAWizard.ADDVEHICLE_SCREEN:
				switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
				break;
			case PMAWizard.FINALCOMMENTS_SCREEN:
				switchScreen(PMAWizard.ADDVEHICLE_SCREEN);
    	}
    	
    }
    	
}
    