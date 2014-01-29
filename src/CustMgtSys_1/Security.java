package CustMgtSys_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Security {

	private static final String DATABASE_URL = "jdbc:mysql://192.168.1.15:3306/customers";
	private static Connection connection;
	private static ResultSet resultSet = null;
	private static Statement statement = null;
	private static boolean connectedToDatabase = false;


	
	private static CustomTableModel model = null;

	private static String username = null;
	private static String secure_password = null;
	private static String first_name = null;
	private static int permissions;

    private static String query;
	    
    
    
    public static final String selectAll = "SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
			+ ",AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info";

	public static final String select_All_Where = selectAll + " WHERE";

	public static final int MATCHES = 0;
	public static final int EXACTLY = 1;

	public static boolean checkLogin(String user, String password) {
		try {
			connection = DriverManager.getConnection(DATABASE_URL, "foobar",
					"foobar159");
			statement = connection.createStatement();
			connectedToDatabase = true;
		} catch (SQLException e) {
			Error.ConnectionError();
		}

		username = user;
		secure_password = password;

		final String selectUser = "SELECT AES_DECRYPT(first,\"rd6mNKL0vD1h95p1i\"), AES_DECRYPT(user,\"rd6mNKL0vD1h95p1i\"),"
				+ "AES_DECRYPT(password,\"rd6mNKL0vD1h95p1i\"), permissions FROM employee WHERE user = AES_ENCRYPT('"
				+ username
				+ "'"
				+ ",\""
				+ "rd6mNKL0vD1h95p1i\") && password = AES_ENCRYPT('"
				+ secure_password + "',\"rd6mNKL0vD1h95p1i\");";

		try {
			resultSet = statement.executeQuery(selectUser);
		} catch (SQLException e) {
			username = null;
			secure_password = null;
			disconnect();
			Error.QueryError();
		}
		try {
			if(resultSet.next()) {
				first_name = resultSet.getString(1);
				permissions = resultSet.getInt(4);
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


	public static void setTableModel(CustomTableModel tablemodel) {
		model = tablemodel;
	}
	
	public static boolean updateTable(String query) {
		if (connectedToDatabase) {
			model.setRowCount(0);
			resultSet = executeQuery(query);
			Object[] tmpRow;
			try {
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
				Error.ResultError();
			}
		} else
			Error.NotConnected();
		return false;
	}

	public static boolean updateTable(String id, String first, int b1,String last, int b2, String address, String city, String state,String zip, String phone, String email) {
		String vid,vfirst,vlast,vaddress,vcity,vstate,vzip,vemail,vphone;
		vid = valid(id);
		vfirst = valid(first);
		vlast = valid(last);
		vaddress = valid(address);
		vcity = valid(city);
		vstate = valid(state);
		vzip = valid(zip);
		vphone = valid(phone);
		vemail = valid(email);
		
		if (id == null || id.equals("")) {
			query = select_All_Where + " id LIKE '%' ";

			if (first == null || first.equals(""))
				query = query + "&& first LIKE '%' ";
			else {
				if (b1 == EXACTLY)
					query = query
							+ "&& first = "
							+ "AES_ENCRYPT('"
							+ vfirst
							+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				else if (b1 == MATCHES)
					query = query
							+ "&& AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE '%"
							+ vfirst + "%'";
				else
					query = query + "&& first LIKE '%' ";
			}
			if (last == null || last.equals(""))
				query = query + "&& last LIKE '%' ";
			else {
				if (b2 == EXACTLY)
					query = query
							+ "&& last = "
							+ "AES_ENCRYPT('"
							+ vlast
							+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				else if (b2 == MATCHES)
					query = query
							+ "&& AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE '%"
							+ vlast + "%'";
				else
					query = query + "&& last LIKE '%' ";
			}
			if (address == null || address.equals(""))
				query = query + "&& address LIKE '%' ";
			else
				query = query + "&& address = " + "AES_ENCRYPT('" + vaddress
						+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
			if (city == null || city.equals(""))
				query = query + "&& city LIKE '%' ";
			else
				query = query + "&& city = " + "AES_ENCRYPT('" + vcity
						+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
			if (state == null || state.equals(""))
				query = query + "&& state LIKE '%' ";
			else
				query = query + "&& state = " + "AES_ENCRYPT('" + vstate
						+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
			if (zip == null || zip.equals(""))
				query = query + "&& zip LIKE '%' ";
			else
				query = query + "&& zip = " + "AES_ENCRYPT('" + vzip
						+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
			if (phone == null || phone.equals("")
					|| phone.equals("(xxx) xxx-xxxx"))
				query = query + "&& primaryPhone LIKE '%' ";
			else
				query = query + "&& primaryPhone = " + "AES_ENCRYPT('" + vphone
						+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
			if (email == null || email.equals(""))
				query = query + "&& email LIKE '%' ";
			else
				query = query + "&& email = " + "AES_ENCRYPT('" + vemail
						+ "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";

		} else
			query = select_All_Where + " id = " + vid + " ";
		query = query + ";";
		return updateTable(query);
	}

	public static boolean addCustomer(String first, String last, String address, String city,String state, String zip, String phone, String email) {
		String vfirst,vlast,vaddress,vcity,vstate,vzip,vemail,vphone;
		vfirst = valid(first);
		vlast = valid(last);
		vaddress = valid(address);
		vcity = valid(city);
		vstate = valid(state);
		vzip = valid(zip);
		vphone = valid(phone);
		vemail = valid(email);
		String insert = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT('" + vfirst + "', SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ " AES_ENCRYPT('" +vlast + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('" + vaddress + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ "AES_ENCRYPT('" + vcity + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('" + vstate + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ "AES_ENCRYPT('" + vzip + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('" + vemail + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ "AES_ENCRYPT('" + vphone + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
		return execute(insert);
	}
	
	public static boolean updateCustomer(int id, String first, String last, String address, String city,String state, String zip, String phone, String email){
		String vfirst,vlast,vaddress,vcity,vstate,vzip,vemail,vphone;
		vfirst = valid(first);
		vlast = valid(last);
		vaddress = valid(address);
		vcity = valid(city);
		vstate = valid(state);
		vzip = valid(zip);
		vphone = valid(phone);
		vemail = valid(email);
		
		String update = "UPDATE info SET first = '" + vfirst + "', last = '" + vlast + "', address = '" + vaddress 
				+ "', city = '" + vcity + "', state = '" + vstate + "', zip = '" + vzip + "', email = '" + vemail + "', primaryPhone = '" + vphone + "'" 
				+ " WHERE id = '" + id + "';";
		update = "UPDATE info SET   first = (AES_ENCRYPT('" + vfirst + "', SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ " AES_ENCRYPT('" +vlast + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('" + vaddress + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ "AES_ENCRYPT('" + vcity + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('" + vstate + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ "AES_ENCRYPT('" + vzip + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT('" + vemail + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
				+ "AES_ENCRYPT('" + vphone + "',SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
		
		
		
		
		
		return execute(update);
	}
	
	public static String getUser(){
		if(connectedToDatabase)
			return username;
		return null;
	}
	
	public static void disconnect() {
		if (connectedToDatabase) {
			try {
				resultSet.close();
				statement.close();
				connection.close();
				connectedToDatabase = false;
			} catch (SQLException e) {
				Error.CloseError();
			}
		}
	}
	
	public static boolean testPassword(String password){
		return password.equals(secure_password);
	}

// **********************************************************************************************************************************************************
	
	public void insertTable0(String nameField, String dateField, String tagsField, String vehicleYearField,
            String vehicleMakeField, String vehicleModelField, String workOrderField, String licField,
            String vinField, String engineField, String transField, String milesField) {

	
		
	}
	// 				private methods 			// 
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

	private static boolean execute(String command) {
		if (connectedToDatabase) {
			try {
				statement.execute(command);
				return true;
			} catch (SQLException e) {
				Error.InsertError();
			}
		} else
			Error.NotConnected();
		return false;
	}	
	private static String valid(String input){
		 if(input != null)
			return  input.replaceAll("'", "\\\\'");
		 return null;

	}
	
	

}
