package CustMgtSys_1;


public class PMA {
	public static void main(String args[]){
		PMAview view = new PMAview();
		PMAController controller = new PMAController(view);
		
		view.submit.addActionListener(controller);
		
	}
}