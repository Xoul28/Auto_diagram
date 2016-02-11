package auto_BSA;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statement extends Block {

	public Statement(String line) {
		this.line = line;
	}

	public Statement() {
	}

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT);
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT);
		 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT);
		 g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY()+HALFOFNORMALHIGHT);
		 drawContent(Coord, g2d);
		if (nextlinedraw) {
			nextArrow(Coord, g2d);
			Coord.setextremeDY(Coord.getextremeDY() + 20);
		}
	}
	
	protected void drawContent(Coords Coord, Graphics2D g2d) {
		MathExpression mathExp = null;
		 try {
			Pattern expr = Pattern.compile("(.*=|return\\s+)(.*)", Pattern.DOTALL);
			Matcher matcher = expr.matcher(line);
			if (matcher.matches()) {
				String str = matcher.group(1);
				String strEnd = matcher.group(2);
				strEnd = strEnd.replaceAll("\\s+", "");
				FontMetrics fm = g2d.getFontMetrics();
				int w = fm.stringWidth(str);
				MathExpressionDrawer.g2d = g2d;
				mathExp = MathExpressionDrawer.Parse(strEnd, g2d);
				int w2 = mathExp.getWidth(g2d);
				int sumw = w + w2;
				if (w + w2 > HALFOFNORMALLENGHT * 2) {
					// TODO тут создать комментарий с mathExp
					Function.CommentList.add(new Comment(str, Coord, mathExp));
				} else {
					mathExp.draw(g2d, Coord.getX() - sumw / 2 + w, Coord.getY());
					System.out.println(mathExp);
					drawCenteredString(g2d, str, Coord.getX() - sumw / 2 + w / 2, Coord.getY());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (line.length() > THEBIGGESTLENGHT && mathExp == null) {
			Function.CommentList.add(new Comment(line, Coord));
		} else if (mathExp == null) {
			drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		}
	}

	@Override
	public int getwidth(Coords Coord) {
		return 0;
	}

	@Override
	public int isIf() {
		return 0;
	}

	@Override
	public int getCyclesMargin() {
		return 0;
	}

	@Override
	public boolean isThereABreakInElseBody() {
		// TODO Auto-generated method stub
		return false;
	}

}
