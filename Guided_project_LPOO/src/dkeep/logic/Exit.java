package dkeep.logic;

public class Exit implements Character{
	
	private int ExitI;
	private int ExitJ;
	private char ExitSprite;
	
	public Exit(int i,int j, char s){
		this.ExitI=i;
		this.ExitJ=j;
		this.ExitSprite=s;
	}

	public int getCoordenateI(){return ExitI;}
	
	public int getCoordenateJ(){return ExitJ;}
	
	public char getSprite(){return ExitSprite;}
	
	public void setCoordenateI(int i){this.ExitI=i;}
	
	public void setCoordenateJ(int j){this.ExitJ=j;}
	
	public void setSprite(char s){this.ExitSprite=s;}
	public boolean hasClub(){return false;}
	
	public Club getClub(){
		Club c = new Club();
		return c;
	}
	
	public void move(){}
}
