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
	nextArrow(Coord, g2d);
	Coord.setY(Coord.getY()+48);
	 for(int i = 0;i<body.length;i++){
		 body[i].paint(Coord,g2d,true);
		  
	 }
	 Coord.setY(Coord.getY()+50+20);
	 Coord.setY(Coord.getY()-25+12);
	 g2d.drawRoundRect(Coord.getX()-50, Coord.getY()-12, 100, 25, 25, 25);
	drawCenteredString(g2d, "Конец", Coord.getX(), Coord.getY());
	}
    @Override
	public void nextArrow(Coords Coord,Graphics2D g2d) {
		 g2d.drawLine(Coord.getX(), Coord.getY()+13, Coord.getX(), Coord.getY()+13+10);
		 g2d.drawLine(Coord.getX(), Coord.getY()+13+10, Coord.getX()-2, Coord.getY()+12+8);
		 g2d.drawLine(Coord.getX(), Coord.getY()+13+10, Coord.getX()+2, Coord.getY()+12+8);
	}
}
