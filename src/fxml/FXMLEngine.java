package fxml;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public abstract class FXMLEngine {
	

	protected final HashMap<String, Node> screens;
	
	protected final HashMap<String,ControlledScreen> controllers;
	
	private Node container = null;	
	
	
	protected FXMLEngine(){
		screens = new HashMap<String,Node>();
		controllers = new HashMap<String,ControlledScreen>();
	}
	
	protected FXMLEngine(Node container){
		screens = new HashMap<String,Node>();
		controllers = new HashMap<String,ControlledScreen>();
		this.container = container;
	}
	
	
	public final Node getContainer(){
		return container;
	}
	
	public final void setContainer(Node container){
		this.container = container;
	}
	
	protected final void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }
	
	
    public final Node getScreen(String name) {
        return screens.get(name);
    }
    
    
    public final ControlledScreen getController(String whichScreen){
    	return controllers.get(whichScreen);
    }
	
	
    public final Parent loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController()); 
            myScreenControler.setScreenParent(this); // set the parent of the node to this
            controllers.put(name,myScreenControler); // add the loaded pages' controller to data structure
            addScreen(name, loadScreen);
            return loadScreen;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
    
    public final void unloadScreen(String name) {
        screens.remove(name);
        controllers.remove(name);     
    }
    
   
    
    public abstract boolean setScreen(final String name);
    
    
    
    public static final Stage loadPopup(Window owner,String fxml_file,int height, int width){
    	
    	FXMLLoader loader = new FXMLLoader(FXMLEngine.class.getResource(fxml_file));
    	Parent root;
		
    	try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
    	Scene scene = new Scene(root, height, width);
    	Stage popupStage = new Stage();
    	
    	popupStage.setScene(scene);
		popupStage.setResizable(false);
        popupStage.initOwner(owner);
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initStyle(StageStyle.UTILITY);
    	
    	return popupStage;
    }
    
    public static final Parent loadScreen(String fxml_file){
    	FXMLLoader loader = new FXMLLoader(FXMLEngine.class.getResource(fxml_file));
    	Parent root;
    	try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    	return root;
    }
    
    
    
    
    
    
    
	
	
}

