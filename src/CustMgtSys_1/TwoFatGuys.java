package CustMgtSys_1;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


public class TwoFatGuys {
	
	
	
	public static void main(String args[]){
		Color normal = new Color(128, 0, 0);
		Color darkBlue = new Color(0, 0, 51);
		Color orange = new Color(255, 128, 0);
		Color silver = new Color(220, 220, 220);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		        	UIManager.setLookAndFeel(info.getClassName());
		            UIManager.put("nimbusBase",new Color(81,127,164));
		            UIManager.put("nimbusBlueGrey",new Color(81,127,164));
		            UIManager.put("control", null);
		            break;
		        }
		    }
		} catch (Exception e) {
			try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
			} 
			catch (UnsupportedLookAndFeelException e1) {
	       // handle exception
			}
			catch (ClassNotFoundException e1) {
	       // handle exception
			}
			catch (InstantiationException e1) {
	       // handle exception
			}
			catch (IllegalAccessException e1) {
	       // handle exception
			}
		}		
		 
		MainWindowView view = new MainWindowView();
		MainWindowController controller = new MainWindowController(view);
		//view.registerControllers(controller);
			
			
		//	String insert = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone)" + 
			//			"VALUES(" + "'" + s[1] + "' ,'" + s[2] + "', '" + s[5] + "' ,'" + s[6] + "' ,'" +
				//		"Texas" + "' ,'" + s[7] + "' ,'" + s[4] + "' ,'" + s[3] + "');";
		
    	
    	
		
		
	}
	
	
}
