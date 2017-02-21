package dkeep.logic;

public class Hero {

	private  char Hero_char = 'H';
	//first map
	private  int Hero_i = 1;
	private  int Hero_j = 1;

	//second map
//	private  int Hero2_i = 7;
//	private  int Hero2_j = 1;
	
	public int getHeroI(){
		return Hero_i;
	}
	
	public int getHeroJ(){
		return Hero_j;
	}

	public void setHeroI (int i){
		this.Hero_i = i;
	}
	
	public void setHeroJ (int j){
		this.Hero_j = j;
	}
}
