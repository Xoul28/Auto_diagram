package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;

public class ForCycle extends Cycles {

	public ForCycle(Block[] body, String line) {
		super(body, line);
	}

	@Override
	public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {      
	      //отрисовываем сам блок цикла
		 int lenh=25,lenv=0,lenhn=50,lenvn=25;
		 //top
		 g2d.drawLine(Coord.getX()-lenh, Coord.getY()-lenvn, Coord.getX()+lenh, Coord.getY()-lenvn);
		 //leftupangle
		 g2d.drawLine(Coord.getX()-lenhn, Coord.getY()-lenv, Coord.getX()-lenh, Coord.getY()-lenvn);
		//righttopangle
		 g2d.drawLine(Coord.getX()+lenh, Coord.getY()-lenvn, Coord.getX()+lenhn, Coord.getY()-lenv);
		//leftdownangle
		 g2d.drawLine(Coord.getX()-lenhn, Coord.getY()+lenv, Coord.getX()-lenh, Coord.getY()+lenvn);
		//rightdownangle
		 g2d.drawLine(Coord.getX()+lenh, Coord.getY()+lenvn, Coord.getX()+lenhn, Coord.getY()+lenv);
		 //bottom
		 g2d.drawLine(Coord.getX()-lenh, Coord.getY()+lenvn, Coord.getX()+lenh, Coord.getY()+lenvn);
		 //строку внутри цикла отрисовываем
		 drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		 //рисуем стрелочку от блока до следующего блока
		 nextArrow(Coord, g2d);
		 //запоминаем координаты возвращения
		 Coords retCoord = new Coords(Coord.getX(),Coord.getY());
		 retCoord.setextremeLX(retCoord.getX()-50);
		 retCoord.setextremeRX(retCoord.getX()+50);
		 Coord.setextremeRX(retCoord.getX()+50);
		 
		 Coord.setY(Coord.getY()+50+20);
		 Coord.setextremeDY(Coord.getextremeDY()+50+20);
		 //отрисовываем тело
		 drawbody(Coord, g2d);
		
	     returning(Coord, retCoord, g2d); 
	}
}