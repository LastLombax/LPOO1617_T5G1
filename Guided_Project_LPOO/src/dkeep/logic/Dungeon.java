package dkeep.logic;

import java.util.Vector;

import dkeep.logic.Hero;
 
  
public class Dungeon extends GameMap{
 
	public Hero h = new Hero(1,1,'H');

	public Guard G = new Guard(1,8,'G', (int) Math.floor(Math.random()*3) );

	public Lever L = new Lever(8,7,'k');
	
	private Vector<Exit>s = new Vector<Exit>();

	private char[][] Dmap = new char [10][10];

	public Dungeon(){
		super();
		this.Dmap[1][4] = 'I';
		this.Dmap[3][4] = 'I';
		this.Dmap[3][2] = 'I';
		this.Dmap[8][2] = 'I';
		this.Dmap[8][4] = 'I';

		//added wall
		for(int i = 0; i < 10;i++){
			if(this.Dmap[i][0]!='I')
				this.Dmap[i][0]='X';			
		}	
		for(int i = 0; i < 10;i++){
			this.Dmap[i][9]='X';			
		}
		for(int j = 0; j < 10;j++){
			this.Dmap[0][j]='X';			
		}
		for(int j = 0; j < 10;j++){
			this.Dmap[9][j]='X';			
		}
		this.Dmap[1][6] =
				this.Dmap[2][1] = this.Dmap[2][2] =this.Dmap[2][4] = this.Dmap[2][5] = this.Dmap[2][6] = 
				this.Dmap[3][6] =
				this.Dmap[4][1] = this.Dmap[4][2] = this.Dmap[4][4] = this.Dmap[4][5] = this.Dmap[4][6] =
				this.Dmap[7][1] = this.Dmap[7][2] = this.Dmap[7][4] = this.Dmap[7][5] = this.Dmap[7][6] = this.Dmap[7][7] =
				this.Dmap[8][6] = 'X';
	}

	public Lever getLever(){
		return L;
	}

	public Key getKey(){
		Key k = new Key();
		return k;
	}

	public boolean hasKey(){
		return false;
	}

	public boolean hasLever(){
		return true;
	}

	public boolean hasHeroClub(){
		return false;
	}

	public char[][] getMap(){return Dmap;}

	public boolean validPos(int x, int y)
	{
		if (Dmap[x][y] == 'X' || Dmap[x][y] == 'I')
			return false;

		return true;
	}

	public GameMap nextMap(GameMap game){return game;}

	public boolean hasGuard(){return true;}

	public boolean hasOgre(){return false;}

	public boolean hasCLub(){return false;}

	public Hero getHero(){return h;}

	public Guard getGuard(){return G;}

	public Club getClub(){
		Club c = new Club();
		return c;
	}

	public Ogre getOgre(){
		Ogre o = new Ogre();
		return o;
	}
	public Club getHeroClub(){
		Club c = new Club();
		return c;
	}
	public Vector<Ogre> getOgres(){
		Vector<Ogre>ogres = new Vector<Ogre>();
		return ogres;
	}

	public Vector<Club> getClubs(){
		Vector<Club>clubs = new Vector<Club>();
		return clubs;
	}
	public Vector<Exit> getExits(){
		Vector<Exit>s = new Vector<Exit>();
		s.add(new Exit(5,0,'I'));
		s.add(new Exit(6,0,'I'));
		return s;		
	}

	public void setMap(char[][] c){
		this.Dmap = c;
	}
 
	public void setHero(Hero h){}

	public void setGuard(Guard g){}

	public void setClub(Club c){}

	public void setOgre(Ogre o){}

	public void setKey(Key k){}

	public void setLever(Lever l){}

	public void setHeroClub(Club c){}
	
	public void setExits(Vector<Exit> e){
		this.s=e;
	}

}
