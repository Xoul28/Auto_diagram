package auto_BSA;

import java.awt.Graphics2D;

public class DoWhileCycleReturning implements CycleReturning{

	 public void returning(Coords Coord,Coords retCoord,Graphics2D g2d) {
		  //left
		  g2d.drawLine(Coord.getX()-50,Coord.getextremeDY()-45,Coord.getextremeLX(),Coord.getextremeDY()-45);
		  //arrows
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY()-45,Coord.getextremeLX()+2, Coord.getextremeDY()-43);
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY()-45, Coord.getextremeLX()+2, Coord.getextremeDY()-47);
		  //up
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY()-45,Coord.getextremeLX(),retCoord.getY()-32);
		  //arrows
		  g2d.drawLine(Coord.getextremeLX(), retCoord.getY()-32, Coord.getextremeLX()-2, retCoord.getY()-29);
		  g2d.drawLine(Coord.getextremeLX(), retCoord.getY()-32, Coord.getextremeLX()+2, retCoord.getY()-29);
		//right
		  g2d.drawLine(Coord.getextremeLX(),retCoord.getY()-32,retCoord.getX(),retCoord.getY()-32);

	  }
}
