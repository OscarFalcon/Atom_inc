package fxml;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class FXMLEngine {
	

	protected final HashMap<String, Node> screens;
	
	protected final HashMap<String,ControlledScreen> controllers;
	
	
	private Node container;
	
	
	public FXMLEngine(){
		screens = new HashMap<String, Node>();
		controllers = new HashMap<String,ControlledScreen>();
	}
	
	public Node getContainer(){
		return container;
	}
	
	protected void setContainer(Node container){
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
    
    
    
    
    
    
    
    
    
    
	
	
}

