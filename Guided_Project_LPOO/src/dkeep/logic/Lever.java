package dkeep.logic;

public class Lever implements Character{ 
	 
	private int LeverI;
	private int LeverJ;
	private char LeverSprite;
	
	public Lever(){
		this.LeverI=-1;
		this.LeverJ=-1; 
	}
	
	public Lever(int i, int j,char s){
		this.LeverI=i; 
		this.LeverJ=j;
		this.LeverSprite=s;
	}
	public int getStun(){return 0;}
	public void setStun(int x) {} {}
	
	public int getCoordenateI(){return LeverI;}
	
	public int getCoordenateJ(){return LeverJ;}
	
	public char getSprite(){return LeverSprite;}
	
	public void setCoordenateI(int i){this.LeverI = i;}
	
	public void setCoordenateJ(int j){this.LeverJ=j;}
	
	public void setSprite(char s){this.LeverSprite=s;}
	
	public boolean hasClub(){return false;}
	public Club getClub(){
		Club c = new Club();
		return c;
	}
	
	public void move(){}

}
