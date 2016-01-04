package auto_BSA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SelectorBlock extends FunBlock{


    
	 
     Block elsebody[];
	 public SelectorBlock(Block[] body, String line,Block[] elsebody) {
		super(body, line);
		this.elsebody = elsebody;
		
	}
		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
		   
		   
			int lenh=35,lenv=10,lenhn=50,lenvn=25;
		     //lefttopline
			 g2d.drawLine(Coord.getX()-lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			   //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()+lenhn, Coord.getY());
			  //rightbottomline
			 g2d.drawLine(Coord.getX()+lenhn, Coord.getY(), Coord.getX(), Coord.getY()+lenvn);
			  //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-lenvn, Coord.getX()-lenhn, Coord.getY());
			 drawCenteredString(g2d, line, Coord.getX(), Coord.getY());		
			 nextArrow(Coord, g2d);
			 
			 Coords retCoord = new Coords(Coord.getX()+50,Coord.getY());
			 
			 
			
			 Coord.setY(Coord.getY()+50+20); 
			 Coord.setextremeDY(Coord.getextremeDY()+50+20);
			 
			 drawbody(Coord, g2d);
			 retCoord.setextremeRX(Coord.getextremeRX()+110-50+getwidth()-minusbody());
			
			 g2d.drawLine(retCoord.getX(), retCoord.getY(), retCoord.getextremeRX(), retCoord.getY());
			 retCoord.setX(retCoord.getextremeRX());
			 retCoord.setextremeLX(retCoord.getX()-50);
			 retCoord.setextremeRX(retCoord.getX()+50);
			 retCoord.setY(retCoord.getY()-25);
			 nextArrow(retCoord, g2d);
			 
			 retCoord.setY(retCoord.getY()+50+20); 
			 retCoord.setextremeDY(retCoord.getY()+25);
				 
			 
			 if(elsebody.length!=0){
				 drawelseBody(retCoord, g2d);
			 }
			 
			
			 
		     drawret(Coord, retCoord, g2d);
		     Coord.setextremeRX(Coord.getextremeRX()+110+getwidth()*2-minusbody()*2);
		  
		     
		}
		
	 private void drawelseBody(Coords retCoord,Graphics2D g2d) {
		 for(int i = 0 ;i < elsebody.length;i++) {
			 if(i != (elsebody.length-1) ) {
				 elsebody[i].paint(retCoord, g2d, true);
				 retCoord.setY(retCoord.getY()+50+20);
				 retCoord.setY(retCoord.getextremeDY()+25);
				 retCoord.setextremeDY(retCoord.getextremeDY()+50);
				
			 }else {
				 elsebody[i].paint(retCoord, g2d, false);
			
			 }
		 }
	 }
     private void drawret(Coords Coord,Coords retCoord,Graphics2D g2d){
    	
		 if(Coord.getextremeDY()>retCoord.getextremeDY()){
			 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()+10);
			 Coord.setextremeDY(Coord.getextremeDY()+10) ;
			 retCoord.setextremeDY(Coord.getextremeDY());
		 }else{
			 g2d.drawLine(Coord.getX(), retCoord.getextremeDY()-10, Coord.getX(), Coord.getextremeDY());
			 retCoord.setextremeDY(retCoord.getextremeDY()) ;
			 Coord.setextremeDY(retCoord.getextremeDY());
		 }
		 g2d.drawLine(Coord.getX(), Coord.getextremeDY()-10, Coord.getX(), Coord.getextremeDY()+10); 
		 g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()); 
		 Coord.setextremeDY(Coord.getextremeDY()+10) ;
		 retCoord.setextremeDY(retCoord.getextremeDY()+10) ;
	 }
     @Override
 	public int getwidth() {
    	 int sum=0;
 		for (int i = 0; i < elsebody.length; i++) {
 			sum+=elsebody[i].getwidth();
 		}
 		for (int i = 0; i < body.length; i++) {
 			sum+=body[i].getwidth();
 		}
 		return sum;
 	}
    public int minusbody(){
    	int sum=0;
 		for (int i = 0; i < body.length; i++) {
 			sum+=body[i].getwidth();
 		}
 		return sum;
    }
}