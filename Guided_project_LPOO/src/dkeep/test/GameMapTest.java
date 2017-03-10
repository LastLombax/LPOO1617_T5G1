package dkeep.test;

import java.util.Vector;

import dkeep.logic.*;

public class GameMapTest extends GameMap{

	private Hero H = new Hero(); 
	private Guard G = new Guard();
	private Lever l = new Lever();
	private Key k = new Key();
	private Ogre O = new Ogre();
	private Vector<Exit>exits = new Vector<Exit>();
	private Club C = new Club();
	private Club H_C = new Club();
	private boolean hasGuard = false;
	private boolean hasOgre = false;
	private boolean hasCLub = false;
	private boolean hasKey = false;
	private boolean hasLever = false;
	private boolean hasHeroClub = false;
	private char[][]map;

	public GameMapTest(){}
	
	public Hero getHero(){return this.H;}

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

	public Guard getGuard(){return G;}

	public Lever getLever(){return l;}
	
	public Key getKey(){return k;}
	
	public Vector<Exit> getExits(){return exits;}
	
	public Club getHeroClub(){return H_C;}

	public char[][] getMap(){return map;}

	public boolean validPos(int x, int y)
	{
		if (map[x][y] == 'X' || map[x][y] == 'I')
			return false;

		return true;
	}

	public boolean hasGuard(){return hasGuard;}
	public boolean hasOgre(){return hasOgre;}
	public boolean hasCLub(){return hasCLub;}
	public boolean hasKey(){return hasKey;}
	public boolean hasLever(){return hasLever;}
	public boolean hasHeroClub(){return hasHeroClub;}

	public void setMap(char[][] c){this.map = c;}

	public void setHero(Hero h){this.H = h;}
	
	public void setGuard(Guard g){
		this.G=g;
		this.hasGuard=true;
	}
	
	public void setClub(Club c){
		this.C=c;
		this.hasCLub=true;
	}
	
	public void setOgre(Ogre o){
		this.O = o;
		this.hasOgre=true;
	}
	
	public void setKey(Key k){
		this.k=k;
		this.hasKey=true;
	}
	
	public void setLever(Lever l){
		this.l=l;
		this.hasLever=true;
	}
	
	public void setHeroClub(Club c){
		this.H_C=c;
		this.hasHeroClub=true;
	}
	
	public void setExits(Vector<Exit> e){
		this.exits=e;
	}


}
