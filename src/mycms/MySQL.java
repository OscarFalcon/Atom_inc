package mycms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbcp2.BasicDataSource;
import pma.PMAObject;
import mycms.Type.Class;



public class MySQL {

	private static final String DATABASE_URL = "jdbc:mysql://??.???.???.???:3306/?";
	private static final String MySQLUser = "?", MySQLPassword = "?";
	private static Connection connection;
	private static final BasicDataSource dataSource = new BasicDataSource();

    public static int operationStatus; 						/** success if the SQL statement was executed correctly. **/
    public static final int OPERATION_FAILED = -1;			/** operation failed **/
    public static final int OPERATION_SUCCESS = 0;			/** operation success **/
       
    public static int errorno; 									/** describes any errors that could arise as below **/
    public static final int SUCCESS = 0;						/** no error **/
    public static final int CONNECTION_ERROR = 1;		 		/** error, cannot establish connection to server**/
    public static final int NO_INTERNET_CONNECTION_ERROR = 2;	/** Internet is not reachable **/
    public static final int PREPARED_STATEMENT_ERROR = 3; 		/** error in preparing a statement **/
    public static final int RESULT_SET_ERROR = 4;				/** error in operating with resultSet, usually resultSet.get or resultSet.set methods **/
    public static final int CLOSE_CONNECTION_ERROR = 5;			/** error in closing a connection **/
    public static final int IS_CLOSED_CONNECTION_ERROR = 6; 	/** error in calling the isClosed method call for a connection object **/
    public static final int EXECUTE_QUERY_ERROR = 7; 			/** error in the execution of a prepared statement that returns a result set object**/
    public static final int EXECUTE_ERROR = 8;					/** error in execution of a prepared statement that does NOT return a result set object **/
    public static final int OTHER_CLOSE_ERROR = 9;				/** error in the closing of either a prepared statement, or result set object **/
    public static final int INVALID_ARGUMENTS = 10;				/** error in the argument parameters passed in **/
    
	
    
    /** initializes connection pool settings **/
	 static{	
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername(MySQLUser);
		dataSource.setPassword(MySQLPassword);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setInitialSize(1); 	/** sets the initial amount of connections. **/
		dataSource.setMaxTotal(1); 		/**Sets the maximum total number of idle and borrows connections that can be active at the same time. **/
	}
	 
	 
		
	/**
	 * Attempts to get a connection from the connection pool.
	 * @return TRUE: if a connection has already been established and that connection is not closed. Or there is
	 * no current opened connection and a connection was successfully established. FALSE: otherwise
	 * error no is either set to CONNECTION_ERROR or NO_INTERNET_CONNECTION_ERROR
	 */
	private static boolean connect() {
		if(isClosed()){
			try{
				connection = dataSource.getConnection();
			    connection.setAutoCommit(false);
		   }catch (SQLException e){
			   errorno = CONNECTION_ERROR;
			   isInternetReachable(); /** is the server down, or Internet down? **/
			   e.printStackTrace();
			   return false;
		   }
		}
		System.out.println("Connected to database");
		return true;
	}
	
	/**
	 * disconnects from the MySQL database. If connection is already closed or is null, does nothing
	 */
	private static void disconnect(){ 
		if(connection == null)
			return;
		try {
			if(!(connection.isClosed()))
				connection.close();
		} catch (SQLException e1) {
			errorno = CLOSE_CONNECTION_ERROR;
			e1.printStackTrace();
		}
		System.out.println("Disconnected from database");
   }
	
	/**
	 * IMPORTANT this method can return false(indicating that the connection is opened)
	 * even when the connection might be closed. This is due to a SQL exception that 
	 * might be raised.
	 * @return TRUE if the connection has been closed, false otherwise.
	 */
	private static boolean isClosed(){
		boolean value = false;
		
		if(connection == null)
			return true;
		
		try{
			if(connection.isClosed())
				value = true;
			else
				value = false;
		}catch(SQLException e){
			errorno = IS_CLOSED_CONNECTION_ERROR;
			e.printStackTrace();
		}
		return value;
	}
	
	
	
