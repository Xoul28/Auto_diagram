package auto_BSA;

import java.awt.Graphics2D;

public class ContinueLine extends Statement {

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		Coord.setY(Coord.getY() - 25);
		g2d.drawLine(Coord.getX(), Coord.getY(), Coord.getX(), Coord.getY() + 7);
		Coord.setY(Coord.getY() + 7);
		Coord.setextremeDY(Coord.getY());
		// g2d.setColor(Color.white);
		// g2d.drawLine(Coord.getX(), Coord.getY(), Coord.getX(),
		// Coord.getY()+10);
		// g2d.setColor(Color.black);
		Coord.continueList.add(new Coords(Coord.getX(), Coord.getY()));
		Coord.setY(Coord.getY() + 5);
		Coord.setextremeDY(Coord.getY());
		countingofbody(Coord);
	}

	@Override
	public int continueMargin() {
		return 30;
	}

	@Override
	public int isContinue() {

		return 1;
	}

}
