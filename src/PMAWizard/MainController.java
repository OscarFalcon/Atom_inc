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
    @FXML
    AnchorPane anchorPane;
    
    public static final int SELECTCUSTOMER = 0;
    public static final int CREATECUSTOMER = 1;
    public static final int SELECTVEHICLE = 2;
    public static final int ADDVEHICLE = 3;
    public static final int FINALCOMMENTS = 4;
    public int current = SELECTCUSTOMER;
    
    
    
    public static final String MAIN    = "PMAWizard.fxml";
    public static final String ADDVEHICLE_SCREEN = "AddVehicleScreen.fxml";
    public static final String CREATECUSTOMER_SCREEN = "CreateCustScreen.fxml";
    public static final String SELECTCUSTOMER_SCREEN = "SelectCustomerScreen.fxml";
    public static final String SELECTVEHICLE_SCREEN = "SelectVehicleScreen.fxml";
    public static final String FINALCOMMENTS_SCREEN = "FinalCommentsScreen.fxml";

    
    
    
    
    
        

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     * @throws IOException 
     */
    public void setVista(Node node){

    	
    	
    		
    	vistaHolder.getChildren().setAll(node);
             
    	
    	//vistaHolder.getChildren().setAll(((Node) FXMLLoader.load(
            //         VistaNavigator.class.getResource(fxml))
               //  )
         //    );
             
             /**
             if(fxml.equals(SELECTCUSTOMER_SCREEN))
            	 current = SELECTCUSTOMER;
             else if(fxml.equals(CREATECUSTOMER_SCREEN))
            	 current = CREATECUSTOMER;
             else if(fxml.equals(SELECTVEHICLE_SCREEN))
            	 current = SELECTVEHICLE;
             else if(fxml.equals(ADDVEHICLE_SCREEN))
            	 current = ADDVEHICLE;
             else if(fxml.equals(FINALCOMMENTS_SCREEN))
            	 current = FINALCOMMENTS;**/
             
         
    }
    
    void switchScreen(String whichScreen){
    	try {
 			FXMLLoader.load(MainController.class.getResource(whichScreen));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	
    	
    	
    	
    	
    }
    
    
    
    
         
    
    public void next(ActionEvent event){
    	switch (current){
    		
    		case SELECTCUSTOMER:
			try {
				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.SELECTVEHICLE)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			//setVista(SELECTVEHICLE_SCREEN);
    			break;
    		case CREATECUSTOMER:
			try {
				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.SELECTVEHICLE)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			//setVista(SELECTVEHICLE_SCREEN);
    			break;
    		case SELECTVEHICLE:
			try {
				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.FINALCOMMENTS)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			//setVista(FINALCOMMENTS_SCREEN);	
    			break;
    			
    		case ADDVEHICLE:    			
			try {
				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.FINALCOMMENTS)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        			//setVista(FINALCOMMENTS_SCREEN);	
    			break;
    		case 4:
    			break;
    	
    	
    	}
    	
    }
    
    public void back(ActionEvent event){
    	switch (current){
    		case SELECTCUSTOMER:
    			break;
    			
    		case CREATECUSTOMER:
    			try {
    				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.SELECTCUSTOMER)));
    				current = SELECTCUSTOMER;
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}
    			break;
    			
    		case SELECTVEHICLE:
    			try {
    				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.SELECTCUSTOMER)));
    				current = SELECTCUSTOMER;
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}
    			break;
    			
    		case ADDVEHICLE:
    			try {
    				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.SELECTVEHICLE)));
    				current = SELECTVEHICLE;
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}
    			break;
    			
    		case FINALCOMMENTS:
    			try {
    				vistaHolder.getChildren().setAll((Node)FXMLLoader.load(VistaNavigator.class.getResource(VistaNavigator.SELECTVEHICLE)));
    				current = SELECTVEHICLE;
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}
    			break;
    				
    	}
    	
    }
    
    

   
    	
}
    