package auto_BSA;

import java.awt.Graphics2D;

public abstract class Block {
	String line;
	 public Block() {
	    }
public abstract  void paint( Coords Coord,Graphics2D g2d);
public void nextArrow(Coords Coord,Graphics2D g2d) {
	 g2d.drawLine(Coord.getX(), Coord.getY()+25, Coord.getX(), Coord.getY()+25+10);
	 g2d.drawLine(Coord.getX(), Coord.getY()+25+10, Coord.getX()-2, Coord.getY()+25+8);
	 g2d.drawLine(Coord.getX(), Coord.getY()+25+10, Coord.getX()+2, Coord.getY()+25+8);
}

}
