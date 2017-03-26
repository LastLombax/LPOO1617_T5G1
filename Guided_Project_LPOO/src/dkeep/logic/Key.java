package dkeep.logic;
import java.io.Serializable;

public class Key implements Character,Serializable{ 
	 
	private int KeyI, KeyJ;
	private char KeySprite;
	
	public Key(){
		this.KeyI=-1;
		this.KeyJ=-1;  
	}
	
	public int getStun(){return 0;}
	public void setStun(int x) {} {}
	
	public Key(int i,int j,char s){
		this.KeyI=i;
		this.KeyJ=j;
		this.KeySprite=s;
	}
	
	public int getCoordenateI(){return KeyI;}
	
	public int getCoordenateJ(){return KeyJ;}
	
	public char getSprite(){return KeySprite;}
	
	public void setCoordenateI(int i){this.KeyI=i;}
	
	public void setCoordenateJ(int j){this.KeyJ=j;}
	
	public void setSprite(char s){this.KeySprite=s;}
	
	public boolean hasClub(){return false;}
	public Club getClub(){
		return null;
	}
	
	public void move(){}

}
