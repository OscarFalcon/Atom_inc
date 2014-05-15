package MyCMS;

public class Session {

	private String first;
	private String last;
	private String username;
	private boolean valid; 
	
	
	public Session(String first,String last,String username){
		this.first = first;
		this.last = last;
		this.username = username;
		valid = true;
	}
	
	public synchronized boolean isValid(){
		return valid;
	}
	
	public synchronized void setValid(boolean b){
		this.valid = b;
	}
	public String getFirst(){
		return first;
	}
	public String getLast(){
		return last;
	}
	public String getUserName(){
		return username;
	}
	public String toString(){
		return "First = " + first + "Last = " + last + "Username = " + username;
	}
	
	
}
