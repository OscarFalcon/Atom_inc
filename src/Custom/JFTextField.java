package Custom;
import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;


public class JFTextField extends JFormattedTextField{
	
	
	public JFTextField(NumberFormat format){
		super(format);
		setText("1");
		setDisabledTextColor(Color.white);
		setEnabled(false);
        setHorizontalAlignment(JFormattedTextField.TRAILING);
	}
	
	public Object getValue(){
	  	if(super.getText().equals(""))
	  		return(0);
	    else return Integer.parseInt(super.getText());
	}

}
