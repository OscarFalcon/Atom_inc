package PMA;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import CustMgtSys_1.Security;
import MyCMS.PMAObject;

public class PMAController {

	private PMAView view;
	
	
	public PMAController(PMAView view) {
		this.view = view;
		view.submit.addActionListener(new listen());
	}
	

	public void writeToSQL() {
		PMAObject pma = new PMAObject();
		
		pma.tags = view.tagsField.getText();
		pma.miles = view.milesField.getText();
		
		for(int i = 0; i < 42; i++){
			
			if(view.buttonLabels[i].isSelected()) //does this work?
				pma.approved[i] = 1;
			else
				pma.approved[i] = 0;
			
			if(view.checkBoxes[i][0].isSelected())
				pma.ok[i] = 1;
			else
				pma.ok[i] = 0;
			
			if(view.checkBoxes[i][1].isSelected())
				pma.notok[i] = 1;
			else
				pma.notok[i] = 0;
			//if(approved)
			
			pma.tech_comments[i] = (String) view.comboBoxes[i][0].getEditor().getItem();
			pma.recommended_repairs[i] = (String) view.comboBoxes[i][1].getEditor().getItem();
			pma.priority[i] = view.comboBoxes[i][2].getSelectedIndex();
			pma.totalParts[i] = new BigDecimal( view.totalFields[i][0].getValue()); //is it safe
			pma.totalLabor[i] = new BigDecimal( view.totalFields[i][1].getValue());
			pma.qty[i] = new Integer(view.QTYfields[i].getText());
			pma.partCost[i] = new BigDecimal(view.numberFields[i][0].getValue());
			//pma.laborCost[i] = new BigDecimal(view.numberFields[i][0].getValue()); ** need to fix
			pma.vendor[i] = view.vendorFields[i].getText();
	
		}	


		
		Security.PMA.updatePMA(pma,view.wo);
	

	}
	
	public void readSQL(){
		PMAObject pma = null;
		try {
			pma = (PMAObject) Security.PMA.loadPMA(view.wo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.tagsField.setText(pma.tags);
		for(int i = 0; i < 42; i++){
			if(pma.ok[i] == 1){
				view.checkBoxes[i][0].setSelected(true);
				view.checkBoxes[i][0].setBackground(Color.green);
			}
			if(pma.notok[i] == 1){
				view.checkBoxes[i][1].setSelected(true);
				view.checkBoxes[i][1].setBackground(Color.red);
			}
			view.comboBoxes[i][0].getEditor().setItem(pma.tech_comments[i]); 
			view.comboBoxes[i][1].getEditor().setItem(pma.recommended_repairs[i]);
			view.comboBoxes[i][2].setSelectedIndex(pma.priority[i]);
			view.totalFields[i][0].setValue(pma.totalParts[i].doubleValue());
			view.totalFields[i][1].setValue(pma.totalLabor[i].doubleValue());
			view.QTYfields[i].setText(String.valueOf(pma.qty[i]));
			view.numberFields[i][0].setValue(pma.partCost[i].doubleValue());
			//view.numberFields[i][1].setValue(pma.laborCost[i].doubleValue());
			view.vendorFields[i].setText(pma.vendor[i]);

		}
	
	}
	
	private class listen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			writeToSQL();
			System.out.println("write to sql");
		}	
	}
	
	
}
