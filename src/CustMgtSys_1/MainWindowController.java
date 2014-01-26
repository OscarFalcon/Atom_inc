package CustMgtSys_1;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the controller for the MainWindowView, controls all intractable fields and buttons
 * 
 * @author Oscar & Adrian
 *
 */


public class MainWindowController implements ChangeListener, ActionListener{

	private MainWindowView view;

	boolean enter = false;

	public MainWindowController(MainWindowView view)  {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == view.about){
			JOptionPane.showMessageDialog(view.about,
					"For an indepth how-to please refer to the MyCMS manual");	
		}
		else if (event.getSource() == view.logout || event.getSource() == view.logoutButton){
			view.switchPanels(1);
		}
		else if(event.getSource() == view.lockScreenButton){
			enter = false;

			view.setEnabled(false);
			view.repaint();
			view.revalidate();
			while(!enter){
				new LockScreen(this);	
			}
		
			view.setEnabled(true);
			UIManager.put("control", null);
			view.repaint();
			view.revalidate();
		}
		
	}

	public void setEnter(boolean b){
		enter = b;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane source = (JTabbedPane)e.getSource();
		
		switch( source.getSelectedIndex() ){
			case -1: 
				break;
			case 0:
				view.remove(view.currentPanel);
				view.add(view.customerPanel);
				view.repaint();
				view.revalidate();
				view.pack();
				view.currentPanel = view.customerPanel;
				break;
			case 1: 
				view.remove(view.currentPanel);
				view.add(view.WorkOrderPanel);
				view.repaint();
				view.revalidate();
				view.pack();
				view.currentPanel = view.WorkOrderPanel;
				break;
			case 2:
				view.remove(view.currentPanel);
				view.add(view.PMAPanel);
				view.repaint();
				view.revalidate();
				view.pack();
				view.currentPanel = view.PMAPanel;
				break;
			default:
		}
		
	}

}