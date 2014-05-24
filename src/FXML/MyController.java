package FXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import Custom.NumberTextField;

public class MyController implements Initializable{
	
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
					,TC26,TC27,TC28,TC29,TC30,TC31,TC32,TC33,TC34,TC35,TC36,TC37,TC38,TC39,TC40,TC41,TC42,TC43,TC44,TC45,TC46,TC147,TC48,TC49;
	
	/** RECOMMENDED REPAIRS  **/
	@FXML private ComboBox RR1,RR2,RR3,RR4,RR5,RR6,RR7,RR8,RR9,RR10,RR11,RR12,RR13,RR14,RR15,RR16,RR17,RR18,RR19,RR20,RR21,RR22,RR23,RR24,RR25,RR26
					,RR27,RR28,RR29,RR30,RR31,RR32,RR33,RR34,RR35,RR36,RR37,RR38,RR39,RR40,RR41,RR42,RR43,RR44,RR45,RR46,RR47,RR48,RR49;
	
	/** PRIORITY **/
	@FXML private ComboBox PRIOR1,PRIOR2,PRIOR3,PRIOR4,PRIOR5,PRIOR6,PRIOR7,PRIOR8,PRIOR9,PRIOR10,PRIOR11,PRIOR12,PRIOR13,PRIOR14,PRIOR15,PRIOR16,PRIOR17,PRIOR18
					,PRIOR19,PRIOR20,PRIOR21,PRIOR22,PRIOR23,PRIOR24,PRIOR25,PRIOR26,PRIOR27,PRIOR28,PRIOR29,PRIOR30,PRIOR31,PRIOR32,PRIOR33,PRIOR34
					,PRIOR35,PRIOR36,PRIOR37,PRIOR38,PRIOR39,PRIOR40,PRIOR41,PRIOR42,PRIOR43,PRIOR44,PRIOR45,PRIOR46,PRIOR47,PRIOR48,PRIOR49;
	
		
	private CheckBox[][] checkboxes;
	
	
	@FXML GridPane grid;
	
	public void checked(ActionEvent event){		
		int place = 0, j=1;
		CheckBox box;
		
		box = (CheckBox) event.getSource();

		// Get the box that was checked!
		//place is what line it is located with respect to corresponding values
		while (place < checkboxes.length){
			if( box == checkboxes[place][0]){
				System.out.println("here");
				j=0;
				break;
			}else if( box == checkboxes[place][1]){
				j=1;
				break;
			}
			place++;
			System.out.println(place);
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

		
		
		row = ws.getRow(2);
		
		cell = row.getCell(0);
		cell.setCellValue(CUST.getText());
		
		cell = row.getCell(3);
		cell.setCellValue(YEAR.getText());
		
		
		fis.close();
		
		FileOutputStream fileOut = null;
		fileOut = new FileOutputStream("/home/oscar/Desktop/workbook.xls");
		wb.write(fileOut);
		fileOut.close();
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		checkboxes = new CheckBox[][] {{OK1, NOTOK1}, {OK2, NOTOK2}, {OK3, NOTOK3}, {OK4, NOTOK4}, {OK5, NOTOK5}};
		// TODO Auto-generated method stub
		//OK1.setSelected(true);
		//OK1.getStyleClass().remove("check-box-regular");
		//OK1.getStyleClass().add("check-box-valid");
		//cust.setText("TESTING");
		
		//grid.add(new NumberTextField(),6, 15);
	}
		
	
	
		
	
	
	
	
}
