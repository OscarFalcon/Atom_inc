package PMAWizard;

import javax.swing.JPanel;

public class PMAWizardModel {
	private Integer next;
	private Integer current;
	private Integer back;
	
	public PMAWizardModel(){
		next = 2;
		current = 0;
		back = -1;
	}
	
	public Integer getNextCard(){
		System.out.println(next);
		return next;
	}
	
	public void setNextCard(Integer newNext){
		next = newNext;
	}
	
	public Integer getCurrentCard(){
		return current;
	}
	
	public void setCurrentCard(Integer newCurrent){
		current = newCurrent;
	}
	
	public Integer getBackCard(){
		return back;
	}
	
	public void setBackCard(Integer newBack){
		back = newBack;
	}
}
