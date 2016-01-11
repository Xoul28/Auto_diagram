package auto_BSA;

import java.awt.Graphics2D;

public class Function extends FunBlock{

	public Function(Block[] body, String line) {
		super(body, line);
	}
	@Override
	public void paint( Coords Coord, Graphics2D g2d,boolean nextlinedraw) {
	    
		g2d.drawRoundRect(Coord.getX()-50, Coord.getY()-12, 100, 25, 25, 25);
	
		drawCenteredString(g2d, "Начало", Coord.getX(), Coord.getY());
		Coord.setY((Coord.getY()-12));
		nextArrow(Coord, g2d);
		Coord.setY((Coord.getY()+12));
		Coord.setY(Coord.getY()+58);
		Coord.setextremeDY(Coord.getY()+25);
		 Coord.setextremeRX(Coord.getX()+50);
		 Coord.setextremeLX(Coord.getX()-50);
		 drawbody(Coord, g2d);
		 Coord.setY(Coord.getextremeDY());
		Coord.setY(Coord.getY()+12);
		 g2d.drawRoundRect(Coord.getX()-50, Coord.getY()-12, 100, 25, 25, 25);
		drawCenteredString(g2d, "Конец", Coord.getX(), Coord.getY());
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
			// Coord.setextremeDY(Coord.getextremeDY()+25);
			 } 
		 }	 
	}
}
