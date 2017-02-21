package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;

public class gameInit {

	//to specific map class
	//first map
	private static int Exit_j = 0;

	//second map
	private static int Key_i = 1;
	private static int Key_j = 7;
	private static boolean lever = false;
	private static char[][] walls;
	private static char[][]walls2;

	//to GameMap
	public void print(char map[][]){
		for(int i =0; i < map.length;i++){
			for(int j=0; j < map[i].length;j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		//prints the map

		gameInit init = new gameInit();

		init.print(walls);

		Game g = new Game();

		g.getMap().buildMap(); //builds 1st map

		//sets map


		int valid;
		do{
			init.print(walls);

			Scanner s= new Scanner(System.in);
			String move = s.next();

			valid = g.movement(move);

			/*	if (valid == 0)
			{

			}	*/
			/*else */if (valid == 1) //facing a wall
				continue;
			else if (valid == 2) //loses
			{
				System.out.println("You lost\n");
				return;
			}


		}while(valid != 0);

		System.out.println("You win!");

		/*	if (!firstStage){
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

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j  && !hasKey)
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

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j && !hasKey)
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

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j && !hasKey)
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

				if(Hero2_i != Key_i && Hero2_j != Key_j && Ogre_i != Key_i && Ogre_j != Key_j && !hasKey)
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
			System.out.println("You won !");
	}
		 */
	}

}
