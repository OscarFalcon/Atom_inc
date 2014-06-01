package Custom;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class DoubleTextField extends MyTextField{
		
	
	public DoubleTextField(){
		super();
		setText("0.0");
	}
	
	@Override
	public void setValue(BigDecimal number){
		String currency = NumberFormat.getCurrencyInstance().format(number);
		super.setText(currency.substring(1, currency.length()-1));	
	}
	
	@Override
	public BigDecimal getValue() {
		String text = validStr(getText());
		return getValueOf(text);
	}
	
	@Override
	protected BigDecimal getValueOf(String input) {
		if(input.length() > 4)
			input = input.substring(0,4);
		
		input = addPeriod(input);	
		return new BigDecimal(input);
	}
	
	private static String addPeriod(String number){
		if(number.length() == 0) 
			number = "00.0";
		else {
			String save = number.substring(number.length()-1, number.length());
			number = number.substring(0, number.length()-1) + "." + save;
		}
		return number;
	} 
}


