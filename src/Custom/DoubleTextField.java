package Custom;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.math.RoundingMode;

public class DoubleTextField extends MyTextField{
		
	
	public DoubleTextField(){
		super();
		setText("0.0");
	}
	
	
	@Override
	protected BigDecimal getValueOf(String input) {
		if(input.equals(""))
			return new BigDecimal(00.0);
		
		if(input.length() > 4){
			input = input.substring(0,4);
			return new BigDecimal(Double.parseDouble(input));
		}
		input = validStr(input);
    	input = trim(input);
    	input = addPeriod(input);
    	BigDecimal bd = new BigDecimal(Double.parseDouble(input));
    	return bd;
	}
	
	@Override
	public void setValue(BigDecimal number) {
		String currency = NumberFormat.getCurrencyInstance().format(number);
		super.setText(currency.substring(1, currency.length()-1));	
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
	
	private static String trim(String text){
		int i = 0;
		while(i < text.length() && text.charAt(i) == '0')
			i++;
		text = text.substring(i);
		return text;
	}
	
	
	
}


