package dkeep.logic;

public class gameState {
	
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

}
