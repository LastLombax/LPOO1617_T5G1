package dkeep.logic;

import dkeep.logic.*;

public class Game {

	private Hero H;	
	private Guard G;	
	private Ogre O;
	boolean hasKey=false;
	private GameMap map;
	
	public int movement(String s)
	{		
		if (s.charAt(0) == 'w')
		{		
			if (!map.validPos(H.getHeroI()-1,H.getHeroJ()))		
				return 1;

			map.getMap()[H.getHeroI()][H.getHeroJ()] = 0; //check if copy or not
			map.getMap()[H.getHeroI()-1][H.getHeroJ()] = 'H';
			H.setHeroI(H.getHeroI()-1);

			G.guardMovement();

			if (G.nearGuard())						
				return 2;			
			
		
			if (H.getHeroI() == Lever_i && H.getHeroJ() == Lever_j)
			{
				map.getMap()[5][0] = 'S';
				map.getMap()[6][0] = 'S';
			}


			if (map.getMap()[H.getHeroI()][H.getHeroJ()] == 'S')			
				return 0;					

		}

		if (s.charAt(0) == 's')
		{			
			if (map.getMap()[H.getHeroI()+1][H.getHeroJ()] == 'X' || map.getMap()[H.getHeroI()+1][H.getHeroJ()] == 'I')
			{
				r.print(map.getMap());
				continue;
			}

			map.getMap()[H.getHeroI()][H.getHeroJ()] = 0;
			map.getMap()[H.getHeroI()+1][H.getHeroJ()] = 'H';
			H.setHeroI(H.getHeroI()+1);

			G.guardMovement();

			if (G.nearGuard())						
				return 2;			

			if (H.getHeroI() == Lever_i && H.getHeroJ() == Lever_j)
			{
				map.getMap()[5][0] = 'S';
				map.getMap()[6][0] = 'S';
				lever = true;
			}


			if  (lever == true && (((H.getHeroI() == Exit1_i) || (H.getHeroI() == Exit2_i)) && (H.getHeroJ() == Exit_j)) )
			{
				System.out.println("coiso");
				firstStage = true;
				break;
			}

		}

		if (s.charAt(0) == 'a')
		{
			if (map.getMap()[H.getHeroI()][H.getHeroJ()-1] == 'X' || map.getMap()[H.getHeroI()][H.getHeroJ()-1] == 'I')
			{
				r.print(map.getMap());
				continue;
			}

			if (map.getMap()[H.getHeroI()][H.getHeroJ()-1] == 'S')
				firstStage = true;

			map.getMap()[H.getHeroI()][H.getHeroJ()] = 0;
			map.getMap()[H.getHeroI()][H.getHeroJ()-1] = 'H';
			H.setHeroJ(H.getHeroJ()-1);

			G.guardMovement();

			if (G.nearGuard())						
				return 2;			

			if (H.getHeroI() == Lever_i && H.getHeroJ() == Lever_j)
			{
				map.getMap()[5][0] = 'S';
				map.getMap()[6][0] = 'S';
			}				
		}

		if (s.charAt(0) == 'd')
		{
			if (map.getMap()[H.getHeroI()][H.getHeroJ()+1] == 'X' || map.getMap()[H.getHeroI()][H.getHeroJ()+1] == 'I')
			{
				r.print(map.getMap());
				continue;
			}

			if (map.getMap()[H.getHeroI()][H.getHeroJ()] == 'S')
			{
				firstStage = true;
				break;
			}	

			map.getMap()[H.getHeroI()][H.getHeroJ()] = 0;
			map.getMap()[H.getHeroI()][H.getHeroJ()+1] = 'H';
			H.setHeroJ(H.getHeroJ()+1);

			G.guardMovement();

			if (G.nearGuard())						
				return 2;			

			if (H.getHeroI() == Lever_i && H.getHeroJ() == Lever_j)
			{
				map.getMap()[5][0] = 'S';
				map.getMap()[6][0] = 'S';
			}			
		}
	}

	public GameMap getMap()
	{
		return map;
	}

	public void setMap(GameMap map)

	{
		this.map = map;
	}

}
