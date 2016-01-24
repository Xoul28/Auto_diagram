package auto_BSA;

import java.awt.Graphics2D;
import java.util.Iterator;

public abstract class Cycles extends FunBlock {
	CycleReturning ret;
	public Cycles(Block[] body,String line) {
		super(body,line);
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
		int sum = 7;
		for (int i = 0; i < body.length; i++) {
		 		sum += body[i].getCyclesMargin();
		 }
		return sum;
	}
	
	 public void drawbreak(Coords Coord,Graphics2D g2d){
		 if(Coord.breakingList.size()>0){
		for (Coords iter : Coord.breakingList) {
			if(iter.getX() >= Coord.getextremeRX()-50-getCyclesMargin()){
			g2d.drawLine(iter.getX(), iter.getY(), Coord.getextremeRX(), iter.getY());
			}else{
				g2d.drawLine(iter.getX(), iter.getY(), iter.getX()+6, iter.getY());
				g2d.drawOval(iter.getX()+6, iter.getY()-6, 12, 12);
				
				g2d.drawLine(Coord.getextremeRX()-6, iter.getY(), Coord.getextremeRX(), iter.getY());
				g2d.drawOval(Coord.getextremeRX()-18, iter.getY()-6, 12, 12);
			}
			Coord.breakingList.remove(iter);
		}
		 }
	 }
}