package FXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import Custom.NumberTextField;
import Custom.ABMTextField;;

public class MyController implements Initializable{
	
	private static int ROW_COUNT = 49;

	
	
	/** title fields **/
	@FXML private Label CUST, DATE, TAGS, YEAR, MAKE, MODEL, WO, LICNUM, VIN, ENG, TRANS, MILES;
	
	/**OK CHECK BOXES **/
	@FXML private CheckBox OK1, OK2, OK3, OK4, OK5, OK6, OK7, OK8, OK9, OK10, OK11, OK12, OK13,OK14,OK15, OK16, OK17, OK18, OK19, OK20, OK21, OK22
	, OK23, OK24, OK25, OK26, OK27, OK28, OK29, OK30, OK31, OK32, OK33, OK34, OK35, OK36, OK37, OK38, OK39, OK40, OK41, OK42, OK43, OK44, OK45, OK46
	, OK47, OK48, OK49;
	
	/** NOT OK CHECK BOXES **/
	@FXML private CheckBox NOTOK1, NOTOK2, NOTOK3, NOTOK4, NOTOK5, NOTOK6, NOTOK7, NOTOK8, NOTOK9, NOTOK10, NOTOK11, NOTOK12, NOTOK13, NOTOK14, NOTOK15
		,NOTOK16, NOTOK17, NOTOK18, NOTOK19, NOTOK20, NOTOK21, NOTOK22, NOTOK23, NOTOK24, NOTOK25, NOTOK26, NOTOK27, NOTOK28, NOTOK29, NOTOK30
		,NOTOK31, NOTOK32, NOTOK33,NOTOK34, NOTOK35, NOTOK36, NOTOK37, NOTOK38, NOTOK39, NOTOK40, NOTOK41, NOTOK42, NOTOK43, NOTOK44, NOTOK45 
		,NOTOK46, NOTOK47, NOTOK48, NOTOK49;
	
	/** TECHNICIANS' COMMENTS **/
	@FXML private  ComboBox TC1,TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9,TC10,TC11,TC12,TC13,TC14,TC15,TC16,TC17,TC18,TC19,TC20,TC21,TC22,TC23,TC24,TC25
					,TC26,TC27,TC28,TC29,TC30,TC31,TC32,TC33,TC34,TC35,TC36,TC37,TC38,TC39,TC40,TC41,TC42,TC43,TC44,TC45,TC46,TC47,TC48,TC49;
	
	/** RECOMMENDED REPAIRS  **/
	@FXML private ComboBox RR1,RR2,RR3,RR4,RR5,RR6,RR7,RR8,RR9,RR10,RR11,RR12,RR13,RR14,RR15,RR16,RR17,RR18,RR19,RR20,RR21,RR22,RR23,RR24,RR25,RR26
					,RR27,RR28,RR29,RR30,RR31,RR32,RR33,RR34,RR35,RR36,RR37,RR38,RR39,RR40,RR41,RR42,RR43,RR44,RR45,RR46,RR47,RR48,RR49;
	
	/** PRIORITY **/
	@FXML private ComboBox PRIOR1,PRIOR2,PRIOR3,PRIOR4,PRIOR5,PRIOR6,PRIOR7,PRIOR8,PRIOR9,PRIOR10,PRIOR11,PRIOR12,PRIOR13,PRIOR14,PRIOR15,PRIOR16,PRIOR17,PRIOR18
					,PRIOR19,PRIOR20,PRIOR21,PRIOR22,PRIOR23,PRIOR24,PRIOR25,PRIOR26,PRIOR27,PRIOR28,PRIOR29,PRIOR30,PRIOR31,PRIOR32,PRIOR33,PRIOR34
					,PRIOR35,PRIOR36,PRIOR37,PRIOR38,PRIOR39,PRIOR40,PRIOR41,PRIOR42,PRIOR43,PRIOR44,PRIOR45,PRIOR46,PRIOR47,PRIOR48,PRIOR49;
	
		
	private CheckBox[][] checkboxes;
	
	private ComboBox<String>[] techcomments;
	
	private ComboBox<String>[] recommendedrepairs;
	
	private ComboBox<String>[] priority;
	
	
	@FXML GridPane grid;
	
