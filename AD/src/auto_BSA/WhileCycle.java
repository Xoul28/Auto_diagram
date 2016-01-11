package auto_BSA;

import java.awt.Graphics2D;

public class WhileCycle extends Cycles{
	  
		public WhileCycle(Block[] body, String line) {
			super(body, line);
		}

		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
		      //отрисовываем сам блок цикла
			 int lenh=35,lenv=10,lenhn=50,lenvn=25;
			 //top
			   //lefttopline
			 g2d.drawLine(Coord.getX()-lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			   //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()+lenhn, Coord.getY());
			  //rightbottomline
			 g2d.drawLine(Coord.getX()+lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			  //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()-lenhn, Coord.getY());
			 
			 drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
			 
			 nextArrow(Coord, g2d);
		
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
		@Override
		 public void returning(Coords Coord,Coords retCoord,Graphics2D g2d) {
			  Coord.setextremeRX(Coord.getextremeRX()+getCyclesMargin()+getwidth(Coord));
			  Coord.setextremeLX(Coord.getextremeLX()-getCyclesMargin());
			  Coord.setextremeDY(Coord.getextremeDY()+5);
			  //рисуем линию вниз
			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()-5);
			  //влево
			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getextremeLX(),Coord.getextremeDY());
			  //вверх
			  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY(),Coord.getextremeLX(),retCoord.getY()-32);
			  
			  Coord.setextremeDY(Coord.getextremeDY()+5);
			//вправо к блоку цикла
			  g2d.drawLine(Coord.getextremeLX(),retCoord.getY()-32,retCoord.getX(),retCoord.getY()-32);
			//вправо от блока цикла 
			  g2d.drawLine(retCoord.getextremeRX(),retCoord.getY(),Coord.getextremeRX(),retCoord.getY());
			//вниз 
			  g2d.drawLine(Coord.getextremeRX(),retCoord.getY(),Coord.getextremeRX(),Coord.getextremeDY());	
			//влево 
			  g2d.drawLine(Coord.getextremeRX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY());	
			//вниз
			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()+10);
			  
			  Coord.setextremeDY(Coord.getextremeDY()+10);
			 // retCoord.setextremeDY(Coord.getextremeDY());	  
		  }
	}