package dkeep.logic;

public interface Character {
	
	public int getCoordenateI(); //returns the coordinate i of the character
	public int getCoordenateJ(); //returns the coordinate j of the character
	public char getSprite(); //returns the sprite of the character
	public void setCoordenateI(int i); //sets the coordinate i of the character
	public void setCoordenateJ(int j); //sets the coordinate j of the character
	public void setSprite(char s); //sets the sprite of the character 
	public boolean hasClub(); //true if character has a club, false if he/she doesn't
	public Club getClub();   //returns the club of the character, null if he/she doesn't have one
	public int getStun();    //returns the stun turn of the character
	public void setStun(int x);	 //sets the stun turn of the character
	public void move(); //moves the character
}
