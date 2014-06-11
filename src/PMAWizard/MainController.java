package PMAWizard;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import PMAWizard.SelectVehicleController;
import MyCMS.MyCMS;

/**
 * Main controller class for the entire layout.
 */
public class MainController {

   
    @FXML private Pane vistaHolder;   /** node that we will add children screens to. */
    @FXML private Button nextButton;
    @FXML private Button backButton;
    @FXML private Button finishButton;

    
    /** data structure that will associate a screen name with a node 
     *  so that we won't have to reload a particular node several times;
     */
    final private HashMap<String,Node> screens = new HashMap<String,Node>();
    private String current_screen;
    
    
    /**
     * Controllers for each screen 
     */
    final private HashMap<String,ControlledScreen> controllers =  new HashMap<String,ControlledScreen>();

    
   
    public ControlledScreen getController(String name){
    	return controllers.get(name);
    }
    
    
   
    /** the currently selected customer id, -1 if no customer is selected **/
    private int custID = -1; 
  
    
    private String customerConcerns;
    
    private String vehicleVIN;
    
    
    
    
    
    public void setNextButtonDisable(boolean b){
    	nextButton.setDisable(b);
    }
    public void setBackButtonDisable(boolean b){
    	backButton.setDisable(b);
    }
    public void setFinishButtonDisable(boolean b){
    	finishButton.setDisable(b);
    }
  
    
    public void setCustID(int custID){
    	this.custID = custID;
    }
    public int getCustID(){
    	return custID;
    }
    public void setCustomerConcerns(String concerns){
    	customerConcerns = concerns;
    }
    public String getCustomerConcerns(){
    	return customerConcerns;
    }
    public void setVehicleVin(String vin){
    	vehicleVIN = vin;
    }
    public String getVehicleVin(){
    	return vehicleVIN;
    }
    
    
    
    
    public void submitPMA(){
    	finalCommentsController fcc = (finalCommentsController) controllers.get(PMAWizard.FINALCOMMENTS_SCREEN);
    	customerConcerns = fcc.getCustomerConcerns();
    	MyCMS.PMA.createPMA(custID, vehicleVIN, customerConcerns);
    	
    }
    
    
    
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
    	ControlledScreen controlledScreen = null;
    	try {
    		node = (Node) fxmlLoader.load();
    		controlledScreen = (ControlledScreen) fxmlLoader.getController();
    		controlledScreen.setController(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		controllers.put(name, controlledScreen);
		screens.put(name, node);
    	return true;
    }
    
    public Node getScreen(String whichScreen){
    	return screens.get(whichScreen);
    }
    
    
    public void finish(){
    	
    }
    public void cancel(){
    	Platform.exit();
    }    
    public void next(ActionEvent event){
    	switch (current_screen){
    		
    		case PMAWizard.SELECTCUSTOMER_SCREEN:
    		case PMAWizard.CREATECUSTOMER_SCREEN:
    			switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
    			SelectVehicleController c = (SelectVehicleController) controllers.get(PMAWizard.SELECTVEHICLE_SCREEN);
    			c.setItems(custID);
    			nextButton.setDisable(true);
      			break;
    		case PMAWizard.SELECTVEHICLE_SCREEN:
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
    