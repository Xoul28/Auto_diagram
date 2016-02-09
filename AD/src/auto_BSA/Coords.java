package auto_BSA;

import java.util.LinkedList;
import java.util.Queue;

public class Coords {
	private  int x;
	private  int y;
	private int extremeLX;
	private int extremeRX;
	private int extremeDY;
	//private int retX;
	//private int retY;
	private int investedCol = 0 ;
	
	public Coords(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Coords(Coords retCoord){
		this.x = retCoord.getX();
		this.y = retCoord.getY();
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
	

	//queue of break-_-
	LinkedList<Coords> breakingList = new LinkedList<Coords>();
	LinkedList<Coords> continueList = new LinkedList<Coords>();
	LinkedList<Coords> returnList = new LinkedList<Coords>();
	
	
	public void setextremeRX(int extremeRX){
		this.extremeRX = extremeRX;
	}
	
	public void setextremeLX(int extremeLX){
		this.extremeLX = extremeLX;
	}
	public int getextremeRX(){
		return extremeRX;
	}
	
	public int getextremeLX(){
		return extremeLX;
	}
	//нижняя координата
	public void setextremeDY(int extremeDY){
		this.extremeDY = extremeDY;
	}
	public int getextremeDY(){
		return extremeDY;
	}
	//количество вложенности
	public void incinvestedCol(){
		investedCol++;
	}
	public int getinvestedCol(){
		return investedCol;
	}
	public void decinvestedCol(){
		investedCol--;
	}
	public void setinvestedCol(int investedCol){
		this.investedCol = investedCol;
	}
}