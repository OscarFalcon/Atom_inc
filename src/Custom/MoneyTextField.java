package Custom;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MoneyTextField extends MyTextField{

	
	public MoneyTextField(){
		super();
		setText("$0.00");
	}
	@Override
	protected BigDecimal getValueOf(String input) {
		input = validStr(input);
    	input = trim(input);
    	input = addPeriod(input);
    	BigDecimal bd = new BigDecimal(Double.parseDouble(input));
    	return bd;
	}

	@Override
	public void setValue(BigDecimal number) {
		String currency = NumberFormat.getCurrencyInstance().format(number);
		super.setText(currency);
	}
	
	/** private static methods **/
    private static String trim(String text){
		int i = 0;
		while(i < text.length() && text.charAt(i) == '0')
			i++;
		text = text.substring(i);
		return text;
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