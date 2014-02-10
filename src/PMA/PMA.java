package PMA;

import java.awt.Color;


public class PMA {
	public static void main(String args[]){
		PMAView view = new PMAView();
		view.nameField.setText("Adrian Martinez");
		view.dateField.setText("Jan 10, 1995");
		view.customerConcerns.setText("flat tire yo!!");
		view.checkBoxes[1][1].setSelected(true);
		view.checkBoxes[1][1].setBackground(Color.red);
		view.comboBoxes[1][0].getEditor().setItem("TEXT has changed!!!!!!");
		view.comboBoxes[1][1].getEditor().setItem("TEXT has Changed!!");
		view.totalFields[1][0].setEnabled(true);
		view.totalFields[1][0].setValue(50.99);
		view.numberFields[1][0].setEnabled(true);
		view.numberFields[1][0].setValue(25.00);
		PMAController controller = new PMAController(view);
		view.submit.addActionListener(controller);
		
	}

	private static Object makeObj(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}
}
