package auto_BSA;

import java.awt.Graphics2D;
import java.util.Arrays;

public abstract class FunBlock extends Block {

	public FunBlock(Block[] body, String line) {
		this.body = body;
		this.line = line;
	}

	Block body[];

	public void drawbody(Coords Coord, Graphics2D g2d) {
		for (int i = 0; i < body.length; i++) {
			if (i != (body.length - 1)) {
				body[i].paint(Coord, g2d, true);
				if (body[i].isBreak() != 0 || body[i].isContinue() != 0 || body[i].isReturn() != 0) {
					break;
				}
				if (body[i].isIf() == 1) {
					if (body[i].isThereABreakInBody()
							&& body[i].isThereABreakInElseBody()) {
						body[i + 1] = new BreakLine();
					}
					if (body[i].isThereAContinueInBody()
							&& body[i].isThereAContinueInElseBody()) {
						body[i + 1] = new ContinueLine();
					}
					if (body[i].isThereAReturnInBody()
							&& body[i].isThereAReturnInElseBody()) {
						body[i].setNoRet();
						body[i+1] = new ReturnStatement("");
						break;
					}
				}
				Coord.setY(Coord.getextremeDY() + 25);
				Coord.setextremeDY(Coord.getextremeDY() + 50);
			} else {
				body[i].paint(Coord, g2d, false);
				if (body[i].isIf() == 1) {
					if (body[i].isThereABreakInBody()
							&& body[i].isThereABreakInElseBody()) {
						body[i] = new BreakLine();
						body[i].paint(Coord, g2d, true);
					}
					if (body[i].isThereAContinueInBody()
							&& body[i].isThereAContinueInElseBody()) {
						body[i] = new ContinueLine();
						body[i].paint(Coord, g2d, true);
					}
					if (body[i].isThereAReturnInBody()
							&& body[i].isThereAReturnInElseBody()) {
						body[i].setNoRet();
						Block blad[] = new Block[body.length+1];
						blad = Arrays.copyOf(body,body.length);
						body = new Block[blad.length];
						body = Arrays.copyOf(blad, blad.length+1);
						body[i+1] = new ReturnStatement("");
						break;
					}

				}
				// Coord.setextremeDY(Coord.getextremeDY()+25);
			}
		}
	}

	@Override
	public int isIf() {
		return 0;
	}

	@Override
	public int getCyclesMargin() {
		int sum = 0;
		for (int i = 0; i < body.length; i++) {
			sum += body[i].getCyclesMargin();
		}
		return sum;
	}

	@Override
	public int getwidth(Coords Coord) {
		
		return 0;
	}

	public int getelsecyclemargin() {
		return 0;
	}

	@Override
	public boolean isThereABreakInBody() {
		for (int i = 0; i < body.length; i++) {
			if (body[i].isBreak() == 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int breakMargin() {
		for (int i = 0; i < body.length; i++) {
			if (body[i].breakMargin() == 30) {
				return 30;
			}
		}
		return 0;
	}

	@Override
	public int continueMargin() {
		for (int i = 0; i < body.length; i++) {
			if (body[i].continueMargin() == 30) {
				return 30;
			}
		}
		return 0;
	}

	@Override
	public boolean isThereABreakInElseBody() {

		return false;
	}

	@Override
	public boolean isThereAContinueInBody() {
		for (int i = 0; i < body.length; i++) {
			if (body[i].isContinue() == 1) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereAReturnInElseBody() {
		
		return false;
	}

	public boolean isThereAReturnInBody() {
		for (int i = 0; i < body.length; i++) {
			if (body[i].isReturn() == 1) {
				return true;
			}
		}
		return false;
	}
}
