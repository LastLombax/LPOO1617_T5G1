package dkeep.logic;

import java.util.Vector;

public class Keep extends GameMap{

	private Hero H = new Hero(7,1,'H');
	private Ogre O = new Ogre(1,4,'O');	
	private Key K = new Key(1,7,'k'); 
	private Club C = new Club(1,5,'*'); 	
	private Club Hero_club = new Club(6,2,'*');
	private char[][] Kmap = new char [9][9];

	public Keep(){
		//added wall
		for(int i = 0; i < 9;i++){
			if(Kmap[i][0]!='I')
				Kmap[i][0]='X';			
		}	
		for(int i = 0; i < 9;i++){
			Kmap[i][8]='X';			
		}
		for(int j = 0; j < 9;j++){
			Kmap[0][j]='X';			
		}
		for(int j = 0; j < 9;j++){
			Kmap[8][j]='X';			
		}
	}
 
	public Hero getHero(){return H;}
	
	public boolean hasHeroClub(){return true;}
	
	public Vector<Ogre> getOgres(){
		Vector<Ogre>ogres = new Vector<Ogre>();
		ogres.add(O); 
		return ogres;
	}
	
	public Vector<Club> getClubs(){
		Vector<Club>clubs = new Vector<Club>();
		clubs.add(C);
		return clubs;
	}

	public Guard getGuard(){
		return null;
	}

	public Lever getLever(){
		return null;
	}

	public Club getHeroClub(){
		return Hero_club;
	}
	public Key getKey(){return K;}

	public Vector<Exit> getExits(){
		Vector<Exit>s = new Vector<Exit>();
		s.add(new Exit(1,0,'I'));
		return s;
	}

	public char[][] getMap(){
		return Kmap;
	}

	public boolean validPos(int x, int y){
		if (Kmap[x][y] == 'X' || Kmap[x][y] == 'I' || Kmap[x][y] == 'O' || Kmap[x][y] == '*')
			return false;

		return true;
	}

	public boolean hasGuard(){return false;}	
	public boolean hasOgre(){return true;}
	public boolean hasKey(){return true;}
	public boolean hasLever(){return false;}
	public boolean hasCLub(){return true;}

	public void setMap(char[][] c){
		this.Kmap = c;
	}
	public void setHero(Hero h){}

	public void setGuard(Guard g){}

	public void setClub(Club c){}

	public void setOgre(Ogre o){}

	public void setKey(Key k){}

	public void setLever(Lever l){}

	public void setHeroClub(Club c){}
	
	public void setExits(Vector<Exit> e){}
}
