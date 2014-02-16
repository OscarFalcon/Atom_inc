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

public class ABMTextField extends JTextField {
	private DecimalFormat format;

	public ABMTextField(DecimalFormat format) {
		this.format = format;
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
				if(!Character.isDigit(text.charAt(text.length()-1))){ 
					text = text.substring(text.length());
					setText(text);
					return;
				}
				text = text.replace("$", "");
				text = text.replace(".", "");
				text = text.replace(",", "");
				text = trim2(text);				//remove all leading 0 chars
				if(text.length() == 0) 
					text = "0.00";
				else if(text.length() == 1)
					text = ".0" + text;
				else {
					String save = text.substring(text.length()-2, text.length());
					text = text.substring(0, text.length()-2) + "." + save;
				}
				setValue(Double.parseDouble(text));
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
	
	public String trim2(String text){
		int i =0;
		while(i < text.length() && text.charAt(i) == '0'){ i++;}
		text = text.substring(i);
		return text;
	}
	
	public Double getValue() {
		return Double.parseDouble(getText());
	}

	public void setValue(double number){
		setText(NumberFormat.getCurrencyInstance().format(number));
	}
}