package auto_BSA;

import java.awt.Graphics2D;

public class EmptyBlock extends Block {

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		g2d.drawLine(Coord.getX(), Coord.getY() - HALFOFNORMALHIGHT,
					 Coord.getX(), Coord.getY() + HALFOFNORMALHIGHT);
	}

	@Override
	public int getwidth(Coords Coord) {
		return 0;
	}

	@Override
	public int isIf() {
		return 0;
	}

	@Override
	public int getCyclesMargin() {
		return 0;
	}

}
