package pma;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PMAView extends Application implements Runnable{

	private int workOrderNumber = -1;
	
	
	
	public PMAView(int workOrderNumber){
		super();
		this.workOrderNumber = workOrderNumber;
	}
	
	@Override
	public void start(Stage stage){
		FXMLLoader fxmlLoader = new FXMLLoader();		
		
		Parent root;
		try {
			root = fxmlLoader.load(getClass().getResource("PMA.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene scene = new Scene(root, 1350, 800);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setMaxWidth(1750);
        stage.setMaxHeight(1000);
        stage.setScene(scene);
        stage.show();
        PMAController controller =  fxmlLoader.getController();
        controller.initializePMA(workOrderNumber,this);
	}
	
	@Override
	public void run(){
		start(new Stage());
	}
	
	
	
}

