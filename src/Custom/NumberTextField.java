package Custom;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class NumberTextField extends TextField
{
	private String lastInput;
	private boolean flag; 
   
	public NumberTextField(){
		super();

		this.setOnKeyReleased(new EventHandler<KeyEvent>(){ 
			@Override
			public void handle(KeyEvent event) {
				moveCarret(getText().length());
			}	
		});
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				lastInput = getText();
				if(event.getCode() == KeyCode.BACK_SPACE)
					flag = true;
				else
					flag = false;
				
				moveCarret(getText().length());
			}
	    });		
		setText("$0.00");
	}
	
	@Override
    public void replaceText(int start, int end, String text){       
		if(flag)
        	setValue(getValueOf(lastInput.substring(0, lastInput.length()-1)));
    	else
    		setValue(getValueOf(lastInput + text));	
    	
    	moveCarret(getText().length()); /** must have **/
    } 
   /**
    * @return the value of the TextField as a Double
    */
	public BigDecimal getValue() {
		return getValueOf(getText());
	}
	
    /**	private methods **/
    private void setValue(BigDecimal number){
		String currency = NumberFormat.getCurrencyInstance().format(number);
		super.setText(currency);
	}
    private void moveCarret(int pos){
        this.positionCaret(pos);
    }
    
    
    /** private static methods **/
    private static String trim(String text){
		int i = 0;
		while(i < text.length() && text.charAt(i) == '0')
			i++;
		text = text.substring(i);
		return text;
	}
	private static String validStr(String s){
		return s.replaceAll("\\D", "");
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
	/** public static methods **/
    public static BigDecimal getValueOf(String currency){
    	currency = validStr(currency);
    	currency = trim(currency);
    	currency = addPeriod(currency);
    	BigDecimal bd = new BigDecimal(Double.parseDouble(currency));
    	return bd;
    }
}