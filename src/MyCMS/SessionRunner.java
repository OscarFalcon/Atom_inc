package MyCMS;

public class SessionRunner implements Runnable{

	private Session s;
	
	@Override
	public void run() {
		if(s == null)
			System.out.println("Error");
		else{
			while(true){
				try {
					Thread.sleep(4000);
					s.setValid(false);
			
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setSession(Session s){
		this.s = s;
	}
}
