package MyCMS;

public class Error {

	private int MyCMSError; 
	private int MySQLError;
	
	public Error(){
		MyCMSError = MyCMS.SUCCESS;
		MySQLError = MyCMS.SUCCESS;
		
	}
	public int getMyCMSError(){
		return MyCMSError;
	}
	public int getMySQLError(){
		return MySQLError;
	}
	public void setMySQLError(int error){
		MySQLError = error;
	}
	public void setMyCMSError(int error){
		MyCMSError = error;
	}
	public void reset(){
		MyCMSError = MyCMS.SUCCESS;
		MySQLError = MySQL.SUCCESS;
	}
	
}

