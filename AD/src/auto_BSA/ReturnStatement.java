package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ReturnStatement extends Statement {

	public ReturnStatement(String line) {
		super(line);
	}
	
	public ReturnStatement(String line, boolean functionCall) {
		super(line, functionCall);
	}
	
	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		super.paint(Coord, g2d, true);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(Coord.getX(), Coord.getextremeDY(), Coord.getX(), Coord.getextremeDY()-15);
		g2d.setColor(Color.BLACK);
		Coord.setY(Coord.getextremeDY());
		Coord.returnList.add(new Coords(Coord.getX(), Coord.getY()-15));
	}

	@Override
	public int isReturn() {
		return 1;
	}
}
