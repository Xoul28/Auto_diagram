package auto_BSA;

import java.awt.Graphics2D;
import java.util.Iterator;

public class DoWhileCycle extends Cycles{
	  
		public DoWhileCycle(Block[] body, String line) {
			super(body, line);
			ret  = new DoWhileCycleReturning();
		}

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {

		Coord.setY(Coord.getY() - 50);
		nextArrow(Coord, g2d);
		Coord.setY(Coord.getY() + 70);
		Coord.setextremeDY(Coord.getextremeDY() + 20);
		Coords retCoord = new Coords(Coord.getX(), Coord.getY());
		retCoord.setextremeLX(retCoord.getX() - 50);
		retCoord.setextremeRX(retCoord.getX() + 50);
		Coord.setextremeRX(retCoord.getX() + 50);

		if (body.length != 0) {
			drawbody(Coord, g2d);
		} else {
			Coord.setY(Coord.getY() - 50 - 20);
			Coord.setextremeDY(Coord.getextremeDY() - 50 - 20);
		}
		Coord.setextremeDY(Coord.getextremeDY() - 20);
		Coord.setY(Coord.getY() + 20);
		Coord.setY(Coord.getextremeDY() + 25);
		Coord.setextremeDY(Coord.getextremeDY() + 50 + 20);
		Coord.setY(Coord.getY() + 20);
		Coord.setextremeDY(Coord.getextremeDY() + 20);
		    
					 
			 //top
					   //lefttopline
			g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
					   //righttopline
			g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY());
					  //rightbottomline
			g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
					  //righttopline
		    g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT, Coord.getY());
					 
		if (line.length() > THEBIGGESTLENGHT) {
			Function.CommentList.add(new Comment(line, Coord));
		} else {
			drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		}
		g2d.drawString("нет", Coord.getX()+HALFOFNORMALLENGHT-HALFOFNORMALHIGHT-17, Coord.getY()+HALFOFNORMALHIGHT+7);
		 g2d.drawString("да", Coord.getX()-HALFOFNORMALLENGHT+HALFOFNORMALHIGHT-30, Coord.getY()-HALFOFNORMALHIGHT+17);
		nextArrow(Coord, g2d);
		if (isThereABreakInBody()) {
			nextArrow(retCoord, g2d);
			Coord.setY(Coord.getextremeDY() + 50);
		} else {

			Coord.setextremeRX(Coord.getextremeRX() + getCyclesMargin()
					+ getwidth(Coord) + breakMargin());
			if (breakMargin() == 30) {
				int min = 100000;
				for (Coords iter : Coord.breakingList) {
					if (iter.getY() < min) {
						min = iter.getY();
					}
				}
				g2d.drawLine(Coord.getextremeRX(), min, Coord.getextremeRX(),
						Coord.getY() + 35);
				g2d.drawLine(Coord.getX(), Coord.getY() + 35,
						Coord.getextremeRX(), Coord.getY() + 35);
				g2d.drawLine(Coord.getX(), Coord.getY() + 35, Coord.getX() + 2,
						Coord.getY() + 37);
				g2d.drawLine(Coord.getX(), Coord.getY() + 35, Coord.getX() + 2,
						Coord.getY() + 33);

			}
			drawbreak(Coord, g2d, retCoord.getY());

			Coord.setextremeLX(Coord.getextremeLX() - getCyclesMargin()
					- continueMargin());
			drawContinue(Coord, g2d, retCoord.getY());
			ret.returning(Coord, retCoord, g2d);

			Coord.setextremeLX(Coord.getextremeLX() + getCyclesMargin());
			Coord.setextremeRX(Coord.getX() + 50);
		}
		countingofbody(Coord);
		countingofbody(retCoord);
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
			}
		}
	}
}
