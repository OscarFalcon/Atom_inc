package Custom;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MoneyTextField extends MyTextField{

	
	public MoneyTextField(){
		super();
		setText("$0.00");
	}
	
	@Override
	public void setValue(BigDecimal number) {
		String currency = NumberFormat.getCurrencyInstance().format(number);
		super.setText(currency);
	}
	
	@Override
	public BigDecimal getValue() {
		String text = validStr(getText());
		return getValueOf(text);
	} 
	
	@Override
	protected BigDecimal getValueOf(String input){		
		if(input.length() > 8)
			input = input.substring(0, 8);
    	
		input = addPeriod(input);    	
    	BigDecimal bd = new BigDecimal(input);
    	return bd;
	}
	
	private static String addPeriod(String number){
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
