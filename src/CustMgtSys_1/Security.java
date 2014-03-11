package CustMgtSys_1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Security {

	private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1/customers";
	private static String MySQLUser = "root", MySQLPassword = "password";
	private static Connection connection;
	private static ResultSet resultSet;


	private static boolean connectedToDatabase = false;
	private static boolean failedToConnect = false;

	private static CustomTableModel model;

	private static String username;
	private static String secure_password;
	private static String first_name;
	private static int permissions;

	private static String query;

	

	private static PreparedStatement loginStatement;	
	private static PreparedStatement queryStatement;
	private static PreparedStatement insertStatement; 
	private static PreparedStatement updateStatement;

	private static PreparedStatement createPMA;
	private static PreparedStatement closePMA;
	private static PreparedStatement createPMAinfo;
	private static PreparedStatement updatePMAinfo;
	private static PreparedStatement createLaborCost;
	private static PreparedStatement updateLaborCost;
	private static PreparedStatement createPartCost;
	private static PreparedStatement updatePartCost;
	private static PreparedStatement createTotalLabor; 
	private static PreparedStatement updateTotalLabor;
	private static PreparedStatement createTotalParts;
	private static PreparedStatement updateTotalParts;
	private static PreparedStatement lastIncrement;

	private static void prepareStatements() {
		try {
			loginStatement = connection.prepareStatement(MySQLStrings.selectUser);
			insertStatement = connection.prepareStatement(MySQLStrings.insert);
			updateStatement = connection.prepareStatement(MySQLStrings.update);
				
			createPMA = connection.prepareStatement(MySQLStrings.createPMAStr);
			closePMA = connection.prepareStatement(MySQLStrings.closePMAStr);
			createPMAinfo = connection.prepareStatement(MySQLStrings.createPMAinfoStr);
			updatePMAinfo = connection.prepareStatement(MySQLStrings.updatePMAInfoStr);
			createLaborCost = connection.prepareStatement(MySQLStrings.createLaborCostStr);
			updateLaborCost = connection.prepareStatement(MySQLStrings.updateLaborCostStr);
			createPartCost = connection.prepareStatement(MySQLStrings.createPartCostStr);
			updatePartCost = connection.prepareStatement(MySQLStrings.updatePartCostStr);
			createTotalLabor = connection.prepareStatement(MySQLStrings.createTotalLaborCostStr);
			updateTotalLabor = connection.prepareStatement(MySQLStrings.updateTotalLaborCostStr);
			createTotalParts = connection .prepareStatement(MySQLStrings.createTotalPartsCostStr);
			updateTotalParts = connection .prepareStatement(MySQLStrings.updateTotalPartsCostStr);
			lastIncrement = connection.prepareStatement(MySQLStrings.lastIncrementStr);
		} catch (SQLException e) {
			e.printStackTrace(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		}
	}


	private static boolean connect(){
		try {
			connection = DriverManager.getConnection(DATABASE_URL, MySQLUser,MySQLPassword);
			connection.setAutoCommit(false);
			connectedToDatabase = true;
			failedToConnect = false;
			prepareStatements();
			return true;
		} catch (SQLException e) {
			failedToConnect = true;
			Error.ConnectionError();
		}
		return false;
	}
	
	public static boolean Login(String user, String password) {
		if(!connect())
			return false;
		try {
			loginStatement.setString(1, user);
			loginStatement.setString(2, password);
			if(  (resultSet = executeQuery(loginStatement)) != null){
				first_name = resultSet.getString(1);
				permissions = resultSet.getInt(4);
				secure_password = password;
				username = user;
				return true;
			}
			else{
				username = null;
				secure_password = null;
				disconnect();
			}
		}catch(SQLException e){
			//handle error
		}
		return false;
	}
	
	public static boolean getFailedConnectionStatus() {
		return failedToConnect;
	}
	
	public static String getUser() {
		if (!connectedToDatabase){
			Error.NotConnected();
			return null;
		}
			return username;
	}
	public static boolean testPassword(String password) {
		return password.equals(secure_password);
	}
	
	public static void disconnect() {
		if (!connectedToDatabase) 
			return;
		try {
			if (resultSet != null)
				resultSet.close();
				connection.close();
				connectedToDatabase = false;
		}catch (SQLException e) {
				Error.CloseError();
		}
		
	}
	
	private static boolean execute(PreparedStatement ps) {
		if(!connectedToDatabase){
			Error.NotConnected();
			return false;
		}
		try {
			ps.execute();
			ps.getConnection().commit();
			return true;
		} catch (SQLException e) {
			Error.InsertError(); //should be correct 
			e.printStackTrace();
		}
		return false;
	}
	
	private static ResultSet executeQuery(PreparedStatement ps){
		ResultSet rs = null;
		if(!connectedToDatabase){
			Error.NotConnected();
			return null;
		}
		try{
			rs = ps.executeQuery();
			ps.getConnection().commit();
			rs.next();
		}catch(SQLException e){
			e.printStackTrace();
			Error.QueryError();//check
			return null;
		}
		return rs;
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
			String query = MySQLStrings.select + ";";
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
			} catch (SQLException e) {
				e.printStackTrace();
				Error.ResultError();
				return false;
			}
			return true;
		}

		public static boolean updateTable(final String id,final  String first,final int b1,
				final String last, final int b2,final  String address,final String city, final String state,
				final String zip,final String phone,final String email){
			
			
			query = MySQLStrings.select_All_Where;
			
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

			query = MySQLStrings.select_All_Where + " id LIKE '%' ";
			
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
		
		
		
		
		
		public static boolean updateTable1(final String id,final  String first,final int b1,
				final String last, final int b2,final  String address,final String city, final String state,
				final String zip,final String phone,final String email){
				
			ResultSet rs = null;
			
			String query = "SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" +
					",AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" +
					",AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" +
					",AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info WHERE first LIKE ?";     

			
			String encrypt_first = "SELECT AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))";
			String encrypt_last = "SELECT AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))";
			
			try{
				PreparedStatement ps = connection.prepareStatement(query);
				PreparedStatement encrFirst = connection.prepareStatement(encrypt_first);
				PreparedStatement encrLast = connection.prepareStatement(encrypt_last);
				
				encrFirst.setString(1,first);
				encrLast.setString(1, last);
			
			
				String eLast;
				
				byte[] eFirst;
				
				encrFirst.setString(1,first);
				rs = executeQuery(encrFirst);
				eFirst = rs.getBytes(1);
				rs = executeQuery(encrLast);
				eLast = rs.getString(1);
			
				if(b1 == EXACTLY)
					ps.setBytes(1 ,eFirst );
				else
					ps.setBytes(1,eFirst );
				
			
				
				
				
				
				
				updateTable(ps);
			
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			return false;
		
		
		
		
		
		
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static class PMA{	
		public static Object loadPMA(int wo) throws SQLException, IOException, ClassNotFoundException{
			final String READ_OBJECT = "select object from PMAObject where wo = ?";
			PreparedStatement ps = connection.prepareStatement(READ_OBJECT);
			ps.setInt(1, wo);
			resultSet = ps.executeQuery();
			connection.commit();
			resultSet.next();
			byte[] buf = resultSet.getBytes("object");
			        ObjectInputStream objectIn = null;
			        if (buf != null)
			            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
			        Object object = objectIn.readObject();
			resultSet.close();
			ps.close();
			System.out.println((PMAObject)object);
			return object;	
		}
		
		public static void updatePMA(Object object, int wo) throws SQLException{
			final String WRITE_OBJECT = "update PMAObject set object = ? where wo = ?";
			PreparedStatement ps = connection.prepareStatement(WRITE_OBJECT);
			ps.setObject(1, object);
			ps.setInt(2, wo);
			execute(ps);
			ps.close();
		}
		
		public static int createPMA(Object object) throws SQLException{
			final String CREATE_PMA = "insert into PMAObject(name,object) VALUES(?,?)";
			PreparedStatement ps = connection.prepareStatement(CREATE_PMA);
			ps.setString(1, object.getClass().getName());
			ps.setObject(2, object);
			execute(ps);
			resultSet = lastIncrement.executeQuery();
			connection.commit();
			resultSet.next();
			int wo = resultSet.getInt(1);
			resultSet.close();
			ps.close();
			return wo;
		}
		
		
		
		
		
			
			
	}//PMA class
		
	

	


	
	
	

	private static class MySQLStrings{
		public static final String selectUser = "SELECT AES_DECRYPT(first,\"rd6mNKL0vD1h95p1i\"), AES_DECRYPT(user,\"rd6mNKL0vD1h95p1i\"),"
				+ "AES_DECRYPT(password,\"rd6mNKL0vD1h95p1i\"), permissions FROM employee WHERE user = AES_ENCRYPT("
				+ "?"
				+ ","
				+ "\"rd6mNKL0vD1h95p1i\") && password = AES_ENCRYPT("
				+ "?" + "," + "\"rd6mNKL0vD1h95p1i\");";

		public static final String select = "SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
				+ ",AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info";

		public static final String insert = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
		
		
		public static final String update = "UPDATE info SET first = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ " last =  AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), address = AES_ENCRYPT(?,"
				+ "SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), city = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
				+ ",state = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "zip = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),email = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "primaryPhone = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
				+ " WHERE id = ? ;";
		
		public static final String select_All_Where = select + " WHERE";
		
		
		public static String lastIncrementStr = "SELECT LAST_INSERT_ID() from PMA;";
		
		public static String createPMAStr = "insert into PMA(date,vin,id,active) VALUES (STR_TO_DATE(?,'%Y-%m-%d'),?,?,1);";
		
		public static String closePMAStr = "UPDATE PMA SET active = 0;";
		
		public static String createPMAinfoStr = "insert into PMA_info(wo) VALUES(?);";
				
		public static String createLaborCostStr = "insert into labor_cost(wo) VALUES (?);";	
				
		public static String createPartCostStr = "insert into part_cost(wo) VALUES (?);";
		
		public static String createTotalLaborCostStr = "insert into total_labor(wo) VALUES (?)";
				
		public static String createTotalPartsCostStr = "insert into total_parts(wo) VALUES (?)";
				
        		
				
		public static String updatePMAInfoStr = "UPDATE PMA_info SET ok  = b'?' ,notok = b'?',tech_comments = ? ,recommended_repairs = ? ,priority = ? ,qty = ? ,vendor = ?);";
		
		
		public static String updateLaborCostStr = "UPDATE labor_cost SET v0 = ? ,v1 = ? ,v2 = ? ,v3 = ? ,v4 = ? ,v5 = ? ,v6 = ? ,v7 = ? ,v8 = ? ,v9 = ? ,v10 = ?," 
				+ "v11 = ? ,v12 = ? ,v13 = ? ,v14 = ? ,v16 = ? ,v17 = ? ,v18 = ? ,v19 = ? ,v20 = ? ,v21 = ? ,v22 = ? ,v23 = ? ,v24 = ? ,v25 = ? ,v26 = ?,v27 = ?," 
				+ "v28 = ? ,v29 = ? ,30 = ? ,v31 = ? ,v32 = ? ,v33 = ? ,v34 = ?,v35 = ? ,v36 = ? ,v37 = ? ,v38 = ? ,v39 = ? ,v40 = ? ,v41 = ?) WHERE wo = ?;";

		public static String updatePartCostStr = "UPDATE part_cost SET v0 = ? ,v1 = ? ,v2 = ? ,v3 = ? ,v4 = ? ,v5 = ? ,v6 = ? ,v7 = ? ,v8 = ? ,v9 = ? ,v10 = ?," 
				+ "v11 = ? ,v12 = ? ,v13 = ? ,v14 = ? ,v16 = ? ,v17 = ? ,v18 = ? ,v19 = ? ,v20 = ? ,v21 = ? ,v22 = ? ,v23 = ? ,v24 = ? ,v25 = ? ,v26 = ?,v27 = ?," 
				+ "v28 = ? ,v29 = ? ,30 = ? ,v31 = ? ,v32 = ? ,v33 = ? ,v34 = ?,v35 = ? ,v36 = ? ,v37 = ? ,v38 = ? ,v39 = ? ,v40 = ? ,v41 = ?) WHERE wo = ?;";

		public static String updateTotalLaborCostStr = "UPDATE total_labor SET v0 = ? ,v1 = ? ,v2 = ? ,v3 = ? ,v4 = ? ,v5 = ? ,v6 = ? ,v7 = ? ,v8 = ? ,v9 = ? ,v10 = ?," 
				+ "v11 = ? ,v12 = ? ,v13 = ? ,v14 = ? ,v16 = ? ,v17 = ? ,v18 = ? ,v19 = ? ,v20 = ? ,v21 = ? ,v22 = ? ,v23 = ? ,v24 = ? ,v25 = ? ,v26 = ?,v27 = ?," 
				+ "v28 = ? ,v29 = ? ,30 = ? ,v31 = ? ,v32 = ? ,v33 = ? ,v34 = ?,v35 = ? ,v36 = ? ,v37 = ? ,v38 = ? ,v39 = ? ,v40 = ? ,v41 = ?) WHERE wo = ?;";
	
		public static String updateTotalPartsCostStr = "UPDATE total_parts SET v0 = ? ,v1 = ? ,v2 = ? ,v3 = ? ,v4 = ? ,v5 = ? ,v6 = ? ,v7 = ? ,v8 = ? ,v9 = ? ,v10 = ?," 
		+ "v11 = ? ,v12 = ? ,v13 = ? ,v14 = ? ,v16 = ? ,v17 = ? ,v18 = ? ,v19 = ? ,v20 = ? ,v21 = ? ,v22 = ? ,v23 = ? ,v24 = ? ,v25 = ? ,v26 = ?,v27 = ?," 
		+ "v28 = ? ,v29 = ? ,30 = ? ,v31 = ? ,v32 = ? ,v33 = ? ,v34 = ?,v35 = ? ,v36 = ? ,v37 = ? ,v38 = ? ,v39 = ? ,v40 = ? ,v41 = ?) WHERE wo = ?;";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}