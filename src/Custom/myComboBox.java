package Custom;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import javax.swing.JComboBox;
import javax.swing.UIManager;

public class myComboBox extends JComboBox{
	
	
	@SuppressWarnings({ "serial", "unchecked" })
	public myComboBox(String[] comments){
		super(comments);
		((JTextField)this.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
		
		
	}
	
}
