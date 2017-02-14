import java.util.Scanner;
public class DungeonMap {

	private static int Hero_i = 1;
	private static int Hero_j = 1;
	private static int Guard_i = 1;
	private static int Guard_j = 8;
	private static int Lever_i = 8;
	private static int Lever_j = 7;
	private static int Exit1_i = 5;
	private static int Exit2_i = 6;
	private static int Exit_j = 0;
	private static boolean lever = false;
	private static char[][] walls;


	public void print(char array[][]){
		for(int i =0; i < 10;i++){
			for(int j=0; j < 10;j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public boolean nearGuard(){

		if ((((Hero_i == Guard_i-1) || (Hero_i == Guard_i+1)) && (Hero_j == Guard_j)) ||
				((Guard_i == Hero_i) && ((Hero_j == Guard_j-1) || (Hero_j == Guard_j+1))))		
			return false;

		return true;

	}


	public static void main(String[] args) {

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
		//prints the map
		DungeonMap r = new DungeonMap();
		r.print(walls);

		//task 2 hero movement
		boolean Win=false;

		while(!Win){
			Scanner s= new Scanner(System.in);
			String movement = s.next();

			if (movement.charAt(0) == 'w')
			{	
							
				
				if (walls[Hero_i-1][Hero_j] == 'X' || walls[Hero_i-1][Hero_j] == 'I')
					continue;

				walls[Hero_i][Hero_j] = 0;

				walls[Hero_i-1][Hero_j] = 'H';

				Hero_i --;		

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
	
					Win = true;
					break;
				}
				
			
			}

			if (movement.charAt(0) == 's')
			{
				
				
				if (walls[Hero_i+1][Hero_j] == 'X' || walls[Hero_i+1][Hero_j] == 'I')
					continue;

				walls[Hero_i][Hero_j] = 0;

				walls[Hero_i+1][Hero_j] = 'H';

				Hero_i++;

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
					Win = true;
					break;
				}
				
			}

			if (movement.charAt(0) == 'a')
			{
				if (walls[Hero_i][Hero_j-1] == 'X' || walls[Hero_i][Hero_j-1] == 'I')
					continue;
				
				walls[Hero_i][Hero_j] = 0;

				walls[Hero_i][Hero_j-1] = 'H';

				Hero_j --;	

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
					Win = true;
					break;
				}
				
			}

			if (movement.charAt(0) == 'd')
			{
				if (walls[Hero_i][Hero_j+1] == 'X' || walls[Hero_i][Hero_j+1] == 'I')
					continue;
			
				walls[Hero_i][Hero_j] = 0;

				walls[Hero_i][Hero_j+1] = 'H';

				Hero_j++;

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
	
					Win = true;
					break;
				}
				
			}
			//	System.out.println("m =" + movement + ".");
			r.print(walls);

		}
		
		if (!Win)
			System.out.println("You were captured!");
		else
			System.out.println("You win!");

	}

}
