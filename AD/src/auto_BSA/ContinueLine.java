package auto_BSA;

import java.awt.Graphics2D;

public class ContinueLine extends Block {

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		Coord.setY(Coord.getY()-25);
		g2d.drawLine(Coord.getX(), Coord.getY(), Coord.getX(), Coord.getY()+7);
		Coord.setY(Coord.getY()+7);
		Coord.setextremeDY(Coord.getY());
//		g2d.setColor(Color.white);
//		g2d.drawLine(Coord.getX(), Coord.getY(), Coord.getX(), Coord.getY()+10);
//		g2d.setColor(Color.black);
		Coord.continueList.add(new Coords(Coord.getX(),Coord.getY()));
		Coord.setY(Coord.getY()+5);
		Coord.setextremeDY(Coord.getY());

	}

	@Override
	public int getwidth(Coords Coord) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isIf() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCyclesMargin() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
    public int isBreak() {
		return 0;
		
	}
	@Override
	public  int breakMargin(){
		return 0;
	}
	@Override
	public  int continueMargin(){
		return 30;
	}

	@Override
	public boolean isThereABreakInElseBody() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int isContinue() {
		
		return 1;
	}

}
