package auto_BSA;

import java.awt.Graphics2D;
import java.util.Arrays;

public class SelectorBlock extends FunBlock {
	Block elsebody[];
	private boolean isret = true;

	public SelectorBlock(Block[] body, String line, Block[] elsebody) {
		super(body, line);
		this.elsebody = elsebody;
	}

	@Override
	public void paint(Coords Coord, Graphics2D g2d, boolean nextlinedraw) {
			
		     //lefttopline
			 g2d.drawLine(Coord.getX()-HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
			   //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()+HALFOFNORMALLENGHT, Coord.getY());
			  //rightbottomline
			 g2d.drawLine(Coord.getX()+HALFOFNORMALLENGHT, Coord.getY(), Coord.getX(), Coord.getY()+HALFOFNORMALHIGHT);
			  //righttopline
			 g2d.drawLine(Coord.getX(), Coord.getY()-HALFOFNORMALHIGHT, Coord.getX()-HALFOFNORMALLENGHT, Coord.getY());
			 g2d.drawString("да", Coord.getX()+HALFOFNORMALLENGHT-HALFOFNORMALHIGHT-17, Coord.getY()+HALFOFNORMALHIGHT+7);
			 g2d.drawString("нет", Coord.getX()+HALFOFNORMALLENGHT-HALFOFNORMALHIGHT+30, Coord.getY()-HALFOFNORMALHIGHT+17);
		if (line.length() > THEBIGGESTLENGHT) {
			Function.CommentList.add(new Comment(line, Coord));
		} else {
			drawCenteredLines(g2d, line, Coord.getX(), Coord.getY());
		}

		nextArrow(Coord, g2d);
		// ���������� ���������� �����������
		Coords retCoord = new Coords(Coord.getX() + 50, Coord.getY());

		Coord.setY(Coord.getY() + 50 + 20);
		Coord.setextremeDY(Coord.getextremeDY() + 50 + 20);

		if (body.length != 0) {
			drawbody(Coord, g2d);
		} else {
			Coord.setY(Coord.getY() - 50 - 20);
			Coord.setextremeDY(Coord.getextremeDY() - 50 - 20);
		}
		if (isThereABreakInElseBody() && isThereABreakInBody()) {
			Coord.breakingList.removeLast();
		}
		if (isThereAContinueInElseBody() && isThereAContinueInBody()) {
			Coord.continueList.removeLast();
		}
		int marg = 50;

		retCoord.setextremeRX(Coord.getextremeRX() + getwidth(Coord)
				- minusbody(Coord) - marg);

		g2d.drawLine(retCoord.getX(), retCoord.getY(), retCoord.getextremeRX(),
				retCoord.getY());

		retCoord.setX(retCoord.getextremeRX());

		retCoord.setY(retCoord.getY() - 25);
		nextArrow(retCoord, g2d);
		retCoord.setY(retCoord.getY() + 25);
		retCoord.setY(retCoord.getY() - 20);
		nextArrow(retCoord, g2d);
		retCoord.setY(retCoord.getY() + 20);

		retCoord.setextremeLX(retCoord.getX() - 50);
		retCoord.setextremeRX(retCoord.getX() + 50);
		// retCoord.setY(retCoord.getY()-1);
		nextArrow(retCoord, g2d);

		retCoord.setY(retCoord.getY() + 50 + 20);
		retCoord.setextremeDY(retCoord.getY() + 25);

		if (elsebody.length != 0) {
			drawelseBody(retCoord, g2d);

		} else {
			retCoord.setextremeDY(retCoord.getextremeDY() - 50);
			g2d.drawLine(retCoord.getX(), retCoord.getextremeDY() - 45,
					retCoord.getX(), Coord.getextremeDY());
		}
		if (isThereABreakInElseBody() && isThereABreakInBody()) {
			retCoord.breakingList.removeLast();
		}
		if (isThereAContinueInBody() && isThereAContinueInElseBody()) {
			retCoord.continueList.removeLast();
		}
		for (Coords iter : retCoord.breakingList) {
			Coord.breakingList.add(iter);
		}
		for (Coords iter : retCoord.continueList) {
			Coord.continueList.add(iter);
		}
		for (Coords iter : retCoord.returnList) {
			Coord.returnList.add(iter);
		}
		
		
		if(isret)
		drawEnd(Coord, retCoord, g2d);
		else{
			countingofbody(Coord);
			countingofbody(retCoord);
		}
	

		// Coord.setextremeRX(Coord.getX()+50);
	}

	private void drawelseBody(Coords retCoord, Graphics2D g2d) {
		for (int i = 0; i < elsebody.length; i++) {
			if (i != (elsebody.length - 1)) {
				elsebody[i].paint(retCoord, g2d, true);
				if (elsebody[i].isBreak() == 1 || elsebody[i].isContinue() != 0 || elsebody[i].isReturn() !=0) {
					break;
				}
				if (elsebody[i].isIf() == 1) {
					if (elsebody[i].isThereABreakInBody()
							&& elsebody[i].isThereABreakInElseBody()) {
						elsebody[i + 1] = new BreakLine();
					}
					if (elsebody[i].isThereAContinueInBody()
							&& elsebody[i].isThereAContinueInElseBody()) {
						elsebody[i + 1] = new ContinueLine();
					}
					if (elsebody[i].isThereAReturnInBody()
							&& elsebody[i].isThereAReturnInElseBody()) {
						elsebody[i].setNoRet();
						elsebody[i+1] = new ReturnStatement("");
						break;
					}
				}
				retCoord.setY(retCoord.getY() + 50 + 20);
				retCoord.setY(retCoord.getextremeDY() + 25);
				retCoord.setextremeDY(retCoord.getextremeDY() + 50);
			} else {
				elsebody[i].paint(retCoord, g2d, false);
				if (elsebody[i].isIf() == 1) {
					if (elsebody[i].isThereABreakInBody()
							&& elsebody[i].isThereABreakInElseBody()) {
						elsebody[i] = new BreakLine();
						elsebody[i].paint(retCoord, g2d, true);
					}
					if (elsebody[i].isThereAContinueInBody()
							&& elsebody[i].isThereAContinueInElseBody()) {
						elsebody[i] = new ContinueLine();
						elsebody[i].paint(retCoord, g2d, true);
					}
					if (elsebody[i].isThereAReturnInBody()
							&& elsebody[i].isThereAReturnInElseBody()) {
						elsebody[i].setNoRet();
						Block blad[] = new Block[elsebody.length+1];
						blad = Arrays.copyOf(elsebody,elsebody.length+1);
						elsebody = new Block[blad.length];
						elsebody = Arrays.copyOf(blad, blad.length);
						elsebody[i+1] = new ReturnStatement("");
						break;
					}
				}
			}
		}
	}
     private void drawEnd(Coords Coord,Coords retCoord,Graphics2D g2d){
    	 if(!isThereABreakInBody()&&!isThereABreakInElseBody()&&!isThereAContinueInBody()&&!isThereAContinueInElseBody()&&!isThereAReturnInBody()&&!isThereAReturnInElseBody()){
		 if(Coord.getextremeDY()>=retCoord.getextremeDY()){
			 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()+10);
			 Coord.setextremeDY(Coord.getextremeDY()+10) ;
			 retCoord.setextremeDY(Coord.getextremeDY());
		 }else{
			 g2d.drawLine(Coord.getX(), retCoord.getextremeDY()+10, Coord.getX(), Coord.getextremeDY());
			 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY()+10, retCoord.getX(), retCoord.getextremeDY());
			 retCoord.setextremeDY(retCoord.getextremeDY()+10) ;
			 Coord.setextremeDY(retCoord.getextremeDY());
		 }
		 g2d.drawLine(Coord.getX(), Coord.getextremeDY()-10, Coord.getX(), Coord.getextremeDY()+10); 
		 g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()); 
		  //arrows
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX()+2, Coord.getextremeDY()-2);
		  g2d.drawLine(Coord.getX(),Coord.getextremeDY(), Coord.getX()+2, Coord.getextremeDY()+2);
		 Coord.setextremeDY(Coord.getextremeDY()+10) ;
		 retCoord.setextremeDY(retCoord.getextremeDY()+10) ;    
	 }
    	 if(isThereABreakInBody()&&!isThereABreakInElseBody()||(isThereAContinueInBody()&&!isThereAContinueInElseBody())||(isThereAReturnInBody()&&!isThereAReturnInElseBody())){
    		 if(retCoord.getextremeDY()>Coord.getextremeDY()){
    			g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(), retCoord.getX(), retCoord.getextremeDY()+15);
    		 	Coord.setextremeDY(retCoord.getextremeDY()+15) ;
    		 	retCoord.setextremeDY(retCoord.getextremeDY()+15) ;
    		 	//arrows
  			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX()+2, Coord.getextremeDY()-2);
  			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(), Coord.getX()+2, Coord.getextremeDY()+2);
    		 	g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), retCoord.getX(), retCoord.getextremeDY()); 
    		 	g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), Coord.getX(), retCoord.getextremeDY()+15);
    		 	Coord.setextremeDY(retCoord.getextremeDY()+15) ;
    		 }else{
    			 //down
    			 g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(), retCoord.getX(), Coord.getextremeDY()+15);
    			 
    			 retCoord.setextremeDY(Coord.getextremeDY()+15) ;
    			 Coord.setextremeDY(retCoord.getextremeDY()) ;
    			  //arrows
    			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(),Coord.getX()+2, Coord.getextremeDY()-2);
    			  g2d.drawLine(Coord.getX(),Coord.getextremeDY(), Coord.getX()+2, Coord.getextremeDY()+2);
    			 
    			 g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), retCoord.getX(), retCoord.getextremeDY()); 
    			 g2d.drawLine(Coord.getX(), retCoord.getextremeDY(), Coord.getX(), retCoord.getextremeDY()+5);
    			 Coord.setextremeDY(Coord.getextremeDY()+5) ;
    			 Coord.setY(Coord.getextremeDY()+25) ;
    			
    		 }
		}
		if (isThereABreakInElseBody() && !isThereABreakInBody()
				|| (!isThereAContinueInBody() && isThereAContinueInElseBody())
				||(!isThereAReturnInBody()&&isThereAReturnInElseBody())) {
			if (retCoord.getextremeDY() < Coord.getextremeDY()) {
				Coord.setextremeDY(Coord.getextremeDY() + 20);
				g2d.drawLine(Coord.getX(), Coord.getextremeDY() - 20,
						Coord.getX(), Coord.getextremeDY());
			} else {
				g2d.drawLine(Coord.getX(), Coord.getextremeDY(), Coord.getX(),
						retCoord.getextremeDY() + 5);
				Coord.setextremeDY(retCoord.getextremeDY() + 5);
			}

		}
		if (isThereABreakInElseBody() && isThereABreakInBody()
				|| (isThereAContinueInBody() && isThereAContinueInElseBody())
				||(isThereAReturnInBody() && isThereAReturnInElseBody())) {
			g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(),
					retCoord.getX(), retCoord.getextremeDY() - 7);
			g2d.drawLine(Coord.getX(), Coord.getextremeDY(), Coord.getX(),
					Coord.getextremeDY() - 7);
			if (Coord.getextremeDY() >= retCoord.getextremeDY()) {
				g2d.drawLine(retCoord.getX(), retCoord.getextremeDY(),
						retCoord.getX(), Coord.getextremeDY() + 10);
				Coord.setextremeDY(Coord.getextremeDY() + 10);
				retCoord.setextremeDY(Coord.getextremeDY());
			} else {
				g2d.drawLine(Coord.getX(), retCoord.getextremeDY() + 10,
						Coord.getX(), Coord.getextremeDY());
				g2d.drawLine(retCoord.getX(), retCoord.getextremeDY() + 10,
						retCoord.getX(), retCoord.getextremeDY());
				retCoord.setextremeDY(retCoord.getextremeDY() + 10);
				Coord.setextremeDY(retCoord.getextremeDY());
			}
			g2d.drawLine(Coord.getX(), Coord.getextremeDY() - 10, Coord.getX(),
					Coord.getextremeDY() + 10);
			g2d.drawLine(Coord.getX(), retCoord.getextremeDY(),
					retCoord.getX(), Coord.getextremeDY());
			// arrows
			g2d.drawLine(Coord.getX(), Coord.getextremeDY(), Coord.getX() + 2,
					Coord.getextremeDY() - 2);
			g2d.drawLine(Coord.getX(), Coord.getextremeDY(), Coord.getX() + 2,
					Coord.getextremeDY() + 2);
			Coord.setextremeDY(Coord.getextremeDY() + 10);
			retCoord.setextremeDY(retCoord.getextremeDY() + 10);

		}
		countingofbody(Coord);
		countingofbody(retCoord);
	}

	@Override
	public int isIf() {
		int sum = 1;
		for (int i = 0; i < body.length; i++) {
			sum += body[i].isIf();
		}
		for (int i = 0; i < elsebody.length; i++) {
			sum += elsebody[i].isIf();
		}
		return sum;
	}

	@Override
	public int getwidth(Coords Coord) {
		int sum = 0;
//		if (elsebody.length==0 || (elsebody.length==1 && (isThereABreakInElseBody()||isThereAContinueInElseBody()))){
//			sum = 65;
//		}else{
		 sum = 115;
//		}

		int k = 0;
		for (int i = 0; i < elsebody.length; i++) {
			sum += elsebody[i].getwidth(Coord);
			k += elsebody[i].isIf();
		}
		if (k == 1) {
			sum = sum - k * 115 + 115;
		}
		k = 0;
		for (int i = 0; i < body.length; i++) {
			sum += body[i].getwidth(Coord);
			k += body[i].isIf();
		}
		if (k == 1) {
			sum = sum - k * 115 + 115;
		}
		return sum + getCyclesMargin();
	}

	public int minusbody(Coords Coord) {
		int sum = 0;
		for (int i = 0; i < elsebody.length; i++) {
			sum += elsebody[i].getwidth(Coord);
		}
		return sum;
	}

	@Override
	public int getCyclesMargin() {
		int sum = 0;
		for (int i = 0; i < body.length; i++) {
			sum += body[i].getCyclesMargin();
		}

		for (int i = 0; i < elsebody.length; i++) {
			sum += elsebody[i].getCyclesMargin();
		}
		return sum;
	}

	@Override
	public int getelsecyclemargin() {
		int sum = 0;
		for (int i = 0; i < body.length; i++) {
			sum += body[i].getCyclesMargin();
		}
		return sum;
	}

	@Override
	public boolean isThereABreakInElseBody() {
		for (int i = 0; i < elsebody.length; i++) {
			if (elsebody[i].isBreak() == 1) {
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
		for (int i = 0; i < elsebody.length; i++) {
			if (elsebody[i].breakMargin() == 30) {
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
		for (int i = 0; i < elsebody.length; i++) {
			if (elsebody[i].continueMargin() == 30) {
				return 30;
			}
		}

		return 0;
	}

	@Override
	public boolean isThereAContinueInElseBody() {
		for (int i = 0; i < elsebody.length; i++) {
			if (elsebody[i].isContinue() == 1) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isThereAReturnInElseBody() {
		for (int i = 0; i < elsebody.length; i++) {
			if (elsebody[i].isReturn() == 1) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isThereAReturnInBody() {
		for (int i = 0; i < body.length; i++) {
			if (body[i].isReturn() == 1) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void setNoRet(){
		isret = false;
	}
}