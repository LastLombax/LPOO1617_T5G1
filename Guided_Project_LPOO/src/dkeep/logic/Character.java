package dkeep.logic;

import java.io.Serializable;

public interface Character extends Serializable{  
	/**  
	 * returns part of the character position 
	 * @return int with the coordinate i of the character.  
	 */   
	public int getCoordenateI();
	
	/**  
	 * returns part of the character position 
	 * @return int with the coordinate j of the character.  
	 */ 
	public int getCoordenateJ();
	
	/**  
	 * returns sprite of the character 
	 * @return char with the sprite of the character.  
	 */ 
	public char getSprite();
	
	/**  
	 * Sets the coordinate i of the character 
	 * @param i int with the coordinate i of the character.  
	 */ 
	public void setCoordenateI(int i);
	
	/**  
	 * Sets the coordinate j of the character 
	 * @param j int with the coordinate j of the character.  
	 */ 
	public void setCoordenateJ(int j);
	
	/**  
	 * Sets the sprite of the character 
	 * @param s char with the sprite of the character.  
	 */ 
	public void setSprite(char s);
	
	/**  
	 * returns a boolean to check if has a Club or not
	 * @return true if has a Club, false if dont. 
	 */ 
	public boolean hasClub();
	
	/**  
	 * returns a Club
	 * @return Club of the character. 
	 */ 
	public Club getClub();
	
	/**  
	 *  returns the turn in which the character is stunned
	 * @return  the turn in which the character is stunned. 
	 */ 
	public int getStun();
	
	/**  
	 *  sets the character with the stun turn x
	 * @param  x int the turn in which the character is stunned. 
	 */ 
	public void setStun(int x);
	
	/**  
	 *  moves the character 
	 */ 
	public void move();
}
