package PMA;

import java.awt.Color;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import CustMgtSys_1.*;
import CustMgtSys_1.Error;


public class PMA {
	public static void main(String args[]){
	/**	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		        	UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			try {
	           
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
			} 
			catch (UnsupportedLookAndFeelException e1) {
			}
			catch (ClassNotFoundException e1) {
	    
			}
			catch (InstantiationException e1) {
	      
			}
			catch (IllegalAccessException e1) {
	      
			}
		}**/		
		
		PMAView view = null;
		
		if(!Security.Login("birdman","password"))
			Error.ConnectionError();
		int workOrder = 16;
		try {
			view = new PMAView(workOrder);
			PMAController controller = new PMAController(view);
			controller.readSQL();
		}catch(Exception e){
		}
		
	}

}
