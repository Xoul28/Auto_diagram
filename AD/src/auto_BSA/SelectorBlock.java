package auto_BSA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SelectorBlock extends FunBlock{
    
	 
	 
	 public SelectorBlock(Block[] body, String line) {
		super(body, line);
		
	}
		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
			int lenh=35,lenv=10,lenhn=50,lenvn=25;
		     //lefttopline
			 g2d.drawLine(Coord.getX()-lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			   //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()+lenhn, Coord.getY());
			  //rightbottomline
			 g2d.drawLine(Coord.getX()+lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			  //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()-lenhn, Coord.getY());
			 drawCenteredString(g2d, line, Coord.getX(), Coord.getY());		
			 nextArrow(Coord, g2d);
			 Coord.setY(Coord.getY()+50+20);
			 drawbody(Coord, g2d);
			 
		}
		
	 
	
}