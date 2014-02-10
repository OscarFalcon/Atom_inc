package Custom;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;



public class JLButton extends JButton{

   
        private static final long serialVersionUID = 1L;
   
        private Color hoverBackgroundColor = new Color(81,127,164);
       
        private Color pressedBackgroundColor = new Color(255,128,0);
        
        private boolean selected = false;
     
        public JLButton(String text) {
            super(text);
            super.setContentAreaFilled(false);
            
            Border border = BorderFactory.createEmptyBorder(0,0,0,0);
            
    		setBorder(border);
    		setBorderPainted(false);
    		setContentAreaFilled(false);
    		setHorizontalAlignment(SwingConstants.LEFT);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        
        public void setSelected(boolean isSelect){
        	this.selected = isSelect;
        }
        
        
        
        @Override
        public boolean isSelected(){
			return selected;
        }
        
        
        @Override
        public void setContentAreaFilled(boolean b) {
        }

        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
    }