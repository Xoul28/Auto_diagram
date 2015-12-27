package auto_BSA;

import java.awt.Graphics2D;

public class ForCycle extends FunBlock{
   

	@Override
	public void paint(String line, Coords Coord, Graphics2D g2d) {
		 int lenh=35,lenv=10,lenhn=50,lenvn=25;
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
		 //left
		 g2d.drawLine(Coord.getX()-lenhn, Coord.getY()-lenv, Coord.getX()-lenhn, Coord.getY()+lenv);
		 //right
		 g2d.drawLine(Coord.getX()+lenhn, Coord.getY()-lenv, Coord.getX()+lenhn, Coord.getY()+lenv);
		 g2d.drawString(line, Coord.getX()-32, Coord.getY()+5);
	}

	
	
}