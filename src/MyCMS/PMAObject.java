package MyCMS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import FXML.MyController;

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
	
	/** OK/NOTOK columns **/
	public boolean[] ok;
	public boolean[] notok;
	
	public int[] ROW_STATUS;
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
		
		ok = new boolean[ROW_COUNT];
		notok = new boolean[ROW_COUNT];
		tech_comments = new String[ROW_COUNT];
		recommended_repairs = new String[ROW_COUNT];
		priority = new int[ROW_COUNT];
		totalParts = new BigDecimal[ROW_COUNT];
		totalLabor = new BigDecimal[ROW_COUNT];
		laborCost = new BigDecimal[ROW_COUNT];
		partCost = new BigDecimal[ROW_COUNT];
		ROW_STATUS = new int[ROW_COUNT];
		qty = new BigDecimal[ROW_COUNT];
		vendor = new String[ROW_COUNT];
		totals = new BigDecimal[9];	
		
		for(int i = 0; i < ROW_COUNT; i ++){
			totalParts[i] = new BigDecimal(0.0);
			totalLabor[i] = new BigDecimal(0.0);
			laborCost[i] = new BigDecimal(0.0);
			partCost[i] = new BigDecimal(0.0);
			qty[i] = new BigDecimal(0);
			ROW_STATUS[i] = MyController.NO_STATUS;
		}
		
	}
}
	