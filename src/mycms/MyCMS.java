package mycms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList; 

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import pma.PMAObject;
import pma.PMAObject.Status;
import workshop.PMARow;
import workshop.Person;
import workshop.Vehicle;
import workshop.WorkOrder; 
import mycms.Type.Class;

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
		final String selectUser;
		final Type<?>[] arguments = new Type<?>[2];
		final Class[] result_types = new Class[3];
		final ArrayList<Object[]> results;
		
		
		selectUser = 	"SELECT AES_DECRYPT(first,'rd6mNKL0vD1h95p1i'),"
						+ "AES_DECRYPT(last,'rd6mNKL0vD1h95p1i')," 
						+ "AES_DECRYPT(user,'rd6mNKL0vD1h95p1i')"
						+ "FROM employee WHERE user = "
						+ "AES_ENCRYPT(?,'rd6mNKL0vD1h95p1i') "
						+ "&& password = AES_ENCRYPT(?,'rd6mNKL0vD1h95p1i') LIMIT 1";
		
		arguments[0] = new Type<String>(username);
		arguments[1] = new Type<String>(password);
		result_types[0] = Class.STRING;
		result_types[1] = Class.STRING;
		result_types[2] = Class.STRING;

		
		if( (results = MySQL.executeQuery(selectUser,arguments,result_types)) == null){/** why did exceuteQuery return null? **/
			//e.setMySQLError(MySQL.errorno); 
			return false;
		}
		
		if(results.size() != 1)
			return false;
		
		return true;
	}// login_employee
	
		
}//employee class
	
	public static class customer{
		
		public static ObservableList<Person> search(final String first,final int b1,final String last, final int b2,
													final String address,final String city,final String state,
													final String zip,final String phone,final String email){
					
			
			final ArrayList<Type<?>> arguments = new ArrayList<Type<?>>();
			final Class[] result_types = new Class[9];
			final ArrayList<Object[]> results;
			final ObservableList<Person> persons = FXCollections.observableArrayList();
			
			String SEARCH_CUSTOMER_STRING = "SELECT id, AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(address,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(city,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(state,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(zip,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(primaryPhone,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) from info WHERE ";
			
			if( (b1 != EXACTLY && b1 != MATCHES) || (b2 != EXACTLY && b2!= MATCHES)){	/** b1 has to be either EXACTLY or MATCHES, same for b2	**/
				//e.setMyCMSError(SEARCH_INVALID_ARGUMENTS_ERROR);
				return null;
			}
			
			if (first == null || first.equals(""))			
					SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "first LIKE '%' ";
			
			else{											
				if (b1 == EXACTLY){
					SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "first = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
					arguments.add(new Type<String>(first)); 
				}
				else{
					SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE ? ";
					arguments.add(new Type<String>("%"+first+"%"));
				}
			}
			if (last != null && !last.equals("")){
				if (b2 == EXACTLY){
					SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& last = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
					arguments.add(new Type<String>(last));
				}
				else{
					SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) LIKE ?" ;
					arguments.add(new Type<String>("%"+last+"%"));
				}	
			}
			if(address != null && !address.equals("")){
				SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& address = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type<String>(address));
			}
			if (city != null && !city.equals("")){
				SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& city = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type<String>(city));
			}
			if (state != null && !state.equals("")){
				SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& state = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type<String>(state));
			}
			if (zip != null && !zip.equals("")){
				SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& zip = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type<String>(zip));
			}
			if (phone != null && !phone.equals("")){
				SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& primaryPhone = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type<String>(phone));
			}
			if (email != null && !email.equals("")){
				SEARCH_CUSTOMER_STRING = SEARCH_CUSTOMER_STRING + "&& email = " + "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) ";
				arguments.add(new Type<String>(email));
			}
			
			result_types[0] = Class.INTEGER;
			result_types[1] = Class.STRING;
			result_types[2] = Class.STRING;
			result_types[3] = Class.STRING;
			result_types[4] = Class.STRING;
			result_types[5] = Class.STRING;
			result_types[6] = Class.STRING;
			result_types[7] = Class.STRING;
			result_types[8] = Class.STRING; 
			
			if( (results = MySQL.executeQuery(SEARCH_CUSTOMER_STRING,arguments,result_types)) == null){
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
			ArrayList<Type<?>> arguments = new ArrayList<Type<?>>();
			final String ADD_CUSTOMER;
			
			ADD_CUSTOMER  = "INSERT INTO info(first,last,address,city,state,zip,email,primaryPhone) VALUES "
							+"(AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
							+ " AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
							+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
							+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
							+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
							+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzselpHn97d7',512)),"
							+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
							+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)))";
						
			arguments.add(new Type<String>(first));
			arguments.add(new Type<String>(last));
			arguments.add(new Type<String>(address));
			arguments.add(new Type<String>(city));
			arguments.add(new Type<String>(state));
			arguments.add(new Type<String>(zip));
			arguments.add(new Type<String>(email));
			arguments.add(new Type<String>(phone));
			
			
			return MySQL.execute(ADD_CUSTOMER, arguments);
			
		}
		
		public static boolean updateCustomer(int id,String first, String last,String address, String city, String state, String zip,String phone, String email, Error e){
			ArrayList<Type<?>> arguments = new ArrayList<Type<?>>();
			final String UPDATE_CUSTOMER;
			
			UPDATE_CUSTOMER = 	  "UPDATE info SET "
								+ "first = AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ " last =  AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ " address = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
								+ "city = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "state = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "zip = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "email = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
								+ "primaryPhone = AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
								+ "WHERE id = ?";
			
			arguments.add(new Type<String>(first));
			arguments.add(new Type<String>(last));
			arguments.add(new Type<String>(address));
			arguments.add(new Type<String>(city));
			arguments.add(new Type<String>(state));
			arguments.add(new Type<String>(zip));
			arguments.add(new Type<String>(email));
			arguments.add(new Type<String>(phone));
			arguments.add(new Type<Integer>(id));

			return MySQL.execute(UPDATE_CUSTOMER, arguments);
		}
	
	}	
	
	public static class pma{
		
		private static int ROW_COUNT = 50;
		
		
		public static PMAObject getPMA(int workOrder){
			
			final Type<?>[] arguments = new Type<?>[1];
			final Class[] result_types = new Class[2];
			final ArrayList<Object[]> results;
			final String statement = "SELECT date,object FROM PMA WHERE wo = ?";

			arguments[0] = new Type<Integer>(workOrder);
			result_types[0] = Class.DATE;
			result_types[1] = Class.PMA_OBJECT;
			
			if( (results = MySQL.executeQuery(statement,arguments, result_types)) == null)
				return null;
			
			/** query succeeded, but returned no results **/
			if(results.size() == 0)
				return null;
			
			PMAObject pma = (PMAObject) results.get(0)[1];
			pma.date = (Date) results.get(0)[0];
			return pma;
		}
		
		public static boolean updatePMA(int workOrder,PMAObject pma){
			final Type<?>[] arguments = new Type<?>[2];
			final String UPDATE_PMA = "UPDATE PMA SET object = ? WHERE wo = ?";

			arguments[0] = new Type<PMAObject>(pma);
			arguments[1] = new Type<Integer>(workOrder);
			return MySQL.execute(UPDATE_PMA, arguments);
		}
		
		public static boolean createPMA(int custID,String vehicleVIN,String customerConcerns,BigDecimal markupRateParts,BigDecimal markupRateLabor){
			
			ArrayList<Type<?>> arguments = new ArrayList<Type<?>>();
			ArrayList<Class> result_types = new ArrayList<Class>();
			ArrayList<Object[]> results;
			
			final String SEARCH_BY_VIN =    "SELECT AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(tags,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), year ," 
											+ "AES_DECRYPT(make,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(model,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(engine,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(trans,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(miles,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
											+ "from vehicle WHERE vin = "
											+ "AES_ENCRYPT(?, SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) && id = ?  LIMIT 1";
		
			final String SEARCH_CUST = 		"SELECT AES_DECRYPT(first,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(last,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)) "
											+ "from info where id = ? LIMIT 1";
			
			
			final String CREATE_PMA = 		"INSERT INTO PMA(vin,id,active,object) VALUES("
											+ "AES_ENCRYPT(?,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), ?,?,?)";
			
			arguments.add(new Type<String>(vehicleVIN));		/** SEARCH FOR VEHICLE INFORMATION IN DATABASE **/
			arguments.add(new Type<Integer>(custID));
			result_types.add(Class.STRING);
			result_types.add(Class.STRING);
			result_types.add(Class.INTEGER);
			result_types.add(Class.STRING);
			result_types.add(Class.STRING);
			result_types.add(Class.STRING);
			result_types.add(Class.STRING);
			result_types.add(Class.STRING);
			
			if((results = MySQL.executeQuery(SEARCH_BY_VIN, arguments,result_types.toArray(new Class[result_types.size()]))) == null)
				return false;
			
			final PMAObject pma;							/** FILL IN PMA WITH VEHICLE INFORMATION **/
			Object[] tmp = results.get(0);
			pma = new PMAObject(ROW_COUNT,markupRateParts,markupRateLabor);
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
			
			arguments.add(new Type<Integer>(custID));		/** SEARCH FOR CUSTOMER INFORMATION **/
			result_types.add(Class.STRING);
			result_types.add(Class.STRING);
			
			if((results = MySQL.executeQuery(SEARCH_CUST, arguments, result_types.toArray(new Class[result_types.size()]))) == null)
				return false;
			
			tmp = results.get(0);						/** FILL IN PMA WITH CUSTOMER INFORMATION **/
			pma.first = (String) tmp[0];
			pma.last = (String) tmp[1];
			
			arguments.clear();
			
			final byte b = 1;							/** INSERT PMA INTO DATABASE **/
			arguments.add(new Type<String>(vehicleVIN));
			arguments.add(new Type<Integer>(custID));
			arguments.add(new Type<Byte>(new Byte(b)));			
			arguments.add(new Type<PMAObject>(pma));
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
			
			ArrayList<Type<?>> arguments = new ArrayList<Type<?>>();
			
			arguments.add(new Type<String>(vin));
			arguments.add(new Type<String>(licnum));
			arguments.add(new Type<String>(tags));
			arguments.add(new Type<Integer>(year));
			arguments.add(new Type<String>(make));
			arguments.add(new Type<String>(model));
			arguments.add(new Type<String>(engine));
			arguments.add(new Type<String>(trans));
			arguments.add(new Type<String>(miles));
			arguments.add(new Type<Integer>(custID));			
				
			return MySQL.execute(ADD_VEHICLE, arguments);
		}
		
		
		
		
		public static ObservableList<Vehicle> getVehicles(int custID){
			final Type<?>[] arguments = new Type<?>[1];
			final Class[] result_types = new Class[9];
			final ArrayList<Object[]> results;
			final ObservableList<Vehicle> list = FXCollections.observableArrayList();
			int length;
			final String SELECT_VEHICLE =   "SELECT AES_DECRYPT(vin,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
											+ "AES_DECRYPT(lic,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))," 
											+ "AES_DECRYPT(tags,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),year," 
											+ "AES_DECRYPT(make,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(model,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(engine,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(trans,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)), "
											+ "AES_DECRYPT(miles,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512))"
											+ "from vehicle INNER JOIN info ON vehicle.id = info.id WHERE vehicle.id = ?";
						
			arguments[0] = new Type<Integer>(custID);
			result_types[0] = Class.STRING;
			result_types[1] = Class.STRING;
			result_types[2] = Class.STRING;
			result_types[3] = Class.INTEGER;
			result_types[4] = Class.STRING;
			result_types[5] = Class.STRING;
			result_types[6] = Class.STRING;
			result_types[7] = Class.STRING;
			result_types[8] = Class.STRING;
			
			if( (results = MySQL.executeQuery(SELECT_VEHICLE, arguments, result_types)) == null)
					return null;
			
			Vehicle vehicle;
			length = results.size();
			for(int i = 0; i < length; i++){
				vehicle = new Vehicle( (String)results.get(i)[0],(String)results.get(i)[1],(String)results.get(i)[2],
						((Integer)results.get(i)[3]).toString(),(String)results.get(i)[4],(String)results.get(i)[5],(String)results.get(i)[6],
						(String)results.get(i)[7],(String)results.get(i)[8]);
				
				
				list.add(vehicle);
			}
			return list;
		}
	}
	
	
	public static class workorder{
		
		public static boolean getWorksOrders(ObservableList<WorkOrder> work_order_list, ArrayList<ObservableList<PMARow>> preview_list){
			final Type<?>[] arguments = new Type<?>[0];
			final Class[] result_types = new Class[21];
			final ArrayList<Object[]> results;
			int length;
			
			final String GET_WORK_ORDERS  =	 "SELECT PMA.wo, "
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
										+ "AES_DECRYPT(info.email,SHA2('a1767a2TE6LsoL4bCg161LbqzpHn97d7',512)),"
										+ "PMA.object "
										+ "FROM vehicle INNER JOIN PMA ON vehicle.vin = PMA.vin INNER JOIN info ON PMA.id = info.id";						
						
			
			if(work_order_list == null || preview_list == null )
				return false;
			
			
			work_order_list.clear();
			preview_list.clear();
			
			
			result_types[0] =  Class.INTEGER;	//wo
			result_types[1] =  Class.DATE;		//date
			result_types[2] =  Class.STRING;	//vin
			result_types[3] =  Class.STRING;	//lic
			result_types[4] =  Class.STRING;	//tags
			result_types[5] =  Class.INTEGER;	//year
			result_types[6] =  Class.STRING;	//make
			result_types[7] =  Class.STRING;	//model
			result_types[8] =  Class.STRING;	//engine
			result_types[9] =  Class.STRING;	//trans
			result_types[10] = Class.STRING;	//miles
			result_types[11] = Class.INTEGER; 	//id 
			result_types[12] = Class.STRING;	//first
			result_types[13] = Class.STRING;	//last
			result_types[14] = Class.STRING;	//address
			result_types[15] = Class.STRING; 	 //city
			result_types[16] = Class.STRING; 	//state
			result_types[17] = Class.STRING; 	//zip
			result_types[18] = Class.STRING;	//phone
			result_types[19] = Class.STRING;  	//email
			result_types[20] = Class.PMA_OBJECT;
			
			if((results = MySQL.executeQuery(GET_WORK_ORDERS, arguments, result_types)) == null)
				return false;
			
			Person person;
			WorkOrder workOrder;
			PMAObject pmaObject;
			ObservableList<PMARow> pmaList;
			length = results.size();
			for(int i = 0; i < length; i++){
				
				person = new Person(((Integer) results.get(i)[11]).toString(),(String) results.get(i)[12],(String) results.get(i)[13],(String) results.get(i)[14]
									,(String) results.get(i)[15],(String) results.get(i)[16],(String) results.get(i)[17],(String) results.get(i)[18],
									(String) results.get(i)[19]);
				
				
				workOrder = new WorkOrder(((Integer)results.get(i)[0]).toString(),(Date)results.get(i)[1],(String)results.get(i)[2],(String) results.get(i)[3]
											,(String)results.get(i)[4],((Integer)results.get(i)[5]).toString(),(String) results.get(i)[6],(String)results.get(i)[7]
											,(String) results.get(i)[8],(String) results.get(i)[9],(String) results.get(i)[10],person); 
				
				work_order_list.add(workOrder);
				
				
				
				pmaObject = (PMAObject) results.get(i)[20];  /** get the PMA and construct the pma preview out of it **/
				
				
				
				pmaList = FXCollections.observableArrayList();
				int size = pmaObject.getRowCount();
				
				for(int j = 0; j < size ;j++){
					if(pmaObject.ROW_STATUS[j][1] == Status.APPROVED){ /** if the current row has been approved **/
						pmaList.add(new PMARow( pmaObject.descriptions[j],
												pmaObject.tech_comments[j],
												pmaObject.recommended_repairs[j],
												PMAObject.priorityToString(pmaObject.priority[j]),
												pmaObject.totalParts[j].toString(),
												pmaObject.totalLabor[j].toString()
						));	
						}
				}
				preview_list.add(pmaList);			
			}
			return true;
		}//getOrderOrders
		
		
		public ObservableList<WorkOrder> searchWorkOrder(){
			ArrayList<Type<?>> arguments = new ArrayList<Type<?>>();
			ArrayList<Class> result_types = new ArrayList<Class>();
			ArrayList<Object[]> results;
			String SEARCH_WORK_WORDERS = "SELECT PMA.wo, "
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
										+ "FROM vehicle INNER JOIN PMA ON vehicle.vin = PMA.vin INNER JOIN info ON PMA.id = info.id WHERE ";
			
			
			return null;
		}
		
		
		
		
	}//workOrder
	
	
}//MyCMS	
