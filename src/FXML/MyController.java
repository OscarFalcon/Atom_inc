package FXML;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.swing.SwingUtilities;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.aspose.cells.Color;
import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.PageOrientationType;
import com.aspose.cells.PageSetup;
import com.aspose.cells.PaperSizeType;
import com.aspose.cells.SheetRender;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import Custom.DoubleTextField;
import Custom.IntegerTextField;
import Custom.MoneyTextField;
import MyCMS.*;


public class MyController implements Initializable{
	
	private static int ROW_COUNT = 50; //the number of rows in the PMA;
	private static int WORK_ORDER_NUMBER = 5; //the work order number used to load a specific PMA 
	
	
	/** title fields **/
	@FXML private Label CUST, DATE, TAGS, YEAR, MAKE, MODEL, WO, LICNUM, VIN, ENG, TRANS, MILES;
	
	/**OK CHECK BOXES **/
	@FXML private CheckBox OK1, OK2, OK3, OK4, OK5, OK6, OK7, OK8, OK9, OK10, OK11, OK12, OK13,OK14,OK15, OK16, OK17, OK18, OK19, OK20, OK21, OK22
	, OK23, OK24, OK25, OK26, OK27, OK28, OK29,OKM,OK30, OK31, OK32, OK33, OK34, OK35, OK36, OK37, OK38, OK39, OK40, OK41, OK42, OK43, OK44, OK45, OK46
	, OK47, OK48, OK49,OK50;
	
	/** NOT OK CHECK BOXES **/
	@FXML private CheckBox NOTOK1, NOTOK2, NOTOK3, NOTOK4, NOTOK5, NOTOK6, NOTOK7, NOTOK8, NOTOK9, NOTOK10, NOTOK11, NOTOK12, NOTOK13, NOTOK14, NOTOK15
		,NOTOK16, NOTOK17, NOTOK18, NOTOK19, NOTOK20, NOTOK21, NOTOK22, NOTOK23, NOTOK24, NOTOK25, NOTOK26, NOTOK27, NOTOK28, NOTOK29,NOTOK30
		,NOTOK31, NOTOK32, NOTOK33,NOTOK34, NOTOK35, NOTOK36, NOTOK37, NOTOK38, NOTOK39, NOTOK40, NOTOK41, NOTOK42, NOTOK43, NOTOK44, NOTOK45 
		,NOTOK46, NOTOK47, NOTOK48, NOTOK49,NOTOK50;
	
	/** TECHNICIANS' COMMENTS **/
	@FXML private  ComboBox<String> TC1,TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9,TC10,TC11,TC12,TC13,TC14,TC15,TC16,TC17,TC18,TC19,TC20,TC21,TC22,TC23,TC24,TC25
					,TC26,TC27,TC28,TC29,TC30,TC31,TC32,TC33,TC34,TC35,TC36,TC37,TC38,TC39,TC40,TC41,TC42,TC43,TC44,TC45,TC46,TC47,TC48,TC49,TC50;
	
	/** RECOMMENDED REPAIRS  **/
	@FXML private ComboBox<String> RR1,RR2,RR3,RR4,RR5,RR6,RR7,RR8,RR9,RR10,RR11,RR12,RR13,RR14,RR15,RR16,RR17,RR18,RR19,RR20,RR21,RR22,RR23,RR24,RR25,RR26
					,RR27,RR28,RR29,RR30,RR31,RR32,RR33,RR34,RR35,RR36,RR37,RR38,RR39,RR40,RR41,RR42,RR43,RR44,RR45,RR46,RR47,RR48,RR49,RR50;
	
	/** PRIORITY **/
	@FXML private ComboBox<String> PRIOR1,PRIOR2,PRIOR3,PRIOR4,PRIOR5,PRIOR6,PRIOR7,PRIOR8,PRIOR9,PRIOR10,PRIOR11,PRIOR12,PRIOR13,PRIOR14,PRIOR15,PRIOR16,PRIOR17,PRIOR18
					,PRIOR19,PRIOR20,PRIOR21,PRIOR22,PRIOR23,PRIOR24,PRIOR25,PRIOR26,PRIOR27,PRIOR28,PRIOR29,PRIOR30,PRIOR31,PRIOR32,PRIOR33,PRIOR34
					,PRIOR35,PRIOR36,PRIOR37,PRIOR38,PRIOR39,PRIOR40,PRIOR41,PRIOR42,PRIOR43,PRIOR44,PRIOR45,PRIOR46,PRIOR47,PRIOR48,PRIOR49,PRIOR50;
	
	/** title labels **/
	@FXML Label L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17,L18,L19,L20,L21,L22,L23,L24,L25,L26,L27,L28,L29,L30,L31,L32,L33,L34,L35,L36,L37,
				L38,L39,L40,L41,L42;
	
	/** vendors fields **/
	@FXML private TextField vendor1,vendor2,vendor3,vendor4,vendor5,vendor6,vendor7,vendor8,vendor9,vendor10,vendor11,vendor12,vendor13,vendor14,
								vendor15,vendor16,vendor17,vendor18,vendor19,vendor20,vendor21,vendor22,vendor23,vendor24,vendor25,
								vendor26,vendor27,vendor28,vendor29,vendor30,vendor31,vendor32,vendor33,vendor34,vendor35,vendor36,vendor37,
								vendor38,vendor39,vendor40,vendor41,vendor42,vendor43,vendor44,vendor45,vendor46,vendor47,vendor48,vendor49,vendor50; 
	
	/** customer concerns text area **/
	@FXML private TextArea CustConcerns;
	
	/** grid pane that holds the rows of the PMA **/
	@FXML private GridPane grid;

	private CheckBox[][] checkboxes;
	
	private ComboBox<String>[] techcomments;
	
	private ComboBox<String>[] recommendedrepairs;
	
	private ComboBox<String>[] priorities;
	
	private TextField[] vendors;
	
	private Label[] labels;
	
	final private MoneyTextField[][] moneyFields = new MoneyTextField[ROW_COUNT][3];
	
	final private IntegerTextField[] QTY = new IntegerTextField[ROW_COUNT];
	
	final private DoubleTextField[] laborHours = new DoubleTextField[ROW_COUNT];
	
	final private MenuItem[] menuItemsApproved = new MenuItem[ROW_COUNT];
	
	final private MenuItem[] menuItemsDisapproved = new MenuItem[ROW_COUNT];
	
	final private MenuItem[] menuItemsInformation = new MenuItem[ROW_COUNT];
	
