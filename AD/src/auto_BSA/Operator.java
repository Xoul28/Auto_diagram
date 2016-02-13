package auto_BSA;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Operator extends MathExpression {
	private String op;
	private MathExpression arg1;
	private MathExpression arg2;
	private static final int MARGIN = 1;

	public Operator(String op, MathExpression arg1, MathExpression arg2, String rest) {
		super("", rest);
		this.op = op;
		this.arg1 = arg1;
		this.arg2 = arg2;
		if (this.arg1 instanceof Parentheses) {
			this.arg1 = ((Parentheses) this.arg1).getArg();
		}
		if (this.arg2 instanceof Parentheses) {
			this.arg2 = ((Parentheses) this.arg2).getArg();
		}
	}
	
	public boolean isDivision() {
		return op.equals("/");
	}

	@Override
	public void draw(Graphics2D g2d, int x, int y) {
		super.draw(g2d, x, y);
		if (op.equals("/")) {
			FontMetrics fm = g2d.getFontMetrics();
			int h1 = arg1.getHeight(g2d);
			int h2 = arg2.getHeight(g2d);
			int w1 = arg1.getWidth(g2d);
			int w2 = arg2.getWidth(g2d);
			int max;
			if (w1 > w2) {
				max = w1;
			} else {
				max = w2;
			}
			arg1.draw(g2d, x + max / 2 - w1 / 2, y - h1 / 2);
			g2d.drawLine(x, y, x + max, y);
			arg2.draw(g2d, x + max / 2 - w2 / 2, y + h2 / 2);
		} else {
			arg1.draw(g2d, x, y);
			x += arg1.getWidth(g2d);
			if (op.equals("*")) {
				x += drawMulOperator(g2d, x, y);
			} else if (op.equals("-")) {
				x += drawMinusOperator(g2d, x, y);
			} else {
				drawString(op, g2d, x, y);
				x += g2d.getFontMetrics().stringWidth(op);
			}
			arg2.draw(g2d, x, y);
		}
	}
	
	// TODO font independent!!!
	private int drawMinusOperator(Graphics2D g2d, int x, int y) {
		g2d.drawLine(x + 1, y, x + 3, y);
		return 5;
	}
	
	// TODO font independent!!!
	private int drawMulOperator(Graphics2D g2d, int x, int y) {
		g2d.fillOval(x + 1, y - 1, 2, 2);
		return 5;
	}

	@Override
	public String toString() {
		return arg1.toString() + " " + op + " " + arg2.toString();
	}

	@Override
	public int getHeight(Graphics2D g2d) {
		int h1 = arg1.getHeight(g2d);
		int h2 = arg2.getHeight(g2d);
		if (op.equals("/")) {
			return (h1 > h2)? 2 * h1 : 2 * h2;
		} else {
			if (h1 > h2) {
				return h1;
			} else {
				return h2;
			}
		}
	}

	@Override
	public int getWidth(Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		int w1 = arg1.getWidth(g2d);
		int w2 = arg2.getWidth(g2d);
		if (op.equals("/")) {
			if (w1 > w2) {
				return w1 + 1;
			} else {
				return w2 + 1;
			}
		} else if (op.equals("*")) {
			return w1 + 5 + w2;
		} else if (op.equals("-")) {
			return w1 + 5 + w2;
		} else {
			return w1 + fm.stringWidth(op) + w2;
		}
	}

}
