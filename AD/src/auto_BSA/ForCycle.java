package auto_BSA;


import java.awt.Graphics2D;

public class ForCycle extends Cycles {

	public ForCycle(Block[] body, String line) {
		super(body, line);
		ret  = new ForCycleReturning();
	}

	@Override
	public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {  
	  
		 int lenh=25,lenv=0;
		 //top
		 g2d.drawLine(Coord.getX()-lenh, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+lenh, Coord.getY()-HALFOFNORMALHIGHT);
		 //leftupangle
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()-lenv, Coord.getX()-lenh, Coord.getY()-HALFOFNORMALHIGHT);
		//righttopangle
		 g2d.drawLine(Coord.getX()+lenh, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()-lenv);
		//leftdownangle
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()+lenv, Coord.getX()-lenh, Coord.getY()+HALFOFNORMALHIGHT);
		//rightdownangle
		 g2d.drawLine(Coord.getX()+lenh, Coord.getY()+HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()+lenv);
		 //bottom
		 g2d.drawLine(Coord.getX()-lenh, Coord.getY()+HALFOFNORMALHIGHT, Coord.getX()+lenh, Coord.getY()+HALFOFNORMALHIGHT);
	
		 drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		
		 nextArrow(Coord, g2d);
		 
		 Coords retCoord = new Coords(Coord.getX(),Coord.getY());
		 retCoord.setextremeLX(retCoord.getX()-50);
		 retCoord.setextremeRX(retCoord.getX()+50);
		 Coord.setextremeRX(retCoord.getX()+50);
		 
		 Coord.setY(Coord.getY()+50+20);
		 Coord.setextremeDY(Coord.getextremeDY()+50+20);
		 
		 if(body.length!=0){
			 drawbody(Coord, g2d);
		 }else{
			 Coord.setY(Coord.getY()-50-20); 
			 Coord.setextremeDY(Coord.getextremeDY()-50-20);
		 }
		if(isThereABreakInBody()){
			nextArrow(retCoord, g2d);
			Coord.setY(Coord.getextremeDY()+50);
		}else{
	     
		 Coord.setextremeRX(Coord.getextremeRX()+getCyclesMargin()+getwidth(Coord)+breakMargin());
		 drawbreak(Coord, g2d, retCoord.getY());
		 Coord.setextremeLX(Coord.getextremeLX()-getCyclesMargin()-continueMargin());
		 drawContinue(Coord, g2d, retCoord.getY());
		 Coord.setextremeDY(Coord.getextremeDY()+5);
		
	     ret.returning(Coord, retCoord, g2d); 
	     
	     Coord.setextremeDY(Coord.getextremeDY()+10);
		 Coord.setextremeLX(Coord.getextremeLX()+getCyclesMargin());
		 Coord.setextremeRX(Coord.getX()+50);
		}
	}
}