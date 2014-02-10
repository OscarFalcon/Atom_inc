package PMA;


public class PMA {
	public static void main(String args[]){
		PMAView view = new PMAView();
		PMAController controller = new PMAController(view);
		
		view.submit.addActionListener(controller);
		
	}
}
