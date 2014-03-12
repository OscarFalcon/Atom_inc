package CustMgtSys_1;

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
	public String tags,year,make,model,lic,vin,engine,trans,miles;
	public Date date;
	public String customer_concerns;
	
	
	public byte[] ok;
	public byte[] notok;
	public byte[] approved;
	public String[] tech_comments;
	public String[] recommended_repairs;
	public int[] priority;
	public BigDecimal[] totalParts;
	public BigDecimal[] totalLabor;
	public BigDecimal[] laborCost;
	public BigDecimal[] partCost;
	public int[] qty;
	public String[] vendor;
	public BigDecimal[] totals; 
	
	
	public PMAObject(){
		ok = new byte[42];
		notok = new byte[42];
		tech_comments = new String[42];
		recommended_repairs = new String[42];
		priority = new int[42];
		totalParts = new BigDecimal[42];
		totalLabor = new BigDecimal[42];
		laborCost = new BigDecimal[42];
		partCost = new BigDecimal[42];
		approved = new byte[42];
		qty = new int[42];
		vendor = new String[42];
		totals = new BigDecimal[9];
	}
	
	public String toString(){
		return "first name = " + first + " total part = " + totalParts[0];
	}
	
}
	