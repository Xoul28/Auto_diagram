package auto_BSA;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class MathExpression {
	public String acc;
    public String rest;
    
    public MathExpression(String acc, String rest) {
		this.acc = acc;
		this.rest = rest;
	}
	
	public void draw(Graphics2D g2d, int x, int y) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							 RenderingHints.VALUE_ANTIALIAS_ON);
		int h = getHeight(g2d);
		int w = getWidth(g2d);
	}
	
	protected void drawWH(Graphics2D g2d, int x, int y) {
		int h = getHeight(g2d);
		int w = getWidth(g2d);
		//* FOR DEBUG
		g2d.drawLine(x, y + h / 2, x, y - h / 2);
		g2d.drawLine(x, y + h / 2, x + w, y + h / 2);
		g2d.drawLine(x, y - h / 2, x + w, y - h / 2);
		g2d.drawLine(x + w, y + h / 2, x + w, y - h / 2);
		g2d.setColor(Color.BLACK);
		//*/
	}
	
	public static void drawString(String str, Graphics2D g2d, int x, int y) {
		FontMetrics fm = g2d.getFontMetrics();
		int yy = y - fm.getDescent() + fm.getHeight() / 2;
		g2d.drawString(str, x, yy);
	}
	
	public abstract int getHeight(Graphics2D g2d);
	public abstract int getWidth(Graphics2D g2d);
	
}
