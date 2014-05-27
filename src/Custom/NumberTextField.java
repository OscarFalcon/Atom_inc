package Custom;

import java.text.NumberFormat;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class NumberTextField extends TextField
{
   
    
	public NumberTextField(){
		super();
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent kr) {
				String text = getText();
/*
				if(ke.getCode() == KeyCode.BACK_SPACE){
					setText(text.substring(0,text.length()-1));
					moveCarret(getText().length());
				}	
				else{
	*/			
				System.out.println("Key Pressed: " + kr.getText());
				System.out.println("getText = " + text);
				text = validStr(text); //replace all non digits with nothing
				text = trim2(text); //remove all leading 0 chars
				setValue(Double.parseDouble(addPeriod(text)));
				System.out.println("cp: " + getCaretPosition());
				//moveCarret(text.length());
				System.out.println("cp: " + getCaretPosition());
				}	
		//	}
		});
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				String text = getText();
				if(event.getCode() == KeyCode.BACK_SPACE){
					setText(text.substring(0, text.length()-1));
					moveCarret(getText().length());
				}
			}
			
		});
		setText("$0.00");
	}
	

    @Override
    public void replaceText(int start, int end, String text)
    {       	
    	if (validate(text))
        {
            super.replaceText(start, end,text);
        }
   	
    } 
    
    @Override
    public void replaceSelection(String text)
    {    	
        if (!validate(text))
        {
            super.replaceSelection(text);
        } 
    } 

    private boolean validate(String text)
    {
        if (text.matches("[0-9]"))
        {
            return true;
        }
        return false;
    }
    
    
    public void moveCarret(int pos){
        this.positionCaret(pos);

    }
    
    
    public Double getValue() {
		String s = getText();
		s = validStr(s);
		s = trim2(s);
		s = addPeriod(s);
		return Double.parseDouble(s);
	}

	public void setValue(double number){
		System.out.println("set text " + NumberFormat.getCurrencyInstance().format(number));
		super.setText(NumberFormat.getCurrencyInstance().format(number));
    	moveCarret(NumberFormat.getCurrencyInstance().format(number).length());
	}
	
	public String trim2(String text){
		int i = 0;
		while(i < text.length() && text.charAt(i) == '0'){ i++;}
		text = text.substring(i);
		return text;
	}
	
	private String validStr(String s){
		return s.replaceAll("\\D", "");
	}
	
	private String addPeriod(String number){
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