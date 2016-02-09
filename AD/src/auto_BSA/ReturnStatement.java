package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ReturnStatement extends Statement {

	public ReturnStatement(String line) {
		super(line);
	}
	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT);
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT);
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT);
		 g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT);
		if (line.length() > THEBIGGESTLENGHT) {
			Function.CommentList.add(new Comment(line, Coord));
		} else {
			drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		}
		
			nextArrow(Coord, g2d);
			Coord.setextremeDY(Coord.getextremeDY() + 20);
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
