package dkeep.logic;

import java.util.*;

public class Game {

	private Vector<GameMap> maps = new Vector<GameMap>();
	private Hero H;
	private Guard G;
	private Ogre O;
	private Lever L;
	private Key K;
	private Vector<Exit> exits = new Vector<Exit>();
	private Vector<Character> enemies = new Vector<Character>();
	private Vector<Character> nonMovingCharacters = new Vector<Character>();
	private GameMap map;
	private boolean hasKey;

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

	//changes the level(restart charcters acording to the map
	public void changeLevel() {

		this.exits = new Vector<Exit>();
		this.enemies = new Vector<Character>();
		this.nonMovingCharacters = new Vector<Character>();

		this.exits = map.getExits();

		if (map.hasGuard()) {
			G = new Guard(map.getGuard().getCoordenateI(), map.getGuard().getCoordenateJ(), map.getGuard().getSprite());
			this.enemies.add(G);
		}

		if (map.hasOgre()) {
			O = new Ogre(map.getOgre().getCoordenateI(), map.getOgre().getCoordenateJ(), map.getOgre().getSprite());
			if(map.hasCLub()){
				O.setClub(map.getClub().getCoordenateI(),map.getClub().getCoordenateJ(),map.getClub().getSprite());
			}
			this.enemies.add(O);
		}

		if (map.hasKey()) {
			K = new Key(map.getKey().getCoordenateI(), map.getKey().getCoordenateJ(), map.getKey().getSprite());
			this.hasKey = false;
			this.nonMovingCharacters.add(K);
		} else
			this.hasKey = true;

		if (map.hasLever()) {
			L = new Lever(map.getLever().getCoordenateI(), map.getLever().getCoordenateJ(), map.getLever().getSprite());
			this.nonMovingCharacters.add(L);
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

	//checks if the hero was captured by he guard
	public boolean nearGuard() {

		if ((((H.getCoordenateI() == G.getCoordenateI() - 1) || (H.getCoordenateI() == G.getCoordenateI() + 1))
				&& (H.getCoordenateJ() == G.getCoordenateJ()))
				|| ((G.getCoordenateI() == H.getCoordenateI()) && ((H.getCoordenateJ() == G.getCoordenateJ() - 1)
						|| (H.getCoordenateJ() == G.getCoordenateJ() + 1))))
			return true;

		return false;
	}

	//checks if the hero was killed by the crazy ogre
	public boolean nearOgre() {

		if ((((H.getCoordenateI() == O.getCoordenateI() - 1) || (H.getCoordenateI() == O.getCoordenateI() + 1))
				&& (H.getCoordenateJ() == O.getCoordenateJ()))
				|| ((O.getCoordenateI() == H.getCoordenateI()) && ((H.getCoordenateJ() == O.getCoordenateJ() - 1)
						|| (H.getCoordenateJ() == O.getCoordenateJ() + 1))))
			return true;

		return false;
	}
	
	public boolean nearClub(Character c){

		if ((((H.getCoordenateI() == c.getClub().getCoordenateI()-1) || (H.getCoordenateI() == c.getClub().getCoordenateI()+1)) && (H.getCoordenateJ() == c.getClub().getCoordenateJ())) ||
				((c.getClub().getCoordenateI() == H.getCoordenateI()) && ((H.getCoordenateJ() == c.getClub().getCoordenateJ()-1) || (H.getCoordenateJ() == c.getClub().getCoordenateJ()+1))))		
			return true;

		return false;
	}

	

	//deals with the input and does most of the game logic
	public int movement(String s) {

		if (s.charAt(0) == 'w') {

			//if u can pass a level,change map/level or finish and win
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

			//checks if the hero was captured or killed
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).move();
				if ((map.hasGuard() && nearGuard()) || (map.hasOgre() && nearOgre())|| (enemies.get(i).hasClub() && nearClub(enemies.get(i))))
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
			if (H.getCoordenateI() == L.getCoordenateI() && H.getCoordenateJ() == L.getCoordenateJ()) {
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

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).move();
				if ((map.hasGuard() && nearGuard()) || (map.hasOgre() && nearOgre())|| (enemies.get(i).hasClub() && nearClub(enemies.get(i))))
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

		} else if (s.charAt(0) == 'a') {

			for (Exit e : exits) {
				if (e.getCoordenateI() == H.getCoordenateI() && e.getCoordenateJ() == (H.getCoordenateJ()-1)
						&& e.getSprite() == 'S') {
					System.out.println("pop");
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

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).move();
				if ((map.hasGuard() && nearGuard()) || (map.hasOgre() && nearOgre())|| (enemies.get(i).hasClub() && nearClub(enemies.get(i))))
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

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).move();
				if ((map.hasGuard() && nearGuard()) || (map.hasOgre() && nearOgre())|| (enemies.get(i).hasClub() && nearClub(enemies.get(i))))
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
