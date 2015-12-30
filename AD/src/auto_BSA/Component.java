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
		 g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		 g2d.setColor(Color.black);
		 Coords c = new Coords(getWidth()/2,50);
		 Block b1[] = new Block [2];
		 Block b3[] = new Block [2];
		 Block b[] = new Block[3];
		 b1[0] = new Statement("Äåéñòâèå 1");
		 b[2] = new Statement("Äåéñòâèå 2");
		 b3[0] = new Statement("Äåéñòâèå 3");
		 b3[1] = new Statement("Äåéñòâèå 4");
		 b[0] = new Statement("Äåéñòâèå 1");
		 b1[0] = new ForCycle(b1, "i>0");
		 b[1] = new ForCycle(b3, "i>0");
		 //b[2] = new Statement("Äåéñòâèå 3");
		 ForCycle f = new ForCycle(b, "i=1...4");
		 Block b2[] = {f};
		 Function fun = new Function(b2, "");
	     fun.paint(c, g2d,true);
	  
	 }
	
}
