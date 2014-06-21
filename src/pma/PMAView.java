package pma;



import com.sun.javafx.tk.Toolkit;
import java.awt.*;
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
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
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
		
		int width = toolkit.getDefaultToolkit().getScreenSize().width;
		int height = toolkit.getDefaultToolkit().getScreenSize().height;
		
		final Scene scene = new Scene(root, width, height);
        stage.setMinWidth(1200);
        stage.setMinHeight(600);
        //stage.setMaxWidth(2050);
        //stage.setMaxHeight(1000);
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

