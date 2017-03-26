package dkeep.logic;

import java.io.Serializable;

public class Guard implements Character, Serializable{

	private char GuardSprite;
	private int Guard_i, Guard_j, ranGuard, GuardIterator;	
	private final int[]movementGuard_i={0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1,-1,-1,-1,-1};
	private final int[]movementGuard_j={-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};	


	public Guard(){
		this.Guard_i=-4;
		this.Guard_j=-4;
	}

	//random guard
	public Guard(int i,int j, char s, int random){

		this.Guard_i=i;
		this.Guard_j=j;
		this.GuardSprite=s;
		this.GuardIterator=0;
		this.ranGuard = random;
	}

	public int getCoordenateI(){return Guard_i;}

	public int getCoordenateJ(){return Guard_j;}

	public char getSprite(){return GuardSprite;}

	public void setCoordenateI(int i){this.Guard_i = i;}

	public void setCoordenateJ(int j){this.Guard_j = j;}

	public int getStun(){return 0;}
	public void setStun(int x) {} {}

	public void updateGuard(char inc){
		if (inc == '+'){
			Guard_i += movementGuard_i[GuardIterator];
			Guard_j += movementGuard_j[GuardIterator];

			GuardIterator++;
		}
		else if (inc == '-'){
			GuardIterator--;
			Guard_i -= movementGuard_i[GuardIterator];
			Guard_j -= movementGuard_j[GuardIterator];
		}
	}

	public void normalMovement(){
		if(GuardIterator == 24)
			GuardIterator = 0;
		updateGuard('+');
	}

	public void drunk(){
		int stops = (int) Math.floor(Math.random()*2);
		if (stops == 0) //normal movement
		{
			if (GuardSprite == 'g') //if it stopped earlier
				guardSleeping();	
			else
				normalMovement();
			
			GuardSprite = 'G';
		}
		else //will fall asleep
			GuardSprite = 'g';
	}

	public void suspicious(){
		int reverse = (int) Math.floor(Math.random()*2);
		
		if (reverse == 0) //doesn't change path
			normalMovement();
		
		else //changes path		
			changePath();			
	}

	public void guardSleeping(){
		int reverse = (int) Math.floor(Math.random()*2);
		if (reverse == 0) //doesn't change path
			normalMovement();
		else //changes path		
			changePath();		
	}
	
	public void changePath(){
		if(GuardIterator == 0)
			GuardIterator = 24;						
		updateGuard('-');	
	}

	public void move(){

		//rookie /normal
		if (ranGuard == 0)
			normalMovement();
		//drunk
		else if (ranGuard == 1) 
			drunk();
		//Suspicious
		else if (ranGuard == 2) 
			suspicious();
	}

	public void setSprite(char s){this.GuardSprite=s;}

	public Club getClub(){
		return null;
	}
	public boolean hasClub(){return false;}

	public int getRanGuard(){return this.ranGuard;}
}
