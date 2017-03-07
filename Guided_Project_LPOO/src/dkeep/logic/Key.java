package dkeep.logic;

public class Key implements Character{
	
	private int KeyI;
	private int KeyJ;
	private char KeySprite;
	
	
	
	public Key(int i,int j,char s){
		this.KeyI=i;
		this.KeyJ=j;
		this.KeySprite=s;
	}
	
	public int getStun(){return 0;}
	public void setStun(int x) {} {}
	public Club getClub(){return null;}	
	public int getCoordenateI(){return KeyI;}	
	public int getCoordenateJ(){return KeyJ;}	
	public char getSprite(){return KeySprite;}	
	public void setCoordenateI(int i){this.KeyI=i;}	
	public void setCoordenateJ(int j){this.KeyJ=j;}	
	public void setSprite(char s){this.KeySprite=s;}	
	public boolean hasClub(){return false;}
	public void move(){}

}
