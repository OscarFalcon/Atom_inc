package Custom;

import java.math.BigDecimal;

public class IntegerTextField extends MyTextField{

	@Override
	public BigDecimal getValueOf(String input) {
		input = validStr(input);
		if(input.equals(""))
			return new BigDecimal(0.0);
		if(input.length() > 3) /** to big to be an Integer! **/
			input = input.substring(0,3);
		return new BigDecimal(Integer.parseInt(input));
	}
	
	
	@Override
	public void setValue(BigDecimal number) {
		setText(new Integer(number.intValue()).toString());
	}
	
	@Override
	public BigDecimal getValue() {
		return getValueOf(getText());
	}
	
}