	private PMAObject PMA; 		/** the PMA object that will be used throughout the program **/
	
	
	public void checked(ActionEvent event){		
		short place,index;
		CheckBox box;
		
		place = 0; 	
		box = (CheckBox) event.getSource();
		
		while(box != checkboxes[place][0] && box != checkboxes[place][1])  /** get the box that was checked **/
			place++;
		if(box == checkboxes[place][0])
			index = 0;
		else
			index = 1;
		
		checkboxes[place][0].getStyleClass().removeAll("check-box-regular","check-box-invalid");																			
		checkboxes[place][index].getStyleClass().removeAll("check-box-regular","check-box-invalid");	
		checkboxes[place][0].getStyleClass().add("check-box-regular");
		checkboxes[place][1].getStyleClass().add("check-box-regular");
		disableAndClearFields(place);
		
		if (index == 0){						 						/**if event came from OK check box **/
			checkboxes[place][1].setSelected(false);
			disableMenuItems(place,true,true,true);		
			PMA.approved[place] = PMAObject.NO_ACTION;
			
		}else{ 	/** event came from NOT OK **/
			checkboxes[place][0].setSelected(false);					/** set OK checkbox to be not selected **/
			
			if(checkboxes[place][index].isSelected()){					/** The Check Box is selected **/
				checkboxes[place][1].getStyleClass().remove("check-box-regular"); 
				checkboxes[place][1].getStyleClass().add("check-box-invalid");	
				disableFields(place,false);								/** enable fields **/
				disableMenuItems(place,false,false,false);
			}
			else{												/** NOT OK  not selected **/
				disableMenuItems(place,true,true,true);
				PMA.approved[place] = PMAObject.NO_ACTION;
			}
			
		}	
		techcomments[place].setDisable(false);
	}
	
	
	/** set all the fields except tech comments to b **/
	private void disableFields(int place,boolean b){
		recommendedrepairs[place].setDisable(b);
		priorities[place].setDisable(b);
		moneyFields[place][0].setDisable(b);
		moneyFields[place][1].setDisable(b);
		moneyFields[place][2].setDisable(b);
		QTY[place].setDisable(b);
		laborHours[place].setDisable(b);
		vendors[place].setDisable(b);
	}
	/** sets all the fields in a row to thier default values **/
	private void clearFields(int place){
		recommendedrepairs[place].setValue("");
		priorities[place].setValue("");
		moneyFields[place][0].setValue(new BigDecimal(0));
		moneyFields[place][1].setValue(new BigDecimal(0));
		moneyFields[place][2].setValue(new BigDecimal(0));
		QTY[place].setValue(new BigDecimal(0));
		vendors[place].setText("");
	}
	private void clearCSS(int place){
		priorities[place].getStyleClass().removeAll("custom-field","green-label","red-label","yellow-label");		
		priorities[place].getEditor().getStyleClass().removeAll("custom-field","green-label","red-label","yellow-label");
		priorities[place].getStyleClass().add("custom-field");
		priorities[place].getEditor().getStyleClass().add("custom-field");
		moneyFields[place][0].getStyleClass().removeAll("custom-field","green-label","red-label","yellow-label");
		moneyFields[place][1].getStyleClass().removeAll("custom-field","green-label","red-label","yellow-label");
	}
	
	private void disableAndClearFields(int place){
		clearFields(place);
		clearCSS(place);
		disableFields(place,true);
	}
	
	private void disableMenuItems(int place, boolean approved, boolean disapproved, boolean information){
			menuItemsApproved[place].setDisable(approved);
			menuItemsDisapproved[place].setDisable(disapproved);
			menuItemsInformation[place].setDisable(information);
	}
	private void lockRow(int place){
		techcomments[place].setDisable(true);
		disableFields(place,true);
	}
	

	public void excel() throws Exception {

		File excel = new File("PMA.xls"); 					/**open file to read from master PMA **/
		FileInputStream fis = new FileInputStream(excel);	/**create fileInputStream from master PMA file **/
		
		
		HSSFWorkbook wb = new HSSFWorkbook(fis); 			/**create new workbook based on master PMA **/	
		Sheet ws = wb.getSheet("Sheet1");					
		
		Cell cell;
		Row row;											
		row = ws.getRow(2);								/** ROW = 2 **/
		cell = row.getCell(0);							/** CUSTOMER NAME **/
		cell.setCellValue(CUST.getText());
		cell = row.getCell(3);							/** YEAR ***/ 
		cell.setCellValue(YEAR.getText());
		cell = row.getCell(4);							/** WORK ORDER NUMBER **/
		cell.setCellValue(WO.getText());
		cell = row.getCell(7);							/** ENGINE **/
		cell.setCellValue(ENG.getText());											
		row = ws.getRow(3);								/** ROW = 3 */
		cell = row.getCell(0);							/** DATE **/
		cell.setCellValue(DATE.getText());
		cell = row.getCell(3);							/** MAKE **/
		cell.setCellValue(MAKE.getText());
		cell = row.getCell(4);							/** LICNUM **/
		cell.setCellValue(LICNUM.getText());
		cell = row.getCell(7);							/** TRANS **/
		cell.setCellValue(TRANS.getText());
		row = ws.getRow(4);								/** ROW = 4 **/			
		cell = row.getCell(0);							/** TAGS **/
		cell.setCellValue(TAGS.getText());
		cell = row.getCell(3);							/** MODEL **/
		cell.setCellValue(MODEL.getText());
		cell = row.getCell(4);							/** VIN **/
		cell.setCellValue(VIN.getText());
 		cell = row.getCell(7);							/** MILES **/
		cell.setCellValue(MILES.getText());
		
		
		HSSFFont redFont = wb.createFont();				/** create style for red x **/
		redFont.setColor(HSSFColor.RED.index);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(redFont);				
	
		
		int rowIndex = 7; 								/** rows for in excel start at row = 7. **/
		for(int i = 0; i < ROW_COUNT; i++,rowIndex++){ 
			row = ws.getRow(rowIndex+addRows(i)); 
			
			
			cell = row.getCell(1);						/** OK **/
			if(checkboxes[i][0].isSelected())
				cell.setCellValue("x");
														/** NOT OK **/
			cell = row.getCell(2); 
			if(checkboxes[i][1].isSelected()){
				cell.setCellStyle(style);
				HSSFRichTextString richString = new HSSFRichTextString("  x");
				richString.applyFont(redFont);
				cell.setCellValue(richString);
			}
			
			if(PMA.approved[i] != PMAObject.NO_ACTION){
				cell = row.getCell(3); 							/**  TECH COMMENTS  **/ 
				cell.setCellValue(techcomments[i].getEditor().getText());
				cell = row.getCell(4);							/** RECOMMENDED REPAIRS **/
				cell.setCellValue(recommendedrepairs[i].getEditor().getText());
				cell = row.getCell(5);							/** PRIORITY **/ 
				cell.setCellValue(priorities[i].getEditor().getText());
				cell = row.getCell(6);							/** TIRES - PARTS **/
				cell.setCellValue(moneyFields[i][0].getText());
				cell = row.getCell(7);							/** TOTAL LABOR **/
				cell.setCellValue(moneyFields[i][1].getText());
				cell = row.getCell(9);							/** QTY **/
				cell.setCellValue(QTY[i].getText());
				cell = row.getCell(10);							/** PARTS COST **/
				cell.setCellValue(moneyFields[i][2].getText());
				cell  = row.getCell(11);						/** LABOR HOURS **/
				cell.setCellValue(laborHours[i].getText());
			}
			
		}
		
		
		
		fis.close();
		FileOutputStream fileOut = null;
		/** open new file stream to write contents of workbook to excel **/
		fileOut = new FileOutputStream("/home/oscar/Desktop/workbook.xls");
		wb.write(fileOut);
		fileOut.close();
		
		
		
		/** list of available printers **/
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        for (PrintService printer : printServices)
            System.out.println("Printer: " + printer.getName()); 
		
		
        /** print dialog **/
		/**
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		attributes.add(new PageRanges(1,1));
		PrinterJob pj = PrinterJob.getPrinterJob();	
		if (pj.printDialog(attributes)){
	    	//Instantiate a new workbook
			Workbook book = new Workbook("/home/oscar/Desktop/workbook.xls");
			
			//Create an object for ImageOptions
			ImageOrPrintOptions  imgOptions = new ImageOrPrintOptions();
			
			//Get the first worksheet
			Worksheet sheet = book.getWorksheets().get(0);
			
			
			PageSetup pageSetup = sheet.getPageSetup();
			pageSetup.setOrientation(PageOrientationType.LANDSCAPE);
			pageSetup.setZoom(73);
			pageSetup.setBottomMargin(1);
			pageSetup.setLeftMargin(1);
			pageSetup.setRightMargin(1);
			pageSetup.setTopMargin(1);
			pageSetup.setPaperSize(PaperSizeType.PAPER_A_4);
			
			//Create a SheetRender object with respect to your desired sheet
			SheetRender sr = new SheetRender(sheet, imgOptions);

			//Print the worksheet  
			sr.toPrinter("Canon-MP240-series");
				
	    }   **/
	}
	
