package MyCMS;

public class SessionRunner implements Runnable{

	Session s;
	
	@Override
	public void run() {
		System.out.println("Thread stated");
		System.out.println(s);
		while(true){
			try {
				Thread.sleep(4000);
				s.setValid(false);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void setSession(Session s){
		this.s = s;
	}
}
