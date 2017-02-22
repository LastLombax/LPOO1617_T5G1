package dkeep.logic;

import dkeep.logic.Hero;
import dkeep.cli.gameInit;

public class Ogre {

	private  int Ogre_i = 1;
	private  int Ogre_j = 4;
	private  int Club_i = 1;
	private  int Club_j = 5;

	//ogre functions
	public boolean nearOgre(){


		if ((((Hero2_i == Ogre_i-1) || (Hero2_i == Ogre_i+1)) && (Hero2_j == Ogre_j)) ||
				((Ogre_i == Hero2_i) && ((Hero2_j == Ogre_j-1) || (Hero2_j == Ogre_j+1))))		
			return false;

		return true;
	}

	public void ogreMovement(){

		boolean valid=false;

		walls2[Ogre_i][Ogre_j] = 0;

		while(!valid){

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
				if(Ogre_j >7)
					Ogre_j--;
				else
				{
					clubMovement();
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
		if(Ogre_i == Key_i && Ogre_j == Key_j)
			walls2[Ogre_i][Ogre_j] = '$';
		else
			walls2[Ogre_i][Ogre_j] = 'O';
	}

	//club functions
	public boolean nearClub(){


		if ((((Hero2_i == Club_i-1) || (Hero2_i == Club_i+1)) && (Hero2_j == Club_j)) ||
				((Club_i == Hero2_i) && ((Hero2_j == Club_j-1) || (Hero2_j == Club_j+1))))		
			return false;

		return true;

	}

	public void clubMovement(){
		boolean valid=false;

		walls2[Club_i][Club_j] = 0;

		while(!valid){

			double randomno = Math.floor(Math.random()*4);

			switch((int)randomno){
			case 0: //go down				
				if(Ogre_i == 7)
					break;
				else
				{
					Club_i = Ogre_i+1;
					Club_j = Ogre_j;
					valid = true;
				}
				break;
			case 1: //go up
				if(Ogre_i == 1)
					break;
				else
				{
					Club_i = Ogre_i-1;
					Club_j = Ogre_j;
					valid = true;
				}
				break;
			case 2: //go right
				if(Ogre_j == 7)
					break;
				else
				{
					Club_i = Ogre_i;
					Club_j = Ogre_j+1;
					valid = true;
				}
				break;
			case 3: //go left
				if(Ogre_j == 1)
					break;
				else
				{
					Club_i = Ogre_i;
					Club_j = Ogre_j-1;
					valid = true;
				}
				break;
			}

		}
		if(Club_i == Key_i && Club_j == Key_j)
			walls2[Club_i][Club_j] = '$';
		else
			walls2[Club_i][Club_j] = '*';

	}


}
