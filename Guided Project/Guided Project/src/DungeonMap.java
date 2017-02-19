import java.util.Scanner;

import java.util.Random;

public class DungeonMap {

	//first map
	private static int Hero_i = 1;
	private static int Hero_j = 1;
	private static int Guard_i = 1;
	private static int Guard_j = 8;
	private static int Lever_i = 8;
	private static int Lever_j = 7;
	private static int Exit1_i = 5; 
	private static int Exit2_i = 6;
	private static int Exit_j = 0;
	//second map
	private static int Hero2_i = 7;
	private static int Hero2_j = 1;
	private static int Ogre_i = 1;
	private static int Ogre_j = 4;
	private static int Club_i = 1;
	private static int Club_j = 5;
	private static int Key_i = 1;
	private static int Key_j = 7;
	private static char Hero_char = 'H';

	private static boolean lever = false;
	private static char[][] walls;
	private static char[][]walls2;
	private static final int[]movementGuard_j={-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};
	private static final int[]movementGuard_i={0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1,-1,-1,-1,-1};
	private static int GuardIterator = 0;

	//maps and printing them
	public void print(char map[][]){
		for(int i =0; i < map.length;i++){
			for(int j=0; j < map[i].length;j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public void firstMap(){
		walls = new char [10][10];

		//added Doors
		walls[5][0] = 'I';
		walls[6][0] = 'I';
		walls[1][4] = 'I';
		walls[3][4] = 'I';
		walls[3][2] = 'I';
		walls[8][2] = 'I';
		walls[8][4] = 'I';

		//added Hero
		walls[Hero_i][Hero_j]='H';

		//added Lever
		walls[Lever_i][Lever_j] = 'k';

		//added Guard
		walls[Guard_i][Guard_j] = 'G';

		//added wall
		for(int i = 0; i < 10;i++){
			if(walls[i][0]!='I')
				walls[i][0]='X';			
		}	
		for(int i = 0; i < 10;i++){
			walls[i][9]='X';			
		}
		for(int j = 0; j < 10;j++){
			walls[0][j]='X';			
		}
		for(int j = 0; j < 10;j++){
			walls[9][j]='X';			
		}
		walls[1][6] =
				walls[2][1] = walls[2][2] =walls[2][4] = walls[2][5] = walls[2][6] = 
				walls[3][6] =
				walls[4][1] = walls[4][2] = walls[4][4] = walls[4][5] = walls[4][6] =
				walls[7][1] = walls[7][2] = walls[7][4] = walls[7][5] = walls[7][6] = walls[7][7] =
				walls[8][6] = 'X';

	}

	public void secondMap(){
		walls2 = new char [9][9];

		//added Door
		walls2[1][0]='I';

		//added wall
		for(int i = 0; i < 9;i++){
			if(walls2[i][0]!='I')
				walls2[i][0]='X';			
		}	
		for(int i = 0; i < 9;i++){
			walls2[i][8]='X';			
		}
		for(int j = 0; j < 9;j++){
			walls2[0][j]='X';			
		}
		for(int j = 0; j < 9;j++){
			walls2[8][j]='X';			
		}

		//added Hero
		walls2[Hero2_i][Hero2_j]='H';

		//added Ogre
		walls2[Ogre_i][Ogre_j]='O';

		//added club
		walls2[Club_i][Club_j] = '*';

		//added Lever
		walls2[Key_i][Key_j]='k';
	}

	//guard functions
	public boolean nearGuard(){

		if ((((Hero_i == Guard_i-1) || (Hero_i == Guard_i+1)) && (Hero_j == Guard_j)) ||
				((Guard_i == Hero_i) && ((Hero_j == Guard_j-1) || (Hero_j == Guard_j+1))))		
			return false;

		return true;

	}

	public void guardMovement(){

		if(GuardIterator == 24)
			GuardIterator =0;
		walls[Guard_i][Guard_j] = 0;

		Guard_i += movementGuard_i[GuardIterator];
		Guard_j += movementGuard_j[GuardIterator];

		walls[Guard_i][Guard_j] = 'G';
		GuardIterator++;
	}

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


	public static void main(String[] args) {

		//prints the map
		DungeonMap r = new DungeonMap();
		r.firstMap();
		r.print(walls);

		//task 2 hero movement
		boolean firstStage=false;
		boolean secondStage=false;
		boolean hasKey=false;

		while(!firstStage){
			Scanner s= new Scanner(System.in);
			String movement = s.next();

			if (movement.charAt(0) == 'w')
			{		

				if (walls[Hero_i-1][Hero_j] == 'X' || walls[Hero_i-1][Hero_j] == 'I')
				{
					r.print(walls);
					continue;
				}

				walls[Hero_i][Hero_j] = 0;
				walls[Hero_i-1][Hero_j] = 'H';
				Hero_i --;

				r.guardMovement();

				if (!r.nearGuard())
				{			
					r.print(walls);
					break;
				}

				if (Hero_i == Lever_i && Hero_j == Lever_j)
				{
					walls[5][0] = 'S';
					walls[6][0] = 'S';
				}


				if (walls[Hero_i][Hero_j] == 'S')
				{
					firstStage = true;
					break;
				}				

			}

			if (movement.charAt(0) == 's')
			{			
				if (walls[Hero_i+1][Hero_j] == 'X' || walls[Hero_i+1][Hero_j] == 'I')
				{
					r.print(walls);
					continue;
				}

				walls[Hero_i][Hero_j] = 0;
				walls[Hero_i+1][Hero_j] = 'H';
				Hero_i++;

				r.guardMovement();

				if (!r.nearGuard())
				{			
					r.print(walls);
					break;
				}

				if (Hero_i == Lever_i && Hero_j == Lever_j)
				{
					walls[5][0] = 'S';
					walls[6][0] = 'S';
					lever = true;
				}


				if  (lever == true && (((Hero_i == Exit1_i) || (Hero_i == Exit2_i)) && (Hero_j == Exit_j)) )
				{
					System.out.println("coiso");
					firstStage = true;
					break;
				}

			}

			if (movement.charAt(0) == 'a')
			{
				if (walls[Hero_i][Hero_j-1] == 'X' || walls[Hero_i][Hero_j-1] == 'I')
				{
					r.print(walls);
					continue;
				}

				if (walls[Hero_i][Hero_j-1] == 'S')
					firstStage = true;

				walls[Hero_i][Hero_j] = 0;
				walls[Hero_i][Hero_j-1] = 'H';
				Hero_j --;

				r.guardMovement();

				if (!r.nearGuard())
				{			
					r.print(walls);
					break;
				}

				if (Hero_i == Lever_i && Hero_j == Lever_j)
				{
					walls[5][0] = 'S';
					walls[6][0] = 'S';
				}				
			}

			if (movement.charAt(0) == 'd')
			{
				if (walls[Hero_i][Hero_j+1] == 'X' || walls[Hero_i][Hero_j+1] == 'I')
				{
					r.print(walls);
					continue;
				}

				if (walls[Hero_i][Hero_j] == 'S')
				{
					firstStage = true;
					break;
				}	

				walls[Hero_i][Hero_j] = 0;
				walls[Hero_i][Hero_j+1] = 'H';
				Hero_j++;

				r.guardMovement();

				if (!r.nearGuard())
				{			
					r.print(walls);
					break;
				}

				if (Hero_i == Lever_i && Hero_j == Lever_j)
				{
					walls[5][0] = 'S';
					walls[6][0] = 'S';
				}			
			}
			//	System.out.println("m =" + movement + ".");
			r.print(walls);
		}

		if (!firstStage){
			System.out.println("You were captured!");
			return;
		}

		//second stage

		r.secondMap();
		r.print(walls2);

		while(!secondStage){
			Scanner s= new Scanner(System.in);
			String movement = s.next();

			if (movement.charAt(0) == 'w'){
				if (walls2[Hero2_i-1][Hero2_j] == 'X' || (walls2[Hero2_i-1][Hero2_j] == 'I' && !hasKey))
				{
					r.print(walls2);
					continue;
				}

				walls2[Hero2_i][Hero2_j] = 0;
				walls2[Hero2_i-1][Hero2_j] = Hero_char;
				Hero2_i --;

				r.ogreMovement();

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j)
					walls2[Key_i][Key_j]='k';

				if (!r.nearOgre())
				{			
					r.print(walls2);
					break;
				}

				if(!r.nearClub())
				{
					r.print(walls2);
					break;
				}

				if(Hero2_i == Key_i && Hero2_j == Key_j){
					hasKey=true;
					Hero_char = 'K';
				}
			}

			if(movement.charAt(0) == 's'){
				if (walls2[Hero2_i+1][Hero2_j] == 'X' || (walls2[Hero2_i+1][Hero2_j] == 'I' && !hasKey))
				{
					r.print(walls2);
					continue;
				}

				walls2[Hero2_i][Hero2_j] = 0;
				walls2[Hero2_i+1][Hero2_j] = Hero_char;
				Hero2_i++;

				r.ogreMovement();

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j)
					walls2[Key_i][Key_j]='k';

				if (!r.nearOgre())
				{			
					r.print(walls2);
					break;
				}

				if(Hero2_i == Key_i && Hero2_j == Key_j){
					hasKey=true;
					Hero_char = 'K';
				}
			}

			if(movement.charAt(0) == 'a'){

				if(walls2[Hero2_i][Hero2_j-1]=='I' && hasKey){
					walls2[1][0]='S';
					r.print(walls2);
					continue;
				}

				if (walls2[Hero2_i][Hero2_j-1] == 'X' || (walls2[Hero2_i][Hero2_j-1] == 'I' && !hasKey))
				{
					r.print(walls2);
					continue;
				}

				if(Hero2_i ==1 && (Hero2_j-1)==0 && walls2[1][0] == 'S'){
					secondStage=true;
					break;
				}

				walls2[Hero2_i][Hero2_j] = 0;
				walls2[Hero2_i][Hero2_j-1] = Hero_char;
				Hero2_j --;

				r.ogreMovement();

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j)
					walls2[Key_i][Key_j]='k';

				if (!r.nearOgre())
				{			
					r.print(walls2);
					break;
				}

				if(Hero2_i == Key_i && Hero2_j == Key_j){
					hasKey=true;
					Hero_char = 'K';
				}
			}

			if(movement.charAt(0) == 'd'){
				if (walls2[Hero2_i][Hero2_j+1] == 'X' || (walls2[Hero2_i][Hero2_j+1] == 'I' && !hasKey))
				{
					r.print(walls2);
					continue;
				}

				walls2[Hero2_i][Hero2_j] = 0;
				walls2[Hero2_i][Hero2_j+1] = Hero_char;
				Hero2_j++;

				r.ogreMovement();

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j)
					walls2[Key_i][Key_j]='k';

				if (!r.nearOgre())
				{			
					r.print(walls2);
					break;
				}

				if(Hero2_i == Key_i && Hero2_j == Key_j){
					hasKey=true;
					Hero_char = 'K';
				}
			}
			r.print(walls2);
		}

		if(!secondStage)
			System.out.println("You died !");
		else
			System.out.println("WIN !");
	}

}