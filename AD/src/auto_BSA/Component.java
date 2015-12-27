package auto_BSA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Component extends JPanel{
	 public Component() {
	        setOpaque(true);
	 }
  
	 @Override
	    protected void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D)g;
		 g2d.getRenderingHint(RenderingHints.KEY_RENDERING);
		 g2d.setColor(Color.black);
		 Coords c = new Coords(getWidth()/2,getHeight()/2);
	     ForCycle f = new ForCycle();
	     for(int i = 0; i<10;i++){
	     f.paint("ØÒÎ ÒÎ", c, g2d);
	     c.setY(c.getY()+50+5);
	     }
	 }
	
}
