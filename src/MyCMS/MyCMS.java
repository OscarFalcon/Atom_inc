package MyCMS;

import java.sql.Date;
import java.util.ArrayList;

import workshop.Vehicle;
import application.Person;
import application.WorkOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList; 


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
	
	
	/**			client options 				**/
	public static final int MATCHES = 0;
	public static final int EXACTLY = 1;
	
	
public static class employee{
	
	public static boolean login_employee(String username,String password){  
		String selectUser;
		ArrayList<Type> arguments = new ArrayList<Type>();
		ArrayList<Integer> result_types = new ArrayList<Integer>();
		ArrayList<Object[]> results;
		
		
		selectUser = 	"SELECT AES_DECRYPT(first,'rd6mNKL0vD1h95p1i'),"
						+ "AES_DECRYPT(last,'rd6mNKL0vD1h95p1i')," 
						+ "AES_DECRYPT(user,'rd6mNKL0vD1h95p1i')"
						+ "FROM employee WHERE user = "
						+ "AES_ENCRYPT(?,'rd6mNKL0vD1h95p1i') "
						+ "&& password = AES_ENCRYPT(?,'rd6mNKL0vD1h95p1i') LIMIT 1";
		
		
		arguments.add(new Type(username));
		arguments.add(new Type(password));
		result_types.add(Type.STRING);
		result_types.add(Type.STRING);
		result_types.add(Type.STRING);

		
		if( (results = MySQL.executeQuery(selectUser,arguments,result_types)) == null){/** why did exceuteQuery return null? **/
			//e.setMySQLError(MySQL.errorno); 
			return false;
		}
		
		if(results.size() != 1)
			return false;
		
		return true;
	}// login_employee
	
		
}//employee class
	
	public static class client{
		
		public static ObservableList<Person> search(final String first,final int b1,final String last, final int b2,
													final String address,final String city,final String state,
													final String zip,final String phone,final String email){
					
			String query =		"SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info WHERE ";
			
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results = null;
			ObservableList<Person> persons = FXCollections.observableArrayList();
			
			
			if( (b1 != EXACTLY && b1 != MATCHES) || (b2 != EXACTLY && b2!= MATCHES)){	/** b1 has to be either EXACTLY or MATCHES, same for b2	**/
				//e.setMyCMSError(SEARCH_INVALID_ARGUMENTS_ERROR);
				return null;
			}
			
			
			if (first == null || first.equals(""))			
					query = query + "first LIKE '%' ";
			else{											
				if (b1 == EXACTLY){
					query = query + "first = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
					arguments.add(new Type(first));
				}
				else{
					query = query + "AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE ? ";
					arguments.add(new Type("%"+first+"%"));
				}
			}
			if (last != null && !last.equals("")){
				if (b2 == EXACTLY){
					query = query + "&& last = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
					arguments.add(new Type(last));
				}
				else{
					query = query + "&& AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE ?" ;
					arguments.add(new Type("%"+last+"%"));
				}	
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
				//e.setMySQLError(MySQL.errorno); 
				return null;
			}
			
			Person person;
			int size = results.size();
			for(int i = 0; i < size; i++){
				person = new Person(((Integer)results.get(i)[0]).toString(),(String)results.get(i)[1],(String)results.get(i)[2],
						 			(String)results.get(i)[3],(String)results.get(i)[4],(String)results.get(i)[5],(String)results.get(i)[6],
						 			(String)results.get(i)[7],(String)results.get(i)[8]);
				
				persons.add(person);
			}
			
			return persons;
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
		
		public static boolean addCustomer(String first, String last,String address, String city, String state, String zip,String phone, String email){
			ArrayList<Type> arguments = new ArrayList<Type>();
			String statement;
			
			statement = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES (AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzselpHn97d7',512)),AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
					+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
			
			
			arguments.add(new Type(first));
			arguments.add(new Type(last));
			arguments.add(new Type(address));
			arguments.add(new Type(city));
			arguments.add(new Type(state));
			arguments.add(new Type(zip));
			arguments.add(new Type(email));
			arguments.add(new Type(phone));
			
			if(MySQL.execute(statement, arguments) == false)
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
		private static int ROW_COUNT = 50;
		
		
		public static PMAObject getPMA(int workOrder){
			String statement = "SELECT date,object FROM PMA WHERE wo = ?";
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;
			
			arguments.add(new Type(workOrder));
			result_types.add(Type.DATE);
			result_types.add(Type.PMA_OBJECT);
			
			if( (results = MySQL.executeQuery(statement,arguments, result_types)) == null)
				return null;
			
			if(results.size() == 0)
				return null;
			
			PMAObject pma = (PMAObject) results.get(0)[1];
			pma.date = (Date) results.get(0)[0];
			return pma;
		}
		
		public static boolean updatePMA(int workOrder,PMAObject pma){
			String statement = "UPDATE PMA SET object = ? WHERE wo = ?";
			ArrayList<Type> arguments = new ArrayList<Type>();
			
			arguments.add(new Type(pma));
			arguments.add(new Type(workOrder));
			return MySQL.execute(statement, arguments);
		}
		
		public static boolean createPMA(int custID,String vehicleVIN,String customerConcerns){
			
			String SEARCH_BY_VIN =    "SELECT AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
									+ "AES_DECRYPT(tags,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), year ," 
									+ "AES_DECRYPT(make,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
									+ "AES_DECRYPT(model,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
									+ "AES_DECRYPT(engine,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
									+ "AES_DECRYPT(trans,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
									+ "AES_DECRYPT(miles,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
									+ "from vehicle WHERE vin = "
									+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) && id = ?  LIMIT 1";
		
			String SEARCH_CUST = 	"SELECT AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
									+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) "
									+ "from info where id = ? LIMIT 1";
			
			
			String CREATE_PMA = 	"INSERT INTO PMA(vin,id,active,object) VALUES("
									+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
									+ "?,?,?)";
			
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;
			
			arguments.add(new Type(vehicleVIN));		/** SEARCH FOR VEHICLE INFORMATION IN DATABASE **/
			arguments.add(new Type(custID));
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
			
			final PMAObject pma;							/** FILL IN PMA WITH VEHICLE INFORMATION **/
			Object[] tmp = results.get(0);
			pma = new PMAObject(ROW_COUNT);
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
			
			arguments.add(new Type(custID));			/** SEARCH FOR CUSTOMER INFORMATION **/
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			
			if((results = MySQL.executeQuery(SEARCH_CUST, arguments, result_types)) == null)
				return false;
			
			tmp = results.get(0);						/** FILL IN PMA WITH CUSTOMER INFORMATION **/
			pma.first = (String) tmp[0];
			pma.last = (String) tmp[1];
			
			arguments.clear();
			
			final byte b = 1;							/** INSERT PMA INTO DATABASE **/
			arguments.add(new Type(vehicleVIN));
			arguments.add(new Type(custID));
			arguments.add(new Type(new Byte(b)));			
			arguments.add(new Type(pma));
			return MySQL.execute(CREATE_PMA, arguments);
		}

	}
	public static class vehicle{
		
		public static boolean addVehicle(int custID,String vin,String licnum,String tags,int year,String make,String model,String engine,String trans,String miles){
			final String ADD_VEHICLE = "INSERT INTO vehicle(vin,lic,tags,year,make,model,engine,trans,miles,id) VALUES("
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "?,"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),?)";
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			
			arguments.add(new Type(vin));
			arguments.add(new Type(licnum));
			arguments.add(new Type(tags));
			arguments.add(new Type(year));
			arguments.add(new Type(make));
			arguments.add(new Type(model));
			arguments.add(new Type(engine));
			arguments.add(new Type(trans));
			arguments.add(new Type(miles));
			arguments.add(new Type(custID));			
				
			return MySQL.execute(ADD_VEHICLE, arguments);
		}
		
		
		
		
		public static ObservableList<Vehicle> getVehicles(int custID){
			final ObservableList<Vehicle> list = FXCollections.observableArrayList();
			
			final String SELECT_VEHICLE =   "SELECT AES_DECRYPT(vin,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
											+ "AES_DECRYPT(tags,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),year," 
											+ "AES_DECRYPT(make,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(model,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(engine,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(trans,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(miles,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
											+ "from vehicle INNER JOIN info ON vehicle.id = info.id WHERE vehicle.id = ?";
			
			if(custID < 0)
				return null;
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;
			
			arguments.add(new Type(custID));
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.INTEGER);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			result_types.add(Type.STRING);
			
			if( (results = MySQL.executeQuery(SELECT_VEHICLE, arguments, result_types)) == null)
					return null;
			
			Vehicle vehicle;
			int length = results.size();
			for(int i = 0; i < length; i++){
				vehicle = new Vehicle( (String)results.get(i)[0],(String)results.get(i)[1],(String)results.get(i)[2],
						((Integer)results.get(i)[3]).toString(),(String)results.get(i)[4],(String)results.get(i)[5],(String)results.get(i)[6],
						(String)results.get(i)[7],(String)results.get(i)[8]
						);
				list.add(vehicle);
			}
			return list;
		}
		
		
	}
	
	
	public static class workOrders{
		
		public static ObservableList<WorkOrder> getWorksOrders(){
			String GET_WORK_ORDERS  =	 "SELECT PMA.wo, "
										+ "PMA.date,"
										+ "AES_DECRYPT(vehicle.vin,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(vehicle.lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(vehicle.tags,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "vehicle.year,"
										+ "AES_DECRYPT(vehicle.make,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(vehicle.model,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(vehicle.engine,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(vehicle.trans,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(vehicle.miles,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "info.id,"
										+ "AES_DECRYPT(info.first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "AES_DECRYPT(info.email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
										+ "FROM vehicle INNER JOIN PMA ON vehicle.vin = PMA.vin INNER JOIN info ON PMA.id = info.id";						
			
			
			ArrayList<Type> arguments = new ArrayList<Type>();
			ArrayList<Integer> result_types = new ArrayList<Integer>();
			ArrayList<Object[]> results;

			result_types.add(Type.INTEGER);	//wo
			result_types.add(Type.DATE);	//date
			result_types.add(Type.STRING);	//vin
			result_types.add(Type.STRING);	//lic
			result_types.add(Type.STRING);	//tags
			result_types.add(Type.INTEGER);	//year
			result_types.add(Type.STRING);	//make
			result_types.add(Type.STRING);	//model
			result_types.add(Type.STRING);	//engine
			result_types.add(Type.STRING);	//trans
			result_types.add(Type.STRING);	//miles
			result_types.add(Type.INTEGER); //id 
			result_types.add(Type.STRING);	//first
			result_types.add(Type.STRING);	//last
			result_types.add(Type.STRING);	//address
			result_types.add(Type.STRING);  //city
			result_types.add(Type.STRING); 	//state
			result_types.add(Type.STRING); 	//zip
			result_types.add(Type.STRING);	//phone
			result_types.add(Type.STRING);  //email
			
			
			if((results = MySQL.executeQuery(GET_WORK_ORDERS, arguments, result_types)) == null)
				return null;
			
			int size = results.size();
			Person person;
			WorkOrder workOrder;
			ObservableList<WorkOrder> list = FXCollections.observableArrayList();
			for(int i = 0; i < size; i++){
				
				person = new Person(((Integer) results.get(i)[11]).toString(),(String) results.get(i)[12],(String) results.get(i)[13],(String) results.get(i)[14]
									,(String) results.get(i)[15],(String) results.get(i)[16],(String) results.get(i)[17],(String) results.get(i)[18],
									(String) results.get(i)[19]);
								
				workOrder = new WorkOrder(((Integer)results.get(i)[0]).toString(),(Date)results.get(i)[1],(String)results.get(i)[2],(String) results.get(i)[3]
											,(String)results.get(i)[4],((Integer)results.get(i)[5]).toString(),(String) results.get(i)[6],(String)results.get(i)[7]
											,(String) results.get(i)[8],(String) results.get(i)[9],(String) results.get(i)[10],person); 
				list.add(workOrder);				
			}
			return list;
		}
		
		
		
		
	}
	
	
	
	

	
	
}