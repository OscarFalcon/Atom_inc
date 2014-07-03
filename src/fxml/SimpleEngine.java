package fxml;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SimpleEngine extends FXMLEngine{


	private Pane pane;
	
	public SimpleEngine(){
		super();
		pane = new Pane(); 
		setContainer(pane);
	}
	
	
	
	@Override
	public boolean setScreen(String name) {
		Node node;
		
		if((node = getScreen(name)) == null)
			return false;
	
		((Pane) getContainer()).getChildren().setAll(node);		
		return true;
	}

	
	
	
	
	
}
