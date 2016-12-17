package auto_BSA;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;

public class Function extends FunBlock {
	static LinkedList<Comment> CommentList = new LinkedList<Comment>();

	public Function(Block[] body, String line) {
		super(body, line);
	}

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		
		drawCenteredString(g2d, line, Coord.getX(), 25);
		g2d.drawRoundRect(Coord.getX() - 50, Coord.getY() - 12, 100, 25, 25, 25);
		drawCenteredString(g2d, "Начало", Coord.getX(), Coord.getY());
		Coord.setY((Coord.getY() - 12));
		nextArrow(Coord, g2d);
		Coord.setY((Coord.getY() + 70));
		Coord.setextremeDY(Coord.getY() + 25);
		Coord.setextremeRX(Coord.getX() + 50);
		Coord.setextremeLX(Coord.getX() - 50);
		drawbody(Coord, g2d);
		if(isThereAReturnInBody()){
			Coord.returnList.removeLast();
			Coord.setextremeDY(Coord.getextremeDY()-15);
		}
		drawComments(g2d, Coord);
		removeComments();
		Coord.setY(Coord.getextremeDY());
		drawReturns(g2d, Coord);
		//Coord.returnList.clear();
		Coord.setY(Coord.getY() + 12);
		
		g2d.drawRoundRect(Coord.getX() - 50, Coord.getY() - 12, 100, 25, 25, 25);
		drawCenteredString(g2d, "Конец", Coord.getX(), Coord.getY());
	}

	private void removeComments() {
		CommentList.clear();
	}
	private void drawReturns(Graphics2D g2d, Coords Coord) {
		for (Coords Return : Coord.returnList) {
			 g2d.drawOval(Return.getX()-13, Return.getY(), 25, 25);
			 drawCenteredString(g2d, breakinthelaw.toString(), Return.getX() , Return.getY()+11);
			 g2d.drawLine(Coord.getX(), Coord.getY(), Coord.getX(), Coord.getY()+40);
			 g2d.drawLine(Coord.getX()-20, Coord.getY()+20, Coord.getX(), Coord.getY()+20);
			 g2d.drawOval(Coord.getX()-45, Coord.getY()+7, 25, 25);
			 drawCenteredString(g2d, breakinthelaw.toString(), Coord.getX()-33 , Coord.getY()+18);
			 Coord.setY(Coord.getY()+40);
			 Block.breakinthelaw =Block.breakinthelaw.intValue()+ 1;
		}
	}
	private void drawComments(Graphics2D g2d, Coords Coord) {
		for (Comment comment : CommentList) {
			int m = 0, n = 0;
			for (Comment comment2 : CommentList) {
				if (comment.Coord.getY() == comment2.Coord.getY()
						&& comment.Coord.getX() != comment2.Coord.getX()) {
					m += 15;
					n += 6;
				}
			}

			g2d.drawLine(comment.Coord.getX()+HALFOFNORMALLENGHT, comment.Coord.getY(), comment.Coord.getX()+HALFOFNORMALLENGHT+3, comment.Coord.getY()-HALFOFNORMALHIGHT-n-7);
			g2d.setColor(Color.white);
			for (int i = comment.Coord.getY()+2; i > comment.Coord.getY()-HALFOFNORMALHIGHT-n-7; i-=8) {
				g2d.drawLine(comment.Coord.getX()+HALFOFNORMALLENGHT+1, i,comment.Coord.getX()+HALFOFNORMALLENGHT+5 , i);
				g2d.drawLine(comment.Coord.getX()+HALFOFNORMALLENGHT+1, i+1,comment.Coord.getX()+HALFOFNORMALLENGHT+5 , i+1);
			}
			g2d.setColor(Color.black);
			comment.Coord.setY(comment.Coord.getY()-n-7-HALFOFNORMALHIGHT);
			g2d.drawLine(comment.Coord.getX()+HALFOFNORMALLENGHT+3, comment.Coord.getY(), Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25, comment.Coord.getY());
			g2d.setColor(Color.white);
			for (int i = comment.Coord.getX()+HALFOFNORMALLENGHT+3; i < Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25; i+=10) {
				g2d.drawLine(i, comment.Coord.getY(),i+5 , comment.Coord.getY());
			}
			g2d.setColor(Color.black);
			g2d.drawLine(Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25, comment.Coord.getY(), Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25, comment.Coord.getY()-m);
			g2d.setColor(Color.white);
			for (int i = comment.Coord.getY(); i > comment.Coord.getY()-m; i-=6) {
				g2d.drawLine(Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25, i,Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25+5 , i);
				g2d.drawLine(Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25, i+1,Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+25+5 , i+1);
			}
			g2d.setColor(Color.black);
			int y = comment.Coord.getY()-m+6;
			int x = Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()+n+30;
			g2d.drawString(comment.line, x, y);
			if (comment.mathExp != null) {
				int w = g2d.getFontMetrics().stringWidth(comment.line);
				int h = g2d.getFontMetrics().getAscent();
				comment.mathExp.draw(g2d, x + w, y - h / 2 + 1);
				
			}
			g2d.drawLine(Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25,comment.Coord.getY()-m+8, Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25, comment.Coord.getY()-m-5);
			g2d.drawLine(Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25, comment.Coord.getY()-m-5, Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25+6, comment.Coord.getY()-m-5);
			g2d.drawLine(Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25, comment.Coord.getY()-m+8, Coord.getextremeRX()+getwidth(Coord)+breakMargin()+getCyclesMargin()-n+n+25+6, comment.Coord.getY()-m+8);
			Coord.setextremeRX(x+g2d.getFontMetrics().stringWidth(comment.line));
			
			countingofbody(Coord);
			Coord.setextremeRX(Coord.getextremeRX()-getwidth(Coord)-breakMargin()-getCyclesMargin()-n-30-+g2d.getFontMetrics().stringWidth(comment.line));
		}

	}

	@Override
	public void drawbody(Coords Coord, Graphics2D g2d) {
		for (int i = 0; i < body.length; i++) {
			if (i != (body.length - 1)) {
				body[i].paint(Coord, g2d, true);
				Coord.setY(Coord.getY() + 50 + 20);
				Coord.setY(Coord.getextremeDY() + 25);
				Coord.setextremeDY(Coord.getextremeDY() + 50);
			} else {
				body[i].paint(Coord, g2d, true);
				// Coord.setextremeDY(Coord.getextremeDY()+25);
			}
		}
	}

	@Override
	public int getwidth(Coords Coord) {
		int width = 0;
		for (int i = 0; i < body.length; i++) {
			if (body[i].getwidth(Coord) > width)
				width = body[i].getwidth(Coord);
		}
		return width;
	}
}
