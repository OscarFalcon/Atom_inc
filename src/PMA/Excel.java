package PMA;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel {

	public static void main(String[] args){
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");
		Row row = sheet.createRow((short) 0);		
		
		
		for(int i = 0; i < 10; i ++){
			Cell cell = row.createCell(i);
			cell.setCellValue(i);
		}
	    FileOutputStream fileOut = null;
		try {
			
			fileOut = new FileOutputStream("/home/oscar/Desktop/workbook.xls");
			wb.write(fileOut);

			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    finally{
	    	try{
	    	    fileOut.close();
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
		
	
	
	Desktop desktop = Desktop.getDesktop();
	try {
	desktop.print(new File("file.xlsx"));
	} catch (Exception e) {           
	e.printStackTrace();
	}
	}

	   
}
