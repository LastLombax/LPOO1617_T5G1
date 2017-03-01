package dkeep.logic;

import java.util.Vector;

public class Keep implements GameMap{

	private Hero H = new Hero(7,1,'H');
	private Ogre O = new Ogre(1,4,'O');
	private Ogre O_1 = new Ogre(2,5,'O');
	private Key K = new Key(1,7,'k');
	private Club C = new Club(1,5,'*'); 
	private Club C_1 = new Club(2,6,'*'); 
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
		
		//added Hero_Club
	//	Kmap[6][2] = '*';
	}

	public Hero getHero(){return H;}
	
	public boolean hasHeroClub(){return true;}
	
	public Vector<Ogre> getOgres(){
		Vector<Ogre>ogres = new Vector<Ogre>();
		ogres.add(O);
		ogres.addElement(O_1);
		return ogres;
	}
	
	public Vector<Club> getClubs(){
		Vector<Club>clubs = new Vector<Club>();
		clubs.add(C);
		clubs.addElement(C_1);
		return clubs;
	}

	public Guard getGuard(){
		Guard g = new Guard();
		return g;
	}

	public Lever getLever(){

		Lever l = new Lever();
		return l;
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
		if (Kmap[x][y] == 'X' || Kmap[x][y] == 'I')
			return false;

		return true;
	}

	public boolean hasGuard(){return false;}	
	public boolean hasOgre(){return true;}
	public boolean hasKey(){return true;}
	public boolean hasLever(){return false;}
	public boolean hasCLub(){return true;}

}
