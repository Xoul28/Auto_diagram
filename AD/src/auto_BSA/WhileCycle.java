package auto_BSA;

import java.awt.Graphics2D;

public class WhileCycle extends Cycles{
	  
		public WhileCycle(Block[] body, String line) {
			super(body, line);
			ret  = new WhileCycleReturning();
		}

		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
		      //drawing cycle body
			 
			 //top
			   //lefttopline
			 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
			   //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY());
			  //rightbottomline
			 g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
			  //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT, Coord.getY());
			 
			 drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
			 
			 nextArrow(Coord, g2d);
		
			 Coords retCoord = new Coords(Coord.getX(),Coord.getY());
			 retCoord.setextremeLX(retCoord.getX()-50);
			 retCoord.setextremeRX(retCoord.getX()+50);
			 Coord.setextremeRX(retCoord.getX()+50);
			 
			 Coord.setY(Coord.getY()+50+20);
			 Coord.setextremeDY(Coord.getextremeDY()+50+20);
			 //отрисовываем тело
			 drawbody(Coord, g2d);
			 Coord.setextremeRX(Coord.getextremeRX()+getCyclesMargin()+getwidth(Coord));
			  Coord.setextremeLX(Coord.getextremeLX()-getCyclesMargin());
			  Coord.setextremeDY(Coord.getextremeDY()+5);
			  
		     ret.returning(Coord, retCoord, g2d);   
		     
		     Coord.setextremeDY(Coord.getextremeDY()+10);
			  Coord.setextremeLX(Coord.getextremeLX()+getCyclesMargin());
			  Coord.setextremeRX(Coord.getX()+50);
		}
	
	}