package dkeep.logic;
 
public class Ogre implements Character{
 
	private char OgreSprite; 
	private int Ogre_i;
	private int Ogre_j;
	private Club c = new Club(); //1,5
	private int turnStun = 0;

	public Ogre(){
		this.Ogre_i=-4;
		this.Ogre_j=-4;
	}

	public Ogre(int i,int j,char s){
		this.Ogre_i=i;
		this.Ogre_j=j;
		this.OgreSprite=s;
	}
	//ogre functions

	public void setClub(int i,int j,char s){
		this.c = new Club(i,j,s);
	}
	
	public void setClub(Club d){
		this.c = d;
	}

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
					Ogre_i++;
					if(Ogre_i>7)
						Ogre_i--;
					else
					{
						clubMovement();
						valid=true;
					}
					break;
				case 1: //go up
					Ogre_i--;
					if(Ogre_i <1)
						Ogre_i++;
					else
					{
						clubMovement();
						valid=true;
					}
					break;
				case 2: //go right
					Ogre_j++;
					if(Ogre_j > 7)
						Ogre_j--;
					else
					{	clubMovement();
					valid=true;
					}
					break;
				case 3: //go left
					Ogre_j--;
					if(Ogre_j<1)
						Ogre_j++;
					else
					{
						clubMovement();
						valid=true;
					}
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


	//club function for movement
	public void clubMovement(){

		boolean valid=false;

		while(!valid){

			double randomno = Math.floor(Math.random()*4);

			switch((int)randomno){
			case 0: //go down				
				if(Ogre_i == 7)
					break;
				else
				{
					c.setCoordenateI(Ogre_i+1);
					c.setCoordenateJ(Ogre_j);
					valid = true;
				}
				break;
			case 1: //go up
				if(Ogre_i == 1)
					break;
				else
				{
					c.setCoordenateI(Ogre_i-1);
					c.setCoordenateJ(Ogre_j);
					valid = true;
				}
				break;
			case 2: //go right
				if(Ogre_j == 7)
					break;
				else
				{
					c.setCoordenateI(Ogre_i);
					c.setCoordenateJ(Ogre_j+1);
					valid = true;
				}
				break;
			case 3: //go left
				if(Ogre_j == 1)
					break;
				else
				{
					c.setCoordenateI(Ogre_i);
					c.setCoordenateJ(Ogre_j-1);
					valid = true;
				}
				break;
			}

		}
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

	public boolean isStunned(boolean stun){
		return stun;
	}
}
