package dkeep.logic;

import dkeep.logic.Hero;


public class Dungeon implements GameMap{

	public int Lever_i = 8;
	public int Lever_j = 7;
	public int Exit1_i = 5;  
	public int Exit2_i = 6;
	private int i_limit = 10;
	private int j_limit = 10;
	char[][] Dmap = new char [i_limit][j_limit];

	public void buildMap(){
		//added Doors
		Dmap[5][0] = 'I';
		Dmap[6][0] = 'I';
		Dmap[1][4] = 'I';
		Dmap[3][4] = 'I';
		Dmap[3][2] = 'I';
		Dmap[8][2] = 'I';
		Dmap[8][4] = 'I';

		//added Hero
		Dmap[Hero_i][Hero_j]='H';

		//added Lever
		Dmap[Lever_i][Lever_j] = 'k';

		//added Guard
		Dmap[Guard_i][Guard_j] = 'G';

		//added wall
		for(int i = 0; i < 10;i++){
			if(Dmap[i][0]!='I')
				Dmap[i][0]='X';			
		}	
		for(int i = 0; i < 10;i++){
			Dmap[i][9]='X';			
		}
		for(int j = 0; j < 10;j++){
			Dmap[0][j]='X';			
		}
		for(int j = 0; j < 10;j++){
			Dmap[9][j]='X';			
		}
		Dmap[1][6] =
				Dmap[2][1] = Dmap[2][2] =Dmap[2][4] = Dmap[2][5] = Dmap[2][6] = 
				Dmap[3][6] =
				Dmap[4][1] = Dmap[4][2] = Dmap[4][4] = Dmap[4][5] = Dmap[4][6] =
				Dmap[7][1] = Dmap[7][2] = Dmap[7][4] = Dmap[7][5] = Dmap[7][6] = Dmap[7][7] =
				Dmap[8][6] = 'X';

	}

	public char[][] getMap(){
		return Dmap;
	}
	
	public boolean validPos(int x, int y)
	{
		if (Dmap[x][y] == 'X' || Dmap[x][y] == 'I')
			return false;
	
		return true;
	}
	
	public GameMap nextMap()
	{
		
	}
}
