package auto_BSA;

import java.awt.Graphics2D;

public class Cycles extends FunBlock{
	
	public Cycles(Block[] body,String line){
		super(body,line);
	}

	@Override
	public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
			
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
			 
			 body[i].paint(Coord,g2d,false);	 
			 //Coord.setextremeDY(Coord.getextremeDY()-20);
			
			 }
			 
		 }
		 
	}
	 @Override
	public int getwidth() {
		    int sum=10;
			for (int i = 0; i < body.length; i++) {
				sum+=body[i].getwidth();
			}
			return sum;
	  }
	 public void returning(Coords Coord,Coords retCoord,Graphics2D g2d){

		  
		  Coord.setextremeRX(Coord.getextremeRX()+5);
		  Coord.setextremeLX(Coord.getextremeLX()-5);
		  Coord.setextremeDY(Coord.getextremeDY()+5);
		  //рисуем линию вниз
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX(),Coord.getextremeDY()-5);
		  //влево
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getextremeLX(),Coord.getextremeDY());
		  //вверх
		  g2d.drawLine(Coord.getextremeLX(),Coord.getextremeDY(),Coord.getextremeLX(),retCoord.getY());
		  
		  Coord.setextremeDY(Coord.getextremeDY()+5);
		//вправо к блоку цикла
		  g2d.drawLine(Coord.getextremeLX(),retCoord.getY(),retCoord.getextremeLX(),retCoord.getY());
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