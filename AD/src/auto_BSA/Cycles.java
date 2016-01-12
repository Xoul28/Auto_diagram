package auto_BSA;

import java.awt.Graphics2D;

public abstract class Cycles extends FunBlock {
	
	public Cycles(Block[] body,String line) {
		super(body,line);
	}

	@Override
	public void drawbody(Coords Coord,Graphics2D g2d) {
		 for(int i = 0 ; i<body.length ; i++) {
			 if(i!=(body.length-1)) {
			 body[i].paint(Coord,g2d,true); 
			 Coord.setY(Coord.getY()+50+20);
			 Coord.setY(Coord.getextremeDY()+25);
			 Coord.setextremeDY(Coord.getextremeDY()+50);
			 
			 }else {
			 body[i].paint(Coord,g2d,false);	 
			 //Coord.setextremeDY(Coord.getextremeDY()-20);
			 }
		 }	 
	}
	@Override
	public int getwidth(Coords Coord) {
		int width=0;
 		for (int i = 0; i < body.length; i++) {
 			if(body[i].getwidth(Coord)>width)
 				width=body[i].getwidth(Coord);	
 		}
 		return width;
	  }
	 public void returning(Coords Coord,Coords retCoord,Graphics2D g2d) {

		  Coord.setextremeRX(Coord.getextremeRX()+getCyclesMargin()+getwidth(Coord));
		  Coord.setinvestedCol(0);
		  Coord.setextremeLX(Coord.getextremeLX()-getCyclesMargin());
		  Coord.setextremeDY(Coord.getextremeDY()+5);
		  //рисуем линию вниз
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()-5);
		  //влево
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getextremeLX(),Coord.getextremeDY());
		  //вверх
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY(),Coord.getextremeLX(),retCoord.getY());
		  
		  Coord.setextremeDY(Coord.getextremeDY()+5);
		//вправо к блоку цикла
		  g2d.drawLine(Coord.getextremeLX(),retCoord.getY(),retCoord.getextremeLX(),retCoord.getY());
		//вправо от блока цикла 
		  g2d.drawLine(retCoord.getextremeRX(),retCoord.getY(),Coord.getextremeRX(),retCoord.getY());
		//вниз 
		  g2d.drawLine(Coord.getextremeRX(),retCoord.getY(),Coord.getextremeRX(),Coord.getextremeDY());	
		//влево 
		  g2d.drawLine(Coord.getextremeRX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY());	
		//вниз
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()+10);
		  
		  Coord.setextremeDY(Coord.getextremeDY()+10);
		  Coord.setextremeLX(Coord.getextremeLX()+getCyclesMargin());
		  Coord.setextremeRX(Coord.getX()+50);
		 // retCoord.setextremeDY(Coord.getextremeDY());
		//  Coord.setextremeRX(Coord.getextremeRX()-50+getcyc());
	  }
     
	 @Override
		public int getCyclesMargin() {
			int sum = 6;
			for (int i = 0; i < body.length; i++) {
	 			sum += body[i].getCyclesMargin();
	 		}
			return sum;
		}
}