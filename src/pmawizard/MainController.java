package pmawizard;

import java.io.IOException;
import java.util.HashMap;

import pmawizard.SelectVehicleController;
import mycms.MyCMS;
import javafx.application.Platform;
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

   
    @FXML private Pane vistaHolder;   /** node that we will add children screens to. */
    @FXML private Button nextButton;
    @FXML private Button backButton;
    @FXML private Button finishButton;

    
    
    /** the currently selected customer id, -1 if no customer is selected **/
    private int custID = -1; 
  
    
    /** the customer concerns screen is typed at the end of the pma **/
    private String customerConcerns;
    
   
   /** the vehicleVIN that is currently selected via the create vehicle or select vehicle sections **/
    private String vehicleVIN;
    
    
    /**  data structure that will associate a screen name with a node 
     *   so that we won't have to reload a particular node several times;
     */
    final private HashMap<String,Node> screens = new HashMap<String,Node>();
    private String current_screen;
    
    
    /**
     * Controllers for each screen 
     */
    final private HashMap<String,ControlledScreen> controllers =  new HashMap<String,ControlledScreen>();

    
    
    /**
     * 
     * @param whichScreen
     * @return The controller for the specified fxml node
     */
    public ControlledScreen getController(String whichScreen){
    	return controllers.get(whichScreen);
    }
    
    
    
    /**
     * The setters for the constant back,next and finish buttons of the PMA Wizard
     * @param b
     */
    public void setNextButtonDisable(boolean b){
    	nextButton.setDisable(b);
    }
    public void setBackButtonDisable(boolean b){
    	backButton.setDisable(b);
    }
    public void setFinishButtonDisable(boolean b){
    	finishButton.setDisable(b);
    	finishButton.setDefaultButton(!b);
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
    
    public void next(ActionEvent event){
    	switch (current_screen){
    		case PMAWizard.SELECTCUSTOMER_SCREEN:
    		case PMAWizard.CREATECUSTOMER_SCREEN:
    			switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
    			SelectVehicleController c = (SelectVehicleController) controllers.get(PMAWizard.SELECTVEHICLE_SCREEN);
    			c.setItems(custID);
    			nextButton.setDisable(true);
    			backButton.setDisable(false);
      			break;
    		case PMAWizard.SELECTVEHICLE_SCREEN:
    		case PMAWizard.ADDVEHICLE_SCREEN:
    			switchScreen(PMAWizard.FINALCOMMENTS_SCREEN);
    			backButton.setDisable(false);
    			nextButton.setDisable(true);
    			break;
    	
    	}
    	
    } 
    public void back(ActionEvent event){
    	switch (current_screen){
			case PMAWizard.CREATECUSTOMER_SCREEN:
				switchScreen(PMAWizard.SELECTCUSTOMER_SCREEN);
				backButton.setDisable(true);
				break;
			case PMAWizard.SELECTVEHICLE_SCREEN:
				switchScreen(PMAWizard.SELECTCUSTOMER_SCREEN);
				backButton.setDisable(true);
				break;
			case PMAWizard.ADDVEHICLE_SCREEN:
				switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
				break;
			case PMAWizard.FINALCOMMENTS_SCREEN:
				switchScreen(PMAWizard.SELECTVEHICLE_SCREEN);
				finishButton.setDisable(true);
    	}
    	
    }
    
    

    // ON ACTION  SECTION 
    
    public void submitPMA(){
    	FinalCommentsController fcc = (FinalCommentsController) controllers.get(PMAWizard.FINALCOMMENTS_SCREEN);
    	customerConcerns = fcc.getCustomerConcerns();
    	MyCMS.pma.createPMA(custID, vehicleVIN, customerConcerns);
    }
    
    public void cancel(){
    	Platform.exit();
    } 
    	
}
    