	private int addRows(int rowNum){
		return (rowNum < 5) ? 0 : (rowNum < 15) ? 1 : (rowNum < 31) ? 2 : (rowNum < 42) ? 3 : 4; 
	}
	
	
	public void save(){
		for(int i = 0; i < ROW_COUNT; i++){
			PMA.ok[i] = checkboxes[i][0].isSelected();										/** OK **/
			PMA.notok[i] = checkboxes[i][0].isSelected();									/** NOT OK **/
			PMA.tech_comments[i] = techcomments[i].getEditor().getText();					/** TECH COMMENTS **/
			PMA.recommended_repairs[i] = recommendedrepairs[i].getEditor().getText();		/** RECOMMENDED REPAIRS **/
			PMA.priority[i] = (short) priorities[i].getSelectionModel().getSelectedIndex();	/** PRIORITY **/
			PMA.totalParts[i] = moneyFields[i][0].getValue();								/** TOTAL PARTS **/
			PMA.totalLabor[i] = moneyFields[i][2].getValue();								/** TOTAL LABOR **/
			PMA.qty[i] = Integer.parseInt(QTY[i].getText());								/** QUANTITY **/
			PMA.partCost[i] = moneyFields[i][1].getValue();									/** PARTS COST **/
			PMA.laborCost[i] = laborHours[i].getValue();									/** LABOR HOURS **/
			PMA.vendor[i] = vendors[i].getText();											/** VENDORS **/
		}
		
		MyCMS.PMA.updatePMA(WORK_ORDER_NUMBER,PMA);
	}
	
	
	
	
@SuppressWarnings("unchecked")
public void initialize(URL location, ResourceBundle resources) {

	/** put all declared FX id variables in arrays for easier processing later on **/
	
		checkboxes = new CheckBox[][] {{OK1, NOTOK1}, {OK2, NOTOK2}, {OK3, NOTOK3}, {OK4, NOTOK4}, {OK5, NOTOK5},{OK6, NOTOK6},
				{OK7, NOTOK7},{OK8, NOTOK8},{OK9, NOTOK9},{OK10, NOTOK10},{OK11, NOTOK11},{OK12, NOTOK12},{OK13, NOTOK13},{OK14, NOTOK14},
				{OK15, NOTOK15},{OK16, NOTOK16},{OK17, NOTOK17},{OK18, NOTOK18},{OK19, NOTOK19},{OK20, NOTOK20},{OK21, NOTOK21},{OK22, NOTOK22},
				{OK23, NOTOK23},{OK24, NOTOK24},{OK25, NOTOK25},{OK26, NOTOK26},{OK27, NOTOK27},{OK28, NOTOK28},{OK29, NOTOK29},{OK30, NOTOK30},
				{OK31, NOTOK31},{OK32, NOTOK32},{OK33, NOTOK33},{OK34, NOTOK34},{OK35, NOTOK35},{OK36, NOTOK36},{OK37, NOTOK37},{OK38, NOTOK38},
				{OK39, NOTOK39},{OK40, NOTOK40},{OK41, NOTOK41},{OK42, NOTOK42},{OK43, NOTOK43},{OK44, NOTOK44},{OK45, NOTOK45},{OK46, NOTOK46},
				{OK47, NOTOK47},{OK48, NOTOK48},{OK49, NOTOK49},{OK50, NOTOK50}};
		

		
		techcomments = new ComboBox[] {TC1,TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9,TC10,TC11,TC12,TC13,TC14,TC15,TC16,TC17,TC18,TC19,TC20,TC21,TC22,TC23,TC24,TC25
				,TC26,TC27,TC28,TC29,TC30,TC31,TC32,TC33,TC34,TC35,TC36,TC37,TC38,TC39,TC40,TC41,TC42,TC43,TC44,TC45,TC46,TC47,TC48,TC49,TC50};
		
		
		recommendedrepairs = new ComboBox[]{RR1,RR2,RR3,RR4,RR5,RR6,RR7,RR8,RR9,RR10,RR11,RR12,RR13,RR14,RR15,RR16,RR17,RR18,RR19,RR20,RR21,RR22,RR23,RR24,RR25,RR26
				,RR27,RR28,RR29,RR30,RR31,RR32,RR33,RR34,RR35,RR36,RR37,RR38,RR39,RR40,RR41,RR42,RR43,RR44,RR45,RR46,RR47,RR48,RR49,RR50};
		
		
		priorities= new ComboBox[] {PRIOR1,PRIOR2,PRIOR3,PRIOR4,PRIOR5,PRIOR6,PRIOR7,PRIOR8,PRIOR9,PRIOR10,PRIOR11,PRIOR12,PRIOR13,PRIOR14,PRIOR15,PRIOR16,PRIOR17,PRIOR18
				,PRIOR19,PRIOR20,PRIOR21,PRIOR22,PRIOR23,PRIOR24,PRIOR25,PRIOR26,PRIOR27,PRIOR28,PRIOR29,PRIOR30,PRIOR31,PRIOR32,PRIOR33,PRIOR34
				,PRIOR35,PRIOR36,PRIOR37,PRIOR38,PRIOR39,PRIOR40,PRIOR41,PRIOR42,PRIOR43,PRIOR44,PRIOR45,PRIOR46,PRIOR47,PRIOR48,PRIOR49,PRIOR50};
		
		labels = new Label[]{L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17,L18,L19,L20,L21,L22,L23,L24,L25,L26,L27,L28,L29,L30,L31,L32,L33,L34,L35,L36,L37,
				L38,L39,L40,L41,L42};
		
	
		vendors = new TextField[]{vendor1,vendor2,vendor3,vendor4,vendor5,vendor6,vendor7,vendor8,vendor9,vendor10,vendor11
				,vendor12,vendor13,vendor14,vendor15,vendor16,vendor17,vendor18,vendor19,vendor20,vendor21,vendor22,vendor23
				,vendor24,vendor25,vendor26,vendor27,vendor28,vendor29,vendor30,vendor31,vendor32,vendor33,vendor34,vendor35
				,vendor36,vendor37,vendor38,vendor39,vendor40,vendor41,vendor42,vendor43,vendor44,vendor45,vendor46,vendor47
				,vendor48,vendor49,vendor50};
		
		initComoBoxOptions();
		initPMA();
		initMenuItems();
		
		for(int i = 0; i < ROW_COUNT; i++){
			/** initialize manually added fields **/
			moneyFields[i][0] = new MoneyTextField();
			moneyFields[i][0].setDisable(true);
	
			moneyFields[i][1] = new MoneyTextField();
			moneyFields[i][1].setDisable(true);
			
			moneyFields[i][2] = new MoneyTextField();
			moneyFields[i][2].setDisable(true);
			
			laborHours[i] = new DoubleTextField();
			laborHours[i].setDisable(true);
			
			QTY[i] = new IntegerTextField();
			QTY[i].setDisable(true);
			
			/** set FXML pre-oaded fields **/
			vendors[i].setDisable(true);
			recommendedrepairs[i].setDisable(true);
			recommendedrepairs[i].setEditable(true);
			priorities[i].setDisable(true);
			priorities[i].setEditable(true);
			techcomments[i].setEditable(true);
		}	
		
		/**Add all numberFields into their appropriate positions in the grid*/
		for(int i=0, addrows=0,col=7,row=9; i < ROW_COUNT; i++, row++){
			addrows = addRows(i); col=7;
			grid.add(moneyFields[i][0], col++, row+addrows);
			grid.add(moneyFields[i][1], col, row+addrows);
			col+=2;
			grid.add(QTY[i],col++,row+addrows);
			grid.add(moneyFields[i][2], col++, row+addrows);
			grid.add(laborHours[i], col++, row+addrows);
			col +=2;
		}
		
		//System.out.println(MyCMS.PMA.createPMA(5856, "MV54WDV64HJ45", "new pma created"));
		
	}

private void initPMA(){
	PMA = null;
	PMA = MyCMS.PMA.getPMA(WORK_ORDER_NUMBER);
	
	CUST.setText(PMA.first);
	WO.setText(new Integer(WORK_ORDER_NUMBER).toString());
	ENG.setText(PMA.engine);
	MAKE.setText(PMA.make);
	LICNUM.setText(PMA.lic);
	TRANS.setText(PMA.trans);
	TAGS.setText(PMA.tags);
	YEAR.setText(new Integer(PMA.year).toString());
	MODEL.setText(PMA.model);
	VIN.setText(PMA.vin);
	MILES.setText(PMA.miles);
	DATE.setText(PMA.date.toString());
	CustConcerns.setText(PMA.customer_concerns);
	CustConcerns.setEditable(false); 
	
	
	
	
	for(int i = 0; i < ROW_COUNT; i++){
		checkboxes[i][0].setSelected(PMA.ok[i]);
		checkboxes[i][1].setSelected(PMA.notok[i]);
		
		techcomments[i].getEditor().setText((PMA.tech_comments[i]));

		
		switch( PMA.approved[i] ){
			case PMAObject.APPROVED:
				alterRow(i,PMAObject.APPROVED);
				lockRow(i);
				break;
			case PMAObject.NOT_APPROVED:
				alterRow(i,PMAObject.NOT_APPROVED);
				lockRow(i);
				break;
			case PMAObject.INFORMATION_ONLY:
				alterRow(i,PMAObject.INFORMATION_ONLY);
				lockRow(i);
				
				
				
				
		}

	}
	
	
	
}

private void initMenuItems(){
	int i = 0;
	for(Label l : labels){
		ContextMenu contextMenu = new ContextMenu();
		MenuItem approve = new MenuItem("Approve Row", new ImageView(new Image("FXML/approve.png")));
		approve.setDisable(true);
		menuItemsApproved[i] = approve;
		MenuItem disapprove = new MenuItem("Disapprove Row",new ImageView(new Image("FXML/red-x.png")));
		disapprove.setDisable(true);
		menuItemsDisapproved[i] = disapprove;
		MenuItem information = new MenuItem("Set Row as Information Only",new ImageView(new Image("FXML/warning.png")));	
		information.setDisable(true);
		menuItemsInformation[i] = information;
		contextMenu.getItems().addAll(approve,disapprove,information);
		approve.setOnAction(new rightClickMenuApprove());
		disapprove.setOnAction(new rightClickMenuDisapprove());
		information.setOnAction(new rightClickMenuInformation());
		l.setContextMenu(contextMenu);
		i++;
	}
	
}
private class rightClickMenuApprove implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent event) {
		int place = 0;
		MenuItem item  =  (MenuItem)event.getSource();
		
