package auto_BSA;

import java.awt.*;

import javax.swing.*;

public class Statement extends Block{

	 public Statement(String line) {
		this.line = line;
	 }

	 @Override
	 public void paint(Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
		 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 g2d.setColor(Color.black);
		 g2d.drawLine(Coord.getX()-50, Coord.getY()-25, Coord.getX()+50, Coord.getY()-25);
		 g2d.drawLine(Coord.getX()-50, Coord.getY()+25, Coord.getX()+50, Coord.getY()+25);
		 g2d.drawLine(Coord.getX()-50, Coord.getY()-25, Coord.getX()-50, Coord.getY()+25);
		 g2d.drawLine(Coord.getX()+50, Coord.getY()-25, Coord.getX()+50, Coord.getY()+25);
		 drawCenteredString(g2d, line, Coord.getX(), Coord.getY());
		 drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		 if(nextlinedraw){
		 nextArrow(Coord, g2d);
		 Coord.setextremeDY(Coord.getextremeDY()+20);
		 }
	 }

	@Override
	public int getwidth() {
		return 0;
	}

}
