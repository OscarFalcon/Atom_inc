package CustMgtSys_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PMAController implements ActionListener {
	
	private PMAview view;
	private boolean[][] isChecked;
	private String[][] comboBoxValues;
	
	public PMAController(PMAview view){
		this.view = view;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Submit")){
			for(int i =0; i < view.checkBoxes.length; i++){
				if(view.checkBoxes[i][0].isSelected()){
					isChecked[i][0] = true;
					isChecked[i][1] = false;
				}else if(view.checkBoxes[i][1].isSelected()){
					isChecked[i][0] = false;
					isChecked[i][1] = true;
				}
			}
			for(int i = 0; i < view.comboBoxes.length; i++){
				for(int j =0; j < 3; j++){
					String item = (String) view.comboBoxes[i][j].getSelectedItem();
					comboBoxValues[i][j] = item;
				}
			}
		}	
	}
}