package auto_BSA;

import java.awt.Graphics2D;

public class Number extends MathExpression {

	public Number(String acc, String rest) {
		super(acc, rest);
	}

	@Override
	public void draw(Graphics2D g2d, int x, int y) {
		super.draw(g2d, x, y);
		drawString(acc, g2d, x, y);
	}
	
	@Override
	public String toString() {
		return acc;
	}

	@Override
	public int getHeight(Graphics2D g2d) {
		return g2d.getFontMetrics().getHeight() - 2;
	}

	@Override
	public int getWidth(Graphics2D g2d) {
		return g2d.getFontMetrics().stringWidth(acc);
	}

}
