package auto_BSA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Cycle extends JPanel{
    String line;
	 public Cycle(String line) {
		 this.line=line;
	        setOpaque(true);
	    }
	 @Override
	    protected void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D)g;
		 g2d.getRenderingHint(RenderingHints.KEY_RENDERING);
		 g2d.setColor(Color.black);
		 int lenh=35,lenv=10,lenhn=50,lenvn=25;
		 //top
		 g2d.drawLine(getWidth()/2-lenh, getHeight()/2-lenvn, getWidth()/2+lenh, getHeight()/2-lenvn);
		 //leftupangle
		 g2d.drawLine(getWidth()/2-lenhn, getHeight()/2-lenv, getWidth()/2-lenh, getHeight()/2-lenvn);
		//righttopangle
		 g2d.drawLine(getWidth()/2+lenh, getHeight()/2-lenvn, getWidth()/2+lenhn, getHeight()/2-lenv);
		//leftdownangle
		 g2d.drawLine(getWidth()/2-lenhn, getHeight()/2+lenv, getWidth()/2-lenh, getHeight()/2+lenvn);
		//rightdownangle
		 g2d.drawLine(getWidth()/2+lenh, getHeight()/2+lenvn, getWidth()/2+lenhn, getHeight()/2+lenv);
		 //bottom
		 g2d.drawLine(getWidth()/2-lenh, getHeight()/2+lenvn, getWidth()/2+lenh, getHeight()/2+lenvn);
		 //left
		 g2d.drawLine(getWidth()/2-lenhn, getHeight()/2-lenv, getWidth()/2-lenhn, getHeight()/2+lenv);
		 //right
		 g2d.drawLine(getWidth()/2+lenhn, getHeight()/2-lenv, getWidth()/2+lenhn, getHeight()/2+lenv);
		 g2d.drawString(line, getWidth()/2-32, getHeight()/2+5);
	 }
	
}