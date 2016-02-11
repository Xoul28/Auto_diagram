package auto_BSA;

import java.awt.Graphics2D;

public class Comment extends Statement {
	Coords Coord;
	MathExpression mathExp;

	public Comment(String line) {
		super(line);
		this.mathExp = null;
	}

	public Comment(String line, Coords Coord) {
		super(line);
		this.Coord = new Coords(Coord);
		this.mathExp = null;
	}
	
	public Comment(String line, Coords Coord, MathExpression mathExp) {
		super(line);
		this.Coord = new Coords(Coord);
		this.mathExp = mathExp;
	}
}
