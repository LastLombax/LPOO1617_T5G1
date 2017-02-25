package dkeep.logic;
import java.util.Vector;

public interface GameMap {
	
	public Hero getHero();  //returns Hero with the initional coordenates
	public Ogre getOgre(); //returns Ogre with the initional coordenates
	public Guard getGuard();  //returns Guard with the initional coordenates
	public Lever getLever(); //returns Lever of map
	public Club getClub();
	public Key getKey(); //returns key of map
	public Vector<Exit> getExits(); //returns all the exits existing in the map

	public char[][] getMap();	//returns the map (char[][])
	
	public boolean validPos(int x, int y); //checks if the position with coordenates [i][j] of the map are valid (not door or wall)
	
	public boolean hasGuard();	//returns true if the level has a Guard,false if not
	public boolean hasOgre();	//returns true if the level has a Ogre,false if not
	public boolean hasCLub();   //returns true if the Ogre has a Club,false if not
	public boolean hasKey();	//returns true if the level has a key,false if not
	public boolean hasLever();  //returns true if the level has a Lever,false if not
	
}
