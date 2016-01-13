package auto_BSA;

import java.awt.Graphics2D;

public class ForCycleReturning implements CycleReturning{

	public void returning(Coords Coord,Coords retCoord,Graphics2D g2d) {
		  //down
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()-5);
		  //left
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getextremeLX(),Coord.getextremeDY());
		  //arrows
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY(),Coord.getextremeLX()+2, Coord.getextremeDY()-2);
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY(), Coord.getextremeLX()+2, Coord.getextremeDY()+2);
		  //up
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY(),Coord.getextremeLX(),retCoord.getY());
		  //arrows
		  g2d.drawLine(Coord.getextremeLX(), retCoord.getY(), Coord.getextremeLX()-2, retCoord.getY()+3);
		  g2d.drawLine(Coord.getextremeLX(), retCoord.getY(), Coord.getextremeLX()+2, retCoord.getY()+3);
		  Coord.setextremeDY(Coord.getextremeDY()+5);
		//right to block
		  g2d.drawLine(Coord.getextremeLX(),retCoord.getY(),retCoord.getextremeLX(),retCoord.getY());
		  //right from block
		  g2d.drawLine(retCoord.getextremeRX(),retCoord.getY(),Coord.getextremeRX(),retCoord.getY());
		//down
		  g2d.drawLine(Coord.getextremeRX(),retCoord.getY(),Coord.getextremeRX(),Coord.getextremeDY());	
		//left
		  g2d.drawLine(Coord.getextremeRX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY());	
		  //arrows
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(), Coord.getX()+2, Coord.getextremeDY()-2);
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(), Coord.getX()+2, Coord.getextremeDY()+2);
		//down
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()+10);
	
	  }

}
