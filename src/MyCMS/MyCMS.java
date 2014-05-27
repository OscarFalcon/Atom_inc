package MyCMS;


import java.util.ArrayList;

import CustMgtSys_1.PMAObject;


public class MyCMS {
	
	/**			general errors		**/
	public static final int SUCCESS = 0;	
	
	/** 		login errors		**/
	public static final int LOGIN_USERNAME_MAX_ERROR = -1;
	public static final int LOGIN_PASSWORD_MAX_ERROR = -2;
	public static final int LOGIN_INVALID_CREDENTIALS_ERROR = -3;
	
	/**			add customer 		**/
	public static final int ADD_INVALID_ARGUMENTS_ERROR = -4;
	
	/**			update customer		**/	
	public static final int UPDATE_INVALID_ARGUMENTS_ERROR = -5;
	
	/**			search customer 	**/
	public static final int SEARCH_INVALID_ARGUMENTS_ERROR = -6;
	
	/** 		employee table					**/ 
	private static final int MAX_USERNAME = 32;
	private static final int MAX_PASSWORD = 32;
	private static final int MAX_EMPLOYEE_FIRST = 32;
	private static final int MAX_EMPLOYEE_LAST = 32;
	
	
	/** 		client table 					**/
	private static final int MAX_CLIENT_FIRST = 32;
	private static final int MAX_CLIENT_LAST = 32;
	private static final int MAX_CLIENT_ADDRESS = 64;
	private static final int MAX_CLIENT_CITY = 64;
	private static final int MAX_CLIENT_STATE = 11;
	private static final int MAX_CLIENT_ZIP = 11;
	private static final int MAX_CLIENT_EMAIL = 255;
	private static final int MAX_CLIENT_PHONE = 20;
	
	/**			client options 				**/
	public static final int MATCHES = 0;
	public static final int EXACTLY = 1;
	
	
public static class employee{
	
	public static Session login_employee(String username,String password, Error e){  
		String selectUser;
		ArrayList<Type> arguments = new ArrayList<Type>();
		ArrayList<Integer> result_types = new ArrayList<Integer>();
		ArrayList<Object[]> results;
		
		
		selectUser = "SELECT AES_DECRYPT(first,\"rd6mNKL0vD1h95p1i\"), AES_DECRYPT(last,\"rd6mNKL0vD1h95p1i\")," 
				+ "AES_DECRYPT(user,\"rd6mNKL0vD1h95p1i\")"
				+ "FROM employee WHERE user = AES_ENCRYPT("
				+ "?"
				+ ","
				+ "\"rd6mNKL0vD1h95p1i\") && password = AES_ENCRYPT("
				+ "?" + "," + "\"rd6mNKL0vD1h95p1i\") LIMIT 1";
		
		
		if(username.length() > MAX_USERNAME){
			e.setMyCMSError(LOGIN_USERNAME_MAX_ERROR);
			return null;
		}
		if(password.length() > MAX_PASSWORD){
			e.setMyCMSError(LOGIN_PASSWORD_MAX_ERROR);
			return null;
		}
				
		arguments.add(new Type(username));
		arguments.add(new Type(password));
		result_types.add(Type.STRING);
		result_types.add(Type.STRING);
		result_types.add(Type.STRING);

		
		if( (results = MySQL.executeQuery(selectUser,arguments,result_types)) == null){/** why did exceuteQuery return null? **/
			e.setMySQLError(MySQL.errorno); 
			return null;
		}
		if(results.size() != 1){
			e.setMyCMSError(LOGIN_INVALID_CREDENTIALS_ERROR);
			return null;
		}
		Object[] tmp = results.get(0);
		return new Session((String)tmp[0],(String)tmp[1],username);
	}// login_employee
		
}//employee class
	
	public static class client{
		
