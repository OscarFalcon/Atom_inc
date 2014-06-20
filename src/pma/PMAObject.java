package pma;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

import com.sun.prism.PhongMaterial.MapType;

public class PMAObject extends Object implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	public static enum Status {
		NO_STATUS,				/** lock all fields, except for tech comments: lock menu items **/
		OK_SELECTED,			/** lock all fields, including tech comments : lock menu items **/
		NOT_OK_SELECTED,		/** unlock all fields : unlock menu items **/
		
		APPROVED,				/** lock all fields, including tech comments : unlock menu items **/
		NOT_APPROVED,			/** lock all fields, including tech comments : unlock menu items **/
		INFORMATION_ONLY		/** lock all fields, including tech comments : unlock menu items **/
		
	}
	
	public static enum Priority { /** refers to the selection of the priority check boxes **/
		HIGH		
		,MED		
		,LOW
	}
	
	/**
	 * Convince data structures that double map a priority type to its string equivalent
	 */
	public static final HashMap<Priority,String> p_s_map = new HashMap<Priority,String>();
	
	public static final HashMap<String,Priority> s_p_map = new HashMap<String,Priority>(); 
	
	static{
		p_s_map.put(Priority.HIGH,"HIGH");
		p_s_map.put(Priority.MED,"MED");
		p_s_map.put(Priority.LOW,"LOW");
		
		s_p_map.put("HIGH", Priority.HIGH);
		s_p_map.put("MED", Priority.MED);
		s_p_map.put("LOW", Priority.LOW);
		
	}

	/** the number of rows in the pma **/
	private int ROW_COUNT; 
	
	
	/** header of PMA **/
	public int wo;
	public int year;
	public String first,last;
	public String tags,make,model,lic,vin,engine,trans,miles;
	public String customer_concerns;
	public Date date;
	

	/** OK/NOTOK	 - 	APPROVED-DISAPPROVED-INFORMATION_ONLY **/
	public Status[][] ROW_STATUS;
	
	/** REPRESENTS THE SELECTED INDEX OF PRIORITY DROP DOWN MENU **/
	public Priority[] priority;
	
	public String[] descriptions;
	public String[] tech_comments;
	public String[] recommended_repairs;
	public String[] vendor;
	
	/** price columns of PMA **/
	public BigDecimal[] totalParts;  		/** what the customer sees **/
	public BigDecimal[] totalLabor;  		/** what the customer sees **/
	public BigDecimal[] laborCost;   		/** stores cost **/
	public BigDecimal[] partCost;    		/** stores cost **/
	public BigDecimal[] totals;  			/** HIGH,MED,LOW,PARTS,LABOR,TOTAL_PARTS_AND_LABOR,TAX,SHOP_SUPPLIES,GRAND_TOTAL **/
	public BigDecimal[] qty; 		

	public final BigDecimal markUpRate_parts;
	public final BigDecimal markUpRate_labor;
	
	
	
	
	public PMAObject(int rowCount,BigDecimal markUpRateParts,BigDecimal markUpRateLabor){
		this.ROW_COUNT = rowCount;
		this.markUpRate_parts = markUpRateParts;
		this.markUpRate_labor = markUpRateLabor;
		
		descriptions =  		new String[ROW_COUNT];
		tech_comments = 		new String[ROW_COUNT];
		recommended_repairs = 	new String[ROW_COUNT];
		priority = 				new Priority[ROW_COUNT];
		totalParts = 			new BigDecimal[ROW_COUNT];
		totalLabor = 			new BigDecimal[ROW_COUNT];
		laborCost = 			new BigDecimal[ROW_COUNT];
		partCost = 				new BigDecimal[ROW_COUNT];
		ROW_STATUS = 			new Status[ROW_COUNT][2];
		qty = 					new BigDecimal[ROW_COUNT];
		vendor = 				new String[ROW_COUNT];
		totals = 				new BigDecimal[9];	
		
		for(int i = 0; i < ROW_COUNT; i ++){
			totalParts[i] = new BigDecimal(0.0);
			totalLabor[i] = new BigDecimal(0.0);
			laborCost[i] = new BigDecimal(0.0);
			partCost[i] = new BigDecimal(0.0);
			qty[i] = new BigDecimal(0);
			ROW_STATUS[i][0] = Status.NO_STATUS;
			ROW_STATUS[i][1] = Status.NO_STATUS;		
		}
		
		for(int i = 0; i < 9; i++)
			totals[i] = new BigDecimal(0.0);
	}
	
	
	
	
	
	
	public int getRowCount(){
		return ROW_COUNT;
	}
	
	public static String priorityToString(Priority value){
		return p_s_map.get(value);
	}
	
	public static Priority stringToPriority(String value){
		return s_p_map.get(value);
	}
	
	
	
	
}
	