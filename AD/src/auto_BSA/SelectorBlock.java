package auto_BSA;

import java.awt.Graphics2D;


public class SelectorBlock extends FunBlock {
     Block elsebody[];
	 public SelectorBlock(Block[] body, String line,Block[] elsebody) {
		super(body, line);
		this.elsebody = elsebody;
	}
		@Override
		public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
			
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
			 //запоминаем координаты возвращения
			 Coords retCoord = new Coords(Coord.getX()+50,Coord.getY());
			
			 Coord.setY(Coord.getY()+50+20); 
			 Coord.setextremeDY(Coord.getextremeDY()+50+20);
			 //TODO стереть это
			 
			 if(body.length!=0){
				 drawbody(Coord, g2d);
			 }else{
				 Coord.setY(Coord.getY()-50-20); 
				 Coord.setextremeDY(Coord.getextremeDY()-50-20);
			 }
			 
			 retCoord.setextremeRX(Coord.getextremeRX()+getwidth(Coord)-minusbody(Coord)-50);
			
			 g2d.drawLine(retCoord.getX(), retCoord.getY(), retCoord.getextremeRX(), retCoord.getY());
			 
			 retCoord.setX(retCoord.getextremeRX());
			
			 retCoord.setY(retCoord.getY()-25);
			 nextArrow(retCoord, g2d);
			 retCoord.setY(retCoord.getY()+25);
			 retCoord.setY(retCoord.getY()-20);
			 nextArrow(retCoord, g2d);
			 retCoord.setY(retCoord.getY()+20);			 
				 
			 retCoord.setextremeLX(retCoord.getX()-50);
			 retCoord.setextremeRX(retCoord.getX()+50);
			 //retCoord.setY(retCoord.getY()-1);
			 nextArrow(retCoord, g2d);
			 
			 retCoord.setY(retCoord.getY()+50+20); 
			 retCoord.setextremeDY(retCoord.getY()+25);
				 
			 if(elsebody.length!=0){
				 drawelseBody(retCoord, g2d);
			 }else{
				 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY()-50, retCoord.getX(), Coord.getextremeDY());
			 }
		     drawEnd(Coord, retCoord, g2d);
		  //   Coord.setextremeRX(Coord.getX()+50);
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
     private void drawEnd(Coords Coord,Coords retCoord,Graphics2D g2d){
		 if(Coord.getextremeDY()>=retCoord.getextremeDY()){
			 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()+10);
			 Coord.setextremeDY(Coord.getextremeDY()+10) ;
			 retCoord.setextremeDY(Coord.getextremeDY());
		 }else{
			 g2d.drawLine(Coord.getX(), retCoord.getextremeDY()+10, Coord.getX(), Coord.getextremeDY());
			 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY()+10, retCoord.getX(), retCoord.getextremeDY());
			 retCoord.setextremeDY(retCoord.getextremeDY()+10) ;
			 Coord.setextremeDY(retCoord.getextremeDY());
		 }
		 g2d.drawLine(Coord.getX(), Coord.getextremeDY()-10, Coord.getX(), Coord.getextremeDY()+10); 
		 g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()); 
		 Coord.setextremeDY(Coord.getextremeDY()+10) ;
		 retCoord.setextremeDY(retCoord.getextremeDY()+10) ;    
	 }
     @Override
 	public int isIf() {
    	int sum = 1;
    	for (int i = 0; i < body.length; i++) {
     			sum+=body[i].isIf();
     		}
    	for (int i = 0; i < elsebody.length; i++) {
 			sum+=elsebody[i].isIf();
 		}
 		return sum;
 	}
     @Override
 	public int getwidth(Coords Coord) {
    	 int sum=110;
    	 int k=0;
 		for (int i = 0; i < elsebody.length; i++) {
 			sum += elsebody[i].getwidth(Coord);
 			k += elsebody[i].isIf();
 		}
 		if(k==1){
 			sum=sum-k*110+110;
 		}
 		k=0;
 		for (int i = 0; i < body.length; i++) {
 			sum += body[i].getwidth(Coord);
 			k += body[i].isIf();
 		}
 		if(k==1){
 		sum=sum-k*110+110;
 		}
 		return sum;
 	}
    public int minusbody(Coords Coord){
    	int sum=0;
 		for (int i = 0; i < elsebody.length; i++) {
 			sum+=elsebody[i].getwidth(Coord);
 		}
 		return sum;
    }
	
}