package MyCMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbcp2.BasicDataSource;



public class MySQL {

	private static final String DATABASE_URL = "jdbc:mysql://24.153.253.126/customers";
	private static final String MySQLUser = "foo", MySQLPassword = "foobar159";
	private static Connection connection;
	private static final BasicDataSource dataSource = new BasicDataSource();
	
    
	
    public static int operationStatus;//success if the SQL statement was executed correctly. 
    public static final int OPERATION_FAILED = -1;			/** operation failed **/
    public static final int OPERATION_SUCCESS = 0;			/** operation success **/
    
    
    public static int errorno; //describes any errors that could arise
    public static final int SUCCESS = 0;					/** no error **/
    public static final int CONNECTION_ERROR = 1;		 	/** error, cannot establish connection **/
    public static final int PREPARED_STATEMENT_ERROR = 2; 	/** error in preparing a statement **/
    public static final int RESULT_SET_ERROR = 3;			/** error in operating with resultSet, usually resultSet.get or resultSet.set methods **/
    public static final int CLOSE_CONNECTION_ERROR = 4;		/** error in closing a connection **/
    public static final int IS_CLOSED_CONNECTION_ERROR = 5; /** error in calling the isClosed method call for a connection object **/
    public static final int EXECUTE_QUERY_ERROR = 6; 		/** error in the execution of a prepared statement that returns a result set object**/
    public static final int EXECUTE_ERROR = 7;				/** error in execution of a prepared statement that does NOT return a result set object **/
    public static final int OTHER_CLOSE_ERROR = 8;			/** error in the closing of either a prepared statement, or result set object **/
    public static final int INVALID_ARGUMENTS = 9;			/** error in the argument parameters passed in **/
    
	/**
	 * This method MUST be called before any other method in the class. 
	 * Initializes connection pool settings.
	 */
	 static{
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername(MySQLUser);
		dataSource.setPassword(MySQLPassword);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setInitialSize(1); //sets the initial amount of connections.
		dataSource.setMaxTotal(1); ///Sets the maximum total number of idle and borrows connections that can be active at the same time.
	}
		
	/**
	 * Attempts to get a connection from the connection pool.
	 * @return TRUE: if a connection has already been established and that connection is not closed. Or there is
	 * no current opened connection and a connection was successfully established. FALSE: otherwise
	 */
	private static boolean connect() {
		if(isClosed()){
			try{
				connection = dataSource.getConnection();
			}catch(SQLException e){
				errorno = CONNECTION_ERROR;
				e.printStackTrace();
				return false;
			}
	    	try{
			   connection.setAutoCommit(false);
		   }catch (SQLException e){
			   errorno = CONNECTION_ERROR;
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

	/**
	 * @param preparedStatementString a valid SQL prepared statement string that represents the query to be performed
	 * @param args the arguments that will pair for each '?' in the prepared statement string
	 * @param arg_types the argument types of any '?' in preparedStatmentString in the form of a string array(ie 'Integer' if the first argument is of type Integer)
	 * @param result_array_types a list of the type specifiers that represent each individual type of the returned object array. 
	 * @return an ArrayList of Object[] that represent a result set object 
	 */
	
	
	public static ArrayList<Object[]> executeQuery(String preparedStatementString, Object[] args, String[] arg_types, String[] result_array_types){	
		int i;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final ArrayList<Object[]> list = new ArrayList<Object[]>();
		
		errorno = SUCCESS;	/**initially, no errors have occurred **/
		operationStatus = OPERATION_FAILED; /** initially, the SQL statement has not completed **/
		
		if(preparedStatementString == null || args == null || arg_types == null || result_array_types == null){
			errorno = INVALID_ARGUMENTS;
			return null;
		}
		if(args.length != arg_types.length){/** for each element in args there must be an appropriate type specifier in arg_types **/
			errorno = INVALID_ARGUMENTS;
			return null;
		}
		if(!connect()){ /** connect to database  **/
			return null; /** connect method will set errorno **/
		}
		if((preparedStatement = prepareStatement(preparedStatementString)) == null)/** prepare statement for execution, will set errorno **/
			return null;
		
		final int length = result_array_types.length; /** at this point, result_array_types cannot equal null **/
		final Object[] tmp = new Object[length];
		
		try{
			for(i = 0; i < args.length;i++){	/** loop through args and call the appropriate set method of preparedStatement depending on arg_types **/
				switch( arg_types[i] ){
					case "Integer":
						preparedStatement.setInt(i+1,(Integer)args[i]);
						break;
					case "Object":
						preparedStatement.setObject(i+1, (Object)args[i]);
						break;
					case "String":
						preparedStatement.setString(i+1,(String)args[i]);
						break;
					default:
						errorno = INVALID_ARGUMENTS;
						return null;
				}
			}		
			if((resultSet = executeQuery(preparedStatement)) == null) /** will set errorno **/
				return null;
			
			operationStatus = OPERATION_SUCCESS; /**at this point the query succeeded **/
						
			i = 0;
			while(i < length && resultSet.next() ){
				for(int j = 0; j < length; j++){
					switch(result_array_types[i]){
						case "Integer":
							tmp[i] = resultSet.getInt(i+1);
							break;
						case "Object":
							tmp[i] = resultSet.getObject(i+1);
							break;
						case "String":
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
	public static boolean execute(String preparedStatementString, Object[] args, String[] arg_types){
			int i;
			PreparedStatement preparedStatement = null;
			
			errorno = SUCCESS;	/**initially, no errors have occurred **/
			operationStatus = OPERATION_FAILED; /** initially, the SQL statement has not completed **/
			
			if(preparedStatementString == null || args == null || arg_types == null){
				errorno = INVALID_ARGUMENTS;
				return false;
			}
			if(args.length != arg_types.length){/** for each element in arg there must be an appropriate type specifier in arg_types **/
				errorno = INVALID_ARGUMENTS;
				return false;
			}
			if(!connect()){ /** connect to database  **/
				return false; /** connect method will set errorno **/
			}
			if((preparedStatement = prepareStatement(preparedStatementString)) == null)//prepare statement for execution
				return false;
			try{
				for(i = 0; i < args.length;i++){	/** loop through args and call the appropriate set method of preparedStatement depending on arg_types **/
					switch( arg_types[i] ){
						case "Integer":
							preparedStatement.setInt(i+1,(Integer)args[i]);
							break;
						case "Object":
							preparedStatement.setObject(i+1, (Object)args[i]);
							break;
						case "String":
							preparedStatement.setString(i+1,(String)args[i]);
							break;
						default:
							System.out.println("Invalid type argument");
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

}
