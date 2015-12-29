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
		public void paint( Coords Coord, Graphics2D g2d) {
			int lenh=35,lenv=10,lenhn=50,lenvn=25;
		     //lefttopline
			 g2d.drawLine(Coord.getX()-lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			   //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()+lenhn, Coord.getY());
			  //rightbottomline
			 g2d.drawLine(Coord.getX()+lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			  //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()-lenhn, Coord.getY());
			 g2d.drawString(line, Coord.getX()-32, Coord.getY()+5);		
			 nextArrow(Coord, g2d);
			 Coord.setY(Coord.getY()+50+10);
			 for(int i = 0;i<body.length;i++){
			 body[i].paint(Coord,g2d);
			 }
			 
		}
		
	 
	
}