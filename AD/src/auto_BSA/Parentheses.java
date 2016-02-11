package auto_BSA;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Parentheses extends MathExpression {
	private MathExpression arg;

	public Parentheses(MathExpression arg, String rest) {
		super("", rest);
		this.arg = arg;
	}

	@Override
	public void draw(Graphics2D g2d, int x, int y) {
		super.draw(g2d, x, y);
		if (arg instanceof Operator && ((Operator) arg).isDivision()) {
			arg.draw(g2d, x, y);
			return;
		}
		int h = arg.getHeight(g2d);
		int w = arg.getWidth(g2d);
		g2d.drawRoundRect(x, y - h / 2 + 1, w + 12, h - 2, 8, 8);
		x += 5;
		g2d.setColor(Color.white);
		g2d.fillRect(x - 2, y - h / 2 + 1, w + 7, h - 1);
		g2d.setColor(Color.black);
		arg.draw(g2d, x, y);
	}
	
	public MathExpression getArg() {
		return arg;
	}
	
	@Override
	public String toString() {
		return "(" + arg.toString()  + ")";
	}

	@Override
	public int getHeight(Graphics2D g2d) {
		return arg.getHeight(g2d) + 2;
	}

	@Override
	public int getWidth(Graphics2D g2d) {
		//FontMetrics fm = g2d.getFontMetrics();
		return arg.getWidth(g2d) + 15;
	}

}
