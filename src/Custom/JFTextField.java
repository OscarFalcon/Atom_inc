package Custom;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 * This is the custom textfield for the QTYfield.
 * 
 * 
 * @author adrian
 *
 */
public class JFTextField extends JFormattedTextField{
	
	
	public JFTextField(NumberFormat format){
		super(format);
		setText("1");
		setDisabledTextColor(Color.white);
		setEnabled(false);
        setHorizontalAlignment(JFormattedTextField.TRAILING);
        
		addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				selectAll();
			}
		});
        
		addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String text = getText();
				text = validStr(text); //replace all non digits with nothing
				setText(text);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
        
	}
	
	public Object getValue(){
	  	if(super.getText().equals(""))
	  		return(0);
	    else return Integer.parseInt(super.getText());
	}
	
	private String validStr(String s){
		return s.replaceAll("\\D", "");
	}
}
