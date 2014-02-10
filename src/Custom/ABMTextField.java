package Custom;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.DecimalFormat;
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

public class ABMTextField extends JFormattedTextField {
	private DecimalFormat format;
	private String decimal;

	public ABMTextField(DecimalFormat format) {
		this.format = format;
		this.setDisabledTextColor(Color.white);
		this.setEnabled(false);
		decimal = Character.toString(format.getDecimalFormatSymbols()
				.getDecimalSeparator());

		setColumns(format.toPattern().length());
		setHorizontalAlignment(JFormattedTextField.TRAILING);

		setText(format.format(0.0));

		AbstractDocument doc = (AbstractDocument) getDocument();
		doc.setDocumentFilter(new ABMFilter());
	}
	
	public void reset(){
		
		AbstractDocument doc = (AbstractDocument) getDocument();
		doc.setDocumentFilter(new ABMFilter());
	}
	

	public Object getValue() {
		if (super.getText().equals(""))
			return (0.00);

		else {
			String value = super.getText();
			value = value.replace("$", "");
			return Double.parseDouble(value.replace(",", ""));
		}
	}

	
	public void setValue(double number){
		setText(format.format(number));
	}

	@Override
	public String getText() {
		if (super.getText().equals(""))
			return ("0.0");
		else
			return super.getText();
	}

	@Override
	public void setText(String text) {
		System.out.println("setText to this" + text);
		Number number = format.parse(text, new ParsePosition(0));

		if (number != null)
			super.setText(text);
	}

	public class ABMFilter extends DocumentFilter {
		public void insertString(FilterBypass fb, int offs, String str,
				AttributeSet a) throws BadLocationException {
			replace(fb, offs, 0, str, a);
		}

		public void replace(FilterBypass fb, int offs, int length, String str,
				AttributeSet a) throws BadLocationException {
			if ("0123456789".contains(str)) {
				Document doc = fb.getDocument();
				StringBuilder sb = new StringBuilder(doc.getText(0,
						doc.getLength()));

				int decimalOffset = sb.indexOf(decimal);

				if (decimalOffset != -1) {
					sb.deleteCharAt(decimalOffset);
					sb.insert(decimalOffset + 1, decimal);
				}

				sb.append(str);

				try {
					String text = format.format(format.parse(sb.toString()));
					super.replace(fb, 0, doc.getLength(), text, a);
				} catch (ParseException e) {
				}
			} else
				Toolkit.getDefaultToolkit().beep();
		}

		public void remove(DocumentFilter.FilterBypass fb, int offset,
				int length) throws BadLocationException {
			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder(
					doc.getText(0, doc.getLength()));

			int decimalOffset = sb.indexOf(decimal);

			if (decimalOffset != -1) {
				sb.deleteCharAt(decimalOffset);
				sb.insert(decimalOffset - 1, decimal);	
			}

			sb.deleteCharAt(sb.length() - 1);

			try {
				String text = format.format(format.parse(sb.toString()));
				super.replace(fb, 0, doc.getLength(), text, null);
			} catch (ParseException e) {
			}
		}
	}
}
