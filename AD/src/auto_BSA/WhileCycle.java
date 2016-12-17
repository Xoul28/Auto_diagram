package auto_BSA;

import java.awt.Graphics2D;

public class WhileCycle extends Cycles{
	  
		public WhileCycle(Block[] body, String line) {
			super(body, line);
			ret  = new WhileCycleReturning();
		}

		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
		      //drawing cycle body
			 
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
		g2d.drawString("да", Coord.getX()+HALFOFNORMALLENGHT-HALFOFNORMALHIGHT-17, Coord.getY()+HALFOFNORMALHIGHT+7);
		 g2d.drawString("нет", Coord.getX()+HALFOFNORMALLENGHT-HALFOFNORMALHIGHT+30, Coord.getY()-HALFOFNORMALHIGHT+17);

		nextArrow(Coord, g2d);

		Coords retCoord = new Coords(Coord.getX(), Coord.getY());
		retCoord.setextremeLX(retCoord.getX() - 50);
		retCoord.setextremeRX(retCoord.getX() + 50);
		Coord.setextremeRX(retCoord.getX() + 50);

		Coord.setY(Coord.getY() + 50 + 20);
		Coord.setextremeDY(Coord.getextremeDY() + 50 + 20);
		// ������������ ����
		if (body.length != 0) {
			drawbody(Coord, g2d);
		} else {
			Coord.setY(Coord.getY() - 50 - 20);
			Coord.setextremeDY(Coord.getextremeDY() - 50 - 20);
		}
		if (isThereABreakInBody()) {
			nextArrow(retCoord, g2d);
			Coord.setY(Coord.getextremeDY() + 50);
		} else {

			Coord.setextremeRX(Coord.getextremeRX() + getCyclesMargin()
					+ getwidth(Coord) + breakMargin());
			drawbreak(Coord, g2d, retCoord.getY());
			Coord.setextremeLX(Coord.getextremeLX() - getCyclesMargin()
					- continueMargin());
			drawContinue(Coord, g2d, retCoord.getY());
			Coord.setextremeDY(Coord.getextremeDY() + 5);

			ret.returning(Coord, retCoord, g2d);

			Coord.setextremeDY(Coord.getextremeDY() + 10);
			Coord.setextremeLX(Coord.getextremeLX() + getCyclesMargin());
			Coord.setextremeRX(Coord.getX() + 50);
		}
		countingofbody(Coord);
		countingofbody(retCoord);
	}

}