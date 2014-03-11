package PMA;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import CustMgtSys_1.PMAObject;
import CustMgtSys_1.Security;


public class PMA {
	public static void main(String args[]){
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())){
					//UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("ComboBox:'Combobox.listRenderer'[Disabled].textForeground", Color.red);
				}
			}
		} catch (Exception e) {
			
		}
		
		PMAView view = null;
		Security.Login("Birdman","password");
		int wo = 18;
		try {
			view = new PMAView(wo);
			PMAController controller = new PMAController(view);
			controller.readSQL();
		}catch(Exception e){
		}
		
	}

}
