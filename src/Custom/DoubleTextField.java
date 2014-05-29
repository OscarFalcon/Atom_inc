package Custom;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class DoubleTextField extends MyTextField{
	
	private BigDecimal maxValue = new BigDecimal(99.9);
	
	public DoubleTextField(){
		super();
		setText("00.0");
	}
	
	
	@Override
	protected BigDecimal getValueOf(String input) {
		input = validStr(input);
		input = addPeriod(input);
		if(input.equals(""))
			return new BigDecimal(00.0);		
		return new BigDecimal(Double.parseDouble(input));
	}

	@Override
	public void setValue(BigDecimal number) {
		if( (number.compareTo(maxValue) == 1)){
				setText("99.9");
				return;
		}
		setText(number.toString());
	}

	private static String addPeriod(String number){
		if(number.length() == 0) 
			number = "00.0";
		else if(number.length() == 1)
			number = ".0" + number;
		else {
			String save = number.substring(number.length()-2, number.length());
			number = number.substring(0, number.length()-2) + "." + save;
		}
		return number;
	} 
	
	
}
