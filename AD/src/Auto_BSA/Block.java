package auto_BSA;

import java.awt.*;
import javax.swing.*;

public class Block extends JPanel{
     String line;
	 public Block(String line) {
		 this.line=line;
	        setOpaque(true);
	    }
	 @Override
	    protected void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D)g;
		 g2d.getRenderingHint(RenderingHints.KEY_RENDERING);
		 g2d.setColor(Color.black);
		 g2d.drawLine(getWidth()/2-50, getHeight()/2-25, getWidth()/2+50, getHeight()/2-25);
		 g2d.drawLine(getWidth()/2-50, getHeight()/2+25, getWidth()/2+50, getHeight()/2+25);
		 g2d.drawLine(getWidth()/2-50, getHeight()/2-25, getWidth()/2-50, getHeight()/2+25);
		 g2d.drawLine(getWidth()/2+50, getHeight()/2-25, getWidth()/2+50, getHeight()/2+25);
		 g2d.drawString(line, getWidth()/2-32, getHeight()/2+5);
	 }
	
}
