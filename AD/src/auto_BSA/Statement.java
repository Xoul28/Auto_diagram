package auto_BSA;

import java.awt.*;

import javax.swing.*;

public class Statement extends BSblock{

	 public Statement(String line, int pos) {
		super(line, pos);
	}

	@Override
	    protected void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D)g;
		 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 g2d.setColor(Color.black);
		 g2d.drawLine(getWidth()/2-50, pos-25, getWidth()/2+50, pos-25);
		 g2d.drawLine(getWidth()/2-50, pos+25, getWidth()/2+50, pos+25);
		 g2d.drawLine(getWidth()/2-50, pos-25, getWidth()/2-50, pos+25);
		 g2d.drawLine(getWidth()/2+50, pos-25, getWidth()/2+50, pos+25);
		 g2d.drawString(line, getWidth()/2-32, pos+5);
	 }

	@Override
	public int getBlockHeight() {
		return 50;
	}


	
}
