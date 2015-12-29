package auto_BSA;

import java.awt.Graphics2D;

public class ForCycle extends FunBlock{
   
    
	public ForCycle(Block[] body, String line) {
		super(body, line);
	}

	@Override
	public void paint( Coords Coord, Graphics2D g2d) {
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
		
		 g2d.drawString(line, Coord.getX()-32, Coord.getY()+5);
		 nextArrow(Coord, g2d);
		 Coord.setY(Coord.getY()+50+10);
		 for(int i = 0;i<body.length;i++){
			 body[i].paint(Coord,g2d);
			  
		 }
		
	}
  
	
	
}