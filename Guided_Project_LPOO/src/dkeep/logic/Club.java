package dkeep.logic;

import java.io.Serializable;

public class Club implements Character, Serializable {
	
	private int ClubI; 
	private int ClubJ; 
	private char ClubSprite;
	
	public Club(){
		this.ClubI=-3;
		this.ClubJ=-3; 
	}
	
	public Club(int i,int j,char s){
		this.ClubI=i;
		this.ClubJ=j;
		this.ClubSprite=s;
	}
	 
	public int getStun(){return 0;}

	public void setStun(int x) {}
	
	public void setCoordenateI(int i){this.ClubI=i;}
	public void setCoordenateJ(int j){this.ClubJ=j;}
	public void setSprite(char s){this.ClubSprite=s;}
	public int getCoordenateI(){return ClubI;}
	public int getCoordenateJ(){return ClubJ;}
	public char getSprite(){return ClubSprite;}
	
	public boolean hasClub(){return false;}
	
	public void move(){};
	public Club getClub(){return null;}
}
