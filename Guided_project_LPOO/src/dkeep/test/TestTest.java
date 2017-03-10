package dkeep.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import dkeep.logic.*;

public class TestTest {

	char [][] map = {{'X','X','X','X','X'},
			{'X',' ',' ',' ','X'},
			{' ',' ',' ',' ','X'},
			{' ',' ',' ',' ','X'},
			{'X','X','X','X','X'}};

	char [][] map_dungeon = new char[10][10];
	char[][] map_keep = new char [9][9];

	public void fillmapdungeon(){
		map_dungeon[1][4] = 'I';
		map_dungeon[3][4] = 'I';
		map_dungeon[3][2] = 'I';
		map_dungeon[8][2] = 'I';
		map_dungeon[8][4] = 'I';

		//added wall
		for(int i = 0; i < 10;i++){
			if(map_dungeon[i][0]!='I')
				map_dungeon[i][0]='X';			
		}	
		for(int i = 0; i < 10;i++){
			map_dungeon[i][9]='X';			
		}
		for(int j = 0; j < 10;j++){
			map_dungeon[0][j]='X';			
		}
		for(int j = 0; j < 10;j++){
			map_dungeon[9][j]='X';			
		}
		map_dungeon[1][6] =
				map_dungeon[2][1] = map_dungeon[2][2] =map_dungeon[2][4] = map_dungeon[2][5] = map_dungeon[2][6] = 
				map_dungeon[3][6] =
				map_dungeon[4][1] = map_dungeon[4][2] = map_dungeon[4][4] = map_dungeon[4][5] = map_dungeon[4][6] =
				map_dungeon[7][1] = map_dungeon[7][2] = map_dungeon[7][4] = map_dungeon[7][5] = map_dungeon[7][6] = map_dungeon[7][7] =
				map_dungeon[8][6] = 'X';
	}

	public void fillmapkeep(){
		//added wall
		for(int i = 0; i < 9;i++){
			if(map_keep[i][0]!='I')
				map_keep[i][0]='X';			
		}	
		for(int i = 0; i < 9;i++){
			map_keep[i][8]='X';			
		}
		for(int j = 0; j < 9;j++){
			map_keep[0][j]='X';			
		}
		for(int j = 0; j < 9;j++){
			map_keep[8][j]='X';			
		}
	}

	Hero H = new Hero(1,1,'H');
	Guard G = new Guard(1,3,'G',(int) Math.floor(Math.random()*3));
	Lever k = new Lever(3,1,'k');
	Ogre O = new Ogre(1,3,'O');
	Key K = new Key(3,1,'k');
	Vector<Exit> exits = new Vector<Exit>();
	{exits.add(new Exit(2,0,'I'));
	exits.add(new Exit(3,0,'I'));}


	@Test
	public void TestMoveHeroIntoFreeCell(){
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("d");
		assertEquals(1,game.getHero().getCoordenateI());
		assertEquals(2,game.getHero().getCoordenateJ());		
	}

	@Test
	public void Testcopy(){
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		char [][] map2 = game.copyMap();
		for(int i =0; i < 5; i++)
			for(int j=0; j < 5;j++)
				assertEquals(map2[i][j],game.getMap().getMap()[i][j]);	
	}

