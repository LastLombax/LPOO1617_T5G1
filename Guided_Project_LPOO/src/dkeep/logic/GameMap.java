package dkeep.logic;
import java.io.Serializable;
import java.util.Vector;
  
public abstract class GameMap implements Serializable { 
	
	/**  
	 * returns Hero with the initial coordinates
	 * @return Hero 
	 */ 
	public abstract Hero getHero();
	
	/**  
	 * returns Ogres with the initial coordinates
	 * @return Vector<Ogre>ogres 
	 */
	public abstract Vector<Ogre> getOgres();
	
	/**  
	 * returns Clubs with the initial coordinates
	 * @return Vector<Club>clubs
	 */
	public abstract Vector<Club> getClubs();
	
	/**  
	 * returns Guard with the initial coordinates
	 * @return Guard
	 */
	public abstract Guard getGuard();
	
	/**  
	 * returns Lever of map
	 * @return Lever
	 */
	public abstract Lever getLever();
	
	/**  
	 *returns key of map
	 * @return Key
	 */
	public abstract Key getKey();
	
	/**  
	 *returns all the exits existing in the map
	 * @return Vector<Exit>exits
	 */
	public abstract Vector<Exit> getExits();
	
	/**  
	 *returns the hero club with the initial coordinates
	 * @return Club
	 */
	public abstract Club getHeroClub();

	/**  
	 *returns the map (char[][])
	 * @return char[][]
	 */
	public abstract char[][] getMap();
	
	/**  
	 *checks if the position with coordinates [i][j] of the map are valid (not door or wall)
	 * @return true if valid position,else false
	 */
	public abstract boolean validPos(int x, int y); //checks if the position with coordinates [i][j] of the map are valid (not door or wall)
	
	/**  
	 *returns true if the level has a Guard,false if not
	 */
	public abstract boolean hasGuard();
	public abstract boolean hasOgre();	//returns true if the level has a Ogre,false if not
	public abstract boolean hasCLub();   //returns true if the Ogre has a Club,false if not
	public abstract boolean hasKey();	//returns true if the level has a key,false if not
	public abstract boolean hasLever();  //returns true if the level has a Lever,false if not
	public abstract boolean hasHeroClub(); //returns true if the level has a club for the hero, false if not
	
	public abstract void setMap(char[][] c); //sets the map with the array c	
	public abstract void setHeroClub(Club c); //sets the hero with the club c
	public abstract void setLever(Lever l); //sets the level l on the map
	public abstract void setKey(Key k); //sets the key k on the map
	public abstract void setOgre(Ogre o); //sets the ogre o on the map
	public abstract void setClub(Club c); //sets the club c on the map
	public abstract void setGuard(Guard g); //sets the guard g on the map
	public abstract void setHero(Hero h);  //sets the hero h on the map
	public abstract void setExits(Vector<Exit> e);  //sets the exits on the vector e on the map
	
}
