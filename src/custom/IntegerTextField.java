package custom;

import java.math.BigDecimal;

public class IntegerTextField extends MyTextField{

	
	public IntegerTextField(){
		super();
		setText("0");
	}
	
	@Override
	public BigDecimal getValueOf(String input) {
		if(input.equals(""))
			return new BigDecimal(0.0);
		if(input.length() > 3) 		/** to big to be an Integer! **/
			input = input.substring(0,3);
		return new BigDecimal(Integer.parseInt(input));
	}
	
	@Override
	public void setValue(BigDecimal number) {	
		setText(new Integer(number.intValue()).toString());
	}
	
	@Override
	public BigDecimal getValue() {
		String text = validStr(getText());
		return getValueOf(text);
	}
	
}