	public void checked(ActionEvent event){		
		int place = 0, j=1;
		CheckBox box;
		
		box = (CheckBox) event.getSource();

		// Get the box that was checked!
		//place is what line it is located with respect to corresponding values
		while (place < checkboxes.length){
			if( box == checkboxes[place][0]){
				j=0;
				break;
			}else if( box == checkboxes[place][1]){
				j=1;
				break;
			}
			place++;
		}
		
		//set all fields to false, default colors appropriately, and set default values to 0.0*********************
		checkboxes[place][0].getStyleClass().remove("check-box-regular");
		checkboxes[place][1].getStyleClass().remove("check-box-regular");
		checkboxes[place][0].getStyleClass().remove("check-box-valid");
		checkboxes[place][1].getStyleClass().remove("check-box-invalid");
		checkboxes[place][0].getStyleClass().add("check-box-regular");
		checkboxes[place][1].getStyleClass().add("check-box-regular");
		if (j==0){
			if(checkboxes[place][j].isSelected()){
				//If the OK checkbox is checked then we dont need to open the corresponding fields
				//checkboxes[place][0].getStyleClass().remove("check-box-regular");
				//checkboxes[place][0].getStyleClass().add("check-box-valid");
			}
			//set NotOK checkbox to be disabled and default color
			checkboxes[place][1].setSelected(false);
			//checkboxes[place][1].getStyleClass().add("check-box-regular");
		} else if (j==1){
			if(checkboxes[place][j].isSelected()){
				//If the NOTOK checkbox is checked then we DO need to open the fields to say whats wrong and give prices
				checkboxes[place][1].getStyleClass().remove("check-box-regular");
				checkboxes[place][1].getStyleClass().add("check-box-invalid");
			}
			//set OK checkbox to be disabled and default color
			checkboxes[place][0].setSelected(false);
			//checkboxes[place][0].getStyleClass().add("check-box-regular");
		}
		
	}

