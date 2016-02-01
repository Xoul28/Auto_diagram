package auto_BSA;

import java.awt.Graphics2D;

public class EmptyBlock extends Statement {

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		g2d.drawLine(Coord.getX(), Coord.getY() - HALFOFNORMALHIGHT, Coord.getX(), Coord.getY() + HALFOFNORMALHIGHT);
		 nextArrow(Coord, g2d);
		 Coord.setextremeDY(Coord.getextremeDY()+20);
	}
}
