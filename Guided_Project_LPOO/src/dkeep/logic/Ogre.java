package dkeep.logic;

import java.io.Serializable;

public class Ogre implements Character,Serializable{

	private char OgreSprite; 
	private int Ogre_i;
	private int Ogre_j;
	private Club c = new Club(); //1,5
	private int turnStun = 0;
	private int mapLength;

	public Ogre(){
		this.Ogre_i=-4;
		this.Ogre_j=-4;
	}

	public Ogre(int i,int j,char s,int length){
		this.Ogre_i=i;
		this.Ogre_j=j;
		this.OgreSprite=s;
		this.mapLength = length;
	}
	//ogre functions

	public void setClub(int i,int j,char s){this.c = new Club(i,j,s);}

	public void setClub(Club d){this.c = d;}

	public void setStun(int x){	this.turnStun = x;}
	public int getStun(){return turnStun;}
	public Club getClub(){return c;}

	public void move(){

		boolean valid=false;

		while(!valid){
			if (getStun() == 0)
			{
				double randomno = Math.floor(Math.random()*4);
				switch((int)randomno){
				case 0: //go down
					if(goDown()) 
						valid = true;
					break;
				case 1: //go up
					if(goUp()) 
						valid = true;
					break;
				case 2: //go right
					if(goRight()) 
						valid = true;
					break;
				case 3: //go left
					if(goLeft())
						valid = true;
					break;		
				}
			}
			else
			{
				clubMovement();
				valid=true;
			}

		}
	}

	public boolean goDown(){
		Ogre_i++;
		if(Ogre_i>this.mapLength)
			Ogre_i--;
		else{
			clubMovement();
			return true;
		}
		return false;
	}
	public boolean goUp(){
		Ogre_i--;
		if(Ogre_i <1)
			Ogre_i++;
		else
		{
			clubMovement();
			return true;
		}
		return false;
	}
	public boolean goLeft(){
		Ogre_j--;
		if(Ogre_j<1)
			Ogre_j++;
		else
		{
			clubMovement();
			return true;
		}
		return false;
	}
	public boolean goRight(){
		Ogre_j++;
		if(Ogre_j > this.mapLength)
			Ogre_j--;
		else
		{	
			clubMovement();
			return true;
		}
		return false;
	}

	//club function for movement
	public void clubMovement(){

		boolean valid=false;

		while(!valid){
			double randomno = Math.floor(Math.random()*4);
			switch((int)randomno){
			case 0: //go down		
				
				valid = clubDown();
				break;
			case 1: //go up
				
				valid =clubUp();
				break;
			case 2: //go right
				
				valid =clubRight();
				break;
			case 3: //go left
				
				valid =clubLeft();
				break;
			}

		}
	}
	
	public boolean clubDown(){
		if(Ogre_i == this.mapLength)
			return false;
		else
			return setClubCoors(1, 0);
	}

	public boolean clubUp(){
		if(Ogre_i == 1)
			return false;
		else
			return setClubCoors(-1, 0);
	}

	public boolean clubRight(){
		if(Ogre_j == this.mapLength)
			return false;
		else
			return setClubCoors(0, 1);
	}
	
	public boolean clubLeft(){
		if(Ogre_j == 1)
			return false;
		else
			return setClubCoors(0, -1);
	}
	public boolean setClubCoors(int i, int j){
		c.setCoordenateI(Ogre_i + i);
		c.setCoordenateJ(Ogre_j + j);
		return true;
	}

	public int getCoordenateI(){return Ogre_i;}

	public int getCoordenateJ(){return Ogre_j;}

	public char getSprite(){return OgreSprite;}

	public void setCoordenateI(int i){this.Ogre_i = i;}

	public void setCoordenateJ(int j){this.Ogre_j = j;}

	public void setSprite(char s){this.OgreSprite = s;}

	public boolean hasClub(){
		if(c.getCoordenateI() >= 0 && c.getCoordenateJ() >=0)
			return true;
		return false;
	}

	public boolean isStunned(boolean stun){return stun;}
}
