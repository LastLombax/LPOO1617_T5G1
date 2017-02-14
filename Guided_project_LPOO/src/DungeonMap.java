
public class DungeonMap {
	public void print(char array[][]){
		for(int i =0; i < 10;i++){
			for(int j=0; j < 10;j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		char[][] walls;
		walls = new char [10][10];
		
		//addes Doors
		walls[5][0] = 'I';
		walls[6][0] = 'I';
		walls[1][4] = 'I';
		walls[3][4] = 'I';
		walls[3][2] = 'I';
		walls[8][2] = 'I';
		walls[8][4] = 'I';
		
		//added Hero
		walls[1][1]='H';
		
		//added Lever
		walls[8][7] = 'k';
		
		//added Guard
		walls[1][8] = 'G';
		
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
		//prints the map
		DungeonMap r = new DungeonMap();
		r.print(walls);

	}

}