		public static ArrayList<Object[]> search(final String first,final int b1,
												final String last, final int b2,final String address,final String city,final String state,
												final String zip,final String phone,final String email, Error e){
					
			String query = "SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
					+ ",AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info WHERE ";
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;
			
			if( (b1 != EXACTLY && b1 != MATCHES) || (b2 != EXACTLY && b2!= MATCHES)){	/** b1 has to be either EXACTLY or MATCHES, same for b2	**/
				e.setMyCMSError(SEARCH_INVALID_ARGUMENTS_ERROR);
				return null;
			}
			if (first == null || first.equals(""))			
					query = query + "first LIKE '%' ";
			else{											
				if (b1 == EXACTLY)
					query = query + "first = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				else
					query = query + "AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE ? ";
				arguments.add(new Type("%"+first+"%"));
			}
			if (last != null && !last.equals("")){
				if (b2 == EXACTLY)
					query = query + "&& last = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				else
					query = query + "&& AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE ?" ;
				arguments.add(new Type("%"+last+"%"));
			}
			if(address != null && !address.equals("")){
				query = query + "&& address = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type(address));
			}
			if (city != null && !city.equals("")){
				query = query + "&& city = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type(city));
			}
			if (state != null && !state.equals("")){
				query = query + "&& state = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type(state));
			}
			if (zip != null && !zip.equals("")){
				query = query + "&& zip = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type(zip));
			}
			if (phone != null && !phone.equals("")){
				query = query + "&& primaryPhone = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type(phone));
			}
			if (email != null && !email.equals("")){
				query = query + "&& email = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type(email));
			}
			result_types.add(Type.INTEGER);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);

			if( (results = MySQL.executeQuery(query,arguments,result_types)) == null){
				e.setMySQLError(MySQL.errorno); 
				return null;
			}
			return results;
		}
		
		/**
		 * adds a new customer to the database. All fields must not be null. This method does not check for any empty strings.
		 * @param first
		 * @param last
		 * @param address
		 * @param city
		 * @param state
		 * @param zip
		 * @param phone
		 * @param email
		 * @return SUCCCESS if successfully added client to database, error code otherwise
		 */
		public static boolean addCustomer(String first, String last,String address, String city, String state, String zip,String phone, String email, Error e){
			ArrayList<Type> arguments = new ArrayList<Type>();
			String statement;
			
			statement = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzselpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
			
			
			if( !isValid(first,MAX_CLIENT_FIRST) || !isValid(last,MAX_CLIENT_LAST) || !isValid(address,MAX_CLIENT_ADDRESS) || !isValid(city,MAX_CLIENT_CITY) 
					|| !isValid(state,MAX_CLIENT_STATE) || !isValid(zip,MAX_CLIENT_ZIP) || !isValid(phone,MAX_CLIENT_PHONE) || !isValid(email,MAX_CLIENT_EMAIL)){
				
				e.setMyCMSError(ADD_INVALID_ARGUMENTS_ERROR);
				return false;
			}
			
			arguments.add(new Type(first));
			arguments.add(new Type(last));
			arguments.add(new Type(address));
			arguments.add(new Type(city));
			arguments.add(new Type(state));
			arguments.add(new Type(zip));
			arguments.add(new Type(email));
			arguments.add(new Type(phone));
			
			if(MySQL.execute(statement, arguments) == false){
				e.setMySQLError(MySQL.errorno);
				return false;
			}	
			return true;
		}
		
		private static boolean isValid(String input, int max_length){
			if(input == null)
				return false;
			if(input.length() > max_length)
				return false;
			return true;
		}
		
		public static boolean updateCustomer(int id,String first, String last,String address, String city, String state, String zip,String phone, String email, Error e){
			ArrayList<Type> arguments = new ArrayList<Type>();
			
			String statement = "UPDATE info SET first = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ " last =  AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), address = AES_ENCRYPT(?,"
					+ "SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), city = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))" 
					+ ",state = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "zip = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),email = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "primaryPhone = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
					+ " WHERE id = ?";
			
			if( !isValid(first,MAX_CLIENT_FIRST) || !isValid(last,MAX_CLIENT_LAST) || !isValid(address,MAX_CLIENT_ADDRESS) || !isValid(city,MAX_CLIENT_CITY) 
					|| !isValid(state,MAX_CLIENT_STATE) || !isValid(zip,MAX_CLIENT_ZIP) || !isValid(phone,MAX_CLIENT_PHONE) || !isValid(email,MAX_CLIENT_EMAIL) || id < 0){
				
				e.setMyCMSError(UPDATE_INVALID_ARGUMENTS_ERROR);
				return false;
			}
			/** set arguments **/
			arguments.add(new Type(first));
			arguments.add(new Type(last));
			arguments.add(new Type(address));
			arguments.add(new Type(city));
			arguments.add(new Type(state));
			arguments.add(new Type(zip));
			arguments.add(new Type(email));
			arguments.add(new Type(phone));
			arguments.add(new Type(id));

