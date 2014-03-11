package CustMgtSys_1;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import PMA.PMAController;
import PMA.PMAView;


public class TwoFatGuys {
	
	
	
	public static void main(String args[]){
		
		 try {
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
		}		
		new MainWindowView();
		

	}
	
	
}
