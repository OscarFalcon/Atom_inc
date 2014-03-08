package CustMgtSys_1;

import javax.swing.JOptionPane;

public class Error {

	public static void MaskFormatterError(){
		JOptionPane.showMessageDialog(null, "Error: Failed to load  page: Please restart program","System Error", JOptionPane.ERROR_MESSAGE);
	}	
	public static void NoCustomerSelected() {
		JOptionPane.showMessageDialog(null, "No customer selected","TwoFatGuys",JOptionPane.INFORMATION_MESSAGE);

	}
	public static void NotConnected(){
		JOptionPane.showMessageDialog(null,"Error: Not connected to database","Connection Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void QueryError(){
		JOptionPane.showMessageDialog(null,"Error: Could not perform database query","MySQL Error",JOptionPane.ERROR_MESSAGE);
	}
	
	public static void ResultError(){
		JOptionPane.showMessageDialog(null, "Error: Failed to read data","MySQL Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void ConnectionError(){
		JOptionPane.showMessageDialog(null,"Error: Failed to connect to server","Connection Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void CloseError(){
		JOptionPane.showMessageDialog(null, "Error: Failed to disconnect from server","Server Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void InsertError(){
		JOptionPane.showMessageDialog(null, "Error: Failed to insert data to database","MySQL Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void UpdateError(){
		JOptionPane.showMessageDialog(null,"Error: Failed to update database","MySQL Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void CloseObjectError(){
		JOptionPane.showMessageDialog(null,"Error: Failed to close operation","MySQL Error",JOptionPane.ERROR_MESSAGE);
	}
	public static void MySQLJavaError(){
		JOptionPane.showMessageDialog(null,"Error: A MySQL Java error has occured","MySQL Error",JOptionPane.ERROR_MESSAGE);
	}
	
	
}