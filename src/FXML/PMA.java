package FXML;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class PMA extends Application{

  
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		int lport;
	    String rhost;
	    int rport;
	    
		/**String user = "";
	    String password = "";
	    String host = "24.153.253.126";
	    int port = 22;
	  
		JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        lport = 4321;
        rhost = "localhost";
        rport = 3306;
        System.out.println("Setting password");
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
        int assinged_port=session.setPortForwardingL(lport, rhost, rport);
        System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport); **/
		
		Parent root = FXMLLoader.load(getClass().getResource("PMA.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
	}
}

