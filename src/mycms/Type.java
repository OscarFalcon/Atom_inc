package mycms;


public class Type<T> {

	
	public enum Type_T{
		BYTE,
		DATE,
		INTEGER,
		STRING,
		PMA_OBJECT,
		OBJECT;
	}
	
	
	
	private T value;

	public Type(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
	
}
