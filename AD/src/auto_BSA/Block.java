package auto_BSA;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

public abstract class Block {
final int HALFOFNORMALLENGHT=50,HALFOFNORMALHIGHT=25;
	String line;
	 public Block() {
	    }
	 //if nextlinedraw -> drawing next line
public abstract  void paint( Coords Coord,Graphics2D g2d,boolean nextlinedraw);
public void nextArrow(Coords Coord,Graphics2D g2d) {
	 g2d.drawLine(Coord.getX(), Coord.getY()+25, Coord.getX(), Coord.getY()+25+20);
//	 g2d.drawLine(Coord.getX(), Coord.getY()+25+20, Coord.getX()-2, Coord.getY()+25+18);
//	 g2d.drawLine(Coord.getX(), Coord.getY()+25+20, Coord.getX()+2, Coord.getY()+25+18);
}
public static void drawCenteredString(Graphics2D g, String s, int w, int h) {
	FontMetrics fm = g.getFontMetrics();
	int x = w - fm.stringWidth(s) / 2;
	int y = h + fm.getAscent() / 2;
	g.drawString(s, x, y);
}
public static void drawCenteredLines(Graphics2D g, String s, int w, int h) {
	int BLOCK_HEIGHT = 50;
	h -= BLOCK_HEIGHT / 2;
	String[] lines = s.split("\n");
	int lineCount = lines.length;
	FontMetrics fm = g.getFontMetrics();
	h -= fm.getAscent() / 2;
	int liney = (BLOCK_HEIGHT + fm.getAscent()) / (lineCount + 1); //+ g.getFontMetrics().getDescent()
	for (int i = 0; i < lineCount; i++) {
		int y = h + (i + 1) * liney;
		// FOR DEBUG g.drawLine(w - 50, y, w + 50, y);
		drawCenteredString(g, lines[i], w, y);
	}
}

//if you see that method do not touch it...
public abstract int getwidth(Coords Coord);
//returns ifs counts
public abstract int isIf();
public  int isBreak(){
	return 0;
}
public abstract int getCyclesMargin();
}

 