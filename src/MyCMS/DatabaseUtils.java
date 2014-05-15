package MyCMS;

import java.security.SecureRandom;

public class DatabaseUtils {

	
	public static class Password{
	  
	    public static final int SALT_BYTE_SIZE = 24;
	
	    public static byte[] generateSalt() {
	        SecureRandom random = new SecureRandom();
	        byte bytes[] = new byte[SALT_BYTE_SIZE];
	        random.nextBytes(bytes);
	        return bytes;
	    }
	    
	    public static String bytetoString(byte[] input) {	
	        return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
	    } 
	    public static String computeHash(){
	    	
	    	
	    	return null;
	    }
	    
	    
	    
	    
	    

	}    
	public static void main(String args[]){
		for(int i = 0; i < 10000 ; i ++ ){
			byte[] bytes = Password.generateSalt();
			System.out.println(Password.bytetoString(bytes));
		}
	}
	   
	  
	   
	
	
	
}
