package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;

public class ForCycle extends FunBlock{
   
    private int bias;
	public ForCycle(Block[] body, String line) {
		super(body, line);
	}

	@Override
	public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
		Coord.incInvestedCol();
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
		
		 drawCenteredString(g2d, line, Coord.getX(), Coord.getY());
		 nextArrow(Coord, g2d);
		 Coords retCoord = new Coords(Coord.getX()-lenhn,Coord.getY()+lenvn);
		 Coord.setY(Coord.getY()+50+20);
		
		 drawbody(Coord, g2d);
		 
		 if(Coord.getInvestedCol()>0) {
			 Coord.incB();
			 returning(Coord, retCoord, g2d);
			 Coord.incBias();
			 
		 }else{
			 Coord.decBias();
			 returning(Coord, retCoord, g2d);
			 Coord.decB();
		 }
		 Coord.decInvestedCol();
		
		
	}
  private void returning(Coords Coord,Coords retCoord,Graphics2D g2d){
	  Coord.setY(Coord.getY()+50+10+Coord.getB());
	  g2d.drawLine(Coord.getX(),Coord.getY()-30,Coord.getX(),Coord.getY()-35-Coord.getB());
	  g2d.drawLine(Coord.getX(),Coord.getY()-30,Coord.getX()-Coord.getBias()-50,Coord.getY()-30);
	  
	  // left arrow
	  g2d.drawLine(Coord.getX()-Coord.getBias()-50, Coord.getY()-30, Coord.getX()-Coord.getBias()-50+2, Coord.getY()-30-2);
	  g2d.drawLine(Coord.getX()-Coord.getBias()-50, Coord.getY()-30, Coord.getX()-Coord.getBias()-50+2, Coord.getY()-30+2);
	  
	  g2d.drawLine(Coord.getX()-Coord.getBias()-50,Coord.getY()-30,retCoord.getX()-Coord.getBias(),retCoord.getY()-25);
	  
	  // top arrow
	  g2d.drawLine(retCoord.getX()-Coord.getBias(),retCoord.getY()-25, retCoord.getX()-Coord.getBias()-2,retCoord.getY()-25+2);
	  g2d.drawLine(retCoord.getX()-Coord.getBias(),retCoord.getY()-25, retCoord.getX()-Coord.getBias()+2,retCoord.getY()-25+2);
	  
	  g2d.drawLine(retCoord.getX()-Coord.getBias(),retCoord.getY()-25,retCoord.getX(),retCoord.getY()-25);	 
	  g2d.drawLine(retCoord.getX()+100,retCoord.getY()-25,retCoord.getX()+100+Coord.getBias(),retCoord.getY()-25);	
	  g2d.drawLine(retCoord.getX()+100+Coord.getBias(),retCoord.getY()-25,retCoord.getX()+100+Coord.getBias(),Coord.getY()-25);	
	  g2d.drawLine(retCoord.getX()+100+Coord.getBias(),Coord.getY()-25,retCoord.getX()+50,Coord.getY()-25);	
	  g2d.drawLine(retCoord.getX()+50,Coord.getY()-25,retCoord.getX()+50,Coord.getY()-15+Coord.getB());	
	 
	  g2d.setColor(Color.white);
	 //g2d.drawLine(Coord.getX(),Coord.getY()-23,Coord.getX(),Coord.getY()-23-Coord.getB());
	// g2d.drawLine(Coord.getX(),Coord.getY()-35-5,Coord.getX(),Coord.getY()-35-Coord.getB()-5);
	  g2d.setColor(Color.black);
	
	  
	  Coord.setY(Coord.getY()-50-10+Coord.getB());
	  
	  
  }
	
	
}