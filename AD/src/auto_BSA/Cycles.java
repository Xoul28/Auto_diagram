package auto_BSA;

import java.awt.Graphics2D;

public abstract class Cycles extends FunBlock {
	CycleReturning ret;
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
	
     
	 @Override
	public int getCyclesMargin() {
		int sum = 6;
		for (int i = 0; i < body.length; i++) {
		 		sum += body[i].getCyclesMargin();
		 }
		return sum;
	}
}