		while( item != menuItemsApproved[place])
			place++;
		
		alterRow(place,PMAObject.APPROVED);
		PMA.approved[place] = PMAObject.APPROVED;
	}
}
private class rightClickMenuDisapprove implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent event) {
		int place = 0;
		MenuItem item  =  (MenuItem)event.getSource();
			
			while( item != menuItemsDisapproved[place])
				place++;
			
			alterRow(place,PMAObject.NOT_APPROVED);
			PMA.approved[place] = PMAObject.NOT_APPROVED;
		}
	}
	private class rightClickMenuInformation implements EventHandler<ActionEvent>{
			@Override
			public void handle(ActionEvent event) {
				int place = 0;
				MenuItem item  =  (MenuItem)event.getSource();
				
				while( item != menuItemsInformation[place])
					place++;
	
				alterRow(place,PMAObject.INFORMATION_ONLY);
				PMA.approved[place] = PMAObject.INFORMATION_ONLY;
			}
	}
	private void alterRow(final int place, final int which){
		String css;
		switch(which){
			case PMAObject.APPROVED:
				css = "green-label";
				break;
			case PMAObject.NOT_APPROVED:
				css = "red-label";
				break;
			case PMAObject.INFORMATION_ONLY:
				css = "yellow-label";
				break;
			default:
				return;
		}
		disableFields(place,true);
		clearCSS(place);
		priorities[place].getStyleClass().add(css);
		priorities[place].getEditor().getStyleClass().add(css);
		moneyFields[place][0].getStyleClass().add(css);
		moneyFields[place][1].getStyleClass().add(css);
	}
	
	
	
	public static void setWorkOrderNumber(int workOrderNumber){
		WORK_ORDER_NUMBER = workOrderNumber;
	}
	
	
	private void initComoBoxOptions(){
		
		/** strings used as combobox options in PMA **/
		
		String[] priority = new String[]{ "", "HIGH", "MED", "LOW" };

		String[] tireTechComments = { "", "TIRE IS WORN OUT", "TIRE IS FLAT",
				"TIRE HAS HOLE IN SIDE WALL", "TIRE HAS IRREGULAR WEAR",
				"TIRE HAS ALIGNMENT WEAR", "1/32 TREAD", "2/32 TREAD",
				"3/32 TREAD", "4/32 TREAD", "5/32 TREAD", "6/32 TREAD",
				"7/32 TREAD", "8/32 TREAD", "9/32 TREAD", "10/32 TREAD",
				"11/32 TREAD", "12/32 TREAD", "13/32 TREAD", "14/32 TREAD",
				"15/32 TREAD" };
		
		String[] tireRecommendedComments = { "", "REPLACE TIRE", "REPAIR TIRE","ROTATE TIRE" };
		
		String[] WWTechComments = { "", "WINDSHIELD IS CRACKED",
				"WINDSHIELD HAS CHIP IN IT", "WINDSHIELD IS SCRATCHED",
				"DRIVERS SIDE FRONT WINDOW NOT GOING UP",
				"PASSENGER SIDE FRONT WINDOW NOT GOING UP",
				"DRIVERS SIDE REAR WINDOW WILL NOT GO UP",
				"PASSENGER SIDE REAR WINDOW NOT GOING UP", };
		String[] WWRecommendedComments = { "", "REPLACE WINDSHIELD",
				"INFORMATION ONLY", "DO DIAGNOSTIC ON WINDOW" };

		
		String[] locksTechComments = { "",
				"DRIVERS SIDE FRONT LOCK NOT WORKING",
				"PASSENGER SIDE FRONT LOCK NOT WORKING",
				"DRIVERS SIDE REAR LOCK NOT WORKING",
				"PASSENGER SIDE REAR LOCK NOT WORKING",
				"POWER LOCKS NOT WORKING" };
		
		String[] locksRecommendedComments = { "", "DO DIAGNOSTICS ON LOCKS" };
		
		
		String[] ACHTechComments = { "", "NEEDS DIAGNOSTIC ON A/C SYSTEM",
				"LOW ON FREON", "HAS LEAK IN A/C SYSTEM",
				"NOT COOLING VERY WELL", "A/C SWITCH IS BROKEN" };
		
		String[] ACHRecommendedComments = { "", "DO A/C SERVICE/DIAGNOSTIC" };
		
		String[] heaterTechComments = { "", "NEEDS DIAGNOSTIC ON HEATER",
				"LOW ON COOLANT", "NEEDS HEATER CONTROL VALVE",
				"HEATER CORE LEAKING" };
		String[] heaterRecommendedComments = { "",
				"DO DIAGNOSTIC ON HEATER SYSTEM", "DO COOLANT PRESSURE TEST",
				"REPLACE HEATER CONTROL VALVE" };
		
		String[] wiperTechComments = { "", "WIPERS NOT WORKING",
				"WIPERS STREAKING", "SQUIRTERS NOT WORKING" };
		
		String[] wiperRecommendedComments = { "",
				"DO DIAGNOSTIC ON WIPER SYSTEM", "REPLACE WIPERS",
				"DO DIAGNOSTIC ON SQUIRTERS" };
		
		String[] hornTechComments = { "", "", "" };
		
		String[] hornRecommendedComments = { "", "DO DIAGNOSTIC ON HORN" };
		
		String[] HLTechComments = { "", "DRIVER SIDE HIGH BEAM IS OUT",
				"DRIVER SIDE LOW BEAM IS OUT",
				"PASSENGER SIDE HIGH BEAM IS OUT",
				"PASSENGER SIDE LOW BEAM IS OUT",
				"DRIVER SIDE HEALIGHT HAS CRACK IN IT",
				"PASSENGER SIDE HEADLIGHT HAS CRACK IN IT",
				"HEAD LIGHTS DO NOT WORK" };
		
		String[] HLRecommendedComments = { "", "REPLACE LIGHT(S)",
				"DO DIAGNOSTICS ON HEAD LIGHTS" };
		
		String[] PTLTechComments = { "", "DRIVER SIDE REAR TAIL LIGHT IS OUT",
				"PASSENGER SIDE REAR TAIL LIGHT IS OUT",
				"PASSENGER SIDE TAIL LIGHT IS CRACKED",
				"DRIVER SIDE TAIL LIGHT IS CRACKED",
				"PARK/TAIL LAMP DO NOT WORK", "LIC PLATE LIGHT IS OUT" };
		
		String[] PTLRecommendedComments = { "", "REPLACE LIGHT(S)",
				"DO DIAGNOSTIC ON PARK/TAIL LAMP" };
		
		String[] brakeTechComments = { "", "DRIVER SIDE BRAKE LIGHT IS OUT",
				"PASSENGER SIDE BRAKE LIGHT IS OUT",
				"PASSENGER SIDE BRAKE LIGHT IS CRACKED",
				"DRIVER SIDE BRAKE LIGHT IS CRACKED",
				"BRAKE LIGHTS DO NOT WORK" };
		
		String[] brakeRecommendedComments = { "", "REPLACE LIGHT(S)",
				"DO DIAGNOSTICS ON BRAKE LIGHTS" };
		
		String[] DSTechComments = { "", "PASSENGER SIDE FENDER IS DENTED",
				"DRIVER SIDE FENDER IS DENTED",
				"DRIVER SIDE FRONT DOOR IS DENTED",
				"DRIVER SIDE REAR DOOR IS DENTED",
				"PASSENGER FRONT DOOR IS DENTED",
				"PASSENGER SIDE REAR DOOR IS DENTED",
				"DRIVER SIDE REAR QUARTER IS DENTED",
				"PASSENGER SIDE REAR QUARTER IS DENTED", "TRUNK LID IS DENTED",
				"HOOD IS DENTED", "TAILGATE IS DENTED", "DENTS/SCRATCHES" };
		
		String[] DSRecommendedComments = { "", "DO ESTIMATE ON BODYWORK",
				"INFORMATION ONLY" };
		
		String[] engineDiagTechComments = { "", "CHECK ENGINE LIGHT IS ON",
				"VEHICLE RUNS ROUGH", "NEED TO DO DIAGNOSTICS" };
		
		String[] engineDiagRecommendedComments = { "", "DO DIAGNOSTICS",
				"INFORMATION ONLY" };
		
		String[] MMTechComments = { "", "DRIVER SIDE MOTOR MOUNT IS BROKEN",
				"PASSENGER SIDE MOTOR MOUNT IS BROKEN",
				"BOTH MOTOR MOUNTS ARE BROKEN", "MOTOR MOUNTS ARE WEAK",
				"NEED TO CHECK MOTOR MOUNTS" };
		
		String[] MMRecommendedComments = { "",
				"REPLACE DRIVER SIDE MOTOR MOUNT",
				"REPLACE PASSENGER SIDE MOTOR MOUNT",
				"REPLACE BOTH MOTOR MOUNTS" };
		
		String[] BCBTechComments = { "", "BATTERY TERMINALS DIRTY",
				"SEAL BATTERY TERMINALS", "BATTERY TERMINALS CORRODED",
				"BATTERY CABLES BROKEN", "BATTERY CABLES CORRODED",
				"NEEDS BATTERY" };
		
		String[] BCBRecommendedComments = { "",
				"CLEAN AND SEAL BATTERY TERMINALS", "REPLACE(1) BATTERY END",
				"REPLACE(2) BATTERY ENDS", "REPLACE POSITIVE BATTERY CABLE",
				"REPLACE NEGATIVE BATTERY CABLE",
				"REPLACE BOTH BATTERY CABLES", "REPLACE BATTERY" };

		String[] EOTechComments = { "", "ENGINE OIL IS LOW",
				"ENGINE OIL IS DIRTY", "ENGINE OIL IS LOW AND DIRTY",
				"ENGINE HAS OIL LEAK" };
		
		String[] EORecommendedComments = { "", "DO OIL CHANGE", "ADD OIL",
				"DO DYE TEST TO CHECK FOR OIL LEAK" };

		String[] TFTechComments = { "", "TRANSMISSION FLUID IS LOW",
				"TRANSMISSION FLUID IS DIRTY",
				"TRANSMISSION FLUID IS LOW AND DIRTY",
				"TRANSMISSION HAS OIL LEAK" };
		
		String[] TFRecommendedComments = { "", "DO TRANSMISSION SERVICE",
				"ADD TRANSMISSION FLUID", "DO DYE TEST TO CHECK FOR FLUID LEAK" };

		String[] WFTechComments = { "", "WASHER FLUID IS LOW",
				"SQUIRTERS NOT WORKING", "WASHER FLUID BOTTLE IS CRACKED" };
		
		String[] WFRecommendedComments = { "", "ADD WASHER FLUID",
				"DO DIAGNOSTICS ON SQUIRTERS",
				"REPLACE WINDSHIELD WASHER BOTTLE" };
		
		String[] BFTechComments = { "", "BRAKE FLUID IS LOW",
				"BRAKE FLUID IS DIRTY", "BRAKE FLUID IS LOW AND DIRTY" };
		
		String[] BFRecommendedComments = { "", "DO BRAKE FLUSH",
				"ADD BRAKE FLUID" };
		
		String[] PSFTechComments = { "", "POWER STEERING FLUID IS LOW",
				"POWER STEERING FLUID IS DIRTY",
				"POWER STEERING FLUID IS LOW AND DIRTY" };
		
		String[] PSFRecommendedComments = { "", "DO A POWER STEERING FLUSH",
				"ADD POWER STEERING FLUID" };
		

		String[] coolantTechComments = { "", "COOLANT IS LOW",
				"COOLANT IS DIRTY", "COOLANT IS LOW AND DIRTY",
				"NEEDS COOLANT FLUSH WITH REPAIRS",
				"HAS COOLANT LEAK DO PRESSURE TEST" };
		
		String[] coolantRecommendedComments = { "", "DO COOLANT FLUSH",
				"ADD COOLANT", "DO PRESSURE TEST TO CHECK FOR LEAKS" };
		
		String[] SBTechComments = { "", "SERPENTINE BELT IS CRACKED",
				"SERPENTINE BELT IS MISSING", "BELT TENSIONER IS WORN",
				"BELT TENSIONER AND BELT ARE WORN" };
		
		String[] SBRecommendedComments = { "", "REPLACE BELT", "ADJUST BELT",
				"REPLACE BELT TENSIONER", "REPLACE BELT TENSIONER AND BELT" };
		
		String[] AFTechComments = { "", "AIR FILTER IS DIRTY",
					"K&N TYPE FILTER NEEDS TO BE CLEANED" };
		
		String[] AFRecommendedComments = { "", "REPLACE AIR FILTER",
					"CLEAN AND RE OIL AIR FILTER" };
		
		String[] FFTechComments = { "", "REPLACE FILTER DUE TO MILEAGE",
				"REPLACE FILTER MANUFACTURER RECOMMEND", "FUE FILTER CLOGGED",
				"NEED TO REMOVE FUEL FILTER TO CHECK IT" };
		
		String[] FFRecommendedComments = { "", "REPLACE FUEL FILTER",
				"REMOVE FILTER AND CHECK FOR CLOGS" };
		
		String[] radiatorTechComments = { "", "RADIATOR IS LEAKING",
				"RADIATOR IS CLOGGED", "OVERHEATING NEED TO CHECK SYSTEM" };
		
		String[] radiatorRecommendedComments = { "",
				"DO PRESSURE CHECK TO CHECK LEAKS", "REPLACE RADIATOR",
				"DO DIAGNOSTICS ON OVERHEATING" };
		
		String[] URHTechComments = { "", "UPPER HODE IS LEAKING",
				"UPPER HOSE FEELS BRITTLE", "NEEDS HOSE CLAMPS REPLACED" };
		
		String[] URHRecommendedComments = { "", "REPLACE UPPER RADIATOR HOSE" };

		String[] LRHTechComments = { "", "LOWER HOSE IS LEAKING",
				"LOWER HOSE FEELS BRITTLE", "NEEDS HOSE CLAMPS REPLACED" };
		
		String[] LRHRecommendedComments = { "", "REPLACE LOWER RADIATOR HOSE" };

		String[] HBHTechComments = { "", "HEATER IS LEAKING",
				"HEATER HOSE FEELS BRITTLE", "NEEDS HOSE CLAMPS REPLACED" };
		
		String[] HBHRecommendedComments = { "", "REPLACE HEATER HOSES" };
		
		String[] SRPTechComments = { "", "STEERING BOX/GEAR IS LEAKING",
				"STEERING BOX/GEAR IS LOOSE", "RACK & PINION IS LEAKING",
				"RACK AND PINION IS LOOSE", "RACK AND PINION MOUNTS ARE WORN",
				"RACK AND PINION MOUNTS ARE BROKEN" };
		
		String[] SRPRecommendedComments = { "", "REPLACE STEERING BOX/GEAR",
				"REPLACE RACK AND PINION", "REPLACE RACK & PINION MOUNTS" };
		
		String[] SLTechComments = { "", "LEFT OUTER TIE ROD WORN",
				"RIGHT OUTER TIE ROD WORN", "LEFT INNER TIE ROD WORN",
				"RIGHT INNER TIE ROD WORN", "BOTH INNER TIE RODS WORN",
				"BOTH OUTER TIE RODS WORN", "BOTH INNER & OUTER TIE RODS WORN",
				"PITMAN ARM WORN", "IDLER ARM WORN", "DRAG LINK WORN",
				"DRAG LINK, LEFT & RIGHT TIE ROD ENDS WORN",
				"PITMAN ARM AND IDLER ARM WORN",
				"PITMAN ARM IDLER ARM & DRAG LINK WORN",
				"STEERING STABILIZER WORN" };
		
		String[] SLRecommendedComments = { "", "REPLACE LEFT OUTER TIE ROD",
				"REPLACE RIGHT OUTER TIE ROD", "REPLACE LEFT INNER TIE ROD",
				"REPLACE RIGHT INNER TIE ROD", "REPLACE BOTH INNER TIE RODS",
				"REPLACE BOTH OUTER TIE RODS",
				"REPLACE BOTH INNER & OUTER TIE RODS", "REPLACE PITMAN ARM",
				"REPLACE IDLER ARM", "REPLACE DRAG LINK",
				"REPLACE DRAG LINK, LEFT&RIGHT TIE ROD ENDS",
				"REPLACE PITMAN ARM AND IDLER ARM",
				"REPLACE PITMAN ARM, IDLER ARM & DRAG LINK",
				"REPLACE STEERING STABILIZER" };
		
		String[] suspTechComments = { "", "LEFT UPPER BALL JOINT WORN",
				"LEFT LOWER BALL JOINT WORN", "RIGHT UPPER BALL JOINT WORN",
				"RIGHT LOWER BALL JOINT WORN",
				"BOTH LEFT UPPER & LOWER BALL JOINTS WORN",
				"BOTH RIGHT UPPER & LOWER BALL JOINTS WORN",
				"ALL BALL JOINTS WORN",
				"RIGHT SIDE LOWER A FRAME BUSHINGS WORN",
				"RIGHT SIDE UPPER A FRAME BUSHINGS WORN",
				"LEFT SIDE LOWER A FRAME BUSHINGS WORN",
				"LEFT SIDE UPPER A FRAME BUSHINGS WORN",
				"ALL A FRAME BUSHINGS WORN", "LEFT SIDE KING PIN(S) WORN",
				"RIGHT SIDE KING PIN(S) WORN", "BOTH KING PINS WORN",
				"LEFT SIDE UPPER STRUT MOUNT WORN",
				"RIGHT SIDE UPPER STRUT MOUNT WORN" };
		
		String[] suspRecommendedComments = { "",
				"REPLACE LEFT UPPER BALL JOINT",
				"REPLACE LEFT LOWER BALL JOINT",
				"REPLACE RIGHT UPPER BALL JOINT",
				"REPLACE RIGHT LOWER BALL JOINT",
				"REPLACE LEFT UPPER & LOWER BALL JOINTS",
				"REPLACE RIGHT UPPER & LOWER BALL JOINTS",
				"REPLACE ALL BALL JOINTS",
				"REPLACE RIGHT SIDE LOWER A FRAME BUSHINGS",
				"REPLACE RIGHT SIDE UPPER A FRAME BUSHINGS",
				"REPLACE LEFT SIDE LOWER A FRAME BUSHINGS",
				"REPLACE LEFT SIDE UPPER A FRAME BUSHINGS",
				"REPLACE ALL A FRAME BUSHINGS",
				"REPLACE LEFT SIDE KING PIN(S)",
				"REPLACE RIGHT SIDE KING PIN(S)", "REPLACE BOTH KING PINS",
				"REPLACE LEFT SIDE UPPER STRUT MOUNT",
				"REPLACE RIGHT SIDE UPPER STRUT MOUNT" };
		
		String[] alignTechComments = { "", "NEEDS ALIGNMENT CAUSING TIRE WEAR",
				"NEEDS ALIGNMENT PULLS LEFT", "NEEDS ALIGNMENT PULLS RIGHT",
				"NEEDS ALIGNMENT AFTER FRONT END WORK" };
		
		String[] alignRecommendedComments = { "", "DO ALIGNMENT" };
		
		String[] FSSTechComments = { "", "FRONT SHOCKS WORN OUT",
				"REAR SHOCKS WORN OUT", "ALL (4) SHOCKS WORN OUT",
				"FRONT SHOCKS LEAKING", "REAR SHOCKS LEAKING",
				"ALL (4) SHOCKS LEAKING", "FRONT SHOCKS CAUSING TIRE WEAR",
				"REAR SHOCKS CAUSING TIRE WEAR",
				"ALL (4) SHOCKS CAUSING TIRE WEAR", "FRONT STRUTS WORN OUT",
				"REAR STRUT WORN OUT", "ALL (4) STRUTS WORN OUT",
				"FRONT STRUTS LEAKING", "REAR STRUTS LEAKING",
				"ALL (4) STRUTS LEAKING", "FRONT STRUTS CAUSING TIRE WEAR",
				"REAR STUTS CAUSING TIRE WEAR",
				"ALL (4) STRUTS CAUSING TIRE WEAR" };
		
		String[] FSSRecommendedComments = { "", "REPLACE FRONT SHOCKS",
				"REPLACE REAR SHOCKS", "REPLACE ALL (4) SHOCKS",
				"REPLACE FRONT STRUTS", "REPLACE REAR STRUTS",
				"REPLACE ALL (4) STRUTS" };
		
		String[] RSSTechComments = { "", "FRONT SHOCKS WORN OUT",
				"REAR SHOCKS WORN OUT", "ALL (4) SHOCKS WORN OUT",
				"FRONT SHOCKS LEAKING", "REAR SHOCKS LEAKING",
				"ALL (4) SHOCKS LEAKING", "FRONT SHOCKS CAUSING TIRE WEAR",
				"REAR SHOCKS CAUSING TIRE WEAR",
				"ALL (4) SHOCKS CAUSING TIRE WEAR", "FRONT STRUTS WORN OUT",
				"REAR STRUT WORN OUT", "ALL (4) STRUTS WORN OUT",
				"FRONT STRUTS LEAKING", "REAR STRUTS LEAKING",
				"ALL (4) STRUTS LEAKING", "FRONT STRUTS CAUSING TIRE WEAR",
				"REAR STRUTS CAUSING TIRE WEAR",
				"ALL (4) STRUTS CAUSING TIRE WEAR" };
		
		String[] RSSRecommendedComments = { "", "REPLACE FRONT SHOCKS",
				"REPLACE REAR SHOCKS", "REPLACE ALL (4) SHOCKS",
				"REPLACE FRONT STRUTS", "REPLACE REAR STRUTS",
				"REPLACE ALL (4) STRUTS" };
		
		String[] FBTechComments = { "",
				"FRONT BRAKES WORN NEED TO BE REPLACED",
				"NEEDS FRONT BRAKES AND BRAKE ROTORS",
				"NEEDS FRONT BRAKES AND BRAKE CALIPERS",
				"NEEDS FRONT BRAKES, ROTORS & CALIPERS" };
		
		String[] FBRecommendedComments = { "", "REPLACE FRONT BRAKES",
				"REPLACE FRONT BRAKES AND BRAKE ROTORS",
				"REPLACE FRONT BRAKES AND BRAKE CALIPERS",
				"REPLACE FRONT BRAKES, ROTORS & CALIPERS" };

		String[] RBTechComments = { "", "CLEAN AND ADJUST BRAKES",
				"REAR BRAKES WORN NEED TO BE REPLACED",
				"NEEDS REAR BRAKES AND BRAKE ROTOR",
				"NEEDS REAR BRAKES AND BRAKE DRUMS",
				"NEEDS REAR BRAKES AND BRAKE CALIPERS",
				"NEEDS REAR BRAKES AND WHEEL CYLINDERS",
				"NEEDS REAR BRAKES,BRAKE ROTOR&CALIPERS",
				"NEEDS REAR BRAKES,BRAKE DRUMS&CYLINDERS" };
		
		String[] RBRecommendedComments = { "", "CLEAN AND ADJUST BRAKES",
				"REPLACE REAR BRAKES", "REPLACE REAR BRAKES AMD BRAKE ROTOR",
				"REPLACE REAR BRAKES AND BRAKE DRUMS",
				"REPLACE REAR BRAKES AND BRAKE CALIPERS",
				"REPLACE REAR BRAKES AND WHEEL CYLINDERS",
				"REPLACE REAR BRAKES,ROTORS & CALIPERS",
				"REPLACE REAR BRAKES,DRUMS & CYLINDERS" };
		
		String[] CVADTechComments = { "", "FRONT U JOINT WORN",
				"REAR U JOINT WORN", "FRONT DRIVE SHAFT 4X4 U JOINT WORN",
				"REAR DRIVE SHAFT 4X4 U JOINT WORN" };
		
		String[] CVADRecommendedComments = { "", "REPLACE FRONT U JOINT",
				"REPLACE REAR U JOINT",
				"REPLACE FRONT DRIVE SHAFT 4X4 U JOINT",
				"REPLACE REAR DRIVE SHAFT 4X4 U JOINT" };
		
		String[] mufflerTechComments = { "", "MUFFLER LEAKING EXHAUST",
				"MUFFLER HAS HOLES IN IT", "MUFFLER IS RUSTED",
				"MUFFLER IS MISSING" };
		
		String[] mufflerRecommendedComments = { "", "REPLACE MUFFLER" };
		
		String[] EPTechComments = { "", "EXAUST PIPES ARE LEAKING EXHAUST",
				"EXHAUST PIPES HOLES IN THEM", "EXHAUST PIPES ARE RUSTED",
				"EXHAUST PIPES ARE MISSING" };
		
		String[] EPRecommendedComments = { "", "REPLACE EXHAUST PIPES" };

		/** make array of all combobox options that will displayed on the same row, ie(tech comment options,recommended repairs options and priority on */
		String[][][] comments = new String[][][]{
				new String[][]{tireTechComments,tireRecommendedComments,priority},
				new String[][]{tireTechComments,tireRecommendedComments,priority},
				new String[][]{tireTechComments,tireRecommendedComments,priority},
				new String[][]{tireTechComments,tireRecommendedComments,priority},
				new String[][]{tireTechComments,tireRecommendedComments,priority},
				new String[][]{WWTechComments,WWRecommendedComments,priority},
				new String[][]{locksTechComments,locksRecommendedComments,priority},
				new String[][]{ACHTechComments,ACHRecommendedComments,priority},
				new String[][]{heaterTechComments,heaterRecommendedComments,priority},
				new String[][]{wiperTechComments,wiperRecommendedComments,priority},
				new String[][]{hornTechComments,hornRecommendedComments,priority},
				new String[][]{HLTechComments,HLRecommendedComments,priority},
				new String[][]{PTLTechComments,PTLRecommendedComments,priority},
				new String[][]{brakeTechComments,brakeRecommendedComments,priority},
				new String[][]{DSTechComments,DSRecommendedComments,priority},
				new String[][]{engineDiagTechComments,engineDiagRecommendedComments,priority},
				new String[][]{MMTechComments,MMRecommendedComments,priority},
				new String[][]{BCBTechComments,BCBRecommendedComments,priority},
				new String[][]{EOTechComments,EORecommendedComments,priority},
				new String[][]{TFTechComments,TFRecommendedComments,priority},
				new String[][]{WFTechComments,WFRecommendedComments,priority},
				new String[][]{BFTechComments,BFRecommendedComments,priority},
				new String[][]{PSFTechComments,PSFRecommendedComments,priority},
				new String[][]{coolantTechComments,coolantRecommendedComments,priority},
				new String[][]{SBTechComments,SBRecommendedComments,priority},
				new String[][]{AFTechComments,AFRecommendedComments,priority},
				new String[][]{FFTechComments,FFRecommendedComments,priority},
				new String[][]{radiatorTechComments,radiatorRecommendedComments,priority},
				new String[][]{URHTechComments,URHRecommendedComments,priority},
				new String[][]{LRHTechComments,LRHRecommendedComments,priority},
				new String[][]{HBHTechComments,HBHRecommendedComments,priority},
				new String[][]{SRPTechComments,SRPRecommendedComments,priority},
				new String[][]{SLTechComments,SLRecommendedComments,priority},
				new String[][]{suspTechComments,suspRecommendedComments,priority},
				new String[][]{alignTechComments,alignRecommendedComments,priority},
				new String[][]{FSSTechComments,FSSRecommendedComments,priority},
				new String[][]{RSSTechComments,RSSRecommendedComments,priority},
				new String[][]{FBTechComments,FBRecommendedComments,priority},
				new String[][]{RBTechComments,RBRecommendedComments,priority},
				new String[][]{CVADTechComments,CVADRecommendedComments,priority},
				new String[][]{mufflerTechComments,mufflerRecommendedComments,priority},
				new String[][]{EPTechComments,EPRecommendedComments,priority}
				};
		
		/** comboboxes use observable lists, so make an arraylist of observable list to loop through and set items for each combobox **/
		ArrayList<ObservableList<String>> observableLists = new ArrayList<ObservableList<String>>();
		
		ArrayList<String> tmp;
		ObservableList<String> olist;
		for(int i = 0; i < 42;i++){
			for(int j = 0; j < comments[i].length;j++){
				tmp = new ArrayList<String>();
				for(int k = 0; k < comments[i][j].length ;k++)
					tmp.add(comments[i][j][k]);
				olist = FXCollections.observableList(tmp);
				observableLists.add(olist);
			}
		}
		/** finally set items for all comboboxes **/
		int index=0;
		for(int i = 0; i < 42; i++){
			techcomments[i].setItems(observableLists.get(index));
			recommendedrepairs[i].setItems(observableLists.get(index+1));
			priorities[i].setItems(observableLists.get(index+2));
			index+=3;
		}
		
		
	}
}//class