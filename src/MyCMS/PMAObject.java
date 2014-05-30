package MyCMS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class PMAObject extends Object implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private static int ROW_COUNT = 50;
	
	public static final int NO_ACTION = 0;
	public static final int APPROVED = 1;
	public static final int NOT_APPROVED = 2;
	public static final int INFORMATION_ONLY = 3;
	
	public static final int HIGH = 0;
	public static final int MED = 1;
	public static final int LOW = 2;
	
	/** header of PMA **/
	public int wo;
	public String first,last;
	public String tags,make,model,lic,vin,engine,trans,miles;
	public Date date;
	public String customer_concerns;
	public int year;
	
	/** ok/notok columns **/
	public boolean[] ok;
	public boolean[] notok;
	
	/** Either NO_ACTION,APPROVED,NOT_APPROVED,INFORMATION_ONLY **/
	public int[] approved;
	
	public String[] tech_comments;
	public String[] recommended_repairs;
	public int[] priority;
	
	/** price columns of PMA **/
	public BigDecimal[] totalParts;//what the customer sees
	public BigDecimal[] totalLabor; //what the customer sees
	public BigDecimal[] laborCost; // stores cost
	public BigDecimal[] partCost; // stores cost 
	
	public int[] qty; 
	
	public String[] vendor;
	
	/**
	 * HIGH,MED,LOW,PARTS,LABOR,TOTAL_PARTS_AND_LABOR,TAX,SHOP_SUPPLIES,GRAND_TOTAL
	 */
	public BigDecimal[] totals; 
	
	
	public PMAObject(){
		ok = new boolean[ROW_COUNT];
		notok = new boolean[ROW_COUNT];
		tech_comments = new String[ROW_COUNT];
		recommended_repairs = new String[ROW_COUNT];
		priority = new int[ROW_COUNT];
		totalParts = new BigDecimal[ROW_COUNT];
		totalLabor = new BigDecimal[ROW_COUNT];
		laborCost = new BigDecimal[ROW_COUNT];
		partCost = new BigDecimal[ROW_COUNT];
		approved = new int[ROW_COUNT];
		qty = new int[ROW_COUNT];
		vendor = new String[ROW_COUNT];
		totals = new BigDecimal[9];
	}
	
	public String toString(){
		return "first name = " + first + " total part = " + totalParts[0];
	}
	
}
	