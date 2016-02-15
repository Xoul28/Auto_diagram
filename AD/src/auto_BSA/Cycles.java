package auto_BSA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Cycles extends FunBlock {
	
	CycleReturning ret;
	public Cycles(Block[] body,String line) {
		super(body,line);
	}

	@Override
	public int getwidth(Coords Coord) {
		int width=0;
 		for (int i = 0; i < body.length; i++) {
 			if(body[i].getwidth(Coord)>width)
 				width=body[i].getwidth(Coord);	
 		}
 		return width;
	}	
     
	 @Override
	public int getCyclesMargin() {
		int sum = 7;
		for (int i = 0; i < body.length; i++) {
		 		sum += body[i].getCyclesMargin();
		 }
		return sum;
	}
	 PixelGrabber pg = null;
	 
	 public void drawbreak(Coords Coord,Graphics2D g2d,int y) {
		
			
			
		if(Component.image != null){
			
			 int pixels[] = new int[Component.image.getWidth()*Component.image.getHeight()];
			 pg = new PixelGrabber(Component.image, 0, 0, 1000, Component.image.getHeight(), pixels, 0, Component.image.getWidth());
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Coords iter : Coord.breakingList) {
			if(y<iter.getY()){
				
				if(bobtry(iter.getX(), Coord.getextremeRX(), iter.getY(),pixels,g2d)){
					g2d.drawLine(iter.getX(), iter.getY(), Coord.getextremeRX(), iter.getY());
				}else{
					
					g2d.drawLine(iter.getX(), iter.getY(), iter.getX()+6, iter.getY());
					g2d.drawOval(iter.getX()+6, iter.getY()-12, 25, 25);
					drawCenteredString(g2d, Block.breakinthelaw.toString(), iter.getX()+18, iter.getY());
					int m = 0;
					for (Coords iter_f : Coord.breakingList) {
						if(iter.getY()==iter_f.getY()&& iter.getX()!=iter_f.getX()){
							m-=27;
						}
					}
					iter.setY(iter.getY()+m);
					
					drawCenteredString(g2d, Block.breakinthelaw.toString(), Coord.getextremeRX()-18, iter.getY());
					g2d.drawLine(Coord.getextremeRX()-6, iter.getY(), Coord.getextremeRX(), iter.getY());
					g2d.drawOval(Coord.getextremeRX()-31, iter.getY()-12, 25, 25);
					Block.breakinthelaw =Block.breakinthelaw.intValue()+ 1;
				}	
				
				}
			}
		
		for (Coords iter : Coord.breakingList) {
			if(y<iter.getY()){
			Coord.breakingList.remove(iter);
			}
		}	 
	 }
	 }
	 public void drawContinue(Coords Coord,Graphics2D g2d,int y){
		
		 if(Component.image!=null){
			 int pixels[] = new int[Component.image.getWidth()*Component.image.getHeight()];
				 pg = new PixelGrabber(Component.image, 0, 0, Component.image.getWidth(), Component.image.getHeight(),pixels,0,  Component.image.getWidth());
			
			 try {
				
				pg.grabPixels();
				
			
				
				}  catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("baby dont hurt me");
				}
			
		for (Coords iter : Coord.continueList) {
			if(y<iter.getY()){
				
				if(bobtry(Coord.getextremeLX(),iter.getX(),iter.getY(),pixels,g2d)) {
					g2d.drawLine(iter.getX(), iter.getY(), Coord.getextremeLX(), iter.getY());
					g2d.drawLine(Coord.getextremeLX()+2, iter.getY()-2, Coord.getextremeLX(), iter.getY());
					g2d.drawLine(Coord.getextremeLX()+2, iter.getY()+2, Coord.getextremeLX(), iter.getY());
				}else{
					
					g2d.drawLine(iter.getX(), iter.getY(), iter.getX()-6, iter.getY());
					g2d.drawLine(iter.getX()-6+2, iter.getY()-2, iter.getX()-6, iter.getY());
					g2d.drawLine(iter.getX()-6+2, iter.getY()+2, iter.getX()-6, iter.getY());
					g2d.drawOval(iter.getX()-31, iter.getY()-12, 25, 25);
					drawCenteredString(g2d, Block.breakinthelaw.toString(), iter.getX()-18, iter.getY());
					int m = 0;
					for (Coords iter_f : Coord.continueList) {
						if(iter.getY()==iter_f.getY()&& iter.getX()!=iter_f.getX()){
							m-=27;
						}
					}
					iter.setY(iter.getY()+m);
					
					drawCenteredString(g2d, Block.breakinthelaw.toString(), Coord.getextremeLX()+18, iter.getY());
					g2d.drawLine(Coord.getextremeLX()+6, iter.getY(), Coord.getextremeLX(), iter.getY());
					g2d.drawLine(Coord.getextremeLX()+2, iter.getY()-2, Coord.getextremeLX(), iter.getY());
					g2d.drawLine(Coord.getextremeLX()+2, iter.getY()+2, Coord.getextremeLX(), iter.getY());
					
					g2d.drawOval(Coord.getextremeLX()+6, iter.getY()-12, 25, 25);
					Block.breakinthelaw =Block.breakinthelaw.intValue()+ 1;
				}	
				Coord.setextremeLX(Coord.getextremeLX()-6);
				countingofbody(Coord);
				Coord.setextremeLX(Coord.getextremeLX()+6);
				
				}
			}
		
		for (Coords iter : Coord.continueList) {
			if(y<iter.getY()){
			Coord.continueList.remove(iter);
			}
		}	
		}
	 }
	 public boolean bobtry(int x1,int x2,int y,int[] pixel,Graphics2D g2d){
	
		 for (int i = x1+1; i < x2; i++) {
			int idx = (y - 0) * Component.image.getWidth() + (i - 0) + 0;		
			if(pixel[idx] != 0){
				return false;
			}
		}
		 return true;
	 }
}