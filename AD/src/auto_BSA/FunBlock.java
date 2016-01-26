package auto_BSA;

import java.awt.Graphics2D;

public abstract class FunBlock extends Block{
	
	public FunBlock(Block[] body,String line){
		this.body = body;
		this.line = line;
	}
    Block body[];

	public void drawbody(Coords Coord,Graphics2D g2d) {
		 for(int i = 0 ; i < body.length ; i++) {
			 if(i!=(body.length-1)) {
			 body[i].paint(Coord,g2d,true); 
			 if(body[i].isBreak() != 0){
				 break;
			 }
			 Coord.setY(Coord.getextremeDY()+25);
			 Coord.setextremeDY(Coord.getextremeDY()+50);
			 }else {
			 body[i].paint(Coord,g2d,false);	 
			// Coord.setextremeDY(Coord.getextremeDY()+25);
			 } 
		 }	 
	}

	@Override
	public int isIf() {
		return 0;
	}
	@Override
	public int getCyclesMargin() {
		int sum = 0;
		for (int i = 0; i < body.length; i++) {
			 	sum += body[i].getCyclesMargin();
		}
		return sum;
	}
	@Override
	public int getwidth(Coords Coord) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getelsecyclemargin(){
		return 0;
	}
	 public boolean isThereABreakInBody() {
		 for (int i = 0; i < body.length; i++) {
			 if(body[i].isBreak() == 1) {
				 return true;
			 }
		 }
		 return false;
	 }
	 @Override
	 public int breakMargin(){
		 for (int i = 0; i < body.length; i++) {
			if(body[i].breakMargin() == 30){
				return 30;
			}
		}
		return 0; 
	 }
}
