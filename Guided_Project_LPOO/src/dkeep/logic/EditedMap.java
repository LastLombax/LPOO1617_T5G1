package dkeep.logic;

import java.util.Vector;
import java.io.Serializable;

public class EditedMap extends GameMap implements Serializable{

	private Hero H;
	private Ogre O;	
	private Key K;
	private Club C; 	
	private Club Hero_club;
	private Exit I;
	private char[][] Emap;// = new char [9][9];

	public EditedMap(char[][] map, int nOgres){
		this.Emap = map;

		for (int i = 0; i < Emap.length; i++)
			for (int j = 0; j < Emap[i].length;j++)
			{
				if (Emap[i][j] == 'k')
					K = new Key(i, j,'k');
				else if (Emap[i][j] == 'A')
					H = new Hero(i,j,'A');
				else if (Emap[i][j] == 'O'){
					O = new Ogre(i,j,'O', Emap[i].length-2);
					defineClub(i,j);
				}
				else if (Emap[i][j] == 'I')
					I = new Exit(i,j,'I');
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

	public void defineClub(int i, int j){
		if (Emap[i+1][j] == ' ')
			C = new Club(i+1,j,'*');
		else if (Emap[i-1][j] == ' ') 
			C = new Club(i-1,j,'*');
		else if (Emap[i][j-1] == ' ')
			C = new Club(i,j-1,'*');
		else if( Emap[i][j-1] == ' ')
			C = new Club(i,j-1,'*');
	}
	public Guard getGuard(){return null;}

	public Lever getLever(){return null;}

	public Club getHeroClub(){return null;}

	public Key getKey(){return K;}

	public Vector<Exit> getExits(){
		Vector<Exit>s = new Vector<Exit>();
		s.add(I);
		return s;
	}

	public char[][] getMap(){return Emap;}

	public boolean validPos(int x, int y){
		if (Emap[x][y] == 'X' || Emap[x][y] == 'I' || Emap[x][y] == 'O' || Emap[x][y] == '*')
			return false;

		return true;
	}


	public boolean hasGuard(){return false;}	
	public boolean hasOgre(){return true;}
	public boolean hasKey(){return true;}
	public boolean hasLever(){return false;}
	public boolean hasCLub(){return true;}

	public void setMap(char[][] c){this.Emap = c;}

	public void setHero(Hero h){}

	public void setGuard(Guard g){}

	public void setClub(Club c){}

	public void setOgre(Ogre o){}

	public void setKey(Key k){}

	public void setLever(Lever l){}

	public void setHeroClub(Club c){}

	public void setExits(Vector<Exit> e){}
}
