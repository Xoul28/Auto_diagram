package auto_BSA;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

public abstract class Block {
	String line;
	 public Block() {
	    }
public abstract  void paint( Coords Coord,Graphics2D g2d,boolean nextlinedraw);
public void nextArrow(Coords Coord,Graphics2D g2d) {
	 g2d.drawLine(Coord.getX(), Coord.getY()+25, Coord.getX(), Coord.getY()+25+20);
	 g2d.drawLine(Coord.getX(), Coord.getY()+25+20, Coord.getX()-2, Coord.getY()+25+18);
	 g2d.drawLine(Coord.getX(), Coord.getY()+25+20, Coord.getX()+2, Coord.getY()+25+18);
}
public static void drawCenteredString(Graphics2D g, String s, int w, int h) {
	FontMetrics fm = g.getFontMetrics();
	int x = w - fm.stringWidth(s) / 2;
	int y = h + fm.getAscent() / 2;
	g.drawString(s, x, y);
}

}
