package MyCMS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class PMAObject extends Object implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int wo;
	public String first,last;
	public String tags,make,model,lic,vin,engine,trans,miles;
	public Date date;
	public String customer_concerns;
	public int year;
	
	public boolean[] ok;
	public boolean[] notok;
	public boolean[] approved;
	public String[] tech_comments;
	public String[] recommended_repairs;
	public int[] priority;
	
	public BigDecimal[] totalParts;//what the customer sees
	public BigDecimal[] totalLabor; //what the customer sees
	
	public BigDecimal[] laborCost; // stores cost
	public BigDecimal[] partCost; // stores cost 
	
	public int[] qty;
	
	public String[] vendor;
	public BigDecimal[] totals; 
	
	
	public PMAObject(){
		ok = new boolean[50];
		notok = new boolean[50];
		tech_comments = new String[50];
		recommended_repairs = new String[50];
		priority = new int[50];
		totalParts = new BigDecimal[50];
		totalLabor = new BigDecimal[50];
		laborCost = new BigDecimal[50];
		partCost = new BigDecimal[50];
		approved = new boolean[50];
		qty = new int[50];
		vendor = new String[50];
		totals = new BigDecimal[9];
	}
	
	public String toString(){
		return "first name = " + first + " total part = " + totalParts[0];
	}
	
}
	