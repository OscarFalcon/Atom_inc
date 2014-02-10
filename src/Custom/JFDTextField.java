package Custom;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

public class JFDTextField extends JFormattedTextField {

	public JFDTextField(NumberFormat format) {
		super(format);
		setDisabledTextColor(Color.white);
		setEnabled(false);
		setHorizontalAlignment(JFormattedTextField.TRAILING);
		setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent comp) {
				try {
					setBackground(Color.white);
					String value = getText();
					if (!value.startsWith("$")) {
						value = "$" + value;
					}
					System.out.println(value);
					setText(value);
					commitEdit();
					return true;
				} catch (ParseException e) {
					setBackground(Color.RED);
					return false;
				}
			}
		});

	}
	
	
	@Override
	public void setEditable(boolean isEditable){
		super.setEditable(isEditable);
		if(isEditable == false){
			setEnabled(false);
			setDisabledTextColor(Color.black);
			setBackground(Color.yellow);
		}else{
			setEnabled(true);
			setDisabledTextColor(Color.white);
			setBackground(Color.white);
		}
	}
	
	

	@Override
	protected void invalidEdit() {
		String value = getText();
		if (!value.startsWith("$")) {
			value = "$" + value;
		}
		System.out.println(value);
		setText(value);
	}

	public Double getValue() {
		if (super.getText().equals(""))
			return (0.00);
		else
			return (new Double(super.getValue().toString()));
	}

	@Override
	public String getText() {
		if (super.getText().equals(""))
			return ("0.0");
		else
			return super.getText();
	}
}