	@Test
	public void TestMoveHeroIntoWall() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("w");
		assertEquals(1,game.getHero().getCoordenateI());
		assertEquals(1,game.getHero().getCoordenateJ());
	}

	@Test
	public void TestMoveAdjGuard() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		Guard J =new Guard(1,3,'G',0);
		gameMap.setGuard(J);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		assertEquals(2,game.movement("d"));
	}

	@Test
	public void TestMoveTowardsDoorWhithioutKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("s");
		assertEquals(1,game.movement("a"));
	}

	@Test
	public void TestMoveTowardsLever() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("s");
		assertEquals(4,game.movement("s"));
		assertEquals('S',game.getExits().get(0).getSprite());
	}

	@Test
	public void TestMoveToExitWhithLever() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		assertEquals(0,game.movement("a"));
	}

	@Test
	public void TestMoveAdjOgre() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		assertEquals(2,game.movement("d"));
	}

	@Test
	public void TestMovetoKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		assertEquals('K',game.getHero().getSprite());
	}

	@Test
	public void TestMovetoExitWithoutKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		assertEquals(1,game.movement("a"));
	}

	@Test
	public void TestMovetoExitOpensDoor() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club());
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		game.movement("a");
		assertEquals('S',game.getExits().get(1).getSprite());
	}

	@Test
	public void TestMovetoExitWithKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		game.movement("a");
		assertEquals(0,game.movement("a"));
	}

	@Test
	public void TestConstructorDungeon(){
		GameMap dungeon = new Dungeon();
		fillmapdungeon();
		for(int i =0; i < 10; i++)
			for(int j=0; j < 10;j++)
				assertEquals(map_dungeon[i][j],dungeon.getMap()[i][j]);

		assertEquals(true,dungeon.hasLever());
		assertEquals(false,dungeon.hasOgre());
		assertEquals(false,dungeon.hasCLub());
		assertEquals(true,dungeon.hasGuard());
		assertEquals(false,dungeon.hasHeroClub());
		assertEquals(false,dungeon.hasKey());

	}

	@Test
	public void TestConstructorKeep(){
		GameMap keep = new Keep();
		fillmapkeep();
		for(int i =0; i < 9; i++)
			for(int j=0; j < 9;j++)
				assertEquals(map_keep[i][j],keep.getMap()[i][j]);

		assertEquals(false,keep.hasLever());
		assertEquals(true,keep.hasOgre());
		assertEquals(true,keep.hasCLub());
		assertEquals(false,keep.hasGuard());
		assertEquals(true,keep.hasHeroClub());
		assertEquals(true,keep.hasKey());
		
		Vector<Ogre>ogres = new Vector<Ogre>();
		ogres.add(new Ogre(1,4,'O'));
		ogres.add(new Ogre(2,5,'O'));
		assertEquals(1,keep.getOgres().get(0).getCoordenateI());
		assertEquals(7,keep.getHero().getCoordenateI());
		assertEquals(null,keep.getGuard());
		assertEquals(null,keep.getLever());
		assertEquals(6,keep.getHeroClub().getCoordenateI());
		assertEquals( 1,keep.getKey().getCoordenateI());
	}
	
	@Test
	public void TestConstructors(){
		Lever lever_teste =new Lever();
		lever_teste.setCoordenateI(1);
		lever_teste.setCoordenateJ(1);
		lever_teste.setSprite('k');

		assertEquals(1,lever_teste.getCoordenateI());
		assertEquals(1,lever_teste.getCoordenateJ());
		assertEquals('k',lever_teste.getSprite());
		assertEquals(0,lever_teste.getStun());
		
		//////
		Key k_teste = new Key();
		k_teste.setCoordenateI(1);
		k_teste.setCoordenateJ(1);
		k_teste.setSprite('k');
		
		assertEquals(1,k_teste.getCoordenateI());
		assertEquals(1,k_teste.getCoordenateJ());
		assertEquals('k',k_teste.getSprite());
		assertEquals(0,k_teste.getStun());
		
		/////
		Hero H_teste = new Hero();
		H_teste.setCoordenateI(1);
		H_teste.setCoordenateJ(1);
		H_teste.setSprite('k');
		
		assertEquals(1,H_teste.getCoordenateI());
		assertEquals(1,H_teste.getCoordenateJ());
		assertEquals('k',H_teste.getSprite());
		assertEquals(0,H_teste.getStun());
		
		////
		Exit e_teste = new Exit(); 
		e_teste.setCoordenateI(1);
		e_teste.setCoordenateJ(1);
		e_teste.setSprite('I');
		
		assertEquals(1,e_teste.getCoordenateI());
		assertEquals(1,e_teste.getCoordenateJ());
		assertEquals('I',e_teste.getSprite());
		assertEquals(0,e_teste.getStun());

	}
	
	@Test
	public void TestRealGame(){
		fillmapdungeon();
		Game g = new Game();
		for(int i =0; i < 10; i++)
			for(int j=0; j < 10;j++)
				assertEquals(map_dungeon[i][j],g.getMap().getMap()[i][j]);
		
		g.movement("d");
		g.movement("d");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("w");
		g.movement("w");
		g.movement("d");
		g.movement("d");
		g.movement("d");
		g.movement("d");
		g.movement("d");
		g.movement("s");
		g.movement("s");
		g.movement("a");
		g.movement("d");
		g.movement("w");
		g.movement("w");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		
		fillmapkeep();
		for(int i =0; i < 9; i++)
			for(int j=0; j < 9;j++)
				assertEquals(map_keep[i][j],g.getMap().getMap()[i][j]);
		
	}


}
