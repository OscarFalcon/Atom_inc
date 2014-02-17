package Custom;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.MaskFormatter;

public class ABMTextField extends JFormattedTextField {

	public ABMTextField(DecimalFormat format) {
		this.setDisabledTextColor(Color.white);
		this.setEnabled(false);

		setColumns(format.toPattern().length());
		setHorizontalAlignment(JFormattedTextField.TRAILING);
		setText(NumberFormat.getCurrencyInstance().format(0.0));
			
		addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				setCaretPosition(getText().length());
			}
		});
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String text = getText();
				text = validStr(text); //replace all non digits with nothing
				text = trim2(text); //remove all leading 0 chars
				setValue(Double.parseDouble(addPeriod(text)));
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
	
	public Double getValue() {
		String s = getText();
		s = validStr(s);
		s = trim2(s);
		s = addPeriod(s);
		return Double.parseDouble(s);
	}

	public void setValue(double number){
		setText(NumberFormat.getCurrencyInstance().format(number));
	}
	
	public String trim2(String text){
		int i = 0;
		while(i < text.length() && text.charAt(i) == '0'){ i++;}
		text = text.substring(i);
		return text;
	}
	
	private String validStr(String s){
		return s.replaceAll("\\D", "");
	}
	
	private String addPeriod(String number){
		if(number.length() == 0) 
			number = "0.00";
		else if(number.length() == 1)
			number = ".0" + number;
		else {
			String save = number.substring(number.length()-2, number.length());
			number = number.substring(0, number.length()-2) + "." + save;
		}
		return number;
	}
	
	
	
}