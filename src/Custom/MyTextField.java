package Custom;

import java.math.BigDecimal;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public abstract class MyTextField extends TextField{
	private String lastInput;
	private boolean flag;
	
	
	
	public MyTextField(){
		super();
	
		this.setOnKeyReleased(new EventHandler<KeyEvent>(){ 
			@Override
			public void handle(KeyEvent event) {
				moveCaret(getText().length());
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
				
				moveCaret(getText().length());
			}
	    });		
	}
	
	@Override
    public void replaceText(int start, int end, String text){       
		if(flag)
        	setValue(getValueOf(lastInput.substring(0, lastInput.length()-1)));
    	else
    		setValue(getValueOf(lastInput + text));	
    	
    	moveCaret(getText().length()); /** must have **/
    } 
	
	

	/**
	 * This method must return the BigDecimal representation of the TextField
	 * @return
	 */
	protected abstract BigDecimal getValueOf(String input);
	
	
	/**
	 * This method must set the TextFields output to that specified by number
	 * @param number
	 */
	public abstract void setValue(BigDecimal number);
	
	
	 /**
	  * Used to move the caret of the TextField
	  * @param pos
	  */
	 final protected void moveCaret(int position){
		 this.positionCaret(position);
	 }
	 
	 final protected String validStr(String input){
			return input.replaceAll("\\D", "");
	 }
	 
	
}
