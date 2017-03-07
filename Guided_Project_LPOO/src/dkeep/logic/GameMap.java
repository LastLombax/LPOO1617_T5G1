package dkeep.logic;
import java.util.Vector;

public interface GameMap {
	
	public Hero getHero();  //returns Hero with the initial coordinates 
	public Vector<Ogre> getOgres(); //returns Ogres with the initial coordinates
	public Vector<Club> getClubs();
	public Guard getGuard();  //returns Guard with the initial coordinates
	public Lever getLever(); //returns Lever of map	
	public Key getKey(); //returns key of map
	public Vector<Exit> getExits(); //returns all the exits existing in the map
	public Club getHeroClub(); //returns the hero club with the initial coordinates
	
	public char[][] getMap();	//returns the map (char[][])
	
	public boolean validPos(int x, int y); //checks if the position with coordinates [i][j] of the map are valid (not door or wall)
	
	public boolean hasGuard();	//returns true if the level has a Guard,false if not
	public boolean hasOgre();	//returns true if the level has a Ogre,false if not
	public boolean hasCLub();   //returns true if the Ogre has a Club,false if not
	public boolean hasKey();	//returns true if the level has a key,false if not
	public boolean hasLever();  //returns true if the level has a Lever,false if not
	public boolean hasHeroClub(); //returns true if the level has a club for the hero, false if not
	
}
