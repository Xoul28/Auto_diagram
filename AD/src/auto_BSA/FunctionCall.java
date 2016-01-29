package auto_BSA;

import java.awt.Graphics2D;

public class FunctionCall extends Statement {

	public FunctionCall(String line) {
		super(line);
	}
	
	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT+7, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT+7, Coord.getY()+HALFOFNORMALHIGHT);
		g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT-7, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT-7, Coord.getY()+HALFOFNORMALHIGHT);
		super.paint(Coord, g2d, nextlinedraw);
	}

}
