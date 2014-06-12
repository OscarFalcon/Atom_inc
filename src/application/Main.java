package application;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{

    public static String screenLoginID = "Login";
    public static String screenLoginFile = "LoginView.fxml";
    public static String screenMainID = "Main";
    public static String screenMainFile = "MainView.fxml";
    
    
    
    @Override
    public void start(Stage primaryStage) {
        MainController.primaryStage = primaryStage;
        
    	ScreenController mainContainer = new ScreenController();
    	mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        mainContainer.loadScreen(Main.screenMainID, Main.screenMainFile);
        mainContainer.setScreen(Main.screenLoginID);
        
        mainContainer.prefHeightProperty().bind(primaryStage.heightProperty());
        mainContainer.prefWidthProperty().bind(primaryStage.widthProperty());
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(mainContainer);
            
        
        //sets the initial size of the frame (stage)
        primaryStage.setWidth(1300);
        primaryStage.setHeight(775);
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
