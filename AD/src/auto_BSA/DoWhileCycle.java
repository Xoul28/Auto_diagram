package auto_BSA;

import java.awt.Graphics2D;

public class DoWhileCycle extends Cycles{
	  
		public DoWhileCycle(Block[] body, String line) {
			super(body, line);
		}

		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
			 Coord.setY(Coord.getY()-50);
			nextArrow(Coord, g2d);
			 Coord.setY(Coord.getY()+70);
			 Coord.setextremeDY(Coord.getextremeDY()+20);
			 Coords retCoord = new Coords(Coord.getX(),Coord.getY());
			 retCoord.setextremeLX(retCoord.getX()-50);
			 retCoord.setextremeRX(retCoord.getX()+50);
			 Coord.setextremeRX(retCoord.getX()+50);
			 
			 
			 //отрисовываем тело
			 drawbody(Coord, g2d);
			 Coord.setextremeDY(Coord.getextremeDY()-20);
			 Coord.setY(Coord.getY()+20);
			 Coord.setY(Coord.getextremeDY()+25);
			 Coord.setextremeDY(Coord.getextremeDY()+50+20);
			
			 Coord.setY(Coord.getY()+20);
			 Coord.setextremeDY(Coord.getextremeDY()+20);
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
		     returning(Coord, retCoord, g2d);   
		}
		@Override
		 public void returning(Coords Coord,Coords retCoord,Graphics2D g2d) {
			  Coord.setextremeRX(Coord.getextremeRX()+getCyclesMargin()+getwidth(Coord));
			  Coord.setextremeLX(Coord.getextremeLX()-getCyclesMargin());
			  //влево
			  g2d.drawLine(Coord.getX()-50,Coord.getextremeDY()-45,Coord.getextremeLX(),Coord.getextremeDY()-45);
			  //вверх
			  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY()-45,Coord.getextremeLX(),retCoord.getY()-32);
			//вправо к блоку цикла
			  g2d.drawLine(Coord.getextremeLX(),retCoord.getY()-32,retCoord.getX(),retCoord.getY()-32);
			  Coord.setextremeLX(Coord.getextremeLX()+getCyclesMargin());
			  Coord.setextremeRX(Coord.getX()+50);
		  }
		@Override
		public void drawbody(Coords Coord,Graphics2D g2d) {
			 for(int i = 0 ; i<body.length ; i++) {
				 if(i!=(body.length-1)) {
				 body[i].paint(Coord,g2d,true); 
				 Coord.setY(Coord.getY()+50+20);
				 Coord.setY(Coord.getextremeDY()+25);
				 Coord.setextremeDY(Coord.getextremeDY()+50);
				 
				 }else {
				 body[i].paint(Coord,g2d,true);	
				 
				
				
				 //Coord.setextremeDY(Coord.getextremeDY()-20);
				 }
			 }	 
		}
	}
