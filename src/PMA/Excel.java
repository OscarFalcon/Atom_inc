package PMA;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel {

	public static void main(String[] args) throws PrintException, IOException{
		
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
		String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
			    System.out.println("Default printer: " + defaultPrinter);
			    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			    FileInputStream in = null;
			    
			    try {
				in = new FileInputStream(new File("c:/temp/foo.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			    PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
			    pras.add(new Copies(1));

			    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			    Doc doc = new SimpleDoc(in, flavor, null);
			    DocPrintJob job = service.createPrintJob();
			    job.print(doc, pras);
			    in.close();
	
	
	}
	
	

	public class PrintJobWatcher{
		  boolean done = false;
		  
		  public PrintJobWatcher(DocPrintJob job) {
		    job.addPrintJobListener(new PrintJobAdapter() {
		      public void printJobCanceled(PrintJobEvent pje) {
		        allDone();
		      }
		      public void printJobCompleted(PrintJobEvent pje) {
		        allDone();
		      }
		      public void printJobFailed(PrintJobEvent pje) {
		        allDone();
		      }
		      public void printJobNoMoreEvents(PrintJobEvent pje) {
		        allDone();
		      }
		      
		      void allDone() {
		        synchronized (PrintJobWatcher.this) {
		          done = true;
		          System.out.println("Printing done ...");
		          PrintJobWatcher.this.notify();
		        }
		      }
		    });
		  }
		  public synchronized void waitForDone() {
		    try {
		      while (!done) {
		        wait();
		      }
		    } catch (InterruptedException e) {
		    }
		  }	
	}	
		
	   
}
