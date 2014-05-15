package MyCMS;

import java.util.ArrayList;

public class MyCMS {

	
	
	/** 		login errors		**/
	public static final int USERNAME_MAX_ERROR = -1;
	public static final int PASSWORD_MAX_ERROR = -2;
	public static final int INVALID_CREDENTIALS = -4;

	
	/**			general errors 		**/
	public static final int SUCCESS = 0;
	public static final int CONNECTION_ERROR = -3;
	public static final int EXECUTION_ERROR = -5;
	public static final int INVALID_ARGUMENTS_ERROR = -6;
	
	
	
	
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
	
	
	
	
	public static class employee{
		
		public static Session login_employee(String username,String password){  
		String selectUser;
		Object[] args = new Object[2];
		String[] arg_types = new String[2];
		String[] result_array_types = new String[3];
		ArrayList<Object[]> results;
		
    		
		if(username.length() > MAX_USERNAME){
			//return USERNAME_MAX_ERROR;
			return null;
		}
		else if(password.length() > MAX_PASSWORD){
			//return PASSWORD_MAX_ERROR;
			return null;
		}
		
		selectUser = "SELECT AES_DECRYPT(first,\"rd6mNKL0vD1h95p1i\"), SELECT AES_DECRYPT(last,\"rd6mNKL0vD1h95p1i\")," 
					+ "AES_DECRYPT(user,\"rd6mNKL0vD1h95p1i\")"
					+ "FROM employee WHERE user = AES_ENCRYPT("
					+ "?"
					+ ","
					+ "\"rd6mNKL0vD1h95p1i\") && password = AES_ENCRYPT("
					+ "?" + "," + "\"rd6mNKL0vD1h95p1i\") LIMIT 1";
			
			
		args[0] = username;
		args[1] = password;
		arg_types[0] = "String";
		arg_types[1] = "String";
		result_array_types[0] = "String";
		result_array_types[1] = "String";
		result_array_types[2] = "String";
		
		if( (results = MySQL.executeQuery(selectUser, args, arg_types,result_array_types)) == null){/** why did exceuteQuery return null? **/
			if(MySQL.errorno == MySQL.CONNECTION_ERROR){
				//return CONNECTION_ERROR;
				return null;
			}
			else{
				return null;
				//return EXECUTION_ERROR;
			}
		}
		if(results.size() != 1){
			//return INVALID_CREDENTIALS;
			return null;
			
		}
		Object[] tmp = results.get(0);
		return new Session((String)tmp[0],(String)tmp[1],username);
		//return SUCCESS;
	}
		
}//employee class
	
	public static class client{
		
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
		public static int addCustomer(String first, String last,String address, String city, String state, String zip,String phone, String email){
			Object[] args = new Object[8];
			String[] arg_types = new String[8];
			String statement;
			
			statement = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzselpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
			
			
			
			if( !isValid(first,MAX_CLIENT_FIRST) || !isValid(last,MAX_CLIENT_LAST) || !isValid(address,MAX_CLIENT_ADDRESS) || !isValid(city,MAX_CLIENT_CITY) 
					|| !isValid(state,MAX_CLIENT_STATE) || !isValid(zip,MAX_CLIENT_ZIP) || !isValid(phone,MAX_CLIENT_PHONE) || !isValid(email,MAX_CLIENT_EMAIL)){
				
				return INVALID_ARGUMENTS_ERROR;
			}
			
			/** set arguments **/
			args[0] = first;
			args[1] = last;
			args[2] = address;
			args[3] = city;
			args[4] = state;
			args[5] = zip;
			args[6] = email;
			args[7] = phone;
			
			/** set argument types **/
			for(int i = 0; i < arg_types.length ;i++)
				arg_types[i] = "String";
			
			
			if(MySQL.execute(statement, args, arg_types) == false){
				if(MySQL.errorno == MySQL.CONNECTION_ERROR)
					return CONNECTION_ERROR;
				else
					return EXECUTION_ERROR;
				
			}	
			return SUCCESS;
		}
		private static boolean isValid(String input, int max_length){
			if(input == null)
				return false;
			if(input.length() > max_length)
				return false;
			return true;
		}
	}


	
	
	
	
	
	
	
	
	public static void main(String args[]){
		
		Session s = employee.login_employee("birdman","password");
		if(s == null){
			System.out.println("NO!");
		}
		SessionRunner r = new SessionRunner();
		//r.setSession(s);
		//Thread t = new Thread(r);
		//t.start();
		//while(true){
		//	System.out.println("valid = " + s.isValid());
		//}
		
	}
}