			if(MySQL.execute(statement, arguments) == false){
				e.setMySQLError(MySQL.errorno);
				return false;
			}	
			return true; 
		}	
	}
	public static class PMA{
		
		public static PMAObject getPMA(int workOrder){
			String statement = "SELECT object FROM PMA WHERE wo = ?";
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;
			
			arguments.add(new Type(workOrder));
			result_types.add(Type.OBJECT);
			results = MySQL.executeQuery(statement,arguments, result_types);
			if(results != null)
				return (PMAObject)results.get(0)[0];
			return null;
		}
		public static boolean updatePMA(int workOrder,PMAObject pma){
			String statement = "UPDATE PMA SET object = ? WHERE wo = ?";
			ArrayList<Type> arguments = new ArrayList<Type>();
			
			arguments.add(new Type(pma));
			arguments.add(new Type(workOrder));
			return MySQL.execute(statement, arguments);
			
		}
		public static boolean createPMA(int custID,String vehicleVIN,String customerConcerns){
			String CREATE_PMA = "INSERT INTO PMA(vin,id,active,object) VALUES(?,?,?,?)";
			
			String SEARCH_BY_VIN =  "SELECT AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),tags,year,make,model,engine,trans,miles " +
					"from vehicle WHERE vin = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIMIT 1";
		
			String SEARCH_CUST = "SELECT AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
			+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info where id = ? LIMIT 1";
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;
			
			
			arguments.add(new Type(vehicleVIN));
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.INTEGER);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);

			if((results = MySQL.executeQuery(SEARCH_BY_VIN, arguments, result_types)) == null)
				return false;
			
			PMAObject pma;
			Object[] tmp = results.get(0);
			pma = new PMAObject();
			pma.customer_concerns = customerConcerns;
			pma.vin = vehicleVIN;
			pma.lic = (String) tmp[0];
			pma.tags = (String) tmp[1];
			pma.year = (Integer) tmp[2];
			pma.make = (String) tmp[3];
			pma.model = (String) tmp[4];
			pma.engine = (String) tmp[5];
			pma.trans = (String) tmp[6];
			pma.miles = (String) tmp[7];
			
			arguments.clear();
			result_types.clear();
			results.clear();
			
			arguments.add(new Type(custID));
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			
			if((results = MySQL.executeQuery(SEARCH_CUST, arguments, result_types)) == null)
				return false;
			
			tmp = results.get(0);
			pma.first = (String) tmp[0];
			pma.last = (String) tmp[1];
			
			arguments.clear();
			
			byte b = 1;
			arguments.add(new Type(vehicleVIN));
			arguments.add(new Type(custID));
			arguments.add(new Type(new Byte(b)));			
			arguments.add(new Type(pma));
			return MySQL.execute(CREATE_PMA, arguments);
		}
		
		
		
	}
	
	
	
	
	
	
	public static void main(String args[]){
		Error e = new Error();
		
		Session s = employee.login_employee("birdman","password",e);
		if(s == null){
			switch( e.getMyCMSError() ){
				case MyCMS.LOGIN_USERNAME_MAX_ERROR:
					System.out.println("USERNAME MAX ERROR");
					break;
				case MyCMS.LOGIN_PASSWORD_MAX_ERROR:
					System.out.println("PASSWORD MAX ERROR");
					break;
				case MyCMS.LOGIN_INVALID_CREDENTIALS_ERROR:
					System.out.println("INVALID CREDENTIALS");
			}
			switch ( e.getMySQLError() ){
				case MySQL.NO_INTERNET_CONNECTION_ERROR:
					System.out.println("NO INTERNET ERROR");
					break;
				case MySQL.CONNECTION_ERROR:
					System.out.println("SERVER DOWN ERROR");
					break;
				case MySQL.SUCCESS:
					break;
				default:
					System.out.println("UNEXPECTED ERROR");
			}
			return;
		}
		e.reset();
		ArrayList<Object[]> results = MyCMS.client.search("Oscar",MyCMS.MATCHES,"Fa",MyCMS.MATCHES,null,null,null,null,null,null,e);
		
		int size = results.size();
		Object[] tmp;
		System.out.println("size = " + size);
		for(int i = 0; i < size; i++){
			System.out.print("getting... ");
			tmp = results.get(i);
			System.out.print((Integer)tmp[0] + " ");
			for(int j = 1; j < 8; j++)
				System.out.print((String)tmp[j] + " ") ;
			System.out.println();
		}
		SessionRunner r = new SessionRunner();
		r.setSession(s);
		Thread t = new Thread(r);
		t.start();
		System.out.println("Session Started");
		while(s.isValid())
			;
		System.out.println("Session Expired");
	}
	
	
}