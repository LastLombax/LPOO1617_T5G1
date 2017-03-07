package dkeep.logic;

import java.util.Vector;

import dkeep.logic.Hero;


public class Dungeon implements GameMap{

	private Hero h = new Hero(1,1,'H');
	private Guard G = new Guard(1,8,'G', (int) Math.floor(Math.random()*3));
	private Lever L = new Lever(8,7,'k');
	private char[][] Dmap = new char [10][10];

	public Dungeon(){

		this.Dmap[1][4] = 'I';
		this.Dmap[3][4] = 'I';
		this.Dmap[3][2] = 'I';
		this.Dmap[8][2] = 'I';
		this.Dmap[8][4] = 'I';

		//added wall
		for(int i = 0; i < 10;i++)
			if(this.Dmap[i][0]!='I')
				this.Dmap[i][0]='X';			

		for(int i = 0; i < 10;i++)
			this.Dmap[i][9]='X';			

		for(int j = 0; j < 10;j++)
			this.Dmap[0][j]='X';			

		for(int j = 0; j < 10;j++)
			this.Dmap[9][j]='X';			

		this.Dmap[1][6] =
				this.Dmap[2][1] = this.Dmap[2][2] =this.Dmap[2][4] = this.Dmap[2][5] = this.Dmap[2][6] = 
				this.Dmap[3][6] =
				this.Dmap[4][1] = this.Dmap[4][2] = this.Dmap[4][4] = this.Dmap[4][5] = this.Dmap[4][6] =
				this.Dmap[7][1] = this.Dmap[7][2] = this.Dmap[7][4] = this.Dmap[7][5] = this.Dmap[7][6] = this.Dmap[7][7] =
				this.Dmap[8][6] = 'X';
	}
	
	public boolean validPos(int x, int y)
	{
		if (Dmap[x][y] == 'X' || Dmap[x][y] == 'I')
			return false;
		return true;
	}

	public Club getClub(){
		Club c = new Club();
		return c;
	}

	public Vector<Ogre> getOgres(){
		Vector<Ogre>ogres = new Vector<Ogre>();
		return ogres;
	}
	
	
	public Vector<Exit> getExits(){
		Vector<Exit>s = new Vector<Exit>();
		s.add(new Exit(5,0,'I'));
		s.add(new Exit(6,0,'I'));
		return s;		
	}
	
	public GameMap nextMap(GameMap game){return game;}
	public boolean hasGuard(){return true;}
	public boolean hasOgre(){return false;}
	public boolean hasCLub(){return false;}
	public Hero getHero(){return h;}
	public Guard getGuard(){return G;}
	public Lever getLever(){return L;}
	public Key getKey(){return null;}
	public boolean hasKey(){return false;}
	public boolean hasLever(){return true;}
	public boolean hasHeroClub(){return false;}
	public char[][] getMap(){return Dmap;}
	public Club getHeroClub(){return null;}
	public Vector<Club> getClubs(){	return null;}
	public Ogre getOgre(){return null;}
}
