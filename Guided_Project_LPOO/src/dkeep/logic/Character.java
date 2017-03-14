package dkeep.logic;

public interface Character {  
	
	public int getCoordenateI(); //returns the coordenate i of the character
	public int getCoordenateJ(); //returns the coordenate j of the character
	public char getSprite(); //returns the sprite of the character
	public void setCoordenateI(int i); //sets the coordenate i of the character
	public void setCoordenateJ(int j); //sets the coordenate j of the character
	public void setSprite(char s); //sets the sprite of the character
	public boolean hasClub();
	public Club getClub();
	public int getStun();
	public void setStun(int x);	
	public void move(); //moves the character
}
