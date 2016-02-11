package auto_BSA;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Component extends JPanel{
	private Block function;
	JPanel cp ;
	 public Component(Block function, JPanel cp) {
		 this.function = function;
		 this.cp = cp;
	     setOpaque(false);
	     this.cp.setBackground(Color.white);
	   
	 }
  
	 @Override
	    protected void paintComponent(Graphics g) {
		
		
		 Graphics2D g2d = (Graphics2D)g;
		 
		 g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		 g2d.setColor(Color.black);
		 Block.breakinthelaw = 1;
		 Coords c = new Coords(getWidth()/2,50);
		   BufferedImage image = //(BufferedImage) cp.createImage(1000, 2500);
				   new BufferedImage(1000, 2500, BufferedImage.TYPE_INT_ARGB);
		 Graphics2D g2 = image.createGraphics();
		 
		   g2d.setColor(Color.BLACK);
		   g2.setColor(Color.BLACK);
		   function.paint(c, g2d,true);
		   function.paint(c, g2,true);
		   /*
		   try {
			   Graphics2D gg = (Graphics2D)g;
			   //gg.setFont(gg.getFont().deriveFont(72.0f));
			//MathExpression expression = MathExpressionDrawer.Parse("((pow(5,3/2))/(exp(0.7)+abs(r+2))+1/2/pow(3,3))", gg);
			   MathExpression expression = MathExpressionDrawer.Parse("exp(e)+sqrt(fabs(((sin(y)-x)*(cos(y)+x))/(pow(10,5)*log(3*x))))", gg);
			expression.draw(gg, 500, 500);
		} catch (Exception e) {
			e.printStackTrace();
		} //*/
	   
	     g2d.dispose();
	     try {
	          ImageIO.write(image, "png", new File("MyImg.png"));
	        }
	        catch(IOException io) { io.printStackTrace(); 
	        }
	}
	     
	 }
	

