package custom;
import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


public class vendorTextField extends JTextField {
	
	public vendorTextField(){
		setDisabledTextColor(Color.white);
		setEnabled(false);
		setHorizontalAlignment(JFormattedTextField.TRAILING);
	}
}
