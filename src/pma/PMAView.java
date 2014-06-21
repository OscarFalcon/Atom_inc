package pma;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PMAView extends Application implements Runnable{

	private final String resource = "../pma/PMA.fxml";
	private final String resource_name = "Screen";
	
	
	private int workOrderNumber;
	
	public PMAView(int workOrderNumber){
		super();
		this.workOrderNumber = workOrderNumber;
	}
	
	@Override
	public void start(Stage stage){
		Parent root = null;
		/**SimpleEngine engine = new SimpleEngine();
		engine.loadScreen(resource_name,resource);
		engine.setScreen(resource_name);		
		Node node = engine.getContainer(); **/
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		try{
			root = fxmlLoader.load(getClass().getResource("PMA.fxml").openStream());	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		final Scene scene = new Scene(root, 1350, 800);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setMaxWidth(1750);
        stage.setMaxHeight(1000);
        stage.setScene(scene);
        stage.show();
       
        PMAController controller =  fxmlLoader.getController();
        controller.initializePMA(workOrderNumber);
 
	}
	
	@Override
	public void run(){
		start(new Stage());
	}
	
	
	
}

