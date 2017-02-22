package dkeep.logic;

import dkeep.logic.Hero;
import dkeep.cli.gameInit;

public class Guard {

	private int Guard_i = 1;
	private int Guard_j = 8;

	private final int[]movementGuard_j={-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};
	private final int[]movementGuard_i={0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1,-1,-1,-1,-1};
	private int GuardIterator = 0;

	public boolean nearGuard(){
		
		if ((((Hero_i == Guard_i-1) || (Hero_i == Guard_i+1)) && (Hero_j == Guard_j)) ||
				((Guard_i == Hero_i) && ((Hero_j == Guard_j-1) || (Hero_j == Guard_j+1))))		
			return true;

		return false;
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


}