	/**
	 * ASSUMES: that there is a live connection for the passed in prepared statement.
	 * 
	 * helper method that executes a prepared statement that does NOT return a resultSet.
	 * This method does not close or open any connections
	 * 
	 *  **/
	private static boolean execute(PreparedStatement ps) {
		if( ps ==null || isClosed() )
			return false;
		try {
			ps.execute();
			ps.getConnection().commit();
			return true;
		} catch (SQLException e) {
			errorno = EXECUTE_ERROR;
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	* ASSUMES: there is a live connection associated with the passed in prepared statement.
	* 
	* helper method that executes a query from a prepared statement that returns a resultSet
	* This method does not open or close any connections 
	* **/
	private static ResultSet executeQuery(PreparedStatement ps) {
		ResultSet rs = null;
		
		if( ps == null || isClosed())
			return null;
		try {
			rs = ps.executeQuery();
			ps.getConnection().commit();
		} catch (SQLException e) {
			errorno = EXECUTE_QUERY_ERROR;
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	/**
	* 
	* helper method that prepares a statement based on the given string
	* 
	**/
    private static PreparedStatement prepareStatement(String statement){
    	PreparedStatement ps;
    	
    	if(isClosed())
    		return null;
    	
    	try {
			ps = connection.prepareStatement(statement);
		} catch (SQLException e) {
			errorno = PREPARED_STATEMENT_ERROR;
			e.printStackTrace();
			return null;
		}
    	return ps;
    }

    
    
    public static ArrayList<Object[]> executeQuery(String mysql_string,ArrayList<Type<?>> arguments, Class[] result_types){
    	return executeQuery(mysql_string,arguments.toArray(new Type<?>[arguments.size()]),result_types);
    }
    
    
	/**
	* @param mysql_string a valid SQL prepared statement string that represents the query to be performed
	* @param args the arguments that will pair for each '?' in the prepared statement string
	* @param arg_types the argument types of any '?' in preparedStatmentString in the form of a string array(ie 'Integer' if the first argument is of type Integer)
	* @param result_array_types a list of the type specifiers that represent each individual type of the returned object array. 
	* @return an ArrayList of Object[] that represent a result set object 
	*/
    public static ArrayList<Object[]> executeQuery(String mysql_string, Type<?>[] arguments, Class[] result_types){	
		int length;
    	int i;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final ArrayList<Object[]> list = new ArrayList<Object[]>();
		
		errorno = SUCCESS;	/**initially, no errors have occurred **/
		operationStatus = OPERATION_FAILED; /** initially, the SQL statement has not completed **/
		
		if(mysql_string == null || arguments == null|| result_types == null){
			errorno = INVALID_ARGUMENTS;
			return null;
		}
		
		if(!connect()){ /** connect to database  **/
			return null; /** connect method will set errorno **/
		}
		
		if((preparedStatement = prepareStatement(mysql_string)) == null)/** prepare statement for execution, will set errorno **/
			return null;
		
		try{
			Object value;
			length = arguments.length;
			for(i = 0; i < length; i++){	/** loop through args and call the appropriate set method of preparedStatement depending on arg_types **/
				value = arguments[i].getValue();
				
				if(value instanceof Byte)
					preparedStatement.setByte(i+1,(Byte)value);
				else if(value instanceof Date )
					preparedStatement.setDate(i+1, (Date)value);
				else if(value instanceof Integer)
					preparedStatement.setInt(i+1, (Integer)value);
				else if(value instanceof String)
					preparedStatement.setString(i+1,(String)value);
				else if(value instanceof PMAObject || value instanceof Object)
					preparedStatement.setObject(i+1, (Object)value);
				else{
					errorno = INVALID_ARGUMENTS;
					return null;
				}
			}		
			
			if((resultSet = executeQuery(preparedStatement)) == null) /** will set errorno **/
				return null;
			
			operationStatus = OPERATION_SUCCESS; /**at this point the query succeeded **/
						
			length = result_types.length; /** at this point, result_array_types cannot equal null **/
			while(resultSet.next()){
				Object[] tmp = new Object[length];
				for(i = 0; i < length; i++){
					switch(result_types[i]){
						case BYTE:
							tmp[i] = resultSet.getByte(i+1);
							break;
						case DATE:
							tmp[i] = resultSet.getDate(i+1);
							break;
						case INTEGER:
							tmp[i] = resultSet.getInt(i+1);
							break;
						case PMA_OBJECT:
							ObjectInputStream objectIn = null;
							byte[] buf = null;
							Object object = null;	
							buf = resultSet.getBytes(i+1);
							if (buf != null){
						    	objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
								object = objectIn.readObject();
								tmp[i] = object;
							}
							break;
						case OBJECT:
							tmp[i] = resultSet.getObject(i+1);
							break;
						case STRING:
							tmp[i] = resultSet.getString(i+1);
							break;
					}//switch
							
				}//for loop
				list.add(tmp);
			}//while
		}catch(SQLException e){
			e.printStackTrace();
			errorno = RESULT_SET_ERROR;
			return null;
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
				if(preparedStatement != null)
					preparedStatement.close();
				disconnect();
			}
			catch(SQLException e){
				errorno = OTHER_CLOSE_ERROR;
				e.printStackTrace();
			}
		}
		return list;
	}
    
    public static boolean execute(String mysql_string,ArrayList<Type<?>> arguments){
    	return execute(mysql_string,arguments.toArray(new Type<?>[arguments.size()]));
    }
    
    
    
	public static boolean execute(String mysql_string, Type<?>[] arguments){
		int length;	
		int i;
			PreparedStatement preparedStatement = null;
			
			errorno = SUCCESS;	/**initially, no errors have occurred **/
			operationStatus = OPERATION_FAILED; /** initially, the SQL statement has not completed **/
			
			if(mysql_string == null || arguments == null){
				System.out.println("Invalid Arguments");
				errorno = INVALID_ARGUMENTS;
				return false;
			}
			
			if(!connect()){ /** connect to database  **/
				return false; /** connect method will set errorno **/
			}
			if((preparedStatement = prepareStatement(mysql_string)) == null)//prepare statement for execution
				return false;
			
			try{
				length = arguments.length;
				Object value;
				for(i = 0; i < length; i++){	/** loop through args and call the appropriate set method of preparedStatement depending on arg_types **/
					value = arguments[i].getValue();
					
					
					if(value instanceof Byte)
						preparedStatement.setByte(i+1,(Byte)value);
					else if(value instanceof Date )
						preparedStatement.setDate(i+1, (Date)value);
					else if(value instanceof Integer)
						preparedStatement.setInt(i+1, (Integer)value);
					else if(value instanceof String)
						preparedStatement.setString(i+1,(String)value);
					else if(value instanceof PMAObject || value instanceof Object)
						preparedStatement.setObject(i+1, (Object)value);
					else{
						errorno = INVALID_ARGUMENTS;
						return false;
					}
				}
				
				if(execute(preparedStatement) == false)
					return false;
				
				operationStatus = OPERATION_SUCCESS; /**at this point the query succeeded **/
				
			}catch(SQLException e){
				e.printStackTrace();
				errorno = RESULT_SET_ERROR;
				return false;
			}
			finally{
				try{
					if(preparedStatement != null)
						preparedStatement.close();
					disconnect();
				}
				catch(SQLException e){
					errorno = OTHER_CLOSE_ERROR;
					e.printStackTrace();
				}
			}
		return true;
	}
	private static boolean isInternetReachable(){
        try {
        	URL url = new URL("http://www.google.com");/** make a URL to a known source **/
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();/** //open a connection to that source **/
            @SuppressWarnings("unused")
			Object objData = urlConnect.getContent(); /** trying to retrieve data from the source. If there is no connection, this line will fail **/
        } catch (IOException e1) {
        	errorno = NO_INTERNET_CONNECTION_ERROR;
            return false;
        }
        return true;
    }
}
