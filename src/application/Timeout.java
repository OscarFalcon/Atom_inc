package application;


public class Timeout implements Runnable {

	private LoginController controller;
	private long time;
	
	
	public void run(){
		try{
			Thread.sleep(time);
			controller.setTimeOutStatus(false);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void setTimeout(LoginController controller,long time){
		this.controller = controller;
		this.time = time;
	}
	

}
