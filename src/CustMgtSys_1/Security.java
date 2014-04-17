package CustMgtSys_1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Security {

	private static final String DATABASE_URL = "jdbc:mysql://24.153.253.126/customers";
	private static final String MySQLUser = "foo", MySQLPassword = "foobar159";
	private static Connection connection;
	private static ResultSet resultSet;


	private static boolean connectedToDatabase = false;
	
	
	private static boolean failedToConnect = false;

	private static String username;
	private static String secure_password;
	private static String first_name;
	private static int permissions;

	private static PreparedStatement loginStatement;	
	private static PreparedStatement queryStatement;
	private static PreparedStatement insertStatement; 
	private static PreparedStatement updateStatement;
	private static PreparedStatement lastIncrement;

	
	private static void prepareStatements() {
		try {
			loginStatement = connection.prepareStatement(MySQLStrings.selectUser);
			insertStatement = connection.prepareStatement(MySQLStrings.insert);
			updateStatement = connection.prepareStatement(MySQLStrings.update);
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
			resultSet = executeQuery(loginStatement);
			if(resultSet.next()){
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
		if(!connectedToDatabase || ps == null){
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
		else if(ps == null)
	       return null;
		
		try{
			rs = ps.executeQuery();
			ps.getConnection().commit();
		}catch(SQLException e){
			e.printStackTrace();
			Error.QueryError();//check
			return null;
		}
		return rs;
	}
	
	public static class client{

		public static final int MATCHES = 0;
		public static final int EXACTLY = 1;
		private static String query;
				
		
		public static ResultSet selectAll(){
			String query = MySQLStrings.select + ";";
			ResultSet rs = null;
			try {
				queryStatement = connection.prepareStatement(query);
				rs = executeQuery(queryStatement);
				//queryStatement.close();
				return rs;
				
			} catch (SQLException e) {
				Error.ResultError();
				e.printStackTrace();
			}
			return rs;
		}
		
		public static ResultSet search(final String id,final  String first,final int b1,
				final String last, final int b2,final  String address,final String city, final String state,
				final String zip,final String phone,final String email){
				
			query = MySQLStrings.select_All_Where;
			
			boolean[] list = new boolean[8]; 
			for(boolean b : list)
				b = false;
				
			if( (b1 != EXACTLY && b1 != MATCHES) || (b2 != EXACTLY && b2!= MATCHES))
				return null;
			
			
			if (id != null && !id.equals("")) {
				query = query + " id = ?";
				try {
					queryStatement = connection.prepareStatement(query);
					queryStatement.setInt(1, Integer.valueOf(id));
					return executeQuery(queryStatement);
				} catch (SQLException e) {
					Error.QueryError();
				}
				return null;
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
				return null;
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
						return null;
					}
					
				}
			}
			ResultSet rs = executeQuery(queryStatement);
			return rs;
		}

		public static int addCustomer(String first, String last,
				String address, String city, String state, String zip,
				String phone, String email) {
			int wo = -1;
			ResultSet rs;
			PreparedStatement ps;
			try{
				insertStatement.setString(1, first);
				insertStatement.setString(2, last);
				insertStatement.setString(3, address);
				insertStatement.setString(4, city);
				insertStatement.setString(5, state);
				insertStatement.setString(6, zip);
				insertStatement.setString(7, phone);
				insertStatement.setString(8, email);
				execute(insertStatement);
				ps = connection.prepareStatement(MySQLStrings.lastIncrementClientStr);
				rs = executeQuery(ps);
				rs.next();
				wo = rs.getInt(1);
				rs.close();
				ps.close();
			}catch(SQLException e){
				Error.MySQLJavaError();
			}
			return wo;
		}

		public static boolean updateCustomer(int id, String first,
				String last, String address, String city, String state,
				String zip, String phone, String email) {
			if( id < 0)
				return false;
			
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
		private static final String READ_OBJECT = "SELECT object FROM PMA WHERE wo = ?";
		private static final String WRITE_OBJECT = "UPDATE PMA SET object = ? WHERE wo = ?";
	
		public static Object loadPMA(final int wo){	
			ResultSet resultSet = null;
			PreparedStatement readObjectStatement = null;
			Object object = null;	
			ObjectInputStream objectIn = null;
			byte[] buf = null;
		
			if(wo < 0)
				return null;
			
			try{
				readObjectStatement = connection.prepareStatement(READ_OBJECT);
				readObjectStatement.setInt(1, wo);
				resultSet = executeQuery(readObjectStatement);
				if(resultSet.next())
					buf = resultSet.getBytes("object");			    
				if (buf != null)
			    	objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
				else
					System.out.println("Error class PMA, load PMA");
			    object = objectIn.readObject();
			}catch(SQLException e){
				e.printStackTrace();
			}
			catch(IOException e){	
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			finally{
				try{
					if(resultSet != null)
					    resultSet.close();
					if(readObjectStatement != null)
					readObjectStatement.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			return object;	
		}
		
		public static boolean updatePMA(final Object object,final int wo){
			PreparedStatement ps = null;
			
			if(object == null || wo < 0)
				 return false;
			
			try{
				ps = connection.prepareStatement(WRITE_OBJECT);
				ps.setObject(1, object);
				ps.setInt(2, wo);
			}catch(SQLException e){
				e.printStackTrace();
			} 
			finally{
				try{
					if(ps != null)
						ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			return execute(ps);
		}
	
		public static int createPMA(final int custID, final String VIN, final String custConcerns){
			PMAObject pma;
			PreparedStatement searchVehicleByVIN = null;
			PreparedStatement searchClient = null;
			PreparedStatement createPMA = null;
			ResultSet rs = null;
			int wo = -1;
			
			final String CREATE_PMA = "INSERT INTO PMA(vin,id,active,object) VALUES(?,?,?,?)";
			
			if(custID < 0 || VIN == null || VIN.equals("") || custConcerns == null)
				return -1;
			
			pma = new PMAObject();
			pma.customer_concerns = custConcerns;
			pma.vin = VIN;
			try{
				searchVehicleByVIN = connection.prepareStatement(MySQLStrings.searchByVinStr);
				searchClient = connection.prepareStatement(MySQLStrings.selectClientNameStr);
				createPMA = connection.prepareStatement(CREATE_PMA);
				
				searchVehicleByVIN.setString(1,VIN);
				rs = executeQuery(searchVehicleByVIN);
				rs.next();
				pma.lic = rs.getString(2);
				pma.tags = rs.getString(3);
				pma.year = rs.getInt(4);
				pma.make = rs.getString(5);
				pma.model = rs.getString(6);
				pma.engine = rs.getString(7);
				pma.trans = rs.getString(7);
				pma.miles = rs.getString(8);
				
				searchClient.setInt(1, custID);
				rs = executeQuery(searchClient);
				rs.next();
				pma.first = rs.getString(1);
				pma.last = rs.getString(2);
				
				byte b = 1;
				createPMA.setString(1,VIN);
				createPMA.setInt(2, custID);
				createPMA.setByte(3,b);//bit to byte?
				createPMA.setObject(4, pma);
				execute(createPMA);
				rs.next();
				
				rs = executeQuery(lastIncrement);
				rs.next();
				wo = rs.getInt(1);
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				try{
					if(rs != null)
						rs.close();
					if(searchVehicleByVIN != null)
						searchVehicleByVIN.close();
					if(searchClient != null)
						searchClient.close();
					if(createPMA != null)
						createPMA.close();
				}
				catch(SQLException e){
					e.printStackTrace();//handle error
				}//catch
			}//finally 
			return wo;	
		}	
	}//PMA class
		
	
	public static class Vehicle{
		
		public static boolean addVehicle(String[]args,int id){
			if(id < 0)
				return false;
			
			PreparedStatement ps = null;
			try{
				ps = connection.prepareStatement(MySQLStrings.insertVehicleStr);
				int i;
				for(i = 0; i < args.length; i++){
					ps.setString(i+1, args[i]);
				}
				ps.setInt(i+1, id);
				
			}
			catch(SQLException e){	
			}//handle error
			return execute(ps);
		}
		
		public static ResultSet searchVehicles(int id){
			if(id < 0)
				return null;
			
			PreparedStatement ps = null;
			try{
				ps = connection.prepareStatement(MySQLStrings.selectVechicleStr);
				ps.setInt(1, id);
			}catch(SQLException e){
				//handle error
			}
			return executeQuery(ps);
		}
		public static ResultSet searchVehicles(String vin){
			if(vin == null || vin.equals(""))
				return null;
			
			
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				ps = connection.prepareStatement(MySQLStrings.searchByVinStr);			
				ps.setString(1,vin);
				rs = executeQuery(ps);
			
			}catch(SQLException e){
				e.printStackTrace();
			}
				return rs;
		}
	}
	
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
		
		
		public static final String selectClientNameStr = "SELECT AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info where id = ?";
		

		public static final String insert = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzselpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
		
		
		public static final String update = "UPDATE info SET first = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ " last =  AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), address = AES_ENCRYPT(?,"
				+ "SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), city = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
				+ ",state = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "zip = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),email = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
				+ "primaryPhone = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
				+ " WHERE id = ? ;";
		
		public static final String select_All_Where = select + " WHERE";
		
		
		public static String lastIncrementStr = "SELECT LAST_INSERT_ID() from PMA";
		
		public static String lastIncrementClientStr = "SELECT LAST_INSERT_ID() from info";
		
		
		public static String insertVehicleStr = "INSERT into vehicle(vin,lic,tags,year,make,model,engine,trans,miles,id) VALUES(" +
					"AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," +
					"?,?,?,?,?,?,?,?)";
		
		public static String selectVechicleStr = "SELECT  AES_DECRYPT(vin,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" +
				",AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),tags,year,make,model,engine,trans,miles from vehicle " +
				"WHERE  id = ?";
		
		public final static String searchByVinStr =  "SELECT  AES_DECRYPT(vin,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" +
				",AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),tags,year,make,model,engine,trans,miles " +
				"from vehicle WHERE vin = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))";
		
		
		
		
	}	
}