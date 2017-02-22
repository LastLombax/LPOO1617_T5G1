package dkeep.logic;

public interface GameMap {

	public char[][] getMap();
	
	public void buildMap();
	
	public GameMap nextMap();
	
	public boolean validPos(int x, int y);

	
}
