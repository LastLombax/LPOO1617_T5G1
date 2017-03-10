package dkeep.logic;

public class Hero implements Character{

	private  char HeroSprite;
	//first map
	private  int Hero_i;
	private  int Hero_j;

	//second map
//	private  int Hero2_i = 7;
//	private  int Hero2_j = 1;
	public Hero(int i,int j,char s){
		this.Hero_i=i;
		this.Hero_j=j;
		this.HeroSprite=s;
	}
	
	public Hero() {}

	public int getStun(){return 0;}
	public void setStun(int x) {} {}

	public void move(){		
	}
	
	public void move2(int i,int j){
		Hero_i +=i;
		Hero_j +=j;
	}
	public void setCoordenateI(int i){
		this.Hero_i = i;
	}
	
	public void setCoordenateJ(int j){
		this.Hero_j = j;
	}
	
	public int getCoordenateI(){
		return Hero_i;
	}
	
	public int getCoordenateJ(){
		return Hero_j;
	}
	
	public char getSprite(){
		return HeroSprite;
	}
	
	public void setSprite(char s){
		HeroSprite = s;
	}
	
	public Club getClub(){
		Club c = new Club();
		return c;
	}
	
	public boolean hasClub(){
		return false;}
}
