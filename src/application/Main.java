package application;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author john
 */
public class Main extends Application{

    public static String screenLoginID = "Login";
    public static String screenLoginFile = "LoginView.fxml";
    public static String screenMainID = "Main";
    public static String screenMainFile = "MainView.fxml";
    
    
    @Override
    public void start(Stage primaryStage) {
        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        mainContainer.loadScreen(Main.screenMainID, Main.screenMainFile);
        
        
        mainContainer.setScreen(Main.screenLoginID);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(mainContainer);
        
       
        mainContainer.prefHeightProperty().bind(primaryStage.heightProperty());
        mainContainer.prefWidthProperty().bind(primaryStage.widthProperty());
        
        
        
        //sets the initial size of the frame (stage)
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }   
}
