package CustMgtSys_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Security {

	private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1/customers";
	private static Connection connection;
	private static ResultSet resultSet = null;
	private static Statement statement = null;

	private static boolean connectedToDatabase = false;
	private static boolean failedToConnect = false;

	private static CustomTableModel model = null;

	private static String username = null;
	private static String secure_password = null;
	private static String first_name = null;
	private static int permissions;

	private static String query;

	private static PreparedStatement loginStatement = null;
	private static PreparedStatement queryStatement = null;
	private static PreparedStatement insertStatement = null; 
	private static PreparedStatement updateStatement = null;
	

	private static final String selectUser = "SELECT AES_DECRYPT(first,\"rd6mNKL0vD1h95p1i\"), AES_DECRYPT(user,\"rd6mNKL0vD1h95p1i\"),"
			+ "AES_DECRYPT(password,\"rd6mNKL0vD1h95p1i\"), permissions FROM employee WHERE user = AES_ENCRYPT("
			+ "?"
			+ ","
			+ "\"rd6mNKL0vD1h95p1i\") && password = AES_ENCRYPT("
			+ "?" + "," + "\"rd6mNKL0vD1h95p1i\");";

	private static final String select = "SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
			+ ",AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info";

	private static String insert = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
	
	
	private static String update = "UPDATE info SET first = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ " last =  AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), address = AES_ENCRYPT(?,"
			+ "SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), city = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
			+ ",state = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "zip = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),email = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "primaryPhone = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
			+ " WHERE id = ? ;";
	
	
	
	
	
	
	private static final String select_All_Where = select + " WHERE";
	
	private static void prepareStatements() {
		try {
			loginStatement = connection.prepareStatement(selectUser);
			insertStatement = connection.prepareStatement(insert);
			updateStatement = connection.prepareStatement(update);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method attempts to log the specified user into the MySQL database
	 * 
	 * @param user
	 * @param password
	 * @return TRUE:
	 */
	public static boolean Login(String user, String password) {
		try {
			connection = DriverManager.getConnection(DATABASE_URL, "root","password");
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			connectedToDatabase = true;
			failedToConnect = false;
			prepareStatements();
		} catch (SQLException e) {
			failedToConnect = true;
			Error.ConnectionError();
			return false;
		}
		try {
			loginStatement.setString(1, user);
			loginStatement.setString(2, password);
			resultSet = loginStatement.executeQuery();
			connection.commit();
		} catch (SQLException e) {
			username = null;
			secure_password = null;
			disconnect();
			Error.QueryError();
			return false;
		}
		try {
			if (resultSet.next()) {
				first_name = resultSet.getString(1);
				permissions = resultSet.getInt(4);
				secure_password = password;
				username = user;
				return true;
			}
		} catch (SQLException e) {
			username = null;
			secure_password = null;
			disconnect();
			Error.ResultError();
		}
		return false;
	}

	/**
	 * This method returns TRUE: if the last attempt to connect was unsuccessful
	 * Not to be confused with whether or not the user entered the right
	 * password
	 * 
	 * @return
	 */
	public static boolean getFailedConnectionStatus() {
		return failedToConnect;
	}

	public static String getUser() {
		if (connectedToDatabase)
			return username;
		Error.NotConnected();
		return null;
	}

	public static void disconnect() {
		if (connectedToDatabase) {
			try {
				if (resultSet != null)
					resultSet.close();
				statement.close();
				connection.close();
				connectedToDatabase = false;
			} catch (SQLException e) {
				Error.CloseError();
			}
		}
	}

	public static boolean testPassword(String password) {
		return password.equals(secure_password);
	}

	/**
	 * ********************************************************** CLIENT
	 * DATABASE
	 * *****************************************************************
	 * ******************************
	 */

	public static class clientDatabase {

		public static final int MATCHES = 0;
		public static final int EXACTLY = 1;
		

		public static void setTableModel(CustomTableModel tablemodel) {
			model = tablemodel;
		}

		public static boolean selectAll(){
			String query = select + ";";
			try {
				queryStatement = connection.prepareStatement(query);
				boolean b = updateTable(queryStatement);
				queryStatement.close();
				return b;
				
			} catch (SQLException e) {
				Error.ResultError();
				e.printStackTrace();
			}
			return false;
		}
		
	
		private static boolean updateTable(PreparedStatement statement) {
			if(!connectedToDatabase){
				Error.NotConnected();
				return false;
			}
			model.setRowCount(0);
			Object[] tmpRow;
			try {
				resultSet = statement.executeQuery();
				statement.getConnection().commit();
				while (resultSet.next()) {
					tmpRow = new Object[] { resultSet.getInt(1),
							resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5),
							resultSet.getString(6), resultSet.getString(7),
							resultSet.getString(8), resultSet.getString(9) };
					model.addRow(tmpRow);
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				Error.ResultError();
				
			}
			return false;
		}

		public static boolean updateTable(final String id,final  String first,final int b1,
				final String last, final int b2,final  String address,final String city, final String state,
				final String zip,final String phone,final String email){
			
			
			query = select_All_Where;
			
			boolean[] list = new boolean[8]; 
			for(boolean b : list)
				b = false;
				
			if( (b1 != EXACTLY && b1 != MATCHES) || (b2 != EXACTLY && b2!= MATCHES)){
				System.out.println("Error in updateTable: Bad input values");
				return false;
			}
			if (id != null && !id.equals("")) {
				query = query + " id = ?";
				try {
					queryStatement = connection.prepareStatement(query);
					queryStatement.setInt(1, Integer.valueOf(id));
				} catch (SQLException e) {
					Error.QueryError();
					return false;
				}
				
				return true;
			}

			query = select_All_Where + " id LIKE '%' ";
			
			if (first == null || first.equals(""))
				query = query + "&& first LIKE '%' ";
			else {
				if (b1 == EXACTLY)
					query = query + " && first = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				else
					query = query + "&& AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE '%?% ";
				list[0] = true;
			}
			if (last == null || last.equals(""))
				query = query + "&& last LIKE '%' ";
			else {
				if (b2 == EXACTLY)
					query = query + " && last = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				else
					query = query + " && AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE '%?% " ;
				list[1] = true;
			}
			if (address == null || address.equals(""))
				query = query + "&& address LIKE '%' ";
			else{
				query = query + "&& address = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				list[2] = true;
			}
			if (city == null || city.equals(""))
				query = query + "&& city LIKE '%' ";
			else{
				query = query + "&& city = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				list[3] = true;
			}
			if (state == null || state.equals(""))
				query = query + "&& state LIKE '%' ";
			else{
				query = query + "&& state = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				list[4] = true;
			}
			if (zip == null || zip.equals(""))
				query = query + "&& zip LIKE '%' ";
			else{
				query = query + "&& zip = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				list[5] = true;
			}
			if (phone == null || phone.equals("")|| phone.equals("(xxx) xxx-xxxx")) 
				query = query + "&& primaryPhone LIKE '%' ";
			else{
				query = query + "&& primaryPhone = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				list[6] = true;
			}
			if (email == null || email.equals(""))
				query = query + "&& email LIKE '%' ";
			else{
				query = query + "&& email = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				list[7] = true;
			}
			query = query + ";";
			
			try {
				queryStatement = connection.prepareStatement(query);
			} catch (SQLException e) {
				Error.QueryError();
				return false;
			}
		
			String[] info = new String[]{first,last,address,city,state,zip,phone,email};
			int count = 1;
			for(int i = 0; i < list.length; i++){
				if(list[i]){
					try {
						queryStatement.setString(count,info[i]);
						count++;
					} catch (SQLException e) {
						Error.QueryError();
						return false;
					}
					
				}
			}
			boolean b = updateTable(queryStatement);
			try {
				queryStatement.close();
			} catch (SQLException e) {
				Error.CloseObjectError();
			}
			return b;
		}

		public static boolean addCustomer(String first, String last,
				String address, String city, String state, String zip,
				String phone, String email) {
			
			try{
				insertStatement.setString(1, first);
				insertStatement.setString(2, last);
				insertStatement.setString(3, address);
				insertStatement.setString(4, city);
				insertStatement.setString(5, state);
				insertStatement.setString(6, zip);
				insertStatement.setString(7, phone);
				insertStatement.setString(8, email);
			}catch(SQLException e){
				Error.MySQLJavaError();
			}
			
			return execute(insertStatement);
		}

		public static boolean updateCustomer(int id, String first,
				String last, String address, String city, String state,
				String zip, String phone, String email) {
			try{
				updateStatement.setString(1, first);
				updateStatement.setString(2, last);
				updateStatement.setString(3, address);
				updateStatement.setString(4, city);
				updateStatement.setString(5, state);
				updateStatement.setString(6, zip);
				updateStatement.setString(7, phone);
				updateStatement.setString(8, email);
				updateStatement.setInt(9, id);
			}catch(SQLException e){
				Error.MySQLJavaError();
			}
			return execute(updateStatement);
		}

	}// class clientDatabase

	// **********************************************************************************************************************************************************

	public void insertTable0(String nameField, String dateField,
			String tagsField, String vehicleYearField, String vehicleMakeField,
			String vehicleModelField, String workOrderField, String licField,
			String vinField, String engineField, String transField,
			String milesField) {

	}

	// private methods //
	private static ResultSet executeQuery(String query) {
		resultSet = null;
		if (!connectedToDatabase) {
			Error.NotConnected();
			return null;
		}
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			Error.QueryError();
		}
		return resultSet;
	}

	public static boolean execute(PreparedStatement ps) {
		if (connectedToDatabase) {
			try {
				ps.execute();
				ps.getConnection().commit();
				return true;
			} catch (SQLException e) {
				Error.InsertError();
			}
		} else
			Error.NotConnected();
		return false;
	}

	

}