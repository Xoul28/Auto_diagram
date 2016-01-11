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

		 Block b6[] = new Block[2];
		 b6[0]= new Statement("јй дири ри");
		 b6[1]= new Statement("јй дири ри");
		 Block b5[] = new Block[0];
//		 b5[0]=new SelectorBlock(b6,"опа",b6);
//		 b5[1]= new SelectorBlock(b6,"опа",b6);
//		 b5[2]= new SelectorBlock(b6,"опа",b6);
		 Block b4[] = new Block[1];
		 b4[0]=  new SelectorBlock(b6,"опа",b6);
		// b4[1]= new SelectorBlock(b6,"опа",b6);
		 
		 Block b2[] = new Block[2];
	     b2[0]= new Statement("јй дири ри");
	     b2[1]= new SelectorBlock(b6,"опа",b6);
	     
	   
	     
	     Block b1[] = new Block[3];
	     b1[0]= new SelectorBlock(b5,"опа",b6);
	     b1[1]= new SelectorBlock(b5,"опа",b6);
	    b1[2]=  new SelectorBlock(b4,"опа",b5);
	     Block b3[] = {new ForCycle(b1,"опа")};
		 Function fun = new Function(b3, "");

	     fun.paint(c, g2d,true);
	  
	 }
	
}
