package auto_BSA;

import java.awt.Graphics2D;

public class DoWhileCycle extends Cycles{
	  
		public DoWhileCycle(Block[] body, String line) {
			super(body, line);
			ret  = new DoWhileCycleReturning();
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
			 
			 if(body.length!=0){
				 drawbody(Coord, g2d);
			 }else{
				 Coord.setY(Coord.getY()-50-20); 
				 Coord.setextremeDY(Coord.getextremeDY()-50-20);
			 }
			 Coord.setextremeDY(Coord.getextremeDY()-20);
			 Coord.setY(Coord.getY()+20);
			 Coord.setY(Coord.getextremeDY()+25);
			 Coord.setextremeDY(Coord.getextremeDY()+50+20);
			 Coord.setY(Coord.getY()+20);
			 Coord.setextremeDY(Coord.getextremeDY()+20);
		    
					 
			 //top
					   //lefttopline
			g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
					   //righttopline
			g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY());
					  //rightbottomline
			g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
					  //righttopline
		    g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT, Coord.getY());
					 
		    drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		 	nextArrow(Coord, g2d);
			Coord.setextremeRX(Coord.getextremeRX()+getCyclesMargin()+getwidth(Coord));
		    Coord.setextremeLX(Coord.getextremeLX()-getCyclesMargin()); 
		    ret.returning(Coord, retCoord, g2d);   
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
				 }
			 }	 
		}
	}
