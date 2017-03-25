package dkeep.cli; 

import java.util.Scanner;
import dkeep.logic.Game;
 
public class gameInit { 
	public static void main(String[] args) {
		Game g = new Game(0);
		g.print();

		int valid; 
		do{
			Scanner s= new Scanner(System.in);
			String move = s.next();
			valid = g.movement(move);	
			
			if (valid == 1) //facing a wall
				continue;
			else if (valid == 2) //loses
			{
				g.print();
				System.out.println("You lost!\n");
				s.close();
				return;
			}
			if(valid==0)
				s.close();
			
			g.print();

		}while(valid != 0);

		System.out.println("You win!");
	}
}
