package MyCMS;

public class Type {

	public static final int STRING = 0;
	public static final int INTEGER = 1;
	public static final int OBJECT = 2;
	
	private Object value;
	private int type;
	
	public Type(String S){
		value = S;
		type = STRING;
	}
	public Type(Integer I){
		value = I;
		type = INTEGER;
	}
	public Type(Object O){
		value = O;
		type = OBJECT;
	}
	public Object getValue(){
		return value;
	}
	public int getType(){
		return type;
	}
}