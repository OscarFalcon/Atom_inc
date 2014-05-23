package FXML;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PMA extends Application{

  
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("PMA.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
	}
}
