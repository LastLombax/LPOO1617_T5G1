package dkeep.logic;

import java.util.*;

public class Game {

	private Vector<GameMap> maps = new Vector<GameMap>();
	private Hero H;
	private Club H_C;
	private Guard G;
	private Ogre O;
	private Lever L;
	private Key K;
	private Vector<Exit> exits = new Vector<Exit>();
	private Vector<Character> enemies = new Vector<Character>();
	private Vector<Character> nonMovingCharacters = new Vector<Character>();
	private GameMap map;
	private boolean hasKey;
	private boolean hasClub;

	//returns a copy of a char[][]
	public char[][] copyMap() {
		char[][] s = new char[map.getMap().length][map.getMap().length];
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap().length; j++) {
				s[i][j] = map.getMap()[i][j];
			}
		}
		return s;
	}

	//builds a vector that contains the maps
	public void buildVectorMaps() {
		Dungeon dg = new Dungeon();
		Keep kp = new Keep();
		maps.add(dg);
		maps.addElement(kp);
	}

	//changes the map to the next one if exists, if not returns 0;
	public int nextMap(GameMap map) {

		for (int i = 0; i < maps.size(); i++) {
			if (maps.get(i) == map && i != maps.size() - 1) {
				this.map = maps.get(i + 1);
				return 1;
			}
		}
		return 0;
	}

	//changes the level(restart characters according to the map)
	public void changeLevel() {

		this.exits = new Vector<Exit>();
		this.enemies = new Vector<Character>();
		this.nonMovingCharacters = new Vector<Character>();

		this.exits = map.getExits();

		if (map.hasGuard()) {
			G = new Guard(map.getGuard().getCoordenateI(), map.getGuard().getCoordenateJ(), map.getGuard().getSprite(), (int) Math.floor(Math.random()*3));
			this.enemies.add(G);
		}

		if (map.hasOgre()) {
			for (int i = 0; i < map.getOgres().size();i++)
			{
				O = new Ogre(map.getOgres().get(i).getCoordenateI(),map.getOgres().get(i).getCoordenateJ(), map.getOgres().get(i).getSprite());
				if(map.hasCLub())
					O.setClub(map.getClubs().get(i).getCoordenateI(), map.getClubs().get(i).getCoordenateJ(),map.getClubs().get(i).getSprite());
				this.enemies.add(O);
			}
		}

		if (map.hasKey())
		{
			K = new Key(map.getKey().getCoordenateI(), map.getKey().getCoordenateJ(), map.getKey().getSprite());
			this.hasKey = false;
			this.nonMovingCharacters.add(K);
		} 
		else
			this.hasKey = true;

		if (map.hasLever()) {
			L = new Lever(map.getLever().getCoordenateI(), map.getLever().getCoordenateJ(), map.getLever().getSprite());
			this.nonMovingCharacters.add(L);
		}

		if (map.hasHeroClub())
		{
			H_C = new Club(map.getHeroClub().getCoordenateI(),map.getHeroClub().getCoordenateJ(),map.getHeroClub().getSprite());
			this.nonMovingCharacters.add(H_C);
		}

		H = new Hero(map.getHero().getCoordenateI(), map.getHero().getCoordenateJ(), map.getHero().getSprite());
	}

	public Game() {


		buildVectorMaps();

		this.map = maps.get(0);

		changeLevel();
	}

	//prints the map (char[][])
	public void print() {

		char[][] m = copyMap();

		// prints exits
		for (Exit c : exits)
			m[c.getCoordenateI()][c.getCoordenateJ()] = c.getSprite();

		// prints levers and Keys
		for (Character c : nonMovingCharacters)
			m[c.getCoordenateI()][c.getCoordenateJ()] = c.getSprite();

		// prints Hero
		m[H.getCoordenateI()][H.getCoordenateJ()] = H.getSprite();

		// prints enemies
		for (Character c : enemies){
			m[c.getCoordenateI()][c.getCoordenateJ()] = c.getSprite();
			if(c.hasClub()){
				m[c.getClub().getCoordenateI()][c.getClub().getCoordenateJ()]=c.getClub().getSprite();
			}
		}

		// prints all
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	//checks if the hero was captured by a guard
	public boolean nearGuard() {

		for (int i = 0; i < enemies.size(); i++)
		{
			if (enemies.get(i).getSprite() == 'G') //it means it's a guard
				if ((((H.getCoordenateI() == G.getCoordenateI() - 1) || (H.getCoordenateI() == G.getCoordenateI() + 1))
						&& (H.getCoordenateJ() == G.getCoordenateJ()))
						|| ((G.getCoordenateI() == H.getCoordenateI()) && ((H.getCoordenateJ() == G.getCoordenateJ() - 1)
								|| (H.getCoordenateJ() == G.getCoordenateJ() + 1))))
					return true;
		}
		return false;
	}

	//checks if the hero was killed by a crazy ogre
	public boolean nearOgre() {

		for (int i = 0; i < enemies.size(); i++)
		{
			if (enemies.get(i).getSprite() == 'O') //it means it's an ogre
				if ((((H.getCoordenateI() == enemies.get(i).getCoordenateI() - 1) || (H.getCoordenateI() == enemies.get(i).getCoordenateI() + 1))
						&& (H.getCoordenateJ() == enemies.get(i).getCoordenateJ()))
						|| ((enemies.get(i).getCoordenateI() == H.getCoordenateI()) && ((H.getCoordenateJ() == enemies.get(i).getCoordenateJ() - 1)
								|| (H.getCoordenateJ() == O.getCoordenateJ() + 1))))
					return true;
		}
		return false;
	}

	public boolean nearOgreX(int x) {

		if (Math.abs(enemies.get(x).getCoordenateI() - H.getCoordenateI()) + Math.abs(enemies.get(x).getCoordenateJ() - H.getCoordenateJ()) <=1)
			return true;
		return false;

	}

	/*	public boolean nearOgreX(int x){

	//	if (enemies.get(x).getSprite() == 'O' || enemies.get(x).getSprite() == '$'){			
			if (H.getCoordenateJ() == enemies.get(x).getCoordenateJ() && H.getCoordenateI() + 1 == enemies.get(x).getCoordenateI() 
					|| H.getCoordenateJ() == enemies.get(x).getCoordenateJ() && H.getCoordenateI() - 1 == enemies.get(x).getCoordenateI()
					|| H.getCoordenateI() == enemies.get(x).getCoordenateI() && H.getCoordenateJ() + 1 == enemies.get(x).getCoordenateJ() 
					|| H.getCoordenateI() == enemies.get(x).getCoordenateI() && H.getCoordenateJ() - 1 == enemies.get(x).getCoordenateJ())
				return true;			
		//}
		return false;
	}*/

	public boolean nearClub(Character c){

		for (int i = 0; i < enemies.size(); i++)
		{
			if (enemies.get(i).hasClub()) //it means it has a club, whether it's an ogre or not
				if ((((H.getCoordenateI() == enemies.get(i).getClub().getCoordenateI()-1) || (H.getCoordenateI() == enemies.get(i).getClub().getCoordenateI()+1)) && (H.getCoordenateJ() == enemies.get(i).getClub().getCoordenateJ())) ||
						((enemies.get(i).getClub().getCoordenateI() == H.getCoordenateI()) && ((H.getCoordenateJ() == enemies.get(i).getClub().getCoordenateJ()-1) || (H.getCoordenateJ() == enemies.get(i).getClub().getCoordenateJ()+1))))		
					return true;
		}

		return false;
	}



	//deals with the input and does most of the game logic
	public int movement(String s) {

		//   UP
		if (s.charAt(0) == 'w') { 

			//if you can pass a level,change map/level or finish and win
			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() - 1 && e.getCoordenateJ() == H.getCoordenateJ()
						&& e.getSprite() == 'S') {
					H.move2(-1, 0);
					if (nextMap(map) == 0)
						return 0;
					else
						changeLevel();
				}
			}

			if (!map.validPos(H.getCoordenateI() - 1, H.getCoordenateJ())) //checks if the hero can move to a valid position
				return 1;

			H.move2(-1, 0); //moves hero

			//checks if the hero picks up the club
			if(!hasClub){
				if(map.hasHeroClub() && H.getSprite() == 'H')
				{
					if(H.getCoordenateI() == H_C.getCoordenateI() && H.getCoordenateJ() == H_C.getCoordenateJ())
					{
						H.setSprite('A');
						H_C.setCoordenateI(0);
						H_C.setCoordenateJ(0);
						hasClub = true;
					}
				}
			}

			//checks if the hero was captured or killed
			for (int i = 0; i < enemies.size(); i++) {

				switch(enemies.get(i).getStun()){

				//first turn of stun
				case 1: 
					enemies.get(i).setStun(2);
					break;
					
				//second turn of stun
				case 2:

					enemies.get(i).setStun(0);
					enemies.get(i).setSprite('O');
					break;

				default:
					enemies.get(i).move();
					break;
				}
				//if the hero is close to an ogre and is armed 
				if (nearOgreX(i) && hasClub && enemies.get(i).getStun() == 0)
				{
					enemies.get(i).setSprite('8');
					enemies.get(i).setStun(1);

				}
				if ((map.hasGuard() && nearGuard() && enemies.get(i).getSprite() == 'G') || enemies.get(i).hasClub() && nearClub(enemies.get(i)))
					return 2;
			}


			//if the hero catch the key 
			if (map.hasKey() && H.getCoordenateI() == K.getCoordenateI() && H.getCoordenateJ() == K.getCoordenateJ()) {
				H.setSprite('K');
				hasKey = true;
				K.setSprite(' ');
			}

			//to open the doors that need a key(make them from 'I' to 'S')
			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() - 1 && e.getCoordenateJ() == H.getCoordenateJ()
						&& e.getSprite() == 'I' && hasKey) {
					e.setSprite('S');
				}
			}

			//activates the lever
			if (H.getCoordenateI() == L.getCoordenateI() && H.getCoordenateJ() == L.getCoordenateJ() && hasKey) {
				for (Exit c : exits)
					c.setSprite('S');
			}

			//checks if the ogre is in the same position as the key,if it is change sprite to '$'
			if (map.hasOgre()) {
				if (O.getCoordenateI() == K.getCoordenateI() && O.getCoordenateJ() == K.getCoordenateJ())
					O.setSprite('$');
				else
					O.setSprite('O');
			}

			for(Character c :enemies){
				if(c.hasClub() && c.getClub().getCoordenateI()==K.getCoordenateI() && c.getClub().getCoordenateJ() == K.getCoordenateJ())
					c.getClub().setSprite('$');
				else
					c.getClub().setSprite('*');
			}


			//   DOWN
		} else if (s.charAt(0) == 's') {

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() + 1 && e.getCoordenateJ() == H.getCoordenateJ()
						&& e.getSprite() == 'S') {
					H.move2(1, 0);
					if (nextMap(map) == 0)
						return 0;
					else
						changeLevel();
				}
			}

			if (!map.validPos(H.getCoordenateI() + 1, H.getCoordenateJ()))
				return 1;

			H.move2(1, 0);


			if(!hasClub){
				if(map.hasHeroClub() && H.getSprite() == 'H')
				{
					if(H.getCoordenateI() == H_C.getCoordenateI() && H.getCoordenateJ() == H_C.getCoordenateJ())
					{
						H.setSprite('A');
						H_C.setCoordenateI(0);
						H_C.setCoordenateJ(0);
						hasClub = true;
					}
				}
			}
			//checks if the hero was captured or killed
			for (int i = 0; i < enemies.size(); i++) {

				switch(enemies.get(i).getStun()){

				//first turn of stun
				case 1: 
					enemies.get(i).setStun(2);
					break;
					
				//second turn of stun
				case 2:

					enemies.get(i).setStun(0);
					enemies.get(i).setSprite('O');
					break;

				default:
					enemies.get(i).move();
					break;
				}
				//if the hero is close to an ogre and is armed
				if (nearOgreX(i) && hasClub && enemies.get(i).getStun() == 0)
				{
					enemies.get(i).setSprite('8');
					enemies.get(i).setStun(1);

				}
				if ((map.hasGuard() && nearGuard() && enemies.get(i).getSprite() == 'G') || enemies.get(i).hasClub() && nearClub(enemies.get(i)))
					return 2;
			}

			if (map.hasKey() && H.getCoordenateI() == K.getCoordenateI() && H.getCoordenateJ() == K.getCoordenateJ()) {
				H.setSprite('K');
				hasKey = true;
				K.setSprite(' ');
			}

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() + 1 && e.getCoordenateJ() == H.getCoordenateJ()
						&& e.getSprite() == 'I' && hasKey) {
					e.setSprite('S');
				}
			}

			if (H.getCoordenateI() == L.getCoordenateI() && H.getCoordenateJ() == L.getCoordenateJ()) {
				for (Exit c : exits)
					c.setSprite('S');
			}

			if (map.hasOgre()) {
				if (O.getCoordenateI() == K.getCoordenateI() && O.getCoordenateJ() == K.getCoordenateJ())
					O.setSprite('$');
				else
					O.setSprite('O');
			}

			for(Character c :enemies){
				if(c.hasClub() && c.getClub().getCoordenateI()==K.getCoordenateI() && c.getClub().getCoordenateJ() == K.getCoordenateJ())
					c.getClub().setSprite('$');
				else
					c.getClub().setSprite('*');
			}

			//   LEFT
		} else if (s.charAt(0) == 'a') {

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() && e.getCoordenateJ() == (H.getCoordenateJ()-1)
						&& e.getSprite() == 'S') {
					H.move2(0, -1);
					if (nextMap(map) == 0)
						return 0;
					else{
						changeLevel();
						print();
					}
				}
			}

			if (!map.validPos(H.getCoordenateI(), H.getCoordenateJ() - 1))
				return 1;

			H.move2(0, -1);

			//checks if the hero picks up the club
			if(!hasClub){
				if(map.hasHeroClub() && H.getSprite() == 'H')
				{
					if(H.getCoordenateI() == H_C.getCoordenateI() && H.getCoordenateJ() == H_C.getCoordenateJ())
					{
						H.setSprite('A');
						H_C.setCoordenateI(0);
						H_C.setCoordenateJ(0);
						hasClub = true;
					}
				}
			}

			//checks if the hero was captured or killed
			for (int i = 0; i < enemies.size(); i++) {

				switch(enemies.get(i).getStun()){

				//first turn of stun
				case 1: 
					enemies.get(i).setStun(2);
					break;
					
				//second turn of stun
				case 2:

					enemies.get(i).setStun(0);
					enemies.get(i).setSprite('O');
					break;

				default:
					enemies.get(i).move();
					break;
				}
				//if the hero is close to an ogre and is armed
				if (nearOgreX(i) && hasClub && enemies.get(i).getStun() == 0)
				{
					enemies.get(i).setSprite('8');
					enemies.get(i).setStun(1);

				}
				if ((map.hasGuard() && nearGuard() && enemies.get(i).getSprite() == 'G') || enemies.get(i).hasClub() && nearClub(enemies.get(i)))
					return 2;
			}

			if (map.hasKey() && H.getCoordenateI() == K.getCoordenateI() && H.getCoordenateJ() == K.getCoordenateJ()) {
				H.setSprite('K');
				hasKey = true;
				K.setSprite(' ');
			}

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() && e.getCoordenateJ() == H.getCoordenateJ() - 1
						&& e.getSprite() == 'I' && hasKey) {
					e.setSprite('S');
				}
			}

			if (H.getCoordenateI() == L.getCoordenateI() && H.getCoordenateJ() == L.getCoordenateJ()) {
				for (Exit c : exits)
					c.setSprite('S');
			}

			if (map.hasOgre()) {
				if (O.getCoordenateI() == K.getCoordenateI() && O.getCoordenateJ() == K.getCoordenateJ())
					O.setSprite('$');
				else
					O.setSprite('O'); 
			}

			for(Character c :enemies){
				if(c.hasClub() && c.getClub().getCoordenateI()==K.getCoordenateI() && c.getClub().getCoordenateJ() == K.getCoordenateJ())
					c.getClub().setSprite('$');
				else
					c.getClub().setSprite('*');
			}

			//   RIGHT
		} else if (s.charAt(0) == 'd') {

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() && e.getCoordenateJ() == H.getCoordenateJ() + 1
						&& e.getSprite() == 'S') {
					H.move2(0, 1);
					if (nextMap(map) == 0)
						return 0;
					else
						changeLevel();
				}
			}

			if (!map.validPos(H.getCoordenateI(), H.getCoordenateJ() + 1))
				return 1;

			H.move2(0, 1);

			//checks if the hero picks up the club
			if(!hasClub){
				if(map.hasHeroClub() && H.getSprite() == 'H')
				{
					if(H.getCoordenateI() == H_C.getCoordenateI() && H.getCoordenateJ() == H_C.getCoordenateJ())
					{
						H.setSprite('A');
						H_C.setCoordenateI(0);
						H_C.setCoordenateJ(0);
						hasClub = true;
					}
				}
			}

			//checks if the hero was captured or killed
			for (int i = 0; i < enemies.size(); i++) {
				
				switch(enemies.get(i).getStun()){

				//first turn of stun
				case 1: 
					enemies.get(i).setStun(2);
					break;
					
				//second turn of stun
				case 2:

					enemies.get(i).setStun(0);
					enemies.get(i).setSprite('O');
					enemies.get(i).move();
					break;

				default:
					enemies.get(i).move();
					break;
				}
				//if the hero is close to an ogre and is armed
				if (nearOgreX(i) && hasClub && enemies.get(i).getStun() == 0)
				{
					enemies.get(i).setSprite('8');
					enemies.get(i).setStun(1);

				}
				if ((map.hasGuard() && nearGuard() && enemies.get(i).getSprite() == 'G') || enemies.get(i).hasClub() && nearClub(enemies.get(i)))
					return 2;
			}

			if (map.hasKey() && H.getCoordenateI() == K.getCoordenateI() && H.getCoordenateJ() == K.getCoordenateJ()) {
				H.setSprite('K');
				hasKey = true;
				K.setSprite(' ');
			}

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() && e.getCoordenateJ() == H.getCoordenateJ() + 1
						&& e.getSprite() == 'I' && hasKey) {
					e.setSprite('S');
				}
			}

			if (H.getCoordenateI() == L.getCoordenateI() && H.getCoordenateJ() == L.getCoordenateJ()) {
				for (Exit c : exits)
					c.setSprite('S');
			}

			if (map.hasOgre()) {
				if (O.getCoordenateI() == K.getCoordenateI() && O.getCoordenateJ() == K.getCoordenateJ())
					O.setSprite('$');
				else
					O.setSprite('O');
			}

			for(Character c :enemies){
				if(c.hasClub() && c.getClub().getCoordenateI()==K.getCoordenateI() && c.getClub().getCoordenateJ() == K.getCoordenateJ())
					c.getClub().setSprite('$');
				else
					c.getClub().setSprite('*');
			}

		} else
			return 1;

		return 4;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}
}
