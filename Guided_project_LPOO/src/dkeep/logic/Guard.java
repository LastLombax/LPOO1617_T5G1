package dkeep.logic;

public class Guard implements Character{

	private char GuardSprite;
	private int Guard_i;
	private int Guard_j;

	private final int[]movementGuard_j={-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};
	private final int[]movementGuard_i={0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1,-1,-1,-1,-1};
	private int GuardIterator;

	
	public Guard(){
		this.Guard_i=-4;
		this.Guard_j=-4;
	}
	
	public Guard(int i,int j, char s){
		this.Guard_i=i;
		this.Guard_j=j;
		this.GuardSprite=s;
		this.GuardIterator=0;
	}

	public int getCoordenateI(){return Guard_i;}
	
	public int getCoordenateJ(){return Guard_j;}
	
	public char getSprite(){return GuardSprite;}
	
	public void setCoordenateI(int i){this.Guard_i = i;}
	
	public void setCoordenateJ(int j){this.Guard_j = j;}
	
	public void move(){
		if(GuardIterator == 24)
			GuardIterator = 0;

		Guard_i += movementGuard_i[GuardIterator];
		Guard_j += movementGuard_j[GuardIterator];

		GuardIterator++;
	}
	
	public void setSprite(char s){this.GuardSprite=s;}
	
	public Club getClub(){
		Club c = new Club();
		return c;
	}
	public boolean hasClub(){return false;}
	}
