package auto_BSA;

import java.awt.Graphics2D;

public class Comment extends Statement {
	Coords Coord;

	public Comment(String line) {
		super(line);
	}

	public Comment(String line, Coords Coord) {
		super(line);
		this.Coord = new Coords(Coord);
	}
}
