package Auto_BSA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SelectorBlock extends JPanel{
    String line;
	 public SelectorBlock(String line) {
		 this.line=line;
	        setOpaque(true);
	    }
	 @Override
	    protected void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D)g;
		 g2d.getRenderingHint(RenderingHints.KEY_RENDERING);
		 g2d.setColor(Color.black);
		 int lenh=35,lenv=10,lenhn=50,lenvn=25;
	     //lefttopline
		 g2d.drawLine(getWidth()/2-lenhn, getHeight()/2, getWidth()/2, getHeight()/2+lenvn);
		   //righttopline
		 g2d.drawLine(getWidth()/2, getHeight()/2-lenvn, getWidth()/2+lenhn, getHeight()/2);
		  //rightbottomline
		 g2d.drawLine(getWidth()/2+lenhn, getHeight()/2, getWidth()/2, getHeight()/2+lenvn);
		  //righttopline
		 g2d.drawLine(getWidth()/2, getHeight()/2-lenvn, getWidth()/2-lenhn, getHeight()/2);
		 g2d.drawString(line, getWidth()/2-32, getHeight()/2+5);
	 }
	
}