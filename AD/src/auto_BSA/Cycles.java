package auto_BSA;

import java.awt.Graphics2D;

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
	 public boolean isThereABreakInCycleBody() {
		 for (int i = 0; i < body.length; i++) {
			 if(body[i].isBreak() == 1) {
				 return true;
			 }
		 }
		 return false;
	 }
}