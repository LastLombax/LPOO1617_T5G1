package dkeep.logic;
import java.util.Vector;

public abstract class GameMap { 
	
	public abstract Hero getHero();  //returns Hero with the initial coordinates
	public abstract Vector<Ogre> getOgres(); //returns Ogres with the initial coordinates
	public abstract Vector<Club> getClubs();
	public abstract Guard getGuard();  //returns Guard with the initial coordinates
	public abstract Lever getLever(); //returns Lever of map	
	public abstract Key getKey(); //returns key of map
	public abstract Vector<Exit> getExits(); //returns all the exits existing in the map
	public abstract Club getHeroClub(); //returns the hero club with the initial coordinates

	public abstract char[][] getMap();	//returns the map (char[][])
	
	public abstract boolean validPos(int x, int y); //checks if the position with coordinates [i][j] of the map are valid (not door or wall)
	
	public abstract boolean hasGuard();	//returns true if the level has a Guard,false if not
	public abstract boolean hasOgre();	//returns true if the level has a Ogre,false if not
	public abstract boolean hasCLub();   //returns true if the Ogre has a Club,false if not
	public abstract boolean hasKey();	//returns true if the level has a key,false if not
	public abstract boolean hasLever();  //returns true if the level has a Lever,false if not
	public abstract boolean hasHeroClub(); //returns true if the level has a club for the hero, false if not
	
	public abstract void setMap(char[][] c);
	public abstract void setHeroClub(Club c);
	public abstract void setLever(Lever l);
	public abstract void setKey(Key k);
	public abstract void setOgre(Ogre o);
	public abstract void setClub(Club c);
	public abstract void setGuard(Guard g);
	public abstract void setHero(Hero h);
	public abstract void setExits(Vector<Exit> e);
	
}
