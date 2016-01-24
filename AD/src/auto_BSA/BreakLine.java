package auto_BSA;

import java.awt.Graphics2D;

public class BreakLine extends Block{

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		Coord.setextremeDY(Coord.getY()-25);
		Coord.setY(Coord.getY()-25);
		Coord.breakingList.add(new Coords(Coord.getX(),Coord.getY()));
		
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
		return 1;
		
	}
}
