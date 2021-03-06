package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class IOBlock extends Statement {

	public IOBlock(String line) {
		this.line = line;
	}

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT + 10, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT);
		g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT - 10, Coord.getY()+HALFOFNORMALHIGHT);
		g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT + 10, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT , Coord.getY()+HALFOFNORMALHIGHT);
		g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT , Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT - 10 , Coord.getY()+HALFOFNORMALHIGHT);
		if (line.length() > THEBIGGESTLENGHT) {
			Function.CommentList.add(new Comment(line, Coord));
		} else {
			drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		}
		if (nextlinedraw) {
			nextArrow(Coord, g2d);
			Coord.setextremeDY(Coord.getextremeDY() + 20);
		}
		
		getwidth(Coord);
	}
}
