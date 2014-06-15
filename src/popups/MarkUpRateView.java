package popups;

import java.io.IOException;

import pma.PMAController;
import pma.PMAObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MarkUpRateView extends Application implements Runnable{

	private PMAObject pma;
	private int index;
	private Stage owner;
	private PMAController pma_controller;
	
	
	public MarkUpRateView(PMAObject pma, int index,Stage owner,PMAController controller){
		this.owner = owner;
		this.index = index;
		this.pma = pma;
		this.pma_controller = controller;
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();		
		
		Parent root;
		try {
			root = fxmlLoader.load(getClass().getResource("/popups/markUpRates.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.initOwner(owner);
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		MarkUpRateController c = fxmlLoader.getController();
		c.initializeFields(pma,index,pma_controller,primaryStage);
	
	}

	@Override
	public void run() {
		try {
			start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
