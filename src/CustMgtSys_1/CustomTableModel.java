package CustMgtSys_1;


import javax.swing.table.DefaultTableModel;



/**
 * This class extends DefaultTableModel, overrides the isCellEditable method to always 
 * return false. 
 * @author ofalcon
 *
 */

public class CustomTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;	
	
	public CustomTableModel(){
		super(new Object[] {"ID", "First Name",
			"Last Name", "Address", "City", "State", "Zip", "Email", "Phone"}, 0);
		
		Security.clientDatabase.setTableModel(this);
		Security.clientDatabase.updateTable(Security.clientDatabase.selectAll);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		if( columnIndex >=0 && columnIndex < getColumnCount()){
			if(columnIndex == 0)
				return Integer.class;
			return String.class;
		}
		return Object.class;
	}	
	
	@Override
	public boolean isCellEditable(int row,int column){
		return false;
	}
	
	
	
	/** public void write(){
		try {
			PrintWriter writer = new PrintWriter("customers.txt", "UTF-8");
			resultSet = Security.updateTable(Security.selectAll);
			while(resultSet.next()){
				writer.println(resultSet.getString(1) + "|" + resultSet.getString(2) + "|" + resultSet.getString(3) + "|" + resultSet.getString(4) +
							"|" + resultSet.getString(5) + "|" + resultSet.getString(6) + "|" +resultSet.getString(7) + "|"+ resultSet.getString(8) + "|" +resultSet.getString(9));
			}
			writer.close();
			
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void read(){
		Scanner in = null;
		String line;
		String tokens[] = new String[9];
		try {
			in = new Scanner(new File("customers.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(in.hasNextLine()){
			line = in.nextLine();
			tokens = line.split("\\|");
			String first = tokens[1];
			String last = tokens[2];
			String address = tokens[3];
			String city = tokens[4]; String state = tokens[5]; String zip = tokens[6]; String email = tokens[7];
			String phone = tokens[8];
			
			
			String insert = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES " 
					+ "(AES_ENCRYPT('" + first +"', SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), AES_ENCRYPT('"+last+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
					+ ",AES_ENCRYPT('"+address+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('"+city+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
					+ ",AES_ENCRYPT('"+state+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('"+zip+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
					+ ",AES_ENCRYPT('"+email+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('"+phone+"',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)));";

			
			Security.execute(insert);
			
		}
	
		
		
	} **/
	

}//CustomTableModel.java

