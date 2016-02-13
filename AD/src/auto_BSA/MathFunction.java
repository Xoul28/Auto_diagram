package auto_BSA;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;

public class MathFunction extends MathExpression {
	private String function;
	private MathExpression arg;
	private MathExpression arg2;
	private java.util.List<MathExpression> args;
	private int argsWidth;
	private int argsMaxHeight;
	private int argsCount;
	private int arg2height;
	private int arg2width;

	public MathFunction(String function, MathExpression arg, String rest) {
		super("", rest);
		this.function = function;
		this.args = null;
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
		this.args = null;
		this.arg = arg;
		if (!(this.arg instanceof Number)) {
			this.arg = new Parentheses(this.arg, this.arg.rest);
		}
		this.arg2 = arg2;
		Font font = MathExpressionDrawer.g2d.getFont();
		Font font2 = font.deriveFont(font.getSize2D() - 4);
		MathExpressionDrawer.g2d.setFont(font2);
		arg2height = arg2.getHeight(MathExpressionDrawer.g2d);
		arg2width = arg2.getWidth(MathExpressionDrawer.g2d);
		MathExpressionDrawer.g2d.setFont(font);
	}
	
	public MathFunction(String function, java.util.List<MathExpression> list, String rest) {
		super("", rest);
		this.function = function;
		if (list.size() == 0) {
			this.arg = null;
			this.arg2 = null;
		} else {
			this.args = list;
			argsMaxHeight = 0;
			for (MathExpression expr : list) {
				if (expr != null) {
					argsWidth += expr.getWidth(MathExpressionDrawer.g2d);
					int h = expr.getHeight(MathExpressionDrawer.g2d);
					if (h > argsMaxHeight) {
						argsMaxHeight = h;
					}
					//this.rest = expr.rest;
					argsCount++;
				}
			}
		}
		if (function.equals("exp")) {
			this.arg2 = args.get(0);
			this.arg = new Number("e", rest);
			Font font = MathExpressionDrawer.g2d.getFont();
			Font font2 = font.deriveFont(font.getSize2D() - 4);
			MathExpressionDrawer.g2d.setFont(font2);
			arg2height = arg2.getHeight(MathExpressionDrawer.g2d);
			arg2width = arg2.getWidth(MathExpressionDrawer.g2d);
			MathExpressionDrawer.g2d.setFont(font);
		}
	}

	@Override
	public void draw(Graphics2D g2d, int x, int y) {
		super.draw(g2d, x, y);
		if (function.equals("log")) {
			drawString("ln", g2d, x, y);
			x += g2d.getFontMetrics().stringWidth("ln");
			int h = args.get(0).getHeight(g2d);
			int w = args.get(0).getWidth(g2d);
			g2d.drawRoundRect(x, y - h / 2 + 1, w + 9, h - 2, 8, 8);
			x += 5;
			g2d.setColor(Color.white);
			g2d.fillRect(x - 2, y - h / 2 + 1, w + 4, h - 1);
			g2d.setColor(Color.black);
			args.get(0).draw(g2d, x, y);
		} else if (function.equals("abs") || function.equals("fabs")) {
			int h = args.get(0).getHeight(g2d);
			g2d.drawLine(x, y + h / 2, x, y - h / 2);
			x += 2;
			args.get(0).draw(g2d, x, y);
			x += args.get(0).getWidth(g2d) + 1;
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
			int h = args.get(0).getHeight(g2d);
			int w = args.get(0).getWidth(g2d);
			g2d.drawLine(x, y, x + 2, y + h / 2);
			g2d.drawLine(x + 2, y + h / 2, x + 4, y - h / 2);
			g2d.drawLine( x + 4, y - h / 2, x + 4 + w, y - h / 2);
			x += 5;
			args.get(0).draw(g2d, x, y);
		} else if (args == null) {
			drawString(function + "()", g2d, x, y);
//			x += g2d.getFontMetrics().stringWidth(function);
//			int h = arg.getHeight(g2d);
//			int w = arg.getWidth(g2d);
//			g2d.drawRoundRect(x, y - h / 2 + 1, w + 9, h - 2, 8, 8);
//			x += 5;
//			g2d.setColor(Color.white);
//			g2d.fillRect(x - 2, y - h / 2 + 1, w + 4, h - 1);
//			g2d.setColor(Color.black);
//			arg.draw(g2d, x, y);
		} else {
			FontMetrics fm = g2d.getFontMetrics();
			drawString(function, g2d, x, y);
			x += fm.stringWidth(function);
			int h = argsMaxHeight;
			int w = argsWidth + fm.stringWidth(",") * (argsCount - 1);
			g2d.drawRoundRect(x, y - h / 2 + 1, w + 9, h - 2, 8, 8);
			x += 5;
			g2d.setColor(Color.white);
			g2d.fillRect(x - 2, y - h / 2 + 1, w + 4, h - 1);
			g2d.setColor(Color.black);
			// рисование аргументов из args
			for (int i = 0; i < argsCount; i++) {
				MathExpression expr = args.get(i);
				if (expr != null) {
					expr.draw(g2d, x, y);
					x += expr.getWidth(g2d);
					if (argsCount - i > 1) {
						drawString(",", g2d, x, y);
						x += fm.stringWidth(",");
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		if (function.equals("pow")) {
			return function + "(" + arg.toString() + ", " + arg2.toString() + ")";
		}
		String ans = function + "(";
		if (arg != null) {
			ans += arg.toString();
		}
		if (args != null) {
			for (MathExpression expr : args) {
				if (expr != null) {
					ans += expr.toString() + ", ";
				}
			}
			return ans.substring(0, ans.length() - 2) + ")";
		}
		return ans + ")";
	}

	@Override
	public int getHeight(Graphics2D g2d) {
		if (function.equals("pow") || function.equals("exp")) {
			return arg.getHeight(g2d) + arg2height;
		} else if (function.equals("sqrt")) {
			return args.get(0).getHeight(g2d) + 4;
		}
		int h = 0;
		if (arg != null) {
			h = arg.getHeight(g2d);
		}
		if (argsMaxHeight > h) {
			h = argsMaxHeight;
		}
		return h;
	}

	@Override
	public int getWidth(Graphics2D g2d) {
		if (function.equals("log")) {
			return args.get(0).getWidth(g2d) + g2d.getFontMetrics().stringWidth("ln") + 15;
		} else if (function.equals("abs") || function.equals("fabs")) {
			return args.get(0).getWidth(g2d) + 4;
		} else if (function.equals("pow") || function.equals("exp")) {
			return arg.getWidth(g2d) + arg2width;
		} else if (function.equals("sqrt")) {
			return args.get(0).getWidth(g2d) + 6;
		}
		int w = 0;
		if (arg != null) {
			w += arg.getWidth(g2d);
		}
		if (arg2 != null) {
			w += arg2.getWidth(g2d);
		}
		if (args == null) {
			return g2d.getFontMetrics().stringWidth(function + "()");
		}
		w += g2d.getFontMetrics().stringWidth(function) + 15;
		w += argsWidth;
		return w;
	}

}
