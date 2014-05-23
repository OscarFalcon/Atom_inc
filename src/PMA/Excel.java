package PMA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.record.PrintSetupRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;

public class Excel {

	public static void main(String[] args) {

		try {
			read_excel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * You can open a workbook object from the master PMA, change individual cells in that workbook
 * then write the workbook to a different stream. GENIOUS
 * 
 * @throws IOException
 */
	
	
	public static void read_excel() throws IOException {
		File excel = new File("C:/Users/Oscar/Desktop/PMA.xls");
		FileInputStream fis = new FileInputStream(excel);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		Sheet ws = wb.getSheet("Sheet1");

		int rowNum = ws.getLastRowNum() + 1;
		int colNum = ws.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowNum][colNum];
		Row row;
		Cell cell;
		
		row = ws.getRow(7);
		cell = row.getCell(4);
		cell.setCellValue("TESTING");
		
		for (int i = 0; i < 10; i++) {
			row = ws.getRow(i);
			for (int j = 0; j < colNum; j++) {
				cell = row.getCell(j);
				if(cell == null)
					break;
				String value = cell.toString();
				data[i][j] = value;
				System.out.println("row: " + cell.getRowIndex() + " col: " + cell.getColumnIndex() + " value: " + value);
			}
		}
		fis.close();
		
		FileOutputStream fileOut = null;
		fileOut = new FileOutputStream("C:/Users/Oscar/Desktop/workbook.xls");
		wb.write(fileOut);
		fileOut.close();
		
	}

	
	
	public static void write_excel() {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");
		Row row = sheet.createRow((short) 0);

		for (int i = 0; i < 10; i++) {
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
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}