package application;



import fxml.StackedEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application{

    public static String screenLoginID = "Login";
    public static String screenLoginFile = "../application/LoginView.fxml";
    public static String screenMainID = "Main";
    public static String screenMainFile = "../application/MainView.fxml";
    
    @Override
    public void start(Stage primaryStage) {
    	MainController.primaryStage = primaryStage;
        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        
    	StackedEngine engine = new StackedEngine();
    	
    	engine.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        engine.loadScreen(Main.screenMainID, Main.screenMainFile);
        engine.setScreen(Main.screenLoginID);
        
        StackPane pane = (StackPane) engine.getContainer();
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
         
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(pane);
           
        //sets the initial size of the frame (stage)
        int width = toolkit.getDefaultToolkit().getScreenSize().width;
		int height = toolkit.getDefaultToolkit().getScreenSize().height;
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        
        
        final Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
     
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }   
}
