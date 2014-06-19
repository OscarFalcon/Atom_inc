package mycms;


public class Type<T> {

	private T value;
	
	public enum Class{
		BYTE,
		DATE,
		INTEGER,
		STRING,
		OBJECT,
		PMA_OBJECT;
	}
	
	
	public Type(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
	
}
