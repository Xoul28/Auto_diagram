package auto_BSA;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MathFunction extends MathExpression {
	private String function;
	private MathExpression arg;
	private MathExpression arg2;
	private int arg2height;
	private int arg2width;

	public MathFunction(String function, MathExpression arg, String rest) {
		super("", rest);
		this.function = function;
		this.arg = arg;
		if (function.equals("exp")) {
			this.arg2 = arg;
			this.arg = new Number("e", rest);
			Font font = MathExpressionDrawer.g2d.getFont();
			Font font2 = font.deriveFont(font.getSize2D() - 4);
			MathExpressionDrawer.g2d.setFont(font2);
			arg2height = arg2.getHeight(MathExpressionDrawer.g2d);
			arg2width = arg2.getWidth(MathExpressionDrawer.g2d);
			MathExpressionDrawer.g2d.setFont(font);
		} else {
			this.arg2 = null;
		}
	}
	
	public MathFunction(String function, MathExpression arg, MathExpression arg2, String rest) {
		super("", rest);
		this.function = function;
		this.arg = arg;
		this.arg2 = arg2;
		Font font = MathExpressionDrawer.g2d.getFont();
		Font font2 = font.deriveFont(font.getSize2D() - 4);
		MathExpressionDrawer.g2d.setFont(font2);
		arg2height = arg2.getHeight(MathExpressionDrawer.g2d);
		arg2width = arg2.getWidth(MathExpressionDrawer.g2d);
		MathExpressionDrawer.g2d.setFont(font);
	}

	@Override
	public void draw(Graphics2D g2d, int x, int y) {
		super.draw(g2d, x, y);
		if (function.equals("log")) {
			drawString("ln", g2d, x, y);
			x += g2d.getFontMetrics().stringWidth("ln");
			int h = arg.getHeight(g2d);
			int w = arg.getWidth(g2d);
			g2d.drawRoundRect(x, y - h / 2 + 1, w + 12, h - 2, 8, 8);
			x += 5;
			g2d.clearRect(x - 2, y - h / 2 + 1, w + 7, h - 1);
			arg.draw(g2d, x, y);
		} else if (function.equals("abs")) {
			int h = arg.getHeight(g2d);
			g2d.drawLine(x, y + h / 2, x, y - h / 2);
			x += 2;
			arg.draw(g2d, x, y);
			x += arg.getWidth(g2d) + 1;
			g2d.drawLine(x, y + h / 2, x, y - h / 2);
		} else if (function.equals("pow")) {
			Font font = g2d.getFont();
			Font font2 = font.deriveFont(font.getSize2D() - 4);
			int h = arg.getHeight(g2d);
			g2d.setFont(font2);
			arg2height = arg2.getHeight(g2d);
			arg2width = arg2.getWidth(g2d);
			g2d.setFont(font);
			arg.draw(g2d, x, y);
			x += arg.getWidth(g2d);
			g2d.setFont(font2);
			arg2.draw(g2d, x, y - h / 2 + 2);
			//System.out.println(h + " " + arg2height);
			g2d.setFont(font);
		} else if (function.equals("exp")) {
			Font font = g2d.getFont();
			Font font2 = font.deriveFont(font.getSize2D() - 4);
			int h = arg.getHeight(g2d);
			g2d.setFont(font2);
			arg2height = arg2.getHeight(g2d);
			arg2width = arg2.getWidth(g2d);
			g2d.setFont(font);
			arg.draw(g2d, x, y);
			x += arg.getWidth(g2d);
			g2d.setFont(font2);
			arg2.draw(g2d, x, y - h / 2 + 2);
			//System.out.println(h + " " + arg2height);
			g2d.setFont(font);
		} else if (function.equals("sqrt")) {
			int h = arg.getHeight(g2d);
			int w = arg.getWidth(g2d);
			g2d.drawLine(x, y, x + 2, y + h / 2);
			g2d.drawLine(x + 2, y + h / 2, x + 4, y - h / 2);
			g2d.drawLine( x + 4, y - h / 2, x + 4 + w, y - h / 2);
			x += 5;
			arg.draw(g2d, x, y);
		} else {
			drawString(function, g2d, x, y);
			x += g2d.getFontMetrics().stringWidth(function);
			int h = arg.getHeight(g2d);
			int w = arg.getWidth(g2d);
			g2d.drawRoundRect(x, y - h / 2 + 1, w + 12, h - 2, 8, 8);
			x += 5;
			g2d.clearRect(x - 2, y - h / 2 + 1, w + 7, h - 1);
			arg.draw(g2d, x, y);
		}
	}
	
	@Override
	public String toString() {
		return function + "(" + arg.toString() + ")";
	}

	@Override
	public int getHeight(Graphics2D g2d) {
		if (function.equals("pow") || function.equals("exp")) {
			return arg.getHeight(g2d) + arg2height;
		} else if (function.equals("sqrt")) {
			return arg.getHeight(g2d) + 4;
		}
		return arg.getHeight(g2d);
	}

	@Override
	public int getWidth(Graphics2D g2d) {
		if (function.equals("log")) {
			return arg.getWidth(g2d) + g2d.getFontMetrics().stringWidth("ln") + 15;
		} else if (function.equals("abs")) {
			return arg.getWidth(g2d) + 4;
		} else if (function.equals("pow") || function.equals("exp")) {
			return arg.getWidth(g2d) + arg2width;
		} else if (function.equals("sqrt")) {
			return arg.getWidth(g2d) + 6;
		}
		return arg.getWidth(g2d) + g2d.getFontMetrics().stringWidth(function) + 15;
	}

}
