package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;

public class ForCycle extends FunBlock{
   
  
	public ForCycle(Block[] body, String line) {
		super(body, line);
	}

	@Override
	public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
	      Coord.incinvestedCol();
	      
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
		 drawCenteredString(g2d, line, Coord.getX(), Coord.getY());
		 //рисуем стрелочку от блока до следующего блока
		 nextArrow(Coord, g2d);
		 //запоминаем координаты возвращения
		 Coords retCoord = new Coords(Coord.getX(),Coord.getY());
		 retCoord.setextremeLX(retCoord.getX()-50);
		 retCoord.setextremeRX(retCoord.getX()+50);
		 
		 Coord.setY(Coord.getY()+50+20);
		 Coord.setextremeDY(Coord.getextremeDY()+50+20);
		 //отрисовываем тело
		 drawbody(Coord, g2d);
		
	     returning(Coord, retCoord, g2d);
         Coord.decinvestedCol();
         if(Coord.getinvestedCol()==0){
        	 Coord.setextremeRX(Coord.getX()+50);
        	 Coord.setextremeLX(Coord.getX()-50);
         }
	
		
		
	}
  private void returning(Coords Coord,Coords retCoord,Graphics2D g2d){

	  
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
  @Override
	public int getwidth() {
	    int sum=10;
		for (int i = 0; i < body.length; i++) {
			sum+=body[i].getwidth();
		}
		return sum;
  }
}