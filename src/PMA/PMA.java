package PMA;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


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
		
		PMAView view = new PMAView();
		view.nameField.setText("Adrian Martinez");
		view.dateField.setText("Jan 10, 1995");
		view.customerConcerns.setText("flat tire yo!!");
		view.activateLine(true, 1);
		view.checkBoxes[1][1].setSelected(true);
		view.checkBoxes[1][1].setBackground(Color.red);
		view.comboBoxes[1][0].getEditor().setItem("TEXT has changed!!!!!!");
		view.comboBoxes[1][1].getEditor().setItem("TEXT has Changed!!");
		//view.totalFields[1][0].setEnabled(true);
		view.totalFields[1][0].setValue(50.99);
		view.numberFields[1][0].setValue(25.00);
		PMAController controller = new PMAController(view);
	}

	private static Object makeObj(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}
}
