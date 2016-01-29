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
	
	 public void drawbreak(Coords Coord,Graphics2D g2d,int y){
		 
	
		for (Coords iter : Coord.breakingList) {
			if(y<iter.getY()){
				
				if(iter.getX() >= Coord.getextremeRX()-50-getCyclesMargin()-breakMargin()){
					g2d.drawLine(iter.getX(), iter.getY(), Coord.getextremeRX(), iter.getY());
				}else{
					
					g2d.drawLine(iter.getX(), iter.getY(), iter.getX()+6, iter.getY());
					g2d.drawOval(iter.getX()+6, iter.getY()-12, 25, 25);
					drawCenteredString(g2d, Block.breakinthelaw.toString(), iter.getX()+18, iter.getY());
					int m = 0;
					for (Coords iter_f : Coord.breakingList) {
						if(iter.getY()==iter_f.getY()&& iter.getX()!=iter_f.getX()){
							m-=27;
						}
					}
					iter.setY(iter.getY()+m);
					
					drawCenteredString(g2d, Block.breakinthelaw.toString(), Coord.getextremeRX()-18, iter.getY());
					g2d.drawLine(Coord.getextremeRX()-6, iter.getY(), Coord.getextremeRX(), iter.getY());
					g2d.drawOval(Coord.getextremeRX()-31, iter.getY()-12, 25, 25);
				}	
				Block.breakinthelaw =Block.breakinthelaw.intValue()+ 1;
				}
			}
		
		for (Coords iter : Coord.breakingList) {
			if(y<iter.getY()){
			Coord.breakingList.remove(iter);
			}
		}	 
	 }
	 public void drawContinue(Coords Coord,Graphics2D g2d,int y){
		 
			
		for (Coords iter : Coord.continueList) {
			if(y<iter.getY()){
				
				if(iter.getX() <= Coord.getextremeLX()+50+getCyclesMargin()+continueMargin()){
					g2d.drawLine(iter.getX(), iter.getY(), Coord.getextremeLX(), iter.getY());
				}else{
					
					g2d.drawLine(iter.getX(), iter.getY(), iter.getX()-6, iter.getY());
					g2d.drawOval(iter.getX()-6, iter.getY()-12, 25, 25);
					drawCenteredString(g2d, Block.breakinthelaw.toString(), iter.getX()-18, iter.getY());
					int m = 0;
					for (Coords iter_f : Coord.continueList) {
						if(iter.getY()==iter_f.getY()&& iter.getX()!=iter_f.getX()){
							m-=27;
						}
					}
					iter.setY(iter.getY()+m);
					
					drawCenteredString(g2d, Block.breakinthelaw.toString(), Coord.getextremeLX()+18, iter.getY());
					g2d.drawLine(Coord.getextremeLX()+6, iter.getY(), Coord.getextremeLX(), iter.getY());
					g2d.drawOval(Coord.getextremeLX()+31, iter.getY()-12, 25, 25);
				}	
				Block.breakinthelaw =Block.breakinthelaw.intValue()+ 1;
				}
			}
		
		for (Coords iter : Coord.continueList) {
			if(y<iter.getY()){
			Coord.continueList.remove(iter);
			}
		}	 
	 }
}