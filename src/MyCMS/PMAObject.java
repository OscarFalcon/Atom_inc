package MyCMS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import PMA.PMAController;

public class PMAObject extends Object implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int ROW_COUNT;
	
	
	/** header of PMA **/
	public int wo;
	public int year;
	public String first,last;
	public String tags,make,model,lic,vin,engine,trans,miles;
	public String customer_concerns;
	public Date date;
	

	/** OK/NOTOK	 - 	APPROVED-DISAPPROVED-INFORMATION_ONLY **/
	public int[][] ROW_STATUS;
	
	/** REPRESENTS THE SELECTED INDEX OF PRIORITY DROP DOWN MENU **/
	public int[] priority;
	
	public String[] tech_comments;
	public String[] recommended_repairs;
	public String[] vendor;
	
	/** price columns of PMA **/
	public BigDecimal[] totalParts;  /** what the customer sees **/
	public BigDecimal[] totalLabor;  /** what the customer sees **/
	public BigDecimal[] laborCost;   /** stores cost **/
	public BigDecimal[] partCost;    /** stores cost **/
	public BigDecimal[] totals;  /** HIGH,MED,LOW,PARTS,LABOR,TOTAL_PARTS_AND_LABOR,TAX,SHOP_SUPPLIES,GRAND_TOTAL **/
	public BigDecimal[] qty; 		

	
	
	public PMAObject(int rowCount){
		ROW_COUNT = rowCount;
		
		tech_comments = new String[ROW_COUNT];
		recommended_repairs = new String[ROW_COUNT];
		priority = new int[ROW_COUNT];
		totalParts = new BigDecimal[ROW_COUNT];
		totalLabor = new BigDecimal[ROW_COUNT];
		laborCost = new BigDecimal[ROW_COUNT];
		partCost = new BigDecimal[ROW_COUNT];
		ROW_STATUS = new int[ROW_COUNT][2];
		qty = new BigDecimal[ROW_COUNT];
		vendor = new String[ROW_COUNT];
		totals = new BigDecimal[9];	
		
		for(int i = 0; i < ROW_COUNT; i ++){
			totalParts[i] = new BigDecimal(0.0);
			totalLabor[i] = new BigDecimal(0.0);
			laborCost[i] = new BigDecimal(0.0);
			partCost[i] = new BigDecimal(0.0);
			qty[i] = new BigDecimal(0);
			ROW_STATUS[i][0] = PMAController.NO_STATUS;
			ROW_STATUS[i][1] = PMAController.NO_STATUS;			
		}
		for(int i = 0; i < 9; i++)
			totals[i] = new BigDecimal(0.0);
		
		
	}
}
	