package fxml;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SimpleEngine extends FXMLEngine{

	private Pane container;
	
	public SimpleEngine(){
		super();
		container = new Pane();
		setContainer(container);
	}
	
	
	@Override
	public boolean setScreen(String name) {
		Node node;
		
		if((node = getScreen(name)) == null)
			return false;
	
		container.getChildren().setAll(node);
		return true;
	}

	
	
	
}
