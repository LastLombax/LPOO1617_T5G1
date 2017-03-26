package dkeep.logic;


public interface Character {  
	   
	public int getCoordenateI(); //returns the coordinate i of the character
	public int getCoordenateJ(); //returns the coordinate j of the character
	public char getSprite(); //returns the sprite of the character
	public void setCoordenateI(int i); //sets the coordinate i of the character
	public void setCoordenateJ(int j); //sets the coordinate j of the character
	public void setSprite(char s); //sets the sprite of the character
	public boolean hasClub(); //returns true if the character has a club
	public Club getClub(); //returns the club of the character
	public int getStun(); //returns the turn in which the character is stunned
	public void setStun(int x);	 //sets the character with the stun turn x
	public void move(); //moves the character
}