	public void excel() throws IOException {
		Row row;
		Cell cell;
		
		File excel = new File("PMA.xls"); /**open file to read from master PMA **/
		FileInputStream fis = new FileInputStream(excel);	  /**create fileInputStream from master PMA file **/
		
		HSSFWorkbook wb = new HSSFWorkbook(fis); 			/**create new workbook based on master PMA **/	
		Sheet ws = wb.getSheet("Sheet1");					/**get the main sheet **/

		
		/** row = 2 **/
		row = ws.getRow(2);
		//CUSTOMER NAME
		cell = row.getCell(0);
		cell.setCellValue(CUST.getText());
		//YEAR 
		cell = row.getCell(3);
		cell.setCellValue(YEAR.getText());
		//WORK ORDER NUMBER
		cell = row.getCell(4);
		cell.setCellValue(WO.getText());
		//ENGINE
		cell = row.getCell(7);
		cell.setCellValue(ENG.getText());
		/** row = 3 */
		row = ws.getRow(3);
		//DATE
		cell = row.getCell(0);
		cell.setCellValue(DATE.getText());
		//MAKE
		cell = row.getCell(3);
		cell.setCellValue(MAKE.getText());
		//LICNUM
		cell = row.getCell(4);
		cell.setCellValue(LICNUM.getText());
		//TRANS
		cell = row.getCell(7);
		cell.setCellValue(TRANS.getText());
		/** row = 4 **/
		row = ws.getRow(4);
		//TAGS
		cell = row.getCell(0);
		cell.setCellValue(TAGS.getText());
		//MODEL
		cell = row.getCell(3);
		cell.setCellValue(MODEL.getText());
		//VIN
		cell = row.getCell(4);
		cell.setCellValue(VIN.getText());
		//MILES
		cell = row.getCell(7);
		cell.setCellValue(MILES.getText());
		
		
		/** create style for red x **/
		HSSFFont redFont = wb.createFont();
		redFont.setColor(HSSFColor.RED.index);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(redFont);		
		
		
	
		int rowIndex = 7;
		for(int i = 0; i < 42; i++,rowIndex++){ //42 rpws
			row = ws.getRow(rowIndex); 
			
			//OK
			cell = row.getCell(1);
			if(checkboxes[i][0].isSelected())
				cell.setCellValue("x");
			//NOT OK
			cell = row.getCell(2); 
			if(checkboxes[i][1].isSelected()){
				cell.setCellStyle(style);
				HSSFRichTextString richString = new HSSFRichTextString("  x");
				richString.applyFont(redFont);
				cell.setCellValue(richString);
			}
			//TECH COMMENTS 
			cell = row.getCell(3);
			cell.setCellValue(techcomments[i].getEditor().getText());
			
			
			
			
		}
		
		
		
		
		
		
		
	/**	
		
		cell.setCellStyle(style);
		HSSFRichTextString richString = new HSSFRichTextString("  x");
		richString.applyFont(redFont);
		cell.setCellValue(richString);
		**/
		
		fis.close();
		
		FileOutputStream fileOut = null;
		fileOut = new FileOutputStream("/home/oscar/Desktop/workbook.xls");
		wb.write(fileOut);
		fileOut.close();
		
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	
	public void initialize(URL location, ResourceBundle resources) {
		
		
		checkboxes = new CheckBox[][] {{OK1, NOTOK1}, {OK2, NOTOK2}, {OK3, NOTOK3}, {OK4, NOTOK4}, {OK5, NOTOK5},{OK6, NOTOK6},
				{OK7, NOTOK7},{OK8, NOTOK8},{OK9, NOTOK9},{OK10, NOTOK10},{OK11, NOTOK11},{OK12, NOTOK12},{OK13, NOTOK13},{OK14, NOTOK14},
				{OK15, NOTOK15},{OK16, NOTOK16},{OK17, NOTOK17},{OK18, NOTOK18},{OK19, NOTOK19},{OK20, NOTOK20},{OK21, NOTOK21},{OK22, NOTOK22},
				{OK23, NOTOK23},{OK24, NOTOK24},{OK25, NOTOK25},{OK26, NOTOK26},{OK27, NOTOK27},{OK28, NOTOK28},{OK29, NOTOK29},{OK30, NOTOK30},
				{OK31, NOTOK31},{OK32, NOTOK32},{OK33, NOTOK33},{OK34, NOTOK34},{OK35, NOTOK35},{OK36, NOTOK36},{OK37, NOTOK37},{OK38, NOTOK38},
				{OK39, NOTOK39},{OK40, NOTOK40},{OK41, NOTOK41},{OK42, NOTOK42},{OK43, NOTOK43},{OK44, NOTOK44},{OK45, NOTOK45},{OK46, NOTOK46},
				{OK47, NOTOK47},{OK48, NOTOK48},{OK49, NOTOK49}};
		
		
		techcomments = new ComboBox[] {TC1,TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9,TC10,TC11,TC12,TC13,TC14,TC15,TC16,TC17,TC18,TC19,TC20,TC21,TC22,TC23,TC24,TC25
				,TC26,TC27,TC28,TC29,TC30,TC31,TC32,TC33,TC34,TC35,TC36,TC37,TC38,TC39,TC40,TC41,TC42,TC43,TC44,TC45,TC46,TC47,TC48,TC49};
		
		
		recommendedrepairs = new ComboBox[]{RR1,RR2,RR3,RR4,RR5,RR6,RR7,RR8,RR9,RR10,RR11,RR12,RR13,RR14,RR15,RR16,RR17,RR18,RR19,RR20,RR21,RR22,RR23,RR24,RR25,RR26
				,RR27,RR28,RR29,RR30,RR31,RR32,RR33,RR34,RR35,RR36,RR37,RR38,RR39,RR40,RR41,RR42,RR43,RR44,RR45,RR46,RR47,RR48,RR49};
		
		
		priority = new ComboBox[] {PRIOR1,PRIOR2,PRIOR3,PRIOR4,PRIOR5,PRIOR6,PRIOR7,PRIOR8,PRIOR9,PRIOR10,PRIOR11,PRIOR12,PRIOR13,PRIOR14,PRIOR15,PRIOR16,PRIOR17,PRIOR18
				,PRIOR19,PRIOR20,PRIOR21,PRIOR22,PRIOR23,PRIOR24,PRIOR25,PRIOR26,PRIOR27,PRIOR28,PRIOR29,PRIOR30,PRIOR31,PRIOR32,PRIOR33,PRIOR34
				,PRIOR35,PRIOR36,PRIOR37,PRIOR38,PRIOR39,PRIOR40,PRIOR41,PRIOR42,PRIOR43,PRIOR44,PRIOR45,PRIOR46,PRIOR47,PRIOR48,PRIOR49};
		
		
		/** init techcomments **/
		for(int i = 0; i < ROW_COUNT ;i++){
			techcomments[i].setEditable(true);
			recommendedrepairs[i].setEditable(true);
			priority[i].setEditable(true);
		}
		String[] tireTechComments = { "", "TIRE IS WORN OUT", "TIRE IS FLAT",
				"TIRE HAS HOLE IN SIDE WALL", "TIRE HAS IRREGULAR WEAR",
				"TIRE HAS ALIGNMENT WEAR", "1/32 TREAD", "2/32 TREAD",
				"3/32 TREAD", "4/32 TREAD", "5/32 TREAD", "6/32 TREAD",
				"7/32 TREAD", "8/32 TREAD", "9/32 TREAD", "10/32 TREAD",
				"11/32 TREAD", "12/32 TREAD", "13/32 TREAD", "14/32 TREAD",
				"15/32 TREAD" };
		
		
		ArrayList<String> arraylist = new ArrayList<String>();
		
		for(int i = 0; i < tireTechComments.length;i++)
			arraylist.add(tireTechComments[i]);
		
	    ObservableList<String> observableList = FXCollections.observableList(arraylist);
		
	    TC1.setItems(observableList);
		
		
		// TODO Auto-generated method stub
		//OK1.setSelected(true);
		//OK1.getStyleClass().remove("check-box-regular");
		//OK1.getStyleClass().add("check-box-valid");
		//cust.setText("TESTING");
		
		//grid.add(new NumberTextField(),6, 15);
	}
	
}
