package dkeep.logic;

import java.util.Vector;

public class Keep implements GameMap{

	private Hero H = new Hero(7,1,'H');
	private Ogre O = new Ogre(1,4,'O');
	private Ogre O_1 = new Ogre(2,5,'O');
	//private Ogre O_2 = new Ogre( 3,6, 'O');
	private Key K = new Key(1,7,'k');
	private Club C = new Club(1,5,'*'); 
	private Club C_1 = new Club(2,6,'*'); 
	//private Club C_2 = new Club(3,7, '*');
	private Club Hero_club = new Club(6,2,'*');
	private char[][] Kmap = new char [9][9];

	public Keep(){

		//added wall
		for(int i = 0; i < 9;i++)
			if(Kmap[i][0]!='I')
				Kmap[i][0]='X';			
		
		for(int i = 0; i < 9;i++)
			Kmap[i][8]='X';			
		
		for(int j = 0; j < 9;j++)
			Kmap[0][j]='X';			
		
		for(int j = 0; j < 9;j++)
			Kmap[8][j]='X';			
		
	}	
	
	public Vector<Ogre> getOgres(){
		Vector<Ogre>ogres = new Vector<Ogre>();
		ogres.add(O);
		ogres.addElement(O_1);
		//ogres.addElement(O_2);
		return ogres;
	} 
	
	public Vector<Club> getClubs(){
		Vector<Club>clubs = new Vector<Club>();
		clubs.add(C);
		clubs.addElement(C_1);
		//clubs.addElement(C_2);
		return clubs;
	}

	public Lever getLever(){
		return null;
	}

	public Vector<Exit> getExits(){
		Vector<Exit>s = new Vector<Exit>();
		s.add(new Exit(1,0,'I'));
		return s;
	}
	
	public boolean validPos(int x, int y){
		if (Kmap[x][y] == 'X' || Kmap[x][y] == 'I' || Kmap[x][y] == 'O' || Kmap[x][y] == '*')
			return false;
		return true;
	}
	
	public boolean hasHeroClub(){return true;}
	public boolean hasGuard(){return false;}	
	public boolean hasOgre(){return true;}
	public boolean hasKey(){return true;}
	public boolean hasLever(){return false;}
	public boolean hasCLub(){return true;}
	public Club getHeroClub(){return Hero_club;}
	public Key getKey(){return K;}	
	public char[][] getMap(){return Kmap;}
	public Guard getGuard(){return null;}
	public Hero getHero(){return H;}
	
	

}
