package auto_BSA;

public class Coords {
	private  int x;
	private  int y;
	
	public Coords(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setXY(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	private int investedCol = 0;
	private int Bias=10;

	public void incInvestedCol(){
		investedCol++;
	}
	
	public void decInvestedCol(){
		investedCol--;
	}
	
	public int getInvestedCol(){
		return investedCol;
	}
	
	public void incBias(){
		Bias+=10;
	}

	public int getBias(){
		return Bias;
	}
	public void decBias(){
		Bias=0;
	}
	
	private int downB = 0;
	public void incB(){
		downB+=5;
	}

	public int getB(){
		return downB;
	}
	public void decB(){
		downB=0;
	}
	
}
